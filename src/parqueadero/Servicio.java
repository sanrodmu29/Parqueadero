
package parqueadero;


public class Servicio 
{
    //atributos propios y privados
    private String IdServicio, DescripcionServicio;
    private double PrecioServicio;

    public Servicio() {
    }

    public Servicio(String IdServicio, String DescripcionServicio, double PrecioServicio) 
    {
        this.IdServicio = IdServicio;
        this.DescripcionServicio = DescripcionServicio;
        this.PrecioServicio = PrecioServicio;
    }

     public String EstructuraReg() {
        return IdServicio + "," + DescripcionServicio + "," + PrecioServicio;
    }
    
    public Servicio IngresarDatos(String id)
    {
        String des;
        double pr;
        des=Validaciones.LeerServicio();
        pr=Validaciones.LeerDouble("Ingrese precio del servicio: ");
        Servicio objser=new Servicio(id,des,pr);
        return objser;
    }
    
    
    @Override
    public String toString() {
        return "IdServicio=" + IdServicio + ", DescripcionServicio=" + DescripcionServicio + ", PrecioServicio=" + PrecioServicio;
    }

    public String getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(String IdServicio) {
        this.IdServicio = IdServicio;
    }

    public String getDescripcionServicio() {
        return DescripcionServicio;
    }

    public void setDescripcionServicio(String DescripcionServicio) {
        this.DescripcionServicio = DescripcionServicio;
    }

    public double getPrecioServicio() {
        return PrecioServicio;
    }

    public void setPrecioServicio(double PrecioServicio) {
        this.PrecioServicio = PrecioServicio;
    }
   
    
    
}
