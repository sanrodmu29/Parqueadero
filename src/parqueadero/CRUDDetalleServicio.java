package parqueadero;

import javax.swing.JOptionPane;

public class CRUDDetalleServicio {
    /* Método que busca un detalle de servicio en el archivo plano y si lo encuentra
    retorna verdadero, sino lo encuentra retorna falso */
    public boolean Buscar(Archivos objArch, String id) {
        boolean sw = false;
        try {
            String servicioId, Placa,IdCelda,ids, horaInicio, horaFin;
            String FechaServicio;
            
            String Reg[];
            objArch.AbrirArchivoModoLectura("DetalleServicios.txt");
            Reg = objArch.LeerRegistro(7);
            
            while (Reg != null && Reg[0] != null) {
                servicioId = Reg[0];
                Placa = Reg[1];
                IdCelda= Reg[2];
                ids=Reg[3];
                horaInicio = Reg[4];
                horaFin = Reg[5];
                FechaServicio = Reg[6];
                
                if (servicioId.equalsIgnoreCase(id)) {
                    sw = true;
                }
                Reg = objArch.LeerRegistro(7);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return sw;
    }

    /* Método que ingresa un detalle de servicio en el archivo */
    public void IngresarDetalleServicio(Archivos objArch, String id, ListaSimple LSVehi, Cola objc,Pila pser) {
        DetalleServicio objDS = new DetalleServicio();
        if (Buscar(objArch, id) == false) {
            objDS = objDS.IngresarDatos(id, LSVehi, objc,pser);
            GrabarDetalleServicio(objArch, objDS);
        } else {
            JOptionPane.showMessageDialog(null, "***** Detalle de servicio YA existe en el archivo *****");
        }
    }

    /* Método que graba físicamente el registro en el archivo */
   public void GrabarDetalleServicio(Archivos objArchivos, DetalleServicio objds) {
    try {
        String cadena = "";
        objArchivos.AbrirArchivoModoEscritura("DetalleServicios.txt");
        cadena = objds.EstructuraReg();
        objArchivos.EscribirRegistro("" + cadena);
        objArchivos.CerrarArchivoModoEscritura();
        JOptionPane.showMessageDialog(null, "***** DETALLE DE SERVICIO GUARDADO CORRECTAMENTE *****");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el detalle de servicio: " + e.getMessage());
    }
}

    // Método que retorna en una cadena todo el contenido del archivo para ser mostrado
    public String MostrarTodo(Archivos objArch) {      
        String cadena = "";
        try {
            String servicioId, Placa,IdCelda,ids, horaInicio, horaFin;
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
                objds = new DetalleServicio(servicioId, Placa,IdCelda,ids, horaInicio, horaFin, java.time.LocalDate.parse(FechaServicio));
                cadena = cadena + objds.EstructuraReg() + "\n";
                
                Reg = objArch.LeerRegistro(7);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "*** Archivo leído y cerrado correctamente ***");
        }
        return cadena;
    }

    public DetalleServicio Consultar(Archivos objArch, String id) {
    DetalleServicio objds, objdsC = null;
    
    // Verificar si el archivo existe primero
    java.io.File archivo = new java.io.File("DetalleServicios.txt");
    if (!archivo.exists()) {
        JOptionPane.showMessageDialog(null, "No hay servicios registrados. El archivo DetalleServicios.txt no existe.");
        return null;
    }
    
    if (Buscar(objArch, id) == true) {
        try {
            String servicioId, Placa,IdCelda,ids, horaInicio, horaFin;
            String FechaServicio;
            String Reg[];
            
            objArch.AbrirArchivoModoLectura("DetalleServicios.txt");
            Reg = objArch.LeerRegistro(7);
            
            while (Reg != null && Reg[0] != null) {
                servicioId = Reg[0];
                Placa = Reg[1];
                IdCelda= Reg[2];
                ids=Reg[3];
                horaInicio = Reg[4];
                horaFin = Reg[5];
                FechaServicio = Reg[6];
                
                objds = new DetalleServicio(servicioId, Placa,IdCelda,ids, horaInicio, horaFin, java.time.LocalDate.parse(FechaServicio));
                if (servicioId.equalsIgnoreCase(id))
                     objdsC = objds;
                           
                Reg = objArch.LeerRegistro(7);
            }
            objArch.CerrarArchivoModoLectura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Dato a consultar en el archivo NO existe");
    }
    return objdsC;
}
    /* Retorna el número de registros que tiene el archivo */
    public int ContarLineas(String arch) {
        int numLineas = 0;
        try {
            java.io.File archivo = new java.io.File(arch);
            if (archivo.isFile() == false) {
                return 0;
            } else {
                java.io.FileReader lectura = new java.io.FileReader(arch);
                java.io.BufferedReader Br = new java.io.BufferedReader(lectura);
                while (Br.readLine() != null) {
                    numLineas++;
                }
                Br.close();
                return numLineas;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return numLineas;
    }
}