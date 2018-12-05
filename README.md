# Domain Primitives API

|Name        |Sate                                                             |
|------------|-----------------------------------------------------------------|
|[CircleCI](https://circleci.com/gh/javacreed/domain-primitives-api)|![CircleCI](https://circleci.com/gh/javacreed/domain-primitives-api.svg?style=svg)|
|[SonarQube](https://sonarcloud.io/dashboard?id=javacreed_domain-primitives-api)|![Quality](https://sonarcloud.io/api/project_badges/measure?project=javacreed_domain-primitives-api&metric=alert_status) ![Bugs](https://sonarcloud.io/api/project_badges/measure?project=javacreed_domain-primitives-api&metric=bugs) ![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=javacreed_domain-primitives-api&metric=code_smells)|

This project provides a set of base classes that can be used when building domain primitives.  The base classes come into three flavours:

1. lang
1. optional
1. mandatory

The lang set of base classes represents domain primitives that are based on the Java primitives, such as `int` and `long`.  The optional and mandatory set of base classes represents options such as `Integer` or `String`, where the optional version allows `null`s while the mandatory peer does not allow `null`s.

For example, say we have a class that has two fields, `title` and `name`.  The `title` field is optional while the `name` field is mandatory,

```java
public class Person {
  private final PersonTitle title;
  private final PersonName name;

}
```

The `Person` uses two domain primitive classes, the `PersonTitle` and the `PersonName`.  The `PersonTitle` should accept `null`s and thus it is optional.

```java
import java.util.Optional;
import com.google.common.base.Preconditions;

import com.javacreed.api.domain.primitives.optional.StringBasedDomainPrimitive;

public class PersonTitle extends StringBasedDomainPrimitive {

    private static final PersonTitle EMPTY = new PersonTitle(Optional.empty());

    public static PersonTitle empty() {
        return EMPTY;
    }

    public static PersonTitle ofNullable(String value) {
        if(value == null){
            return empty();
        }

        return of(value);
    }

    public static PersonTitle of(String value) throws NullPointerException {
        Preconditions.checkNotNull(value);
        return new PersonTitle(Optional.of(value));
    }

    private PersonTitle(Optional<String> value) {
        super(value);
    }
}
```

The `PersonName` class, on the other hand, is mandatory.

```java
import com.google.common.base.Preconditions;

import com.javacreed.api.domain.primitives.mandatory.StringBasedDomainPrimitive;

public class PersonName extends StringBasedDomainPrimitive {

    public static PersonName of(String value) throws NullPointerException {
        Preconditions.checkNotNull(value);
        return new PersonName(value);
    }

    private PersonName(String value) {
        super(value);
    }
}
```

Will add more to this page later on.
