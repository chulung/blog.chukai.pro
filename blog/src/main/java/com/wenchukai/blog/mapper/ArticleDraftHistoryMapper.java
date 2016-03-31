package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.ArticleDraft;
import com.wenchukai.blog.model.ArticleDraftHistory;
import com.wenchukai.blog.model.ArticleDraftHistoryExample;
import com.wenchukai.blog.model.ArticleDraftHistoryWithBLOBs;
import com.wenchukai.blog.model.ArticleDraftWithBLOBs;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ArticleDraftHistoryMapper {
    @SelectProvider(type=ArticleDraftHistorySqlProvider.class, method="countByExample")
    int countByExample(ArticleDraftHistoryExample example);

    @Insert({
        "insert into article_draft_history (id, article_Id, ",
        "title, create_time, ",
        "update_time, author, ",
        "user_id, mender, ",
        "is_publish, type_id, ",
        "is_delete, version, ",
        "chang_log, context, ",
        "html_context)",
        "values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{author,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER}, #{mender,jdbcType=VARCHAR}, ",
        "#{isPublish,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER}, ",
        "#{isDelete,jdbcType=INTEGER}, #{version,jdbcType=INTEGER}, ",
        "#{changLog,jdbcType=VARCHAR}, #{context,jdbcType=LONGVARCHAR}, ",
        "#{htmlContext,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(ArticleDraft record);

    @InsertProvider(type=ArticleDraftHistorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insertSelective(ArticleDraftHistory record);

    @SelectProvider(type=ArticleDraftHistorySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="article_Id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="mender", property="mender", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_publish", property="isPublish", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="chang_log", property="changLog", jdbcType=JdbcType.VARCHAR),
        @Result(column="context", property="context", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="html_context", property="htmlContext", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ArticleDraftHistoryWithBLOBs> selectByExampleWithBLOBs(ArticleDraftHistoryExample example);

    @SelectProvider(type=ArticleDraftHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="article_Id", property="articleId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="mender", property="mender", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_publish", property="isPublish", jdbcType=JdbcType.INTEGER),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="chang_log", property="changLog", jdbcType=JdbcType.VARCHAR)
    })
    List<ArticleDraftHistory> selectByExample(ArticleDraftHistoryExample example);

    @UpdateProvider(type=ArticleDraftHistorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ArticleDraftHistoryWithBLOBs record, @Param("example") ArticleDraftHistoryExample example);

    @UpdateProvider(type=ArticleDraftHistorySqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ArticleDraftHistoryWithBLOBs record, @Param("example") ArticleDraftHistoryExample example);

    @UpdateProvider(type=ArticleDraftHistorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ArticleDraftHistory record, @Param("example") ArticleDraftHistoryExample example);
}