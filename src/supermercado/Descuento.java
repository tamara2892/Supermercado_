package supermercado;

public abstract class Descuento {

    private double montoDesc;

    public double getMontoDesc() {
        return montoDesc;
    }

    public void setMontoDesc(double montoFijo) {
        this.montoDesc = montoFijo;
    }

    public void setMontoPorc(double montoPorc) {
        this.montoDesc = montoPorc;
    }

    public abstract double montoFinal(double montoInicial);

}
