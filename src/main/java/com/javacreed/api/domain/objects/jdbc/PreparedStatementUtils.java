package com.javacreed.api.domain.objects.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.javacreed.api.domain.objects.StringBasedDomainObject;

public class PreparedStatementUtils {

  public static void setString(final PreparedStatement ps, final int column, final StringBasedDomainObject input)
      throws SQLException {
    if (input != null && input.getValue().isPresent()) {
      ps.setString(column, input.getValue().get());
    } else {
      ps.setNull(column, Types.VARCHAR);
    }
  }

  private PreparedStatementUtils() {}

}
