
package parqueadero;

import javax.swing.JOptionPane;



public class CRUDTurnos 
{
     /*Metodo que busca un turno en el archivo plano y si lo encuentra
	retorna verdadero, sino lo encuentra retorna falso*/
	public boolean Buscar(Archivos objArch, String id) {
		boolean sw = false;//retorno
		try {
			//locales auxiliares para extraer la informacion del archivo
			String IdT;

			String Reg[];//para tomar la linea String como vector de datos y facilitar el trabajo con el registro
			//se abre el archivo modo lectura y se imprime el mensaje de apertura que retorna
			objArch.AbrirArchivoModoLectura("Turnos.txt");
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

				//si el id que extraimos del archivo en Reg es igual al ID que se esta buscando
				if (IdT != null && IdT.equalsIgnoreCase(id)) {
					sw = true;//se encuentra
				}//fin si
				//se lee el otro registro para que termine secuencialmente la lectura del archivo texto
				Reg = objArch.LeerRegistro(4);
			}//fin mientras
			//cerramos el archivo plano de texto en modo lectura
			objArch.CerrarArchivoModoLectura();

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "***Archivo leido y cerrado correctamente*****");
		}
		return sw;

	}//fin de buscar

	//--------------------------------------------------------------    
	/*metodo que a partir de un id lo busca en el archivo y si no lo encuentra
	 lo graba fisicamente con sus otros datos en el archivo y si lo encuentra muestra un mensaje 
	 y no graba de nuevo el turno porque estara repetido*/
	public void IngresarTurno(Archivos objArch, String id) {
		Turno objT = new Turno();
		//se invoca el metodo buscar   
		if (Buscar(objArch, id) == false) {
			//se llama el metodo de ingresar datos del turno que recibe id y retorna el objeto turno  
			objT = objT.IngresarDatos(id);
			//se invoca el metodo que graba fisicamente en el archivo
			GrabarTurno(objArch, objT);
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "*****Turno YA existe en el archivo*****");
		}//fin si
	}//fin de ingresar

	/*metodo que graba fisicamente el registro en el archivo, recibe el objeto
	  archivo para el manejo del archivo y el objeto turno que lo pasa a un
	  String con la estructura de registro deseada para el .txt  */
	public void GrabarTurno(Archivos objArchivos, Turno objt) {
		try {
			String cadena = "";//para pasar el objeto a cadena con estructura
			objArchivos.AbrirArchivoModoEscritura("Turnos.txt");
			/*con el objeto que llega se invoca el metodo para la estructura del registro
			separado por comas y se recibe en la cadena para grabarla en el archivo*/
			cadena = objt.EstructuraReg();//se recibe el objeto pero en una cadena de atributos separados por comas
			//la cadena separada por comas se graba persistentemente en memoria
			objArchivos.EscribirRegistro("" + cadena);
			objArchivos.CerrarArchivoModoEscritura();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "*****SE GRABA EN El ARCHIVO*****");
		}
	}//fin de grabar turno

	//metodo que retorna en una cadena todo el contenido del archivo para ser mostrado
	public String MostrarTodo(Archivos objArch) {
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

				cadena = cadena + objt.EstructuraReg() + "\n";//se puede el ToString

				Reg = objArch.LeerRegistro(4);
			}//fin mientras  
			objArch.CerrarArchivoModoLectura();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
		}
		return cadena;
	}//fin de mostrar todo el archivo

	public Turno Consultar(Archivos objArch, String id) {
		Turno objt, objtC = null;//variable local para el retorno
		if (Buscar(objArch, id) == true)//se encuentra el dato en el archivo
		{
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

					objt = new Turno(IdT, Des, Hi, Hf);
					if (IdT != null && IdT.equalsIgnoreCase(id))
						objtC = objt;

					Reg = objArch.LeerRegistro(4);
				}//fin mientras  
				objArch.CerrarArchivoModoLectura();
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente*****");
			}
		} else {//No se encuentra
			javax.swing.JOptionPane.showMessageDialog(null, "Dato a consultar en el archivo NO existe");
		}//fin si
		return objtC;//retorna en null o el turno
	}//fin consultar

	/*retorna el numero de registros que tiene el archivo*/
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

        //Código en CRUDTurnos   --- Emmanuel
public void CopiarPilaTurnosAArchivo(Archivos objArch, Pila pilaTurnos, Pila aux, ManejoPila objp) {

    // 1. Borrar lo anterior del archivo
    objArch.BorrarContenido("Turnos.txt");

    // 2. Copiar toda la pila al archivo
    Turno t;
    Object dato;

    while (!pilaTurnos.IsEmpty()) {
        dato = pilaTurnos.Pop(); // sacar de la pila original
        t = (Turno) dato;

        // abrir en modo escritura (append), porque ya está vacío
        objArch.AbrirArchivoModoEscritura("Turnos.txt");
        objArch.EscribirRegistro(t.EstructuraReg());
        objArch.CerrarArchivoModoEscritura();

        aux.Push(t);  // guardarlo en auxiliar
    }

    // 3. Devolver los datos a la pila original
    objp.PasarPila(aux, pilaTurnos);

    JOptionPane.showMessageDialog(null, "Pila de TURNOS copiada con éxito al archivo.");
}


    
}
