package com.chulung.craft.mapper.typehandler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedJdbcTypes(value = { JdbcType.TIMESTAMP }, includeNullJdbcType = true)
@MappedTypes(java.time.LocalDateTime.class)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setTimestamp(i, new Timestamp(Date.from(parameter.atZone(ZoneId.systemDefault()).toInstant()).getTime()));
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return this.getLocalDateTime(rs.getTimestamp(columnName));
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return this.getLocalDateTime(rs.getTimestamp(columnIndex));
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return this.getLocalDateTime(cs.getTimestamp(columnIndex));
	}

	private LocalDateTime getLocalDateTime(Timestamp date) {
		return date.toLocalDateTime();
	}

}
