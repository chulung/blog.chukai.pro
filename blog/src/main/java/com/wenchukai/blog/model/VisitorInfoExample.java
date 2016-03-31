package com.wenchukai.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitorInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VisitorInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andUserAgentIsNull() {
            addCriterion("user_Agent is null");
            return (Criteria) this;
        }

        public Criteria andUserAgentIsNotNull() {
            addCriterion("user_Agent is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgentEqualTo(String value) {
            addCriterion("user_Agent =", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotEqualTo(String value) {
            addCriterion("user_Agent <>", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThan(String value) {
            addCriterion("user_Agent >", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThanOrEqualTo(String value) {
            addCriterion("user_Agent >=", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThan(String value) {
            addCriterion("user_Agent <", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThanOrEqualTo(String value) {
            addCriterion("user_Agent <=", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLike(String value) {
            addCriterion("user_Agent like", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotLike(String value) {
            addCriterion("user_Agent not like", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentIn(List<String> values) {
            addCriterion("user_Agent in", values, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotIn(List<String> values) {
            addCriterion("user_Agent not in", values, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentBetween(String value1, String value2) {
            addCriterion("user_Agent between", value1, value2, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotBetween(String value1, String value2) {
            addCriterion("user_Agent not between", value1, value2, "userAgent");
            return (Criteria) this;
        }

        public Criteria andAccessTimeIsNull() {
            addCriterion("access_Time is null");
            return (Criteria) this;
        }

        public Criteria andAccessTimeIsNotNull() {
            addCriterion("access_Time is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTimeEqualTo(Date value) {
            addCriterion("access_Time =", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeNotEqualTo(Date value) {
            addCriterion("access_Time <>", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeGreaterThan(Date value) {
            addCriterion("access_Time >", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("access_Time >=", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeLessThan(Date value) {
            addCriterion("access_Time <", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeLessThanOrEqualTo(Date value) {
            addCriterion("access_Time <=", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeIn(List<Date> values) {
            addCriterion("access_Time in", values, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeNotIn(List<Date> values) {
            addCriterion("access_Time not in", values, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeBetween(Date value1, Date value2) {
            addCriterion("access_Time between", value1, value2, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeNotBetween(Date value1, Date value2) {
            addCriterion("access_Time not between", value1, value2, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessUrlIsNull() {
            addCriterion("access_Url is null");
            return (Criteria) this;
        }

        public Criteria andAccessUrlIsNotNull() {
            addCriterion("access_Url is not null");
            return (Criteria) this;
        }

        public Criteria andAccessUrlEqualTo(String value) {
            addCriterion("access_Url =", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotEqualTo(String value) {
            addCriterion("access_Url <>", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlGreaterThan(String value) {
            addCriterion("access_Url >", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlGreaterThanOrEqualTo(String value) {
            addCriterion("access_Url >=", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlLessThan(String value) {
            addCriterion("access_Url <", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlLessThanOrEqualTo(String value) {
            addCriterion("access_Url <=", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlLike(String value) {
            addCriterion("access_Url like", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotLike(String value) {
            addCriterion("access_Url not like", value, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlIn(List<String> values) {
            addCriterion("access_Url in", values, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotIn(List<String> values) {
            addCriterion("access_Url not in", values, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlBetween(String value1, String value2) {
            addCriterion("access_Url between", value1, value2, "accessUrl");
            return (Criteria) this;
        }

        public Criteria andAccessUrlNotBetween(String value1, String value2) {
            addCriterion("access_Url not between", value1, value2, "accessUrl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}