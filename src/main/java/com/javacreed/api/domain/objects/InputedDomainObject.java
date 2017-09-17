package com.javacreed.api.domain.objects;

public class InputedDomainObject<T> extends ValidatedDomainObject {

  /** TODO: consider using optional instead */
  protected final T object;

  protected InputedDomainObject(final String value, final ValidationState validationState, final T object)
      throws NullPointerException {
    super(value, validationState);
    this.object = object;
  }

  public T getObject() {
    return object;
  }
}
