import com.uem.Searcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearcherTestCase {
    private Searcher searcher;

    @BeforeEach
    void setUp() {
        searcher = new Searcher();
    }

    @Test
    void searchWordWhenItExists() {
        assertTrue(searcher.searchWord("banana", List.of("apple", "banana", "cherry")));
    }

    @Test
    void searchWordWhenItDoesNotExist() {
        assertFalse(searcher.searchWord("orange", List.of("apple", "banana", "cherry")));
    }

    @Test
    void getWordByValidIndex() {
        assertEquals("banana", searcher.getWordByIndex(List.of("apple", "banana", "cherry"), 1));
    }

    @Test
    void getWordByInvalidIndexReturnsNull() {
        List<String> words = List.of("apple", "banana");
        assertNull(searcher.getWordByIndex(words, -1));
        assertNull(searcher.getWordByIndex(words, 2));
        assertNull(searcher.getWordByIndex(List.of(), 0));
    }

    @Test
    void searchByPrefixOnlyReturnsMatchingWords() {
        List<String> words = List.of("casa", "cama", "perro", "escama", "camino");
        assertEquals(List.of("casa", "cama", "camino"), searcher.searchByPrefix("ca", words));
    }

    @Test
    void searchByPrefixWithNoMatchesReturnsEmptyList() {
        assertEquals(List.of(), searcher.searchByPrefix("xy", List.of("casa", "perro")));
    }

    @Test
    void filterByKeywordReturnsAllMatches() {
        List<String> words = List.of("perro grande", "gato", "perro pequeño", "perro");
        assertEquals(List.of("perro grande", "perro pequeño", "perro"),
                searcher.filterByKeyword("perro", words));
    }

    @Test
    void filterByKeywordWithNoMatchesReturnsEmptyList() {
        assertEquals(List.of(), searcher.filterByKeyword("pez", List.of("perro", "gato")));
    }

    @Test
    void searchExactPhraseFindsPhraseAfterFirstElement() {
        assertTrue(searcher.searchExactPhrase("banana", List.of("apple", "banana", "cherry")));
    }

    @Test
    void searchExactPhraseRejectsAbsentPhraseAndPartialMatch() {
        List<String> words = List.of("apple", "banana split", "cherry");
        assertFalse(searcher.searchExactPhrase("banana", words));
        assertFalse(searcher.searchExactPhrase("orange", words));
        assertFalse(searcher.searchExactPhrase("apple", List.of()));
    }
}