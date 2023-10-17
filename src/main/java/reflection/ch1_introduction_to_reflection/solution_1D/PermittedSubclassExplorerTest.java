package reflection.ch1_introduction_to_reflection.solution_1D;

import org.junit.jupiter.api.Test;

import java.lang.constant.ConstantDesc;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static reflection.util.TestHelpers.assertClassesEquals;
import static reflection.util.TestHelpers.assertStringsClassesEquals;

public class PermittedSubclassExplorerTest {
    @Test
    public void testExecutable() {
        // works for Java 17 until 19
        assertClassesEquals(Set.of(Method.class, Constructor.class),
                PermittedSubclassExplorer.find(Executable.class));
    }

    @Test
    public void testConstantDesc() {
        // works for Java 17 until 19
        assertStringsClassesEquals(Set.of("ClassDesc", "PrimitiveClassDescImpl", "ReferenceClassDescImpl",
                        "MethodHandleDesc", "AsTypeMethodHandleDesc", "DirectMethodHandleDesc", "DirectMethodHandleDescImpl",
                        "MethodTypeDesc", "MethodTypeDescImpl", "DynamicConstantDesc",
                        "Double", "Float", "Integer", "Long", "String"),
                PermittedSubclassExplorer.find(ConstantDesc.class));
    }

    @Test
    public void testString() {
        assertThrows(IllegalArgumentException.class,
                () -> PermittedSubclassExplorer.find(String.class));
    }

    sealed interface A {}
    sealed interface B extends A {}
    sealed interface C extends A {}
    sealed interface D extends B, C {}
    sealed class E implements A {}
    non-sealed class F extends E {}
    final class G extends F {} // not in set
    final class H extends E implements D {}

    @Test
    public void testOwnHierarchy() {
        assertStringsClassesEquals(
                Set.of("B", "C", "D", "E", "F", "H"),
                PermittedSubclassExplorer.find(A.class)
        );
    }
}
