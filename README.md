# Domain Primitives API 

Build status: [![CircleCI](https://circleci.com/gh/javacreed/domain-primitives-api.svg?style=svg)](https://circleci.com/gh/javacreed/domain-primitives-api)


This project provides a set of base classes that can be used when building domain primitives.  The base classes come into three flavours:

1. lang
1. optional
1. mandatory

The lang set of base classes represents domain primitives that are based on the Java primitives, such as `int` and `long`.  The optional and mandatory set of base classes represents options such as `Integer` or `String`, where the optional version allows `null`s while the mandatory peer does not allow `null`s.

For example, say we have a class that has two fields, `title` and `name`.  The `title` field is optional while the `name` field is mandatory, 

```
public class Person {
  private final PersonTitle title;
  private final PersonName name;

}
```

The `Person` uses two domain primitive classes, the `PersonTitle` and the `PersonName`.  The `PersonTitle` should accept `null`s and thus it is optional.

```
import com.javacreed.api.domain.primitives.optional.StringBasedDomainPrimitive;

public class PersonTitle extends StringBasedDomainPrimitive {
}
```

The `PersonName` class, on the other hand, is mandatory.

```
import com.javacreed.api.domain.primitives.mandatory.StringBasedDomainPrimitive;

public class PersonName extends StringBasedDomainPrimitive {
}
```

Will add more to this page later on.