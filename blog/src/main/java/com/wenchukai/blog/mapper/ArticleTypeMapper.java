package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.ArticleType;
import com.wenchukai.blog.model.ArticleTypeExample;
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

public interface ArticleTypeMapper {
    @SelectProvider(type=ArticleTypeSqlProvider.class, method="countByExample")
    int countByExample(ArticleTypeExample example);

    @Delete({
        "delete from article_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into article_type (id, type_name, ",
        "create_time, update_time, ",
        "user_id)",
        "values (#{id,jdbcType=INTEGER}, #{typeName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(ArticleType record);

    @InsertProvider(type=ArticleTypeSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insertSelective(ArticleType record);

    @SelectProvider(type=ArticleTypeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<ArticleType> selectByExample(ArticleTypeExample example);

    @Select({
        "select",
        "id, type_name, create_time, update_time, user_id",
        "from article_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_name", property="typeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    ArticleType selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ArticleTypeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ArticleType record, @Param("example") ArticleTypeExample example);

    @UpdateProvider(type=ArticleTypeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ArticleType record, @Param("example") ArticleTypeExample example);

    @UpdateProvider(type=ArticleTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ArticleType record);

    @Update({
        "update article_type",
        "set type_name = #{typeName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ArticleType record);
}