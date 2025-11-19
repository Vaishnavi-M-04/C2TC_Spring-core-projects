package com.springone.autowiringXml;

import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springone.autowiringXml.Product;

public class ProductMain {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("productconfig.xml");

    	// Fetching the Cart bean with autowire="byType"        
    	Cart cartByType = ac.getBean("cartByType", Cart.class);
    	// Displaying the total price with the autowiring method used
    	System.out.println("Total price using autowire byType: " + calculateTotal(cartByType));

        // Fetching the Cart bean with autowire="byName"
        Cart cartByName = ac.getBean("cartByName", Cart.class);
        // Displaying the total price with the autowiring method used
        System.out.println("Total price using autowire byName: " + calculateTotal(cartByName));

        // Fetching the Cart bean that explicitly references the productList
        Cart cartWithRef = ac.getBean("cart", Cart.class);
        System.out.println("Total price using explicit reference: " + calculateTotal(cartWithRef));

        
		
		
		
		  Product p1 = ac.getBean("productThree", Product.class); 
		  Product p2 = ac.getBean("productThree", Product.class);
		  
		  System.out.println(p1 == p2); // false (Different instances due toprototypescope)
		  
		  Product p3 = ac.getBean("productOne", Product.class); 
		  Product p4 = ac.getBean("productOne", Product.class);
		  
		  System.out.println(p3 == p4); 
		 
    }

    private static double calculateTotal(Cart cart) 
    {
    	// Retrieving the list of products from the cart
    	ArrayList<com.springone.autowiringXml.Product> list = cart.getProductList();
        double total = 0;
        // Calculating the total price of all products
        for (com.springone.autowiringXml.Product prod : list) {
            total += prod.getPrice();
        }
        return total;

	}

}
