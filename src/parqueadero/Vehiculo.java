
package parqueadero;

public class Vehiculo 
{
    //atributos propios y privados
    private String Placa,IdDue,Tipo, Marca, Color ;
    private int Modelo;
    private boolean Estado;//nos indica falso tiene averias y verdadero está bien

    public Vehiculo() {
    }

    public Vehiculo(String Placa, String IdDue, String Tipo, String Marca, String Color, int Modelo, boolean Estado) {
        this.Placa = Placa;
        this.IdDue = IdDue;
        this.Tipo = Tipo;
        this.Marca = Marca;
        this.Color = Color;
        this.Modelo = Modelo;
        this.Estado = Estado;
    }

  
    public Vehiculo IngresarDatos(String Pla,ListaDoble listaclientes)
    {
        //auxiliares para la lectura
         String Ti, Mar, Col,id;
         int Mod;
         boolean Est;
         id=Validaciones.ValidarCliente(listaclientes);//que exista el cliente en la lista
         Ti=Validaciones.LeerTipoVehiculo();
         Mar=Validaciones.LeerString("Marca del vehículo:");
         Col=Validaciones.LeerString("Color:");
         Mod=Validaciones.LeerInt("Modelo del Vehículo");
         
         Est=true;//luego se elabora metodo de cambiar estado
         
         Vehiculo objv=new Vehiculo(Pla,id,Ti,Mar,Col,Mod,Est);
         return objv;
    }//fin ingresar
    
    /*metodo para ayuda del archivo plano de texto, este metodo organiza
    la informacion separada por comas y la retorna para ser grabada
    en el archivo*/
     public String EstructuraReg() {
        return Placa + "," +IdDue + "," + Tipo + "," + Marca + "," + Color + "," + Modelo + "," + Estado;
    }

    @Override
    public String toString() {
        return "Placa=" + Placa + ", IdDue=" + IdDue + ", Tipo=" + Tipo + ", Marca=" + Marca + ", Color=" + Color + ", Modelo=" + Modelo + ", Estado=" + Estado;
    }

    
    
   

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getIdDue() {
        return IdDue;
    }

    public void setIdDue(String IdDue) {
        this.IdDue = IdDue;
    }

        public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getModelo() {
        return Modelo;
    }

    public void setModelo(int Modelo) {
        this.Modelo = Modelo;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    
    
    
}
