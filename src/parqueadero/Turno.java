
package parqueadero;



public class Turno 
{
    //atributos propios y privados
    private String IdTurno;
    private String DescripcionTurno;
    private String horaInicio,horaFin;//hora militar 00-24

    public Turno() {
    }

    public Turno(String IdTurno, String DescripcionTurno, String horaInicio, String horaFin) {
        this.IdTurno = IdTurno;
        this.DescripcionTurno = DescripcionTurno;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    

    public Turno IngresarDatos(String id)
    {
        //variables auxiliares
        String des,hi,hf;
        
        des=Validaciones.LeerTurno();
        hi=Validaciones.LeerHora("Hora de inicio del turno hh");
        hf=Validaciones.LeerHora("Hora de fin del turno hh");
        Turno objt=new Turno(id,des,hi,hf);
        return objt;
    }
    
    
    public String EstructuraReg()
    {
      return IdTurno + "," + DescripcionTurno + "," + horaInicio + "," + horaFin;  
    }

    @Override
    public String toString() {
        return "Id Turno=" + IdTurno + ", Descripcion Turno=" + DescripcionTurno + ", hora Inicio=" + horaInicio + ", hora Final=" + horaFin;
    }
       

    public String getIdTurno() {
        return IdTurno;
    }

    public void setIdTurno(String IdTurno) {
        this.IdTurno = IdTurno;
    }

    public String getDescripcionTurno() {
        return DescripcionTurno;
    }

    public void setDescripcionTurno(String DescripcionTurno) {
        this.DescripcionTurno = DescripcionTurno;
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
    
    
    
    
}
