package parqueadero;

import javax.swing.JOptionPane;

/**
 *
 * @author docenteitm
 */
public class Parqueadero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       ManejoListas objml=new ManejoListas();
       
       ListaSimple objls=new ListaSimple();//vehiculos
       ListaSimple objlsPago=new ListaSimple();//pagos
       ListaSimple objlsDetallePago=new ListaSimple();//detalle de pago
       
       ListaDoble objld=new ListaDoble();//clientes
       ListaDoble objldEmp=new ListaDoble();//empleados
       ListaDoble objldDS=new ListaDoble();//detalle de servicios que es como prestar los servicios
       
              
       Archivos objArc=new Archivos();
       CRUDVehiculo objCRUDV=new CRUDVehiculo();
       CRUDCliente objCRUDC=new CRUDCliente();
       CRUDCeldas objCRUDCel=new CRUDCeldas();
       CRUDTurnos objCRUDT=new CRUDTurnos();
       CRUDServicio objCRUDS=new CRUDServicio();
       CRUDEmpleado objCRUDE=new CRUDEmpleado();
       CRUDDetalleServicio objCRUDDS=new CRUDDetalleServicio();
       CRUDConvenio objCRUDCon=new CRUDConvenio();
       CRUDPagos objCRUDPago=new CRUDPagos();
       CRUDDetallesPago objCRUDDetPag=new CRUDDetallesPago();
               
       Parqueadero objpar=new Parqueadero();
       
       
       Pila objp=new Pila(100000);//pila de turnos
       Pila objpa=new Pila(100000);//pila auxiliar
       Pila objpSer=new Pila(100000);//pila de servicios
       Pila objpaSer=new Pila(100000);//pila auxiliar de servicios
       
       Cola objc=new Cola(100000);//cola de celdas
       Cola objca=new Cola(100000);//cola auxiliar
       Cola objColaConvenio=new Cola(100000);//cola de convenios
       Cola objcoa=new Cola(100000);//cola auxiliar, se pueden usar las otras auxiliares , peros si estamos con combinaciÃ³n de pronto hay conflicto
       
       
       ManejoPila objmp=new ManejoPila(); 
       ManejoCola objmc=new ManejoCola();
       
       ArbolBI objArb=new ArbolBI();
       
       //opciones del menu se llaman igual al menu que controlan
       int opppal, MenuPpalOpcion1, MenuPpalOpcion1_1,MenuPpalOpcion1_1_8, MenuPpalOpcion1_2, 
               MenuPpalOpcion1_3, MenuPpalOpcion1_3_10, MenuPpalOpcion1_4, MenuPpalOpcion1_5, 
               MenuPpalOpcion1_5_7, MenuPpalOpcion1_6,MenuPpalOpcion1_7, MenuPpalOpcion1_6_8, MenuPpalOpcion1_4_6, 
               MenuPpalOpcion2, MenuPpalOpcion2_9, MenuPpalOpcion1_2_10, MenuPpalOpcion3, 
               MenuPpalOpcion3_8, MenuPpalOpcion4, MenuPpalOpcion4_9, MenuPpalOpcion4_10, MenuPpalOpcion4_10_4;
       
       String pl,id, texto;
       Vehiculo objv=new Vehiculo();//para los retornos
       Cliente objcl=new Cliente();
       Celda celda=new Celda();
       Turno objt=new Turno();
       Servicio objs=new Servicio();
       Convenio conv=new Convenio();
       DetalleServicio objDeSer=new DetalleServicio();
       Empleado objEmp=new Empleado();
       Pago objPago=new Pago();
       DetallePago objDetPag=new DetallePago();
       
       Object obj;
       String nom;
       
        
       
       ///////////////////////////////////////////copiar archivos a estructuras !!! //////////////////////////////////
       int respDatos=JOptionPane.showConfirmDialog(null,"Desea copiar los archivos a las estructuras?","Datos parqueadero",JOptionPane.YES_NO_OPTION);
       if(respDatos==JOptionPane.YES_OPTION)
       {
            objld=objml.CopiarArchivoListaDoble(objArc,objld);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la lista doble de clientes");
            objldEmp=objml.CopiarArchivoListaDobleEmpleado(objArc,objldEmp);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la lista doble de empleados");
            objls=objml.CopiarArchivoListaSimple(objArc,objls);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la lista simple de vehÃ­culos");                          
            objp=objmp.CopiarArchivoPila(objArc, objp);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la pila de turnos ");
            objpSer=objmp.CopiarArchivoPilaServicio(objArc, objpSer);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la pila de servicios");
            objc=objmc.CopiarArchivoCola(objArc, objc);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la cola de celdas ");
            objColaConvenio=objmc.CopiarArchivoColaConvenios(objArc, objColaConvenio);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la cola de convenios ");
            objldDS=objml.CopiarArchivoListaDobleDetalleServicio(objArc,objldDS);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la lista doble de detalles de servicio");
            objlsPago=objml.CopiarArchivoListaSimplePagos(objArc, objlsPago);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la lista simple de pagos");
            objlsDetallePago=objml.CopiarArchivoListaSimpleDetallePago(objArc, objlsDetallePago);
            JOptionPane.showMessageDialog(null,"El archivo se copiÃ³ a la lista simple de detalles de pagos");
       }//fin si
       
       ///////////////////////////////////////////copiar archivos a estructuras !!! //////////////////////////////////
       
       
       //////////////////////OJO OJO OJO OJO///////////////////////////////////////////////////
       //   RECUERDE, EL HECHO DE PASAR LA INFORMACION DE LOS ARCHIVOS A LAS ESTRUCTURAS     //
       //   NO, NO, NO QUIERE DECIR QUE ENTONCES ES LO MISMO!....OJO SE USA COMO PRUEBA DE   //
       //   ESCRITORIO PERO SU MANEJO ES TOTALMENTE DISTINTO Y ES LO QUE ESTAMOS EVALUANDO   //
       //   SI SU EJERCICIO MENCIONA EL ARCHIVO...DEBE MANEJAR EL ARCHIVO!!! OJO CON ESTO    //
       //                                                                                    //
       ////////////////////////////////////////////////////////////////////////////////////////
       
       
       do{//menu principal ciclo para menu principal
          opppal=Validaciones.LeerInt(Menu.MenuPpal());
          switch(opppal)//en caso de opcion elegida en el principal
          {
              case 1://Registrar informaciÃ³n BASE
                     do{//ciclo para Registrar informaciÃ³n BASE
                     MenuPpalOpcion1=Validaciones.LeerInt(Menu.MenuPpalOpcion1());
                     switch(MenuPpalOpcion1)//en caso de opcion elegida en menu 
                     {
//////////////////////////////////////////////MANEJO DE LISTA DOBLE DE CLIENTES  objl  ///////////////////////////////
                         case 1://manejo de clientes
                               do{//ciclo para menu de manejo de clientes
                                  MenuPpalOpcion1_1=Validaciones.LeerInt(Menu.MenuPpalOpcion1_1());
                                  switch(MenuPpalOpcion1_1)//en caso de opcion elegida en menu de manejo de clientes
                                  {
                                    case 1://FALTA LA PARTE DONDE LE INGRESAMOS EL VEHICULO!!!!!!!!!!Alejandro Chica perez
                                           objld=new ListaDoble();
                                           int resp=JOptionPane.showConfirmDialog(null,"Crear lista por inicio?"
                                            ,"Creacion",JOptionPane.YES_NO_OPTION);
                                           if(resp==JOptionPane.YES_OPTION)
                                           {
                                               objld=objml.CrearLista(objld, 1);
                                           }
                                           else
                                               objld=objml.CrearLista(objld, 2);
                                           break;
                                    case 2://Consultar Cliente con su(s) vehiculo(s)!!!!! Daniel Mosquera
                                            if(objld.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                            else//hay datos
                                            {
                                                id=Validaciones.LeerString("id del cliente: ");
                                                if(objml.Buscar(id, objld)==true)//lo encontrÃ³
                                                {
                                                    texto=objld.p.toString();//se guardan los datos del dueÃ±o
                                                    texto=texto+"\n"+"\n"+objml.ConcatenarVehiculosCliente(id, objls);//se guardan sus vehiculos
                                                    JOptionPane.showMessageDialog(null,"Vehiculo(s) del cliente: \n"+texto);
                                                }
                                                else//no lo encuentra
                                                    JOptionPane.showMessageDialog(null,"El cliente NO existe en la lista doble");
                                            }//fin si  
                                           break;
 
                                    case 3://Consultar datos bÃ¡sicos de Cliente
                                           if(objld.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                           else//hay datos
                                            {
                                                id=Validaciones.LeerString("id del cliente a consultar: ");
                                                if(objml.Buscar(id, objld)==true)//lo encontrÃ³
                                                {
                                                    JOptionPane.showMessageDialog(null,"El cliente es: \n"+objld.p.toString());
                                                }
                                                else//no lo encuentra
                                                    JOptionPane.showMessageDialog(null,"El cliente NO existe en la lista doble");
                                            }//fin si  
                                           break;     
                                    case 4://Eliminar Cliente con su(s) vehÃ­culo(s) FALTA eliminar sus vehiculos!!!!
                                           if(objld.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de clientes esta vacia");
                                           else
                                            {   
                                                id=Validaciones.LeerString("Ingrese id de cliente eliminar: ");
                                                objml.LiberarDato(id, objld);
                                                //falta borrar los vehiculos del cliente!!!!  
                                            }//fin si
                                            break; 
                                            
                                    case 5://Actualizar Cliente   -  Emmanuel Alexander
                                           if(objld.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de clientes esta vacia");
                                           else
                                            {   
                                                id=Validaciones.LeerString("Ingrese id de cliente para actualizar: ");
                                                objld=objml.ActualizarCliente(objld,id);
                                                
                                            }//fin si
                                            break;
                                    case 6://Listar todos los Clientes
                                          if(objld.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista de clientes vacia");
                                          else
                                            {
                                                resp=JOptionPane.showConfirmDialog(null,"Imprimir lista por inicio?","Creacion",JOptionPane.YES_NO_OPTION);
                                                if(resp==JOptionPane.YES_OPTION)
                                                {
                                                    texto=objld.ConcatenarDesdeInicio();
                                                    if(!texto.equals(""))
                                                     JOptionPane.showMessageDialog(null,"la lista doble de clientes es: \n"+texto);
                                                }
                                                else
                                                {
                                                 texto=objld.ConcatenarDesdeFinal();
                                                 if(!texto.equals(""))
                                                     JOptionPane.showMessageDialog(null,"la lista doble de clientes es: \n"+texto);
                                                }//fin si 
                                            }//fin si
                                            break;  
                                    case 7: //Listar Clientes-vehiculos!!!! Daniel Mosquera
                                            if(objld.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista de clientes vacia");
                                          else
                                            {
                                                texto=objml.ConcatenarVehiculosYClientes(objls, objld);
                                                if(!texto.equals(""))
                                                     JOptionPane.showMessageDialog(null,"la lista de clientes y sus vehÃ­culos es: \n"+texto);
                                            }
                                            break;
                                    case 8:// menu CRUD Clientes 
                                            do{//ciclo para menu de archivo clientes
                                                MenuPpalOpcion1_1_8=Validaciones.LeerInt(Menu.MenuPpalOpcion1_1_8());
                                                switch(MenuPpalOpcion1_1_8)//en caso de opcion elegida en menu de archivo cliente
                                                {
                                                    case 1:id=Validaciones.LeerString("Id cliente: ");
                                                           objCRUDC.IngresarCliente(objArc, id);
                                                           break;
                                                    case 2:texto=objCRUDC.MostrarTodo(objArc);
                                                           if(texto.equals(""))
                                                              JOptionPane.showMessageDialog(null,"El archivo de clientes esta vacio");
                                                           else
                                                               JOptionPane.showMessageDialog(null,"El archivo de clientes es: \n"+texto);
                                                           break;
                                                    case 3:id=Validaciones.LeerString("id del usuario a consultar: ");
                                                           objcl=objCRUDC.Consultar(objArc, id);
                                                           if(objcl!=null)
                                                               JOptionPane.showMessageDialog(null,"La informaciÃ³n del cliente es:\n"+objcl.toString());
                                                           break;        
                                                    case 4://Copiar TODA la lista doble de clientes al archivo...FALTA  Esteban Arturo Cadena
                                                           
                                                           break;
                                                }//fin caso de archivo cliente
                  
                                            }while(MenuPpalOpcion1_1_8<5);//fin del mientras de archivo cliente
                                            break; //fin caso MenuPpalOpcion1_1_8 archivo cliente
                                        
                                        
                                    }//fin caso MenuPpalOpcion1_1  manejo clientes
                                }while(MenuPpalOpcion1_1<9);
                                break;
                                
////////////////////////////////////////////////MANEJO DE LISTA DOBLE DE EMPLEADOS  objldEmp  ///////////////////////////////       
                         case 2://manejo de empleados
                               do{//ciclo para menu de manejo de empleados
                                  MenuPpalOpcion1_2=Validaciones.LeerInt(Menu.MenuPpalOpcion1_2());
                                  switch(MenuPpalOpcion1_2)//en caso de opcion elegida en menu de manejo de empleados
                                  {
                                    case 1://Ingresar Empleados
                                            objldEmp=new ListaDoble();
                                           int resp=JOptionPane.showConfirmDialog(null,"Crear lista por inicio?"
                                            ,"Creacion",JOptionPane.YES_NO_OPTION);
                                           if(resp==JOptionPane.YES_OPTION)
                                           {
                                               objldEmp=objml.CrearLista(objldEmp, 1);
                                           }
                                           else
                                               objldEmp=objml.CrearLista(objldEmp, 2);
                                           break;
                                    
                                    case 2://Consultar Empleados
                                            if(objldEmp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                            else
                                            {
                                                id=Validaciones.LeerString("Digite id de empleado a consultar:");
                                                if(objml.BuscarEmpleado(id, objldEmp)==true)
                                                    JOptionPane.showMessageDialog(null,"El empleado es:\n"+objldEmp.p.toString());
                                                else
                                                    JOptionPane.showMessageDialog(null,"El empleado no se encuentra en la lista");
                                                //fin si
                                            }//fin si
                                            break;  
                                    case 3://Cambiar Cargo a un Empleado
                                            if(objldEmp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                            else
                                            {
                                                id=Validaciones.LeerString("Digite id de empleado a cambiar su cargo:");
                                                objml.CambiosEspecificos(objldEmp, id, 3,objp);
                                            }//fin si
                                            break;
                                    case 4://Cambiar Turno a un Empleado
                                            if(objldEmp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                            else
                                            {
                                                id=Validaciones.LeerString("Digite id de empleado a cambiar su turno:");
                                                objml.CambiosEspecificos(objldEmp, id, 4,objp);
                                            }//fin si
                                            break; 
                                    case 5://Eliminar Empleado
                                            if(objldEmp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                            else
                                            {
                                                id=Validaciones.LeerString("Digite id de empleado a eliminar:");
                                                objml.LiberarDatoEmpleado(id, objldEmp);
                                            }//fin si
                                            break;
                                    case 6://FALTA !!! Actualizar Empleado   
                                            break; 
                                    case 7://Listar todos los Empleados
                                             if(objldEmp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                             else
                                             {
                                                  resp=JOptionPane.showConfirmDialog(null,"Imprimir lista por inicio?","Listar",JOptionPane.YES_NO_OPTION);
                                                  if(resp==JOptionPane.YES_OPTION)
                                                  {
                                                      texto=objldEmp.ConcatenarDesdeInicio();
                                                      if(!texto.equals(""))
                                                       JOptionPane.showMessageDialog(null,"la lista doble de empleados es: \n"+texto);
                                                  }
                                                  else
                                                  {
                                                   texto=objldEmp.ConcatenarDesdeFinal();
                                                   if(!texto.equals(""))
                                                       JOptionPane.showMessageDialog(null,"la lista doble de empleados es: \n"+texto);
                                                  }//fin si 
                                             }//fin si
                                            break;
                                    case 8://Listar empleados por cargo  Jorge Andres Alvarez
                                         if(objldEmp.IsEmpty() == true){
                                              JOptionPane.showMessageDialog(null,"Lista vacia");
                                            }else{
                                                      JOptionPane.showMessageDialog(null, "Ingrese el cargo para buscar y listar: ");
                                                      String atri = Validaciones.LeerCargo();
                                                      JOptionPane.showMessageDialog(null, "La lista de empleados con cargo "+atri+" es: \n"+objml.ListarPorAtributoEmp(objldEmp, objp,8, atri));
                                                      }// fin si
                                            break;   
                                    case 9://Listar empleados por turno   Jorge Andres Alvarez
                                         if(objldEmp.IsEmpty() == true){
                                              JOptionPane.showMessageDialog(null,"Lista vacia");
                                            }else{    
                                                      JOptionPane.showMessageDialog(null, "Ingrese el "
                                                              + "turno para buscar y listar: ");  
                                                      String atri = Validaciones.LeerTurno();
                                                      JOptionPane.showMessageDialog(null, "La lista de empleados por turno "+atri+" es: \n"+objml.ListarPorAtributoEmp(objldEmp,objp, 9, atri));
                                                      }// fin si
                                            break;
                                       
                                    case 10://CRUD Empleados
                                            do{//ciclo para menu de archivo empleados
                                                MenuPpalOpcion1_2_10=Validaciones.LeerInt(Menu.MenuPpalOpcion1_2_10());
                                                switch(MenuPpalOpcion1_2_10)//en caso de opcion elegida en menu de archivo empleado
                                                {
                                                    case 1:id=Validaciones.LeerString("Id empleado: ");
                                                           objCRUDE.IngresarEmpleado(objArc, id,objp);
                                                           break;
                                                    case 2:texto=objCRUDE.MostrarTodo(objArc);
                                                           if(texto.equals(""))
                                                              JOptionPane.showMessageDialog(null,"El archivo de empleados esta vacio");
                                                           else
                                                               JOptionPane.showMessageDialog(null,"El archivo de empleados es: \n"+texto);
                                                           break;
                                                    case 3:id=Validaciones.LeerString("id del empleado a consultar: ");
                                                           objEmp=objCRUDE.Consultar(objArc, id);
                                                           if(objEmp!=null)
                                                               JOptionPane.showMessageDialog(null,"La informaciÃ³n del empleado es:\n"+objEmp.toString());
                                                           break;        
                                                    case 4://Copiar TODA la lista doble de clientes al archivo
                                                           if(objldEmp.IsEmpty()==true)
                                                                JOptionPane.showMessageDialog(null,"Lista vacia");
                                                           else
                                                           {
                                                               objml.CopiarListaDobleArchivoEmpleado(objArc, objldEmp);
                                                           }
                                                           break;
                                                }//fin caso de archivo MenuPpalOpcion1_2_10
                  
                                             }while(MenuPpalOpcion1_2_10<5);//fin del mientras de archivo empleados
                                             break;
                                           
                                  }//fin caso MenuPpalOpcion1_2 manejo empleados
                               }while(MenuPpalOpcion1_2<11);
                               break;
                               
//////////////////////////////////////////////////////////MANEJO DE LISTA SIMPLE DE VEHICULOS  objls  ///////////////////////////////       
                         case 3://manejo de vehiculos
                               do{//ciclo para menu de manejo de vehiculos
                                  MenuPpalOpcion1_3=Validaciones.LeerInt(Menu.MenuPpalOpcion1_3());
                                  switch(MenuPpalOpcion1_3)//en caso de opcion elegida en menu de manejo de vehiculos
                                  {
                                    case 1://Ingresar VehÃ­culos con su cliente existente 
                                            objls=new ListaSimple();//como es para crear, y es excluyente se dan condiciones iniciales
                                            int resp=JOptionPane.showConfirmDialog(null,"Crear la lista por Inicio? ","Creando por Inicio o por Final",JOptionPane.YES_NO_OPTION);
                                            //preguntamos al usuario de que manera crearÃ¡ la lista, si por inicio o por final porque son excluyentes    
                                            if(resp==JOptionPane.YES_OPTION)
                                                 objml.CrearLista(objls,1,objld);//se manda 1 para crear por inicio
                                            else
                                                 objml.CrearLista(objls,2,objld);//se manda 2 para crear por final
                                            break;
                                    case 2://Consultar VehÃ­culo
                                           if(objls.IsEmpty()==true)
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                           else
                                            {   
                                                pl=Validaciones.LeerString("Ingrese placa del vehiculo a consultar: ");
                                                if(objml.Buscar(pl, objls)==true)
                                                    JOptionPane.showMessageDialog(null,"El vehÃ­culo es: \n"+objls.p.toString());
                                                else
                                                    JOptionPane.showMessageDialog(null,"El vehÃ­culo No existe en la lista simple");
                                                //fin si
                                            }//fin si
                                           break;
                                    case 3://Eliminar VehÃ­culo
                                            if(objls.IsEmpty()==true)
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                            else
                                            {   
                                                pl=Validaciones.LeerString("Ingrese placa del vehiculo a eliminar: ");
                                                objml.LiberarDato(pl, objls);
                                            }//fin si
                                            break;
                                    case 4://Actualizar VehÃ­culo....  Joseph Emmanuel Sanchez
                                           if(objls.IsEmpty()==true)
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                            else
                                            {   
                                                pl=Validaciones.LeerString("Ingrese placa del vehiculo a actualizar: ");
                                                objml.actualizarVehiculo(objls,pl,objld);//recibe la lista simple de vehiculo, la placa, la lista doble de clientes
                                            }//fin si
                                            break;
                                    case 5://Listar todos los VehÃ­culos
                                           if(objls.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                           else
                                                JOptionPane.showMessageDialog(null,"la lista de vehiculos es: \n"+objls.ConcatenarLista());
                                           break;
                                    case 6://Listar vehÃ­culos por Estado
                                           if(objls.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                           else
                                           {
                                               boolean est=Validaciones.LeerBoolean("Desea ver listado de vehiculos en mal estado(false)\nen buen estado(true)?");
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos es: \n"+objml.ListarPorEstado(objls, est));
                                           }//fin si
                                           break;
                                    case 7://Listar vehÃ­culos por Tipo
                                           if(objls.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                           else
                                           {
                                               String atri=Validaciones.LeerTipoVehiculo();
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos tipo "+atri+" es: \n"+objml.ListarPorAtributo(objls, 7, atri));
                                           }//fin si
                                           break; 
                                    case 8://Listar vehÃ­culos por marca
                                           if(objls.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                           else
                                           {
                                               String atri=Validaciones.LeerString("Ingrese marca para buscar y listar: ");
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos marca "+atri+" es: \n"+objml.ListarPorAtributo(objls, 8, atri));
                                           }//fin si
                                           break;  
                                     case 9://Listar vehÃ­culos por modelo
                                           if(objls.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null,"la lista de vehiculos esta vacia");
                                           else
                                           {
                                               int atriInt=Validaciones.LeerInt("Ingrese modelo para buscar y listar: ");
                                               JOptionPane.showMessageDialog(null,"la lista de vehiculos marca "+atriInt+" es: \n"+objml.ListarPorAtributo(objls, 9, atriInt));
                                           }//fin si
                                           break; 
                                           
                                     case 10://CRUD VehÃ­culos
                                             do{//ciclo para menu de archivo vehiculo
                                                MenuPpalOpcion1_3_10=Validaciones.LeerInt(Menu.MenuPpalOpcion1_3_10());
                                                switch(MenuPpalOpcion1_3_10)//en caso de opcion elegida en menu de archivo vehiculo
                                                {
                                                    case 1: pl=Validaciones.LeerString("Placa del vehÃ­culo: ");
                                                            objCRUDV.IngresarVehiculo(objArc, pl,objld);
                                                             break;
                                                    case 2:texto=objCRUDV.MostrarTodo(objArc);
                                                           if(texto.equals(""))
                                                              JOptionPane.showMessageDialog(null,"El archivo de vehiculos esta vacio");
                                                           else
                                                               JOptionPane.showMessageDialog(null,"El archivo de vehiculos es: \n"+texto);
                                                           break;
                                                    case 3:pl=Validaciones.LeerString("Placa del vehÃ­culo a consultar: ");
                                                           objv=objCRUDV.Consultar(objArc, pl);
                                                           if(objv!=null)
                                                               JOptionPane.showMessageDialog(null,"La informaciÃ³n del vehiculos es:\n"+objv.toString());
                                                           break;        
                                                    case 4://Copiar TODA la lista simple de vehiculos al archivo...FALTA Juan Camilo Buritica
                                                           break;
                                                }//fin caso de archivo vehiculo
                                               }while(MenuPpalOpcion1_3_10<5);//fin del mientras de archivo vehiculo
                                               break;
                                  }//fin caso MenuPpalOpcion1_3
                               }while(MenuPpalOpcion1_3<11);
                               break;
///////////////////////////////////////////////////MANEJO DE PILA DE SERVICIOS  objpSer  ///////////////////////////////       
                         case 4://manejo de servicios
                               do{//ciclo para menu de manejo de servicios
                                  MenuPpalOpcion1_4=Validaciones.LeerInt(Menu.MenuPpalOpcion1_4());
                                  switch(MenuPpalOpcion1_4)//en caso de opcion elegida en menu de manejo de servicios
                                  {  
                                      case 1://Ingresar Servicio objpSer
                                             objpSer=objmp.IngresarPilaServicio(objpSer);
                                             break;
                                      case 2://Consultar Servicio
                                             if(objpSer.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de servicios estÃ¡ vacia");
                                             else { 
                                                    id=Validaciones.LeerString("Ingrese id de servicio a consultar: ");
                                                    obj=objmp.ConsultarServicio(objpSer, objpaSer, id);
                                                    if(obj!=null)
                                                          JOptionPane.showMessageDialog(null,"el dato es: "+obj.toString());
                                                   }//fin si
                                             break;
                                      case 3://Eliminar Servicio
                                             if(objpSer.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de servicios estÃ¡ vacia");
                                             else 
                                             {
                                               id=Validaciones.LeerString("Ingrese id de servicio a eliminar: ");
                                               JOptionPane.showMessageDialog(null,objmp.LiberarServicio(objpSer, objpaSer, id));
                                             }
                                             break;
                                      case 4://Listar Servicios
                                             if(objpSer.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de servicios estÃ¡ vacia");
                                            else 
                                               JOptionPane.showMessageDialog(null, "la pila es: \n"+objmp.ConcatenarPila(objpSer, objpaSer));
                                            break; 
                                      case 5://Actualizar Servicio
                                             break; 
                                      case 6://CRUD Servicios
                                              do{//ciclo para menu de archivo servicio
                                                    MenuPpalOpcion1_4_6=Validaciones.LeerInt(Menu.MenuPpalOpcion1_4_6());
                                                    switch(MenuPpalOpcion1_4_6)//en caso de opcion elegida en menu de archivo servicios
                                                    {
                                                        case 1:id=Validaciones.LeerString("Id servicio: ");
                                                               objCRUDS.IngresarServicio(objArc, id);
                                                               break;
                                                        case 2:texto=objCRUDS.MostrarTodo(objArc);
                                                               if(texto.equals(""))
                                                                  JOptionPane.showMessageDialog(null,"El archivo de servicios esta vacio");
                                                               else
                                                                   JOptionPane.showMessageDialog(null,"El archivo de servicios es: \n"+texto);
                                                               break;
                                                        case 3:id=Validaciones.LeerString("id del servicio a consultar: ");
                                                               objs=objCRUDS.Consultar(objArc, id);
                                                               if(objs!=null)
                                                                   JOptionPane.showMessageDialog(null,"La informaciÃ³n del servicio es:\n"+objs.toString());
                                                               break;        
                                                        case 4://Copiar TODA la pila de servicios al archivo...FALTA 
                                                           break;       
                                                    }//fin caso de archivo servicio

                                                }while(MenuPpalOpcion1_4_6<5);//fin del mientras de archivo servicio
                                                break; //fin caso MenuPpalOpcion1_5_7 de archivo servicio 
                                             
                                  }//fin caso MenuPpalOpcion1_4
                                 }while(MenuPpalOpcion1_4<7);
                                 break; 
                         
////////////////////////////////////////////MANEJO DE PILA DE TURNOS  objp  /////////////////////////////// ///////////      
                         case 5://manejo de TURNOS
                               do{//ciclo para menu de manejo de turnos
                                  MenuPpalOpcion1_5=Validaciones.LeerInt(Menu.MenuPpalOpcion1_5());
                                  switch(MenuPpalOpcion1_5)//en caso de opcion elegida en menu de manejo de servicios
                                  {  
                                      case 1://Ingresar Turno
                                             objp=objmp.IngresarPilaTurnos(objp);
                                             break;  
                                      case 2://Consultar Turno
                                             if(objp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de turnos estÃ¡ vacia");
                                             else { 
                                                    id=Validaciones.LeerString("Ingrese id de turno a consultar: ");
                                                    obj=objmp.Consultar(objp, objpa, id);
                                                    if(obj!=null)
                                                          JOptionPane.showMessageDialog(null,"el dato es: "+obj.toString());
                                                   }
                                             break;    
                                      case 3://Eliminar Turno
                                             if(objp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de turnos estÃ¡ vacia");
                                             else 
                                             {
                                               id=Validaciones.LeerString("Ingrese id de turno a eliminar: ");
                                               JOptionPane.showMessageDialog(null,objmp.LiberarDato(objp, objpa, id));
                                             }
                                            break;
                                      case 4://Listar Turnos
                                            if(objp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de turnos estÃ¡ vacia");
                                            else 
                                               JOptionPane.showMessageDialog(null, "la pila es: \n"+objmp.ConcatenarPila(objp, objpa));
                                            break; 
                                      case 5://Actualizar Turno
                                             if(objp.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la pila de turnos estÃ¡ vacia");
                                             else { 
                                                  id=Validaciones.LeerString("Ingrese id de turno a cambiar: ");
                                                  objp=objmp.Actualizar(objp, objpa, id);

                                                 }
                                             break; 
                                      case 6://Listar por DescripciÃ³n de turno...
                                             break; 
                                      case 7://CRUD Turnos
                                             do{//ciclo para menu de archivo turnos
                                                    MenuPpalOpcion1_5_7=Validaciones.LeerInt(Menu.MenuPpalOpcion1_5_7());
                                                    switch(MenuPpalOpcion1_5_7)//en caso de opcion elegida en menu de archivo turnos
                                                    {
                                                        case 1:id=Validaciones.LeerString("Id turno: ");
                                                               objCRUDT.IngresarTurno(objArc, id);
                                                               break;
                                                        case 2:texto=objCRUDT.MostrarTodo(objArc);
                                                               if(texto.equals(""))
                                                                  JOptionPane.showMessageDialog(null,"El archivo de turnos esta vacio");
                                                               else
                                                                   JOptionPane.showMessageDialog(null,"El archivo de turnos es: \n"+texto);
                                                               break;
                                                        case 3:id=Validaciones.LeerString("id del turno a consultar: ");
                                                               objt=objCRUDT.Consultar(objArc, id);
                                                               if(objt!=null)
                                                                   JOptionPane.showMessageDialog(null,"La informaciÃ³n del turno es:\n"+objt.toString());
                                                               break;        
                                                        case 4://Copiar TODA la pila de turnos al archivo...Emmanuel
                                                                if(objp.IsEmpty()==false)
                                                                {
                                                                    objCRUDT.CopiarPilaTurnosAArchivo(objArc, objp, objpa, objmp);                     
                                                                }
                                                                else
                                                                {
                                                                    JOptionPane.showMessageDialog(null, "La pila de TURNOS estÃ¡ vacÃ­a.");
                                                                }

                                                           break;       
                                                    }//fin caso de archivo turnos

                                                }while(MenuPpalOpcion1_5_7<5);//fin del mientras de archivo turnos
                                                break; //fin caso MenuPpalOpcion1_5_7 de archivo turnos 
                                                   
                                  }//fin caso MenuPpalOpcion1_5
                               }while(MenuPpalOpcion1_5<8);
                               break;

//////////////////////////////////////////////////MANEJO DE COLA DE CELDAS  objc  ///////////////////////////////       
                         case 6://manejo de celdas
                               do{//ciclo para menu de manejo de celdas
                                  MenuPpalOpcion1_6=Validaciones.LeerInt(Menu.MenuPpalOpcion1_6());
                                  switch(MenuPpalOpcion1_6)//en caso de opcion elegida en menu de manejo de celdas
                                  {  
                                      case 1://Ingresar Celda
                                             objc=objmc.IngresarColaCeldas(objc);
                                             break;  
                                      case 2://Consultar Celda       
                                             if(objc.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de celdas estÃ¡ vacia");
                                             else {
                                                      id=Validaciones.LeerString("Ingrese id de celda a consultar: ");
                                                      obj=objmc.Consultar(objc, objca, id);
                                                      if(obj!=null)
                                                          JOptionPane.showMessageDialog(null,"el dato es: "+obj.toString());
                                                  }
                                            break;
                                      case 3://Eliminar Celda
                                             if(objc.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de celdas estÃ¡ vacia");
                                             else {
                                                  id=Validaciones.LeerString("Ingrese id de celda eliminar: ");
                                                  JOptionPane.showMessageDialog(null,objmc.LiberarDato(objc, objca, id));
                                                 }
                                             break;
                                      case 4://Listar Celdas
                                             if(objc.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de celdas estÃ¡ vacia");
                                             else 
                                               JOptionPane.showMessageDialog(null, "la cola de celdas es: \n"+objmc.ConcatenarCola(objc, objca));
                                             break;
                                      case 5://Actualizar Celda
                                             if(objc.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de celdas estÃ¡ vacia");
                                             else {
                                                     id=Validaciones.LeerString("Ingrese id de celda a cambiar: ");
                                                     objc=objmc.Actualizar(objc, objca, id);
                                                   }
                                             break;
                                      case 6://Listar por Tipo VehÃ­culo  - Daniel Mosquera
                                             if(objc.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de celdas estÃ¡ vacia");
                                             else{
                                                 String vh;
                                                 //JOptionPane.showMessageDialog(null, "seleccione el tipo de vehiculo a listar");
                                                 vh=Validaciones.LeerTipoVehiculo();
                                               JOptionPane.showMessageDialog(null, "las celdas por tipo "+vh+" son: \n"+objmc.ListarPorAtributo(objc, objca, 6,vh));
                                             }
                                             break;
                                      case 7://Listar por Estado - Daniel Mosquera
                                             if(objc.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de celdas estÃ¡ vacia");
                                             else{
                                                 //preguntamos al usuario por que estado desea listar 
                                                int resp=JOptionPane.showConfirmDialog(null,"Listar por celdas desocupada (true)? ","Listar",JOptionPane.YES_NO_OPTION);  
                                                if(resp==JOptionPane.YES_OPTION)
                                                {
                                                    JOptionPane.showMessageDialog(null, "la cola de celdas es: \n"+objmc.ListarPorAtributo(objc, objca, 1,""));
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "la cola de celdas es: \n"+objmc.ListarPorAtributo(objc, objca, 2,""));
                                                }
                                             }
                                             break;
                                      case 8://CRUD Celdas
                                             do{//ciclo para menu de archivo celdas
                                                MenuPpalOpcion1_6_8=Validaciones.LeerInt(Menu.MenuPpalOpcion1_6_8());
                                                switch(MenuPpalOpcion1_6_8)//en caso de opcion elegida en menu de archivo celdas
                                                {
                                                    case 1:id=Validaciones.LeerString("Id celda: ");
                                                           objCRUDCel.IngresarCelda(objArc, id);
                                                           break;
                                                    case 2:texto=objCRUDCel.MostrarTodo(objArc);
                                                           if(texto.equals(""))
                                                              JOptionPane.showMessageDialog(null,"El archivo de celdas esta vacio");
                                                           else
                                                               JOptionPane.showMessageDialog(null,"El archivo de celdas es: \n"+texto);
                                                           break;
                                                    case 3:id=Validaciones.LeerString("id de la celda a consultar: ");
                                                           celda=objCRUDCel.Consultar(objArc, id);
                                                           if(celda!=null)
                                                               JOptionPane.showMessageDialog(null,"La informaciÃ³n de la celda es:\n"+celda.toString());
                                                           break;        
                                                    case 4://Copiar TODA la cola de celdas al archivo...FALTA  Santiago aguirre
                                                           if(objc.IsEmpty()==false)
                                                                {
                                                                    objCRUDCel.CopiarColaArchivo(objArc, objc, objca, objmc);
                                                                }
                                                                else
                                                                {
                                                                    JOptionPane.showMessageDialog(null, "La cola de CELDAS estÃ¡ vacÃ­a.");
                                                                }
                                                            break;        
                                                }//fin caso de archivo celdas

                                                }while(MenuPpalOpcion1_6_8<5);//fin del mientras de archivo celdas
                                                break;   
                                                
                                        }//fin caso MenuPpalOpcion1_6
                                    }while(MenuPpalOpcion1_6<9);
                                    break;
///////////////////////////////////MANEJO DE ARBOL BINARIO DE EMPLEADOS///////////////////////                                    
                         case 7://manejo de arbol de empleados
                                int resp=JOptionPane.showConfirmDialog(null,"Desea usar los datos de la prueba de escritorio?","Organigrama de 8 Nodos",JOptionPane.YES_NO_OPTION);
                                if(resp==JOptionPane.YES_OPTION)//si a la prueba de escritorio
                                {
                                    //se crea la raiz del arbol
                                    objArb.PruebaEscritorioCrearAdministradorRaiz(objArb.getRaiz());
                                    //se crea a partir de la raiz de administrador todo el arbol
                                    objArb.PruebaEscritorioOrganigrama(objArb.getRaiz());
                                }
                                else{//NO a la prueba de escritorio se ingresa manual todo
                                    //se crea la raiz del arbol sin recursividad
                                    id=Validaciones.LeerString("id del gerente: ");
                                    objEmp=objEmp.IngresarDatos(id, objp);
                                    objArb.CrearRaiz(objEmp);//gerente
                                    //crear recursivamente el arbol de empleados (organigrama)
                                    objArb.Crear(objArb.getRaiz(), objp);//recibe el apuntador a la raiz y la pila de turnos
                                }//fin si
                                do{//ciclo para menu de manejo de organigrama
                                  MenuPpalOpcion1_7=Validaciones.LeerInt(Menu.MenuPpalOpcion1_7());
                                  switch(MenuPpalOpcion1_7)//en caso de opcion elegida en menu de manejo de arbol
                                  {  
                                      case 1://mostrar organigrama en inorden
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"Organigrama en inorden: \n"+objArb.InOrden(objArb.getRaiz()));
                                             } 
                                            break;
                                      case 2://mostrar organigrama en postorden
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"Organigrama en postorden: \n"+objArb.PostOrden(objArb.getRaiz()));
                                             } 
                                            break;
                                      case 3://mostrar organigrama en preorden
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"Organigrama en preorden: \n"+objArb.PreOrden(objArb.getRaiz()));
                                             } 
                                            break;       
                                      case 4://peso del Ã¡rbol
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"El nÃºmero de empleados en el organigrama es: "+objArb.Peso(objArb.getRaiz()));
                                             } 
                                            break;  
                                       case 5://hermanos en el Ã¡rbol
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"Hermanos o pares\n"+objArb.mostrarHermanos());
                                             } 
                                            break;        
                                      case 6://nÃºmero de nodos en el Ã¡rbol
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"El nÃºmero de empleados en el organigrama es: "+objArb.NumeroElementos(objArb.getRaiz()));
                                             } 
                                            break;   
                                      case 7://hijos izquierdos en el Ã¡rbol
                                             if(objArb.Estavacio()==false)//hay datos
                                             {      
                                                objArb.Inicializar();
                                                JOptionPane.showMessageDialog(null,"Los hijos izquierdo del organigrama son: "+objArb.listarHijosIzquierdos(objArb.getRaiz()));
                                             } 
                                            break; 
                                      case 8://Ancestros de un nodo
                                          if (objArb.Estavacio()) { 
                                            JOptionPane.showMessageDialog(null, "El Ã¡rbol estÃ¡ vacÃ­o, no hay ancestros para buscar.");
                                            } else {
                                            // Pedimos el ID usando tu clase Validaciones
                                            id = Validaciones.LeerString("Ingrese el ID del empleado para conocer sus ancestros:");
        
                                            // Llamamos al nuevo mÃ©todo
                                            objArb.MostrarAncestros(id);
                                            }
                                          break;
                                      case 9://altura del arbol DANIEL MARTINEZ
                                          if(objArb.Estavacio() == false)
                                          {
                                             objArb.Inicializar();
                                             JOptionPane.showMessageDialog(null, "La altura del organigrama es:  " + objArb.Altura());
                                          }
                                          break;
                                     case 10: // Insertar hoja
                                         if (objArb.Estavacio()) {
                                         JOptionPane.showMessageDialog(null, "El Ã¡rbol estÃ¡ vacÃ­o. Use la opciÃ³n 1 para crear la RaÃ­z primero.");
                                         } else {
                                         id = Validaciones.LeerString("Ingrese el ID del empleado PADRE a quien le asignarÃ¡ el nuevo ingreso:");
                                         // Se llama al mÃ©todo pasando el ID del padre y la pila de turnos necesaria para crear el empleado
                                         objArb.InsertarNuevaHoja(id, objp); 
                                         }
                                            break;
                                     case 11: // Insertar SubÃ¡rbol
                                        if (objArb.Estavacio()) { // <--- VERIFICA SI TU OBJETO SE LLAMA objArbol U objABI
                                             JOptionPane.showMessageDialog(null, "El Ã¡rbol estÃ¡ vacÃ­o. Primero cree la raÃ­z.");
                                          } else {
                                             id = Validaciones.LeerString("Ingrese el ID del empleado donde insertarÃ¡ el subÃ¡rbol:");
                                              // Enviamos el ID del padre y la pila de turnos necesaria
                                          objArb.InsertarSubArbol(id, objp); // <--- VERIFICA EL NOMBRE DEL OBJETO
                                            }
                                         break;
                                         case 12: // Mostrar empleados por cargo
                                         if (objArb.Estavacio()) {
                                              JOptionPane.showMessageDialog(null, "El Ã¡rbol estÃ¡ vacÃ­o.");
                                         } else {
                                              // 1. Pedimos el cargo a buscar
                                              // Puedes usar tu Validaciones.LeerCargo() si quieres que sea con menÃº
                                              String cargo = Validaciones.LeerString("Ingrese el cargo a buscar (Ej: Gerente, Cajero):");
        
                                              // 2. Inicializamos la variable global 'texto' del Ã¡rbol
                                              objArb.Inicializar();
        
                                              // 3. Llamamos al mÃ©todo recursivo
                                              String resultado = objArb.ListarPorCargo(objArb.getRaiz(), cargo);
        
                                              // 4. Mostramos el resultado
                                              if (resultado.equals("")) {
                                                JOptionPane.showMessageDialog(null, "No se encontraron empleados con el cargo: " + cargo);
                                              } else {
                                                JOptionPane.showMessageDialog(null, "*** EMPLEADOS CON CARGO: " + cargo.toUpperCase() + " ***\n\n" + resultado);
                                                    }
                                                 }
                                             break;
                                             case 13: // Mostrar camino de un nodo a otro
                                             if (objArb.Estavacio()) { // Verifica el nombre de tu objeto (objArb / objABI)
                                                  JOptionPane.showMessageDialog(null, "El Ã¡rbol estÃ¡ vacÃ­o.");
                                             } else {
                                                  String idO = Validaciones.LeerString("Ingrese el ID del nodo ORIGEN (Desde dÃ³nde sale):");
                                                  String idD = Validaciones.LeerString("Ingrese el ID del nodo DESTINO (A dÃ³nde llega):");
        
                                                // Llamada al mÃ©todo
                                                if(idO.equalsIgnoreCase(idD)){
                                                    JOptionPane.showMessageDialog(null, "El origen y el destino son el mismo nodo.");
                                                } else {
                                                       objArb.MostrarCaminoEntreNodos(idO, idD);
                                                     }
                                                }
                                                break;
                                                case 14: // Copia de Seguridad (Ã�rbol -> Archivo)
    if (objArb.Estavacio()) {
        JOptionPane.showMessageDialog(null, "El Ã¡rbol estÃ¡ vacÃ­o. No hay nada que guardar.");
    } else {
        try {
            // 1. Definimos el nombre del archivo
            String nombreArchivo = "RespaldoEmpleados.txt";
            
            // 2. Borramos contenido viejo y abrimos modo escritura
            // (Usando tu clase Archivos tal cual estÃ¡ en el proyecto)
            objArc.BorrarContenido(nombreArchivo);
            objArc.AbrirArchivoModoEscritura(nombreArchivo);
            
            // 3. Preparamos los datos desde el Ã�rbol
            objArb.Inicializar(); // Limpiamos la variable 'texto'
            objArb.GenerarCopiaSeguridad(objArb.getRaiz()); // Llenamos 'texto' con los datos
            
            // 4. Verificamos si trajo datos
            if (objArb.texto.equals("")) {
                JOptionPane.showMessageDialog(null, "Error al procesar los datos del Ã¡rbol.");
            } else {
                // 5. Truco: Convertimos el texto gigante en un arreglo dividiendo por renglones (\n)
                // AsÃ­ podemos usar tu mÃ©todo EscribirRegistro lÃ­nea por lÃ­nea
                String[] lineas = objArb.texto.split("\n");
                
                for (int i = 0; i < lineas.length; i++) {
                    objArc.EscribirRegistro(lineas[i]);
                }
                
                JOptionPane.showMessageDialog(null, "Â¡Copia de seguridad realizada con Ã©xito!\n" +
                        "Se guardaron " + lineas.length + " empleados en " + nombreArchivo);
            }
            
            // 6. Cerrar el archivo obligatoriamente
            objArc.CerrarArchivoModoEscritura();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
        }
    }
    break;
                                  }//fin caso MenuPpalOpcion1_7
                                }while(MenuPpalOpcion1_7<15);
                                break;
                                    
                     }//fin caso de Registrar informaciÃ³n BASE
                     
                     }while(MenuPpalOpcion1<8);//fin mientras Registrar informaciÃ³n BASE
                     break;//fin opcion 1 del ppal de Registrar informaciÃ³n BASE
                     
              //Registrar o Cancelar Servicios OPCION 2 DEL MENU PRINCIPAL
              case 2: 
 ///////////////////////////Registrar o Cancelar Servicios - Detalle de Servicio
                     do{//ciclo para menu de Detalle de Servicio
                        MenuPpalOpcion2=Validaciones.LeerInt(Menu.MenuPpalOpcion2());
                        switch(MenuPpalOpcion2)//en caso de opcion elegida en menu de Detalle de Servicio
                        {
                            case 1://Registrar parqueo. agrega a la lista doble en memoria (no al archivo)
                                   // Uso ManejoDetalleServicio.CrearListaDetalleServicio para ingresar uno o varios
                                   // detalles de servicio directamente en la lista doble objldDS
                                   objldDS = objml.CrearListaDetalleServicio(objldDS, objls, objc,objpSer);
                                   break;
                            case 2://Cancelar parqueo
                                   if(objldDS.IsEmpty()==true)
                                        JOptionPane.showMessageDialog(null,"No hay detalles de servicio registrados");
                                   else
                                    {   
                                        String idCancelar = Validaciones.LeerString("ID del servicio a cancelar: ");
                                        objml.LiberarDato(idCancelar, objldDS);
                                    }//fin si
                                   break;  
                            case 3://Cerrar parqueo (colocar hora de salida)
                                   if(objldDS.IsEmpty()==true)
                                        JOptionPane.showMessageDialog(null,"No hay detalles de servicio registrados");
                                   else
                                    {   
                                        String idCerrar = Validaciones.LeerString("ID del servicio a cerrar: ");
                                        if(objml.Buscar(idCerrar, objldDS))//lo encontrÃ³
                                        {
                                            String nuevaHoraFin = Validaciones.LeerHora("Nueva hora de fin:");
                                            ((DetalleServicio)objldDS.p.getDato()).setHoraFin(nuevaHoraFin);
                                            JOptionPane.showMessageDialog(null,"Hora de fin actualizada para el servicio: "+idCerrar);
                                        }
                                        else//no lo encuentra
                                            JOptionPane.showMessageDialog(null,"Servicio no encontrado");
                                    }//fin si
                                   break;
                            case 4://Listar celdas desocupadas
                                   texto = objml.ListarCeldasDesocupadas(objc, objca);
                                   if(texto.equals(""))
                                        JOptionPane.showMessageDialog(null,"No hay celdas desocupadas");
                                   else
                                        JOptionPane.showMessageDialog(null,"Celdas desocupadas:\n"+texto);
                                   break; 
                            case 5://Listar celdas ocupadas
                                   if (objc.IsEmpty()==true)
                                          JOptionPane.showMessageDialog(null,"No hay celdas registradas");
                                   else
                                   {
                                          texto = objml.ListarCeldasOcupadas(objc, objca);
                                          if(texto.equals(""))
                                                 JOptionPane.showMessageDialog(null,"No hay celdas ocupadas");
                                          else
                                                  JOptionPane.showMessageDialog(null,"Celdas ocupadas:\n"+texto);
                                          }
                                   break;
                            case 6://Listar parqueos (detalle de servicios)
                                   if(objldDS.IsEmpty()==true)
                                        JOptionPane.showMessageDialog(null,"No hay detalles de servicio registrados");
                                   else
                                    {
                                        texto = objldDS.ConcatenarDesdeInicio();
                                        JOptionPane.showMessageDialog(null,"Lista de detalles de servicio:\n"+texto);
                                    }//fin si
                                   break; 
                            case 7://Consultar un parqueo
                                   if(objldDS.IsEmpty()==true)
                                        JOptionPane.showMessageDialog(null,"No hay detalles de servicio registrados");
                                   else
                                    {   
                                        String idConsulta = Validaciones.LeerString("ID del servicio a consultar: ");
                                        if(objml.Buscar(idConsulta, objldDS))//lo encontrÃ³
                                        {
                                            JOptionPane.showMessageDialog(null,"Detalle del servicio:\n"+objldDS.p.toString());
                                        }
                                        else//no lo encuentra
                                            JOptionPane.showMessageDialog(null,"Servicio no encontrado");
                                    }//fin si
                                   break;
                            case 8://Actualizar un parqueo
                                   if(objldDS.IsEmpty()==true)
                                        JOptionPane.showMessageDialog(null,"No hay detalles de servicio registrados");
                                   else
                                    {   
                                        id = Validaciones.LeerString("ID del servicio a actualizar: ");
                                        objldDS=objml.Actualizar(objldDS, id,objc,objpSer, objls);
                                    }
                                   break;   
                            case 9://CRUD DetalleServicio
                                   do{//ciclo para menu de archivo detalles de servicio
                                        MenuPpalOpcion2_9=Validaciones.LeerInt(Menu.MenuPpalOpcion2_9());
                                        switch(MenuPpalOpcion2_9)//en caso de opcion elegida en menu de archivo detalles de servicio
                                        {
                                            case 1://Ingresar en archivo detalles de servicios
                                                   String idArchivo = Validaciones.LeerString("ID del detalle de servicio: ");
                                                   objCRUDDS.IngresarDetalleServicio(objArc, idArchivo, objls,objc,objpSer);
                                                   break;
                                            case 2://Mostrar Todo el archivo detalles de servicios
                                                   String textoArchivo = objCRUDDS.MostrarTodo(objArc);
                                                   if(textoArchivo.equals(""))
                                                      JOptionPane.showMessageDialog(null,"El archivo de detalles de servicio estÃ¡ vacÃ­o");
                                                   else
                                                       JOptionPane.showMessageDialog(null,"Archivo de detalles de servicio:\n"+textoArchivo);
                                                   break;
                                            case 3://Consultar un detalle de servicio especÃ­fico
                                                   String idConsultaArchivo = Validaciones.LeerString("ID del detalle de servicio a consultar: ");
                                                   DetalleServicio objds = objCRUDDS.Consultar(objArc, idConsultaArchivo);
                                                   if(objds!=null)
                                                       JOptionPane.showMessageDialog(null,"InformaciÃ³n del detalle de servicio:\n"+objds.toString());
                                                   break;        
                                            case 4://Copiar TODA la lista doble de detalles de servicios al archivo
                                                   objldDS = objml.CopiarArchivoListaDobleDetalleServicio(objArc, objldDS);
                                                   JOptionPane.showMessageDialog(null,"Archivo copiado a la lista doble de detalles de servicio");
                                                   break;
                                        }//fin caso de archivo detalles de servicio
                  
                                    }while(MenuPpalOpcion2_9<5);//fin del mientras de archivo detalles de servicio
                                    break; //fin caso MenuPpalOpcion2_9 archivo detalles de servicio
                                        
                        }//fin caso MenuPpalOpcion2  Detalle de Servicio
                    }while(MenuPpalOpcion2<10);
                    break;//fin opcion 2 del ppal de Detalle de Servicio

                    
///////////////////////////Registrar Convenios - Contrato OPCION 3 DEL MENU PRINCIPAL
              case 3: 
                     do{//ciclo para Registrar o cancelar convenios
                     MenuPpalOpcion3=Validaciones.LeerInt(Menu.MenuPpalOpcion3());
                     switch(MenuPpalOpcion3)//en caso de opcion elegida en menu 
                     {
                         /////////////////////////MANEJO DE COLA DE CONVENIOS  objColaConvenio  ///////////////////////////////
                         case 1://Registrar Convenio
                                objColaConvenio=objmc.IngresarConvenio(objColaConvenio, objld);//recibe la cola de convenios y la lista de clientes
                                break;
                         case 2://Cancelar Convenio
                                 if(objColaConvenio.IsEmpty()==true)
                                      JOptionPane.showMessageDialog(null, "la cola de convenios estÃ¡ vacia");
                                 else {
                                       id=Validaciones.LeerString("Ingrese id de convenio a cancelar o eliminar: ");
                                       JOptionPane.showMessageDialog(null,objmc.LiberarDatoCon(objColaConvenio, objcoa, id));
                                 }//fin si
                                break;
                         case 3://Cerrar Convenio (validar Fecha finalizaciÃ³n) Joseph Sanchez
                                if(objColaConvenio.IsEmpty()==true)
                                      JOptionPane.showMessageDialog(null, "la cola de convenios estÃ¡ vacia");
                                else {
                                    objmc.CerrarConvenio(objColaConvenio, objcoa);
                                     
                                 }//fin si
                                break;
                         case 4://Listar Convenios
                                if(objColaConvenio.IsEmpty()==true)
                                   JOptionPane.showMessageDialog(null, "la cola de convenios estÃ¡ vacia");
                                else 
                                   JOptionPane.showMessageDialog(null, "la cola de convenios es: \n"+objmc.ConcatenarCola(objColaConvenio, objcoa));
                                //fin si
                                break;
                         case 5://Listar clientes con Convenio
                                //Listar clientes con Convenio - Joseph Sanchez
                                if(objColaConvenio.IsEmpty()==true || objld.IsEmpty()==true)
                                {
                                    JOptionPane.showMessageDialog(null,"La lista de clientes o la cola de convenios esta vacia");
                                }else{
                                    String clientesConvenio = objmc.ListarClientesConvenio(objColaConvenio, objcoa, objld);
                                    JOptionPane.showMessageDialog(null,"Los clientes con convenio son:\n" + clientesConvenio);   
                                }//fin si
                                break; 
                         case 6://Consultar un Convenio
                                if(objColaConvenio.IsEmpty()==true)
                                                JOptionPane.showMessageDialog(null, "la cola de convenios estÃ¡ vacia");
                                else {
                                      id=Validaciones.LeerString("Ingrese id de convenio a consultar: ");
                                      obj=objmc.ConsultarCon(objColaConvenio, objcoa, id);
                                      if(obj!=null)
                                        JOptionPane.showMessageDialog(null,"el convenio es: "+obj.toString());
                                     }//fin si
                                break;
                         case 7://Actualizar un Convenio
                                if(objColaConvenio.IsEmpty()==true)
                                    JOptionPane.showMessageDialog(null, "la cola de convenios estÃ¡ vacia");
                                else {
                                      id=Validaciones.LeerString("Ingrese id de convenio a actualizar ");
                                      objmc.actualizarConvenio(objColaConvenio, objcoa, id, objld);
                                     }//fin si
                                break;
                         case 8://CRUD Convenio
                               do{//ciclo para menu de archivo Convenio  Santiago Rodriguez muÃ±oz
                                    MenuPpalOpcion3_8=Validaciones.LeerInt(Menu.MenuPpalOpcion3_8());
                                    switch(MenuPpalOpcion3_8)//en caso de opcion elegida en menu de archivo Convenio
                                    {
                                        case 1:id=Validaciones.LeerString("Id Convenio: ");
                                               objCRUDCon.IngresarConvenio(objArc, id, objld);
                                               break;
                                        case 2:texto=objCRUDCon.MostrarTodo(objArc);
                                               if(texto.equals(""))
                                                   JOptionPane.showMessageDialog(null,"El archivo de Convenios esta vacio");
                                               else
                                                   JOptionPane.showMessageDialog(null,"El archivo Convenios es: \n"+texto);
                                               break;
                                        case 3:id=Validaciones.LeerString("id Convenio consultar: ");
                                               conv=objCRUDCon.Consultar(objArc, id);
                                               if(conv!=null)
                                                     JOptionPane.showMessageDialog(null,"La informaciÃ³n de convenios:\n"+celda.toString());
                                                break;        
                                        case 4://Copiar TODA la cola de celdas al archivo...FALTA 
                                            if(objColaConvenio.IsEmpty() == true) {
            JOptionPane.showMessageDialog(null, "La cola de CONVENIOS estÃ¡ vacÃ­a, no hay nada que copiar.");
       } else {
            // Se invoca el mÃ©todo pasando: Archivo, Cola Original, Cola Auxiliar (objcoa) y ManejoCola (objmc)
            objCRUDCon.CopiarColaConveniosArchivo(objArc, objColaConvenio, objcoa, objmc);
       }
       break;       
                                                                                       
                                    }//fin caso de archivo celdas MenuPpalOpcion3_8   
                                  }while(MenuPpalOpcion3_8<5);//fin del mientras de archivo celdas MenuPpalOpcion3_8
                                  break;  
                         }//fin caso MenuPpalOpcion3
                     }while(MenuPpalOpcion3<9);//fin mientras MenuPpalOpcion3
                     break;         
                     
///////////////////////////Registrar o Generar Pago - Contrato OPCION 4 DEL MENU PRINCIPAL
              case 4: 
                     do{//ciclo para Registrar o cancelar Pagos con sus deralles de pago
                     MenuPpalOpcion4=Validaciones.LeerInt(Menu.MenuPpalOpcion4());
                     switch(MenuPpalOpcion4)//en caso de opcion elegida en menu 
                     {
    ///////////////////////////////////////MANEJO DE LISTA SIMPLE DE PAGO Y DETALLE DE PAGO objlsPago - objlsDetallePago  ///////////////////////////////
                         case 1://Ingresar Pago  
                                objlsPago=objml.CrearListaPagos(objlsPago,objls, objld, objpSer, objlsDetallePago);//se manda 1 para crear por inicio
                                break;
                         case 2://Mostrar listado de pagos
                                if(objlsPago.IsEmpty()==true)
                                   JOptionPane.showMessageDialog(null,"Lista vacia");
                                else
                                   JOptionPane.showMessageDialog(null,"la lista doble de pagos es: \n"+objlsPago.ConcatenarLista());
                               break;
                         case 3://Consultar un pago especÃ­fico
                                if(objlsPago.IsEmpty()==true)
                                   JOptionPane.showMessageDialog(null,"Lista vacia");
                                else{
                                   id = Validaciones.LeerString("Ingrese el id del pago a consultar: "); 
                                   String pagoConsultado = (objml.ConsultarPago(objlsPago,id)).toString();
                                   JOptionPane.showMessageDialog(null,"El pago\n"+ pagoConsultado);
                                }
                                break;
                         case 4://Eliminar un Pago
                                break;
                         case 5://Actualizar un pago especÃ­fico
                                break; 
                          case 6://Ingresar detalles a un pago especÃ­fico
                                if(objlsPago.IsEmpty() == true) {
                                          JOptionPane.showMessageDialog(null, "No hay pagos registrados");
                                } else {
                                          String idPago = Validaciones.LeerString("Ingrese el ID del pago al que desea agregar detalles: ");
                                objml.agregarDetallesAPago(idPago, objlsPago, objlsDetallePago, objpSer);
                                                }
                                break;
                         case 7://Consultar un pago especÃ­fico con sus detalles
                             if(objlsPago.IsEmpty()==true)
                               JOptionPane.showMessageDialog(null,"La lista de pagos estÃ¡ vacÃ­a");
                             else {
                                id = Validaciones.LeerString("Ingrese el id del pago a consultar: ");
                             // Se llama al mÃ©todo pasando las dos listas simples
                               objml.ConsultarPagoConDetalles(id, objlsPago, objlsDetallePago);
                                  }
                                break;
                         case 8://Consultar los pagos de un cliente especÃ­fico
                                break;       
                         case 9://CRUD Pagos
                                do{//ciclo para menu de archivo Pagos
                                    MenuPpalOpcion4_9=Validaciones.LeerInt(Menu.MenuPpalOpcion4_9());
                                    switch(MenuPpalOpcion4_9)//en caso de opcion elegida en menu de archivo pagos
                                    {
                                        case 1:id=Validaciones.LeerString("Id Pago: ");
                                               objCRUDPago.IngresarPago(id, objArc, objls, objld, objpSer, objlsDetallePago);
                                               break;
                                        case 2:texto=objCRUDPago.MostrarTodo(objArc);  
                                               if(texto.equals(""))
                                                   JOptionPane.showMessageDialog(null,"El archivo de pagos esta vacio");
                                               else
                                                   JOptionPane.showMessageDialog(null,"El archivo pagos es: \n"+texto);
                                               break;
                                        case 3:id=Validaciones.LeerString("id pago consultar: ");
                                               objPago=objCRUDPago.Consultar(objArc, id);
                                               if(objPago!=null)
                                                     JOptionPane.showMessageDialog(null,"La informaciÃ³n de pagos:\n"+objPago.toString());
                                                break;        
                                        case 4://Copiar TODA la lista simple de pagos al archivo...FALTA 
                                                if(objlsPago.IsEmpty() == true) {
                                                JOptionPane.showMessageDialog(null, "La lista de PAGOS estÃ¡ vacÃ­a, no hay nada que copiar.");
                                                } else {
                                                // Se invoca el mÃ©todo nuevo creado en CRUDPagos
                                                objCRUDPago.CopiarListaPagosAArchivo(objArc, objlsPago);
                                                }
break;
                                                   
                                       
        
                                                    
                                    }//fin caso de archivo celdas MenuPpalOpcion4_9   
                                  }while(MenuPpalOpcion4_9<5);//fin del mientras de archivo celdas MenuPpalOpcion4_9
                                  break;  
//////////////////////////////////////////////////manejo detalles de pago //////////////////////////////////////////////////////////////////////                         
                         case 10://menu Manejo de Detalles de pago
                                 do{//ciclo para manejar detalles de Pagos
                                    MenuPpalOpcion4_10=Validaciones.LeerInt(Menu.MenuPpalOpcion4_10());
                                    switch(MenuPpalOpcion4_10)//en caso de opcion elegida en menu 
                                    {
    //////////////////////////////////////////////MANEJO DE LISTA SIMPLE DE DETALLE DE PAGO  objlsDetallePago  ///////////////////////////////
                                        case 1://Mostrar todo el contenido de la lista
                                               if(objlsDetallePago.IsEmpty()==true)
                                                      JOptionPane.showMessageDialog(null,"Lista vacia");
                                               else
                                                   JOptionPane.showMessageDialog(null,"la lista doble de pagos es: \n"+objlsDetallePago.ConcatenarLista());
                                               break;
                                        case 2://Actualizar un detalle especifico (requiere el pago en caso de cambiar valor)
                                                 
                                               break;
                                        case 3://Consultar un detalle especÃ­fico
                                               
                                               break;        
                                        case 4: //CRUD detalles de Pagos
                                                do{//ciclo para menu de archivo detallePagos
                                                     MenuPpalOpcion4_10_4=Validaciones.LeerInt(Menu.MenuPpalOpcion4_10_4());
                                                     switch(MenuPpalOpcion4_10_4)//en caso de opcion elegida en menu de archivo pagos
                                                     {
                                                         case 1:id=Validaciones.LeerString("Id Pago: ");
                                                                String idp=Validaciones.ValidarPago(objlsPago);
                                                                objCRUDDetPag.IngresarDetallePago(objArc, id, idp, objpSer);
                                                                break;
                                                         case 2:texto=objCRUDDetPag.MostrarTodo(objArc);
                                                                if(texto.equals(""))
                                                                    JOptionPane.showMessageDialog(null,"El archivo de detalle de pagos esta vacio");
                                                                else
                                                                    JOptionPane.showMessageDialog(null,"El archivo de detalles pago es: \n"+texto);
                                                                break;
                                                         case 3:id=Validaciones.LeerString("id detalle de pago consultar: ");
                                                                objDetPag=objCRUDDetPag.Consultar(objArc, id);
                                                                if(objDetPag!=null)
                                                                      JOptionPane.showMessageDialog(null,"La informaciÃ³n de detalle de pagos:\n"+objDetPag.toString());
                                                                 break;        
                                                         case 4://Copiar TODA la lista simple de pagos al archivo...FALTA 
                                                                 break;        
                                                     }//fin caso de archivo celdas MenuPpalOpcion4_10_4   
                                                   }while(MenuPpalOpcion4_10_4<5);//fin del mientras de archivo celdas MenuPpalOpcion4_10_4
                                                    
                                               break;
                                               
                                        }//FIN CASO MenuPpalOpcion4_10
                                       }while(MenuPpalOpcion4_10<5);//fin mientras MenuPpalOpcion4_10
                                     break;
                                 
                         }//fin caso MenuPpalOpcion4
                     }while(MenuPpalOpcion4<11);//fin mientras MenuPpalOpcion4
                     break;    
                     
           }//fin caso opppal
        
       }while(opppal<5);//mientras del principal hasta la opcion 5  
       
       
        
        
    }//fin del main
    
    
    
}
