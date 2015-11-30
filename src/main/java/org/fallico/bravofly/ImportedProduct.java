package org.fallico.bravofly;

/**
 * Created by ricky on 29/11/15.
 */
public class ImportedProduct extends Product {
    public ImportedProduct(ProductType productType, int quantity, double price) {
        super(productType, quantity, price);
        super.imported = true;
    }
}
