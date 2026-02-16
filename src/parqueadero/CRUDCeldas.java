
package parqueadero;


import java.io.*;
import javax.swing.JOptionPane;

public class CRUDCeldas 
{
  /* Método que busca una celda en el archivo plano y si lo encuentra
    retorna verdadero, sino lo encuentra retorna falso */
    public boolean Buscar(Archivos objArch, String id) {
        boolean sw = false; // retorno
        try {
            // locales auxiliares para extraer la información del archivo
            String IdCelda, TipoVehiculo;
            boolean Estado;          
            String Reg[]; // para tomar la linea String como vector de datos
            // se abre el archivo modo lectura
            objArch.AbrirArchivoModoLectura("Celdas.txt");
            // se invoca al método de leer registro con 3 atributos
            Reg = objArch.LeerRegistro(3);
            // mientras existan datos en el archivo
            while (Reg != null) {
                // asignar datos del registro a variables locales
                IdCelda = Reg[0];
                TipoVehiculo = Reg[1];
                Estado = Boolean.parseBoolean(Reg[2]);
                              
                // si el ID que extraemos del archivo es igual al ID que se está buscando
                if (IdCelda.equalsIgnoreCase(id)) {
                    sw = true;
                }
                // leer siguiente registro
                Reg = objArch.LeerRegistro(3);
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
    public void IngresarCelda(Archivos objArch, String id) {
        Celda objC = new Celda();
        // se invoca el método buscar   
        if (Buscar(objArch, id) == false) {
            // se llama el método de ingresar datos de la celda
            objC = objC.IngresarDatos(id);
            // se invoca el método que graba físicamente en el archivo
            GrabarCelda(objArch, objC);
        } else {
            JOptionPane.showMessageDialog(null, "***** Celda YA existe en el archivo *****");
        }
    }
    /* Método que graba físicamente el registro en el archivo */
    public void GrabarCelda(Archivos objArchivos, Celda objc) {
        try {
            String cadena = ""; // para pasar el objeto a cadena con estructura
           objArchivos.AbrirArchivoModoEscritura("Celdas.txt");
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
                cadena = cadena + objc.EstructuraReg() + "\n";
                                
                Reg = objArch.LeerRegistro(3);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return cadena;
    }
    public Celda Consultar(Archivos objArch, String id) {
        Celda objc, objcC = null; // variable local para el retorno
        if (Buscar(objArch, id) == true) { // se encuentra el dato en el archivo
            try {
                // locales auxiliares
                String IdCelda, TipoVehiculo;
                boolean Estado;                
                String Reg[];
                JOptionPane.showMessageDialog(null, "" + objArch.AbrirArchivoModoLectura("Celdas.txt"));
                Reg = objArch.LeerRegistro(3);                
                while (Reg != null) {
                    IdCelda = Reg[0];
                    TipoVehiculo = Reg[1];
                    Estado = Boolean.parseBoolean(Reg[2]);           
                    objc = new Celda(IdCelda, TipoVehiculo, Estado);
                    if (IdCelda.equalsIgnoreCase(id))
                         objcC = objc;
                               
                    Reg = objArch.LeerRegistro(3);
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
    /* Retorna el número de registros que tiene el archivo */
    public int ContarLineas(String arch) {
        int numLineas = 0; // contador de líneas
        try {
            File archivo = new File(arch);
            if (archivo.isFile() == false) { // si no hay archivo
                return 0;  // retorna cero registros
            } else { // si hay archivo
                FileReader lectura = new FileReader(arch);
                BufferedReader Br = new BufferedReader(lectura);
                      while (Br.readLine() != null) { // mientras se lean líneas
                    numLineas++; // contador de líneas
                }
                Br.close(); // cerrar buffer
                return numLineas; // retorna número de líneas
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLineas;
    }    
    /* Método adicional para cambiar estado de una celda */
    public void CambiarEstadoCelda(Archivos objArch, String id, boolean nuevoEstado) {
        Celda objc = Consultar(objArch, id);
        if (objc != null) {
            objc.setEstado(nuevoEstado);
            JOptionPane.showMessageDialog(null, "Estado de celda " + id + " cambiado a: " + (nuevoEstado ? "LIBRE" : "OCUPADA"));
        } else {
            JOptionPane.showMessageDialog(null, "Celda no encontrada");
        }
    }
    
    
    //Código en CRUDCeldas   ---  Emmanuel
// Método que COPIA toda la cola de celdas al archivo Celdas.txt
// BORRANDO todo lo que haya previamente en el archivo
public void CopiarColaArchivo(Archivos objArch, Cola c1, Cola temp, ManejoCola objc) {
    boolean archivoAbierto = false;
    try {
        // 1) BORRAR el contenido previo
        objArch.BorrarContenido("Celdas.txt");

        // 2) Abrir el archivo UNA vez en modo escritura (ahora está vacío)
        objArch.AbrirArchivoModoEscritura("Celdas.txt");
        archivoAbierto = true;

        // 3) Recorrer la cola sin perder datos: desencolar -> escribir -> encolar en temp
        while (!c1.IsEmpty()) {
            Celda cel = (Celda) c1.Pop();
            objArch.EscribirRegistro(cel.EstructuraReg());
            temp.Push(cel);
        }

        // 4) Cerrar el archivo
        objArch.CerrarArchivoModoEscritura();
        archivoAbierto = false;

        // 5) Restaurar la cola original
        objc.PasarCola(temp, c1);

        JOptionPane.showMessageDialog(null, "La cola de CELDAS fue copiada al archivo correctamente.");

    } catch (Exception e) {
        // Si hubo error, intentamos cerrar el archivo si quedó abierto
        try {
            if (archivoAbierto) objArch.CerrarArchivoModoEscritura();
        } catch (Exception ex) {
            // ignorar cierre fallido
        }
        // Restaurar la cola aunque ocurriera error (intento seguro)
        try { objc.PasarCola(temp, c1); } catch (Exception ex) {}

        JOptionPane.showMessageDialog(null, "ERROR copiando la cola al archivo: " + e.getMessage());
    }
}

    
}

