package org.fallico.bravofly;

import java.util.List;

/**
 * Created by ricky on 29/11/15.
 */
public interface TaxFilter {
    List<Tax> applyFor(Product product);
}
