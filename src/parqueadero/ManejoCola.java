
package parqueadero;

import java.time.LocalDate;
import javax.swing.JOptionPane;


public class ManejoCola 
{
    
    /*metodo que ingresa celdas en una cola de 
    celdas, recibe y retorna la cola*/    
public Cola IngresarColaCeldas(Cola objc)
{
int resp;//respuesta del usuario para ingreso de celdas
resp=JOptionPane.showConfirmDialog(null,"Ingresar celda?","Encolando",JOptionPane.YES_NO_OPTION);
Celda objce;
String id;
while (resp==JOptionPane.YES_OPTION)//mientras si celdas
{
	objce=new Celda();//llamada al constructor vacio para sobreescribir
        id=Validaciones.LeerString("Ingrese id celda: ");
	objce=objce.IngresarDatos(id);//tenemos el objeto de celda
 	objc.Push(objce);//se encola el dato de celda
	resp=JOptionPane.showConfirmDialog(null,"Ingresar otra celda?","Encolando",JOptionPane.YES_NO_OPTION);
}//fin mientras
return objc;
}//fin ingresar

/*metodo que retorna una cadena con todos los datos de la cola de celdas
sin perder los datos, recibe dos colas*/
public String ConcatenarCola(Cola objc, Cola objca)
{
String texto="";//variable local de retorno
Object dato;//para desencolar el objeto celda
while (objc.IsEmpty()==false)//mientras tenga datos la cola
{
	dato=objc.Pop();//se desencola la celda en dato
	texto=texto+dato.toString()+"\n";//se concatena
	objca.Push(dato);//se pasa a la cola auxiliar para que no se pierda el dato
}//fin mientras
PasarCola(objca, objc);//metodo para dejar los datos en la cola original
return texto;
}//Fin concatenar

/*metodo que recibe dos colas y pasa los datos de la primera cola que recibe
a la segunda cola que recibe*/
public void PasarCola(Cola c1, Cola c2)
{
while(c1.IsEmpty()==false)
{
  c2.Push(c1.Pop());
}//fin mientras
}//fin pasarcola

/*metodo para insertar de primero en la cola,
o sea primero en salir*/
/*metodo que inserta de primero una celda en la cola
con la filosofía primero en entrar primero en salir*/
public Cola InsertarDePrimero(Cola c1, Cola c2)
{
Celda celda=new Celda();//objeto de celda
String id;//id del objeto a insertar
if(c1.IsEmpty()==false)//si hay datos en cola
{
    PasarCola(c1,c2);//dejamos cola original vacia y pasamos las celdas a la auxiliar
    id=Validaciones.LeerString("Id de celda para insertar de primero: ");
    celda=celda.IngresarDatos(id);//se pide los datos de celda	
    c1.Push(celda);//se encola el dato que quedará de primero
    PasarCola(c2,c1);//se pasan los datos de nuevo a la cola original
    JOptionPane.showMessageDialog(null,"El dato "+id+" se insertó de primero");
}//Fin si
return c1;
}//fin

/*metodo para insertar de ultimo en la cola,
o sea ultimo en salir*/
public Cola InsertarDeUltimo(Cola c1)
{
Celda celda=new Celda();
String id;//id del objeto a insertar
if(c1.IsEmpty()==false)//si hay datos en cola
{
   id=Validaciones.LeerString("Id de celda para insertar de último: ");//Leer id
   celda=celda.IngresarDatos(id);
   c1.Push(celda);//se encola de ultimo
   JOptionPane.showMessageDialog(null,"El dato "+id+" se insertó de último");
}//fin si
return c1;
}//fin	

public int NumeroElementos(Cola c1, Cola c2)
{
int cont=0;//contador local de retorno
while (c1.IsEmpty()==false)
{
	c2.Push(c1.Pop());
	cont=cont+1;
}//fin mientras
PasarCola(c2,c1);
return cont;
}//Fin

/*metodo que elimina y retorna el ultimo dato de la cola
tomar en cuenta que el ultimo es el ultimo en salir*/
public Object EliminarUltimo(Cola c1, Cola c2)
{
Object dato;//dato para el retorno del ultimo eliminado
int cont= NumeroElementos(c1,c2);//se cuentan los datos existentes
for(int i=1;i<=cont-1;i++)//ciclo que recorre hasta el penultimo dato
{
	c2.Push(c1.Pop());//se desencola en auxiliar
}//fin para
dato=c1.Pop();//ultimo dato de la cola se elimina y se guarda para el retorno
PasarCola(c2,c1);//se pasan todos de nuevo a la original excepto el ultimo
return dato;
}//fin

/*metodo que elimina y retorna el primer dato de la cola
tomar en cuenta que el primero es el primero en salir*/
public Object EliminarPrimero(Cola c1)
{
return c1.Pop();
}//Fin

/*metodo que retorna falso o verdadero de acuerdo a un id de 
celda, retorna verdadero si la celda se encuentra
o falso si la celda no se encuentra en cola
OJO no deja apuntadores solo avisa si el dato está o no en cola*/
public boolean Buscar(Cola objc, Cola objca, String id)
{
boolean sw=false;//variable local de retorno
Celda datoc;//para desencolar el objeto celda
while (objc.IsEmpty()==false)//mientras tenga datos la cola
{
	datoc=(Celda)objc.Peek();//se toma la celda que se desencola o próximo en salir
        //ojo recuerden que el peek NO desencola
	if(datoc.getIdCelda().equalsIgnoreCase(id))//si se encuentra celda
           sw=true;
        //fin si
	objca.Push(objc.Pop());//se desencola directamente de la cola original a la auxiliar para que no se pierda el dato
}//fin mientras
PasarCola(objca, objc);//metodo para dejar los datos en la cola original
return sw;
}//Fin concatenar

/*metodo que retorna un mensaje que indica si un dato es eliminado o no,
recibe las colas y el id de celda que vamos a eliminar*/
public String LiberarDato(Cola c1, Cola c2, String id)
{
String texto="No se encuentra, No se elimina el dato";//mensaje de retorno
Object dato;//para desencolar
if(Buscar(c1,c2,id)==true)//encontró el id de celda buscado
{
    while(c1.IsEmpty()==false)//mientras hayan datos en cola
    {
        dato=c1.Pop();//desencolamos
        if(!id.equalsIgnoreCase(((Celda)dato).getIdCelda()))
           c2.Push(dato);//encolar el diferente a id
        //Fin si 
    }//Fin mientras
    PasarCola(c2,c1);
    texto="Dato encontrado y eliminado "+id;
}//Fin si
return texto;
}//fin

/* - Jorge Alvarez - Katalina Gonzales - Dylan Arias - */
    public Cola InsertarDespues(Cola objCola, Cola tempCola) 
    {
        Celda nuevaCelda = new Celda(); //para el ingreso
        String id;
                
        String id_despues = Validaciones.LeerString("Id del dato referencia:");
        if(Buscar(objCola,tempCola,id_despues)==true){
                id = Validaciones.LeerString("id de la nueva celda para ingresar despues: ");
                nuevaCelda = nuevaCelda.IngresarDatos(id);

                while (!objCola.IsEmpty()) {

                    if (((Celda) objCola.Peek()).getIdCelda().equalsIgnoreCase(id_despues)) {
                        tempCola.Push(objCola.Pop());

                        objCola.Push(nuevaCelda);

                        PasarCola(tempCola, objCola);

                        JOptionPane.showMessageDialog(null, "El dato " + id + " se insertó despues de " + id_despues);
                        return objCola;
                    }
                    tempCola.Push(objCola.Pop());
                }
        }else
               JOptionPane.showMessageDialog(null, "No se encontro el id " + id_despues);

        return objCola;
    }//fin

    
    /*metodo que retorna el objeto en caso de encontrarlo, sino lo encuentra
    retorna null*/

public Object Consultar(Cola c1, Cola c2, String id)
{
Object dato=null;
if(Buscar(c1,c2,id)==true)//si el dato existe se hace el proceso
{
	while(c1.IsEmpty()==false)//mientras cola tenga datos
        {
		if(((Celda)c1.Peek()).getIdCelda().equalsIgnoreCase(id))//buscamos el dato
			dato=c1.Peek();//guardamos el dato de retorno con el vistazo peek
		//fin si
		c2.Push(c1.Pop());//se desencola el dato y se encola en la auxiliar para seguir buscando
        }//fin mientras
	PasarCola(c2,c1);//todo como estaba
}
else 
       JOptionPane.showMessageDialog(null,"el dato a consultar no existe "+id);
//Fin si
return dato;
}//fin consultar

public Cola Actualizar(Cola c1, Cola c2, String id)
{
Object dato;//variable local para desencolar
int op;//la opcion para el menu de los cambios
int resp;//confirmar si toma los cambios
boolean est;
String tpv;//variables auxiliare y locales para tomar los cambios
if(Buscar(c1,c2,id)==true)//tenemos la certeza que existe el dato
{
    while(c1.IsEmpty()==false)
    {
	dato=c1.Pop();//desencolamos el dato
        if (((Celda)dato).getIdCelda().equalsIgnoreCase(id))//si llegamos al dato para cambios
        {
        //se inicializan las auxiliares con los datos del objeto desencolado y encontrado    
        tpv=((Celda)dato).getTipoVehiculo();
	est=((Celda)dato).getEstado();
	do{//mientras para el menu de cambio
	  op=Validaciones.LeerInt("Menu actualización\n"+
		     "\nId celda"+id+
	             "\n1. Tipo de vehiculo que se coloca en la celda: "+tpv+
                     "\n2. El estado de la celda es (true desocupada, false ocupada): "+est+
                     "\n3. Terminar cambios");
	switch(op)
        {//En caso de (op)
             	case 1:tpv=Validaciones.LeerTipoVehiculo();
                       break;
		case 2:est=Validaciones.LeerBoolean("true desocupada, false ocupada");
                       break;  
        }//Fin caso
        }while(op<3);//fin mientras del menu de opciones		
	resp=JOptionPane.showConfirmDialog(null,"Actualizar datos?","Actualizar",JOptionPane.YES_NO_OPTION);
	if(resp==JOptionPane.YES_OPTION)//si quiere guardar los cambios
            {//se actualizan
             ((Celda)dato).setTipoVehiculo(tpv);
             ((Celda)dato).setEstado(est);
             JOptionPane.showMessageDialog(null,"datos actualizados con éxito");
            }//fin si
        }//fin si
        c2.Push(dato);//guardamos en auxiliar para que no se pierda
    }//fin mientras
    PasarCola(c2,c1);//todo como estaba, incluyendo si se actualizó alguno
}//no se encuentra buscar = falso
else
       JOptionPane.showMessageDialog(null,"el dato para actualizar no existe "+id);
//Fin si
return c1;
}//fin
    
    
// metodo que copia todo el contenido del archivo a la cola de celdas
    public Cola CopiarArchivoCola(Archivos objArch, Cola c1) {      
        String cadena = "";
        try {
            // locales auxiliares
            String IdCelda, TipoVehiculo;
            boolean Estado;
            String Reg[];
            // abrir archivo modo lectura
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Celdas.txt"));
            Reg = objArch.LeerRegistro(3);
            
            while (Reg != null && Reg[0] != null) {
                // asignar datos del registro
                IdCelda = Reg[0];
                TipoVehiculo = Reg[1];
                Estado = Boolean.parseBoolean(Reg[2]);
                Celda objc;
                objc = new Celda(IdCelda, TipoVehiculo, Estado);
                c1.Push(objc);//se encola el registro
                Reg = objArch.LeerRegistro(3);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return c1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////C O L A   D E   C O N V E N I O S /////////////////////////////////////
    
    
public Cola IngresarConvenio(Cola objco, ListaDoble LDclientes)
{
int resp;//respuesta del usuario para ingreso de celdas
resp=JOptionPane.showConfirmDialog(null,"Ingresar convenio?","Encolando",JOptionPane.YES_NO_OPTION);
Convenio objcon;
String id;
while (resp==JOptionPane.YES_OPTION)
{//mientras si convenios
	objcon=new Convenio();//llamada al constructor vacio para sobreescribir
        id=Validaciones.LeerString("Ingrese id convenio: ");
	objcon=objcon.IngresarDatos(id, LDclientes);//tenemos el objeto de convenio
 	objco.Push(objcon);//se encola el dato de convenio
	resp=JOptionPane.showConfirmDialog(null,"Ingresar otro convenio?","Encolando",JOptionPane.YES_NO_OPTION);
}//fin mientras
return objco;
}//fin ingresar
  
public boolean BuscarConvenio(Cola objcol, Cola objca, String id)
{
boolean sw=false;//variable local de retorno
Convenio datoc;//para desencolar el objeto convenio
while (objcol.IsEmpty()==false)//mientras tenga datos la cola
{
	datoc=(Convenio)objcol.Peek();//se toma la celda que se desencola o próximo en salir
        //ojo recuerden que el peek NO desencola
	if(datoc.getIdConvenio().equalsIgnoreCase(id))//si se encuentra convenio
           sw=true;
        //fin si
	objca.Push(objcol.Pop());//se desencola directamente de la cola original a la auxiliar para que no se pierda el dato
}//fin mientras
PasarCola(objca, objcol);//metodo para dejar los datos en la cola original
return sw;
}//Fin concatenar

public String LiberarDatoCon(Cola c1, Cola c2, String id)
{
String texto="No se encuentra, No se elimina el convenio";//mensaje de retorno
Object dato;//para desencolar
if(BuscarConvenio(c1,c2,id)==true)//encontró el id de convenio buscado
{
    while(c1.IsEmpty()==false)//mientras hayan datos en cola
    {
        dato=c1.Pop();//desencolamos
        if(!id.equalsIgnoreCase(((Convenio)dato).getIdConvenio()))
           c2.Push(dato);//encolar el diferente a id
        //Fin si 
    }//Fin mientras
    PasarCola(c2,c1);
    texto="Convenio encontrado y eliminado "+id;
}//Fin si
return texto;
}//fin

public Object ConsultarCon(Cola c1, Cola c2, String id)
{
Object dato=null;
if(BuscarConvenio(c1,c2,id)==true)//encontró el id de convenio buscado
{
    while(c1.IsEmpty()==false)//mientras hayan datos en cola
    {
		if(((Convenio)c1.Peek()).getIdConvenio().equalsIgnoreCase(id))//buscamos el dato
			dato=c1.Peek();//guardamos el dato de retorno con el vistazo peek
		//fin si
		c2.Push(c1.Pop());//se desencola el dato y se encola en la auxiliar para seguir buscando
    }//fin mientras
PasarCola(c2,c1);//todo como estaba
}
else 
       JOptionPane.showMessageDialog(null,"el dato a consultar no existe "+id);
//Fin si
return dato;
}//fin consultar

// metodo que copia todo el contenido del archivo a la cola de convenios
    public Cola CopiarArchivoColaConvenios(Archivos objArch, Cola c1) {  
        String cadena = "";
        try {
            // locales auxiliares
            String IdConvenio, idCli, Des;
            LocalDate fi,ff;
            double pre;
            String Reg[];
            // abrir archivo modo lectura
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Convenios.txt"));
            Reg = objArch.LeerRegistro(6);
            
            while (Reg != null && Reg[0] != null) {
                // asignar datos del registro
                IdConvenio = Reg[0];
                idCli = Reg[1];
                Des = Reg[2];
                fi=LocalDate.parse(Reg[3]);
                ff=LocalDate.parse(Reg[4]);
                pre=Double.parseDouble(Reg[5]);
                Convenio objc;
                objc = new Convenio(IdConvenio, idCli,Des,fi,ff,pre);
                c1.Push(objc);
                                
                Reg = objArch.LeerRegistro(6);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return c1;
    } 
    
    //metodo que muestra los clientes con convenio--Joseph Sanchez
public String ListarClientesConvenio(Cola objColaConvenios, Cola objcoa,ListaDoble clientes)
{
    /*recorremos la cola de convenios, por cada nodo de convenio recorremos toda la lista
     doble de clientes*/
     Convenio datoc;
     String clientesConvenio = "";

    while (objColaConvenios.IsEmpty()==false)//mientras tenga datos la cola
    {
        datoc=(Convenio)objColaConvenios.Peek();//se toma la celda que se desencola o próximo en salir
        Nodo objc = clientes.getStart();//se vuelve a poner en estar cada iteracion de la cola

        while(objc!=null)//recorremos la lista de clientes
        {
           if(datoc.getIdCliente().equalsIgnoreCase(((Cliente)objc.getDato()).getIdUser()))
               clientesConvenio = clientesConvenio +  ((Cliente)objc.getDato()).toString() + "\n";
            //fin si

            objc = objc.getSiguiente();
        }//fin mienreas clientes

        objcoa.Push(objColaConvenios.Pop());//se desencola directamente de la cola original a la auxiliar para que no se pierda el dato
    }//fin mientras convenios
        PasarCola(objcoa, objColaConvenios);//metodo para dejar los datos en la cola original
        return clientesConvenio;
}//Fin listar clientes con convenio


/*metodo que verifica si se finalizo el convenio y de 
ser asi lo cierra(librera)--Joseph Sanchez*/
public void CerrarConvenio(Cola objColaConvenios, Cola objcoa)
{
    // Pedimos el id del convenio a cerrar
    String id = Validaciones.LeerString("Ingrese el ID del convenio a cerrar:");

    // Consultamos el convenio usando tu método existente
    Convenio conv = (Convenio) ConsultarCon(objColaConvenios, objcoa, id);

    if (conv == null) {
        JOptionPane.showMessageDialog(null, " El convenio con ID " + id + " no existe.");
        return;
    }//fin si

    LocalDate hoy = LocalDate.now();

    if (conv.getFechaFinalizacion().isBefore(hoy)) {
        // Si está vencido, lo eliminamos
        String mensaje = LiberarDatoCon(objColaConvenios, objcoa, id);
        JOptionPane.showMessageDialog(null, mensaje); // "Convenio encontrado y eliminado"
    } else {
        JOptionPane.showMessageDialog(null, "El convenio aún no ha finalizado. Fecha de finalización: " 
                                    + conv.getFechaFinalizacion());
    }//fin si
}//fin cerrarconvenio


/*metodo para listar por varios atributos en la cola de celas*/
public String ListarPorAtributo(Cola objc, Cola objca, int op, String tipo)
{
    String texto="";//variable local de retorno
    Object dato;//para desencolar el objeto celda
    while (objc.IsEmpty()==false)//mientras tenga datos la cola
    {
        switch(op)
        {
            case 1:
                dato=objc.Pop();//se desencola la celda en dato
                if(((Celda)dato).getEstado()==true)//Listar por estado (desocupado)
                {
                    texto=texto+dato.toString()+"\n";//se concatena
                }
                objca.Push(dato);//se pasa a la cola auxiliar para que no se pierda el dato
                break;
            case 2:
                dato=objc.Pop();//se desencola la celda en dato
                if(((Celda)dato).getEstado()==false)//Listar por estado (desocupado)
                {
                    texto=texto+dato.toString()+"\n";//se concatena
                }
                objca.Push(dato);//se pasa a la cola auxiliar para que no se pierda el dato
                break;
            case 6:
                dato=objc.Pop();//se desencola la celda en dato
                if(((Celda)dato).getTipoVehiculo().equalsIgnoreCase(tipo))//Listar por estado (ocupado)
                {
                    texto=texto+dato.toString()+"\n";//se concatena
                }
                objca.Push(dato);//se pasa a la cola auxiliar para que no se pierda el dato
                break;
        }
    }//fin mientras
    PasarCola(objca, objc);//metodo para dejar los datos en la cola original
    return texto;
}

/*metodo que busca el convenio y de ser encontrado se puede actualizar todos los campos
  menos el idconvenio--Joseph Sanchez*/
public void actualizarConvenio(Cola objColaConvenios,Cola objcoa,String idCon,ListaDoble clientes)
{   //Verificamos que el convenio exista en la cola
    Convenio dato;
    if(BuscarConvenio(objColaConvenios,objcoa,idCon))
    {
         while(objColaConvenios.IsEmpty()==false)
        {
	dato=(Convenio)objColaConvenios.Pop();//desencolamos el dato
        if (((Convenio)dato).getIdConvenio().equalsIgnoreCase(idCon))//si llegamos al dato para cambios
        {
        // Variables inicializadas con los valores actuales
        //atributos propios y privados
        String idCliente = dato.getIdCliente();
        String DescripcionConvenio = dato.getDescripcionConvenio();
        LocalDate FechaInicio = dato.getFechaInicio();
        LocalDate FechaFinalizacion = dato.getFechaFinalizacion();
        double PrecioConvenio = dato.getPrecioConvenio();

        int op;
        int resp;

        do{
            //menu de atributos a actualizar (muestra valores actuales)
            op = Validaciones.LeerInt(
                "Menu actualización\n" +
                "\nIdConvenio: " + dato.getIdConvenio()+
                "\n1. IdCliente " + idCliente +        
                "\n2. DescripcionConvenio: " + DescripcionConvenio +
                "\n3. Fecha de inicio: " + FechaInicio +
                "\n4. Fecha de finalizacion: " + FechaFinalizacion +
                "\n5. Precio del convenio: " + PrecioConvenio +
                "\n6. Terminar cambio");

            switch(op)
            {//En caso de (op)
                case 1:
                    idCliente =Validaciones.ValidarCliente(clientes);
                    break;
                case 2:    
                    DescripcionConvenio = Validaciones.LeerString("Ingrese la descricion del convenio: ");
                    break;
                case 3:
                    FechaInicio = Validaciones.leerFecha("Ingrese la fecha de inicio del convenio: ");
                    break;
                case 4:
                    FechaFinalizacion = Validaciones.leerFecha("Ingrese la fecha de finalizacion del convenio: ");
                    break;
                case 5:
                    PrecioConvenio = Validaciones.LeerDouble("Ingrese el precio del covenio: ");
                    break;
            }//Fin switch
        } while(op < 6);//fin while del menu de opciones

        // Preguntar si desea guardar los cambios
        resp = JOptionPane.showConfirmDialog(null, "¿Actualizar datos?", "Actualizar", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION)//si quiere guardar los cambios
        {
            // Se actualizan los atributos del objeto directamente
            dato.setIdCliente(idCliente);
            dato.setDescripcionConvenio(DescripcionConvenio);
            dato.setFechaInicio(FechaInicio);
            dato.setFechaFinalizacion(FechaFinalizacion);
            dato.setPrecioConvenio(PrecioConvenio);
            

            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se realizaron cambios del convenio " + dato.getIdConvenio());
        }
        }//sino es igual el id se sigue buscando
        objcoa.Push(dato);//se guarda en auxiliar
        }//fin mientras del recorrido de cola
        PasarCola(objcoa,objColaConvenios); //todo como estaba
    }//se encuentra el convenio se hace todo el proceso
    else
    {
        JOptionPane.showMessageDialog(null, "El convenio no se encuentra registrado en la cola: " + idCon);
    }//fin if Buscar
}//fin actualizar



}
