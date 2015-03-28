package pl.openpkw.poc.backend;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;

import pl.openpkw.poc.backend.annotations.RequestParameter;
import pl.openpkw.poc.backend.domain.Formularz;

@Singleton
public class FormDataMapper {

    public Formularz getFormData(HttpServletRequest request) {
        Formularz form = new Formularz();

        Field[] fields = Formularz.class.getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                Annotation a = f.getAnnotation(RequestParameter.class);
                if (a != null) {
                    String parameterName = ((RequestParameter) a).value();
                    if (parameterName.equals("")) {
                        parameterName = f.getName();
                    }
                    String parameterValue = request.getParameter(parameterName);
                    if (parameterValue != null) {
                        parameterValue = new String(parameterValue.getBytes("iso-8859-1"), "UTF-8");
                    }
                    f.set(form, parameterValue);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Failed to set field " + f.getName() + ": " + ex.getMessage(), ex);
            }
        }

        return form;
    }
}