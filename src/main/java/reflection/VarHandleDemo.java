package reflection;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VarHandleDemo {
    public static class NameHolder {
        private volatile String name;
        private static final VarHandle varHandle;
        static {
            try {
                varHandle = MethodHandles.lookup().findVarHandle(NameHolder.class, "name", String.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public NameHolder(String name) {
            this.name = name;
        }

        public void changeName(String newName) {
            String currentName;
            do {
                currentName = name;
            } while (!varHandle.compareAndSet(this, currentName, newName));
        }
    }

    public static void main(String[] args) {
        NameHolder nameHolder = new NameHolder("john");
        nameHolder.changeName("vinod");
        System.out.println(nameHolder.name);
    }
}
