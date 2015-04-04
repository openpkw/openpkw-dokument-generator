package pl.openpkw.poc.backend.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface FormElement {
	int page();
	int x();
	int y();
	int digits() default 0;
	int cellWidth() default 25;
}