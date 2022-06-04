package shell;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Map;


public class PrettyPrint {
    public static void print(Object object) {
        print(object, "");
    }

    public static void print(Object object, String prefix) {
        if (isMap(object)) {
            printMap((Map<?, ?>) object, prefix);
        } else if (isCollection(object)) {
            printCollection((Collection<?>) object, prefix);
        } else {
            printObject(object, prefix);
        }
    }

    protected static void printMap(@NotNull Map<?, ?> map, String prefix) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (isPrintableObject(entry.getValue())) {
                printKeyValue(entry.getKey(), entry.getValue(), prefix);
            } else {
                printKey(entry.getKey(), prefix);
                print(entry.getValue(), nextPrefix(prefix));
            }
        }
    }

    protected static void printCollection(@NotNull Collection<?> collection, String prefix) {
        for (Object element : collection) {
            if (isPrintableObject(element)) {
                printArrayElement(element, prefix);
            } else {
                printArrayKey(prefix);
                print(element, nextPrefix(prefix));
            }
        }
    }

    protected static void printObject(Object object, String prefix) {
        if (object != null) {
            Class<?> cls = object.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                try {
                    Method fieldGetter = cls.getMethod(getFieldGetterName(fieldName));
                    Object value = fieldGetter.invoke(object);
                    if (isPrintableObject(value)) {
                        printKeyValue(fieldName, value, prefix);
                    } else {
                        printKey(fieldName, prefix);
                        print(value, nextPrefix(prefix));
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
                }
            }
        }
    }

    protected static void printKeyValue(Object key, Object value, String prefix) {
        System.out.println(prefix + key + ": " + value);
    }

    protected static void printKey(Object key, String prefix) {
        System.out.println(prefix + key + ':');
    }

    protected static void printArrayKey(String prefix) {
        System.out.println(prefix + '-');
    }

    protected static void printArrayElement(Object element, String prefix) {
        System.out.println(prefix + "- " + element);
    }

    protected static String nextPrefix(String prefix) {
        return prefix + '\t';
    }

    protected static String getFieldGetterName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    protected static boolean isMap(Object object) {
        return object instanceof Map;
    }

    protected static boolean isCollection(Object object) {
        return object instanceof Collection;
    }

    protected static boolean isEnum(Object object) {
        return object != null && object.getClass().isEnum();
    }

    protected static boolean isPrimitive(Object object) {
        return object != null && object.getClass().isPrimitive();
    }

    protected static boolean isPrimitiveWrapper(Object object) {
        if (object != null) {
            String type = object.getClass().getTypeName();
            return type.startsWith("java.lang.");
        } else {
            return false;
        }
    }

    protected static boolean isDateObject(Object object) {
        return object instanceof Date;
    }

    protected static boolean isPrintableObject(Object object) {
        return isEnum(object) || isPrimitive(object) || isPrimitiveWrapper(object) || isDateObject(object) || object == null;
    }
}
