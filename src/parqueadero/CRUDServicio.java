
package parqueadero;


public class CRUDServicio 
{
       /*Metodo que busca un turno en el archivo plano y si lo encuentra
	retorna verdadero, sino lo encuentra retorna falso*/
	public boolean Buscar(Archivos objArch, String id) {
		boolean sw = false;//retorno
		try {
			//locales auxiliares para extraer la informacion del archivo
			String IdS;

			String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
			//se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
			objArch.AbrirArchivoModoLectura("Servicio.txt");
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

				//si el id que extraimos del archivo en Reg es igual al ID que se esta buscando
				if (IdS != null && IdS.equalsIgnoreCase(id)) {
					sw = true;//se encuentra
				}//fin si
				//se lee el otro registro para que termine secuencialmente la lectura del archivo texto
				Reg = objArch.LeerRegistro(3);
			}//fin mientras
			//cerramos el archivo plano de texto en modo lectura
			objArch.CerrarArchivoModoLectura();

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "**Archivo leido y cerrado correctamente**");
		}
		return sw;

	}//fin de buscar

	//--------------------------------------------------------------    
	/*metodo que a partir de un id lo busca en el archivo y si no lo encuentra
	 lo graba fisicamente con sus otros datos en el archivo y si lo encuentra muestra un mensaje 
	 y no graba de nuevo el turno porque estara repetido*/
	public void IngresarServicio(Archivos objArch, String id) {
		Servicio objs = new Servicio();
		//se invoca el metodo buscar   
		if (Buscar(objArch, id) == false) {
			//se llama el metodo de ingresar datos del turno que recibe id y retorna el objeto turno  
			objs = objs.IngresarDatos(id);
			//se invoca el metodo que graba fisicamente en el archivo
			GrabarServicio(objArch, objs);
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "**Servicio YA existe en el archivo**");
		}//fin si
	}//fin de ingresar

	/*metodo que graba fisicamente el registro en el archivo, recibe el objeto
	  archivo para el manejo del archivo y el objeto turno que lo pasa a un
	  String con la estructura de registro deseada para el .txt  */
	public void GrabarServicio(Archivos objArchivos, Servicio objs) {
		try {
			String cadena = "";//para pasar el objeto a cadena con estructura
			objArchivos.AbrirArchivoModoEscritura("Servicio.txt");
			/*con el objeto que llega se invoca el metodo para la estructura del registro
			separado por comas y se recibe en la cadena para grabarla en el archivo*/
			cadena = objs.EstructuraReg();//se recibe el objeto pero en una cadena de atributos separados por comas
			//la cadena separada por comas se graba persistentemente en memoria
			objArchivos.EscribirRegistro("" + cadena);
			objArchivos.CerrarArchivoModoEscritura();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "**SE GRABA EN El ARCHIVO**");
		}
	}//fin de grabar turno

	//metodo que retorna en una cadena todo el contenido del archivo para ser mostrado
	public String MostrarTodo(Archivos objArch) {
		String cadena = "";
		try {
			//locales auxiliares para extraer la informacion del archivo
			String IdS, Des, Pre;

			String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
    
    //se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
			javax.swing.JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Servicio.txt"));
			//se invoca al metodo de leer registro con 4 atributos para el vector de la linea o registro del archivo plano 
			//se recibe el texto en Reg
			Reg = objArch.LeerRegistro(3);
			//mientras existan datos en el archivo
			while (Reg != null && Reg[0] != null) //mientras not EOF()
			{
				/*los datos del Reg que se obtiene del archivo plano de texto se 
				asignan a las variables auxiliares locales para su facil manejo 
				como posiciones del vector String*/
				IdS = Reg[0];
				Des = Reg[1];
                                Pre = Reg[2];

				Servicio objs;
				objs = new Servicio(IdS, Des, Double.parseDouble(Pre));

				cadena = cadena + objs.EstructuraReg() + "\n";//se puede el ToString

				Reg = objArch.LeerRegistro(3);
			}//fin mientras  
			objArch.CerrarArchivoModoLectura();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "**Archivo leído y cerrado correctamente**");
		}
		return cadena;
	}//fin de mostrar todo el archivo

	public Servicio Consultar(Archivos objArch, String id) {
		Servicio objs, objsC = null;//variable local para el retorno
		if (Buscar(objArch, id) == true)//se encuentra el dato en el archivo
		{
			try {
				//locales auxiliares para extraer la informacion del archivo
				String IdS, Des, Pre;

				String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
				//se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
				javax.swing.JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Servicio.txt"));
				//se invoca al metodo de leer registro con 4 atributos para el vector de la linea o registro del archivo plano 
				//se recibe el texto en Reg
				Reg = objArch.LeerRegistro(3);
				//mientras existan datos en el archivo
				while (Reg != null && Reg[0] != null) //mientras not EOF()
				{
					/*los datos del Reg que se obtiene del archivo plano de texto se 
					asignan a las variables auxiliares locales para su facil manejo 
					como posiciones del vector String*/
					IdS = Reg[0];
					Des = Reg[1];
					Pre = Reg[2];
					
					objs = new Servicio(IdS, Des, Double.parseDouble(Pre));
					if (IdS != null && IdS.equalsIgnoreCase(id))
						objsC = objs;

					Reg = objArch.LeerRegistro(3);
				}//fin mientras  
				objArch.CerrarArchivoModoLectura();
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, "**Archivo leído y cerrado correctamente**");
			}
		} else {//No se encuentra
			javax.swing.JOptionPane.showMessageDialog(null, "Dato a consultar en el archivo NO existe");
		}//fin si
		return objsC;//retorna en null o el turno
	}//fin consultar

	//retorna el numero de registros que tiene el archivo/
	public int ContarLineas(String arch) {
		int numLineas = 0;//contador de lineas y es su retorno

		try {
			java.io.File archivo = new java.io.File(arch);
                        if (archivo.isFile() == false)//si no hay archivo
			{
				return 0;  //retorna cero registros
			} else//si hay archivo
			{
				java.io.FileReader lectura = new java.io.FileReader(arch);
				java.io.BufferedReader Br = new java.io.BufferedReader(lectura);//objeto logico de archivo

				while (Br.readLine() != null) //mientras se "lean" lineas y no llegue a null
				{
					numLineas++;//contador de lineas
				}
				Br.close();//se cierra el buffer o sea el almacenamiento
				return numLineas;//retorna el numero de lineas o registros que tiene
			}//fin si
		} catch (java.io.IOException e) {
			e.printStackTrace();/*printStackTrace( ) Se utiliza para imprimir 
								   el registro del stack donde se ha iniciado la excepción*/
		}
		return numLineas;
	}//fin de contar lineas


    
}
