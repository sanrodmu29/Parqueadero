/*
----------------
      Nodo 
----------------
-Dato: objeto
- sig: Nodo
- ant: Nodo
----------------
Nodo()
Nodo(d)
Nodo(d,next)
Nodo(li,d,ld)
----------------

*/

package parqueadero;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Nodo 
{
    
    //atributos propios de la clase
    private Object dato;
    private Nodo siguiente, anterior;
    
    //constructor vacio
    public Nodo()
    {} 
    //fin método Nodo

    /*constructor que recibe el dato 
    y copia al nodo en su parte dato la informacion
     y coloca la liga o enlace en null*/
    public Nodo(Object d)
    {
        dato=d;
        siguiente=null;//siempre los nodos terminales
        
    }//fin método Nodo

     /*constructor que recibe el dato y en enlace 
    siguiente y copia el nodo en su parte dato la informacion
     y coloca la liga o enlace en la dirección de 
    memoria que recibe*/
    public Nodo(Object d, Nodo s) {
        this.dato = d;
        this.siguiente = s;
    }

    /*este constructor lo usaremos para la ingresar los datos en
    las tres partes del nodo doble*/
    
    public Nodo(Nodo li, Object info, Nodo ld )
    {
        anterior=li;
        dato=info;
        siguiente=ld;
    }
        
    
    @Override
    public String toString() {
        return dato.toString();
    }
    
    
     //destructor ojo, este metodo finalize se  genera, es el inicio de un destructor
    public void finalize()
    {   try {
        super.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//fin finalize

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    
    
    
}//fin clase nodo
