package org.fallico.bravofly;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by ricky on 29/11/15.
 */
public class Receipt {
    private String title;
    private LinkedHashMap<String, Product> map;
    private double salesTaxes = 0.00;
    private double total = 0.00;
    private DecimalFormat df = new DecimalFormat("#.##");

    public Receipt(String title, LinkedHashMap<String, Product> map) {
        this.title = title;
        this.map = map;
        df.setMinimumFractionDigits(2);
    }

    public String getSalesTaxes() {
        return df.format(salesTaxes);
    }

    public String getTotal() {
        return df.format(total);
    }

    private void addToSalesTaxes(double tax) {
        this.salesTaxes += tax;
    }
    private void addToTotal(double total) {
        this.total += total;
    }

    public List<ReceiptLine> getReceiptLines() {
        List<ReceiptLine> ret = new ArrayList<ReceiptLine>();
        for (String key: map.keySet()) {
            Product product = map.get(key);
            double tax = new TaxCalculator(product).getTax();
            double total = product.getPrice() + tax;
            ReceiptLine receiptLine = new ReceiptLine(product.getQuantity(), key, df.format(total));
            addToSalesTaxes(tax);
            addToTotal(total);
            ret.add(receiptLine);
        }
        return ret;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedHashMap<String, Product> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, Product> map) {
        this.map = map;
    }
}
