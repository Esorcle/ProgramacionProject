package es.esorcle.swing.db.dao.types;

public class Customer {
    /**
     * Atributos de la clase Customer
     */
    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer salesRepEmployeeNumber;
    private Float creditLimit;

    /**
     * Contructor por defecto
     */
    public Customer() {
    }

    /**
     * Constructor de la clase Customer
     * @param customerNumber Número de cliente.
     * @param customerName Nombre del cliente.
     * @param contactLastName Apellidos de la persona de contacto.
     * @param contactFirstName Nombre de la persona de contacto.
     * @param phone Teléfono.
     * @param addressLine1 Dirección principal.
     * @param city Ciudad.
     * @param country País.
     */
    public Customer(int customerNumber, String customerName, String contactLastName, String contactFirstName, String phone, String addressLine1, String city, String country) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
    }

    /**
     * Método que devuelve una cadena que representa al objeto
     *
     * @retunr String
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", customerName='" + customerName + '\'' +
                ", contactLastName='" + contactLastName + '\'' +
                ", contactFirstName='" + contactFirstName + '\'' +
                ", phone='" + phone + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", salesRepEmployeeNumber=" + salesRepEmployeeNumber +
                ", creditLimit=" + creditLimit +
                '}';
    }

    /**
     * Método para obtener customerNumber.
     *
     * @return int
     */
    public int getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Método para establecer customerNumber.
     *
     * @param customerNumber int
     */
    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     * Método para obtener customerName.
     *
     * @return java.lang.String
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Método para establecer customerName.
     *
     * @param customerName java.lang.String
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Método para obtener contactLastName.
     *
     * @return java.lang.String
     */
    public String getContactLastName() {
        return contactLastName;
    }

    /**
     * Método para establecer contactLastName.
     *
     * @param contactLastName java.lang.String
     */
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    /**
     * Método para obtener contactFirstName.
     *
     * @return java.lang.String
     */
    public String getContactFirstName() {
        return contactFirstName;
    }

    /**
     * Método para establecer contactFirstName.
     *
     * @param contactFirstName java.lang.String
     */
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    /**
     * Método para obtener phone.
     *
     * @return java.lang.String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Método para establecer phone.
     *
     * @param phone java.lang.String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Método para obtener addressLine1.
     *
     * @return java.lang.String
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Método para establecer addressLine1.
     *
     * @param addressLine1 java.lang.String
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Método para obtener addressLine2.
     *
     * @return java.lang.String
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Método para establecer addressLine2.
     *
     * @param addressLine2 java.lang.String
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Método para obtener city.
     *
     * @return java.lang.String
     */
    public String getCity() {
        return city;
    }

    /**
     * Método para establecer city.
     *
     * @param city java.lang.String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Método para obtener state.
     *
     * @return java.lang.String
     */
    public String getState() {
        return state;
    }

    /**
     * Método para establecer state.
     *
     * @param state java.lang.String
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Método para obtener postalCode.
     *
     * @return java.lang.String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Método para establecer postalCode.
     *
     * @param postalCode java.lang.String
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Método para obtener country.
     *
     * @return java.lang.String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Método para establecer country.
     *
     * @param country java.lang.String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Método para obtener salesRepEmployeeNumber.
     *
     * @return java.lang.Integer
     */
    public Integer getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    /**
     * Método para establecer salesRepEmployeeNumber.
     *
     * @param salesRepEmployeeNumber java.lang.Integer
     */
    public void setSalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    /**
     * Método para obtener creditLimit.
     *
     * @return java.lang.Float
     */
    public Float getCreditLimit() {
        return creditLimit;
    }

    /**
     * Método para establecer creditLimit.
     *
     * @param creditLimit java.lang.Float
     */
    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
    }
}
