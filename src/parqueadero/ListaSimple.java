
package parqueadero;

public class ListaSimple 
{
    //atributos propios y privados
    private Nodo Start;//apuntador al primer nodo

    public ListaSimple() 
    {
        Start=null;//lista vacia condiciones iniciales
    }

    /*metodo para validar si la lista esta vacia o no*/
    public boolean IsEmpty()
    {
        if(Start==null)//condiciones iniciales
            return true;//está vacia
        else
            return false;//tiene al menos un dato
    //fin si
    }//fin isempty
    
    /*metodo que recibe un dato y lo inserta de primero
    en la lista, en caso de no haber lista la crea*/
    public void CrearPorInicio(Object d)
    {
        if(IsEmpty()==true)
            setStart(new Nodo(d));
        else
            setStart(new Nodo(d,getStart()));
        //fin is
    }//fin de crear por inicio
    
    //apuntadores auxiliares
    Nodo q;//para recorridos
    Nodo t,p;//para metodo buscar p queda en dato y t atras
    
        
    /*metodo que retorna en una cadena todo el contenido
    de la lista*/
    public String ConcatenarLista()
    {
        String cadena="";//local para el retorno
        if(IsEmpty()==false)//hay datos en la lista
        {
            q=getStart();//colocamos a q apuntando al primer nodo
            while(q!=null)//mientras llegue q a null
            {
                cadena=cadena+q.getDato().toString()+"\n";
                q=q.getSiguiente();//se adelanta en la lista
            }//fin mientras
        }//fin si
        return cadena;
    }//fin de concatenar
    
    /*metodo que recibe un dato y lo inserta en la lista por final,
    en caso de no haber lista se crea*/
    public void CrearPorFinal(Object d)
    {
        if(IsEmpty()==true)//si lista vacia
        {
            setStart(new Nodo(d));//se crea el primer nodo
            End=getStart();//se apunta además con q
        }   
        else//ya hay datos
        {
            UbicarUltimo();//debemos usar este metodo para que ubique un apuntador al final en este caso End
            End.setSiguiente(new Nodo(d));//se enlaza el nuevo nodo con el nodo final apuntado por End
            End=End.getSiguiente();//se pasa a End para el nuevo que quedó de ultimo
        }//fin si
            
    }//fin de crear por final
    
    /*este metodo ubica el apuntador End en el ultimo
    nodo de la lista*/
    Nodo End,z;//apuntador auxiliar que se ubica con el metodo al final
    public void UbicarUltimo()
    {
        if(IsEmpty()==false)//hay datos
        {
            End=getStart();//se ubica al inicio para el recorrido
            while(End.getSiguiente()!=null)//mientras se ubica en el ultimo
            {
                z=End;//Para que quede atras de End
                End=End.getSiguiente();//se adelanta
            }//fin mientras
            
        }//fin si
    }//fin ubicar ultimo
    
     //Metodo para insertar un dato de último, parte de la pregunta si hay datos en lista
    
    public void InsertarUltimo(Object d)
    {
        if(IsEmpty()==false)//hay datos
        {
            UbicarUltimo();
            End.setSiguiente(new Nodo(d));
        }//fin si
    }//fin insertarultimo

/*metodo que retorna y elimina el primer dato de la lista*/
public Object LiberarPrimero()
{
Object dato=null;//variable local para el retorno
if(IsEmpty()==false)//hay datos
{
     dato=getStart().getDato(); //tomamos el dato que se esta borrando
     q=getStart();//colocamos el auxiliar para poder liberar el espacio
     setStart(getStart().getSiguiente());
     q.finalize();
}//fin si
return dato;
}//fin
/*metodo que retorna y elimina el ultimo dato de la lista*/
public Object LiberarUltimo()
{
Object dato=null;
if(IsEmpty()==false)
{
      if(getStart().getSiguiente()==null)//lista de un solo nodo
     {         
        dato=getStart().getDato();//se toma el dato que se esta borrando 
	getStart().finalize();//lista va a quedar vacia
        setStart(null);//condiciones iniciales de lista vacia
     }
     else//lista con mas de un dato
     {
            UbicarUltimo();//ubicamos el apuntador en el ultimo y penultimo
            dato=End.getDato();//se toma el dato que se esta borrando
            End.finalize();//se libera el ultimo
            z.setSiguiente(null);//el penultimo ya queda como ultimo
     }//fin si
}//fin si
return dato;
}//fin	 

/*metodo que libera despues de un buscar, o que el apuntador auxiliar p y t esten ubicados*/
public void Liberar(ListaSimple objls)
{
    if(objls.p==objls.getStart())//si el dato esta de primero
            {
                objls.LiberarPrimero();
            }
            else
            {
                objls.UbicarUltimo();//ubicamos a End
                if(objls.p==objls.End)//el dato esta de ultimo
                    objls.LiberarUltimo();
                else//el dato esta en medio de dos nodos
                {
                    objls.t.setSiguiente(objls.p.getSiguiente());
                    objls.p.finalize();
                }//fin si
            }//fin si
}

//METODO QUE RETORNA EL NUMERO DE VEHICULOS DE LA LISTA
public int ContarElementos()
{
    int cont=0;//variable del retorno
    if(IsEmpty()==false)//hay datos
    {
        q=getStart();//se coloca q en start
        while(q!=null)
        {
            cont++;//se cuenta un vehiculo
            q=q.getSiguiente();//se adelanta en la lista
        }//fin mientras
    }//fin si
    return cont;
}//fin de contar elementos
    
    
    public Nodo getStart() {
        return Start;
    }

    public void setStart(Nodo Start) {
        this.Start = Start;
    }
    
        
    
}//fin clase lista simple
