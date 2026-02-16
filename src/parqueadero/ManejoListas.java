
package parqueadero;

import java.time.LocalDate;
import javax.swing.JOptionPane;


public class ManejoListas 
{
    
    /*metodo para crear una lista simple de vehiculos, recibe la lista y recibe
    una opcion, si la opcion es 1 se creará por inicio y si es 2 se creará por final*/
    public ListaSimple CrearLista(ListaSimple objl, int op, ListaDoble clientes)
    {
        int resp;
        Vehiculo objv;
        String pl;
        resp=JOptionPane.showConfirmDialog(null,"Ingresar vehiculo?","Lista simple de vehiculos",JOptionPane.YES_NO_OPTION);
        while(resp==JOptionPane.YES_OPTION)
        {
            objv=new Vehiculo();//para que sobreescriba
            pl=Validaciones.LeerString("Placa del vehículo: ");
            objv=objv.IngresarDatos(pl,clientes);//se ingresa un vehiculo
            if(op==1)//se creará por inicio
               objl.CrearPorInicio(objv);//se graba en la lista
            else//se crea por final
               objl.CrearPorFinal(objv);//se graba en la lista
            resp=JOptionPane.showConfirmDialog(null,"Ingresar otro vehiculo?","Lista simple de vehiculos",JOptionPane.YES_NO_OPTION);
        }//fin mientras
        return objl;
    }//fin crear
    
    
    /*metodo que busca por la placa en la lista simple de vehiculos,
 retorna verdadero si lo encuentra y ademas deja el apuntador
 p en el dato, y al apuntador t atras de p, y retorna falso en 
 caso de no encontrarlo*/
    public boolean Buscar(String pl, ListaSimple objls)
    {
       if(objls.IsEmpty()==false)//si hay datos
       {
         String pla;//variable que debemos usar para capturar la placa 
         objls.p=objls.getStart();//colocamos el apuntador al inicio
         pla=((Vehiculo)objls.p.getDato()).getPlaca();/*tomamos la placa del vehiculo
         que esta en el nodo para que no haga la pregunta con p en null!! esto
         NO es necesario en el algoritmo porque es un problema de programacion 
         de java no del analisis*/
         while(objls.p!=null&&!(pla.equalsIgnoreCase(pl)))
         {
             objls.t=objls.p;//ubicamos al apuntador t atras de p
             objls.p=objls.p.getSiguiente();//p adelanta en la lista
             if(objls.p!=null)/*si hay aun datos para tomar otro id, esto 
                 no lo tenemos que hacer en el algoritmo*/
                 pla=((Vehiculo)objls.p.getDato()).getPlaca();
         }//fin mientras
       }
       if(objls.p==null)//recorrio toda la lista y no lo encuentra
           return false;
       else//p quedo ubicada en la lista en el dato buscado
           return true;
    }//fin buscar
    
    /*Metodo que sirve para insertar un vehiculo antes de 
    cualquiera (deseado) en la lista simple de vehiculos*/
    public void InsertarAntes(String DatoInsertar, String DatoRef, ListaSimple objls, ListaDoble clientes)
    {
       Vehiculo objVeh=new Vehiculo();//ya no almacenamos solo un dato sino todo el vehiculo
        if(objls.IsEmpty()==false)
        {
            if(Buscar(DatoRef,objls)==false)//si no encuentra la placa referencia a buscar 
            {
                JOptionPane.showMessageDialog(null, "No se pudo insertar");
            }
            else//si encuentra la referencia
            {
                objVeh=objVeh.IngresarDatos(DatoInsertar,clientes);//se pide todos los datos de la placa a insertar
                if(objls.p==objls.getStart())//si se va a insertar de primero
                {
                    objls.CrearPorInicio(objVeh);//se inserta en Start
                }
                else//no está de primero el dato referencia
                {
                    objls.t.setSiguiente(new Nodo(objVeh, objls.p));//se inserta entre dos nodos señalados por t y p
                }//fin si
            }//fin si
        }//fin si
    }//fin de insertar antes 
    
