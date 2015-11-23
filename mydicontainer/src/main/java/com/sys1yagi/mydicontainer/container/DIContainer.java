package com.sys1yagi.mydicontainer.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class DIContainer {

    private final Map<Class, Provider> objectMap = new HashMap<>();

    public static DIContainer create() {
        return new DIContainer();
    }

    public <T> void register(Class<T> clazz, Provider<T> provider) {
        objectMap.put(clazz, provider);
    }

    public <T> T get(Class<T> clazz) {
        if (objectMap.containsKey(clazz)) {
            return (T) objectMap.get(clazz).get();
        } else {
            return null;
        }
    }

    public void inject(Object target) {
        for (Field field : target.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.annotationType().equals(Inject.class)) {
                    Object object = get(field.getType());
                    if (object != null) {
                        try {
                            field.setAccessible(true);
                            field.set(target, object);
                            field.setAccessible(false);
                        } catch (Exception e) {
                            throw new IllegalAccessError(e.getMessage());
                        }
                        break;
                    }
                }
            }
        }
    }
}
