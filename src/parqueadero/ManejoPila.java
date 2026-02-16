
package parqueadero;

import javax.swing.JOptionPane;


public class ManejoPila
{

/*metodo que ingresa turnos en una pila de 
    turnos, recibe y retorna la pila*/    
public Pila IngresarPilaTurnos(Pila objp)
{
int resp;//respuesta del usuario para ingreso de turnos
resp=JOptionPane.showConfirmDialog(null,"Ingresar turno?","Apilando",JOptionPane.YES_NO_OPTION);
Turno objt;
String id;
while (resp==JOptionPane.YES_OPTION)//mientras si turnos
{
	objt=new Turno();//llamada al constructor vacio para sobreescribir
        id=Validaciones.LeerString("Ingrese id turno: ");
	objt=objt.IngresarDatos(id);//tenemos el objeto de turno
 	objp.Push(objt);//se apila el turno
	resp=JOptionPane.showConfirmDialog(null,"Ingresar otro turno?","Apilando",JOptionPane.YES_NO_OPTION);
}//fin mientras
return objp;
}//fin ingresar

/*metodo que retorna una cadena con todos los datos de la pila de turnos
sin perder los datos, recibe dos pilas*/
public String ConcatenarPila(Pila objp, Pila objpa)
{
String texto="";//variable local de retorno
Object dato;//para desapilar el objeto turno
while (objp.IsEmpty()==false)//mientras tenga datos la pila
{
	dato=objp.Pop();//se desapila turno en dato
	texto=texto+dato.toString()+"\n";//se concatena
	objpa.Push(dato);//se pasa a la pila auxiliar para que no se pierda el dato
}//fin mientras
PasarPila(objpa, objp);//metodo para dejar los datos en la pila original
return texto;
}//Fin concatenar

/*metodo que recibe dos pilas y pasa los datos de la primera pila que recibe
a la segunda pila que recibe*/
public void PasarPila(Pila p1, Pila p2)
{
while(p1.IsEmpty()==false)
{
  p2.Push(p1.Pop());
}//fin mientras
}//fin pasarpila

/*metodo para insertar de primero en la pila,
o sea primero en salir*/
public Pila InsertarDePrimero(Pila p1)
{
Turno objt=new Turno();//para el ingreso de turno
String id;//codigo de turno
if(p1.IsEmpty()==false)//si hay datos en pila
{
	id=Validaciones.LeerString("id de turno para insertar de primero");
	objt=objt.IngresarDatos(id);//se pide el objeto turno
	p1.Push(objt);//se apila el objeto turno
        JOptionPane.showMessageDialog(null,"El dato "+id+" se insertó de primero");
}//fin si
return p1;
}//fin insertar

/*metodo para insertar de ultimo en la pila,
o sea ultimo en salir*/
public Pila InsertarDeUltimo(Pila p1,Pila p2)
{
Turno objt=new Turno();//para el ingreso
String id;//id del dato
if(p1.IsEmpty()==false)
{
	id=Validaciones.LeerString("id de turno para ingresar de último: ");
	objt=objt.IngresarDatos(id);
	PasarPila(p1,p2);//dejamos la pila original vacia
	p1.Push(objt);//apilamos el dato que quedará de ultimo
	PasarPila(p2,p1);//colocamos de nuevo los datos en la pila original
        JOptionPane.showMessageDialog(null,"El dato "+id+" se insertó de último");
}//Fin si
return p1;
}//fin

/*metodo que elimina y retorna el primer dato de la pila
tomar en cuenta que el primero es el ultimo en salir*/
public Object EliminarPrimero(Pila p1, Pila p2)
{
Object Dato;//para el retorno
PasarPila(p1,p2);//se pasan los datos de p1 a p2 para que el ultimo quede de primero
Dato=p2.Pop();//se desapila el dato de encima de la pila auxiliar
PasarPila(p2,p1);//se apilan los datos que quedan de nuevo en la pila original
return Dato;
}//Fin

/*metodo que elimina y retorna el ultimo dato de la pila
tomar en cuenta que el ultimo es el primero en salir*/
public Object EliminarUltimo(Pila p1)
{
Object Dato;//para el retorno
Dato=p1.Pop();//se desapila el dato de encima de la pila 
return Dato;
//return p1.Pop();
}//Fin
    
public int NumeroElementos(Pila p1, Pila p2)
{
int cont=0;//contador local de retorno
while (p1.IsEmpty()==false)
{
	p2.Push(p1.Pop());
	cont=cont+1;
}//fin mientras
PasarPila(p2,p1);
return cont;
}//Fin

/*metodo que retorna falso o verdadero de acuerdo a un id de 
turno, retorna verdadero si el turno se encuentra
o falso si el turno no se encuentra en pila
OJO no deja apuntadores solo avisa si el dato está o no en pila*/
public boolean Buscar(Pila objp, Pila objpa, String id)
{
boolean sw=false;//variable local de retorno
Turno datot;//para desapilar el objeto turno
while (objp.IsEmpty()==false)//mientras tenga datos la pila
{
	datot=(Turno)objp.Peek();//se toma el turno que se desapila o próximo en salir
        //ojo recuerden que el peek NO desapila
	if(datot.getIdTurno().equalsIgnoreCase(id))//si se encuentra turno
           sw=true;
        //fin si
	objpa.Push(objp.Pop());//se desapila directamente de la pila original a la auxiliar para que no se pierda el dato
}//fin mientras
PasarPila(objpa, objp);//metodo para dejar los datos en la pila original
return sw;
}//Fin concatenar

/*metodo que retorna un mensaje que indica si un dato es eliminado o no,
recibe las pilas y el id de turno que vamos a eliminar*/
public String LiberarDato(Pila p1, Pila p2, String id)
{
String texto="No se encuentra, No se elimina el dato";//mensaje de retorno
Object dato;//para despilar
if(Buscar(p1,p2,id)==true)//encontró el id de turno buscado
{
    while(p1.IsEmpty()==false)//mientras hayan datos en pila
    {
        dato=p1.Pop();//desapilamos
        if(!id.equalsIgnoreCase(((Turno)dato).getIdTurno()))
           p2.Push(dato);
        //Fin si 
    }//Fin mientras
    PasarPila(p2,p1);
    texto="Dato encontrado y eliminado "+id;
}//Fin si
return texto;
}//fin

/* - Jorge Alvarez - Katalina Gonzales - Dylan Arias - */
    public Pila InsertarDespues(Pila objPila, Pila tempPila) 
    {
        Turno nuevoTurno = new Turno(); //para el ingreso
        String id;
                
        String id_despues = Validaciones.LeerString("Id del dato referencia:");
        if(Buscar(objPila,tempPila,id_despues)==true){
                id = Validaciones.LeerString("id del nuevo turno para ingresar despues: ");
                nuevoTurno = nuevoTurno.IngresarDatos(id);

                while (!objPila.IsEmpty()) {

                    if (((Turno) objPila.Peek()).getIdTurno().equalsIgnoreCase(id_despues)) {
                        tempPila.Push(objPila.Pop());

                        objPila.Push(nuevoTurno);

                        PasarPila(tempPila, objPila);

                        JOptionPane.showMessageDialog(null, "El dato " + id + " se insertó despues de " + id_despues);
                        return objPila;
                    }
                    tempPila.Push(objPila.Pop());
                }
        }else
               JOptionPane.showMessageDialog(null, "No se encontro el id " + id_despues);

        return objPila;
    }//fin
    
