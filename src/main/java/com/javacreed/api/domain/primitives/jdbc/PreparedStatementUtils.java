package com.javacreed.api.domain.primitives.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import com.javacreed.api.domain.primitives.lang.ByteBasedDomainPrimitive;
import com.javacreed.api.domain.primitives.lang.IntBasedDomainPrimitive;
import com.javacreed.api.domain.primitives.lang.LongBasedDomainPrimitive;
import com.javacreed.api.domain.primitives.lang.ShortBasedDomainPrimitive;
import com.javacreed.api.domain.primitives.optional.StringBasedDomainPrimitive;
import com.javacreed.api.domain.primitives.optional.UuidBasedDomainPrimitive;

public class PreparedStatementUtils {

  public static void setByte(final PreparedStatement ps, final int column, final Byte param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setByte(column, param);
    } else {
      ps.setNull(column, Types.TINYINT);
    }
  }

  public static void setByte(final PreparedStatement ps, final int column, final ByteBasedDomainPrimitive object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setByte(ps, column, object == null ? null : object.get());
  }

  public static void setByte(final PreparedStatement ps, final int column,
      final Optional<? extends ByteBasedDomainPrimitive> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setByte(ps, column, object.orElse(null));
  }

  public static void setBytes(final PreparedStatement ps, final int column, final byte[] param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setBytes(column, param);
    } else {
      ps.setNull(column, Types.VARBINARY);
    }
  }

  public static void setBytes(final PreparedStatement ps, final int column,
      final Optional<UuidBasedDomainPrimitive> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setBytes(ps, column, object.orElse(null));
  }

  public static void setBytes(final PreparedStatement ps, final int column, final UuidBasedDomainPrimitive object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setBytes(ps, column, object == null ? null : object.asBytesOrNull());
  }

  public static void setInt(final PreparedStatement ps, final int column, final IntBasedDomainPrimitive object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setInt(ps, column, object == null ? null : object.get());
  }

  public static void setInt(final PreparedStatement ps, final int column, final Integer param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setInt(column, param);
    } else {
      ps.setNull(column, Types.INTEGER);
    }
  }

  public static void setInt(final PreparedStatement ps, final int column,
      final Optional<? extends IntBasedDomainPrimitive> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setInt(ps, column, object.orElse(null));
  }

  public static void setLong(final PreparedStatement ps, final int column, final Long param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setLong(column, param);
    } else {
      ps.setNull(column, Types.BIGINT);
    }
  }

  public static void setLong(final PreparedStatement ps, final int column, final LongBasedDomainPrimitive object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setLong(ps, column, object == null ? null : object.get());
  }

  public static void setLong(final PreparedStatement ps, final int column,
      final Optional<? extends LongBasedDomainPrimitive> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setLong(ps, column, object.orElse(null));
  }

  public static void setShort(final PreparedStatement ps, final int column,
      final Optional<? extends ShortBasedDomainPrimitive> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setShort(ps, column, object.orElse(null));
  }

  public static void setShort(final PreparedStatement ps, final int column, final Short param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setShort(column, param);
    } else {
      ps.setNull(column, Types.SMALLINT);
    }
  }

  public static void setShort(final PreparedStatement ps, final int column, final ShortBasedDomainPrimitive object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setShort(ps, column, object == null ? null : object.get());
  }

  public static void setString(final PreparedStatement ps, final int column,
      final Optional<? extends StringBasedDomainPrimitive> optional) throws SQLException, NullPointerException {
    PreparedStatementUtils.setString(ps, column, optional.orElse(null));
  }

  public static void setString(final PreparedStatement ps, final int column, final String param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setString(column, param);
    } else {
      ps.setNull(column, Types.VARCHAR);
    }
  }

  public static void setString(final PreparedStatement ps, final int column, final StringBasedDomainPrimitive object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setString(ps, column, object == null ? null : object.orNull());
  }

  private PreparedStatementUtils() {}
}
