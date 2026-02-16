package parqueadero;

import javax.swing.JOptionPane;

public class Pila 
{
    //atributos propios y privados
    private int maxsize;//tamaño maximo del almacenamiento
    private int size;//contador de nodos para que no se pase de maximo tamaño
    private Nodo top,q;//apuntador al tope y auxiliar q
    
    //constructores
    public Pila()
    {}
    public Pila(int n)//condiciones iniciales del constructor
    {
        maxsize=n;
        top=null;
        size=0;
    }
         
    public boolean IsEmpty() 
    {   //si esta vacia
        return size <= 0;//modo abreviado ...es lo mismo
    }
    
    public boolean IsFull() 
    {//si esta llena o sea ya se llego al maximo
        if(size >= maxsize)
            return true;
        else 
            return false;
    }
    
          //  JOptionPane.showMessageDialog(null,"*****Pila LLena DESBORDAMIENTO DE PILA*****");//OPCIONAL
          
    public void Push(Object d)//apilar
    {
        if(IsFull()==false)//se van a colocar datos...problema?si esta llena
        {
            size=size + 1;//contador de nodos apilados
            q=new Nodo(d);//crear nuevo nodo con apuntador en q
            q.setSiguiente(top);//se enlaza el top con el nuevo para que quede de primero
            top=q;//se pasa el top para el nuevo por ser el ultimo en entrar
        }
        else//no hay datos
        {
            JOptionPane.showMessageDialog(null,"*****Pila Llena DESBORDAMIENTO DE PILA*****");
              //(OPCIONAL);opcional avisarle al usuario pila LLENA
        }
        //opcional avisarle al usuario "pila LLENA..Desbordamiento de pila"
    }//fin de push - apilar
    
    public Object Pop()
    {
        Object d=null;
        if(IsEmpty()==false)//se van a quitar  datos...problema?si esta vacia
        {
            d = top.getDato();//se toma el dato a desapilar para retornar
            if (top.getSiguiente() == null) //un solo nodo?
            {
                top.finalize();
                top=( null);//pila vacia
            } else {//hay mas de un dato
                q=top;//se coloca auxiliar q para liberar memoria
                top=(top.getSiguiente());//se pasa a top para el siguiente
                q.finalize();
            }
            size=size - 1;//contador de nodos apilados se disminuye en uno por desapilar
        }    
        else//no hay datos para desapilar
        {
            JOptionPane.showMessageDialog(null,"*****Pila Vacia SUBDESBORDAMIENTO DE PILA*****");
              //OPCIONAL);opcional avisarle al usuario pila VACIA
        }
        return d;
        
    }//fin  de pop - desapilar

    public Object  Peek()//vistazo al próximo en salir
    {
       Object dato=null; //dato para retornar
       if(IsEmpty()==false)//si hay datos
       {
           dato=top.getDato();//se toma el de arriba en la pila, ultimo en entrar, primero en salir
       }
      return dato;	
    }//Fin método pop



}//fin clase pila
