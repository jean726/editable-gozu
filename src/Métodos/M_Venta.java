package Métodos;

import java.util.ArrayList;

import Modelo.Producto;
import Modelo.Venta;

public class M_Venta {

    private ArrayList<Venta> listaVentas;

    public M_Venta() {

    	listaVentas = new ArrayList<>();
    }

    
    public void agregarVenta(Venta venta) {
        listaVentas.add(venta);
    }

    
    public boolean eliminarVenta(String nombreProducto) {
        for (int i = 0; i < listaVentas.size(); i++) {
            Venta v = listaVentas.get(i);
            if (v.getProducto().getNombre().equalsIgnoreCase(nombreProducto)) {
                listaVentas.remove(i);
                return true;
            }
        }
        return false;
    }

   
    public void limpiarVentas() {
        listaVentas.clear();
    }

  
    public ArrayList<Venta> getVentas() {
        return listaVentas;
    }

    
    public double calcularTotal() {
        double total = 0;
        for (Venta v : listaVentas) {
            total += v.calcularSubtotal();
        }
        return total;
    }
}
