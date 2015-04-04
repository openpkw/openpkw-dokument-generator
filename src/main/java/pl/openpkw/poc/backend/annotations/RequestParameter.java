package pl.openpkw.poc.backend.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {
	String value() default "";
}