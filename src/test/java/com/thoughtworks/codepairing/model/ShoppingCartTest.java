package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final int SMALL_PRICE = 20;
    public static final String PRODUCT = "Product";

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT,"null"));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(100.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT, "NULL"));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT, "NULL"));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(90.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT, "NULL"));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(10, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT, "NULL"));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT, ""));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceForBuyTwoGetOneFreeWith10Discount() {
        List<Product> products = asList(
                new Product(PRICE, "DIS_10_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "DIS_10_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "DIS_10_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "", "product 2", "")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(280, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyTwoGetOneFreeWith15Discount() {
        List<Product> products = asList(
                new Product(PRICE, "DIS_15_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "DIS_15_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "DIS_15_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "", "product 2", "")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(270, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyTwoGetOneFreeWith20Discount() {
        List<Product> products = asList(
                new Product(PRICE, "DIS_20_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "DIS_20_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "DIS_20_ABCD", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "", "product 2", "")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(260, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyTwoGetOneFree() {
        List<Product> products = asList(
                new Product(PRICE, "", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "", PRODUCT, "BUY_TWO_GET_ONE_FREE"),
                new Product(PRICE, "", PRODUCT, "BUY_TWO_GET_ONE_FREE")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(200, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyFiveGetTwoFreeWith10Discount() {
        List<Product> products = asList(
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_10_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(90, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyFiveGetTwoFreeWith15Discount() {
        List<Product> products = asList(
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_15_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(85, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyFiveGetTwoFreeWith20Discount() {
        List<Product> products = asList(
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "DIS_20_ABCD", PRODUCT, "BUY_FIVE_GET_TWO_FREE")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(80, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuyFiveGetTwoFree() {
        List<Product> products = asList(
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE"),
                new Product(SMALL_PRICE, "", PRODUCT, "BUY_FIVE_GET_TWO_FREE")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(100, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForOver500Discount() {
        List<Product> products = asList(
                new Product(PRICE, "", PRODUCT, ""),
                new Product(PRICE, "", PRODUCT, ""),
                new Product(PRICE, "", PRODUCT, ""),
                new Product(PRICE, "", PRODUCT, ""),
                new Product(PRICE, "", PRODUCT, ""),
                new Product(PRICE, "", PRODUCT, ""),
                new Product(PRICE, "", PRODUCT, "")
        );
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(665, order.getTotalPrice(), 0.0);
    }
}
