package com.moffy5612.iinteg.misc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.FIELD
})
public @interface IIntegContentInstance {
    Class<?> contentClass();
    String[] requiredModIds() default {};
}
