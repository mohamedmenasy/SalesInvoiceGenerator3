import controller.FileOperationsController;
import model.pojo.InvoiceHeader;
import model.pojo.InvoiceLine;
import view.MainScreen;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        new MainScreen("Sales Invoice Generator");
        testFileOperation();
    }

    private static void testFileOperation() {
        FileOperationsController fileOperations = new FileOperationsController();
        try {
            ArrayList<InvoiceHeader> invoiceHeaders =
                    fileOperations.loadInvoiceHeader(new File("InvoiceHeader.csv"));
            fileOperations.loadInvoiceItems(invoiceHeaders, new File("InvoiceLine.csv"));

            for (InvoiceHeader invoiceHeader : invoiceHeaders) {
                System.out.println(invoiceHeader.getInvoiceNum());
                System.out.println("{");
                System.out.print(new SimpleDateFormat("dd/MM/yyyy").format(invoiceHeader.getInvoiceDate()));
                System.out.print(", ");
                System.out.println(invoiceHeader.getCustomerName());
                for (InvoiceLine invoiceLine : invoiceHeader.getInvoiceLines()) {
                    System.out.print(invoiceLine.getItemName() + ", ");
                    System.out.print(invoiceLine.getItemPrice() + ", ");
                    System.out.print(invoiceLine.getCount());
                    System.out.println();
                }
                System.out.println("}");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
