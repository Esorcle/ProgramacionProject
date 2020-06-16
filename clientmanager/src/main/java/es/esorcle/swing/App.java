package es.esorcle.swing;

import es.esorcle.swing.db.ConnectionDB;
import es.esorcle.swing.db.ConnectionData;
import es.esorcle.swing.db.dao.ADClassicModels;
import es.esorcle.swing.db.dao.ClassicModelsException;
import es.esorcle.swing.db.dao.types.Customer;
import es.esorcle.swing.db.dao.types.Payment;
import es.esorcle.swing.utils.ActionButton;
import es.esorcle.swing.utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class App extends JFrame {

    /**
     * Declaración de variables de la aplicación principal
     */
    private JButton exitButton;
    private JTextField jtUser;
    private JPasswordField jtPass;
    private JTextField jtDataBase;
    private JButton connectButton;
    private JTextField jtServer;
    private JTextField jtPort;
    private JPanel jPanelMain;
    private JPanel menuPanel;
    private JPanel connectionPanel;
    private JButton newCustomerButton;
    private JButton searchButton;
    private JPanel customerPanel;
    private JTextField jIdCustomer;
    private JTextField jNameCustomer;
    private JTextField jContactFirst;
    private JTextField jContactFLast;
    private JTextField jAddress;
    private JTextField jAddress2;
    private JTextField jPhone;
    private JTextField jCity;
    private JTextField jState;
    private JTextField jPostalCode;
    private JTextField jCountry;
    private JTextField jCredit;
    private JTextField jSalesEmployee;
    private JButton saveCustomerButton;
    private JTextField jSearch;
    private JPanel jSearchTablePanel;
    private JButton editButton;
    private JButton removeButton;
    private JButton addSalesButton;
    private JButton showButton;
    private JButton backButton;
    private JButton payButton;
    private JPanel payPanel;
    private JTextField jPayCustomerName;
    private JTextField jTotalPayments;
    private JPanel jPayTablePanel;
    private ConnectionData connectionData;
    private JTable jSearchTable;
    private JTable jPayTable;
    private ActionButton actionButton;


    /**
     * Constructor de la clase App
     */
    public App() {

        //Configuraciones iniciales
        super("Gestión de clientes");
        setContentPane(jPanelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        setVisible(true);
        setSize(500, 400);
        setLocationRelativeTo(null);

        //Oculto laos demás paneles
        menuPanel.setVisible(false);
        customerPanel.setVisible(false);
        payPanel.setVisible(false);
        backButton.setVisible(false);

        Border originalBorder = jtUser.getBorder();
        String selectCustomer = "Seleccione un cliente";

        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                connectButtonListener(originalBorder);
            }
        });

        //Se crean las tablas a usar
        DefaultTableModel searchTable = getTableModelSearch();
        DefaultTableModel payTable = getTableModelPayments();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                searchButtonListener(searchTable);
            }
        });

        newCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newCustomerButtonListener();
            }
        });

        saveCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean notError = validateFieldCustomer(originalBorder);

                if (notError) {
                    Customer customer = getCustomerData();

                    try {
                        if (ActionButton.NEW_CUSTOMER.equals(actionButton)) {
                            if (ADClassicModels.insertCustomer(customer)) {
                                JOptionPane.showMessageDialog(null, "Cliente guardado");
                            }
                        }
                        if (ActionButton.EDIT.equals(actionButton)) {
                            if (ADClassicModels.updateCustomer(customer)) {
                                JOptionPane.showMessageDialog(null, "CLiente actualizado");
                            }
                        }
                    } catch (ClassicModelsException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actionButton = ActionButton.EDIT;
                showCustomer(true);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jSearchTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, selectCustomer);
                } else {
                    int row = jSearchTable.getSelectedRow();
                    int customerNumber = (int) jSearchTable.getValueAt(row, 0);

                    int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere eliminar el cliente?", "¡Atención!", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        try {
                            ADClassicModels.removeCustomer(customerNumber);
                            searchButtonListener(searchTable);
                        } catch (ClassicModelsException cme) {
                            JOptionPane.showMessageDialog(null, cme.getMessage());
                        }

                    }
                }
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payButtonListener(payTable);
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actionButton = ActionButton.SHOW;
                showCustomer(false);
            }
        });

        addSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jSearchTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, selectCustomer);
                } else {
                    int row = jSearchTable.getSelectedRow();
                    int customerNumber = (int) jSearchTable.getValueAt(row, 0);

                    String inputDialog = JOptionPane.showInputDialog("Inserte número de vendedor");
                    int salesId = -1;
                    if (!Utils.isEmptyString(inputDialog)) {
                        if (Utils.isDouble(inputDialog)) {
                            salesId = Integer.valueOf(inputDialog);
                            try {
                                boolean add = false;
                                add = ADClassicModels.assingnSales(salesId, customerNumber);
                                if (add) {
                                    JOptionPane.showMessageDialog(null, "¡Vendedor asignado!");
                                }

                            } catch (ClassicModelsException cme) {
                                JOptionPane.showMessageDialog(null, cme.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe introducir un número");
                        }
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menuPanel.setVisible(true);
                customerPanel.setVisible(false);
                payPanel.setVisible(false);
                backButton.setVisible(false);
                actionButton = ActionButton.MENU;

            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectionDB.close();
                System.exit(0);
            }
        });
    }

    /**
     * Método que recoge y valida los datos introducidos del cliente.
     *
     * @return un Customer.
     */
    private Customer getCustomerData() {
        Customer customer = new Customer(Integer.valueOf(jIdCustomer.getText()), jNameCustomer.getText(),
                jContactFLast.getText(), jContactFirst.getText(), jPhone.getText(), jAddress.getText(),
                jCity.getText(), jCountry.getText());

        if (Utils.isInteger(jSalesEmployee.getText())) {
            customer.setSalesRepEmployeeNumber(Integer.valueOf(jSalesEmployee.getText()));
        }
        if (Utils.isFloat(jCredit.getText())) {
            customer.setCreditLimit(Float.valueOf(jCredit.getText()));
        }
        if (Utils.isEmptyString(jAddress2.getText())) {
            customer.setAddressLine2(null);
        } else {
            customer.setAddressLine2(jAddress2.getText());
        }
        if (Utils.isEmptyString(jState.getText())) {
            customer.setState(null);
        } else {
            customer.setState(jState.getText());
        }
        if (Utils.isEmptyString(jPostalCode.getText())) {
            customer.setPostalCode(null);
        } else {
            customer.setPostalCode(jPostalCode.getText());
        }
        return customer;
    }

    /**
     * Método para el evento del payButton.
     * Muestra los pagos del cliente y su total.
     *
     * @param payTable La tabla donde se volvará los datos recibidos de la base de datos.
     */
    private void payButtonListener(DefaultTableModel payTable) {

        if (jSearchTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");

        } else {
            int row = jSearchTable.getSelectedRow();
            int customerNumber = (int) jSearchTable.getValueAt(row, 0);

            menuPanel.setVisible(false);
            payPanel.setVisible(true);
            backButton.setVisible(true);
            double total = 0;

            try {
                List<Payment> paymentList = ADClassicModels.getPaymentsList(customerNumber);

                payTable.setRowCount(0);

                for (Payment payment : paymentList) {
                    payTable.addRow(new Object[]{
                            payment.getCheckNumber(), payment.getPaymentDate(), " " + payment.getAmount()
                    });
                    total += payment.getAmount();
                }
            } catch (ClassicModelsException cme) {
                JOptionPane.showMessageDialog(null, cme.getMessage());
                System.out.println(cme.getMessage());
            }
            jPayCustomerName.setText(String.valueOf(customerNumber));
            jTotalPayments.setText(String.valueOf(total));
        }
    }

    /**
     * Método para el evento del botón mostrar cliente y editar cliente.
     * Muestra los datos del cliente selecciónado.
     *
     * @param editable indica si los datos pueden ser modificados según el botón pulsado.
     */
    private void showCustomer(boolean editable) {

        if (jSearchTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");

        } else {
            int row = jSearchTable.getSelectedRow();
            int customerNumber = (int) jSearchTable.getValueAt(row, 0);

            try {
                Customer customer = ADClassicModels.getCustomer(customerNumber);

                customerPanel.setVisible(true);
                menuPanel.setVisible(false);
                backButton.setVisible(true);
                saveCustomerButton.setVisible(editable);

                jIdCustomer.setEditable(false);
                jNameCustomer.setEditable(editable);
                jContactFLast.setEditable(editable);
                jContactFirst.setEditable(editable);
                jPhone.setEditable(editable);
                jAddress.setEditable(editable);
                jAddress2.setEditable(editable);
                jCity.setEditable(editable);
                jState.setEditable(editable);
                jPostalCode.setEditable(editable);
                jCountry.setEditable(editable);
                jSalesEmployee.setEditable(editable);
                jCredit.setEditable(editable);

                jIdCustomer.setText("" + customer.getCustomerNumber());
                jNameCustomer.setText(customer.getCustomerName());
                jContactFLast.setText(customer.getContactLastName());
                jContactFirst.setText(customer.getContactFirstName());
                jPhone.setText(customer.getPhone());
                jAddress.setText(customer.getAddressLine1());
                jAddress2.setText(customer.getAddressLine2());
                jCity.setText(customer.getCity());
                jState.setText(customer.getState());
                jPostalCode.setText(customer.getPostalCode());
                jCountry.setText(customer.getCountry());
                jSalesEmployee.setText(customer.getSalesRepEmployeeNumber() != null ? "" + customer.getSalesRepEmployeeNumber() : "");
                jCredit.setText(customer.getCreditLimit() != null ? "" + customer.getCreditLimit() : "");

            } catch (ClassicModelsException cme) {
                JOptionPane.showMessageDialog(null, cme.getMessage());
            }
        }
    }

    /**
     * Método para ingresar un nuevo cliente.
     * Resetea la página.
     */
    private void newCustomerButtonListener() {

        menuPanel.setVisible(false);
        customerPanel.setVisible(true);
        backButton.setVisible(true);
        setSize(800, 500);
        setLocationRelativeTo(null);
        actionButton = ActionButton.NEW_CUSTOMER;

        jIdCustomer.setEditable(true);
        jNameCustomer.setEditable(true);
        jContactFLast.setEditable(true);
        jContactFirst.setEditable(true);
        jPhone.setEditable(true);
        jAddress.setEditable(true);
        jAddress2.setEditable(true);
        jCity.setEditable(true);
        jState.setEditable(true);
        jPostalCode.setEditable(true);
        jCountry.setEditable(true);
        jSalesEmployee.setEditable(true);
        jCredit.setEditable(true);

        jIdCustomer.setText(null);
        jNameCustomer.setText(null);
        jContactFLast.setText(null);
        jContactFirst.setText(null);
        jPhone.setText(null);
        jAddress.setText(null);
        jAddress2.setText(null);
        jCity.setText(null);
        jState.setText(null);
        jPostalCode.setText(null);
        jCountry.setText(null);
        jSalesEmployee.setText(null);
        jCredit.setText(null);
    }

    /**
     * Método para el evento del searchButton.
     * Realiza una búsqueda en la base de datos donde coincida cualquier campo con el valor buscado.
     *
     * @param tableModel La tabla donde se volvará los datos devueltos por la base de datos.
     */
    private void searchButtonListener(DefaultTableModel tableModel) {
        try {
            List<Customer> customersList = ADClassicModels.getCustomersList(jSearch.getText());

            tableModel.setRowCount(0);

            for (Customer customer : customersList) {
                tableModel.addRow(new Object[]{
                        customer.getCustomerNumber(), customer.getCustomerName(), customer.getContactFirstName() +
                        " " + customer.getContactLastName(), customer.getPhone(), customer.getCity(), customer.getCountry(),
                        customer.getSalesRepEmployeeNumber(), customer.getCreditLimit()
                });
            }
        } catch (ClassicModelsException cme) {
            JOptionPane.showMessageDialog(null, cme.getMessage());
            System.out.println(cme.getMessage());
        }
    }

    /**
     * Método para crear la tabla que recogerá la lista de clientes.
     *
     * @return La tabla creada con los campos correspondientes.
     */
    private DefaultTableModel getTableModelSearch() {

        DefaultTableModel tableModel = new DefaultTableModel();
        jSearchTable = new JTable(tableModel);

        //DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Persona de contacto");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Ciudad");
        tableModel.addColumn("País");
        tableModel.addColumn("Empleado");
        tableModel.addColumn("Crédito");

        tableModel.addRow(new Object[]{
                "No hay resultados", "", ""
        });

        JScrollPane jScrollPanel = new JScrollPane(jSearchTable);
        jSearchTablePanel.setLayout(new BoxLayout(jSearchTablePanel, BoxLayout.PAGE_AXIS));
        jSearchTablePanel.add(jScrollPanel);
        return tableModel;
    }

    /**
     * Nétodo para crear la tabla que recogerá la lista de pagos del cliente seleccinado.
     *
     * @return la tabla creada con los campos correspondientes.
     */
    private DefaultTableModel getTableModelPayments() {

        DefaultTableModel tableModel = new DefaultTableModel();
        jPayTable = new JTable(tableModel);

        tableModel.addColumn("Número de pedido");
        tableModel.addColumn("Fecha pago");
        tableModel.addColumn("Cantidad");

        JScrollPane jScrollPanel = new JScrollPane(jPayTable);
        jPayTablePanel.setLayout(new BoxLayout(jPayTablePanel, BoxLayout.PAGE_AXIS));
        jPayTablePanel.add(jScrollPanel);
        return tableModel;
    }

    /**
     * Método para la acción del connectButton.
     * Estabalece la conexión a una base de datos.
     *
     * @param
     */
    private void connectButtonListener(Border originalBorder) {

        boolean notError = validateFieldConnection(originalBorder);

        if (notError) {
            connectionData = new ConnectionData(jtServer.getText().toLowerCase(), jtPort.getText(), jtDataBase.getText().toLowerCase(), jtUser.getText(), jtPass.getPassword());

            if (ConnectionDB.getConnection(connectionData) == null) {
                JOptionPane.showMessageDialog(null, "Error de conexión");
                connectButton.setText("Conectar");
            } else {
                connectionPanel.setVisible(false);
                menuPanel.setVisible(true);
                setSize(900, 600);
                setLocationRelativeTo(null);
            }
        }
    }

    /**
     * Método para validar los datos introducidos del cliente según su tipo.
     *
     * @param originalBorder Color original de los tField.
     * @return Si son correctos los datos o no.
     */
    private boolean validateFieldCustomer(Border originalBorder) {
        //Comprobación de datos introducidos para la conexión
        boolean notError = true;
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 1);

        if (Utils.isInteger(jIdCustomer.getText())) {
            jIdCustomer.setBorder(originalBorder);
        } else {
            jIdCustomer.setBorder(redBorder);
            notError = false;
        }
        if (Utils.isEmptyString(jNameCustomer.getText())) {
            jNameCustomer.setBorder(redBorder);
            notError = false;
        } else {
            jNameCustomer.setBorder(originalBorder);
        }
        if (Utils.isEmptyString(jContactFirst.getText())) {
            jContactFirst.setBorder(redBorder);
            notError = false;
        } else {
            jContactFirst.setBorder(originalBorder);
        }
        if (Utils.isEmptyString(jContactFLast.getText())) {
            jContactFLast.setBorder(redBorder);
            notError = false;
        } else {
            jContactFLast.setBorder(originalBorder);
        }
        if (Utils.isEmptyString(jAddress.getText())) {
            jAddress.setBorder(redBorder);
            notError = false;
        } else {
            jAddress.setBorder(originalBorder);
        }
        if (Utils.isEmptyString(jPhone.getText())) {
            jPhone.setBorder(redBorder);
            notError = false;
        } else {
            jPhone.setBorder(originalBorder);
        }
        if (Utils.isEmptyString(jCity.getText())) {
            jCity.setBorder(redBorder);
            notError = false;
        } else {
            jCity.setBorder(originalBorder);
        }
        if (Utils.isEmptyString(jCountry.getText())) {
            jCountry.setBorder(redBorder);
            notError = false;
        } else {
            jCountry.setBorder(originalBorder);
        }
        if (Utils.isInteger(jSalesEmployee.getText()) || Utils.isEmptyString(jSalesEmployee.getText())) {
            jSalesEmployee.setBorder(originalBorder);
        } else {
            jSalesEmployee.setBorder(redBorder);
            notError = false;
        }
        if (Utils.isFloat(jCredit.getText()) || Utils.isEmptyString(jCredit.getText())) {
            jCredit.setBorder(originalBorder);
        } else {
            jCredit.setBorder(redBorder);
            notError = false;
        }

        return notError;
    }

    /**
     * Método para validar los datos de conexión a la base de datos según su tipo.
     *
     * @param originalBorder color original de los tField.
     * @return Si son correctos los datos o no.
     */
    private boolean validateFieldConnection(Border originalBorder) {
        //Comprobación de datos introducidos para la conexión
        boolean notError = true;
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 1);

        if ("".equals(jtServer.getText()) || jtServer.getText() == null) {
            jtServer.setBorder(redBorder);
            notError = false;
        } else {
            jtServer.setBorder(originalBorder);
        }
        if ("".equals(jtPort.getText()) || jtPort.getText() == null) {
            jtPort.setBorder(redBorder);
            notError = false;
        } else {
            jtPort.setBorder(originalBorder);
        }
        if ("".equals(jtDataBase.getText()) || jtDataBase.getText() == null) {
            jtDataBase.setBorder(redBorder);
            notError = false;
        } else {
            jtDataBase.setBorder(originalBorder);
        }
        if ("".equals(jtUser.getText()) || jtUser.getText() == null) {
            jtUser.setBorder(redBorder);
            notError = false;
        } else {
            jtUser.setBorder(originalBorder);
        }
        if (jtPass.getPassword().length < 1) {
            jtPass.setBorder(redBorder);
            notError = false;
        } else {
            jtPass.setBorder(originalBorder);
        }
        return notError;
    }

}
