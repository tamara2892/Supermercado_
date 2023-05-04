package supermercado;

public class Cliente {

    private String nombres;
    private String cuil;
    private String telefono;
    private String email;

    public Cliente() {
    }

    public Cliente(String nombres, String cuil, String telefono, String email) {
        this.nombres = nombres;
        this.cuil = cuil;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente [nombres=" + nombres + ", cuil=" + cuil + ", telefono=" + telefono + ", email=" + email + "]";
    }

}
