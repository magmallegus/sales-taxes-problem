package org.fallico.bravofly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricky on 29/11/15.
 */
public class TaxFilterImpl implements TaxFilter {

    @Override
    public List<Tax> applyFor(Product product){
        List<Tax> appliedTax = new ArrayList<Tax>();

        if(product.isImported()) {
            appliedTax.add(Tax.Duty);
        }
        if(ProductType.Other.equals(product.getProductType())) {
            appliedTax.add(Tax.Basic);
        }
        return appliedTax;
    }
}
