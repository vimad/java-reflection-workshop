package reflection.util;

public class DeadlockError extends Error {
    private static final long serialVersionUID = -8832371745242532272L;
    private final Thread thread;

    public DeadlockError(Thread thread) {
        super("Deadlock involving thread: " + thread);
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }
}
