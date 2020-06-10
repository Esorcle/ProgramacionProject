package es.esorcle.swing.db.dao.types;

import java.sql.Date;

public class Payment {
    /**
     * Atributos de la clase
     */
    private Customer customer;
    private String checkNumber;
    private Date paymentDate;
    private double amount;

    /**
     * Constructor por defecto para clase Payment
     */
    public Payment() {
    }

    /**
     * Constructor clase Payment
     * @param customer número de cliente
     * @param checkNumber código del pago
     * @param paymentDate fecha del pago
     * @param amount cantidad total del pago
     */
    public Payment(Customer customer, String checkNumber, Date paymentDate, double amount) {
        this.customer = customer;
        this.checkNumber = checkNumber;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    /**
     * Método que devuelve una cadena que representa al objeto
     *
     * @retunr String
     */
    @Override
    public String toString() {
        return "Payment{" +
                "customer=" + customer +
                ", checkNumber='" + checkNumber + '\'' +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }

    /**
     * Método para obtener customer.
     *
     * @return es.esorcle.swing.db.dao.types.Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Método para establecer customer.
     *
     * @param customer es.esorcle.swing.db.dao.types.Customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Método para obtener checkNumber.
     *
     * @return java.lang.String
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * Método para establecer checkNumber.
     *
     * @param checkNumber java.lang.String
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * Método para obtener paymentDate.
     *
     * @return java.sql.Date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Método para establecer paymentDate.
     *
     * @param paymentDate java.sql.Date
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Método para obtener amount.
     *
     * @return double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Método para establecer amount.
     *
     * @param amount double
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
