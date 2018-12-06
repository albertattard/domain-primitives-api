package com.javacreed.api.domain.primitives.utils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class BigDecimalUtilsTest {

  @Test
  public void dealsNulls() {
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue(null, e -> e.toString()));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue("", null));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue(null, "#,###.00", e -> e.toString()));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue("", null, e -> e.toString()));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue("", "", null));
  }

  @Test
  public void parseValuesUsingDefaultFormat() {
    final Map<String, BigDecimal> params = new LinkedHashMap<>();
    params.put("0", new BigDecimal("0"));
    params.put("1", new BigDecimal("1"));
    params.put("1000", new BigDecimal("1000"));
    params.put("1,000", new BigDecimal("1000"));
    params.put("1,000.00", new BigDecimal("1000.00"));

    params.forEach((s, b) -> Assert.assertEquals(b, BigDecimalUtils.parseValue(s, e -> e.toString())));
  }
}
