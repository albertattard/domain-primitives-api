package com.javacreed.api.domain.primitives.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import com.javacreed.api.domain.primitives.lang.LongBasedDomainPrimitive;
import org.junit.Test;
import org.mockito.Mockito;

import com.javacreed.api.domain.primitives.lang.ByteBasedDomainPrimitive;
import com.javacreed.api.domain.primitives.lang.IntBasedDomainPrimitive;

public class PreparedStatementUtilsTest {

  @Test
  public void should_invoke_setByte_when_given_Byte_value_is_not_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, Byte.valueOf((byte) 2));

    Mockito.verify(ps).setByte(1, (byte) 2);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setInt_when_given_Integer_value_is_not_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setInt(ps, 1, Integer.valueOf(2));

    Mockito.verify(ps).setInt(1, 2);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_INTEGER_when_given_an_empty_IntBasedDomainPrimitive_optional() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setInt(ps, 1, Optional.empty());

    Mockito.verify(ps).setNull(1, Types.INTEGER);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_INTEGER_when_given_Byte_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setInt(ps, 1, (Integer) null);

    Mockito.verify(ps).setNull(1, Types.INTEGER);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_INTEGER_when_given_IntBasedDomainPrimitive_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setInt(ps, 1, (IntBasedDomainPrimitive) null);

    Mockito.verify(ps).setNull(1, Types.INTEGER);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_TINYINT_when_given_an_empty_ByteBasedDomainPrimitive_optional() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, Optional.empty());

    Mockito.verify(ps).setNull(1, Types.TINYINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_TINYINT_when_given_Byte_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, (Byte) null);

    Mockito.verify(ps).setNull(1, Types.TINYINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_TINYINT_when_given_ByteBasedDomainPrimitive_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, (ByteBasedDomainPrimitive) null);

    Mockito.verify(ps).setNull(1, Types.TINYINT);
    Mockito.verifyNoMoreInteractions(ps);
  }
  
  @Test
  public void should_invoke_setLong_when_given_Long_value_is_not_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setLong(ps, 1, Long.valueOf(2));

    Mockito.verify(ps).setLong(1, 2);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_BIGINT_when_given_an_empty_LongBasedDomainPrimitive_optional() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setLong(ps, 1, Optional.empty());

    Mockito.verify(ps).setNull(1, Types.BIGINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_BIGINT_when_given_byte_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setLong(ps, 1, (Long) null);

    Mockito.verify(ps).setNull(1, Types.BIGINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_BIGINT_when_given_LongBasedDomainPrimitive_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setLong(ps, 1, (LongBasedDomainPrimitive) null);

    Mockito.verify(ps).setNull(1, Types.BIGINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

}
