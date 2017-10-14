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

public class PreparedStatementUtils {

  public static void setByte(final PreparedStatement ps, final int column, final ByteBasedDomainObject object)
      throws SQLException, NullPointerException {
    if (object != null) {
      ps.setByte(column, object.getValue());
    } else {
      ps.setNull(column, Types.TINYINT);
    }
  }

  public static void setByte(final PreparedStatement ps, final int column,
      final Optional<? extends ByteBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setByte(ps, column, object.orElse(null));
  }

  public static void setInt(final PreparedStatement ps, final int column, final IntBasedDomainObject object)
      throws SQLException, NullPointerException {
    if (object != null) {
      ps.setInt(column, object.getValue());
    } else {
      ps.setNull(column, Types.INTEGER);
    }
  }

  public static void setInt(final PreparedStatement ps, final int column,
      final Optional<? extends IntBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setInt(ps, column, object.orElse(null));
  }

  public static void setLong(final PreparedStatement ps, final int column, final LongBasedDomainObject object)
      throws SQLException, NullPointerException {
    if (object != null) {
      ps.setLong(column, object.getValue());
    } else {
      ps.setNull(column, Types.BIGINT);
    }
  }

  public static void setLong(final PreparedStatement ps, final int column,
      final Optional<? extends LongBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setLong(ps, column, object.orElse(null));
  }

  public static void setShort(final PreparedStatement ps, final int column,
      final Optional<? extends ShortBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setShort(ps, column, object.orElse(null));
  }

  public static void setShort(final PreparedStatement ps, final int column, final ShortBasedDomainObject object)
      throws SQLException, NullPointerException {
    if (object != null) {
      ps.setShort(column, object.getValue());
    } else {
      ps.setNull(column, Types.SMALLINT);
    }
  }

  public static void setString(final PreparedStatement ps, final int column,
      final Optional<? extends StringBasedDomainObject> object) throws SQLException, NullPointerException {
    PreparedStatementUtils.setString(ps, column, object.orElse(null));
  }

  public static void setString(final PreparedStatement ps, final int column, final StringBasedDomainObject object)
      throws SQLException, NullPointerException {
    if (object != null && object.getValue().isPresent()) {
      ps.setString(column, object.getValue().get());
    } else {
      ps.setNull(column, Types.VARCHAR);
    }
  }

  private PreparedStatementUtils() {}
}
