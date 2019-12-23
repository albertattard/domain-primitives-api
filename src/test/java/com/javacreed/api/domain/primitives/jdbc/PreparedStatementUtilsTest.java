package com.javacreed.api.domain.primitives.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.javacreed.api.domain.primitives.lang.ByteBasedDomainPrimitive;

public class PreparedStatementUtilsTest {

  @Test
  public void should_invoke_setByte_when_given_byte_value_is_not_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, Byte.valueOf((byte) 2));

    Mockito.verify(ps).setByte(1, (byte) 2);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_tinyint_when_given_an_empty_ByteBasedDomainPrimitive_optional() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, Optional.empty());

    Mockito.verify(ps).setNull(1, Types.TINYINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_tinyint_when_given_byte_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, (Byte) null);

    Mockito.verify(ps).setNull(1, Types.TINYINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

  @Test
  public void should_invoke_setNull_with_tinyint_when_given_ByteBasedDomainPrimitive_value_is_null() throws SQLException {
    final PreparedStatement ps = Mockito.mock(PreparedStatement.class);

    PreparedStatementUtils.setByte(ps, 1, (ByteBasedDomainPrimitive) null);

    Mockito.verify(ps).setNull(1, Types.TINYINT);
    Mockito.verifyNoMoreInteractions(ps);
  }

}