    /*metodo que retorna el objeto en caso de encontrarlo, sino lo encuentra
    retorna null*/

public Object Consultar(Pila p1, Pila p2, String id)
{
Object dato=null;
if(Buscar(p1,p2,id)==true)//si el dato existe se hace el proceso
{
	while(p1.IsEmpty()==false)//mientras pila tenga datos
        {
		if(((Turno)p1.Peek()).getIdTurno().equalsIgnoreCase(id))//buscamos el dato
			dato=p1.Peek();//guardamos el dato de retorno con el vistazo peek
		//fin si
		p2.Push(p1.Pop());//se desapila el dato y se apila en la auxiliar para seguir buscando
        }//fin mientras
	PasarPila(p2,p1);//todo como estaba
}
else 
       JOptionPane.showMessageDialog(null,"el dato a consultar no existe "+id);
//Fin si
return dato;
}//fin consultar

public Pila Actualizar(Pila p1, Pila p2, String id)
{
Object dato;//variable local para desapilar
int op;//la opcion para el menu de los cambios
int resp;//confirmar si toma los cambios
String des,hi,hf;//variables auxiliare y locales para tomar los cambios
if(Buscar(p1,p2,id)==true)//tenemos la certeza que existe el dato
{
    while(p1.IsEmpty()==false)
    {
	dato=p1.Pop();//desapilamos el datos
        if (((Turno)dato).getIdTurno().equalsIgnoreCase(id))//si llegamos al dato para cambios
        {
        //se inicializan las auxiliares con los datos del objeto desapilado y encontrado    
        des=((Turno)dato).getDescripcionTurno();
	hi=((Turno)dato).getHoraInicio();
	hf=((Turno)dato).getHoraFin();
        do{//mientras para el menu de cambio
	  op=Validaciones.LeerInt("Menu actualización\n"+
		     "\nId turno"+id+
	             "\n1. Descripción del turno: "+des+
                     "\n2. Hora inicio: "+hi+
                     "\n3.  Hora final:"+hf+
                     "\n4. Terminar cambios");
	switch(op)
        {//En caso de (op)
             	case 1:des=Validaciones.LeerTurno();
                       break;
		case 2:hi=Validaciones.LeerHora("Nueva hora de inicio.");
                       break;  
		case 3:hf=Validaciones.LeerHora("Nueva hora de final.");
                       break;  
        }//Fin caso
        }while(op<4);//fin mientras del menu de opciones		
	resp=JOptionPane.showConfirmDialog(null,"Actualizar datos?","Actualizar",JOptionPane.YES_NO_OPTION);
	if(resp==JOptionPane.YES_OPTION)//si quiere guardar los cambios
            {//se actualizan
             ((Turno)dato).setDescripcionTurno(des);
             ((Turno)dato).setHoraInicio(hi);
             ((Turno)dato).setHoraFin(hf);
             JOptionPane.showMessageDialog(null,"datos actualizados con éxito");
            }//fin si
        }//fin si
        p2.Push(dato);//guardamos en auxiliar para que no se pierda
    }//fin mientras
    PasarPila(p2,p1);//todo como estaba, incluyendo si se actualizó alguno
}//no se encuentra buscar = falso
else
       JOptionPane.showMessageDialog(null,"el dato para actualizar no existe "+id);
//Fin si
return p1;
}//fin

//metodo que copia todo el contenido del archivo a la pila de turnos
	public Pila CopiarArchivoPila(Archivos objArch, Pila p1) {
		String cadena = "";
		try {
			//locales auxiliares para extraer la informacion del archivo
			String IdT, Des, Hi, Hf;

			String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
			//se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
			javax.swing.JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Turnos.txt"));
			//se invoca al metodo de leer registro con 4 atributos para el vector de la linea o registro del archivo plano 
			//se recibe el texto en Reg
			Reg = objArch.LeerRegistro(4);
			//mientras existan datos en el archivo
			while (Reg != null && Reg[0] != null) //mientras not EOF()
			{
				/*los datos del Reg que se obtiene del archivo plano de texto se 
				asignan a las variables auxiliares locales para su facil manejo 
				como posiciones del vector String*/
				IdT = Reg[0];
				Des = Reg[1];
				Hi = Reg[2];
				Hf = Reg[3];

				Turno objt;
				objt = new Turno(IdT, Des, Hi, Hf);

				p1.Push(objt);//se apila

				Reg = objArch.LeerRegistro(4);//para que salga del ciclo
			}//fin mientras  
			objArch.CerrarArchivoModoLectura();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
		}
		return p1;
	}//fin de copiar

   
        
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////P I L A  S E R V I C I O ///////////////////////////////////////////////////////////////////
        
