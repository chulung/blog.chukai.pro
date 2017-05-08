package com.chulung.search.core;

import com.chulung.search.config.CSearchConfig;
import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.*;

import static com.chulung.search.core.CSearchDocument.CONTEXT;
import static com.chulung.search.core.CSearchDocument.ID;
import static com.chulung.search.core.CSearchDocument.TITLE;

/**
 * 创建索引及查询操作
 * Created by chulung on 2016/11/7.
 */

@Component
public class CSearchImpl implements InitializingBean,DisposableBean,CSearch {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private static final int DEFAULT_RESULT_SIZE = 10;

    private Directory directory;
    @Autowired
    private CSearchConfig cSearchConfig;

    /**
     * 使用HanLP中文分词，该分词区分大小写，因此索引及查询均对入参做了toLowerCase操作
     */
    private Analyzer analyzer = new HanLPAnalyzer();
    private DirectoryReader reader;
    private IndexWriter indexWriter;


    @Override
    public boolean createIndex(CSearchDocument doc){
       return this.createIndex(Collections.singletonList(doc));
    }

    @Override
    public boolean createIndex(List<CSearchDocument> docs) {
        IndexWriter writer = null;
        try {
            writer = this.getIndexWriter();
            for (CSearchDocument doc:docs){
                //创建前尝试先删除已有的
                logger.info("tryDelete id={}",doc.getId());
                writer.deleteDocuments(new Term(ID,doc.getId()));
                Document document = new Document();
                document.add(new StringField(ID, doc.getId(), Field.Store.YES));
                //HanLp区分大小写，所以全转小写
                document.add(new TextField(TITLE, doc.getTitle().toLowerCase(), Field.Store.YES));
                document.add(new TextField(CONTEXT, doc.getContent().toLowerCase(), Field.Store.YES));
                writer.addDocument(document);
                logger.info("createIndex id={}",doc.getId());
            }
        } catch (Exception e) {
            try {
                if (writer!=null)
                writer.rollback();
            } catch (Exception e1) {
                logger.error("",e1);
            }
            return false;
        } finally {
            if (writer != null)
                try {
                    writer.commit();
                } catch (Exception e) {
                    logger.error("",e);
                }

        }
        return true;
    }

    @Override
    public void clearAll() {
        try {

            IndexWriter writer = this.getIndexWriter();
            writer.deleteAll();
            writer.commit();
        } catch (Exception e) {
            logger.error("",e);
        }
    }

    @Override
    public List<CSearchDocument> search(String queryStr, int num) throws Exception {
        if (StringUtils.isBlank(queryStr)) {
            return Collections.emptyList();
        }
        //搜索标题和内容两个字段
        String[] fields = {TITLE, CONTEXT};
        QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
        //HanLp区分大小写，所以全转小写
        Query query = queryParser.parse(queryStr.toLowerCase());
        IndexSearcher searcher = new IndexSearcher(getReader());
        TopDocs topDocs = searcher.search(query, num);
        //设置高亮格式
        Highlighter highlighter = new Highlighter(this.cSearchConfig.getHighLighterFormatter(), new QueryScorer(query));
        //设置返回字符串长度
        highlighter.setTextFragmenter(new SimpleFragmenter(150));
        List<CSearchDocument> result = new ArrayList<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            //这里的.replaceAll("\\s*", "")是必须的，\r\n这样的空白字符会导致高亮标签错位
            String content = doc.get(CONTEXT).replaceAll("\\s*", "");
            //没有高亮字符会返回null
            String highContext = highlighter.getBestFragment(analyzer, CONTEXT, content);
            String title = doc.get(TITLE).replaceAll("\\s*", "");
            String highTitle = highlighter.getBestFragment(analyzer, TITLE, title);
            result.add(new CSearchDocument(doc.get(CSearchDocument.ID), highTitle==null?title:highTitle, highContext==null?subContext(content):highContext));
        }
        return result;
    }

    /**
     * 根据 {@link CSearchConfig#fragmentSize}截取片段长度
     * @param content
     * @return
     */
    private String subContext(String content) {
        return  content.length()>cSearchConfig.getFragmentSize()?content.substring(0,cSearchConfig.getFragmentSize()):content;
    }

    /**
     * reader 返回当前reader 如果文档有更新则新打开一个
     * @return
     * @throws Exception
     */
    private DirectoryReader getReader() throws Exception {
        if (reader==null){
            this.reader=DirectoryReader.open(directory);
        }
        //有更新则重新打开,读入新增加的增量索引内容，满足实时查询需求
        DirectoryReader newReader = DirectoryReader.openIfChanged((DirectoryReader)reader,  getIndexWriter(), false);
        if (newReader != null) {
            reader.close();
            reader = newReader;
        }
        return reader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (cSearchConfig.isRamDirectory()){
            directory = new RAMDirectory();
        }else{
             directory = FSDirectory.open(Paths.get(this.cSearchConfig.getIndexStorePath()));
        }
         indexWriter=new IndexWriter(directory, new IndexWriterConfig(analyzer));
    }

    private IndexWriter getIndexWriter() {
        return indexWriter;
    }

    @Override
    public List<CSearchDocument> search(String key) throws Exception {
        return  this.search(key, DEFAULT_RESULT_SIZE);
    }

    @Override
    public void destroy() throws Exception {
        indexWriter.close();
        directory.close();
    }
}
