
package parqueadero;


public class Cliente extends Usuario
{
   
    public Cliente() {
    }

    public Cliente(String IdUser, String Nombre, String Apellido, String Celular, String Email, String Direccion) {
        super(IdUser, Nombre, Apellido, Celular, Email, Direccion);
    }

    
    public Cliente IngresarDatos(String id)
    {
        //ingresar de la super clase
        EntrarDatos(id);
        Cliente objc=new Cliente(id,Nombre,Apellido,Celular,Email,Direccion);
        return objc;
    }
    
    public String EstructuraReg()
    {
        return IdUser+","+Nombre+","+Apellido+","+Celular+","+Email+","+Direccion;
    }
    
    @Override
    public String toString() {
        return  super.toString();
    }
   
    
}
