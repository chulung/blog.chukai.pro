package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.ArticleDraft;
import com.wenchukai.blog.model.ArticleDraftExample;
import com.wenchukai.blog.model.ArticleDraftWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ArticleDraftMapper {
    @SelectProvider(type=ArticleDraftSqlProvider.class, method="countByExample")
    int countByExample(ArticleDraftExample example);

    @Delete({
        "delete from article_draft",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into article_draft (id, article_Id, ",
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
    int insert(ArticleDraftWithBLOBs record);

    @InsertProvider(type=ArticleDraftSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insertSelective(ArticleDraftWithBLOBs record);

    @SelectProvider(type=ArticleDraftSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
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
    List<ArticleDraftWithBLOBs> selectByExampleWithBLOBs(ArticleDraftExample example);

    @SelectProvider(type=ArticleDraftSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
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
    List<ArticleDraft> selectByExample(ArticleDraftExample example);

    @Select({
        "select",
        "id, article_Id, title, create_time, update_time, author, user_id, mender, is_publish, ",
        "type_id, is_delete, version, chang_log, context, html_context",
        "from article_draft",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
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
    ArticleDraftWithBLOBs selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ArticleDraftSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ArticleDraftWithBLOBs record, @Param("example") ArticleDraftExample example);

    @UpdateProvider(type=ArticleDraftSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ArticleDraftWithBLOBs record, @Param("example") ArticleDraftExample example);

    @UpdateProvider(type=ArticleDraftSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ArticleDraft record, @Param("example") ArticleDraftExample example);

    @UpdateProvider(type=ArticleDraftSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ArticleDraftWithBLOBs record);

    @Update({
        "update article_draft",
        "set article_Id = #{articleId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "mender = #{mender,jdbcType=VARCHAR},",
          "is_publish = #{isPublish,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "version = #{version,jdbcType=INTEGER},",
          "chang_log = #{changLog,jdbcType=VARCHAR},",
          "context = #{context,jdbcType=LONGVARCHAR},",
          "html_context = #{htmlContext,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ArticleDraftWithBLOBs record);

    @Update({
        "update article_draft",
        "set article_Id = #{articleId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "mender = #{mender,jdbcType=VARCHAR},",
          "is_publish = #{isPublish,jdbcType=INTEGER},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "version = #{version,jdbcType=INTEGER},",
          "chang_log = #{changLog,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ArticleDraft record);
}