    public Pila IngresarPilaServicio(Pila objp)
    {
    int resp;//respuesta del usuario para ingreso de turnos
    resp=JOptionPane.showConfirmDialog(null,"Ingresar servicio?","Apilando",JOptionPane.YES_NO_OPTION);
    Servicio objs;
    String id;
    while (resp==JOptionPane.YES_OPTION)//mientras si servicio
    {
            objs=new Servicio();//llamada al constructor vacio para sobreescribir
            id=Validaciones.LeerString("Ingrese id Servicio: ");
            objs=objs.IngresarDatos(id);//tenemos el objeto de servicio
            objp.Push(objs);//se apila el servicio
            resp=JOptionPane.showConfirmDialog(null,"Ingresar otro Servicio?","Apilando",JOptionPane.YES_NO_OPTION);
    }//fin mientras
    return objp;
    }//fin ingresar

    /*metodo para insertar de primero en la pila,
    o sea primero en salir*/
    public Pila InsertarDePrimeroServicio(Pila p1)
    {
    Servicio objs=new Servicio();//para el ingreso de turno
    String id;//codigo de turno
    if(p1.IsEmpty()==false)//si hay datos en pila
    {
            id=Validaciones.LeerString("id de servicio para insertar de primero");
            objs=objs.IngresarDatos(id);//se pide el objeto servicio
            p1.Push(objs);//se apila el objeto servicio
            JOptionPane.showMessageDialog(null,"El dato "+id+" se insertó de primero");
    }//fin si
    return p1;
    }//fin insertar

