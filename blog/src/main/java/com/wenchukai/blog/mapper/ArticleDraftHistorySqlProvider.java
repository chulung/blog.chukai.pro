package com.wenchukai.blog.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.wenchukai.blog.model.ArticleDraftHistoryExample.Criteria;
import com.wenchukai.blog.model.ArticleDraftHistoryExample.Criterion;
import com.wenchukai.blog.model.ArticleDraftHistoryExample;
import com.wenchukai.blog.model.ArticleDraftHistoryWithBLOBs;
import java.util.List;
import java.util.Map;

public class ArticleDraftHistorySqlProvider {

    public String countByExample(ArticleDraftHistoryExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("article_draft_history");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(ArticleDraftHistoryWithBLOBs record) {
        BEGIN();
        INSERT_INTO("article_draft_history");
        
        VALUES("id", "#{id,jdbcType=INTEGER}");
        
        if (record.getArticleId() != null) {
            VALUES("article_Id", "#{articleId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAuthor() != null) {
            VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getMender() != null) {
            VALUES("mender", "#{mender,jdbcType=VARCHAR}");
        }
        
        if (record.getIsPublish() != null) {
            VALUES("is_publish", "#{isPublish,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            VALUES("type_id", "#{typeId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getVersion() != null) {
            VALUES("version", "#{version,jdbcType=INTEGER}");
        }
        
        if (record.getChangLog() != null) {
            VALUES("chang_log", "#{changLog,jdbcType=VARCHAR}");
        }
        
        if (record.getContext() != null) {
            VALUES("context", "#{context,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getHtmlContext() != null) {
            VALUES("html_context", "#{htmlContext,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExampleWithBLOBs(ArticleDraftHistoryExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("article_Id");
        SELECT("title");
        SELECT("create_time");
        SELECT("update_time");
        SELECT("author");
        SELECT("user_id");
        SELECT("mender");
        SELECT("is_publish");
        SELECT("type_id");
        SELECT("is_delete");
        SELECT("version");
        SELECT("chang_log");
        SELECT("context");
        SELECT("html_context");
        FROM("article_draft_history");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String selectByExample(ArticleDraftHistoryExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("article_Id");
        SELECT("title");
        SELECT("create_time");
        SELECT("update_time");
        SELECT("author");
        SELECT("user_id");
        SELECT("mender");
        SELECT("is_publish");
        SELECT("type_id");
        SELECT("is_delete");
        SELECT("version");
        SELECT("chang_log");
        FROM("article_draft_history");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ArticleDraftHistoryWithBLOBs record = (ArticleDraftHistoryWithBLOBs) parameter.get("record");
        ArticleDraftHistoryExample example = (ArticleDraftHistoryExample) parameter.get("example");
        
        BEGIN();
        UPDATE("article_draft_history");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getArticleId() != null) {
            SET("article_Id = #{record.articleId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAuthor() != null) {
            SET("author = #{record.author,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            SET("user_id = #{record.userId,jdbcType=INTEGER}");
        }
        
        if (record.getMender() != null) {
            SET("mender = #{record.mender,jdbcType=VARCHAR}");
        }
        
        if (record.getIsPublish() != null) {
            SET("is_publish = #{record.isPublish,jdbcType=INTEGER}");
        }
        
        if (record.getTypeId() != null) {
            SET("type_id = #{record.typeId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDelete() != null) {
            SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getVersion() != null) {
            SET("version = #{record.version,jdbcType=INTEGER}");
        }
        
        if (record.getChangLog() != null) {
            SET("chang_log = #{record.changLog,jdbcType=VARCHAR}");
        }
        
        if (record.getContext() != null) {
            SET("context = #{record.context,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getHtmlContext() != null) {
            SET("html_context = #{record.htmlContext,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("article_draft_history");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("article_Id = #{record.articleId,jdbcType=INTEGER}");
        SET("title = #{record.title,jdbcType=VARCHAR}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        SET("author = #{record.author,jdbcType=VARCHAR}");
        SET("user_id = #{record.userId,jdbcType=INTEGER}");
        SET("mender = #{record.mender,jdbcType=VARCHAR}");
        SET("is_publish = #{record.isPublish,jdbcType=INTEGER}");
        SET("type_id = #{record.typeId,jdbcType=INTEGER}");
        SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        SET("version = #{record.version,jdbcType=INTEGER}");
        SET("chang_log = #{record.changLog,jdbcType=VARCHAR}");
        SET("context = #{record.context,jdbcType=LONGVARCHAR}");
        SET("html_context = #{record.htmlContext,jdbcType=LONGVARCHAR}");
        
        ArticleDraftHistoryExample example = (ArticleDraftHistoryExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("article_draft_history");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("article_Id = #{record.articleId,jdbcType=INTEGER}");
        SET("title = #{record.title,jdbcType=VARCHAR}");
        SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        SET("author = #{record.author,jdbcType=VARCHAR}");
        SET("user_id = #{record.userId,jdbcType=INTEGER}");
        SET("mender = #{record.mender,jdbcType=VARCHAR}");
        SET("is_publish = #{record.isPublish,jdbcType=INTEGER}");
        SET("type_id = #{record.typeId,jdbcType=INTEGER}");
        SET("is_delete = #{record.isDelete,jdbcType=INTEGER}");
        SET("version = #{record.version,jdbcType=INTEGER}");
        SET("chang_log = #{record.changLog,jdbcType=VARCHAR}");
        
        ArticleDraftHistoryExample example = (ArticleDraftHistoryExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    protected void applyWhere(ArticleDraftHistoryExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}