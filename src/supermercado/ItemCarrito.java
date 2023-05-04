package supermercado;

public class ItemCarrito {

    private Item item;
    private int cantidad;

    public ItemCarrito(Item item, int cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return item.getNombre() + "\t" + item.getPrecio() + "\t" + cantidad + "\n";
    }

}
