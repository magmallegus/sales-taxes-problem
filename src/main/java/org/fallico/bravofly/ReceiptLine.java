package org.fallico.bravofly;

/**
 * Created by ricky on 30/11/15.
 */
public class ReceiptLine {
    private int quantity;
    private String description;
    private String priceWithTax;
    public ReceiptLine(int quantity, String key, String priceWithTax) {
        this.quantity = quantity;
        this.description = key;
        this.priceWithTax = priceWithTax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceWithTax() {
        return priceWithTax;
    }

    public void setPriceWithTax(String priceWithTax) {
        this.priceWithTax = priceWithTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
