package org.fallico.bravofly;

import java.util.List;

/**
 * Created by ricky on 29/11/15.
 */
public class TaxCalculator {
    private Product product;
    private TaxFilter taxFilter;

    public TaxCalculator(Product product, TaxFilter taxFilter) {
        this.product = product;
        this.taxFilter = taxFilter;
    }

    public TaxCalculator(Product product) {
        this.product = product;
        this.taxFilter = new TaxFilterImpl();
    }

    public double getTax() {
        List<Tax> taxes = taxFilter.applyFor(product);
        double amount = 0.00;
        for(Tax tax: taxes) {
            amount += (product.getPrice() * tax.getTax());
        }
        return Math.round(amount * 20.0)/20.0;
    }
}
