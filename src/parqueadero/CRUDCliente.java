
package parqueadero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;


public class CRUDCliente 
{
      /*Metodo que busca un cliente en el archivo plano y si lo encuentra
    retorna verdadero, sino lo encuentra retorna falso*/
    public boolean Buscar(Archivos objArch, String id) {
        boolean sw = false;//retorno
        try {
            //locales auxiliares para extraer la informacion del archivo
            String IdU, Nom, Ape,Cel,Em ,Dire;
    
                     
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            objArch.AbrirArchivoModoLectura("Clientes.txt");
            //se invoca al metodo de leer registro con 7 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(6);
            //mientras existan datos en el archivo
            while (Reg != null) //mientras not EOF()
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
                                             
                //si el id que extraimos del archivo en Reg es igual al ID que se esta buscando
                if (IdU.equalsIgnoreCase(id)) 
                {
                    sw = true;//se encuentra
                }//fin si
                //se lee el otro registro para que termine secuencialmente la lectura del archivo texto
                Reg = objArch.LeerRegistro(6);
            }//fin mientras
            //cerramos el archivo plano de texto en modo lectura
            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leido y cerrado correctamente*****");
        }
        return sw;

    }//fin de buscar

    //--------------------------------------------------------------    
    /*metodo que a partir de un id lo busca en el archivo y si no lo encuentra
     lo graba fisicamente con sus otros datos en el archivo y si lo encuentra muestra un mensaje 
     y no graba de nuevo el usuario porque estara repetido*/
    public void IngresarCliente(Archivos objArch, String id) 
    {
        Cliente objC = new Cliente();
        //se invoca el metodo buscar   
        if (Buscar(objArch, id) == false) {
            //se llama el metodo de ingresar datos de del usuario que recibe id y retorna el objeto cliente  
            objC = objC.IngresarDatos(id);
            //se invoca el metodo que graba fisicamente en el archivo
            GrabarCliente(objArch, objC);
        } else {
            JOptionPane.showMessageDialog(null, "*****Cliente YA existe en el archivo*****");
        }//fin si
    }//fin de ingresar
    
/*metodo que graba fisicamente el registro en el archivo, recibe el objeto
  archivo para el manejo del archivo y el objeto cliente que lo pasa a un
  String con la estructura de registro deseada para el .txt  */
    public void GrabarCliente(Archivos objArchivos, Cliente objc) 
    {
        try {
            String cadena = "";//para pasar el objeto a cadena con estructura
            objArchivos.AbrirArchivoModoEscritura("Clientes.txt");
            /*con el objeto que llega se invoca el metodo para la estructura del registro
            separado por comas y se recibe en la cadena para grabarla en el archivo*/
            cadena = objc.EstructuraReg();//se recibe el objeto pero en una cadena de atributos separados por comas
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
                
                cadena = cadena + objc.EstructuraReg() + "\n";//se puede el ToString
                                
                Reg = objArch.LeerRegistro(6);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return cadena;
     }//fin de mostrar todo el archivo

      
 public Cliente Consultar(Archivos objArch,String id)   
 {
     Cliente objc, objcC =null;//variable local para el retorno
     if(Buscar(objArch, id)==true)//se encuentra el dato en el archivo
     {
         try {
            //locales auxiliares para extraer la informacion del archivo
            String IdU, Nom, Ape,Cel,Em ,Dire;
            
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Clientes.txt"));
            //se invoca al metodo de leer registro con 7 atributos para el vector de la linea o registro del archivo plano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(6);
            //mientras existan datos en el archivo
            while (Reg!=null) //mientras not EOF()
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
                
                objc=new Cliente(IdU,Nom,Ape,Cel,Em,Dire);
                if(IdU.equalsIgnoreCase(id))
                     objcC=objc;
                               
                Reg = objArch.LeerRegistro(6);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
     }else{//No se encuentra
         JOptionPane.showMessageDialog(null,"Dato a consultar en el archivo NO existe");
     }//fin si
  return objcC ;//retorna en null o el vehiculo
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

     
}
