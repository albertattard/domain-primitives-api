package com.javacreed.api.domain.objects.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import com.javacreed.api.domain.objects.ByteBasedDomainObject;
import com.javacreed.api.domain.objects.IntBasedDomainObject;
import com.javacreed.api.domain.objects.LongBasedDomainObject;
import com.javacreed.api.domain.objects.ShortBasedDomainObject;
import com.javacreed.api.domain.objects.StringBasedDomainObject;
import com.javacreed.api.domain.objects.UuidBasedDomainObject;

public class PreparedStatementUtils {

  public static void setByte(final PreparedStatement ps, final int column, final Byte param)
      throws SQLException, NullPointerException {
    if (param != null) {
      ps.setByte(column, param);
    } else {
      ps.setNull(column, Types.TINYINT);
    }
  }

  public static void setByte(final PreparedStatement ps, final int column, final ByteBasedDomainObject object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setByte(ps, column, object == null ? null : object.getValue());
  }

  public static void setByte(final PreparedStatement ps, final int column,
      final Optional<? extends ByteBasedDomainObject> object) throws SQLException, NullPointerException {
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
      final Optional<UuidBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setBytes(ps, column, object.orElse(null));
  }

  public static void setBytes(final PreparedStatement ps, final int column, final UuidBasedDomainObject object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setBytes(ps, column, object == null ? null : object.getNullableBytes());
  }

  public static void setInt(final PreparedStatement ps, final int column, final IntBasedDomainObject object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setInt(ps, column, object == null ? null : object.getValue());
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
      final Optional<? extends IntBasedDomainObject> object) throws SQLException, NullPointerException {
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

  public static void setLong(final PreparedStatement ps, final int column, final LongBasedDomainObject object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setLong(ps, column, object == null ? null : object.getValue());
  }

  public static void setLong(final PreparedStatement ps, final int column,
      final Optional<? extends LongBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setLong(ps, column, object.orElse(null));
  }

  public static void setShort(final PreparedStatement ps, final int column,
      final Optional<? extends ShortBasedDomainObject> object) throws SQLException, NullPointerException {
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

  public static void setShort(final PreparedStatement ps, final int column, final ShortBasedDomainObject object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setShort(ps, column, object == null ? null : object.getValue());
  }

  public static void setString(final PreparedStatement ps, final int column,
      final Optional<? extends StringBasedDomainObject> optional) throws SQLException, NullPointerException {
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

  public static void setString(final PreparedStatement ps, final int column, final StringBasedDomainObject object)
      throws SQLException, NullPointerException {
    PreparedStatementUtils.setString(ps, column, object == null ? null : object.getNullable());
  }

  private PreparedStatementUtils() {}
}
