
package parqueadero;


public class DetallePago 
{
    //atributos propios y privados
    private String detalleId,pagoId,servicioId;
    private double precio;

    public DetallePago() {
    }

    public DetallePago(String detalleId, String pagoId, String servicioId, double precio) {
        this.detalleId = detalleId;
        this.pagoId = pagoId;
        this.servicioId = servicioId;
        this.precio = precio;
    }

    
      /*falta el ingresar datos, porque hay datos que no se ingresan sino que se extraen de las estructuras*/
    public DetallePago IngresarDatos(String id, String idpago,Pila pser)
    {
        Pila p2=new Pila(10000);//para poder mandar a consultar
        ManejoPila objmp=new ManejoPila();//para el manejo de pilas
        String idser;
        double pre;//auxiliares
        idser=Validaciones.ValidarIdServicio(pser);//se lee un id de servicio existente...
        Servicio objser=(Servicio)objmp.ConsultarServicio(pser, p2, idser);//el objeto de servicio para extraer el precio
        
        DetallePago objdet=new DetallePago(id,idpago,idser,objser.getPrecioServicio());
        return objdet;
    }
    
    public String EstructuraReg() {
        return detalleId + "," + pagoId + "," + servicioId + "," + precio;
    }
    
    @Override
    public String toString() {
        return "detalle d=" + detalleId + ", pago Id=" + pagoId + ", servicio Id=" + servicioId + ", precio=" + precio;
    }

    public String getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(String detalleId) {
        this.detalleId = detalleId;
    }

    public String getPagoId() {
        return pagoId;
    }

    public void setPagoId(String pagoId) {
        this.pagoId = pagoId;
    }

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
