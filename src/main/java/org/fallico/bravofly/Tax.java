package org.fallico.bravofly;

/**
 * Created by ricky on 29/11/15.
 */
public enum Tax {
    Basic(0.1), Duty(0.05);

    private final double tax;
    Tax(double tax) {
        this.tax = tax;
    }
    public double getTax() { return tax; }
}
