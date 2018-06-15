package com.javacreed.api.domain.primitives.optional;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ListBasedDomainPrimitive<T> extends ObjectBasedDomainPrimitive<List<T>> implements Iterable<T> {

  protected ListBasedDomainPrimitive(final List<T> value) {
    super(value);
  }

  protected ListBasedDomainPrimitive(final Optional<List<T>> value) throws NullPointerException {
    super(value);
  }

  @Override
  public Iterator<T> iterator() {
    return map(l -> l.iterator()).orElse(Collections.emptyIterator());
  }

}
