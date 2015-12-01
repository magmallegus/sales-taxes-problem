package org.fallico.bravofly;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricky on 29/11/15.
 */


public class SalesTaxTest {
    public static final String BOOK = "book";
    public static final String MUSIC_CD = "music CD";
    public static final String CHOCOLATE_BAR = "chocolate BAR";
    public static final String IMPORTED_BOX_OF_CHOCOLATES = "imported box of chocolates";
    public static final String IMPORTED_BOTTLE_OF_PERFUME = "imported bottle of perfume";
    public static final String BOTTLE_OF_PERFUME = "bottle of perfume";
    public static final String PACKET_OF_HEADACHE_PILLS = "packet of headache pills";
    public static final String BOX_OF_IMPORTED_CHOCOLATES = "box of imported chocolates";
    static LinkedHashMap<String, Product> input1;
    static LinkedHashMap<String, Product> input2;
    static LinkedHashMap<String, Product> input3;
    TaxFilter taxFilter = new TaxFilterImpl();

    @BeforeClass
    public static void setup(){
        input1 = new LinkedHashMap<String, Product>();
        input1.put(BOOK, new Product(ProductType.Book, 1, 12.49));
        input1.put(MUSIC_CD, new Product(ProductType.Other, 1, 14.99));
        input1.put(CHOCOLATE_BAR, new Product(ProductType.Food, 1, 0.85));

        input2 = new LinkedHashMap<String, Product>();
        input2.put(IMPORTED_BOX_OF_CHOCOLATES, new ImportedProduct(ProductType.Food, 1, 10.00));
        input2.put(IMPORTED_BOTTLE_OF_PERFUME, new ImportedProduct(ProductType.Other, 1, 47.50));

        input3 = new LinkedHashMap<String, Product>();
        input3.put(IMPORTED_BOTTLE_OF_PERFUME, new ImportedProduct(ProductType.Other, 1, 27.99));
        input3.put(BOTTLE_OF_PERFUME, new Product(ProductType.Other, 1, 18.99));
        input3.put(PACKET_OF_HEADACHE_PILLS, new Product(ProductType.Medical, 1, 9.75));
        input3.put(BOX_OF_IMPORTED_CHOCOLATES, new ImportedProduct(ProductType.Food, 1, 11.25));
    }
    @Test
    public void noTaxForBook1(){
        Product book = input1.get(BOOK);
        TaxCalculator tc = new TaxCalculator(book, taxFilter);
        assertEquals(12.49, getPriceWithTax(book, tc), 0.01);
        assertEquals(0.00, tc.getTax(), 0.01);
    }
    @Test
    public void taxForMusic1(){
        Product music = input1.get(MUSIC_CD);
        TaxCalculator tc = new TaxCalculator(music, taxFilter);
        assertEquals(16.49, getPriceWithTax(music, tc), 0.01);
        assertEquals(1.50, tc.getTax(), 0.01);
    }
    @Test
    public void noTaxForChocolate1(){
        Product choco = input1.get(CHOCOLATE_BAR);
        TaxCalculator tc = new TaxCalculator(choco, taxFilter);
        assertEquals(0.85, getPriceWithTax(choco, tc), 0.01);
        assertEquals(0.00, tc.getTax(), 0.01);
    }

    @Test
    public void onlyDutyTaxForImportedChocolate2(){
        Product importedChoco = input2.get(IMPORTED_BOX_OF_CHOCOLATES);
        TaxCalculator tc = new TaxCalculator(importedChoco, taxFilter);
        assertEquals(10.50, getPriceWithTax(importedChoco, tc), 0.01);
        assertEquals(0.50, tc.getTax(), 0.01);
    }
    @Test
    public void allTaxesForImportedPerfume2() {
        Product importedPerfume = input2.get(IMPORTED_BOTTLE_OF_PERFUME);
        TaxCalculator tc = new TaxCalculator(importedPerfume, taxFilter);
        assertEquals(7.15, tc.getTax(), 0.01);
        assertEquals(54.65, getPriceWithTax(importedPerfume, tc), 0.01);
    }
    @Test
    public void allTaxesForImportedPerfume3() {
        Product importedPerfume = input3.get(IMPORTED_BOTTLE_OF_PERFUME);
        TaxCalculator tc = new TaxCalculator(importedPerfume, taxFilter);
        assertEquals(32.19, getPriceWithTax(importedPerfume, tc), 0.01);
        assertEquals(4.20, tc.getTax(), 0.01);
    }
    @Test
    public void onlyDutyTaxesForPerfume3() {
        Product perfume = input3.get(BOTTLE_OF_PERFUME);
        TaxCalculator tc = new TaxCalculator(perfume, taxFilter);
        assertEquals(20.89, getPriceWithTax(perfume, tc), 0.01);
        assertEquals(1.90, tc.getTax(), 0.01);
    }
    @Test
    public void noTaxForPills3() {
        Product product = input3.get(PACKET_OF_HEADACHE_PILLS);
        TaxCalculator tc = new TaxCalculator(product, taxFilter);
        assertEquals(9.75, getPriceWithTax(product, tc), 0.01);
        assertEquals(0.00, tc.getTax(), 0.01);
    }
    @Test
    public void onlyDutyForOImportedChocolate3() {
        Product product = input3.get(BOX_OF_IMPORTED_CHOCOLATES);
        TaxCalculator tc = new TaxCalculator(product, taxFilter);
        assertEquals(11.85, getPriceWithTax(product, tc), 0.01);
        assertEquals(0.60, tc.getTax(), 0.01);
    }

    @Test
    public void printReceipt1() {

        assertEquals("Output 1:\n" +
                "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate BAR: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83", new  ReceiptPrinterDefault(new Receipt("Output 1:", input1)).print()
        );
    }
    @Test
    public void printReceipt2() {
        assertEquals("Output 2:\n" +
                "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15", new ReceiptPrinterDefault(new Receipt("Output 2:", input2)).print()
        );
    }

    @Test
    public void printReceipt3() {
        assertEquals("Output 3:\n" +
                "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 imported box of chocolates: 11.85\n" +
                "Sales Taxes: 6.70\n" +
                "Total: 74.68", new ReceiptPrinterDefault(new Receipt("Output 3:", input3)).print()

        );
    }

    private double getPriceWithTax(Product product, TaxCalculator tc) {
        return tc.getTax() + product.getPrice();
    }
}
