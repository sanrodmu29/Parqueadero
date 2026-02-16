package parqueadero;

import javax.swing.JOptionPane;

public class Cola 
{
    //atributos propios y privados
    private int maxsize;//tamaño maximo del almacenamiento
    private int size;//contador de nodos para que no se pase de maximo tamaño
    private  Nodo Final,Front,q;//apuntador a la cabeza de la fila y  apuntador a la cola de la fila y q auxiliar
    
    //constructores
    public Cola()
    {}
    public Cola(int n)//condiciones iniciales del constructor cola
    {
        maxsize=n;
        Final=null;
        Front=null;
        size=0;
        
    }
        
    public boolean IsEmpty() 
    {   //si esta vacia
        return size<= 0; //no hay nodos
    }

    public boolean IsFull() 
    {   //si esta llena o sea ya se llego al maximo
        return size>=maxsize;
    }
           
    public void Push(Object d)//encolar
    {
        if (IsFull()==false)//se le puede ingresar informacion
        { 
            size=size+ 1;//contador de nodos encolados
            q=new Nodo(d);//crear nuevo nodo con apuntador en q
            if(Front==null)//si es el primer dato
               {
                Front=q;
                Final=q;//ambos apuntadores para el nuevo nodo
                }
             else//si ya hay un dato
                {
                 Final.setSiguiente(q);//se enlaza el siguiente de Final al q
                 Final=q;//se pasa el Final para q .. el nuevo nodo
                }//fin si 
        }//fin si
        else
        {
        //sino pila llena//  
            JOptionPane.showMessageDialog(null,"*****Pila LLena DESBORDAMIENTO DE PILA*****");//OPCIONAL//opcional
        }
    }//fin de push - encolar
    
    public Object Pop()//desencolar
    {
        Object d=null;//dato para retornar el desencolado
        if (IsEmpty()==false)//si hay datos
        {
             size=size-1;//contador de nodos encolados se disminuye el desencolado
             d = Front.getDato();//se toma el dato que se va a desencolar para el retorno
            if(Front==Final)//si hay un solo nodo
            {
                Front.finalize();
                Front=null;//cola vacia!!
                Final=null;//cola vacia!!
            }
            else//hay mas de un dato
            {
                q=Front;//se coloca el apuntador q para liberar el espacio de memoria
                Front=Front.getSiguiente();//se pasa el apuntador Front al siguiente
                q.finalize();//llamada al destructor
            }//fin si
            }
        else
        {
        //sino pila vacia//  
            JOptionPane.showMessageDialog(null,"*****Pila Vacia SUBDESBORDAMIENTO DE PILA*****");//OPCIONAL
        }//fin si
        
        return d;
    }//fin de pop - encolar

    public Object  Peek()//vistazo al próximo en salir
    {
       Object dato=null; //dato para retornar
       if(IsEmpty()==false)//si hay datos
       {
           dato=Front.getDato();//se toma el dato que se va a retornar
       }
       return dato;//se retorna el dato sin sacarlo de la cola
    }//Fin método peek


   
   
}//fin clase cola
