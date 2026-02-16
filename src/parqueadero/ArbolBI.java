
package parqueadero;

import javax.swing.JOptionPane;

public class ArbolBI 
{
   
    private Nodo raiz;

    //constructor vacio, inicializa el apuntador raiz, condiciones iniciales de arbol binario
    public ArbolBI()
    {
    raiz=null;
    }
   
    //auxiliares
    Object info ;
    Nodo q, aux; 
    String texto="",id;
    int cont=0;
    boolean sw;
    Empleado objE=new Empleado();//para el ingreso de datos
    
 //metodo booleano para saber si el arbol esta vacio o no   
 public boolean Estavacio()
 {
        return raiz==null; //“ARBOL VACIO!!!“
 }//fin esta vacio
  
 /*existen muchas formas para la creación de arbol binario
 esta es una de muchas, nuestra forma parte de crear primero el nodo raiz y luego
 recursivamente se crean sus dos subramas*/
 //este metodo crea el nodo raiz unicamente
 public void CrearRaiz(Object info) 
    {   
       setRaiz(new Nodo(null,info,null)); 
       q=getRaiz();
    }
    //fin crearRaiz
  
  
 /*este metodo recibe un apuntador y en esa posicion crea un nodo, es udado por 
 el metodo Insertar nodos*/
 public void IngresarHijos(Nodo q, Pila pturnos) 
 {       
      objE=new Empleado();
      id=Validaciones.LeerString("id del empleado para el empleado de: "+q.getDato());
      objE=objE.IngresarDatos(id, pturnos);
      aux=new Nodo(null,objE,null); 
    
 }//fin de ingresar hijo
 
 
/*este método recibe el apuntador y usando el metodo ingresar hijos, le da la opcion al 
 usuario de ingresar dato por izquierda(anterior) o por derecha(siguiente), aun no es
 metodo recursivo*/
 public void InsertarNodos(Nodo q, Pila pturnos)
{
    int resp;
   resp=JOptionPane.showConfirmDialog(null,"Ingresar empleado izquierdo (Anterior) de "+((Empleado)q.getDato()).Nombre,"Ingreso de datos",JOptionPane.YES_NO_OPTION);
   
   if (resp==JOptionPane.YES_OPTION)
   {
            IngresarHijos(q,pturnos); 
            q.setAnterior(aux); 
   }//fin si
   
   resp=JOptionPane.showConfirmDialog(null,"Ingresar empleado derecho (Siguiente) de "+((Empleado)q.getDato()).Nombre,"Ingreso de datos",JOptionPane.YES_NO_OPTION);
   
   if (resp==JOptionPane.YES_OPTION)
   {
            IngresarHijos(q,pturnos); 
            q.setSiguiente(aux); 
   }//fin si
}//Fin InsertarNodos

 
 
//metodo recursivo para el arbol usa el metodo insertar nodos por izq o por der
    public void Crear(Nodo q,Pila pturnos)
    {        
      //caso base o caso degenerado 
          if(q!=null) 
          {//este metodo esta en preorden
            InsertarNodos(q,pturnos); 
            //llamada recursiva por la izquierda
            Crear(q.getAnterior(),pturnos) ;
            //llamada recursiva por la derecha
            Crear(q.getSiguiente(),pturnos) ;
          }// fin si
    }//fin crear
    
    
    
    /*este metodo es necesario para inicializar auxiliares porque si las incicia en el metodo recursivo
    es como si la estuvieran inicializando en un ciclo (obviamente hay mas formas)*/
    public void Inicializar()
    {
        texto=""; 
        cont=0;
        sw=false;
    }
    
    //retorna el contenido del arbol en una cadena en inorden
    public String InOrden(Nodo q)
    {        
      
      //caso base o caso degenerado 
          
        if(q!=null) 
          {
             //llamada recursiva por la izquierda
            InOrden(q.getAnterior()) ;//este metodo esta en preorden
            //raiz
            texto=texto+"( "+q.getDato()+" )\n"; 
            //llamada recursiva por la derecha
            InOrden(q.getSiguiente()) ;
          }// fin si
          return texto;
    }//fin crear
    
    
    //retorna el contenido del arbol en una cadena en preorden
     public String PreOrden(Nodo q)
    {        
      
      //caso base o caso degenerado 
          
        if(q!=null) 
          {
            //raiz
            texto=texto+"( "+q.getDato()+" )\n";
            //llamada recursiva por la izquierda
            PreOrden(q.getAnterior()) ;//este metodo esta en preorden
            //llamada recursiva por la derecha
            PreOrden(q.getSiguiente()) ;
          }// fin si
          return texto;
    }//fin preorden
    
