
package parqueadero;


public class Empleado extends Usuario
{
     //atributos propios y privados
    private String Cargo;
    private String IdTurnoLaboral;

    //constructores
    public Empleado() {
    }

    public Empleado(String IdUser, String Nombre, String Apellido, String Celular, String Email, String Direccion, String Cargo, String IdTurnoLaboral) {
        super(IdUser, Nombre, Apellido, Celular, Email, Direccion);
        this.Cargo = Cargo;
        this.IdTurnoLaboral = IdTurnoLaboral;
    }
    
    public Empleado IngresarDatos(String id, Pila p1){
         //variables aux
         String carg, idt;
         
         EntrarDatos(id);//se ingresan los datos de la herencia
         carg=Validaciones.LeerCargo();
         idt=Validaciones.ValidarTurno(p1);
        
         Empleado objEm=new Empleado(id,Nombre,Apellido,Celular,Email,Direccion,carg,idt);
         return objEm;
     
     }//fin ingresar datos

    @Override
    public String toString() {
        return super.toString() + " Cargo=" + Cargo + ", IdTurnoLaboral=" + IdTurnoLaboral;
    }

    //para el archivo
    public String EstructuraReg()
    {
        return IdUser+ "," + Nombre + "," + Apellido + "," + Celular + "," + Email+"," + Direccion+ "," +Cargo+","+IdTurnoLaboral;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getIdTurnoLaboral() {
        return IdTurnoLaboral;
    }

    public void setIdTurnoLaboral(String IdTurnoLaboral) {
        this.IdTurnoLaboral = IdTurnoLaboral;
    }
    
    
}
