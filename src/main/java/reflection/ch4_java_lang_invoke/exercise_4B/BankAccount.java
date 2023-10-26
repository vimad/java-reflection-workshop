package reflection.ch4_java_lang_invoke.exercise_4B;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * Refactor this to use a VarHandle instead.
 */
public class BankAccount {
    // INVARIANT: balance must never be negative!!!
    private volatile double balance;

    public BankAccount(double balance) {
        if (balance < 0) throw new IllegalArgumentException("balance < 0");
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("amount < 0");
        return changeBalanceBy(amount);
    }

    public boolean withdraw(double amount) {
        if (amount < 0) throw new IllegalArgumentException("amount < 0");
        return changeBalanceBy(-amount);
    }

    private boolean changeBalanceBy(double amount) {
        double current, next;
        do {
            current = getBalance();
            next = current + amount;
            if (next < 0) return false;
        } while (!BALANCE.compareAndSet(this, current, next));
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public boolean transferTo(BankAccount other, double amount) {
        if (amount < 0) throw new IllegalArgumentException("amount < 0");
        if (!changeBalanceBy(-amount)) return false;
        other.deposit(amount);
        return true;
    }

    private static final VarHandle BALANCE;
    static {
        try {
            BALANCE = MethodHandles.lookup().findVarHandle(
                    BankAccount.class, "balance", double.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}