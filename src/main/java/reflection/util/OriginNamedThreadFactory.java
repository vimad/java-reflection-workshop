package reflection.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class OriginNamedThreadFactory implements ThreadFactory {
    private final StackTraceElement origin;
    private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

    public OriginNamedThreadFactory() {
        origin = new Throwable().getStackTrace()[1];
    }

    public Thread newThread(Runnable r) {
        Thread t = defaultFactory.newThread(r);
        String name = t.getName() + "@" + origin.getClassName() + "." +
            origin.getMethodName() + "()";
        t.setName(name);
        return t;
    }
}
