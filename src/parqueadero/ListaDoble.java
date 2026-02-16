
package parqueadero;


public class ListaDoble 
{
    //atributo propio y privado
    Nodo Start;//apuntador al primer nodo de la lista

    //constructor vacio
    public ListaDoble() 
    {
        Start=null;
    }

    //metodo para validar el contenido de la lista
   public boolean IsEmpty()
   {
      if(getStart()==null)
          return true;//esta vacia
      else
          return false;//tiene datos
   }
   //apuntadores auxiliares
   Nodo q;//recorridos en general
   Nodo End;//apuntador que se ubicará en el ultimo nodo en ejecucion
   Nodo p;//apuntados SOLO para el BUSCAR
   
   //metodo para insertar o crear la lista por final
   //el dato que ingresa va quedando de ultimo
public void CrearPorFinal(Object info)
{
if (IsEmpty()==true){
    setStart(new Nodo(null,info,null));
    End=getStart();
    }
else{
   End.setSiguiente(new Nodo(End, info, null));
   End=End.getSiguiente();

}//Fin si
}//fin de insertar nodo por final

//metodo para insertar o crear la lista por inicio
   //el dato que ingresa va quedando de primero
public void CrearPorInicio(Object info)
{
if (IsEmpty()==true) //lista vacia 
    setStart(new Nodo(null,info,null));
else
{
      getStart().setAnterior( new Nodo(null,info, getStart()));
      setStart(getStart().getAnterior());
}//Fin si
}//fin de insertar nodo por inicio

/*metodo que inserta un nodo al inicio*/
public void InsertarInicio(Object dato)
{
    if(IsEmpty()==false)//hay datos
    {
        getStart().setAnterior(new Nodo(null,dato,getStart()));
        setStart(getStart().getAnterior());
    }//fin si
    
}//fin insertar

/*metodo que inserta un nodo al final*/
public void InsertarUltimo(Object dato)
{
    if(IsEmpty()==false)//hay datos
    {
        UbicarUltimo();//ubicamos al apuntador End
        End.setSiguiente(new Nodo(End,dato,null));
    }//fin si
}//fin insertar

/*metodo que retorna y elimina el ultimo dato de la lista*/
public Object LiberarUltimo()
{
    Object dato=null;//variable local para el retorno
    if(IsEmpty()==false)//hay datos
    {   
        dato=getStart().getDato();//se retorna para saber que se elimina
        if(getStart().getSiguiente()==null)//lista de un solo nodo
        {
            getStart().finalize();
            setStart(null);
        }
        else//hay mas de un dato en la lista
        {
          UbicarUltimo();
          dato=End.getDato();
          End.getAnterior().setSiguiente(null);
          End.finalize();
        }//fin si
    }//fin si
    return dato;
}
/*metodo que retorna y elimina el primer dato de la lista*/
public Object LiberarPrimero()
{
    Object dato=null;//variable local para el retorno
    if(IsEmpty()==false)//hay datos
    {   
        dato=getStart().getDato();//se retorna para saber que se elimina
        if(getStart().getSiguiente()==null)//lista de un solo nodo
        {
            getStart().finalize();
            setStart(null);
        }
        else//hay mas de un dato en la lista
        {
            setStart(getStart().getSiguiente());
            getStart().getAnterior().finalize();
            getStart().setAnterior(null);
            
        }//fin si
    }//fin si
    return dato;
}//fin de liberar

/*metodo que libera despues de un buscar, o que el apuntador auxiliar p este ubicado*/
public void Liberar(ListaDoble objld)
{
    if(objld.p==objld.getStart())//si el dato esta de primero o es solo un dato
            {
                objld.LiberarPrimero();//de primero y se borra
            }
            else
            {
                if(objld.p.getSiguiente()==null)//el dato esta de ultimo
                    objld.LiberarUltimo();
                else//el dato esta en medio de dos nodos
                {
                    objld.p.getAnterior().setSiguiente(objld.p.getSiguiente());
                    objld.p.getSiguiente().setAnterior(objld.p.getAnterior());
                    objld.p.finalize();
                }//fin si
            }//fin si
    
}

/*metodo que retorna el contenido de la lista
comenzando en start*/
public String ConcatenarDesdeInicio()
{
    String texto="";//variable local de retorno
    if(IsEmpty()==false)//hay datos
    {
        q=getStart();//q desde inicio o start
        while(q!=null)
        {
            texto=texto+q.getDato().toString()+"\n";
            q=q.getSiguiente();//adelantamos en lista
        }//fin mientras
    }//fin si
    return texto;
}
    /*metodo para ubicar el apuntador End en el 
ultimo nodo de la lista*/
public void UbicarUltimo()
{
    if(IsEmpty()==false)//hay datos
    {
        End=getStart();//q desde inicio o start
        while(End.getSiguiente()!=null)
        {
            End=End.getSiguiente();//adelantamos en lista
        }//fin mientras
    }//fin si
}//si hay datos el apuntador End quedo ubicado en el ultimo nodo
    
/*metodo que retorna el contenido de la lista
comenzando en End*/
public String ConcatenarDesdeFinal()
{
    String texto="";//variable local de retorno
    if(IsEmpty()==false)//hay datos
    {
        UbicarUltimo();//End esta en ultimo nodo
        while(End!=null)
        {
            texto=texto+End.getDato().toString()+"\n";
            End=End.getAnterior();//hacia adelante desde el ultimo
        }//fin mientras
    }//fin si
    return texto;
}


    public Nodo getStart() {
        return Start;
    }

    public void setStart(Nodo Start) {
        this.Start = Start;
    }
    
    
    
    
}
