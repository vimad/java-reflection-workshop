package reflection.ch4_java_lang_invoke.exercise_4B;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import reflection.util.DeadlockTester;
import reflection.util.OriginNamedThreadFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DO NOT CHANGE.
 */
public class BankAccountTest {
    @Test
    public void testForDeadlock() throws InterruptedException {
        BankAccount switzerland = new BankAccount(1_000_000);
        BankAccount greece = new BankAccount(1_000);

        DeadlockTester deadlockTester = new DeadlockTester();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                switzerland.transferTo(greece, 100);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                greece.transferTo(switzerland, 100);
            }
        });

        t1.start();
        t2.start();

        t1.join(1000);
        deadlockTester.checkThatThreadTerminates(t1);
    }

    @Test
    public void testWithdraw() throws InterruptedException {
        BankAccount account = new BankAccount(100);
        account.withdraw(100);
        assertEquals(0, account.getBalance());
        assertFalse(account.withdraw(1));
    }

    @Test
    public void testTransfer() throws InterruptedException {
        BankAccount switzerland = new BankAccount(0);
        BankAccount greece = new BankAccount(1000);

        ExecutorService pool = Executors.newFixedThreadPool(2, new OriginNamedThreadFactory());

        Future<?> futureGreece = pool.submit(() -> {
                    for (int i = 0; i < 10_000_000; i++) {
                        greece.transferTo(switzerland, 10);
                    }
                }
        );
        Future<?> depositGreece = pool.submit(() -> {
                    for (int i = 0; i < 10_000_000; i++) {
                        greece.deposit(10);
                        greece.withdraw(10);
                    }
                }
        );

        pool.shutdown();
        try {
            depositGreece.get();
            futureGreece.get();
        } catch (ExecutionException e) {
            fail(e.toString());
        }
    }

    private static final int NUMBER_OF_DEPOSIT_WITHDRAWS_FOR_CORRECTNESS_TEST = 10_000_000;

    @Test
    public void testForCorrectness() throws InterruptedException {
        BankAccount account = new BankAccount(1000);
        Runnable depositWithdraw = () -> {
            for (int i = 0; i < NUMBER_OF_DEPOSIT_WITHDRAWS_FOR_CORRECTNESS_TEST; i++) {
                account.deposit(100);
                account.withdraw(100);
            }
        };

        ExecutorService pool = Executors.newCachedThreadPool(new OriginNamedThreadFactory());
        pool.submit(depositWithdraw);
        pool.submit(depositWithdraw);
        pool.shutdown();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) ;
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testForNegativeAmounts() throws InterruptedException {
        BankAccount account = new BankAccount(1000);

        ExecutorService pool = Executors.newCachedThreadPool(new OriginNamedThreadFactory());
        pool.submit(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                account.transferTo(account, 100_000_000);
            }
        });
        pool.shutdown();

        for (int i = 0; i < 10_000_000; i++) {
            double balance = account.getBalance();
            assertTrue(balance >= 0, "Balance is now " + balance);
        }
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) ;
    }

    @Test
    public void testNegativeTransfer() {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);
        assertThrows(IllegalArgumentException.class,
                () -> account1.transferTo(account2, -500));
    }

    @Test
    public void testOverdrawnTransfer() {
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1000);
        assertFalse(account1.transferTo(account2, 10000));
    }

    @Test
    public void testForBankRobber() {
        BankAccount account = new BankAccount(1000);
        assertFalse(account.withdraw(1_000_000));
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testForDecimals() {
        BankAccount account = new BankAccount(123.45);
        account.withdraw(21.44);
        assertEquals(102.01, account.getBalance(), 0.001);
    }

    @Test
    public void testClassStructure() throws NoSuchFieldException {
        Field balanceField = BankAccount.class.getDeclaredField("balance");
        assertTrue(Modifier.isVolatile(balanceField.getModifiers()),
                "We expected balance to now be a volatile double");
        assertSame(double.class, balanceField.getType(),
                "We expected balance to now be a volatile double");

        try {
            Field BALANCEField = BankAccount.class.getDeclaredField("BALANCE");
            assertTrue(Modifier.isStatic(BALANCEField.getModifiers())
                            && Modifier.isFinal(BALANCEField.getModifiers())
                            && Modifier.isPrivate(BALANCEField.getModifiers()),
                    "VarHandle should be private final static");
        } catch (NoSuchFieldException e) {
            fail("Expected VarHandle to have the same name as field, but upper case");
        }
    }

    @Test
    public void testVisibility() throws InterruptedException {
        BankAccount account = new BankAccount(1000);
        Thread thread = new Thread(() -> {
            while (account.getBalance() == 1000) ;
        });
        thread.start();
        Thread.sleep(500);
        account.deposit(100);
        thread.join(100);
        assertFalse(thread.isAlive(), "Expected thread to shut down after deposit(100)");
    }
}
