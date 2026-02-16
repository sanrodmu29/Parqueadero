
package parqueadero;


public class Celda
{
    //atributos propios y privados
    private String IdCelda, TipoVehiculo;
    private boolean Estado; //true desocupada, false ocupada

    public Celda() {
    }

    public Celda(String IdCelda, String TipoVehiculo, boolean Estado) {
        this.IdCelda = IdCelda;
        this.TipoVehiculo = TipoVehiculo;
        this.Estado = Estado;
    }

    public Celda IngresarDatos(String idc)
    {
        //locales para la lectura
        String tv;
        tv=Validaciones.LeerTipoVehiculo();
        Celda objcel=new Celda(idc,tv,true);
        return objcel;
    }
    
    public String EstructuraReg()
    {
         return IdCelda + "," + TipoVehiculo + "," + Estado;
    }
        
    @Override
    public String toString() {
        return "IdCelda=" + IdCelda + ", TipoVehiculo=" + TipoVehiculo + ", Estado=" + Estado;
    }

    public String getIdCelda() {
        return IdCelda;
    }

    public void setIdCelda(String IdCelda) {
        this.IdCelda = IdCelda;
    }

    public String getTipoVehiculo() {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(String TipoVehiculo) {
        this.TipoVehiculo = TipoVehiculo;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    
            
    
}