     //retorna el contenido del arbol en una cadena en postorden
     public String PostOrden(Nodo q)
    {        
      
      //caso base o caso degenerado 
          
        if(q!=null) 
          {
            //llamada recursiva por la izquierda
            PostOrden(q.getAnterior()) ;//este metodo esta en preorden
            //llamada recursiva por la derecha
            PostOrden(q.getSiguiente()) ;
            //raiz
            texto=texto+"( "+q.getDato()+" )\n";
          }// fin si
          return texto;
    }//fin postorden
     


     //retorna en una cadena los hermanos o pares
     public String BuscarHermanos(Nodo q)
    {        
      
      //caso base o caso degenerado 
          
        if(q!=null) 
          {
            //raiz
            if(EsRama(q)==true)
                texto=texto+"( "+q.getAnterior().getDato()+" hermano o par de "+q.getSiguiente().getDato()+" )\n";
            
            //llamada recursiva por la izquierda
            BuscarHermanos(q.getAnterior()) ;//este metodo esta en preorden
            //llamada recursiva por la derecha
            BuscarHermanos(q.getSiguiente()) ;
            }// fin si
          return texto;
    }//fin postorden
     
     
     //Metodo que busca todos los hermanos en el arbol desde el padre 
    //y se concatenan en un String
    //Si no tiene hermano no se concatena al String
    private String concatHermanos(Nodo padre) {
        if (padre == null) { //Si el padre no es un nodo vacio
            return "";
        }
        
        String text = "";
        
        if (padre.getAnterior() != null && padre.getSiguiente() != null) {
            text += "Hermanos:\n" 
                    + padre.getAnterior().getDato().toString() 
                    + "\n" 
                    + padre.getSiguiente().getDato().toString() 
                    + "\n\n";
        }

        text += concatHermanos(padre.getAnterior());
        text += concatHermanos(padre.getSiguiente());
        
        return text;
    } //fin concatHermanos()
    
    public String mostrarHermanos(){
        return concatHermanos(raiz);
    }
     
 /*retorna verdadero si el apuntador que recibe esta apuntando a un nodo rama,
   o sea sus apuntadores izquierdo(anterior) y/o derecho(siguiente) NO son null*/
  public boolean EsRama(Nodo q)
    {
      int sw=0;
        if(q.getAnterior()!=null && q.getSiguiente()!=null){
            sw=1;
        }else{
            return false;
        }
      return sw==1;
    }

  
 /*retorna verdadero si el apuntador que recibe esta apuntando a un nodo terminal o nodo hoja,
   o sea sus apuntadores izquierdo(anterior) y derecho(siguiente) son null*/
  public boolean EsHoja(Nodo q)
    {
        int sw=0;
        if(q.getAnterior()==null && q.getSiguiente()==null){
            sw=1;
        }else{
            return false;
        }
      return sw==1;
    }
   
  /*retorna un contador con el numero de nodos del arbol*/
public int NumeroElementos(Nodo q)
{
    // caso base:
    if(q == null)
        return 0;
   // 1 + sub arbol izquierdo + sub arbol derecho
    return 1 + NumeroElementos(q.getAnterior()) + NumeroElementos(q.getSiguiente());

}//hay que probarlo en ejecucion
 
public int Peso(Nodo q)
{
    // caso base:
    if(q == null)
        return 0;
   // //verificar que sea hoja
    if (EsHoja(q)!= false) 
        return 1;
    return Peso(q.getAnterior()) + Peso(q.getSiguiente());
}



public int Altura() {
    return Altura(raiz);
}

public int Altura(Nodo q) {
    if (q == null)
        return 0;

    int izquierda = Altura(q.getAnterior());
    int derecha = Altura(q.getSiguiente());

    int mayor = (izquierda > derecha) ? izquierda : derecha;

    return 1 + mayor;
}

public String listarHijosIzquierdos(Nodo q) 
{
    if (q == null) return texto;

    // Si tiene hijo izquierdo, lo agregamos
    if (q.getAnterior() != null) {
        texto += "( " + q.getAnterior().getDato() + " )\n";
    }

    // Recorremos ambos lados del árbol
    listarHijosIzquierdos(q.getAnterior());
    listarHijosIzquierdos(q.getSiguiente());

    return texto;
}

   

//prueba de escritorio para tener un organigrama inicial
public void PruebaEscritorioCrearAdministradorRaiz(Nodo q)//primera parte creacion de raiz
{
    objE=new Empleado("1070","Isabella","Castro","341-7891235","chavela@yahoo","Pellegrini404","Administrador","03N");
    CrearRaiz(objE);
}



