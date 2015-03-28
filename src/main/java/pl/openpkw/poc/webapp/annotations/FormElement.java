package pl.openpkw.poc.webapp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FormElement {
	int page();
	int x();
	int y();
	int digits() default 0;
	int cellWidth() default 25;
}