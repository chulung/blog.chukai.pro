package com.wenchukai.blog.mapper;

import com.wenchukai.blog.model.VisitorInfo;
import com.wenchukai.blog.model.VisitorInfoExample;
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

public interface VisitorInfoMapper {
    @SelectProvider(type=VisitorInfoSqlProvider.class, method="countByExample")
    int countByExample(VisitorInfoExample example);

    @Delete({
        "delete from visitor_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into visitor_info (id, ip, ",
        "user_Agent, access_Time, ",
        "access_Url)",
        "values (#{id,jdbcType=INTEGER}, #{ip,jdbcType=VARCHAR}, ",
        "#{userAgent,jdbcType=VARCHAR}, #{accessTime,jdbcType=TIMESTAMP}, ",
        "#{accessUrl,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insert(VisitorInfo record);

    @InsertProvider(type=VisitorInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Integer.class)
    int insertSelective(VisitorInfo record);

    @SelectProvider(type=VisitorInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_Agent", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_Time", property="accessTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="access_Url", property="accessUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<VisitorInfo> selectByExample(VisitorInfoExample example);

    @Select({
        "select",
        "id, ip, user_Agent, access_Time, access_Url",
        "from visitor_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_Agent", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_Time", property="accessTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="access_Url", property="accessUrl", jdbcType=JdbcType.VARCHAR)
    })
    VisitorInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VisitorInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") VisitorInfo record, @Param("example") VisitorInfoExample example);

    @UpdateProvider(type=VisitorInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") VisitorInfo record, @Param("example") VisitorInfoExample example);

    @UpdateProvider(type=VisitorInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisitorInfo record);

    @Update({
        "update visitor_info",
        "set ip = #{ip,jdbcType=VARCHAR},",
          "user_Agent = #{userAgent,jdbcType=VARCHAR},",
          "access_Time = #{accessTime,jdbcType=TIMESTAMP},",
          "access_Url = #{accessUrl,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VisitorInfo record);
}