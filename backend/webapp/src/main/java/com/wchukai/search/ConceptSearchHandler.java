package com.wchukai.search;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wchukai.web.mapper.ConceptMapper;
import com.wchukai.web.model.BaseComponent;
import com.wchukai.web.model.Concept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author chukai
 */
@Component
public class ConceptSearchHandler extends BaseComponent implements ApplicationListener<ContextRefreshedEvent> {

    public static final int PAGE_SIZE = 1000;
    @Autowired
    private ConceptMapper conceptMapper;

    private TrieTree trieTree = new TrieTree();
    private List<String> conceptNames = new ArrayList();
    private Random random = new Random();

    public List<String> liveSearch(String word) {
        return trieTree.prefixeMatching(word);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            this.resetTree();
        }).start();
    }

    private void resetTree() {
        trieTree = new TrieTree();
        int pageNum = 1;
        while (true) {
            PageHelper.startPage(pageNum, PAGE_SIZE);
            Page<Concept> articles = (Page<Concept>) this.conceptMapper.selectAll();
            for (Concept concept : articles) {
                trieTree.insert(concept.getConceptName());
                conceptNames.add(concept.getConceptName());
            }
            if (articles.size() < PAGE_SIZE) {
                break;
            }
            pageNum++;
        }
    }

    public List<String> randomSelect(int count) {
        Set<String> rs = new HashSet<>(count);
        for (int i = 0; i < count; i++) {
            rs.add(this.conceptNames.get(random.nextInt(this.conceptNames.size())));
        }
        return new ArrayList<>(rs);
    }
}
