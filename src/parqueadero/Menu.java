
package parqueadero;


public class Menu 
{

    public Menu() {
    }
    
     public static String MenuPpal()
    {
        return "M E N U   P R I N C I P A L\n"
                + "1. Registrar información BASE\n"
                + "2. Registrar o Cancelar Servicios\n"
                + "3. Registar Convenios - Contrato\n"
                + "4. Registrar o Generar Pago\n"
                + "5. Salir";
    }
    
      public static String MenuPpalOpcion1()//Registrar información BASE
    {
        return "Registrar información BASE\n"
                + "1. Manejo Clientes\n"
		+ "2. Manejo Empleados\n"
                + "3. Manejo Vehículo\n"
                + "4. Manejo Servicios\n"
                + "5. Manejo Turnos\n"
		+ "6. Manejo Celdas\n"
                + "7. Árbol binario de Empleados\n"
                + "8. Volver al Menu Principal";
    }

      public static String MenuPpalOpcion1_1()
    {
        return "MANEJO CLIENTES  (Lista Doble)\n"
                + "1. Ingresar Clientes con su(s) vehículo(s)\n"
                + "2. Consultar Cliente con su(s) vehiculo(s)\n"
                + "3. Consultar datos básicos de Cliente\n"
                + "4. Eliminar Cliente con su(s) vehículo(s)\n"
                + "5. Actualizar Cliente\n"
                + "6. Listar todos los Clientes\n"
		+ "7. Listar Clientes-vehiculos\n"
 		+ "8. CRUD Clientes\n"
                + "9. Volver al Menu Anterior";
    }

    public static String MenuPpalOpcion1_2()
    {
        return "MANEJO EMPLEADOS  (Lista Doble)\n"
                + "1. Ingresar Empleados\n"
                + "2. Consultar Empleados\n"
                + "3. Cambiar Cargo a un Empleado\n"
		+ "4. Cambiar Turno a un Empleado\n"
                + "5. Eliminar Empleado\n"
                + "6. Actualizar Empleado\n"
                + "7. Listar todos los Empleados\n"
		+ "8. Listar empleados por cargo\n"
		+ "9. Listar empleados por turno\n"
 		+ "10. CRUD Empleados\n"
                + "11. Volver al Menu Anterior";
    }

public static String MenuPpalOpcion1_3()
    {
        return "MANEJO VEHICULOS  (Lista Simple)\n"
                + "1. Ingresar Vehículos con su cliente existente\n"
                + "2. Consultar Vehículo\n"
                + "3. Eliminar Vehículo\n"
                + "4. Actualizar Vehículo\n"
                + "5. Listar todos los Vehículos\n"
		+ "6. Listar vehículos por Estado\n"
		+ "7. Listar vehículos por Tipo\n"
		+ "8. Listar vehículos por Marca\n"
		+ "9. Listar vehículos por Modelo\n"
 		+ "10. CRUD Vehículos\n"
                + "11. Volver al Menu Anterior";
    }

      public static String MenuPpalOpcion1_4()
    {
        return "MANEJO SERVICIOS  (Pila)\n"
                + "1. Ingresar Servicio\n"
                + "2. Consultar Servicio\n"
                + "3. Eliminar Servicio\n"
                + "4. Listar Servicios\n"
                + "5. Actualizar Servicio\n"
		+ "6. CRUD Servicios\n"
                + "7. Volver al Menu Anterior";
    }

      public static String MenuPpalOpcion1_5()
    {
        return "MANEJO TURNOS  (Pila)\n"
                + "1. Ingresar Turno\n"
                + "2. Consultar Turno\n"
                + "3. Eliminar Turno\n"
                + "4. Listar Turnos\n"
                + "5. Actualizar Turno\n"
		+ "6. Listar por Descripción de turno\n"
		+ "7. CRUD Turnos\n"
                + "8. Volver al Menu Anterior";
    }

