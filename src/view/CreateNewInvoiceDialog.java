package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static controller.FileOperationsController.dateFormat;

/**
 * Dialog to create new invoice
 */
public class CreateNewInvoiceDialog extends JDialog {
    private JLabel customerNameLabel;
    private JTextField customerName;
    private JLabel invoiceDateLabel;
    private JFormattedTextField invoiceDate;
    private JButton okButton;
    private JButton cancelButton;

    public CreateNewInvoiceDialog(MainScreen frame) {

        customerNameLabel = new JLabel("Customer Name : ");
        customerName = new JTextField(20);
        invoiceDateLabel = new JLabel("Invoice Date : ");

        invoiceDate = new JFormattedTextField(dateFormat);
        invoiceDate.setToolTipText("Format: day-month-year, ex: 22-12-2020" );
        invoiceDate.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_MINUS))) {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
                    e.consume();
                }
            }
        });

        okButton = new JButton("Ok");
        okButton.setActionCommand("createInvoice");
        okButton.addActionListener(frame);
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("cancelInvoice");
        cancelButton.addActionListener(frame);

        setLayout(new GridLayout(3, 2));
        setResizable(false);

        add(invoiceDateLabel);
        add(invoiceDate);
        add(customerNameLabel);
        add(customerName);
        add(okButton);
        add(cancelButton);
        pack();
    }

    public String getCustomerName() {
        return customerName.getText();
    }

    public String getInvoiceDate() {
        if (invoiceDate.getText() != null)
            return invoiceDate.getText();
        return "";
    }
}