     /*Metodo que sirve para insertar un vehiculo despues de 
    cualquiera (deseado) en la lista simple de vehiculos*/
    public void InsertarDespues(String DatoInsertar, String DatoRef, ListaSimple objls, ListaDoble clientes)
    {
       Vehiculo objVeh=new Vehiculo();//ya no almacenamos solo un dato sino todo el vehiculo
        if(objls.IsEmpty()==false)
        {
            if(Buscar(DatoRef,objls)==false)//si no encuentra la placa referencia a buscar 
            {
                JOptionPane.showMessageDialog(null, "No se pudo insertar");
            }
            else//si encuentra la referencia
            {
                objVeh=objVeh.IngresarDatos(DatoInsertar,clientes);//se pide todos los datos de la placa a insertar
                objls.UbicarUltimo();//para validar si esta de ultimo el dato referencia
                if(objls.p==objls.End)//si se va a insertar de ultimo
                {
                    objls.InsertarUltimo(objVeh);//se inserta de ultimo
                }
                else//no está de primero el dato referencia
                {
                    objls.p.setSiguiente(new Nodo(objVeh, objls.p.getSiguiente()));//se inserta entre dos nodos señalados 
                }//fin si
            }//fin si
        }//fin si
    }//fin de insertar despues
    
/*metodo que elimina un vehiculo de la lista simple de vehiculos*/    
public void LiberarDato(String plEliminar,ListaSimple objls)
{
    if(objls.IsEmpty()==false)//hay datos
    {
        if(Buscar(plEliminar,objls)==false)
            JOptionPane.showMessageDialog(null,"Dato a eliminar no existe en la lista");
        else//si existe
        {
            objls.Liberar(objls);
            JOptionPane.showMessageDialog(null,"Dato eliminado de la lista "+plEliminar);
        }//fin si
        
    }//fin si
   
}//fin liberar
    
/*metodo que copia todo el contenido del archivo a la lista simple por final, tambien 
lo puede cambiar a por inicio y retorna la lista con los datos del archivo*/  
public ListaSimple CopiarArchivoListaSimple(Archivos objArch, ListaSimple objls) 
    {      
        String cadena = "";
        try {
            //locales auxiliares para extraer la informacion del archivo
            String pl,TiVe, Mar, Co,id;
            int Mod;
            boolean Est;
           
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Vehiculos.txt"));
            //se invoca al metodo de leer registro con 6 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(7);
            //mientras existan datos en el archivo
            while (Reg!=null) //mientras not EOF()
            {
               /*los datos del Reg que se obtiene del archivo plano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                pl = Reg[0];
                id= Reg[1];
                TiVe= Reg[2] ;
                Mar =Reg[3] ;
                Co =Reg[4] ;
                Mod= Integer.parseInt(Reg[5]);
                Est = Boolean.parseBoolean(Reg[6]);
                                
                Vehiculo objv;
                objv=new Vehiculo(pl,id,TiVe,Mar,Co,Mod,Est);
                //aca se copia a la lista simple el vehiculo que leemos del archivo
                objls.CrearPorFinal(objv);//se puede objls.CrearPorInicio(objv); 
                               
                Reg = objArch.LeerRegistro(7);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return objls;
     }//fin de mostrar todo el archivo

/*mostrar un listado de los vehiculos de acuerdo a su estado*/
public String ListarPorEstado(ListaSimple objls,boolean est )
{
String texto="";//variable local del retorno
Nodo s;//apuntador local
//se puede usar los apuntadores tambien de la objls
if(objls.IsEmpty()==false)//hay datos
{
   s=objls.getStart();//colocamos el apuntador al inicio de la lista
   while(s!=null)//mientras de recorrido
   {
      if(((Vehiculo)s.getDato()).getEstado()==est)
      {
	texto=texto+s.getDato().toString()+"\n";
      }//fin si
      s=s.getSiguiente();//adelantamos en la lista
   }//fin mientras
}//fin si
return texto;
}//fin listar  

/*mostrar un listado de los vehiculos de acuerdo a un atributo que puede ser:
Tipo, Marca o modelo*/
public String ListarPorAtributo(ListaSimple objls,int op,Object atrib)
{
String texto="";//variable local del retorno
Nodo s;//apuntador local
//se puede usar los apuntadores tambien de la objls
if(objls.IsEmpty()==false)//hay datos
{
   s=objls.getStart();//colocamos el apuntador al inicio de la lista
   while(s!=null)//mientras de recorrido
   {
      if(op==7&&((Vehiculo)s.getDato()).getTipo().equalsIgnoreCase((String)atrib))
      {//listar por tipo
	texto=texto+s.getDato().toString()+"\n";
      }
      else
      {//listar por marca
          if(op==8&&((Vehiculo)s.getDato()).getMarca().equalsIgnoreCase((String)atrib))
          {
	     texto=texto+s.getDato().toString()+"\n";
          }
          else
              if(op==9&&((Vehiculo)s.getDato()).getModelo()==((int)atrib))
                {//listar por modelo
                  texto=texto+s.getDato().toString()+"\n";
                }//fin si
          //fin si
      }//fin si
      s=s.getSiguiente();//adelantamos en la lista
   }//fin mientras
}//fin si
return texto;
}//fin listar 





/*Metodo que se encarga de analizar y recorrer toda la lista en busca de el tipo de 
de vehiculo para ser eliminados*/
public String EliminarTipoVehiculo(ListaSimple objls,String tipoV){
    Nodo x; //Apuntador
    String texto= "";
    if(objls.IsEmpty() == false)//Hay lista
    {
        x = objls.getStart();//Se inicia el apuntador al start
        while(x != null)//Minetras x sea diferente de null
        {
            /*Condicional que comparar los tipos de vehiculos */
            if(((Vehiculo)x.getDato()).getTipo().equalsIgnoreCase(tipoV))
            {
                texto = texto + x.getDato() + "\n";
                //Se elimina el dato de la lista que sea de tipoV
                LiberarDato(((Vehiculo)x.getDato()).getPlaca(), objls);
            }//Finsi
            x = x.getSiguiente();//se adelanta en la lista
        }//fin mientras
    }//fin si
    return texto;
}//fin eliminar

/*metodo que acumula el modelo de los vehiculos para promediar*/
public int AcumularModelos(ListaSimple objls)
{
    int TotalAcum=0;//variable local del retorno
    if(objls.IsEmpty()==false)//hay datos
    {
        objls.q=objls.getStart();//se inicia desde el primer vehiculo
        while(objls.q!=null)
        {
            TotalAcum=TotalAcum+((Vehiculo)objls.q.getDato()).getModelo();//se acumula el modelo
            objls.q=objls.q.getSiguiente();//se adelanta en la lista
        }//fin mientras
        
    }//fin si
    return TotalAcum;
}//fin de acumular

/*metodo que retorna un promedio, recibe un entero y un real y retorna el promedio
en caso de poder hacer la division*/

public int PromediarModelo(ListaSimple objls)
{
    int SModelos, TotalE;//locales para los resultados
    SModelos=AcumularModelos(objls);//se recibe el numero de elementos
    TotalE=objls.ContarElementos();//se recibe el acumulador de modelos
    if(TotalE>=1)//si se puede hacer el promedio
        return SModelos/TotalE;
    else
        return 0;
    //fin si
    
}//fin de promediar


/*joseph sanchez metodo que busca el vehiculo y de ser encontrado se puede actualizar todos los campos
  menos el id y la placa*/
//FALTO EL CAMBIO DE LA CEDULA DEL DUEÑO...LO CORREGÍ
public void actualizarVehiculo(ListaSimple vehiculos, String placa, ListaDoble Clientes)
{   //Verificamos que el vehiculo existe en la lista simple
    if(Buscar(placa,vehiculos))
    {
        // Traemos el objeto Vehiculo apuntado por p
        Vehiculo v = (Vehiculo) vehiculos.p.getDato();

        // Variables inicializadas con los valores actuales
        String propietario=v.getIdDue();
        String tipo = v.getTipo();
        String marca = v.getMarca();
        String color = v.getColor();
        Boolean estado = v.getEstado();
        int modelo = v.getModelo();

        int op;
        int resp;

        do{
            //menu de atributos a actualizar (muestra valores actuales)
            op = Validaciones.LeerInt(
                "Menu actualización\n" +
                "\nPlaca: " + v.getPlaca() +
                "\n1. Cédula del dueño: " + propietario+        
                "\n2. Tipo: " + tipo +
                "\n3. Marca: " + marca +
                "\n4. Color: " + color +
                "\n5. Modelo: " + modelo +
                "\n6. Estado: " + estado +
                "\n7. Terminar cambio");

            switch(op)
            {//En caso de (op)
                case 1:
                    propietario=Validaciones.ValidarCliente(Clientes);
                    break;
                case 2:    
                    tipo = Validaciones.LeerTipoVehiculo();
                    break;
                case 3:
                    marca = Validaciones.LeerString("Ingrese la marca del vehiculo: ");
                    break;
                case 4:
                    color = Validaciones.LeerString("Ingrese el color del vehiculo: ");
                    break;
                case 5:
                    modelo = Validaciones.LeerInt("Ingrese el modelo del vehiculo: ");
                    break;
                case 6:
                    estado = Validaciones.LeerBoolean("Ingrese el estado del vehiculo (True/False):");
                    break;
                case 7:
                    break;
               
            }//Fin switch
        } while(op < 7);//fin while del menu de opciones

        // Preguntar si desea guardar los cambios
        resp = JOptionPane.showConfirmDialog(null, "¿Actualizar datos?", "Actualizar", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION)//si quiere guardar los cambios
        {
            // Se actualizan los atributos del objeto directamente
            v.setIdDue(propietario);
            v.setTipo(tipo);
            v.setMarca(marca);
            v.setColor(color);
            v.setModelo(modelo);
            v.setEstado(estado);

            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se realizaron cambios al vehículo " + placa);
        }
    }
    else
    {
        JOptionPane.showMessageDialog(null, "El vehiculo no se encuentra registrado en la lista: " + placa);
    }//fin if Buscar
}//fin actualizar



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////  L I S T A  D O B L E   D E  C L I E N T E S /////////////////////////////////////////////



 /*metodo para crear una lista doble de clientes, recibe la lista y recibe
    una opcion, si la opcion es 1 se creará por inicio y si es 2 se creará por final*/
    public ListaDoble CrearLista(ListaDoble objl, int op)
    {
        int resp;
        Cliente objCli;
        String id;
        resp=JOptionPane.showConfirmDialog(null,"Ingresar Cliente?","Lista doble de clientes",JOptionPane.YES_NO_OPTION);
        while(resp==JOptionPane.YES_OPTION)
        {
            objCli=new Cliente();//para que sobreescriba
            id=Validaciones.LeerString("id del usuario: ");
            objCli=objCli.IngresarDatos(id);//se ingresa un cliente
            
            if(op==1)//se creará por inicio
               objl.CrearPorInicio(objCli);//se graba en la lista
            else//se crea por final
               objl.CrearPorFinal(objCli);//se graba en la lista
            resp=JOptionPane.showConfirmDialog(null,"Ingresar otro cliente?","Lista simple de clientes",JOptionPane.YES_NO_OPTION);
        }//fin mientras
        return objl;
    }//fin crear

    /*metodo que busca por id del cliente en lista doble de clientes,
 retorna verdadero si lo encuentra y ademas deja el apuntador
 p en el dato y retorna falso en caso de no encontrarlo*/
    public boolean Buscar(String id, ListaDoble objld)
    {
       if(objld.IsEmpty()==false)//si hay datos
       {
         String idu;//variable que debemos usar para capturar el id
         objld.p=objld.getStart();//colocamos el apuntador al inicio
         idu=((Cliente)objld.p.getDato()).getIdUser();/*tomamos el id del cliente
         que esta en el nodo para que no haga la pregunta con p en null!! esto
         NO es necesario en el algoritmo porque es un problema de programacion 
         de java no del analisis*/
         while(objld.p!=null&&!(idu.equalsIgnoreCase(id)))
         {
             objld.p=objld.p.getSiguiente();//p adelanta en la lista
             if(objld.p!=null)/*si hay aun datos para tomar otro id, esto 
                 no lo tenemos que hacer en el algoritmo*/
                 idu=((Cliente)objld.p.getDato()).getIdUser();
         }//fin mientras
       }
       if(objld.p==null)//recorrio toda la lista y no lo encuentra
           return false;
       else//p quedo ubicada en la lista en el dato buscado
           return true;
    }//fin buscar

    
      /*metodo que recibe la placa del vehiculo y retorna su dueño en caso de existir*/
    public Object BuscarDuePlaca(String placa, ListaSimple objls, ListaDoble clien){
       
        Object cliente=null;//variable del retorno
        String cc;//cedula del dueño
        if (objls.IsEmpty() == false)//hay datos en vehiculos
        {
            if(Buscar(placa, objls)==true)//se busca la placa en la lista de vehiculos, si se encuentra
            {
                cc = ((Vehiculo)objls.p.getDato()).getIdDue();//se extrae de vehiculo la cc del dueño
                if(Buscar(cc, clien)==true)//se busca la cedula en la lista de clientes
                {
                    cliente=clien.p.getDato();//se guarda en la variable del retorno
                }//fin si
                
            }//fin si
        }//fin si
        return cliente; 
    }
    
    
    
public void InsertarDespues(String idRef, String idIns, ListaDoble objld)
{
Cliente objcli=new Cliente();//para el ingreso del dato a insertar
if(objld.IsEmpty()==false)//hay datos
{
 if(Buscar(idRef,objld)==false)
      JOptionPane.showMessageDialog(null,"Dato referencia no existe en la lista, No se inserta");
 else //encontro el dato referencia
 {
       objcli=objcli.IngresarDatos(idIns);  //se piden los datos del cliente a insertar
       if(objld.p.getSiguiente()==null)//se inserta de ultimo
	      objld.InsertarUltimo(objcli);//se invoca el metodo para insertar de ultimo
       else{//no está de ultimo, es entre dos nodos
             objld.p.setSiguiente(new Nodo(objld.p, objcli, objld.p.getSiguiente()));
             objld.p.getSiguiente().getSiguiente().setAnterior(objld.p.getSiguiente());
           }//fin si
       JOptionPane.showMessageDialog(null,"Dato "+idIns+" se inserto despues de "+idRef+" en la lista"); 
 }//fin si
}//fin si
}//fin insertar
  

/*metodo que elimina un cliente de la lista doble de clientes*/    
public void LiberarDato(String idEliminar,ListaDoble objld)
{
    if(objld.IsEmpty()==false)//hay datos
    {
        if(Buscar(idEliminar,objld)==false)
            JOptionPane.showMessageDialog(null,"Dato a eliminar no existe en la lista");
        else//si existe
        {
            if(objld.p==objld.getStart())//si el dato esta de primero o es solo un dato
            {
                objld.LiberarPrimero();//de primero y se borra
            }
            else
            {
                if(objld.p.getSiguiente()==null)//el dato esta de ultimo
                    objld.LiberarUltimo();
                else//el dato esta en medio de dos nodos
                {
                    objld.p.getAnterior().setSiguiente(objld.p.getSiguiente());
                    objld.p.getSiguiente().setAnterior(objld.p.getAnterior());
                    objld.p.finalize();
                }//fin si
            }//fin si
            JOptionPane.showMessageDialog(null,"Dato eliminado de la lista "+idEliminar);
        }//fin si
        
    }//fin si
   
}//fin liberar

//metodo que copia la informacion del archivo a la lista doble de clientes
    public ListaDoble CopiarArchivoListaDoble(Archivos objArch, ListaDoble objld)
    {      
        String cadena = "";
        try {
           //locales auxiliares para extraer la informacion del archivo
            String IdU, Nom, Ape,Cel,Em ,Dire;
            
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Clientes.txt"));
            //se invoca al metodo de leer registro con 6 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(6);
            //mientras existan datos en el archivo
            while (Reg!=null&& Reg[0]!=null) //mientras not EOF()
            {
               /*los datos del Reg que se obtiene del archivo plano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                IdU = Reg[0];
                Nom= Reg[1];
                Ape= Reg[2] ;
                Cel= Reg[3] ;
                Em =Reg[4] ;
                Dire= Reg[5];
                
                Cliente objc;
                objc=new Cliente(IdU,Nom,Ape,Cel,Em,Dire);
                
                objld.CrearPorFinal(objc);//se copia en la lista por final, se puede por inicio tambien
                                
                Reg = objArch.LeerRegistro(6);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return objld;
     }//fin de mostrar todo el archivo
  

/*metodo que retorna el vehiculo de menor
modelo de una lista*/
public Vehiculo vehiculoMenor(ListaSimple objls)
{
    Vehiculo vehiculoMenor=new Vehiculo();
  //inicializamos las variables que necesitaremos
   if (objls.IsEmpty() == false)//hay lista
  {
    vehiculoMenor = (Vehiculo) objls.getStart().getDato(); 
    Nodo s = objls.getStart().getSiguiente();
    while (s!= null)
      {   /*recorremos y comparamos cada modelo de la lista
          simple de vehiculos*/
          if (((Vehiculo)s.getDato()).getModelo()< vehiculoMenor.getModelo())
          {
             vehiculoMenor = (Vehiculo)s.getDato();
          
          }//fin si
          s = s.getSiguiente();
      }//fin mientras
}//fin si
  return vehiculoMenor;
}
 
/*metodo que retorna el nombre del cliente con el mas antiguo
modelo de vehiculo*/
public String mostrarMenorC(ListaDoble objld, ListaSimple objls) 
{
    Vehiculo vehiculo = vehiculoMenor(objls);//se invoca al metodo que retorna el mas antiguo
    String idUser = vehiculo.getIdDue();//se toma la cc del dueño para buscarlo en la lista de clientes
        //verificar que el id se encuentre en la lista de clientes
    if (Buscar(idUser, objld)==true) //si el dueño del carro se encuentra en la lista se toma el nombre
    {
        return ((Cliente)objld.p.getDato()).getNombre()+" "+((Cliente)objld.p.getDato()).getApellido();
    }//fin si
   
    return "por alguna razón no tenemos la información de "+idUser;
}//fin mostrarMenorC

/*Metodo que busca y retorna los vehculos de un clente, recibe
      el id del cliente y la lista de vehiculos*/
    public String ConcatenarVehiculosCliente(String id, ListaSimple objlV)
    {
        String idD,texto="";//variable para retorno
        if(objlV.IsEmpty()==false)//si hay datos
        {
            Nodo temp=objlV.getStart();//colocamos el apuntador al inicio

            while(temp != null)//mientras no sea nulo
            {
                //comprobar que el dato no sea null
                if (temp.getDato() != null) {
                    idD=((Vehiculo)temp.getDato()).getIdDue();//leemos el id
                    if(idD.equalsIgnoreCase(id))
                    {
                        texto=texto+((Vehiculo)temp.getDato()).toString()+"\n";
                    }
                }
                temp=temp.getSiguiente();//avanzamos al siguiente
            }
        }
        return texto;
    }

    /*Metodo que busca para TODOS  los clientes sus vehiculos y retorna este listado, recibe
      el id del cliente y la lista de vehiculos y la lista doble de clientes*/
    public String ConcatenarVehiculosYClientes(ListaSimple objlV, ListaDoble objlC)
    {
     String id;   
     String texto="";
     if(objlC.IsEmpty()==false)
     {
        Nodo temp= objlC.getStart();//nodo temporal
        String Todo="";
        while(temp!=null)
        {
            id=((Cliente)temp.getDato()).getIdUser();//obtener id
            texto=temp.toString();//se guardan los datos del dueño
            texto=texto+"\n"+ConcatenarVehiculosCliente(id,objlV);//se guardan los vehiculos del dueño
            Todo=Todo+texto+"\n";
            temp=temp.getSiguiente();//se adelanta
        }
        JOptionPane.showMessageDialog(null,"Cliente(s) y sus Vehiculo(s): \n"+"\n"+Todo);//Mostrar todo junto
    }//fin si
    return texto;
    }

/*Método que actualiza la información de un cliente si el id buscado
está en la lista doble de clientes*/
public ListaDoble ActualizarCliente(ListaDoble lista, String id) {
    if (Buscar(id, lista)) { //Buscar cliente en la lista doble
        Cliente cli = (Cliente) lista.p.getDato(); //Apuntador al dato encontrado

        // Variables locales con los datos actuales
        String nom = cli.getNombre();
        String ape = cli.getApellido();
        String cel = cli.getCelular();
        String email = cli.getEmail();
        String dir = cli.getDireccion();
        int op;
        int resp;

        // Menú de actualización
        do {
            op = Validaciones.LeerInt("MENÚ DE ACTUALIZACIÓN CLIENTE\n" +
                    "\nId cliente: " + id +
                    "\n1. Nombre: " + nom +
                    "\n2. Apellido: " + ape +
                    "\n3. Celular: " + cel +
                    "\n4. Email: " + email +
                    "\n5. Dirección: " + dir +
                    "\n6. Terminar cambios");

            switch (op) {
                case 1:
                    nom = Validaciones.LeerString("Nuevo nombre del cliente:");
                    break;
                case 2:
                    ape = Validaciones.LeerString("Nuevo apellido del cliente:");
                    break;
                case 3:
                    cel = Validaciones.LeerString("Nuevo celular del cliente:");
                    break;
                case 4:
                    email = Validaciones.LeerString("Nuevo email del cliente:");
                    break;
                case 5:
                    dir = Validaciones.LeerString("Nueva dirección del cliente:");
                    break;
            }

        } while (op < 6);

        // Confirmar los cambios
        resp = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?", "Confirmar actualización", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            cli.setNombre(nom);
            cli.setApellido(ape);
            cli.setCelular(cel);
            cli.setEmail(email);
            cli.setDireccion(dir);
            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No se realizaron cambios.");
        }

    } else {
        JOptionPane.showMessageDialog(null, "El cliente con id " + id + " no existe.");
    }

    return lista;
}//fin
    


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////L I S T A   D O B L E   E M P L E A D O S /////////////////////////////////////////////


/*metodo para crear una lista doble de empleados, recibe la lista y recibe
    una opcion, si la opcion es 1 se creará por inicio y si es 2 se creará por final*/
    public ListaDoble CrearListaEmpleado(ListaDoble objl, int op, Pila p1)
    {
        int resp;
        Empleado objEm;
        String id;
        resp=JOptionPane.showConfirmDialog(null,"Ingresar Empleados?","Lista doble de empleados",JOptionPane.YES_NO_OPTION);
        while(resp==JOptionPane.YES_OPTION)
        {
            objEm=new Empleado();//para que sobreescriba
            id=Validaciones.LeerString("id del empleado: ");
            objEm=objEm.IngresarDatos(id,p1);//se ingresa un empleado
            if(op==1)//se creará por inicio
               objl.CrearPorInicio(objEm);//se graba en la lista
            else//se crea por final
               objl.CrearPorFinal(objEm);//se graba en la lista
            resp=JOptionPane.showConfirmDialog(null,"Ingresar otro empleado?","Lista doble de empleados",JOptionPane.YES_NO_OPTION);
        }//fin mientras
        return objl;
    }//fin crear
    
     
    /*metodo que busca por id de empleados en lista doble de empleados,
 retorna verdadero si lo encuentra y ademas deja el apuntador
 p en el dato y retorna falso en caso de no encontrarlo*/
    public boolean BuscarEmpleado(String id, ListaDoble objld)
    {
       if(objld.IsEmpty()==false)//si hay datos
       {
         String idu;//variable que debemos usar para capturar el id
         objld.p=objld.getStart();//colocamos el apuntador al inicio
         idu=((Empleado)objld.p.getDato()).getIdUser();/*tomamos el id del empleado
         que esta en el nodo para que no haga la pregunta con p en null!! esto
         NO es necesario en el algoritmo porque es un problema de programacion 
         de java no del analisis*/
         while(objld.p!=null&&!(idu.equalsIgnoreCase(id)))
         {
             objld.p=objld.p.getSiguiente();//p adelanta en la lista
             if(objld.p!=null)/*si hay aun datos para tomar otro id, esto 
                 no lo tenemos que hacer en el algoritmo*/
                 idu=((Empleado)objld.p.getDato()).getIdUser();
         }//fin mientras
       }
       if(objld.p==null)//recorrio toda la lista y no lo encuentra
           return false;
       else//p quedo ubicada en la lista en el dato buscado
           return true;
    }//fin buscar    

       /*Metodo que  busca un dato referente, si lo encuentra, insertara en la lista
    despues de ese dato*/
    public void InsertarDespuesEmpleado(String idRef, String idIns, ListaDoble objld, Pila p1)
{
Empleado objEm=new Empleado();//para el ingreso del dato a insertar
if(objld.IsEmpty()==false)//hay datos
{
 if(BuscarEmpleado(idRef,objld)==false)
      JOptionPane.showMessageDialog(null,"Dato referencia no existe en la lista, No se inserta");
 else //encontro el dato referencia
 {
       objEm=objEm.IngresarDatos(idIns,p1);  //se piden los datos del cliente a insertar
       if(objld.p.getSiguiente()==null)//se inserta de ultimo
	      objld.InsertarUltimo(objEm);//se invoca el metodo para insertar de ultimo
       else{//no está de ultimo, es entre dos nodos
             objld.p.setSiguiente(new Nodo(objld.p, objEm, objld.p.getSiguiente()));
             objld.p.getSiguiente().getSiguiente().setAnterior(objld.p.getSiguiente());
           }//fin si
       JOptionPane.showMessageDialog(null,"Dato "+idIns+" se inserto despues de "+idRef+" en la lista"); 
 }//fin si
}//fin si
}//fin insertar
    
   public void LiberarDatoEmpleado(String idEliminar,ListaDoble objld)
{
    if(objld.IsEmpty()==false)//hay datos
    {
        if(BuscarEmpleado(idEliminar,objld)==false)
            JOptionPane.showMessageDialog(null,"Dato a eliminar no existe en la lista");
        else//si existe
        {
            objld.Liberar(objld);
            JOptionPane.showMessageDialog(null,"Dato eliminado de la lista "+idEliminar);
        }//fin si
        
    }//fin si
   
}//fin liberar
  
    
    //metodo que copia la informacion del archivo a la lista doble de Empleados
    public ListaDoble CopiarArchivoListaDobleEmpleado(Archivos objArch, ListaDoble objld)
    {      
        String cadena = "";
        try {
           //locales auxiliares para extraer la informacion del archivo
            String IdU, Nom, Ape,Cel,Em ,Dire, car, idTurLa;
            
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Empleado.txt"));
            //se invoca al metodo de leer registro con 8 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(8);
            //mientras existan datos en el archivo
            while (Reg!=null&& Reg[0]!=null) //mientras not EOF()
            {
               /*los datos del Reg que se obtiene del archivo plano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                IdU = Reg[0];
                Nom= Reg[1];
                Ape= Reg[2] ;
                Cel= Reg[3] ;
                Em =Reg[4] ;
                Dire= Reg[5];
                car = Reg[6];
                idTurLa = Reg[7];
                
                Empleado obje;
                obje=new Empleado(IdU,Nom,Ape,Cel,Em,Dire, car, idTurLa);
                
                objld.CrearPorFinal(obje);//se copia en la lista por final, se puede por inicio tambien
                                
                Reg = objArch.LeerRegistro(8);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "**Archivo leído y cerrado correctamente**");
        }
        return objld;
     }//fin de mostrar todo el archivo   
 
  /*Método que pasa los datos de la lista doble de empleados al archivo,
    al hacerlo borra los datos del archivo y crea uno nuevo con los datos
    de la lista doble*/
    public void CopiarListaDobleArchivoEmpleado(Archivos objArch, ListaDoble objldEmp) {
    try {
        // Primero se borra todo el contenido anterior del archivo
        objArch.BorrarContenido("Empleado.txt");
        // Abrir el archivo en modo escritura (sobrescribe el contenido anterior)
        JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoEscritura("Empleado.txt"));
        Nodo aux = objldEmp.getStart();//Primer nodo de la lista
        // Recorrer toda la lista doble
        while (aux != null)//mientras haya datos
        {
            Empleado emp = (Empleado) aux.getDato();//obtener el objeto empleado
            // Construir una línea con todos los campos separados por comas
            String registro = emp.getIdUser() + "," +
                              emp.getNombre() + "," +
                              emp.getApellido() + "," +
                              emp.getCelular() + "," +
                              emp.getEmail() + "," +
                              emp.getDireccion() + "," +
                              emp.getCargo() + "," +
                              emp.getIdTurnoLaboral();
            // Escribir el registro completo en el archivo
            objArch.EscribirRegistro(registro);
            // Pasar al siguiente nodo
            aux = aux.getSiguiente();
        }
        // Cerrar el archivo
        objArch.CerrarArchivoModoEscritura();
        JOptionPane.showMessageDialog(null, "Archivo guardado correctamente (reescrito desde lista).");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
    }
}
    
/*metodo para cambiar cargo o turno de un empleado*/
public void CambiosEspecificos(ListaDoble objldEmp, String id, int op,Pila p1)    
{
    
String atri="";
Empleado objEm=new Empleado();//para el ingreso del dato a insertar
if(objldEmp.IsEmpty()==false)//hay datos
{
    if(BuscarEmpleado(id,objldEmp)==false)
         JOptionPane.showMessageDialog(null,"Empleado no existe en la lista");
    else //encontro el dato referencia
    {
        JOptionPane.showMessageDialog(null, "El empleado es:\n"+objldEmp.p.toString());
        if(op==3)//cambiar cargo
             atri=Validaciones.LeerCargo();  
        else
        {
            if (op==4)//cambiar turno
                atri=Validaciones.ValidarTurno(p1);
            //fin si
        }//fin si
        int resp=JOptionPane.showConfirmDialog(null,"Esta seguro del cambio de "+atri,"Cambios específicos",JOptionPane.YES_NO_OPTION);
        if(resp==JOptionPane.YES_OPTION)
        {
            if(op==3)//cambiar cargo
                ((Empleado)objldEmp.p.getDato()).setCargo(atri);  
        else
        {
            if (op==4)//cambiar turno
                ((Empleado)objldEmp.p.getDato()).setIdTurnoLaboral(atri);
            //fin si
        }//fin si
        JOptionPane.showMessageDialog(null, "Se cambió al empleado "+atri);     
        }//fin si
   }//fin si
   }//fin si
}//fin cambios especificos


/*mostrar un listado de los empleados de acuerdo a un atributo que puede ser:
    cargo o turno*/
public String ListarPorAtributoEmp(ListaDoble objld,Pila objp, int op,Object atrib)
{
String texto="";//variable local del retorno
Nodo x = null;//apuntador local

if(objld.IsEmpty()==false)//hay datos
{
   x=objld.getStart();//colocamos el apuntador al inicio de la lista
   
   Pila objpa = new Pila(10000);
   ManejoPila objmp = new ManejoPila();
   Object dato = null; //auxiliar
   
   while(x!=null)//recorrido
   {
      
      //listar por cargo
          if(op==8&&((Empleado)x.getDato()).getCargo().equalsIgnoreCase((String)atrib))
          {
	     texto=texto+x.getDato().toString()+"\n";
          }//fin si
          else{
              if(op == 9)
                {
 
                    while (objp.IsEmpty() == false) //recorremos la pila
                    {
                        dato = objp.Pop();
                        objpa.Push(dato);

                        if ( ((Turno)dato).getIdTurno().equalsIgnoreCase( ((Empleado)x.getDato()).getIdTurnoLaboral() ) //comparamos el id del turno de pila con el del empleado
                            && ((Turno)dato).getDescripcionTurno().equalsIgnoreCase((String)atrib))
                        {
                            texto = texto + ((Empleado)x.getDato()).toString() + "\n";
                        }
                     
                    }
                    
                    objmp.PasarPila(objpa, objp);
                }//fin si
          }//fin si
       x= x.getSiguiente();//adelantamos en la lista
   }//fin mientras
}//fin si

//Si no encuentra ningun dato para listar
if( texto.equals("") && op == 8){
    JOptionPane.showMessageDialog(null, "No se encuentró ningun empleado con el cargo: "+atrib);
}
if( texto.equals("") && op == 9){
    JOptionPane.showMessageDialog(null, "No se encuentró ningun empleado con el turno: "+atrib);
   
}
return texto;
}//fin listar  






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////M A N E J O    D E   L I S T A   D O B L E   D E   D E T A L L E   D E  S E R V I C I O S////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public ListaDoble CrearListaDetalleServicio(ListaDoble objld, ListaSimple LSVehi, Cola objc,Pila pser) {
        int resp;
        DetalleServicio objDS;
        String id;
        resp = JOptionPane.showConfirmDialog(null, "Ingresar detalle de servicio?", "Lista doble de detalles de servicio", JOptionPane.YES_NO_OPTION);
        while (resp == JOptionPane.YES_OPTION) {
            objDS = new DetalleServicio();
            id = Validaciones.LeerString("ID del detalle de servicio: ");
            objDS = objDS.IngresarDatos(id, LSVehi,objc,pser);
            objld.CrearPorFinal(objDS);
            resp = JOptionPane.showConfirmDialog(null, "Ingresar otro detalle de servicio?", "Lista doble de detalles de servicio", JOptionPane.YES_NO_OPTION);
        }
        return objld;
    }

    /* Método que busca por id del detalle de servicio en lista doble */
    public boolean BuscarDetalleServicio(String id, ListaDoble objld) {
        if (objld.IsEmpty() == false) {
            String idServicio;
            objld.p = objld.getStart();
            idServicio = ((DetalleServicio) objld.p.getDato()).getServicioId();
            while (objld.p != null && !(idServicio.equalsIgnoreCase(id))) {
                objld.p = objld.p.getSiguiente();
                if (objld.p != null)
                    idServicio = ((DetalleServicio) objld.p.getDato()).getServicioId();
            }
        }
        if (objld.p == null)
            return false;
        else
            return true;
    }

    /* Método que elimina un detalle de servicio de la lista doble */
    public void LiberarDetalleServicio(String idEliminar, ListaDoble objld) {
        if (objld.IsEmpty() == false) {
            if (Buscar(idEliminar, objld) == false)
                JOptionPane.showMessageDialog(null, "Dato a eliminar no existe en la lista");
            else {
                objld.Liberar(objld);
                JOptionPane.showMessageDialog(null, "Dato eliminado de la lista " + idEliminar);
            }
        }
    }

    /* Método que copia la información del archivo a la lista doble de detalles de servicio */
    public ListaDoble CopiarArchivoListaDobleDetalleServicio(Archivos objArch, ListaDoble objld) {
        try {
            String servicioId, Placa, IdCelda,ids, horaInicio, horaFin;
            String FechaServicio;
            String Reg[];
            
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("DetalleServicios.txt"));
            Reg = objArch.LeerRegistro(7);
            
            while (Reg != null && Reg[0] != null) {
               servicioId = Reg[0];
                Placa = Reg[1];
                IdCelda= Reg[2];
                ids=Reg[3];
                horaInicio = Reg[4];
                horaFin = Reg[5];
                FechaServicio = Reg[6];
                
                DetalleServicio objds;
                objds = new DetalleServicio(servicioId, Placa, IdCelda,ids, horaInicio, horaFin, java.time.LocalDate.parse(FechaServicio));
                objld.CrearPorFinal(objds);
                
                Reg = objArch.LeerRegistro(6);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return objld;
    }
    
    /* Método para actualizar un detalle de servicio en la lista doble */
public ListaDoble Actualizar(ListaDoble objldDS, String id, Cola objc, Pila pser, ListaSimple lsv)
{
    
    int op, resp;
    String Pl, IdC,ids, HoraI, HoraS;
    LocalDate FeS;
    
    if(objldDS.IsEmpty() == false)
    {
        if (Buscar(id, objldDS) == true) {
            
            DetalleServicio objDS_actual = (DetalleServicio) objldDS.p.getDato();
            
            //se inicializan las auxiliares con los datos del objeto desencolado y encontrado
            Pl=objDS_actual.getPlaca();
            ids=objDS_actual.getIdServicio();
            HoraI=objDS_actual.getHoraInicio();
            HoraS=objDS_actual.getHoraFin();
            FeS=objDS_actual.getFechaServicio();
            IdC=objDS_actual.getIdCelda();
            
	do{//mientras para el menu de cambio
	  op=Validaciones.LeerInt("Menu actualización\n"+
		     "\nID Servicio: "+id+
                     "\n1. PlacaVehiculo: "+Pl+
	             "\n2. Hora de inicio: "+HoraI+
                     "\n3. Hora de fin: "+HoraS+
                     "\n4. Fecha del servicio: "+FeS+
                     "\n5. Celda: "+IdC+
                     "\n6. id Servicio: "+ids+
                     "\n7. Terminar cambios");
	switch(op)
        {//En caso de (op)
                case 1:Pl=Validaciones.ValidarVehiculo(lsv);
                       break;
             	case 2:HoraI=Validaciones.LeerHora("Hora de ingreso al servicio ");
                       break;
		case 3:HoraS=Validaciones.LeerHora("Hora de salida del servicio ");
                       break;  
                case 4:FeS=Validaciones.leerFecha("Nueva fecha de servicio");
                       break;
                case 5:IdC=Validaciones.ValidarCelda(objc);
                       break;
                case 6:ids=Validaciones.ValidarIdServicio(pser);
                       break;  
        }//Fin caso
        }while(op<7);//fin mientras del menu de opciones		
	resp=JOptionPane.showConfirmDialog(null,"Actualizar datos?","Actualizar",JOptionPane.YES_NO_OPTION);
	if(resp==JOptionPane.YES_OPTION)//si quiere guardar los cambios
            {//se actualizan
             objDS_actual.setPlaca(Pl);
             objDS_actual.setHoraInicio(HoraI);
             objDS_actual.setHoraFin(HoraS);
             objDS_actual.setFechaServicio(FeS);
             objDS_actual.setIdCelda(IdC);
             objDS_actual.setIdServicio(ids);
             JOptionPane.showMessageDialog(null,"datos actualizados con éxito");
            }//fin si
        
            // Nota: Al modificar objDS_actual, se modifica automáticamente el dato en el nodo 
            // porque son la misma referencia de objeto.
            
        } else {
            // No se encontró el nodo
            JOptionPane.showMessageDialog(null, "El ID del detalle de servicio (" + id + ") no se encontró en la lista.");
        }
    } else {
        // La lista está vacía
        JOptionPane.showMessageDialog(null, "La lista de detalles de servicio está vacía. No se puede actualizar.");
    }
    return objldDS;
}

    /* Método que retorna una cadena con las celdas ocupadas (Estado == false)
       Recibe la cola original y una cola auxiliar para no perder datos
    */
    public String ListarCeldasOcupadas(Cola objc, Cola objca) {
        String texto = ""; // texto de retorno
        Object dato; // para desencolar

        // Recorremos la cola original, desencolamos y guardamos en la auxiliar
        while (objc.IsEmpty() == false) {
            dato = objc.Pop();
            // Estado: true = desocupada, false = ocupada
            if (!((Celda) dato).getEstado()) {
                texto = texto + dato.toString() + "\n";
            }
            objca.Push(dato);
        }

        // Restaurar la cola original desde la auxiliar
        while (objca.IsEmpty() == false) {
            objc.Push(objca.Pop());
        }

        return texto;
    }
    
    /* Método que retorna una cadena con las celdas ocupadas (Estado == false)
       Recibe la cola original y una cola auxiliar para no perder datos
    */
    public String ListarCeldasDesocupadas(Cola objc, Cola objca) {
        String texto = ""; // texto de retorno
        Object dato; // para desencolar

        // Recorremos la cola original, desencolamos y guardamos en la auxiliar
        while (objc.IsEmpty() == false) {
            dato = objc.Pop();
            // Estado: true = desocupada, false = ocupada
            if (((Celda) dato).getEstado()) {
                texto = texto + dato.toString() + "\n";
            }
            objca.Push(dato);
        }

        // Restaurar la cola original desde la auxiliar
        while (objca.IsEmpty() == false) {
            objc.Push(objca.Pop());
        }

        return texto;
    }
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////M A N E J O  D E  L I S T A  S I M P L E   D E  P A G O S /////////////////////////////////////////////////////////////////////

    
       /*metodo para crear una lista simple de PAGOS, recibe la lista y recibe
    una opcion, si la opcion es 1 se creará por inicio y si es 2 se creará por final*/
    public ListaSimple CrearListaPagos(ListaSimple lspagos, ListaSimple veh, ListaDoble clientes,Pila serv, ListaSimple detapago)
    {
        int resp;
        Pago objpago;
        String idpago;
        resp=JOptionPane.showConfirmDialog(null,"Ingresar pago?","Lista simple de Pagos",JOptionPane.YES_NO_OPTION);
        while(resp==JOptionPane.YES_OPTION)
        {
            objpago=new Pago();//para que sobreescriba
            idpago=Validaciones.LeerString("Id de pago: ");
            objpago=objpago.IngresarDatos(idpago, veh, clientes, serv, detapago);//se ingresa un pago con sus detalles
            lspagos.CrearPorFinal(objpago);//se graba en la lista
            resp=JOptionPane.showConfirmDialog(null,"Ingresar otro pago?","Lista simple de pagos",JOptionPane.YES_NO_OPTION);
        }//fin mientras
        return lspagos;
    }//fin crear
    
    
    /*metodo que busca por la id de pago en la lista simple de pagos,
 retorna verdadero si lo encuentra y ademas deja el apuntador
 p en el dato, y al apuntador t atras de p, y retorna falso en 
 caso de no encontrarlo*/
    public boolean BuscarPagos(String idp, ListaSimple objls)
    {
       if(objls.IsEmpty()==false)//si hay datos
       {
         String id;//variable que debemos usar para capturar el pago
         objls.p=objls.getStart();//colocamos el apuntador al inicio
         id=((Pago)objls.p.getDato()).getPagoId();/*tomamos el id de pago
         que esta en el nodo para que no haga la pregunta con p en null!! esto
         NO es necesario en el algoritmo porque es un problema de programacion 
         de java no del analisis*/
         while(objls.p!=null&&!(id.equalsIgnoreCase(idp)))
         {
             objls.t=objls.p;//ubicamos al apuntador t atras de p
             objls.p=objls.p.getSiguiente();//p adelanta en la lista
             if(objls.p!=null)/*si hay aun datos para tomar otro id, esto 
                 no lo tenemos que hacer en el algoritmo*/
                 id=((Pago)objls.p.getDato()).getPagoId();
         }//fin mientras
       }
       if(objls.p==null)//recorrio toda la lista y no lo encuentra
           return false;
       else//p quedo ubicada en la lista en el dato buscado
           return true;
    }//fin buscar

    
    //copia el archivo a la lista simple de pagos
    public ListaSimple CopiarArchivoListaSimplePagos(Archivos objArch,ListaSimple lspago) {
        String cadena = "";

        try {
            // variables locales
            String id, idC, pl;
            LocalDate fec;
            double mon;
            String Reg[];

            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Pagos.txt"));
            Reg = objArch.LeerRegistro(5);

            while (Reg != null) {
                id = Reg[0];
                pl = Reg[1];
                idC = Reg[2];
                mon = Double.parseDouble(Reg[3]);
                fec = LocalDate.parse(Reg[4]);
               
                Pago objp = new Pago(id, pl, idC, mon, fec);
                
                lspago.CrearPorFinal(objp);

                Reg = objArch.LeerRegistro(5);
            }

            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente***");
        }

        return lspago;
    }// fin mostrar todo

    //copia el archivo a la lista simple de detalles de pagos
     public ListaSimple CopiarArchivoListaSimpleDetallePago(Archivos objArch, ListaSimple DetallePag ) 
    {      
        String cadena = "";
        try {
            //locales auxiliares para extraer la informacion del archivo
            String id,idpa,idserv;
            double pre;
           
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("DetallePago.txt"));
            //se invoca al metodo de leer registro con 6 atributos para el vector de la linea o registro del archivo IdDano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(4);
            //mientras existan datos en el archivo
            while (Reg!=null) //mientras not EOF()
            {
               /*los datos del Reg que se obtiene del archivo de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                id = Reg[0];
                idpa= Reg[1];
                idserv= Reg[2] ;
                pre= Double.parseDouble(Reg[3]);
                                                
                DetallePago objdp;
                objdp=new DetallePago(id,idpa,idserv,pre);
                
                DetallePag.CrearPorFinal(objdp);
                                
                Reg = objArch.LeerRegistro(4);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return DetallePag;
     }//fin de mostrar todo el archivo

     
     /*metodo que busca un pago y si lo encuentra devuelve el objeto-Joseph sanchez */
    public Object ConsultarPago(ListaSimple objlsPago, String id)
    {   if (objlsPago.IsEmpty()==false)
        {   
            Object pago;
            //si el pago esta en la lista
            if (BuscarPagos(id,objlsPago)==true)
            { 
                pago = (Pago)objlsPago.p.getDato();//si se encunetra el dato estaria en p
                return pago;
            }else{
                return "No ha sido encontrado el pago con id #: " + id;
            }//fin si buscar pago
        }else{
              return "La lista esta vacia";
             }//fin si hay lista
        }//fin consultarPago
     
     
    //jose lozano
    public boolean agregarDetallesAPago(String idPago, ListaSimple listaPagos, ListaSimple detallePagos, Pila pserv) {
    try {
        // Buscar el pago en la lista
        if (BuscarPagos(idPago, listaPagos) == false) {
            JOptionPane.showMessageDialog(null, "Error: No se encontró el pago con ID " + idPago);
            return false;
        }
        
        // Obtener el pago existente
        Pago pagoExistente = (Pago) listaPagos.p.getDato();
        double montoOriginal = pagoExistente.getMonto();
        double montoNuevosDetalles = 0;
        int contadorDetalles = 0;
        
        // Mostrar información del pago
        JOptionPane.showMessageDialog(null, 
            "Pago encontrado:\n" + 
            "ID: " + pagoExistente.getPagoId() + "\n" +
            "Placa: " + pagoExistente.getPlaca() + "\n" +
            "Cliente: " + pagoExistente.getIdCliente() + "\n" +
            "Monto actual: $" + montoOriginal + "\n" +
            "Fecha: " + pagoExistente.getFecha());
        
        // Ingresar nuevos detalles
        int resp = JOptionPane.YES_OPTION;
        while (resp == JOptionPane.YES_OPTION) {
            DetallePago objdet = new DetallePago();
            
            // Generar ID único para el nuevo detalle
            String idDetalle = Validaciones.LeerString("Ingrese ID para el nuevo detalle de pago: ");
            
            // Crear el nuevo detalle
            objdet = objdet.IngresarDatos(idDetalle, idPago, pserv);
            
            // Guardar en la lista de detalles
            detallePagos.CrearPorFinal(objdet);
            
            // Acumular el monto de los nuevos detalles
            montoNuevosDetalles += objdet.getPrecio();
            contadorDetalles++;
            
            JOptionPane.showMessageDialog(null, 
                "Detalle agregado:\n" +
                "ID: " + objdet.getDetalleId() + "\n" +
                "Servicio: " + objdet.getServicioId() + "\n" +
                "Precio: $" + objdet.getPrecio());
            
            resp = JOptionPane.showConfirmDialog(null, 
                "¿Ingresar más detalles de pago a " + idPago + "?", 
                "Agregando detalles", 
                JOptionPane.YES_NO_OPTION);
        }
        
        // Actualizar el monto total del pago si se agregaron detalles
        if (contadorDetalles > 0) {
            double nuevoMontoTotal = montoOriginal + montoNuevosDetalles;
            pagoExistente.setMonto(nuevoMontoTotal);
            
            JOptionPane.showMessageDialog(null, 
                "¡Detalles agregados exitosamente!\n\n" +
                "Resumen del pago ID: " + idPago + "\n" +
                "Monto original: $" + montoOriginal + "\n" +
                "Detalles agregados: " + contadorDetalles + "\n" +
                "Monto adicional: $" + montoNuevosDetalles + "\n" +
                "Nuevo monto total: $" + nuevoMontoTotal);
            
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se agregaron nuevos detalles al pago.");
            return false;
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado al agregar detalles: " + e.getMessage());
        return false;
    }
}    
 
    
    
    
    
    
    
     
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////M A N E J O  D E  L I S T A  S I M P L E   D E T A L L E  P A G O  /////////////////////////////////////////////////////////////////////  
     
     /*metodo que busca por la id de pago en la lista simple de pagos,
 retorna verdadero si lo encuentra y ademas deja el apuntador
 p en el dato, y al apuntador t atras de p, y retorna falso en 
 caso de no encontrarlo*/
    public boolean BuscarDetPago(String idDp, ListaSimple objls)
    {
       if(objls.IsEmpty()==false)//si hay datos
       {
         String id;//variable que debemos usar para capturar el id de detalle pago
         objls.p=objls.getStart();//colocamos el apuntador al inicio
         id=((DetallePago)objls.p.getDato()).getDetalleId();/*tomamos el id de detalle pago
         que esta en el nodo para que no haga la pregunta con p en null!! esto
         NO es necesario en el algoritmo porque es un problema de programacion 
         de java no del analisis*/
         while(objls.p!=null&&!(id.equalsIgnoreCase(idDp)))
         {
             objls.t=objls.p;//ubicamos al apuntador t atras de p
             objls.p=objls.p.getSiguiente();//p adelanta en la lista
             if(objls.p!=null)/*si hay aun datos para tomar otro id, esto 
                 no lo tenemos que hacer en el algoritmo*/
                 id=((DetallePago)objls.p.getDato()).getDetalleId();
         }//fin mientras
       }
       if(objls.p==null)//recorrio toda la lista y no lo encuentra
           return false;
       else//p quedo ubicada en la lista en el dato buscado
           return true;
    }//fin buscar
    
    /* Método para consultar un pago específico y listar todos sus detalles asociados
   Recibe el ID del pago, la lista simple de pagos y la lista simple de detalles de pago */
public void ConsultarPagoConDetalles(String idPago, ListaSimple lsPagos, ListaSimple lsDetalles)
{
    // Variables locales
    Nodo q;
    boolean encontrado = false;
    Pago objPago = null;
    String info = "";
    
    // ---------------------------------------------------------
    // 1. BUSCAR EL ENCABEZADO DEL PAGO EN LA LISTA DE PAGOS
    // ---------------------------------------------------------
    if (lsPagos.IsEmpty() == false)
    {
        q = lsPagos.getStart(); // Nos ubicamos al inicio
        while (q != null)
        {
            // Convertimos el dato genérico a Pago
            Pago p = (Pago) q.getDato();
            
            // Comparamos el ID (pagoId definido en tu clase Pago)
            if (p.getPagoId().equalsIgnoreCase(idPago))
            {
                objPago = p;
                encontrado = true;
                break; // Rompemos el ciclo si lo encontramos
            }
            q = q.getSiguiente(); // Avanzamos
        }
    }

    // ---------------------------------------------------------
    // 2. SI SE ENCONTRÓ, BUSCAR SUS DETALLES Y MOSTRAR TODO
    // ---------------------------------------------------------
    if (encontrado == true)
    {
        // Construimos la cadena con la información del Pago
        info += "--- INFORMACIÓN DEL PAGO ---\n";
        info += objPago.toString() + "\n\n";
        info += "--- DETALLES DEL PAGO ---\n";

        boolean tieneDetalles = false;
        
        // Recorremos la lista de detalles si no está vacía
        if (lsDetalles.IsEmpty() == false)
        {
            q = lsDetalles.getStart(); // Reiniciamos q para la nueva lista
            while (q != null)
            {
                // Convertimos el dato genérico a DetallePago
                DetallePago objDet = (DetallePago) q.getDato();
                
                // Verificamos si este detalle pertenece al ID del pago que estamos consultando
                // Se asume que DetallePago tiene el método getIdPago() o getPagoId()
                if (objDet.getPagoId().equalsIgnoreCase(idPago))
                {
                    info += objDet.toString() + "\n"; // Concatenamos el detalle
                    tieneDetalles = true;
                }
                q = q.getSiguiente();
            }
        }
        
        // Validamos si no se encontraron detalles
        if (tieneDetalles == false)
        {
            info += "No hay detalles registrados para este pago.";
        }

        // Mostramos toda la información acumulada
        JOptionPane.showMessageDialog(null, info);
    }
    else
    {
        // Si no se encontró el pago principal
        JOptionPane.showMessageDialog(null, "El Pago con ID " + idPago + " NO existe en la lista.");
    }
}
    

     
     
     
}//fin clase manejo de listas
