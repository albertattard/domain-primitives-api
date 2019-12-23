package com.javacreed.api.domain.primitives.utils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class BigDecimalUtilsTest {

  @Test
  public void should_parse_given_value_using_default_format() {
    final Map<String, BigDecimal> params = new LinkedHashMap<>();
    params.put("0", new BigDecimal("0"));
    params.put("1", new BigDecimal("1"));
    params.put("1000", new BigDecimal("1000"));
    params.put("1,000", new BigDecimal("1000"));
    params.put("1,000.00", new BigDecimal("1000.00"));

    params.forEach((s, b) -> Assert.assertEquals(b, BigDecimalUtils.parseValue(s, e -> e.toString())));
  }

  @Test
  public void should_throw_an_IllegalArgumentException_when_given_an_invalid_value() {
    Assert.assertThrows(IllegalArgumentException.class, () -> BigDecimalUtils.parseValue("abc", e -> "Failed to pars value"));
  }

  @Test
  public void should_throw_NullPointerException_when_given_null() {
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue(null, e -> e.toString()));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue("", null));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue(null, "#,###.00", e -> e.toString()));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue("", null, e -> e.toString()));
    Assert.assertThrows(NullPointerException.class, () -> BigDecimalUtils.parseValue("", "", null));
  }
}
