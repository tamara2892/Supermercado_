package supermercado;

public class DescuentoProp extends Descuento {

    public double montoFinal(double montoInicial) {
        return montoInicial - (this.getMontoDesc() / 100 * montoInicial);
    }
}
