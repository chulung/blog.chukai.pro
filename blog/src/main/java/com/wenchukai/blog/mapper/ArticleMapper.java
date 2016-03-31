package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.Article;
import com.wenchukai.blog.model.ArticleExample;
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

public interface ArticleMapper {
    @SelectProvider(type=ArticleSqlProvider.class, method="countByExample")
    int countByExample(ArticleExample example);

    @Delete({
        "delete from article",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into article (id, title, ",
        "create_time, update_time, ",
        "author, user_Id, ",
        "mender, type_id, ",
        "derivation_Url, version, ",
        "isdelete, chang_log, ",
        "context)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{author,jdbcType=VARCHAR}, #{userId,jdbcType=INTEGER}, ",
        "#{mender,jdbcType=VARCHAR}, #{typeId,jdbcType=INTEGER}, ",
        "#{derivationUrl,jdbcType=VARCHAR}, #{version,jdbcType=INTEGER}, ",
        "#{isdelete,jdbcType=INTEGER}, #{changLog,jdbcType=VARCHAR}, ",
        "#{context,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(Article record);

    @InsertProvider(type=ArticleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insertSelective(Article record);

    @SelectProvider(type=ArticleSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_Id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="mender", property="mender", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="derivation_Url", property="derivationUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="isdelete", property="isdelete", jdbcType=JdbcType.INTEGER),
        @Result(column="chang_log", property="changLog", jdbcType=JdbcType.VARCHAR),
        @Result(column="context", property="context", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    @SelectProvider(type=ArticleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_Id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="mender", property="mender", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="derivation_Url", property="derivationUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="isdelete", property="isdelete", jdbcType=JdbcType.INTEGER),
        @Result(column="chang_log", property="changLog", jdbcType=JdbcType.VARCHAR)
    })
    List<Article> selectByExample(ArticleExample example);

    @Select({
        "select",
        "id, title, create_time, update_time, author, user_Id, mender, type_id, derivation_Url, ",
        "version, isdelete, chang_log, context",
        "from article",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_Id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="mender", property="mender", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="derivation_Url", property="derivationUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="isdelete", property="isdelete", jdbcType=JdbcType.INTEGER),
        @Result(column="chang_log", property="changLog", jdbcType=JdbcType.VARCHAR),
        @Result(column="context", property="context", jdbcType=JdbcType.LONGVARCHAR)
    })
    Article selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ArticleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    @UpdateProvider(type=ArticleSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    @UpdateProvider(type=ArticleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    @UpdateProvider(type=ArticleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Article record);

    @Update({
        "update article",
        "set title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "user_Id = #{userId,jdbcType=INTEGER},",
          "mender = #{mender,jdbcType=VARCHAR},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "derivation_Url = #{derivationUrl,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=INTEGER},",
          "isdelete = #{isdelete,jdbcType=INTEGER},",
          "chang_log = #{changLog,jdbcType=VARCHAR},",
          "context = #{context,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Article record);

    @Update({
        "update article",
        "set title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "user_Id = #{userId,jdbcType=INTEGER},",
          "mender = #{mender,jdbcType=VARCHAR},",
          "type_id = #{typeId,jdbcType=INTEGER},",
          "derivation_Url = #{derivationUrl,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=INTEGER},",
          "isdelete = #{isdelete,jdbcType=INTEGER},",
          "chang_log = #{changLog,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Article record);
}