 public void PruebaEscritorioOrganigrama(Nodo q)//segunda parte - arbol completo de 8 nodos
 {
    q.setAnterior(new Nodo(null,new Empleado("1010","Lucía","Mendoza","351-7894562","Lucha@gmail","CalleFlores123","Gerente","02O"),null));//empleado izq de administrador 
    q.setSiguiente(new Nodo(null,new Empleado("1020","Mateo","Ríos","11-6543210","Teo@gmail","AvLibertad456","Supervisor","02O"),null));//empleado der de administrador
    
    q.getAnterior().setAnterior(new Nodo(null,new Empleado("1050","Camila","Herrera","Carrera20","Kami@gmail","Carrera20","Cajero","03D"),null));//empleado izq de gerente
    q.getAnterior().setSiguiente(new Nodo(null,new Empleado("1000","Javier","Rocha","376-4567891","Roca@gmail","Sarmiento707","Cajero","02O"),null));//empleado der de gerente
    
    q.getSiguiente().setAnterior(new Nodo(null,new Empleado("1030","Valeria","Soto","261-8529630","Vale@hotmail","SanMartín789","Operario","01N"),null));//empleado izq de supervisor
    q.getSiguiente().setSiguiente(new Nodo(null,new Empleado("1060","Alejandro","Morales","221-4561237","Alecho@gmail","Calle7-303","Operario","03N"),null));//empleado der de supervisor
    
    q.getSiguiente().getAnterior().setAnterior(new Nodo(null,new Empleado("1040","Santiago","Vega","299-7412580","vegano@yahoo","Belgrano101","Lavador","02N"),null));//empleado izq de operario
        
 }

  
    /**
     * @return the raiz
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    
    

    /* Método público para buscar y mostrar los ancestros de un ID dado */
    public void MostrarAncestros(String id) {
        Inicializar(); // Reinicia la variable 'texto' a ""
        
        // Llamamos al método recursivo enviando la raíz
        if (BuscarAncestrosRecursivo(getRaiz(), id) == true) {
            // Si retorna verdadero, se encontró el nodo
            if (texto.equals("")) {
                JOptionPane.showMessageDialog(null, "El empleado con ID " + id + " es la RAÍZ del árbol (No tiene ancestros).");
            } else {
                JOptionPane.showMessageDialog(null, "*** ANCESTROS DE " + id + " ***\n(Desde la Raíz hasta el Padre)\n\n" + texto);
            }
        } else {
            // Si retorna falso, el ID no existe
            JOptionPane.showMessageDialog(null, "El empleado con ID " + id + " NO existe en el árbol.");
        }
    }

    /* Método privado recursivo que busca el nodo y construye la cadena de ancestros */
    private boolean BuscarAncestrosRecursivo(Nodo q, String id) {
        if (q != null) {
            // Extraemos el dato del nodo casteado a Empleado
            Empleado objEmp = (Empleado) q.getDato();

            // 1. Verificar si encontramos el nodo buscado (Caso Base de Éxito)
            // Se usa getIdUser() asumiendo que Empleado hereda de Usuario
            if (objEmp.getIdUser().equalsIgnoreCase(id)) {
                return true; // Le avisamos al padre que lo encontramos
            }

            // 2. Si no es, buscamos por la izquierda (Anterior) O por la derecha (Siguiente)
            if (BuscarAncestrosRecursivo(q.getAnterior(), id) == true || BuscarAncestrosRecursivo(q.getSiguiente(), id) == true) {
                
                // 3. Si alguno de los hijos retorna true, significa que 'q' es un ANCESTRO
                // Concatenamos la información de 'q' al inicio de 'texto' para ordenarlos (Raíz -> Padre)
                texto = "( " + objEmp.getNombre() + " - Cargo: " + objEmp.getCargo() + " )\n" + "   |\n" + texto;
                
                return true; // Retornamos true para seguir avisando hacia arriba
            }
        }
        return false; // Caso Base de Fallo (nodo nulo o no encontrado en esta rama)
    }
    
