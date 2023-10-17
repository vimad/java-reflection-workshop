package reflection.ch1_introduction_to_reflection.exercise_1A;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.*;

import static reflection.util.TestHelpers.assertClassesEquals;

public class ClassExplorerTest {
    interface A {}
    interface B {}
    interface C extends A, B {}
    interface D {}
    interface E {}
    interface F extends C {}
    class G implements D {}
    class H extends G implements C {}
    class I extends H {}
    class J extends I implements F {}
    class K extends J implements E {}

    @Test
    public void testG() {
        var interfaces = ClassExplorer.findAllInterfaces(G.class);
        assertClassesEquals(Set.of(D.class), interfaces);
    }

    @Test
    public void testH() {
        var interfaces = ClassExplorer.findAllInterfaces(H.class);
        assertClassesEquals(Set.of(A.class, B.class, C.class, D.class), interfaces);
    }

    @Test
    public void testI() {
        var interfaces = ClassExplorer.findAllInterfaces(I.class);
        assertClassesEquals(Set.of(A.class, B.class, C.class, D.class), interfaces);
    }

    @Test
    public void testJ() {
        var interfaces = ClassExplorer.findAllInterfaces(J.class);
        assertClassesEquals(Set.of(A.class, B.class, C.class, D.class, F.class), interfaces);
    }

    @Test
    public void testK() {
        var interfaces = ClassExplorer.findAllInterfaces(K.class);
        assertClassesEquals(Set.of(A.class, B.class, C.class, D.class, E.class, F.class), interfaces);
    }

    @Test
    public void testString() {
        var interfaces = ClassExplorer.findAllInterfaces(String.class);
        assertClassesEquals(Set.of(Constable.class, CharSequence.class, Comparable.class,
                Serializable.class, ConstantDesc.class), interfaces);
    }

    @Test
    public void testLinkedList() {
        var interfaces = ClassExplorer.findAllInterfaces(LinkedList.class);
        assertClassesEquals(Set.of(Iterable.class, Collection.class, Serializable.class,
                Deque.class, Queue.class, Cloneable.class, List.class), interfaces);
    }
}