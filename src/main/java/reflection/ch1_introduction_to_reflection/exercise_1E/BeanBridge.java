package reflection.ch1_introduction_to_reflection.exercise_1E;

import java.beans.IntrospectionException;

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
        throw new UnsupportedOperationException("TODO");
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
        throw new UnsupportedOperationException("TODO");
    }
}