  // ---------------------------------------------------------------------
    // BUSCADOR AUXILIAR: Retorna el OBJETO Nodo dado un ID (necesario para poder modificarlo)
    // ---------------------------------------------------------------------
    private Nodo BuscarNodoRetorno(Nodo q, String idBusqueda) {
        if (q != null) {
            // Convertimos el dato genérico a Empleado para ver su ID
            Empleado emp = (Empleado) q.getDato();
            
            // Si lo encontramos, retornamos el puntero a ese nodo
            if (emp.getIdUser().equalsIgnoreCase(idBusqueda)) {
                return q; 
            }
            
            // Si no, buscamos por la izquierda
            Nodo encontradoIzq = BuscarNodoRetorno(q.getAnterior(), idBusqueda);
            if (encontradoIzq != null) return encontradoIzq;
            
            // Si no, buscamos por la derecha
            return BuscarNodoRetorno(q.getSiguiente(), idBusqueda);
        }
        return null; // No existe en esta rama
    }

    // ---------------------------------------------------------------------
    // MÉTODO 10: INSERTAR NUEVA HOJA A UN PADRE ESPECÍFICO
    // ---------------------------------------------------------------------
    public void InsertarNuevaHoja(String idPadre, Pila pturnos) {
        // 1. Buscamos el nodo del padre donde queremos insertar
        Nodo padre = BuscarNodoRetorno(raiz, idPadre);

        if (padre == null) {
            JOptionPane.showMessageDialog(null, "Error: El empleado padre con ID " + idPadre + " NO existe en el árbol.");
            return;
        }

        // 2. Verificamos disponibilidad de espacios (Hijos nulos)
        boolean izqLibre = (padre.getAnterior() == null);
        boolean derLibre = (padre.getSiguiente() == null);

        if (!izqLibre && !derLibre) {
            JOptionPane.showMessageDialog(null, "El empleado " + idPadre + " ya tiene sus dos lados ocupados.\nNo se puede insertar más hojas directas aquí.");
            return;
        }

        // 3. Decidir dónde insertar
        int opcion = -1; // 0=Izq, 1=Der
        
        if (izqLibre && derLibre) {
            // Ambos libres, dejar elegir al usuario
            String[] botones = {"Izquierda (Anterior)", "Derecha (Siguiente)"};
            opcion = JOptionPane.showOptionDialog(null, "¿En qué posición desea agregar al nuevo empleado?", "Elegir Ubicación", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        } else if (izqLibre) {
            JOptionPane.showMessageDialog(null, "Solo hay espacio a la IZQUIERDA. Se insertará allí.");
            opcion = 0;
        } else {
            JOptionPane.showMessageDialog(null, "Solo hay espacio a la DERECHA. Se insertará allí.");
            opcion = 1;
        }

        // 4. Pedir datos y crear el nodo
        // Reutilizamos lógica de tus métodos existentes
        objE = new Empleado();
        String nuevoId = Validaciones.LeerString("Ingrese ID del NUEVO empleado:");
        
        // Verificamos que el nuevo ID no exista ya en el árbol para evitar duplicados
        if (BuscarNodoRetorno(raiz, nuevoId) != null) {
            JOptionPane.showMessageDialog(null, "¡El ID " + nuevoId + " ya existe en el árbol! Cancelando inserción.");
            return;
        }

        objE = objE.IngresarDatos(nuevoId, pturnos);
        Nodo nuevoNodo = new Nodo(null, objE, null); // Hoja nueva (sus hijos son null)

        // 5. Enlazar al padre
        if (opcion == 0) {
            padre.setAnterior(nuevoNodo);
        } else if (opcion == 1) {
            padre.setSiguiente(nuevoNodo);
        }

        JOptionPane.showMessageDialog(null, "¡Nueva hoja insertada exitosamente debajo de " + idPadre + "!");
    }
    
    // ---------------------------------------------------------------------
    // MÉTODO 11: INSERTAR SUBÁRBOL (Rama completa)
    // ---------------------------------------------------------------------
    public void InsertarSubArbol(String idPadre, Pila pturnos) {
        //  Buscamos el nodo padre
        Nodo padre = BuscarNodoRetorno(raiz, idPadre);

        if (padre == null) {
            JOptionPane.showMessageDialog(null, "Error: El empleado padre con ID " + idPadre + " NO existe.");
            return;
        }

        //  Verificamos espacios disponibles
        boolean izqLibre = (padre.getAnterior() == null);
        boolean derLibre = (padre.getSiguiente() == null);

        if (!izqLibre && !derLibre) {
            JOptionPane.showMessageDialog(null, "El empleado " + idPadre + " ya tiene ambos lados ocupados. No se puede insertar subárbol.");
            return;
        }

        //  Decidir dónde iniciar el subárbol
        int opcion = -1; // -1 indica que no se ha elegido nada

        if (izqLibre && derLibre) {
            String[] botones = {"Izquierda", "Derecha"};
            opcion = JOptionPane.showOptionDialog(null, "El padre está libre por ambos lados.\n¿Dónde desea iniciar el subárbol?", 
                    "Insertar Subárbol", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
        } else if (izqLibre) {
            JOptionPane.showMessageDialog(null, "Se iniciará el subárbol por la IZQUIERDA (único espacio libre).");
            opcion = 0;
        } else {
            JOptionPane.showMessageDialog(null, "Se iniciará el subárbol por la DERECHA (único espacio libre).");
            opcion = 1;
        }

        //  Crear la RAÍZ del subárbol
        // Usamos la misma lógica de ingreso de datos
        objE = new Empleado();
        String idNuevo = Validaciones.LeerString("Ingrese el ID de la RAÍZ del nuevo subárbol:");
        
        // Validación básica para no repetir ID
        if (BuscarNodoRetorno(raiz, idNuevo) != null) {
            JOptionPane.showMessageDialog(null, "El ID " + idNuevo + " ya existe. Cancelando.");
            return;
        }

        objE = objE.IngresarDatos(idNuevo, pturnos);
        Nodo nuevoNodo = new Nodo(null, objE, null); // Creamos el nodo

        //  Enlazar al padre
        if (opcion == 0) {
            padre.setAnterior(nuevoNodo);
        } else if (opcion == 1) {
            padre.setSiguiente(nuevoNodo);
        }

        // Llamamos al método CREAR recursivo sobre el nuevo nodo
        // Esto permite que el usuario siga agregando hijos a este nuevo nodo
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea agregar hijos a " + objE.getNombre() + " (Crear resto del subárbol)?", 
                "Continuar Subárbol", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            Crear(nuevoNodo, pturnos); // Reutilizamos tu método recursivo existente
        }
    }
   
    // ---------------------------------------------------------------------
    // MÉTODO 12: LISTAR EMPLEADOS POR CARGO 
    // ---------------------------------------------------------------------
    public String ListarPorCargo(Nodo q, String cargoBuscado) {
        if (q != null) {
            // 1. Ir a la izquierda (Recursividad)
            ListarPorCargo(q.getAnterior(), cargoBuscado);

            // 2. Analizar el nodo actual (Raíz)
            Empleado emp = (Empleado) q.getDato();
            
            // Comparamos el cargo (ignorando mayúsculas/minúsculas)
            // Asumimos que tu clase Empleado tiene el método getCargo()
            if (emp.getCargo().equalsIgnoreCase(cargoBuscado)) {
                // Si coincide, lo acumulamos en la variable global 'texto'
                texto = texto + emp.toString() + "\n";
            }

            // 3. Ir a la derecha (Recursividad)
            ListarPorCargo(q.getSiguiente(), cargoBuscado);
        }
        return texto;
    }
    
    // ---------------------------------------------------------------------
    // AUXILIAR: Retorna un String con la ruta desde la Raíz hasta el ID buscado
    // Ejemplo de retorno: "100,50,20" (IDs separados por coma)
    // ---------------------------------------------------------------------
    private String ObtenerRutaDesdeRaiz(Nodo q, String idBuscado) {
        if (q == null) {
            return null; // Camino cerrado
        }
        
        Empleado emp = (Empleado) q.getDato();
        
        // 1. Si encontramos el nodo, retornamos su ID
        if (emp.getIdUser().equalsIgnoreCase(idBuscado)) {
            return emp.getIdUser(); 
        }
        
        // 2. Buscamos por la IZQUIERDA
        String rutaIzq = ObtenerRutaDesdeRaiz(q.getAnterior(), idBuscado);
        if (rutaIzq != null) {
            // Si lo encontró por allá, anteponemos mi ID al camino y retornamos
            return emp.getIdUser() + "," + rutaIzq; 
        }
        
        // 3. Buscamos por la DERECHA
        String rutaDer = ObtenerRutaDesdeRaiz(q.getSiguiente(), idBuscado);
        if (rutaDer != null) {
            // Si lo encontró por allá, anteponemos mi ID al camino y retornamos
            return emp.getIdUser() + "," + rutaDer;
        }
        
        return null; // No estaba por ningún lado
    }

    // ---------------------------------------------------------------------
    // MÉTODO 13: CALCULAR Y MOSTRAR CAMINO ENTRE DOS NODOS (A -> B)
    // ---------------------------------------------------------------------
    public void MostrarCaminoEntreNodos(String idOrigen, String idDestino) {
        // 1. Obtenemos las rutas individuales desde la Raíz
        String rutaA = ObtenerRutaDesdeRaiz(raiz, idOrigen);
        String rutaB = ObtenerRutaDesdeRaiz(raiz, idDestino);
        
        // Validaciones
        if (rutaA == null) {
            JOptionPane.showMessageDialog(null, "El nodo Origen " + idOrigen + " NO existe en el árbol.");
            return;
        }
        if (rutaB == null) {
            JOptionPane.showMessageDialog(null, "El nodo Destino " + idDestino + " NO existe en el árbol.");
            return;
        }

        // 2. Convertimos los Strings a Arreglos para compararlos
        String[] vectorA = rutaA.split(","); // Ruta del Origen
        String[] vectorB = rutaB.split(","); // Ruta del Destino
        
        // 3. Encontramos el punto de divergencia (Ancestro Común)
        // Avanzamos mientras los caminos sean iguales
        int indiceComun = 0;
        while (indiceComun < vectorA.length && indiceComun < vectorB.length && 
               vectorA[indiceComun].equals(vectorB[indiceComun])) {
            indiceComun++;
        }
        // El while se rompe cuando son diferentes, así que el último igual fue el anterior
        indiceComun--; 

        // 4. Construimos el texto del camino final
        String caminoFinal = "Ruta de " + idOrigen + " a " + idDestino + ":\n\n";
        
        // PASO A: Subir desde el Origen hasta el Ancestro Común
        // Recorremos vectorA hacia atrás
        for (int i = vectorA.length - 1; i > indiceComun; i--) {
            caminoFinal += "[" + vectorA[i] + "] (Subir) \n   |\n   v\n";
        }
        
        // PASO B: El punto de giro (Ancestro)
        caminoFinal += "** [" + vectorA[indiceComun] + "] (Punto de Conexión) **\n";
        
        // PASO C: Bajar desde el Ancestro hasta el Destino
        // Recorremos vectorB hacia adelante
        for (int i = indiceComun + 1; i < vectorB.length; i++) {
            caminoFinal += "   |\n   v\n[" + vectorB[i] + "] (Bajar)\n";
        }
        
        // Mostrar resultado
        JOptionPane.showMessageDialog(null, caminoFinal);
    }
    
    // ---------------------------------------------------------------------
    // Recorre el árbol y guarda en archivo
    // ---------------------------------------------------------------------
    public void GenerarCopiaSeguridad(Nodo q) {
        if (q != null) {
            // 1. Procesar la Raíz (El nodo actual)
            Empleado emp = (Empleado) q.getDato();
            
            // Armamos la línea igual que en tus otros CRUDs (separado por comas)
            // Asumimos que Empleado tiene los get correspondientes o hereda de Usuario
            String linea = emp.getIdUser() + "," + 
                           emp.getNombre() + "," + 
                           emp.getApellido() + "," + 
                           emp.getCargo() + "," + 
                           emp.getEmail(); 
                           // Puedes agregar más atributos si tienes (Celular, Dirección, etc.)
            
            // Acumulamos en la variable global 'texto' con un salto de línea
            texto = texto + linea + "\n";

            // 2. Recorrer Izquierda (Recursivo)
            GenerarCopiaSeguridad(q.getAnterior());

            // 3. Recorrer Derecha (Recursivo)
            GenerarCopiaSeguridad(q.getSiguiente());
        }
    }
    
}//fin clase arbol BI
