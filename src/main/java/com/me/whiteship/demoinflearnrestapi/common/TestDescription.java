package com.me.whiteship.demoinflearnrestapi.common;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface TestDescription {
	String value();
}
