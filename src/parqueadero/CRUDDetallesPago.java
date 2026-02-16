
package parqueadero;

import javax.swing.JOptionPane;


public class CRUDDetallesPago 
{
     /*Metodo que busca un DetallePago en el archivo  y si lo encuentra
    retorna verdadero, sino lo encuentra retorna falso*/
    public boolean Buscar(Archivos objArch, String IdD) {
        boolean sw = false;//retorno
        try {
            //locales auxiliares para extraer la informacion del archivo
            
             String id,idpa,idserv;
             double pre;
           
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            objArch.AbrirArchivoModoLectura("DetallePago.txt");
            //se invoca al metodo de leer registro con 4 atributos para el vector de la linea o registro del archivo 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(4);
            //mientras existan datos en el archivo
            while (Reg != null) //mientras not EOF()
            {
                /*los datos del Reg que se obtiene del archivo IdDano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                id = Reg[0];
                idpa= Reg[1];
                idserv= Reg[2] ;
                pre= Double.parseDouble(Reg[3]);
               
                              
                //si la IdDetallePago que extraimos del archivo en Reg es igual a la IdDetallePago que se esta buscando
                if (IdD.equalsIgnoreCase(id)) 
                {
                    sw = true;
                }//fin si
                //se lee el otro registro para que termine secuencialmente la lectura del archivo texto
                Reg = objArch.LeerRegistro(4);
            }//fin mientras
            //cerramos el archivo IdDano de texto en modo lectura
            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leido y cerrado correctamente*****");
        }
        return sw;

    }//fin de buscar

    //--------------------------------------------------------------    
    /*metodo que a partir de una IdDetallePago lo busca en el archivo y si no lo encuentra
     lo graba fisicamente con sus otros datos en el archivo y si lo encuentra muestra un mensaje 
     y no graba de nuevo el DetallePago porque estara repetido*/
    public void IngresarDetallePago(Archivos objArch, String IdD,String idpago, Pila pserv) 
    {
        DetallePago objd = new DetallePago();
        //se invoca el metodo buscar   
        if (Buscar(objArch, IdD) == false) {
            //se llama el metodo de ingresar datos de del DetallePago que recibe IdDetallePago y retorna el objeto DetallePago  
            objd = objd.IngresarDatos(IdD, idpago, pserv);
            //se invoca el metodo que graba fisicamente en el archivo
            GrabarDetallePago(objArch, objd);
        } else {
            JOptionPane.showMessageDialog(null, "*****DetallePago YA existe en el archivo*****");
        }//fin si
    }//fin de ingresar
    
/*metodo que graba fisicamente el registro en el archivo, recibe el objeto
  archivo para el manejo del archivo y el objeto DetallePago que lo pasa a un
  String con la estructura de registro deseada para el .txt  */
    public void GrabarDetallePago(Archivos objArchivos, DetallePago objd) 
    {
        try {
            String cadena = "";//para pasar el objeto a cadena con estructura
            objArchivos.AbrirArchivoModoEscritura("DetallePago.txt");
            /*con el objeto que llega se invoca el metodo para la estructura del registro
            separado por comas y se recibe en la cadena para grabarla en el archivo*/
            cadena = objd.EstructuraReg();//se recibe el objeto pero en una cadena de atributos separados por comas
            //la cadena separada por comas se graba persistentemente en memoria
            objArchivos.EscribirRegistro("" + cadena);
            objArchivos.CerrarArchivoModoEscritura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*****SE GRABA EN El ARCHIVO*****");
        }
    }//fin de grabar DetallePago

    //metodo que retorna en una cadena todo el contenido del archivo para ser mostrado
    public String MostrarTodo(Archivos objArch) 
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
               /*los datos del Reg que se obtiene del archivo IdDano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                id = Reg[0];
                idpa= Reg[1];
                idserv= Reg[2] ;
                pre= Double.parseDouble(Reg[3]);
                                                
                DetallePago objdp;
                objdp=new DetallePago(id,idpa,idserv,pre);
                
                cadena = cadena + objdp.EstructuraReg() + "\n";//se puede el ToString
                                
                Reg = objArch.LeerRegistro(4);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
        return cadena;
     }//fin de mostrar todo el archivo

      
 public DetallePago Consultar(Archivos objArch,String Idb)   
 {
     DetallePago objd, objdC =null;//variable local para el retorno
     if(Buscar(objArch, Idb)==true)//se encuentra el dato en el archivo
     {
         try {
            //locales auxiliares para extraer la informacion del archivo
            String id,idpa,idserv;
            double pre;
            
            String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
            //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
            JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("DetallePago.txt"));
            //se invoca al metodo de leer registro con 12 atributos para el vector de la linea o registro del archivo IdDano 
            //se recibe el texto en Reg
            Reg = objArch.LeerRegistro(4);
            //mientras existan datos en el archivo
            while (Reg!=null) //mientras not EOF()
            {
               /*los datos del Reg que se obtiene del archivo IdDano de texto se 
                asignan a las variables auxiliares locales para su facil manejo 
                como posiciones del vector String*/
                id = Reg[0];
                idpa= Reg[1];
                idserv= Reg[2] ;
                pre= Double.parseDouble(Reg[3]);
                objd=new DetallePago(id,idpa,idserv,pre);
                if(Idb.equalsIgnoreCase(id))
                     objdC=objd;
                               
                Reg = objArch.LeerRegistro(4);
            }//fin mientras  
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
        }
     }else{//No se encuentra
         JOptionPane.showMessageDialog(null,"Dato a consultar en el archivo NO existe");
     }//fin si
  return objdC ;//retorna en null o el DetallePago
 }//fin consultar
    
}
