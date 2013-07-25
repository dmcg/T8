package picasso.t8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class T8CharactersTest {

    private final T8 t8 = new T8(Collections.<String>emptyList());

    @Test public void inputCharFor() {
        assertEquals('2', t8.inputCharFor('a'));
        assertEquals('2', t8.inputCharFor('b'));
        assertEquals('2', t8.inputCharFor('c'));

        assertEquals('3', t8.inputCharFor('d'));
    }

    @Test public void stems() {
        assertTrue(t8.stemsFor("").isEmpty());

        assertEquals(asList("a"), t8.stemsFor("a"));
        assertEquals(asList("a", "aa", "aar", "aard"), t8.stemsFor("aard"));
    }

    @Test public void inputFor() {
        assertEquals("2", t8.inputFor("a"));
        assertEquals("2223", t8.inputFor("abcd"));
    }

    @Test public void inputStemsFor() {
        assertEquals(asList("2", "22", "222", "2223"), t8.inputStemsFor("abcd"));
    }

}
