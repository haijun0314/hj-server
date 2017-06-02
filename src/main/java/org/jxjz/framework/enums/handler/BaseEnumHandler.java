package org.jxjz.framework.enums.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.jxjz.framework.enums.IHasValueEnum;

public class BaseEnumHandler<E extends Enum<?> & IHasValueEnum> extends BaseTypeHandler<IHasValueEnum> {
	  private Class<E> clazz;
	  
	    public BaseEnumHandler(Class<E> enumType) {
	        if (enumType == null)
	            throw new IllegalArgumentException("Type argument cannot be null");
	 
	        this.clazz = enumType;
	    }
	 
	    @Override
	    public void setNonNullParameter(PreparedStatement ps, int i, IHasValueEnum parameter, JdbcType jdbcType)
	            throws SQLException {
	        ps.setString(i, parameter.getValue());
	    }
	 
	    @Override
	    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
	        return CodeEnumUtil.codeOf(clazz, rs.getString(columnName));
	    }
	 
	    @Override
	    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	        return CodeEnumUtil.codeOf(clazz, rs.getString(columnIndex));
	    }
	 
	    @Override
	    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	        return CodeEnumUtil.codeOf(clazz, cs.getString(columnIndex));
	    }
}
