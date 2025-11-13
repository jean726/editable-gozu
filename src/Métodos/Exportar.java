package Métodos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JOptionPane;

import Modelo.Producto;
import Modelo.Venta;

public class Exportar {

   
    private static final String CARPETA_EXPORTACIONES = "exportaciones";
    private static final String SEPARADOR = ";";

    
    private static String obtenerRutaArchivo(String nombreArchivo) {
        File carpeta = new File(CARPETA_EXPORTACIONES);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        return CARPETA_EXPORTACIONES + "/" + nombreArchivo;
    }

   
    public static void exportarProductos(List<Producto> productos) {

        if (productos == null || productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay productos para exportar.");
            return;
        }

        String rutaArchivo = obtenerRutaArchivo("productos.csv");

        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {

            
            pw.println("Código" + SEPARADOR + "Nombre" + SEPARADOR + "Precio" 
                       + SEPARADOR + "Stock" + SEPARADOR + "Categoría");

           
            for (Producto p : productos) {
                pw.println(
                        p.getCodigo() + SEPARADOR +
                        p.getNombre() + SEPARADOR +
                        p.getPrecio() + SEPARADOR +
                        p.getStock() + SEPARADOR +
                        p.getCategoria()
                );
            }

            JOptionPane.showMessageDialog(null,
                    "✅ Productos exportados correctamente en:\n" + rutaArchivo);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Error al exportar productos:\n" + e.getMessage());
        }
    }

    
    public static void exportarVentas(List<Venta> ventas) {

        if (ventas == null || ventas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay ventas para exportar.");
            return;
        }

        String rutaArchivo = obtenerRutaArchivo("ventas.csv");

        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {

            
            pw.println("Producto" + SEPARADOR + "Cantidad" + SEPARADOR + "Precio" 
                       + SEPARADOR + "Descuento" + SEPARADOR + "Subtotal");

           
            for (Venta v : ventas) {

                pw.println(
                        v.getProducto().getNombre() + SEPARADOR +
                        v.getCantidad() + SEPARADOR +
                        v.getProducto().getPrecio() + SEPARADOR +
                        v.getDescuento() + SEPARADOR +
                        v.calcularSubtotal()
                );
            }

            JOptionPane.showMessageDialog(null,
                    "✅ Ventas exportadas correctamente en:\n" + rutaArchivo);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Error al exportar ventas:\n" + e.getMessage());
        }
    }
}