      public static String MenuPpalOpcion1_6()
    {
        return "MANEJO CELDAS (Cola)\n"
                + "1. Ingresar Celda\n"
                + "2. Consultar Celda\n"
                + "3. Eliminar Celda\n"
                + "4. Listar Celdas\n"
                + "5. Actualizar Celda\n"
		+ "6. Listar por Tipo Vehículo\n"
		+ "7. Listar por Estado\n"
		+ "8. CRUD Celdas\n"
                + "9. Volver al Menu Anterior";
    }

     

public static String MenuPpalOpcion2()//Registrar o Cancelar Parqueo
    {
        return "MANEJO DE SERVICIOS (DetalleServicio ListaDoble)\n"
                + "1. Registrar Servicio o parqueo\n"
		+ "2. Cancelar Servicio o parqueo\n"
                + "3. Cerrar Servicio o parqueo (colocar hora de salida)\n"
                + "4. Listar celdas desocupadas\n"
                + "5. Listar celdas ocupadas\n"
		+ "6. Listar Servicios prestados (detalle de servicios)\n"
		+ "7. Consultar un Servicio o parqueo\n"
		+ "8. Actualizar un Servicio o parqueo\n"
		+ "9. CRUD DetalleServicio\n"
                + "10. Volver al Menu Principal";
    }

public static String MenuPpalOpcion3()//Registar Convenios - Contrato
    {
        return "MANEJO DE CONVENIOS (Cola)\n"
                + "1. Registrar Convenio\n"
		+ "2. Cancelar Convenio\n"
                + "3. Cerrar Convenio (validar Fecha finalización)\n"
                + "4. Listar Convenios\n"
                + "5. Listar clientes con Convenio\n"
		+ "6. Consultar un Convenio\n"
		+ "7. Actualizar un Convenio\n"
		+ "8. CRUD Convenio\n"
                + "9. Volver al Menu Principal";
    }

public static String MenuPpalOpcion4()//Registrar o Generar Pago
    {
        return "MANEJO DE PAGOS (Lista Simple)\n"
                + "1. Ingresar Pago\n"
                + "2. Mostrar listado de pagos\n"
                + "3. Consultar un pago específico\n" 
                + "4. Eliminar un Pago\n" 
                + "5. Actualizar un pago específico\n"
                + "6. Ingresar detalles a un pago específico\n" 
                + "7. Consultar un pago específico con sus detalles\n" 
                + "8. Consultar los pagos de un cliente específico\n"
		+ "9. CRUD de Pagos\n"
		+ "10. MANEJO DE DETALLES DE PAGO\n"  
                + "11. Volver al Menu Principal\n";
    }

public static String MenuPpalOpcion4_10()//manejo de detalles de pago - lo maneja PAGOS
    {
      return "Menu LISTA SIMPLE DETALLE DE PAGO\n"
                + "1. Mostrar todo el contenido de la lista\n"
                + "2. Actualizar un detalle especifico (requiere el pago en caso de cambiar valor)\n" 
                + "3. Consultar un detalle específico\n"
		+ "4. CRUD de detalles Pagos\n"
                + "5. Volver a menu de Manejo pagos\n";
    }

