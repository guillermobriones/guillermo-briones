import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;


import com.uem.Article;
import com.uem.Order;


public class OrderTestCase {
    List<Article> articles;

    @BeforeEach
    public void setUp() {
        articles = new ArrayList<Article>();
        articles.add(new Article("Articulo1", 2, 10.0, 10));
        articles.add(new Article("Articulo2", 1, 20.0, 0));
    }

    @Test
    public void testGetGrossTotal() {
        Order order = new Order("1", articles);
        double grossTotal = order.GetGrossTotal();
        assertEquals(40.0, grossTotal);
    }

    @Test
    public void testGetDiscountedTotal() {
        Order order = new Order("1", articles);
        double discountedTotal = order.GetDiscountedTotal();
        assertEquals(38.0, discountedTotal);
    }

    @Test
    public void testToString() {
        Order order = new Order("1", articles);
        String result = order.ToString();
        assertEquals("", result);
    }

    @Test 
    public void testGettersAndSetters() {
        Order order = new Order("1", articles);
        assertEquals("1", order.getId());
        assertEquals(articles, order.getArticles());

        order.setId("2");
        assertEquals("2", order.getId());

        List<Article> newArticles = new ArrayList<Article>();
        newArticles.add(new Article("Articulo3", 3, 15.0, 5));
        order.setArticles(newArticles);
        assertEquals(newArticles, order.getArticles());
    }


}