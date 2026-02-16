
package parqueadero;

import java.time.LocalDate;
import javax.swing.JOptionPane;


public class Pago 
{
    //atributos propios y privados
    private String pagoId, Placa, IdCliente;
    private double monto; 
    private  LocalDate fecha;
    DetallePago objdet;//el agregado

    public Pago() {
    }

    public Pago(String pagoId, String Placa, String IdCliente, double monto, LocalDate fecha) {
        this.pagoId = pagoId;
        this.Placa = Placa;
        this.IdCliente = IdCliente;
        this.monto = monto;//es el valor total de todos los servicios del pago, o sea la suma
        this.fecha = fecha;
    }

    
    /*falta el ingresar datos, porque hay datos que no se ingresan sino que se extraen de las estructuras*/
    public Pago IngresarDatos(String idp, ListaSimple vehi, ListaDoble clie, Pila serv,ListaSimple detaPago)
    {
        //auxiliares
         ManejoListas objm=new ManejoListas();//para la cedula del dueño de acuerdo a la placa
         String Pla, IdCl;
         double total; 
         LocalDate fecha;
         Pla=Validaciones.ValidarVehiculo(vehi);
         Object cliente=objm.BuscarDuePlaca(Pla, vehi, clie);//se llama al metodo que busca el cliente de acuerdo con su placa
         IdCl=((Cliente)cliente).getIdUser();
         total=IngresarDetalles(idp,serv,detaPago);
         fecha=Validaciones.FechaActual();
         Pago objpago=new Pago(idp,Pla,IdCl,total,fecha);
         return objpago;
    }
    
    /*como detalle de pagos es un agregado de pago, se maneja el ingreso desde pago*/
    
    public double IngresarDetalles(String idp, Pila pserv, ListaSimple detaPago)
    {
        String id;//para id de detalle de pago
        double total=0;//para acumular todos los pagos de servicios
        int resp=JOptionPane.YES_OPTION;//ojo para que haya por lo menos un detalle
        while(resp==JOptionPane.YES_OPTION)//mientras quiera ingresar detalles o servicios al pago
        {
            objdet=new DetallePago();//para que sobreescriba
            id=Validaciones.LeerString("Ingrese id de detalle de pago para el pago número: "+idp);
            objdet=objdet.IngresarDatos(id, idp, pserv);//se lee el detalle
            detaPago.CrearPorFinal(objdet);//se graba en la lista de detalles, sino se pierde
            total=total+objdet.getPrecio();//se acumula en el total de pago
            resp=JOptionPane.showConfirmDialog(null,"Ingresar mas detalles de pago a "+idp,"Facturando",JOptionPane.YES_NO_OPTION);
            
        }//fin mientras
        return total;//el total del pago idp       
    }
    
    
    
    public String EstructuraReg() {
        return pagoId + "," + Placa + "," + IdCliente + "," + monto + "," + fecha;
    }
    
    @Override
    public String toString() {
        return "pago Id=" + pagoId + ", Placa=" + Placa + ", Id Cliente=" + IdCliente + ", monto=" + monto + ", fecha=" + fecha;
    }

  
    public String getPagoId() {
        return pagoId;
    }

    public void setPagoId(String pagoId) {
        this.pagoId = pagoId;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
 
    
    
    
    
}
