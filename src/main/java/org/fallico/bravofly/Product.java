package org.fallico.bravofly;

/**
 * Created by ricky on 29/11/15.
 */
public class Product {
    protected double price;
    protected int quantity;
    protected boolean imported;
    protected ProductType productType;

    public Product(ProductType productType, int quantity, double price) {
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.imported = false;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
