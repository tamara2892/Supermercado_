package supermercado;

import java.util.List;

public class Carrito {

    private int id;
    private Cliente cliente;
    private List<ItemCarrito> items;
    private double montoTotal;
    private double montoTotalconDescuento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoTotalconDescuento() {
        return montoTotalconDescuento;
    }

    public void setMontoTotalconDescuento(double montoTotalconDescuento) {
        this.montoTotalconDescuento = montoTotalconDescuento;
    }

    @Override
    public String toString() {
        return "Monto Total = " + montoTotal + "\nMonto Final con Descuento = "
                + montoTotalconDescuento;
    }

}
