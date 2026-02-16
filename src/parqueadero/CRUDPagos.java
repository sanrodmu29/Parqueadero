
//AUN NO SE HA PROBADO!!!!!!
package parqueadero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
public class CRUDPagos 
{
    
    //--------------------------------------------------------------
    /* Método que busca un pago en el archivo Pagos.txt por su ID
       Retorna true si lo encuentra, false si no existe
    --------------------------------------------------------------*/
    public boolean Buscar(Archivos objArch, String idBuscar) {
        boolean sw = false; // retorno

        try {
            // variables locales
            String id, idC, pl;
            LocalDate fec;
            double mon;
            String Reg[];

            // abrir archivo modo lectura
            objArch.AbrirArchivoModoLectura("Pagos.txt");
            Reg = objArch.LeerRegistro(5);

            // recorrer archivo
            while (Reg != null) {
                id = Reg[0];
                pl = Reg[1];
                idC = Reg[2];
                mon = Double.parseDouble(Reg[3]);
                fec = LocalDate.parse(Reg[4]);

                if (id.equalsIgnoreCase(idBuscar)) {
                    sw = true;
                }

                Reg = objArch.LeerRegistro(5);
            }

            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente***");
        }

        return sw;
    }// fin buscar

    //--------------------------------------------------------------
    /* Método que ingresa un nuevo pago si no existe en el archivo */
    //--------------------------------------------------------------
    public void IngresarPago(String idp, Archivos objArch, ListaSimple listavehiculos, ListaDoble listaclientes,Pila serv,ListaSimple detallepago) {
        Pago objp = new Pago();

        // si el pago no existe, se graba
        if (Buscar(objArch,idp) == false) {
            objp=objp.IngresarDatos(idp, listavehiculos, listaclientes, serv, detallepago);
            GrabarPago(objArch, objp);
            JOptionPane.showMessageDialog(null, "***** Pago registrado correctamente *****");
        } else {
            JOptionPane.showMessageDialog(null, "***** El ID del pago ya existe en el archivo *****");
        }
    }// fin ingresar pago

    //--------------------------------------------------------------
    /* Método que graba físicamente el pago en el archivo */
    //--------------------------------------------------------------
    public void GrabarPago(Archivos objArch, Pago objp) {
        try {
            String cadena = "";

            objArch.AbrirArchivoModoEscritura("Pagos.txt");

            cadena = objp.EstructuraReg(); // pago convertido a cadena
            objArch.EscribirRegistro("" + cadena);

            objArch.CerrarArchivoModoEscritura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***** Error al grabar el pago *****");
        }
    }// fin grabar pago

    //--------------------------------------------------------------
    /* Método que muestra todo el contenido del archivo Pagos.txt */
    //--------------------------------------------------------------
    public String MostrarTodo(Archivos objArch) {
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
                cadena = cadena + objp.EstructuraReg() + "\n";

                Reg = objArch.LeerRegistro(5);
            }

            objArch.CerrarArchivoModoLectura();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente***");
        }

        return cadena;
    }// fin mostrar todo

    //--------------------------------------------------------------
    /* Método que consulta un pago específico por su ID */
    //--------------------------------------------------------------
    public Pago Consultar(Archivos objArch, String idf) {
        Pago objp, objpC = null;

        if (Buscar(objArch, idf) == true) {
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

                    objp = new Pago(id, pl, idC, mon, fec);

                    if (id.equalsIgnoreCase(idf))
                        objpC = objp;

                    Reg = objArch.LeerRegistro(5);
                }

                objArch.CerrarArchivoModoLectura();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "***Archivo leído y cerrado correctamente***");
            }

        } else {
            JOptionPane.showMessageDialog(null, "***** El pago con ese ID no existe en el archivo *****");
        }

        return objpC;
    }// fin consultar

    //--------------------------------------------------------------
    /* Método que cuenta las líneas (registros) en el archivo */
    //--------------------------------------------------------------
    public int ContarLineas(String arch) {
        int numLineas = 0;

        try {
            File archivo = new File(arch);
            if (!archivo.isFile()) {
                return 0;
            } else {
                FileReader lectura = new FileReader(arch);
                BufferedReader Br = new BufferedReader(lectura);

                while (Br.readLine() != null) {
                    numLineas++;
                }

                Br.close();
                return numLineas;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numLineas;
    }// fin contar líneas

    /* Método que COPIA toda la lista simple de pagos al archivo Pagos.txt
       BORRANDO todo lo que haya previamente en el archivo */
    public void CopiarListaPagosAArchivo(Archivos objArch, ListaSimple lsPagos) {
        try {
            // 1. Borrar el contenido previo del archivo para sobreescribir
            objArch.BorrarContenido("Pagos.txt");

            // 2. Abrir el archivo en modo escritura
            objArch.AbrirArchivoModoEscritura("Pagos.txt");

            // 3. Recorrer la lista simple usando el nodo auxiliar q
            Nodo q = lsPagos.getStart(); // Obtenemos el inicio de la lista
            
            while (q != null) {
                // Extraemos el objeto Pago del nodo y hacemos casting
                Pago objP = (Pago) q.getDato();
                
                // Obtenemos la cadena con formato csv usando el método de la clase Pago
                String cadena = objP.EstructuraReg();
                
                // Escribimos el registro en el archivo
                objArch.EscribirRegistro(cadena);
                
                // Avanzamos al siguiente nodo de la lista
                q = q.getSiguiente();
            }

            // 4. Cerrar el archivo
            objArch.CerrarArchivoModoEscritura();

            JOptionPane.showMessageDialog(null, "***** La lista de PAGOS fue copiada al archivo correctamente *****");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al copiar la lista de pagos al archivo: " + e.getMessage());
        }
    }
    
}
