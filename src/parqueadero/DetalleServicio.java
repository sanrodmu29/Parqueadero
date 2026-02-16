
package parqueadero;

import java.time.LocalDate;


public class DetalleServicio 
{
  //atributos propios y privados
   private String servicioId, Placa, IdCelda, IdServicio, horaInicio, horaFin;   //servicioId es la identidad de la clase DetalleServicio y IdServicio es el servicio prestado
   private LocalDate FechaServicio;

    public DetalleServicio() {
    }

    public DetalleServicio(String servicioId, String Placa, String IdCelda, String IdServicio, String horaInicio, String horaFin, LocalDate FechaServicio) {
        this.servicioId = servicioId;
        this.Placa = Placa;
        this.IdCelda = IdCelda;
        this.IdServicio = IdServicio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.FechaServicio = FechaServicio;
    }

   
    public DetalleServicio IngresarDatos(String id, ListaSimple LSVehi, Cola objc,Pila pser)
     {
        String Pla,ids, horaI, horaF, IdCel;
        LocalDate FechaSer;
        Pla=Validaciones.ValidarVehiculo(LSVehi);//el vehiculo TIENE que estar en la lista simple
        IdCel=Validaciones.ValidarCelda(objc);//la celda tiene que estar en Cola y desocupada
        ids=Validaciones.ValidarIdServicio(pser);//el servicio tiene que estar en la pila
        horaI=Validaciones.LeerHora("Hora de ingreso al servicio ");
        horaF=Validaciones.LeerHora("Hora de salida del servicio ");
        FechaSer=Validaciones.FechaActual();//se toma la fecha del sistema para el servicio
        
        DetalleServicio objDS=new DetalleServicio(id,Pla,IdCel,ids,horaI,horaF,FechaSer);
        return objDS;
     }
     
     
     public String EstructuraReg() {
        return servicioId + "," + Placa + "," + IdCelda + "," + IdServicio + "," + horaInicio + "," + horaFin + "," + FechaServicio;
    }

    @Override
    public String toString() {
        return "codifo del detalle de servicio=" + servicioId + ", Placa=" + Placa + ", Id Celda=" + IdCelda + ", Id Servicio=" + IdServicio + ", hora Inicio=" + horaInicio + ", hora Fin=" + horaFin + ", Fecha Servicio=" + FechaServicio;
    }
    
   

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getIdCelda() {
        return IdCelda;
    }

    public void setIdCelda(String IdCelda) {
        this.IdCelda = IdCelda;
    }

    public String getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(String IdServicio) {
        this.IdServicio = IdServicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDate getFechaServicio() {
        return FechaServicio;
    }

    public void setFechaServicio(LocalDate FechaServicio) {
        this.FechaServicio = FechaServicio;
    }

}
