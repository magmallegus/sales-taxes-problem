package org.fallico.bravofly;

import java.util.List;

/**
 * Created by ricky on 29/11/15.
 */
public class ReceiptPrinterDefault implements ReceiptPrinter {
    private Receipt receipt;

    public ReceiptPrinterDefault(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public String print() {
        StringBuilder output = new StringBuilder(receipt.getTitle()).append("\n");
        List<ReceiptLine> receiptLines = receipt.getReceiptLines();

        for(ReceiptLine line: receiptLines) {
            output.append(line.getQuantity()).append(" ").append(line.getDescription()).append(": ").append(line.getPriceWithTax()).append("\n");
        }
        output.append("Sales Taxes: ").append(receipt.getSalesTaxes()).append("\n");
        output.append("Total: ").append(receipt.getTotal());
        return output.toString();
    }
}
