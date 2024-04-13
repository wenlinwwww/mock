package com.thoughtworks.codepairing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<Product> products;
    private Customer customer;

    public ShoppingCart(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Order checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        Map<String, Integer> productCountMap = new HashMap<>();
        // map (key product int count)
        // count % 3 = 0, countinue。
        // 价格
        // checkout不要用这么多if else，code有一百种。不建议写if else，用枚举实现if else逻辑。
        // for循环嵌套不要太多。

        // 积分问题，买二送一，continue，不同的个数，判断，count是不是大于2，是不是被checkout一次了，还有出现单双次数。
        // 加个函数，先判断product重复东西，加个map，买三件，累计两个，或者累计三个
//        for (Product product : products) {
//            double discount = 0;
//            if (productCountMap.get(product) == null) {
//                productCountMap.put(product, 1);
//            } else {
//                productCountMap.put(product, productCountMap.get(product)+1);
//            }
//            if (product.getProductCode().startsWith("DIS_10")) {
//                discount = (product.getPrice() * 0.1);
//                loyaltyPointsEarned += (product.getPrice() / 10);
//            } else if (product.getProductCode().startsWith("DIS_15")) {
//                discount = (product.getPrice() * 0.15);
//                loyaltyPointsEarned += (product.getPrice() / 15);
//            } else if (product.getProductCode().startsWith("DIS_20")) {
//                discount = (product.getPrice() * 0.2);
//                loyaltyPointsEarned += (product.getPrice() / 20);
//            } else {
//                loyaltyPointsEarned += (product.getPrice() / 5);
//            }
//
//            totalPrice += product.getPrice() - discount;
//        }
        for (Product product : products) {
            double discount;
            double points;
            productCountMap.merge(product.getName(), 1, Integer::sum);
            int count = productCountMap.get(product.getName());

            ProductDiscount discountEnum = transferDiscountToEnum(product.getProductCode());
            discount = discounts(product.getPrice(), discountEnum.getDiscount());
            if (!product.getProductCode().isEmpty()) {
                points = loyaltyPoints(product.getPrice(), discountEnum.getDiscount());
            } else {
                points = product.getPrice() / 5;
            }
            Sale sale = transferSaleToEnum(product.getTag());
            if (!product.getTag().isEmpty() && sale != Sale.NULL) {
                int itemsToBuy = sale.getBuyNumber() + sale.getFreeNumber();
                int free = sale.getFreeNumber();
                if (count % itemsToBuy != 0) {
                    loyaltyPointsEarned += (int) points;
                    totalPrice += product.getPrice() - discount;
                } else if (sale.getFreeNumber() > 1) {
                    loyaltyPointsEarned -= (int) (points * (free - 1));
                    totalPrice -= (free - 1) * (product.getPrice() - discount);
                }
            } else if (product.getTag().isEmpty() || sale == Sale.NULL) {
                loyaltyPointsEarned += (int) points;
                totalPrice += product.getPrice() - discount;
            }
        }
        if (totalPrice >= 500) {
            totalPrice = totalPrice * 0.95;
        }
        return new Order(totalPrice, loyaltyPointsEarned);
    }


    public Sale transferSaleToEnum(String s) {
        if (s == null || s.isEmpty()) return Sale.NULL;
        Pattern pattern = Pattern.compile("^(BUY_.+|null)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        String enumWord;
        Sale sale = null;
        if (matcher.find()) {
            enumWord = matcher.group().toUpperCase();
            try {
                sale = Sale.valueOf(enumWord);
            } catch (IllegalArgumentException e) {
                System.out.println("No enum match");
            }
        } else {
            System.out.println("No string matches regex");
        }
        return sale;
    }

    public ProductDiscount transferDiscountToEnum(String s) {
        if (s == null || s.isEmpty()) return ProductDiscount.NULL;
        Pattern pattern = Pattern.compile("^DIS_\\d+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        String enumWord;
        ProductDiscount productDiscount = null;
        if (matcher.find()) {
            enumWord = matcher.group().toUpperCase();
            try {
                productDiscount = ProductDiscount.valueOf(enumWord);
            } catch (IllegalArgumentException e) {
                System.out.println("No enum match");
            }
        } else {
            System.out.println("No string matches regex");
        }
        return productDiscount;
    }

    public double discounts(double price, double percentage) {
        return price * percentage;
    }

    public double loyaltyPoints(double price, double divider) {
        if (divider != 0) return price / (divider * 100);
        return 0;
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName() + ", " + p.getPrice()).collect(Collectors.joining("\n"));
    }
}
