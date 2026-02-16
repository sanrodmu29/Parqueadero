
package parqueadero;

import java.io.*;
import javax.swing.JOptionPane;

public class CRUDVehiculo
{
     /*Metodo que busca un vehiculo en el archivo plano y si lo encuentra
    retorna verdadero, sino lo encuentra retorna falso*/
    public boolean Buscar(Archivos objArch, String pla) {
        boolean sw = false;//retorno
        try {
            //locales auxiliares para extraer la informacion del archivo
            
            String pl,TiVe, Mar, Co,id;
            int Mod;
            boolean Est;
                     
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            objArch.AbrirArchivoModoLectura("Vehiculos.txt");
            //se invoca al metodo de leer registro con 8 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(7);
            //mientras existan datos en el archivo
            while (Reg != null) //mientras not EOF()
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
                              
                //si la placa que extraimos del archivo en Reg es igual a la placa que se esta buscando
                if (pl.equalsIgnoreCase(pla)) 
                {
                    sw = true;
                }//fin si
                //se lee el otro registro para que termine secuencialmente la lectura del archivo texto
                Reg = objArch.LeerRegistro(7);
            }//fin mientras
            //cerramos el archivo plano de texto en modo lectura
            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leido y cerrado correctamente*****");
        }
        return sw;

    }//fin de buscar

    //--------------------------------------------------------------    
    /*metodo que a partir de una placa lo busca en el archivo y si no lo encuentra
     lo graba fisicamente con sus otros datos en el archivo y si lo encuentra muestra un mensaje 
     y no graba de nuevo el vehiculo porque estara repetido*/
    public void IngresarVehiculo(Archivos objArch, String pl,ListaDoble clientes) 
    {
        Vehiculo objV = new Vehiculo();
        //se invoca el metodo buscar   
        if (Buscar(objArch, pl) == false) {
            //se llama el metodo de ingresar datos de del vehiculo que recibe placa y retorna el objeto vehiculo  
            objV = objV.IngresarDatos(pl,clientes);
            //se invoca el metodo que graba fisicamente en el archivo
            GrabarVehiculo(objArch, objV);
        } else {
            JOptionPane.showMessageDialog(null, "*****vehiculo YA existe en el archivo*****");
        }//fin si
    }//fin de ingresar
    
/*metodo que graba fisicamente el registro en el archivo, recibe el objeto
  archivo para el manejo del archivo y el objeto vehiculo que lo pasa a un
  String con la estructura de registro deseada para el .txt  */
    public void GrabarVehiculo(Archivos objArchivos, Vehiculo objv) 
    {
        try {
            String cadena = "";//para pasar el objeto a cadena con estructura
            objArchivos.AbrirArchivoModoEscritura("Vehiculos.txt");
            /*con el objeto que llega se invoca el metodo para la estructura del registro
            separado por comas y se recibe en la cadena para grabarla en el archivo*/
            cadena = objv.EstructuraReg();//se recibe el objeto pero en una cadena de atributos separados por comas
            //la cadena separada por comas se graba persistentemente en memoria
            objArchivos.EscribirRegistro("" + cadena);
            objArchivos.CerrarArchivoModoEscritura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*****SE GRABA EN El ARCHIVO*****");
        }
    }//fin de grabar vehiculo

    //metodo que retorna en una cadena todo el contenido del archivo para ser mostrado
    public String MostrarTodo(Archivos objArch) 
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
                
                cadena = cadena + objv.EstructuraReg() + "\n";//se puede el ToString
                                
                Reg = objArch.LeerRegistro(7);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return cadena;
     }//fin de mostrar todo el archivo

      
 public Vehiculo Consultar(Archivos objArch,String pla)   
 {
     Vehiculo objv, objvC =null;//variable local para el retorno
     if(Buscar(objArch, pla)==true)//se encuentra el dato en el archivo
     {
         try {
            //locales auxiliares para extraer la informacion del archivo
            String pl,TiVe, Mar, Co,id;
            int Mod;
            boolean Est;
            
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Vehiculos.txt"));
            //se invoca al metodo de leer registro con 12 atributos para el vector de la linea o registro del archivo plano 
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
                objv=new Vehiculo(pl,id,TiVe,Mar,Co,Mod,Est);
                if(pl.equalsIgnoreCase(pla))
                     objvC=objv;
                               
                Reg = objArch.LeerRegistro(7);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
     }else{//No se encuentra
         JOptionPane.showMessageDialog(null,"Dato a consultar en el archivo NO existe");
     }//fin si
  return objvC ;//retorna en null o el vehiculo
 }//fin consultar
 
 /*retorna el numero de registros que tiene el archivo*/
  public int ContarLineas(String arch)
    {
      int numLineas=0;//contador de lineas y es su retorno
      
      try
      {
       File archivo = new File(arch);
       if(archivo.isFile()== false)//si no hay archivo
       {
         return 0;  //retorna cero registros
       }
       else//si hay archivo
        {
       FileReader lectura = new FileReader(arch);
       BufferedReader Br = new BufferedReader(lectura);//objeto logico de archivo
       
        while (Br.readLine()!=null) //mientras se "lean" lineas y no llegue a null
          {
            numLineas++;//contador de lineas
          }
        Br.close();//se cierra el buffer o sea el almacenamiento
        return numLineas;//retorna el numero de lineas o registros que tiene
       }//fin si
      }
      catch (IOException e)
      {
         e.printStackTrace();/*printStackTrace( ) Se utiliza para imprimir 
                                el registro del stack donde se ha iniciado la excepción*/
      }
      return numLineas;
    }//fin de contar lineas

  
  
}//fin clase CRUD