    /*metodo para insertar de ultimo en la pila,
    o sea ultimo en salir*/
    public Pila InsertarDeUltimoServicio(Pila p1,Pila p2)
    {
    Servicio objs=new Servicio();//para el ingreso
    String id;//id del dato
    if(p1.IsEmpty()==false)
    {
            id=Validaciones.LeerString("id de servicio para ingresar de último: ");
            objs=objs.IngresarDatos(id);
            PasarPila(p1,p2);//dejamos la pila original vacia
            p1.Push(objs);//apilamos el dato que quedará de ultimo
            PasarPila(p2,p1);//colocamos de nuevo los datos en la pila original
            JOptionPane.showMessageDialog(null,"El dato "+id+" se insertó de último");
    }//Fin si
    return p1;
    }//fin

    /*metodo que retorna falso o verdadero de acuerdo a un id de 
    turno, retorna verdadero si el turno se encuentra
    o falso si el turno no se encuentra en pila
    OJO no deja apuntadores solo avisa si el dato está o no en pila*/
    public boolean BuscarServicio(Pila objp, Pila objpa, String id)
    {
    boolean sw=false;//variable local de retorno
    Servicio datoS;//para desapilar el objeto turno
    while (objp.IsEmpty()==false)//mientras tenga datos la pila
    {
            datoS=(Servicio)objp.Peek();//se toma el turno que se desapila o próximo en salir
            //ojo recuerden que el peek NO desapila
            if(datoS.getIdServicio().equalsIgnoreCase(id))//si se encuentra turno
               sw=true;
            //fin si
            objpa.Push(objp.Pop());//se desapila directamente de la pila original a la auxiliar para que no se pierda el dato
    }//fin mientras
    PasarPila(objpa, objp);//metodo para dejar los datos en la pila original
    return sw;
    }//Fin concatenar

    /* - Jorge Alvarez - Katalina Gonzales - Dylan Arias - */
        public Pila InsertarDespuesServicio(Pila objPila, Pila tempPila) 
        {
            Servicio nuevoServicio = new Servicio(); //para el ingreso
            String id;

            String id_despues = Validaciones.LeerString("Id del dato referencia:");
            if(BuscarServicio(objPila,tempPila,id_despues)==true){
                    id = Validaciones.LeerString("id del nuevo turno para ingresar despues: ");
                    nuevoServicio = nuevoServicio.IngresarDatos(id);

                    while (!objPila.IsEmpty()) {

                        if (((Servicio) objPila.Peek()).getIdServicio().equalsIgnoreCase(id_despues)) {
                            tempPila.Push(objPila.Pop());

                            objPila.Push(nuevoServicio);

                            PasarPila(tempPila, objPila);

                            JOptionPane.showMessageDialog(null, "El dato " + id + " se insertó despues de " + id_despues);
                            return objPila;
                        }
                        tempPila.Push(objPila.Pop());
                    }
            }else{
                   JOptionPane.showMessageDialog(null, "No se encontro el id " + id_despues);
            }
            return objPila;
        }//fin
        
    public Object ConsultarServicio(Pila p1, Pila p2, String id)
        {
        Object dato=null;
        if(BuscarServicio(p1,p2,id)==true)//si el dato existe se hace el proceso
        {
                while(p1.IsEmpty()==false)//mientras pila tenga datos
                {
                        if(((Servicio)p1.Peek()).getIdServicio().equalsIgnoreCase(id))//buscamos el dato
                                dato=p1.Peek();//guardamos el dato de retorno con el vistazo peek
                        //fin si
                        p2.Push(p1.Pop());//se desapila el dato y se apila en la auxiliar para seguir buscando
                }//fin mientras
                PasarPila(p2,p1);//todo como estaba
        }
        else 
               JOptionPane.showMessageDialog(null,"el dato a consultar no existe "+id);
        //Fin si
        return dato;
        }//fin consultar
        
