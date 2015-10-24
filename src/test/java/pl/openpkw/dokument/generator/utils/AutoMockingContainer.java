package pl.openpkw.dokument.generator.utils;

import java.lang.reflect.Constructor;

public class AutoMockingContainer {
    
    public <T> T createCut(Class<T> clazz) {
        try {
            Constructor[] constructors = clazz.getDeclaredConstructors();

            T instance = (T) constructors[0].newInstance(new Object[] {});
            return instance;
            
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create class "+clazz+": "+ex.getMessage(), ex);
        }
    }

}