 public static String MenuPpalOpcion1_1_8()//crud clientes
    {
      return "***** M E N U  A R C H I V O  C L I E N T E S *****\n"
                + "1. Ingresar en archivo clientes\n"
                + "2. Mostrar Todo el archivo clientes\n"
                + "3. Consultar un cliente específico\n"
		+ "4. Copiar TODA la lista doble de clientes al archivo\n"
                + "5. Volver al menu anterior\n";
    }

public static String MenuPpalOpcion1_2_10()//crud empleados
    {
      return "***** M E N U  A R C H I V O  E M P L E A D O S *****\n"
                + "1. Ingresar en archivo empleados\n"
                + "2. Mostrar Todo el archivo empleados\n"
                + "3. Consultar un empleado específico\n"
		+ "4. Copiar TODA la lista doble de empleados al archivo\n"
                + "5. Volver al menu anterior\n";
    }

public static String MenuPpalOpcion1_3_10()//crud vehiculos
    {
      return "***** M E N U  A R C H I V O  V E H I C U L O S *****\n"
                + "1. Ingresar en archivo vehículos\n"
                + "2. Mostrar Todo el archivo vehículos\n"
                + "3. Consultar un vehículo específico\n"
		+ "4. Copiar TODA la lista simple de vehículos al archivo\n"
                + "5. Volver al menu anterior\n";
    }

public static String MenuPpalOpcion1_4_6()//crud servicios
    {
      return "***** M E N U  A R C H I V O  S E R V I C I O S *****\n"
                + "1. Ingresar en archivo servicios\n"
                + "2. Mostrar Todo el archivo servicios\n"
                + "3. Consultar un servicio específico\n"
		+ "4. Copiar TODA la pila de servicios al archivo\n"
                + "5. Volver al menu anterior\n";
    }

public static String MenuPpalOpcion1_5_7()//crud turnos
    {
      return "***** M E N U  A R C H I V O  T U R N O S *****\n"
                + "1. Ingresar en archivo turnos\n"
                + "2. Mostrar Todo el archivo turnos\n"
                + "3. Consultar un turno específico\n"
		+ "4. Copiar TODA la pila de turnos al archivo\n"
                + "5. Volver al menu anterior\n";
    }


public static String MenuPpalOpcion1_6_8()//crud celdas
    {
      return "***** M E N U  A R C H I V O  C E L D A S  *****\n"
                + "1. Ingresar en archivo celdas\n"
                + "2. Mostrar Todo el archivo celdas\n"
                + "3. Consultar una celda específica\n"
		+ "4. Copiar TODA la cola de celdas al archivo\n"
                + "5. Volver al menu anterior\n";
    }

public static String MenuPpalOpcion2_9()//crud detalle servicio-parqueos
    {
      return "***** M E N U  A R C H I V O  D E T A L L E S  D E  S E R V I C I O S *****\n"
                + "1. Ingresar en archivo detalles de servicios\n"
                + "2. Mostrar Todo el archivo detalles de servicios\n"
                + "3. Consultar un detalle de servicio específico\n"
		+ "4. Copiar TODA la lista doble de detalles de servicios al archivo\n"
                + "5. Volver al menu anterior\n";

    }

public static String MenuPpalOpcion3_8()//crud convenios
    {
      return "***** M E N U  A R C H I V O  C O N V E N I O S *****\n"
                + "1. Ingresar en archivo convenios\n"
                + "2. Mostrar Todo el archivo convenios\n"
                + "3. Consultar un convenio específico\n"
		+ "4. Copiar TODA la cola de convenios\n"
                + "5. Volver al menu anterior\n";

    }

public static String MenuPpalOpcion4_9()//crud pagos
    {
      return "***** M E N U  A R C H I V O  P A G O S *****\n"
                + "1. Ingresar en archivo pagos\n"
                + "2. Mostrar Todo el archivo pagos\n"
                + "3. Consultar un pago específico\n"
		+ "4. Copiar TODA la lista simple de pagos\n"
                + "5. Volver al menu anterior\n";

    }

public static String MenuPpalOpcion4_10_4()//crud detalle de pagos
    {
      return "***** M E N U  A R C H I V O  D E T A L L E S  D E  P A G O S *****\n"
                + "1. Ingresar en archivo detalles de pagos\n"
                + "2. Mostrar Todo el archivo detalles de pagos\n"
                + "3. Consultar un detalle de pago específico\n"
		+ "4. Copiar TODA la lista simple de detalles de pagos\n"
                + "5. Volver al menu anterior\n";

    }

public static String MenuPpalOpcion1_7()
    {
        return "MANEJO CELDAS (Cola)\n"
                + "1. Inorden\n"
                + "2. PostOrden\n"
                + "3. Preorden\n"
                + "4. Peso\n"
                + "5. Hermanos\n"
                + "6. Numero de nodos\n"
                + "7. Hijos Izquierdos\n"
                + "8. Ancestros de un nodo\n"
                + "9. Altura\n"
                + "10. Insertar Hoja\n"
                + "11. Insertar SubArbol\n"
                + "12. Mostrar empleados por cargo\n"
                + "13. Mostrar Camino de un nodo a otro\n"
                + "14. Archivos en arboles"
                + "15. Terminar";
    }


}


