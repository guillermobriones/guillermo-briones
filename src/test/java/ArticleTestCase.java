import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.uem.Model.Article;


public class ArticleTestCase {
   Article article;
   Article article2;
   
    @BeforeEach
    public void setUp() {
        article = new Article("Articulo1", 2, 10.0, 10);
        article2 = new Article ("Articulo2", 1, 20.0, 0);
    }

    @Test 
    public void testGetGrossTotal() {
        double grossTotal = article.GetGrossTotal();
        assertEquals(20.0, grossTotal);
        double grossTotal2 = article2.GetGrossTotal();
        assertEquals(20.0, grossTotal2);
    }

    @Test
    public void testGetDiscountedAmount() {
        double discountedAmount = article.GetDiscountedamount();
        assertEquals(18.0, discountedAmount);
        double discountedAmount2 = article2.GetDiscountedamount();
        assertEquals(20.0, discountedAmount2);
    }

    @Test 
    public void testGettersAndSetters() {
          assertEquals("Articulo1", article.getNombre());
          assertEquals(2, article.getCantidad());
          assertEquals(10.0, article.getPrecio());
          assertEquals(10.0, article.getDescuento());
    
          article.setNombre("Articulo3");
          article.setCantidad(3);
          article.setPrecio(15.0);
          article.setDescuento(5.0);
    
          assertEquals("Articulo3", article.getNombre());
          assertEquals(3, article.getCantidad());
          assertEquals(15.0, article.getPrecio());
          assertEquals(5.0, article.getDescuento());
     }
}