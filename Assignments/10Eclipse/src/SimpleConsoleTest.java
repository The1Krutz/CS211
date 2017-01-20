import java.io.FileNotFoundException;

// CS211 BC, W.P. Iverson
// September 2016
public class SimpleConsoleTest {

    public static void main(String[] args) throws FileNotFoundException {
    	Sku one = new Sku("book",13.0);
		Sku two = new Sku("book",13);
		Sku three = new Sku("another",42);
		System.out.println(one.equals(two));  // true
		System.out.println(one.equals(three));  // false
		System.out.println(two); // book, $13.00
		System.out.println(two.priceFor(123)); // 1599
		ShoppingCart basket = new ShoppingCart();
		System.out.println(basket.size()); // 0
		NumSelected five = new NumSelected(two,5); 
		NumSelected six = new NumSelected(three,4); 
		basket.add(five);
		basket.add(six);
		System.out.println(basket.getTotal());  // 233
		six = new NumSelected(three,1);
		basket.add(six);
		System.out.println(basket.getTotal());  // 107
		basket.setDiscount(true);
		System.out.println(basket.getTotal());  // 96.3
    }
}