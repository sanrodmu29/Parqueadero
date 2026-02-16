
package parqueadero;

import java.time.LocalDate;
import javax.swing.JOptionPane;


public class CRUDConvenio 
{
     /* Método que busca un convenio en el archivo plano y si lo encuentra
    retorna verdadero, sino lo encuentra retorna falso */
    public boolean Buscar(Archivos objArch, String id) {
        boolean sw = false; // retorno
        try {
            // locales auxiliares para extraer la información del archivo
            String IdConvenio, idCli, Des;
            LocalDate fi,ff;
            double pre;
            String Reg[]; // para tomar la linea String como vector de datos
            // se abre el archivo modo lectura
            objArch.AbrirArchivoModoLectura("Convenios.txt");
            // se invoca al método de leer registro con 6 atributos
            Reg = objArch.LeerRegistro(6);
            // mientras existan datos en el archivo
            while (Reg != null) {
                // asignar datos del registro a variables locales
                IdConvenio = Reg[0];
                idCli = Reg[1];
                Des = Reg[2];
                fi=LocalDate.parse(Reg[3]);
                ff=LocalDate.parse(Reg[4]);
                pre=Double.parseDouble(Reg[5]);
                // si el ID que extraemos del archivo es igual al ID que se está buscando
                if (IdConvenio.equalsIgnoreCase(id)) {
                    sw = true;
                }
                // leer siguiente registro
                Reg = objArch.LeerRegistro(6);
            }
            // cerrar archivo
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return sw;
    }
    // --------------------------------------------------------------        
    /* Método que a partir de un ID lo busca en el archivo y si no lo encuentra
     lo graba físicamente con sus otros datos en el archivo */
    public void IngresarConvenio(Archivos objArch, String id, ListaDoble Clientes) {
        Convenio objC = new Convenio();
        // se invoca el método buscar   
        if (Buscar(objArch, id) == false) {
            // se llama el método de ingresar datos de convenio
            objC = objC.IngresarDatos(id,Clientes);
            // se invoca el método que graba físicamente en el archivo
            GrabarConvenio(objArch, objC);
        } else {
            JOptionPane.showMessageDialog(null, "***** Convenio YA existe en el archivo *****");
        }
    }
    /* Método que graba físicamente el registro en el archivo */
    public void GrabarConvenio(Archivos objArchivos, Convenio objc) {
        try {
            String cadena = ""; // para pasar el objeto a cadena con estructura
           objArchivos.AbrirArchivoModoEscritura("Convenios.txt");
            // obtener estructura del registro separado por comas
            cadena = objc.EstructuraReg();
            // grabar en el archivo
            objArchivos.EscribirRegistro("" + cadena);
            objArchivos.CerrarArchivoModoEscritura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***** SE GRABA EN EL ARCHIVO *****");
        }
    }

    // Método que retorna en una cadena todo el contenido del archivo para ser mostrado
    public String MostrarTodo(Archivos objArch) {      
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
                cadena = cadena + objc.EstructuraReg() + "\n";
                                
                Reg = objArch.LeerRegistro(6);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return cadena;
    }
    public Convenio Consultar(Archivos objArch, String id) {
        Convenio objc, objcC = null; // variable local para el retorno
        if (Buscar(objArch, id) == true) { // se encuentra el dato en el archivo
            try {
                // locales auxiliares
                String IdConvenio, idCli, Des;
                LocalDate fi,ff;
                double pre;                
                String Reg[];
                JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Convenios.txt"));
                Reg = objArch.LeerRegistro(6);                
                while (Reg != null) {
                    IdConvenio = Reg[0];
                    idCli = Reg[1];
                    Des = Reg[2];
                    fi=LocalDate.parse(Reg[3]);
                    ff=LocalDate.parse(Reg[4]);
                    pre=Double.parseDouble(Reg[5]);     
                    objc = new Convenio(IdConvenio, idCli,Des,fi,ff,pre);
                    if (IdConvenio.equalsIgnoreCase(id))
                         objcC = objc;
                               
                    Reg = objArch.LeerRegistro(6);
                }
                objArch.CerrarArchivoModoLectura();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
            }
        } else { // No se encuentra
            JOptionPane.showMessageDialog(null, "Dato a consultar en el archivo NO existe");
        }
        return objcC; // retorna null o la celda
    } 
 
 
    /* Método que COPIA toda la cola de convenios al archivo Convenios.txt
       BORRANDO todo lo que haya previamente en el archivo.
       Requiere la cola auxiliar y el objeto de manejo para restaurar los datos en memoria. */
    public void CopiarColaConveniosArchivo(Archivos objArch, Cola c1, Cola c2, ManejoCola mc) {
        try {
            // 1. Borrar el contenido previo del archivo para sobreescribir
            objArch.BorrarContenido("Convenios.txt");

            // 2. Abrir el archivo en modo escritura
            objArch.AbrirArchivoModoEscritura("Convenios.txt");

            // 3. Recorrer la cola: Desencolar -> Escribir -> Encolar en Auxiliar
            while (c1.IsEmpty() == false) {
                // Sacamos el objeto y hacemos casting a Convenio
                Convenio objCon = (Convenio) c1.Pop();
                
                // Obtenemos la estructura CSV del convenio
                String cadena = objCon.EstructuraReg();
                
                // Escribimos en el archivo físico
                objArch.EscribirRegistro(cadena);
                
                // Guardamos en la cola auxiliar para no perder el dato en memoria
                c2.Push(objCon);
            }

            // 4. Cerrar el archivo
            objArch.CerrarArchivoModoEscritura();

            // 5. IMPORTANTE: Restaurar la cola original usando el método de ManejoCola
            mc.PasarCola(c2, c1);

            JOptionPane.showMessageDialog(null, "***** La cola de CONVENIOS fue copiada al archivo correctamente *****");

        } catch (Exception e) {
            // En caso de error, intentamos restaurar la cola para no perder datos en memoria
            mc.PasarCola(c2, c1); 
            JOptionPane.showMessageDialog(null, "Error al copiar la cola de convenios al archivo: " + e.getMessage());
        }
    }
    
}
