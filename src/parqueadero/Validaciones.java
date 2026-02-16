
package parqueadero;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import javax.swing.JOptionPane;


public class Validaciones 
{
    /*este metodo retorna un entero mayor que cero con las
    validaciones respectivas*/
    public static int LeerInt(String mensaje)
    {
        
    int num=0;//variable de retorno
    do{//obliga al usuario a digitar lo que necesito
       try{//maneja las excepciones que se salen de las manos
           num=Integer.parseInt(JOptionPane.showInputDialog(mensaje));//se pide el entero a validar
           if(num<=0)//solo es para mostrar mensaje de error
             JOptionPane.showMessageDialog(null,"Debe digitar número mayor que cero");
          }catch(Exception err)
          {//no ingresa lo pedido
             JOptionPane.showMessageDialog(null,"Debe digitar NUMEROS ");
          }
         //se sale con el numero mayor que cero de lo contrario se queda    
        }while(num<=0); 
    return num;
    }//fin de leer enteros
    
    /*este metodo retorna un real mayor o igual a cero con las
    validaciones respectivas*/
    public static double LeerDouble(String mensaje)
    {
        
    double num=0;//variable de retorno
    do{//obliga al usuario a digitar lo que necesito
       try{//maneja las excepciones que se salen de las manos
           num=Double.parseDouble(JOptionPane.showInputDialog(mensaje));//se pide el entero a validar
           if(num<0)//solo es para mostrar mensaje de error
             JOptionPane.showMessageDialog(null,"Debe digitar número mayor o igual que cero");
          }catch(Exception err)
          {//no ingresa lo pedido
             JOptionPane.showMessageDialog(null,"Debe digitar NUMEROS ");
          }
         //se sale con el numero mayor o igual que cero de lo contrario se queda    
        }while(num<0); 
    return num;
    }//fin de leer reales
    
     /*este metodo retorna un string o cadena con las
    validaciones respectivas, no lo deja en blanco*/
    public static String LeerString(String mensaje)
    {
    String txt="";//variable de retorno
    do{//obliga al usuario a digitar lo que necesito
       try{//maneja las excepciones que se salen de las manos
           txt=JOptionPane.showInputDialog(mensaje);//se pide el string a validar
           if(txt.equals(""))//solo es para mostrar mensaje de error
             JOptionPane.showMessageDialog(null,"Debe digitar información");
          }catch(Exception err)
          {//no ingresa lo pedido
             JOptionPane.showMessageDialog(null,"Debe digitar información");
          }
         //se sale con algun tipo de entrada de lo contrario se queda    
        }while(txt.equals("")); 
    return txt;
    }//fin de leer cadena o texto
    
    public static boolean LeerBoolean(String mensaje)
    {
        Object opciones;//las opciones del selector o lista
        //se crea un vector tipo Object con los datos que requerimos
        Object[]Estado={"True","False"}; 
        do{//mientras para que se elija una opcion
            opciones=JOptionPane.showInputDialog(null,mensaje+"\nEstado?: ","Seleccione una opción",JOptionPane.QUESTION_MESSAGE,null,Estado,Estado[0]);
            //lectura del dato
        }while(opciones==null);//se sale solo si selecciona
        String Est=opciones.toString();//se pasa Object a string
        boolean sw=Boolean.parseBoolean(Est);
        return sw;
    }
    
    public static String LeerTipoVehiculo()
    {
        Object opciones;//las opciones del selector o lista
        //se crea un vector tipo Object con los datos que requerimos
        Object[]TipoVeh={"Automóvil","Camioneta","Motocicleta","Campero","Microbus","Bus","Buseta"}; 
        do{//mientras para que se elija una opcion
            opciones=JOptionPane.showInputDialog(null,"Tipo Vehículo: ","Seleccione una opción",JOptionPane.QUESTION_MESSAGE,null,TipoVeh,TipoVeh[0]);
            //lectura del dato
        }while(opciones==null);//se sale solo si selecciona
        String tipoV=opciones.toString();//se pasa Object a string
        return tipoV;
    }

  public static String LeerCargo()
    {
        Object opciones;//las opciones del selector o lista
        //se crea un vector tipo Object con los datos que requerimos
        Object[]TipoCar={"Administrador","Gerente","Supervisor","Operario","Cajero","Lavador"}; 
        do{//mientras para que se elija una opcion
            opciones=JOptionPane.showInputDialog(null,"Tipo Cargo: ","Seleccione una opción",JOptionPane.QUESTION_MESSAGE,null,TipoCar,TipoCar[0]);
            //lectura del dato
        }while(opciones==null);//se sale solo si selecciona
        String tipoC=opciones.toString();//se pasa Object a string
        return tipoC;
    }

