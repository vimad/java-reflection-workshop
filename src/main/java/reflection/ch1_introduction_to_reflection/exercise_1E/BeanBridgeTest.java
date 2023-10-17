package reflection.ch1_introduction_to_reflection.exercise_1E;

import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;

import static org.junit.jupiter.api.Assertions.*;

public class BeanBridgeTest {
    @Test
    public void testConvertBeanToRecordWithNullValues() throws ReflectiveOperationException, IntrospectionException {
        var bean = new PersonBean(null, null, 0);
        var record = BeanBridge.convertBeanToRecord(bean, PersonRecord.class);
        assertNull(record.firstName());
        assertNull(record.lastName());
        assertEquals(0, record.age());
        compareBeanToRecord(bean, record);
    }

    @Test
    public void testConvertBeanToRecordWithActualValues() throws ReflectiveOperationException, IntrospectionException {
        var bean = new PersonBean("Heinz", "Kabutz", 50);
        var record = BeanBridge.convertBeanToRecord(bean, PersonRecord.class);
        assertEquals("Heinz", record.firstName());
        assertEquals("Kabutz", record.lastName());
        assertEquals(50, record.age());
        compareBeanToRecord(bean, record);
    }

    private void compareBeanToRecord(PersonBean bean, PersonRecord record) {
        assertEquals(bean.getFirstName(), record.firstName());
        assertEquals(bean.getLastName(), record.lastName());
        assertEquals(bean.getAge(), record.age());
    }

    @Test
    public void testConvertRecordToBeanWithNullValues() throws ReflectiveOperationException, IntrospectionException {
        var record = new PersonRecord(null, null, 0);
        var bean = BeanBridge.convertRecordToBean(record, PersonBean.class);
        assertNull(bean.getFirstName());
        assertNull(bean.getLastName());
        assertEquals(0, bean.getAge());
        compareBeanToRecord(bean, record);
    }

    @Test
    public void testConvertRecordToBeanWithActualValues() throws ReflectiveOperationException, IntrospectionException {
        var record = new PersonRecord("Heinz", "Kabutz", 50);
        var bean = BeanBridge.convertRecordToBean(record, PersonBean.class);
        assertEquals("Heinz", bean.getFirstName());
        assertEquals("Kabutz", bean.getLastName());
        assertEquals(50, bean.getAge());
        compareBeanToRecord(bean, record);
    }

    @Test
    public void testMissingRecordProperties() throws ReflectiveOperationException, IntrospectionException {
        record PersonRecordReduced(String name, int age) {}
        var record = new PersonRecordReduced("Heinz Kabutz", 50);
        assertThrows(IllegalArgumentException.class,
                () -> BeanBridge.convertRecordToBean(record, PersonBean.class));
    }

    @Test
    public void testMissingBeanProperties() throws ReflectiveOperationException, IntrospectionException {
        class PersonBeanReduced {
            private final String name;
            private final int age;

            public PersonBeanReduced(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }
        }
        var bean = new PersonBeanReduced("Heinz Kabutz", 50);
        assertThrows(IllegalArgumentException.class,
                () -> BeanBridge.convertBeanToRecord(bean, PersonRecord.class));
    }

    public static class LotsOfPropertiesBean {
        private int i0;
        private int i1;
        private int i2;
        private int i3;
        private int i4;
        private int i5;
        private int i6;
        private int i7;
        private int i8;
        private int i9;

        public LotsOfPropertiesBean() {}

        public LotsOfPropertiesBean(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.i0 = i0;
            this.i1 = i1;
            this.i2 = i2;
            this.i3 = i3;
            this.i4 = i4;
            this.i5 = i5;
            this.i6 = i6;
            this.i7 = i7;
            this.i8 = i8;
            this.i9 = i9;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LotsOfPropertiesBean that = (LotsOfPropertiesBean) o;

            if (i0 != that.i0) return false;
            if (i1 != that.i1) return false;
            if (i2 != that.i2) return false;
            if (i3 != that.i3) return false;
            if (i4 != that.i4) return false;
            if (i5 != that.i5) return false;
            if (i6 != that.i6) return false;
            if (i7 != that.i7) return false;
            if (i8 != that.i8) return false;
            if (i9 != that.i9) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = i0;
            result = 31 * result + i1;
            result = 31 * result + i2;
            result = 31 * result + i3;
            result = 31 * result + i4;
            result = 31 * result + i5;
            result = 31 * result + i6;
            result = 31 * result + i7;
            result = 31 * result + i8;
            result = 31 * result + i9;
            return result;
        }

        public int getI0() {
            return i0;
        }

        public void setI0(int i0) {
            this.i0 = i0;
        }

        public int getI1() {
            return i1;
        }

        public void setI1(int i1) {
            this.i1 = i1;
        }

        public int getI2() {
            return i2;
        }

        public void setI2(int i2) {
            this.i2 = i2;
        }

        public int getI3() {
            return i3;
        }

        public void setI3(int i3) {
            this.i3 = i3;
        }

        public int getI4() {
            return i4;
        }

        public void setI4(int i4) {
            this.i4 = i4;
        }

        public int getI5() {
            return i5;
        }

        public void setI5(int i5) {
            this.i5 = i5;
        }

        public int getI6() {
            return i6;
        }

        public void setI6(int i6) {
            this.i6 = i6;
        }

        public int getI7() {
            return i7;
        }

        public void setI7(int i7) {
            this.i7 = i7;
        }

        public int getI8() {
            return i8;
        }

        public void setI8(int i8) {
            this.i8 = i8;
        }

        public int getI9() {
            return i9;
        }

        public void setI9(int i9) {
            this.i9 = i9;
        }
    }

    public record LotsOfPropertiesRecord(
            int i0, int i1, int i2, int i3, int i4,
            int i5, int i6, int i7, int i8, int i9) {}

    @Test
    public void testConvertBigBeanToRecordWithActualValues() throws ReflectiveOperationException, IntrospectionException {
        var bean = new LotsOfPropertiesBean(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var record = BeanBridge.convertBeanToRecord(bean, LotsOfPropertiesRecord.class);
        compareBeanToRecord(bean, record);
    }

    private void compareBeanToRecord(LotsOfPropertiesBean bean, LotsOfPropertiesRecord record) {
        assertEquals(bean.getI0(), record.i0());
        assertEquals(bean.getI1(), record.i1());
        assertEquals(bean.getI2(), record.i2());
        assertEquals(bean.getI3(), record.i3());
        assertEquals(bean.getI4(), record.i4());
        assertEquals(bean.getI5(), record.i5());
        assertEquals(bean.getI6(), record.i6());
        assertEquals(bean.getI7(), record.i7());
        assertEquals(bean.getI8(), record.i8());
        assertEquals(bean.getI9(), record.i9());
    }

    @Test
    public void testConvertRecordToBigBeanWithActualValues() throws ReflectiveOperationException, IntrospectionException {
        var record = new LotsOfPropertiesRecord(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var bean = BeanBridge.convertRecordToBean(record, LotsOfPropertiesBean.class);
        compareBeanToRecord(bean, record);
    }
}