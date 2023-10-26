package reflection.ch1_introduction_to_reflection.exercise_1E;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.stream.Stream;

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
    public static <T, R extends Record>
    R convertBeanToRecord(T bean, Class<R> recordType)
            throws ReflectiveOperationException, IntrospectionException {
        RecordComponent[] recordComponents = recordType.getRecordComponents();
        Class<?>[] recordComponentTypes = Stream.of(recordComponents)
                .map(RecordComponent::getType)
                .toArray(Class<?>[]::new);
        Object[] recordComponentValues = new Object[recordComponents.length];
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        nextRecordComponent:
        for (int i = 0; i < recordComponents.length; i++) {
            RecordComponent recordComponent = recordComponents[i];
            String propertyName = recordComponent.getName();
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                if (propertyName.equals(descriptor.getName())) {
                    recordComponentValues[i] = descriptor.getReadMethod().invoke(bean);
                    continue nextRecordComponent;
                }
            }
            throw new IllegalArgumentException("Could not find property: " + propertyName);
        }
        System.out.println(Arrays.toString(recordComponentTypes));
        System.out.println(Arrays.toString(recordComponentValues));
        Constructor<R> constructor = recordType.getConstructor(recordComponentTypes);
        R record = constructor.newInstance(recordComponentValues);
        return record;
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
    public static <T extends Record, R>
    R convertRecordToBean(T record, Class<R> beanType)
            throws ReflectiveOperationException, IntrospectionException {
        Constructor<R> constructor = beanType.getConstructor();
        R bean = constructor.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanType);
        nextRecordComponent:
        for (RecordComponent recordComponent : record.getClass().getRecordComponents()) {
            String name = recordComponent.getName();
            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                if (name.equals(propertyDescriptor.getName())) {
                    Object value = recordComponent.getAccessor().invoke(record);
                    propertyDescriptor.getWriteMethod().invoke(bean, value);
                    continue nextRecordComponent;
                }
            }
            throw new IllegalArgumentException("Could not find property: " + name);
        }
        return bean;
    }
}