package reflection.ch1_introduction_to_reflection.solution_1E;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanBridge {
    /**
     * Given the input bean, create a record instance of type
     * recordType with all properties set in the constructor.
     *
     * @param bean       the input bean
     * @param recordType the class of record that we need to create
     * @return an instance of the record
     * @throws IllegalArgumentException if a property is missing in the bean
     */
    public static <T, R extends Record> R convertBeanToRecord(T bean, Class<R> recordType)
            throws ReflectiveOperationException, IntrospectionException {
        Map<String, RecordComponent> constructorParameterTypesMap =
                Arrays.stream(recordType.getRecordComponents())
                        .collect(Collectors.toMap(
                                RecordComponent::getName,
                                Function.identity(),
                                (a, b) -> a,
                                LinkedHashMap::new
                        ));
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        Map<String, Object> beanValues = new HashMap<>();
        for (var descriptor : beanInfo.getPropertyDescriptors()) {
            String name = descriptor.getName();
            Method readMethod = descriptor.getReadMethod();
            Object value = readMethod.invoke(bean);
            beanValues.put(name, value);
        }
        Class<?>[] constructorParameterTypes =
                constructorParameterTypesMap.values()
                        .stream()
                        .map(RecordComponent::getType)
                        .toArray(Class<?>[]::new);
        Object[] constructorParameters =
                constructorParameterTypesMap.keySet().stream()
                        .map(name -> {
                            if (beanValues.containsKey(name)) return beanValues.get(name);
                            throw new IllegalArgumentException(
                                    "Bean does not have property \"" + name + "\"");
                        })
                        .toArray();

        Constructor<R> recordConstructor =
                recordType.getConstructor(constructorParameterTypes);
        return recordConstructor.newInstance(constructorParameters);
    }

    /**
     * Given the input record, create a bean instance of type
     * beanType with all properties set using setters.
     *
     * @param bean       the input record
     * @param recordType the class of bean that we need to create
     * @return an instance of the bean
     * @throws IllegalArgumentException if a property is missing in the record
     */
    public static <T extends Record, R> R convertRecordToBean(T record, Class<R> beanType)
            throws ReflectiveOperationException, IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(beanType);
        Constructor<R> beanConstructor = beanType.getConstructor();
        R bean = beanConstructor.newInstance();

        Map<String, Object> recordValues = new HashMap<>();
        for (var recordComponent : record.getClass().getRecordComponents()) {
            String name = recordComponent.getName();
            Method readMethod = recordComponent.getAccessor();
            Object value = readMethod.invoke(record);
            recordValues.put(name, value);
        }

        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            Method setter = descriptor.getWriteMethod();
            if (setter != null) {
                String name = descriptor.getName();
                if (recordValues.containsKey(name)) {
                    Object value = recordValues.get(name);
                    setter.invoke(bean, value);
                } else {
                    throw new IllegalArgumentException(
                            "Record does not have property \"" + name + "\"");
                }
            }
        }

        return bean;
    }
}