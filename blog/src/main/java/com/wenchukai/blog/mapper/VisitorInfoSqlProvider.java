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

import com.wenchukai.blog.model.VisitorInfo;
import com.wenchukai.blog.model.VisitorInfoExample.Criteria;
import com.wenchukai.blog.model.VisitorInfoExample.Criterion;
import com.wenchukai.blog.model.VisitorInfoExample;
import java.util.List;
import java.util.Map;

public class VisitorInfoSqlProvider {

    public String countByExample(VisitorInfoExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("visitor_info");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(VisitorInfo record) {
        BEGIN();
        INSERT_INTO("visitor_info");
        
        VALUES("id", "#{id,jdbcType=INTEGER}");
        
        if (record.getIp() != null) {
            VALUES("ip", "#{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAgent() != null) {
            VALUES("user_Agent", "#{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessTime() != null) {
            VALUES("access_Time", "#{accessTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccessUrl() != null) {
            VALUES("access_Url", "#{accessUrl,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(VisitorInfoExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("ip");
        SELECT("user_Agent");
        SELECT("access_Time");
        SELECT("access_Url");
        FROM("visitor_info");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        VisitorInfo record = (VisitorInfo) parameter.get("record");
        VisitorInfoExample example = (VisitorInfoExample) parameter.get("example");
        
        BEGIN();
        UPDATE("visitor_info");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getIp() != null) {
            SET("ip = #{record.ip,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAgent() != null) {
            SET("user_Agent = #{record.userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessTime() != null) {
            SET("access_Time = #{record.accessTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccessUrl() != null) {
            SET("access_Url = #{record.accessUrl,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("visitor_info");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("ip = #{record.ip,jdbcType=VARCHAR}");
        SET("user_Agent = #{record.userAgent,jdbcType=VARCHAR}");
        SET("access_Time = #{record.accessTime,jdbcType=TIMESTAMP}");
        SET("access_Url = #{record.accessUrl,jdbcType=VARCHAR}");
        
        VisitorInfoExample example = (VisitorInfoExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(VisitorInfo record) {
        BEGIN();
        UPDATE("visitor_info");
        
        if (record.getIp() != null) {
            SET("ip = #{ip,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAgent() != null) {
            SET("user_Agent = #{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessTime() != null) {
            SET("access_Time = #{accessTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getAccessUrl() != null) {
            SET("access_Url = #{accessUrl,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(VisitorInfoExample example, boolean includeExamplePhrase) {
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