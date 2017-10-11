package com.javacreed.api.domain.objects.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

import com.javacreed.api.domain.objects.LongBasedDomainObject;
import com.javacreed.api.domain.objects.StringBasedDomainObject;

public class PreparedStatementUtils {

  public static void setLong(final PreparedStatement ps, final int column, final LongBasedDomainObject object)
      throws SQLException, NullPointerException {
    if (object != null) {
      ps.setLong(column, object.getValue());
    } else {
      ps.setNull(column, Types.BIGINT);
    }
  }

  public static void setString(final PreparedStatement ps, final int column,
      final Optional<? extends StringBasedDomainObject> object) throws SQLException, NullPointerException {
    if (object.isPresent()) {
      PreparedStatementUtils.setString(ps, column, object.get());
    } else {
      ps.setNull(column, Types.VARCHAR);
    }
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
