package picasso.t8;

import org.junit.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class T8WordsTest {

    @Test public void singleEntry() {
        T8 t8 = new T8(asList("a"));
        assertEquals(asList(), t8.suggestionsFor("1"));
        assertEquals(asList("a"), t8.suggestionsFor("2"));
    }

    @Test public void singleCharacterEntries() {
        T8 t8 = new T8(asList("a", "b", "d"));
        assertEquals(asList(), t8.suggestionsFor("1"));
        assertEquals(asList("a", "b"), t8.suggestionsFor("2"));
        assertEquals(asList("d"), t8.suggestionsFor("3"));
    }

    @Test public void multiCharacterEntries() {
        T8 t8 = new T8(asList("a", "aa", "ad", "aab", "aad", "d"));
        assertEquals(asList(), t8.suggestionsFor("1"));
        assertEquals(asList("a", "aa", "ad", "aab", "aad"), t8.suggestionsFor("2"));
        assertEquals(asList("aa", "aab", "aad"), t8.suggestionsFor("22"));
        assertEquals(asList("aab"), t8.suggestionsFor("222"));
        assertEquals(asList("d"), t8.suggestionsFor("3"));
    }

}
