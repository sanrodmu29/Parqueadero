
package parqueadero;

public class Usuario 
{
   //atributos propios y protegidos
   protected String IdUser, Nombre, Apellido, Celular, Email, Direccion;

    public Usuario() {
    }

    public Usuario(String IdUser, String Nombre, String Apellido, String Celular, String Email, String Direccion) {
        this.IdUser = IdUser;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Celular = Celular;
        this.Email = Email;
        this.Direccion = Direccion;
    }

    @Override
    public String toString() {
        return "IdUser=" + IdUser + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Celular=" + Celular + ", Email=" + Email + ", Direccion=" + Direccion;
    }

    
    
    public void EntrarDatos(String idu)
    {
        IdUser=idu;
        Nombre=Validaciones.LeerString("Nombre: ");
        Apellido=Validaciones.LeerString("Apellido: ");
        Celular=Validaciones.LeerString("Número celular: ");
        Email=Validaciones.LeerString("Email: ");
        Direccion=Validaciones.LeerString("Dirección: ");
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String IdUser) {
        this.IdUser = IdUser;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
       
}