 //Método para validar una fecha escrita por el usuario formato (AAAA-MM-DD)
    public static LocalDate leerFecha(String mensaje) {
        boolean sw;
        LocalDate fecha = null;
        do {//Mientras que no haya error
            try {
                //Se convierte el string en Local Date
                fecha = LocalDate.parse(JOptionPane.showInputDialog(mensaje+" aaaa-mm-dd "));

                sw = true;
            } catch (DateTimeParseException e) {//Error del local date como el formato
                JOptionPane.showMessageDialog(null, "ERROR - OJO la Excepcion es: " + e);
                sw = false;
            }
        } while (sw != true);

        return fecha;
    }//Fin de leer fechas

//Método que pasa de local date a string
    public static String pasarFechaaString(LocalDate fecha) {
        String fechaS;

        fechaS = (fecha).toString();

        return fechaS;
    }

/*Tipos de turnos de trabajo
Jornada diurna: Se realiza entre las 6:00 y las 20:00 horas, con una duración máxima de 8 horas diarias. 
Jornada nocturna: Se realiza entre las 20:00 y las 6:00 horas, con una duración máxima de 7 horas diarias. 
Jornada mixta: Combina periodos de la jornada diurna y nocturna, siempre que el periodo nocturno no exceda las tres horas y media. 
Turno matutino: Se refiere al turno que comienza durante la mañana, como por ejemplo, de 6 a.m. a 2 p.m. 
Turno vespertino/tardío: Se refiere al turno que comienza durante la tarde, como por ejemplo, de 2 p.m. a 10 p.m. 
Turno de noche: Se refiere al turno que se realiza durante la noche, por ejemplo, de 10 p.m. a 6 a.m. 
Turno rotativo: Los empleados rotan entre diferentes turnos (diurnos, nocturnos o mixtos) según un cronograma establecido. 
Turno de guardia: Se refiere a un turno de guardia para responder a emergencias o necesidades inesperadas. 
Turno fijo: Un trabajador realiza siempre el mismo tipo de turno (por ejemplo, siempre diurno o siempre nocturno). */

     public static String LeerTurno()
    {
        Object opciones;//las opciones del selector o lista
        //se crea un vector tipo Object con los datos que requerimos
        Object[]TipoTurno={"Diurno","Nocturno","Mixto","Rotativo","Guardia","Fijo"}; 
        do{//mientras para que se elija una opcion
            opciones=JOptionPane.showInputDialog(null,"Tipo Turno: ","Seleccione una opción",JOptionPane.QUESTION_MESSAGE,null,TipoTurno,TipoTurno[0]);
            //lectura del dato
        }while(opciones==null);//se sale solo si selecciona
        String tipoT=opciones.toString();//se pasa Object a string
        return tipoT;
    }
     
     public static String LeerServicio()
    {
        Object opciones;//las opciones del selector o lista
        //se crea un vector tipo Object con los datos que requerimos
        Object[]Servicio={"Parqueo_por_horas_o_fracción",
                              "Parqueo_por_días",
                              "Mensualidades_residentes",
                              "Parqueo_para_motos",
                              "Parqueo_bicicletas",
                              "Parqueo_Vehiculos",
                              "Lavado_de_vehículos_básico",
                              "Lavado_de_vehículos_completo",
                              "Mantenimiento_básico_nivel_aceite_presión_llantas",
                              "Otro"}; 
        do{//mientras para que se elija una opcion
            opciones=JOptionPane.showInputDialog(null,"Descripción del Servicio: ","Seleccione una opción",JOptionPane.QUESTION_MESSAGE,null,Servicio,Servicio[0]);
            //lectura del dato
        }while(opciones==null);//se sale solo si selecciona
        String DescriServ=opciones.toString();//se pasa Object a string
        return DescriServ;
    }
     
      //este metodo retorna el año actual
    public static int AActual()
    {
        int AA;//definimos la variable para el año - variable de retorno
        Calendar fecha=Calendar.getInstance();//SE TOMA LA FECHA DEL SISTEMA
        AA=fecha.get(Calendar.YEAR);//solo el año que es el retorno
        return AA;
    }//fin de año actual
    
    //este metodo retorna la fecha actual
    public static LocalDate  FechaActual()
    {
        return LocalDate.now();
    }//fin de año actual

