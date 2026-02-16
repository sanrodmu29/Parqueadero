
package parqueadero;

import java.time.LocalDate;


public class Convenio 
{
    //atributos propios y privados
    private String idConvenio, idCliente, DescripcionConvenio;
    private LocalDate FechaInicio, FechaFinalizacion;
    private double PrecioConvenio;

    public Convenio() {
    }

    public Convenio(String idConvenio, String idCliente, String DescripcionConvenio, LocalDate FechaInicio, LocalDate FechaFinalizacion, double PrecioConvenio) {
        this.idConvenio = idConvenio;
        this.idCliente = idCliente;
        this.DescripcionConvenio = DescripcionConvenio;
        this.FechaInicio = FechaInicio;
        this.FechaFinalizacion = FechaFinalizacion;
        this.PrecioConvenio = PrecioConvenio;
    }

    public Convenio IngresarDatos(String id, ListaDoble LDclientes)
    {
        String idCli, Desc;
        LocalDate FechaI, FechaF;
        double Precio;
        idCli=Validaciones.ValidarCliente(LDclientes);//el cliente del convenio TIENE que existir en la lista doble de clientes
        Desc=Validaciones.LeerString("Corta Descripción del convenio:");
        FechaI=Validaciones.leerFecha("Fecha de inicio del convenio:");
        FechaF=Validaciones.leerFecha("Fecha final del convenio:");
        Precio=Validaciones.LeerDouble("Precio del convenio:");
        Convenio objcon=new Convenio(id,idCli,Desc,FechaI,FechaF,Precio);
        return objcon;
        
    }
    
    
    public String EstructuraReg() {
        return idConvenio + "," + idCliente + "," + DescripcionConvenio + "," + FechaInicio + "," + FechaFinalizacion + "," + PrecioConvenio;
    }
    
    @Override
    public String toString() {
        return "id Convenio=" + idConvenio + ", id Cliente=" + idCliente + ", Descripcion Convenio=" + DescripcionConvenio + ", Fecha de Inicio=" + FechaInicio + ", Fecha de Finalizacion=" + FechaFinalizacion + ", Precio Convenio=" + PrecioConvenio;
    }

    public String getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(String idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescripcionConvenio() {
        return DescripcionConvenio;
    }

    public void setDescripcionConvenio(String DescripcionConvenio) {
        this.DescripcionConvenio = DescripcionConvenio;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return FechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate FechaFinalizacion) {
        this.FechaFinalizacion = FechaFinalizacion;
    }

    public double getPrecioConvenio() {
        return PrecioConvenio;
    }

    public void setPrecioConvenio(double PrecioConvenio) {
        this.PrecioConvenio = PrecioConvenio;
    }
    
    
    
    
}
