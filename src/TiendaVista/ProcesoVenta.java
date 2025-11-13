package TiendaVista;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Producto;
import Modelo.Venta;
import Métodos.M_Producto;
import Métodos.M_Venta;
import Métodos.Exportar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcesoVenta extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    private JTextField txtCodigo, txtNombre, txtPrecio, txtStock, txtCantidad, txtDescuento;
    private JTable table;
    private DefaultTableModel modeloTabla;

    private JButton btnBuscar, btnAgregarVenta, btnFinalizarVenta, btnExportarVentas;

    private M_Venta ventaManager = new M_Venta();
    private M_Producto productoManager = new M_Producto();

    public ProcesoVenta() {
        setTitle("Proceso de Venta");
        setBounds(100, 100, 600, 620);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblTitulo = new JLabel("PROCESO DE VENTA");
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitulo.setBounds(190, 10, 250, 30);
        contentPanel.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(25, 54, 60, 13);
        contentPanel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(82, 51, 96, 19);
        contentPanel.add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        btnBuscar.setBounds(210, 50, 85, 21);
        contentPanel.add(btnBuscar);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(25, 92, 60, 13);
        contentPanel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(82, 89, 130, 19);
        txtNombre.setEditable(false);
        contentPanel.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(25, 131, 45, 13);
        contentPanel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(82, 128, 96, 19);
        txtPrecio.setEditable(false);
        contentPanel.add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(25, 168, 45, 13);
        contentPanel.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(82, 165, 96, 19);
        txtStock.setEditable(false);
        contentPanel.add(txtStock);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(25, 202, 70, 13);
        contentPanel.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(102, 199, 76, 19);
        contentPanel.add(txtCantidad);

        JLabel lblDescuento = new JLabel("Descuento:");
        lblDescuento.setBounds(25, 238, 71, 13);
        contentPanel.add(lblDescuento);

        txtDescuento = new JTextField();
        txtDescuento.setBounds(102, 235, 76, 19);
        contentPanel.add(txtDescuento);

        btnAgregarVenta = new JButton("Agregar Venta");
        btnAgregarVenta.addActionListener(this);
        btnAgregarVenta.setBounds(210, 276, 143, 30);
        contentPanel.add(btnAgregarVenta);

        btnExportarVentas = new JButton("Exportar Ventas");
        btnExportarVentas.addActionListener(this);
        btnExportarVentas.setBounds(370, 276, 143, 30);
        contentPanel.add(btnExportarVentas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 327, 530, 130);
        contentPanel.add(scrollPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Descuento");
        modeloTabla.addColumn("Subtotal");

        table = new JTable(modeloTabla);
        scrollPane.setViewportView(table);

        btnFinalizarVenta = new JButton("Finalizar Venta");
        btnFinalizarVenta.addActionListener(this);
        btnFinalizarVenta.setBounds(210, 478, 143, 30);
        contentPanel.add(btnFinalizarVenta);
    }

    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            buscarProducto();
        } else if (e.getSource() == btnAgregarVenta) {
            agregarVenta();
        } else if (e.getSource() == btnFinalizarVenta) {
            finalizarVenta();
        } else if (e.getSource() == btnExportarVentas) {
            Exportar.exportarVentas(ventaManager.getVentas());
        }
    }

    private void buscarProducto() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            Producto producto = productoManager.buscarProducto(codigo);

            if (producto != null) {
                txtNombre.setText(producto.getNombre());
                txtPrecio.setText(String.valueOf(producto.getPrecio()));
                txtStock.setText(String.valueOf(producto.getStock()));
                JOptionPane.showMessageDialog(this, "Producto encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código inválido.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarVenta() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            double descuento = Double.parseDouble(txtDescuento.getText().trim());

            Producto producto = productoManager.buscarProducto(codigo);

            if (producto == null) {
                JOptionPane.showMessageDialog(this, "Primero busque un producto válido.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidad > producto.getStock()) {
                JOptionPane.showMessageDialog(this, "Cantidad superior al stock.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Venta venta = new Venta(cantidad, producto, descuento);
            ventaManager.agregarVenta(venta);

            modeloTabla.addRow(new Object[] {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    cantidad,
                    descuento,
                    venta.calcularSubtotal()
            });

          
            producto.setStock(producto.getStock() - cantidad);
            txtStock.setText(String.valueOf(producto.getStock()));

            JOptionPane.showMessageDialog(this, "Venta agregada.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos para cantidad y descuento.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarVenta() {
        double total = ventaManager.calcularTotal();
        JOptionPane.showMessageDialog(this, "Venta finalizada.\nTotal: S/ " + total);

        ventaManager.limpiarVentas();
        modeloTabla.setRowCount(0);
    }
}

