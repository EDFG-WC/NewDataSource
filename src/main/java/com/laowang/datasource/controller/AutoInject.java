package com.laowang.datasource.controller;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
// 有了以上4个注解, 我们的注解就成功了.
public @interface AutoInject {
}