    public static String LeerHora(String mensaje)
    {
        Object opciones;//las opciones del selector o lista
        //se crea un vector tipo Object con los datos que requerimos
        Object[]Horas={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"}; 
        //se muestra el mensaje
        //JOptionPane.showMessageDialog(null, mensaje);
        do{//mientras para que se elija una opcion
            opciones=JOptionPane.showInputDialog(null,mensaje+"\nHora (militar): ","Seleccione la hora",JOptionPane.QUESTION_MESSAGE,null,Horas,Horas[0]);
            //lectura del dato
        }while(opciones==null);//se sale solo si selecciona
        String Hora=opciones.toString();//se pasa Object a string
        return Hora;
    }

   
    /*metodo que valida que un cliente SI esté en nuestra lista doble de clientes, porque prestamos
    el servicio los contratos los convenios a los clientes existentes, sino está se debe ingresar como nuevo
    solo se retorna un id existente sino se queda en el ciclo*/
    public static String ValidarCliente(ListaDoble listaclientes){
        ManejoListas ml = new ManejoListas();//para los metodos de esta clase
        boolean existente=false;
        String id;
        do{
           id=Validaciones.LeerString("Id del cliente: ");
           if(ml.Buscar(id, listaclientes)==true)//si el cliente existe retorna el id existente
                existente=true;
           else{//si no existe se muestra la lista para que la mire 
                if (JOptionPane.showConfirmDialog(null, "¿Desea ver la lista de clientes?", "Cliente no encontrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "La lista de clientes del parqueadero es: (busque por favor bien su id)\n" + listaclientes.ConcatenarDesdeInicio());

               }//fin si
        }while(existente==false);//fin mientras
       return id; 
    }

     /*metodo que valida que un vehiculo SI esté en nuestra lista simple de vehiculos, porque prestamos
    el servicio los contratos los convenios a los vehiculos existentes, sino está se debe ingresar como nuevo
    solo se retorna un id existente sino se queda en el ciclo*/
    public static String ValidarVehiculo(ListaSimple listavehi){
        ManejoListas ml = new ManejoListas();//para los metodos de esta clase
        boolean existente=false;
        String pl;
        do{
           pl=Validaciones.LeerString("Placa del vehiculo: ");
           if(ml.Buscar(pl, listavehi)==true)//si la placa existe retorna la pl existente
                existente=true;
           else{//si no existe se muestra la lista para que la mire 
                if (JOptionPane.showConfirmDialog(null, "¿Desea ver la lista de vehiculos?", "Vehículo no encontrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "La lista de vehiculos del parqueadero es: (busque por favor bien su placa)\n" + listavehi.ConcatenarLista());

               }//fin si
        }while(existente==false);//fin mientras
       return pl; 
    }
    
    
    /*metodo que valida que un turno SI esté en nuestra pila de turnos, porque los empleados
    tienen su turno establecido existente, sino está el turno se debe ingresar como nuevo
    solo se retorna un id de turno existente sino se queda en el ciclo*/
    public static String ValidarTurno(Pila p1){
        Pila p2=new Pila(100000);//la pila auxiliar para que no sea mucha la carga en los parametros
        ManejoPila mp = new ManejoPila();//para los metodos de esta clase
        boolean existente=false;
        String id;
        do{
           id=Validaciones.LeerString("id del turno: ");
           if(mp.Buscar(p1, p2, id)==true)//si el turno existe retorna el id existente
                existente=true;
           else{//si no existe se muestra la lista para que la mire 
                if (JOptionPane.showConfirmDialog(null, "¿Desea ver la pila de turnos?", "id Turno no encontrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "La pila de turnos es: (busque por favor bien su id)\n" + mp.ConcatenarPila(p1, p2));

               }//fin si
        }while(existente==false);//fin mientras
       return id; 
    }
    
    /*metodo que valida que una celda SI exista en nuestra cola de celdas*/
    public static String ValidarCelda(Cola objc){
        Cola objca=new Cola(1000000);//cola auxiliar
        ManejoCola mc = new ManejoCola();//para los metodos de esta clase
        boolean existente, disponible=false;
        String idcel;
        do{
           existente=false;
           idcel=Validaciones.LeerString("Id de la celda para el servicio: ");
           if(mc.Buscar(objc,objca,idcel)==true){//si la celda existe
                existente=true;
                disponible=CeldaDisponible(objc,idcel);
                if(disponible==false){
                    JOptionPane.showMessageDialog(null, "La celda existe pero no esta disponible");
                }
           }
           else{//si no existe se muestra la cola para que la mire 
                if (JOptionPane.showConfirmDialog(null, "¿Desea ver la cola de celdas?", "Celda no encontrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "La cola de celdas del parqueadero es: (busque por favor bien el id)\n" + mc.ConcatenarCola(objc, objca));
               }//fin si
        }while(existente==false || disponible==false);//fin mientras
       return idcel;
    }
    
    /*metodo que valida que una celda SI exista en nuestra cola de celdas*/
    public static boolean CeldaDisponible(Cola objc, String idcel)
    {
        Cola objcaux=new Cola(1000000);//cola auxiliar
        ManejoCola mc = new ManejoCola();//para los metodos de esta clase
        Object dato;//para desencolar
        boolean disp;
        int sw=0;
        while(objc.IsEmpty()==false)
        {
            dato=objc.Pop();//desencolamos el dato
            disp=((Celda)dato).getEstado();
            if (((Celda)dato).getIdCelda().equalsIgnoreCase(idcel))//si llegamos al dato para cambios
            {
                if(disp==true){
                    //se cambia el estado a no disponible   
                    ((Celda)dato).setEstado(false);
                    sw=1;
                }//FIN SI
            }//FIN SI
            objcaux.Push(dato);//guardamos en auxiliar para que no se pierda
                //OJO OJO OJ OSE ESTAN PERDIENDO LOS DATOS DE LA COLA DE CELDAS CON ESTO...LO VOY A DEJAR HASTA EL FINAL
                /*if(sw==1)
                {
                    mc.PasarCola(objcaux,objc);//todo como estaba, incluyendo si se actualizó alguno
                    return true;
                }*/
            
        }//fin mientras
        mc.PasarCola(objcaux,objc);//todo como estaba, incluyendo si se actualizó alguno
        return (sw==1);
    }

    /*metodo que valida que un servicio SI esté en nuestra pila de servicios, porque prestamos
    el servicio existente, sino está se queda en el ciclo*/
    public static String ValidarIdServicio(Pila pser){
        ManejoPila mp = new ManejoPila();//para los metodos de esta clase
        Pila paux=new Pila(100000);//pila auxiliar pero e puede tambien recibir, cualquiera está bien
        boolean existente=false;
        String id;
        do{
           id=Validaciones.LeerString("Id del servicio: ");
           if(mp.BuscarServicio(pser, paux, id)==true)//si el servicio existe retorna el id existente
                existente=true;
           else{//si no existe se muestra la pila para que la mire 
                if (JOptionPane.showConfirmDialog(null, "¿Desea ver la pila de servicios?", "Servicio no encontrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "La pila de servicios del parqueadero es: (busque por favor bien su id)\n" + mp.ConcatenarPila(pser, paux));

               }//fin si
        }while(existente==false);//fin mientras
       return id; 
    }
    
     /*metodo que valida que el vehiculo si sea del cliente*/
    public static boolean ValidarVehiculoDelCliente(String placa, String idCliente, ListaSimple objls){
        ManejoListas objml=new ManejoListas();
        Vehiculo v;
        if (objls.IsEmpty() == false){
            if(objml.Buscar(placa, objls)==true)
            {
                v = (Vehiculo)objls.p.getDato();
                if ( v.getIdDue().equalsIgnoreCase(idCliente)){
                    return true; // Es del cliente
                }
                
            }
        }
        return false; // No es del cliente o no existe
    }
 
     /*metodo que valida que un pago SI esté en nuestra lista simple de pagos, porque los detalles de 
    pago se hacen solo a pagos existentes,  solo se retorna un id existente sino se queda en el ciclo*/
    public static String ValidarPago(ListaSimple listaPagos){
        ManejoListas ml = new ManejoListas();//para los metodos de esta clase
        boolean existente=false;
        String id;
        do{
           id=Validaciones.LeerString("id del pago: ");
           if(ml.BuscarPagos(id, listaPagos)==true)//si el id de pago existe retorna id existente
                existente=true;
           else{//si no existe se muestra la lista para que la mire 
                if (JOptionPane.showConfirmDialog(null, "¿Desea ver la lista de pagos?", "Factura o pago no encontrado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "La lista de pagos es: (busque por favor bien su pago)\n" + listaPagos.ConcatenarLista());

               }//fin si
        }while(existente==false);//fin mientras
       return id; 
    }
  
    
   
}

