package es.esorcle.swing.db.dao.types;

public class Employee {
    /**
     * Atributos de la clase Employee.
     */
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private Integer reportsTo;
    private String jobTitle;

    /**
     * Constructor por defecto de la clase Employee.
     */
    public Employee() {
    }

    /**
     * Constructor de la clase Employee.
     * @param employeeNumber int. Número de empleado.
     * @param lastName String. Apellidos del empleado.
     * @param firstName String. Nombre del empleado.
     * @param extension String. Extensión del empleado.
     * @param email String. Email del empleado.
     * @param officeCode String. Código de la oficina del empleado.
     * @param reportsTo int. Número de empleado de su jefe.
     * @param jobTitle String. Puesto del empleado.
     */
    public Employee(int employeeNumber, String lastName, String firstName, String extension, String email, String officeCode, int reportsTo, String jobTitle) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.reportsTo = reportsTo;
        this.jobTitle = jobTitle;
    }

    /**
     * Método que devuelve una cadena que representa al objeto
     *
     * @retunr String
     */
    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", extension='" + extension + '\'' +
                ", email='" + email + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", reportsTo=" + reportsTo +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }

    /**
     * Método para obtener employeeNumber.
     *
     * @return int
     */
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * Método para establecer employeeNumber.
     *
     * @param employeeNumber int
     */
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * Método para obtener lastName.
     *
     * @return java.lang.String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Método para establecer lastName.
     *
     * @param lastName java.lang.String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Método para obtener firstName.
     *
     * @return java.lang.String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Método para establecer firstName.
     *
     * @param firstName java.lang.String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Método para obtener extension.
     *
     * @return java.lang.String
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Método para establecer extension.
     *
     * @param extension java.lang.String
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Método para obtener email.
     *
     * @return java.lang.String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método para establecer email.
     *
     * @param email java.lang.String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método para obtener officeCode.
     *
     * @return java.lang.String
     */
    public String getOfficeCode() {
        return officeCode;
    }

    /**
     * Método para establecer officeCode.
     *
     * @param officeCode java.lang.String
     */
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    /**
     * Método para obtener reportsTo.
     *
     * @return java.lang.Integer
     */
    public Integer getReportsTo() {
        return reportsTo;
    }

    /**
     * Método para establecer reportsTo.
     *
     * @param reportsTo java.lang.Integer
     */
    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

    /**
     * Método para obtener jobTitle.
     *
     * @return java.lang.String
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Método para establecer jobTitle.
     *
     * @param jobTitle java.lang.String
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
