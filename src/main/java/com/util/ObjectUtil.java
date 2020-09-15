package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public final class ObjectUtil {

    private ObjectUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T obj) {
        try {
            if (obj == null) {
                return null;
            }
            Class<?> clazz = obj.getClass();
            T clone = (T) clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if (!Modifier.isFinal(field.getModifiers())) {
                    if (field.get(obj) instanceof List<?>) {
                        List<?> copiedList = deepCopyList((List<?>) field.get(obj));
                        field.set(clone, copiedList);
                    } else {
                        field.set(clone, field.get(obj));
                    }
                }
            }
            while (true) {
                if (Object.class.equals(clazz)) {
                    break;
                }
                clazz = clazz.getSuperclass();
                Field[] sFields = clazz.getDeclaredFields();
                for (int i = 0; i < sFields.length; i++) {
                    Field field = sFields[i];
                    field.setAccessible(true);
                    if (!Modifier.isFinal(field.getModifiers())) {
                        if (field.get(obj) instanceof List<?>) {
                            List<?> copiedList = deepCopyList((List<?>) field.get(obj));
                            field.set(clone, copiedList);
                        } else {
                            field.set(clone, field.get(obj));
                        }
                    }
                }
            }
            return clone;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public static <T> List<T> deepCopyList(List<T> arg) {
        if (arg == null) {
            return null;
        }
        List<T> retList = new ArrayList<T>();
        for (T each : arg) {
            retList.add(deepCopy(each));
        }
        return retList;
    }

}
