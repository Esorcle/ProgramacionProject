package es.esorcle.swing.db.dao;

import es.esorcle.swing.db.ConnectionDB;
import es.esorcle.swing.db.dao.types.Customer;
import es.esorcle.swing.db.dao.types.Employee;
import es.esorcle.swing.db.dao.types.Payment;
import es.esorcle.swing.utils.Utils;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ADClassicModels {
    /**
     * Método para dar formato a los números decimales.
     * Se estabalece en 2 decimales.
     */
    private static final DecimalFormat decimalFormat = new DecimalFormat("####.##");

    /**
     * Método para insertar un nuevo cliente.
     * Comprueba si ya existe en la base de datos.
     * @param customer Cliente a insertar.
     * @return Si se ha podido realizar la operación o no.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static boolean insertCustomer(Customer customer) throws ClassicModelsException {
        boolean inserted = false;

        Connection connection = ConnectionDB.getConnection();

        try {
            //Compruebo si el cliente que quiero introducir existe ya en la BD
            inserted = existCustomer(customer.getCustomerNumber());

            //Si exite lanzo un error
            if (inserted) {
                throw new ClassicModelsException("Lo siento, pero ese número de cliente ya existe. Ingrese un número nuevo para el cliente");
                //Si no exixte, continuo
            } else {
                //Creo la sentencia SQL
                String sqlInsertCustomer = "INSERT INTO customers (customerNumber, customerName, contactLastName, " +
                        "contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, " +
                        "SalesRepEmployeeNumber, creditLimit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sqlInsertCustomer);
                statement.setInt(1, customer.getCustomerNumber());
                statement.setString(2, customer.getCustomerName());
                statement.setString(3, customer.getContactLastName());
                statement.setString(4, customer.getContactFirstName());
                statement.setString(5, customer.getPhone());
                statement.setString(6, customer.getAddressLine1());
                statement.setString(8, customer.getCity());
                statement.setString(11, customer.getCountry());
                if ("".equals(customer.getAddressLine2())) {
                    statement.setString(7, null);
                } else {
                    statement.setString(7, customer.getAddressLine2());
                }
                if ("".equals(customer.getState())) {
                    statement.setString(9, null);
                } else {
                    statement.setString(9, customer.getState());
                }
                if ("".equals(customer.getPostalCode())) {
                    statement.setString(10, null);
                } else {
                    statement.setString(10, customer.getPostalCode());
                }
                if (customer.getSalesRepEmployeeNumber() == null) {
                    statement.setNull(12, Types.INTEGER);
                } else {
                    statement.setInt(12, customer.getSalesRepEmployeeNumber());
                }
                if (customer.getCreditLimit() == null) {
                    statement.setNull(13, Types.FLOAT);
                } else {
                    statement.setFloat(13, customer.getCreditLimit());
                }

                int rsInsertCustomer = statement.executeUpdate();
                if (rsInsertCustomer != 0) {
                    inserted = true;
                } else {
                    inserted = false;
                }
            }
        } catch (SQLException e) {
            throw new ClassicModelsException("Error al ingresar. Compruebe los datos y vuelva a intentarlo");
        }
        return inserted;
    }

    /**
     * Método para actualizar un cliente.
     *
     * @param customer Cliente a actualizar.
     * @return Si se ha podido realizar la operación o no.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static boolean updateCustomer(Customer customer) throws ClassicModelsException {
        Connection connection = ConnectionDB.getConnection();
        boolean updated = false;

        try {
            String sqlUpdateCustomer = "UPDATE customers SET customerName = ?, contactLastName = ?, " +
                    "contactFirstName = ?, phone = ?, addressLine1 = ?, addressLine2 = ? , city = ?, state = ?," +
                    " postalCode = ?, country = ?, SalesRepEmployeeNumber = ?, creditLimit = ? WHERE customerNumber = ?";

            PreparedStatement stUpdateCustomer = connection.prepareStatement(sqlUpdateCustomer);
            stUpdateCustomer.setInt(13, customer.getCustomerNumber());
            stUpdateCustomer.setString(1, customer.getCustomerName());
            stUpdateCustomer.setString(2, customer.getContactLastName());
            stUpdateCustomer.setString(3, customer.getContactFirstName());
            stUpdateCustomer.setString(4, customer.getPhone());
            stUpdateCustomer.setString(5, customer.getAddressLine1());
            stUpdateCustomer.setString(7, customer.getCity());
            stUpdateCustomer.setString(10, customer.getCountry());
            if ("".equals(customer.getAddressLine2())) {
                stUpdateCustomer.setString(6, null);
            } else {
                stUpdateCustomer.setString(6, customer.getAddressLine2());
            }
            if ("".equals(customer.getState())) {
                stUpdateCustomer.setString(8, null);
            } else {
                stUpdateCustomer.setString(8, customer.getState());
            }
            if ("".equals(customer.getPostalCode())) {
                stUpdateCustomer.setString(9, null);
            } else {
                stUpdateCustomer.setString(9, customer.getPostalCode());
            }
            if (customer.getSalesRepEmployeeNumber() == null) {
                stUpdateCustomer.setNull(11, Types.INTEGER);
            } else {
                stUpdateCustomer.setInt(11, customer.getSalesRepEmployeeNumber());
            }
            if (customer.getCreditLimit() == null) {
                stUpdateCustomer.setNull(12, Types.FLOAT);
            } else {
                stUpdateCustomer.setFloat(12, customer.getCreditLimit());
            }

            int rsUpdateCustomer = stUpdateCustomer.executeUpdate();
            if (rsUpdateCustomer != 0) {
                updated = true;
            } else {
                updated = false;
            }
        } catch (SQLException e) {
            throw new ClassicModelsException("Error al actualizar. Compruebe los datos y vuelva a intentarlo");
        }
        return updated;
    }

    /** Método para obtener los datos de un cliente
     * @param customerNumber Número de cliente.
     * @return El cliente.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static Customer getCustomer(int customerNumber) throws ClassicModelsException {
        Connection connection = ConnectionDB.getConnection();
        Customer customer = null;

        try {
            String sqlCustomer = "SELECT * FROM customers WHERE customerNumber = ?";
            PreparedStatement stCustomer = connection.prepareStatement(sqlCustomer);
            stCustomer.setInt(1, customerNumber);

            ResultSet rsCustomer = stCustomer.executeQuery();

            if (rsCustomer.next()) {
                customer = new Customer();
                customer.setCustomerNumber(rsCustomer.getInt("customerNumber"));
                customer.setCustomerName(rsCustomer.getString("customerName"));
                customer.setContactLastName(rsCustomer.getString("contactLastName"));
                customer.setContactFirstName(rsCustomer.getString("contactFirstName"));
                customer.setPhone(rsCustomer.getString("phone"));
                customer.setAddressLine1(rsCustomer.getString("addressLine1"));
                customer.setAddressLine2(rsCustomer.getString("addressLine2"));
                customer.setCity(rsCustomer.getString("city"));
                customer.setState(rsCustomer.getString("state"));
                customer.setPostalCode(rsCustomer.getString("postalCode"));
                customer.setCountry(rsCustomer.getString("country"));
                customer.setSalesRepEmployeeNumber(rsCustomer.getInt("salesRepEmployeeNumber"));
                customer.setCreditLimit(rsCustomer.getFloat("creditLimit"));
            } else {
                throw new ClassicModelsException("No se ha encontrado ningun cliente con la búsqueda realizada");
            }
        } catch (SQLException e) {
            throw new ClassicModelsException("No se ha podido realizar la consulta");
        }
        return customer;
    }

    /**
     * Método para eliminar un cliente.
     * @param customerNumber Número de cliente a eliminar.
     * @return Si se ha podido realizar la operación o no.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static boolean removeCustomer(int customerNumber) throws ClassicModelsException {
        Connection connection = ConnectionDB.getConnection();
        boolean removed = false;

        if (existCustomer(customerNumber)) {
            try {
                connection.setAutoCommit(false);

                String sqlPayment = "DELETE FROM payments WHERE customerNumber = ?";
                PreparedStatement stPayment = connection.prepareStatement(sqlPayment);
                stPayment.setInt(1,customerNumber);

                int payment = stPayment.executeUpdate();

                String sqlOrders = "SELECT orderNumber FROM orders WHERE customerNumber = ? ";
                PreparedStatement stOrders = connection.prepareStatement(sqlOrders);
                stOrders.setInt(1,customerNumber);

                ResultSet rsOders = stOrders.executeQuery();

                String lista = "(";
                List<Integer> ordersDetailsList = new ArrayList<>();
                while(rsOders.next()) {
                    int orderNumber = rsOders.getInt("orderNumber");
                    ordersDetailsList.add(orderNumber);
                    lista += "?,";
                }

                if(!ordersDetailsList.isEmpty()) {
                    lista= lista.substring(0,lista.length()-1);
                    lista = lista + ")";

                    String sqlOrderDetails = "DELETE FROM orderdetails WHERE orderNumber IN " + lista;
                    PreparedStatement stOrderDetails = connection.prepareStatement(sqlOrderDetails);
                    int cont = 1;
                    for (Integer number : ordersDetailsList) {
                        stOrderDetails.setInt(cont, number);
                        cont++;
                    }

                    int removeDetails = stOrderDetails.executeUpdate();

                    String sqlRemoveOrders = "DELETE FROM orders WHERE customerNumber = ?";
                    PreparedStatement stRemoveOrders = connection.prepareStatement(sqlRemoveOrders);
                    stRemoveOrders.setInt(1, customerNumber);

                    int remove = stRemoveOrders.executeUpdate();

                }

                    String sqlRemoveCustomer = "DELETE FROM customers WHERE customerNumber = ?";
                    PreparedStatement stRemoveCustomer = connection.prepareStatement(sqlRemoveCustomer);
                    stRemoveCustomer.setInt(1, customerNumber);

                    int ok = stRemoveCustomer.executeUpdate();

                    if (ok != 0) {
                        removed = true;
                        connection.commit();
                    }

            } catch (SQLException e) {
                try {
                    if (connection != null) {
                        System.out.println("Dejamos la BD en su estado inicial");
                        connection.rollback();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error en el rollback");
                    ex.printStackTrace();
                }
                e.printStackTrace();
                throw new ClassicModelsException("No se ha podido borrar el cliente. Vuelva a intentarlo");
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return removed;
    }

    /**
     * Método para conocer la lista de clientes que coincide con el parámetro de la búsqueda
     * @param busqueda Parámetro de búsqueda.
     * @return lista de clientes.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static List<Customer> getCustomersList(String busqueda) throws ClassicModelsException {
        Connection connection = ConnectionDB.getConnection();
        List<Customer> customerList = new ArrayList<>();

        try {
            String sqlCustomers = "SELECT * FROM customers WHERE customerName LIKE ? OR " +
                    "contactLastName LIKE ? OR contactFirstName LIKE ? OR phone LIKE ? OR addressLine1 LIKE ? " +
                    "OR addressLine2 LIKE ? OR city LIKE ? OR state LIKE ? OR postalCode LIKE ? OR " +
                    "country LIKE ?";
            if (Utils.isDouble(busqueda)) {
                sqlCustomers += " OR customerNumber LIKE ? OR salesRepEmployeeNumber LIKE ? OR creditLimit LIKE ?";
            }
            PreparedStatement stCustomer = connection.prepareStatement(sqlCustomers);

            stCustomer.setString(1, busqueda);
            stCustomer.setString(2, busqueda);
            stCustomer.setString(3, busqueda);
            stCustomer.setString(4, busqueda);
            stCustomer.setString(5, busqueda);
            stCustomer.setString(6, busqueda);
            stCustomer.setString(7, busqueda);
            stCustomer.setString(8, busqueda);
            stCustomer.setString(9, busqueda);
            stCustomer.setString(10, busqueda);

            if (Utils.isDouble(busqueda)) {
                stCustomer.setInt(11, Integer.parseInt(busqueda));
                stCustomer.setInt(12, Integer.parseInt(busqueda));
                stCustomer.setFloat(13, Float.parseFloat(busqueda));
            }

            ResultSet rsCustomer = stCustomer.executeQuery();

            Customer customer = null;

            if (rsCustomer.next()) {
                boolean primero = true;

                while (primero == true || rsCustomer.next()) {
                    customer = new Customer();

                    customer.setCustomerNumber(rsCustomer.getInt("customerNumber"));
                    customer.setCustomerName(rsCustomer.getString("customerName"));
                    customer.setContactLastName(rsCustomer.getString("contactLastName"));
                    customer.setContactFirstName(rsCustomer.getString("contactFirstName"));
                    customer.setPhone(rsCustomer.getString("phone"));
                    customer.setAddressLine1(rsCustomer.getString("addressLine1"));
                    customer.setAddressLine2(rsCustomer.getString("addressLine2"));
                    customer.setCity(rsCustomer.getString("city"));
                    customer.setState(rsCustomer.getString("state"));
                    customer.setPostalCode(rsCustomer.getString("postalCode"));
                    customer.setCountry(rsCustomer.getString("country"));
                    customer.setSalesRepEmployeeNumber(rsCustomer.getInt("salesRepEmployeeNumber"));
                    customer.setCreditLimit(rsCustomer.getFloat("creditLimit"));

                    customerList.add(customer);
                    primero = false;
                }
            } else {
                throw new ClassicModelsException("No se ha encontrado ninguna coincidencia con la búsqueda realizada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    /**
     * Método para asignar un vendedor a un cliente.
     *
     * @param salesId Número del vendedor.
     * @param customerId Número del cliente.
     * @return Si se ha podido realizar la operación o no.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static boolean assingnSales(int salesId, int customerId) throws ClassicModelsException {

        Connection connection = ConnectionDB.getConnection();
        boolean update = false;

        try {
            boolean existed;
            existed = existEmployee(salesId);

                if (existed) {
                    String sqlAssignSales = "UPDATE customers SET salesRepEmployeeNumber = ? WHERE customerNumber = ?";
                    PreparedStatement statementAssignSales = connection.prepareStatement(sqlAssignSales);
                    statementAssignSales.setInt(1, salesId);
                    statementAssignSales.setInt(2, customerId);

                    int returnAssignSales = statementAssignSales.executeUpdate();

                    if (returnAssignSales != 0) {
                        update = true;
                    }
                } else {
                    throw new ClassicModelsException("No existe ese vendedor");
                }
        } catch (SQLException e) { //Atrapo la excepción de fallos en SQL que nada le importa al user
            e.printStackTrace();
            throw new ClassicModelsException("No se ha posdido realizar la consulta");
        }
        return update;
    }

    /**
     * Método para conocer la lista de los vendedores existentes.
     * En beta para añadir funcionalidad a la aplicación.
     * @return Lista de vendedores.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static List<Employee> getSalesRep() throws ClassicModelsException {

        Connection connection = ConnectionDB.getConnection();
        List<Employee> employeeList = new ArrayList<>();

        try {
            String sql = "SELECT employeeNumber, lastName, firstName FROM employees WHERE jobTitle LIKE 'Sales Rep'";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery();

            Employee employee = null;

            while (rs.next()) {

                employee = new Employee();

                employee.setEmployeeNumber(rs.getInt("employeeNumber"));
                employee.setLastName(rs.getString("lastName"));
                employee.setFirstName(rs.getString("firstName"));
//                employee.setExtension(rs.getString("extension"));
//                employee.setEmail(rs.getString("email"));
//                employee.setOfficeCode(rs.getString("officeCode"));
//                employee.setReportsTo(rs.getInt("reportsTo"));
//                employee.setJobTitle(rs.getString("jobTitle"));

                employeeList.add(employee);
            }

        } catch (SQLException e) {
            throw new ClassicModelsException("No se ha podido volcar la lista de vendedores. Por favor, vuelva a intentarlo");
        }
        return employeeList;
    }

    /**
     * Método para obter la lista de pagos de un cliente.
     * @param customerNumber Número de cliente.
     * @return Lista de pagos asociados al cliente.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    public static List<Payment> getPaymentsList(int customerNumber) throws ClassicModelsException {

        Connection connection = ConnectionDB.getConnection();
        List<Payment> paymentList = new ArrayList<>();

            try {
                String sqlPaymentCustomerList = "SELECT * FROM payments WHERE customerNumber = ?";
                PreparedStatement stPaymentCustomersList = connection.prepareStatement(sqlPaymentCustomerList);
                stPaymentCustomersList.setInt(1, customerNumber);

                ResultSet rsPaymentsCustomerList = stPaymentCustomersList.executeQuery();

                Payment payment = null;
                Customer customer = null;

                while (rsPaymentsCustomerList.next()) {

                    payment = new Payment();
                    customer = new Customer();
                    customer.setCustomerNumber(rsPaymentsCustomerList.getInt("customerNumber"));

                    payment.setCustomer(customer);
                    payment.setCheckNumber(rsPaymentsCustomerList.getString("checkNumber"));
                    payment.setPaymentDate(rsPaymentsCustomerList.getDate("paymentDate"));
                    payment.setAmount(rsPaymentsCustomerList.getDouble("amount"));

                    paymentList.add(payment);
                }

            } catch (SQLException e) {
                throw new ClassicModelsException("No se ha podido mostrar la lista de pagos. Vuelva a intentarlo");
            }
        return paymentList;
    }

    /**
     * Método para comprobar si existe el empleado en la base de datos.
     * @param employeeNumber Número de empleado.
     * @return Si el empledado existe o no.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    private static boolean existEmployee(int employeeNumber) throws ClassicModelsException {

        Connection connection = ConnectionDB.getConnection();
        boolean existed = false;

        try {
            String sqlEmployee = "SELECT * FROM employees WHERE employeeNumber = ?";
            PreparedStatement stEmployee = connection.prepareStatement(sqlEmployee);
            stEmployee.setInt(1, employeeNumber);

            ResultSet existed2 = stEmployee.executeQuery();
            if (existed2.next()) {
                existed = true;
            }
        } catch (SQLException e) {
            throw new ClassicModelsException("No se ha posido realizar la consulta. vuelva a intentarlo.");
        }
        return existed;
    }

    /**
     * Método para comprobar si existe el cliente en la base de datos.
     * @param customerNumber Número de cliente.
     * @return Si el cliente existe o no.
     * @throws ClassicModelsException Al no poder realizar la operación.
     */
    private static boolean existCustomer(int customerNumber) throws ClassicModelsException {

        boolean existed = false;
        try {
            Customer existCustomer = getCustomer(customerNumber);
            existed=true;
        } catch (ClassicModelsException cme) {
            cme.printStackTrace();
        }
        return existed;
    }

}


