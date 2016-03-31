package com.wenchukai.ckbatis.typehandler;

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

@MappedTypes({ LocalDateTime.class })
@MappedJdbcTypes({ JdbcType.TIMESTAMP })
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setDate(i, new Date(Date.from(parameter.atZone(ZoneId.systemDefault()).toInstant()).getTime()));
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
