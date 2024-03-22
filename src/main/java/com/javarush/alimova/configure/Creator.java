package com.javarush.alimova.configure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Creator {

    private final static Map<Class<?>, Object> components = new HashMap<>();

    public static <T> T getComponent(Class<T> classComponent) {
        Object component = components.get(classComponent);
        if (component == null) {
            Constructor<?> constructor = classComponent.getConstructors()[0];
            Class<?>[] parameterType = constructor.getParameterTypes();
            Object[] parameter = new Object[parameterType.length];
            for (int i = 0; i < parameter.length; i++) {
                parameter[i] = Creator.getComponent(parameterType[i]);
            }
            try {
                Object newInstant = constructor.newInstance(parameter);
                components.put(classComponent, newInstant);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return (T) components.get(classComponent);
    }
}
