package Métodos;

import java.util.ArrayList;
import Modelo.Producto;

public class M_Producto {

    
    private static ArrayList<Producto> lista = new ArrayList<>();

    public M_Producto() {
      
    }

    // AGREGAR PRODUCTO
    public void agregarProducto(Producto p) {
        lista.add(p);
    }

    // ELIMINAR POR CÓDIGO
    public boolean eliminarProducto(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == codigo) {
                lista.remove(i);
                return true;
            }
        }
        return false;
    }

    // BUSCAR POR CÓDIGO
    public Producto buscarProducto(int cod) {
        for (Producto p : lista) {
            if (p.getCodigo() == cod) return p;
        }
        return null;
    }

    // LISTA COMPLETA
    public ArrayList<Producto> getProductos() {
        return lista;
    }

    // TAMAÑO
    public int tamañoP() {
        return lista.size();
    }

    // OBTENER POR ÍNDICE
    public Producto obtener(int index) {
        return lista.get(index);
    }
}