        public Pila CopiarArchivoPilaServicio(Archivos objArch, Pila p1) {
		String cadena = "";
		try {
			//locales auxiliares para extraer la informacion del archivo
			String IdS, Des;
                        double  Pre;

			String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
			//se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
			javax.swing.JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Servicio.txt"));
			//se invoca al metodo de leer registro con 4 atributos para el vector de la linea o registro del archivo plano 
			//se recibe el texto en Reg
			Reg = objArch.LeerRegistro(4);
			//mientras existan datos en el archivo
			while (Reg != null && Reg[0] != null) //mientras not EOF()
			{
				/*los datos del Reg que se obtiene del archivo plano de texto se 
				asignan a las variables auxiliares locales para su facil manejo 
				como posiciones del vector String*/
				IdS = Reg[0];
				Des = Reg[1];
                                Pre = Double.parseDouble(Reg [2]);
				

				Servicio objs;
				objs = new Servicio(IdS, Des, Pre);

				p1.Push(objs);//se apila

				Reg = objArch.LeerRegistro(4);//para que salga del ciclo
			}//fin mientras  
			objArch.CerrarArchivoModoLectura();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "**Archivo leído y cerrado correctamente**");
		}
		return p1;
	}//fin de copiar
        
        
        /*metodo que retorna un mensaje que indica si un dato es eliminado o no,
recibe las pilas y el id de turno que vamos a eliminar*/
public String LiberarServicio(Pila p1, Pila p2, String id)
{
String texto="No se encuentra, No se elimina el dato";//mensaje de retorno
Object dato;//para despilar
if(Buscar(p1,p2,id)==true)//encontró el id de turno buscado
{
    while(p1.IsEmpty()==false)//mientras hayan datos en pila
    {
        dato=p1.Pop();//desapilamos
        if(!id.equalsIgnoreCase(((Servicio)dato).getIdServicio()))
           p2.Push(dato);
        //Fin si 
    }//Fin mientras
    PasarPila(p2,p1);
    texto="Dato encontrado y eliminado "+id;
}//Fin si
return texto;
}//fin
        
     
/*Método que pasa los datos de la pila de turnos al archivo,
    al hacerlo borra los datos del archivo y crea uno nuevo con los datos
    de la pila--Joseph Sanchez*/
public void CopiarPilaArchivoTurnos(Archivos objArch, Pila p1, Pila p2)
{
    try {
        //primero se borra el contenido anterior del archivo
        objArch.BorrarContenido("Turnos.txt");
        //abrimos el archivo modo escritura
        JOptionPane.showMessageDialog(null,
                "" + objArch.AbrirArchivoModoEscritura("Turnos.txt"));

        Turno objt; 
        String linea;

        //desapilamos y guardamos en p2 para no perder los datos
        while (!p1.IsEmpty()) {

            objt = (Turno)p1.Pop(); 

            //Convertimos objeto Turno a una línea (Id;Descripcion;HoraInicio;HoraFin)
            linea = objt.EstructuraReg();

            //se escribe la línea en el archivo
            objArch.EscribirRegistro(linea);

            p2.Push(objt); //pasar a auxiliar
        }//fin mientras

        //restauramos pila original
        PasarPila(p2, p1);

        objArch.CerrarArchivoModoEscritura();
        JOptionPane.showMessageDialog(null,
                "Datos guardados correctamente en Turnos.txt");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
                "Error al guardar Turnos.txt");
    }
}//fin copiar a archivo turnos


//metodo que toma la pila de servicios, borra el archivo y pasa la pila sin repeticiones(Manejo de pilas)/
public void CopiarPilaArchivoServicio(Archivos objArch, Pila objp, Pila objpa) {
    try {

        objArch.BorrarContenido("Servicio.txt");
        JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoEscritura("Servicio.txt"));

        Pila temp = new Pila(100000); // pila temporal

        while (!objp.IsEmpty()) {

            Servicio servActual = (Servicio) objp.Pop();
            boolean repetido = false;

            // revisar repetidos en auxiliar
            while (!objpa.IsEmpty()) {
                Servicio s = (Servicio) objpa.Pop();
                if (s.getIdServicio() == servActual.getIdServicio()) {
                    repetido = true;
                }
                temp.Push(s);
            }

            // restaurar auxiliar
            while (!temp.IsEmpty()) {
                objpa.Push(temp.Pop());
            }

            // escribir solo si no es repetido
            if (!repetido) {
                String registro =
                    servActual.getIdServicio() + "," +
                    servActual.getDescripcionServicio() + "," +
                    servActual.getPrecioServicio();
                objArch.EscribirRegistro(registro);
            }

            // meter el actual
            objpa.Push(servActual);
        }

        // restaurar original
        while (!objpa.IsEmpty()) {
            objp.Push(objpa.Pop());
        }

        objArch.CerrarArchivoModoEscritura();
        JOptionPane.showMessageDialog(null, "Archivo guardado SIN repetidos.");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
} 
        
}
