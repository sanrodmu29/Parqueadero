Guía General del Proyecto – Sistema de Gestión de Parqueadero

1. Introducción

Este documento describe las necesidades y el alcance del sistema de gestión de un parqueadero. 
Está dirigido a orientar el inicio del proyecto desde cero, definiendo qué debe hacer el sistema, 
cómo se espera que lo use el personal del parqueadero y cuáles son los objetivos principales del sistema.

2. Objetivo del sistema

El objetivo del sistema es apoyar la operación diaria del parqueadero, permitiendo llevar control 
de los vehículos que ingresan y salen, los clientes, las celdas disponibles y los pagos realizados, 
de forma organizada, clara y confiable, reduciendo errores y el uso de registros manuales.

3. Alcance del sistema

El sistema permitirá:
- Registrar información básica de clientes.
- Registrar información básica de vehículos.
- Controlar el ingreso y salida de vehículos.
- Administrar las celdas del parqueadero.
- Registrar pagos y consultar su historial.
- Generar reportes básicos para consulta del administrador.
- Guardar y recuperar información para respaldo.

No se contempla en esta versión:
- Integración con sistemas externos.
- Pagos en línea.
- Manejo de múltiples sedes de parqueadero.

4. Descripción general del funcionamiento

El sistema contará con un menú principal desde el cual el usuario podrá acceder a las diferentes opciones:
registro de clientes, registro de vehículos, control de ingresos y salidas, gestión de pagos y consultas.

El usuario interactuará con la aplicación mediante ventanas o cuadros de diálogo que le solicitarán los datos 
necesarios y mostrarán los resultados de las operaciones realizadas.

5. Requerimientos funcionales

5.1. Gestión de clientes  
El sistema debe permitir:
- Registrar clientes nuevos.
- Consultar clientes por su identificación.
- Modificar los datos de un cliente.
- Eliminar clientes del sistema.

5.2. Gestión de vehículos  
El sistema debe permitir:
- Registrar vehículos nuevos.
- Consultar vehículos por su placa.
- Modificar los datos de un vehículo.
- Eliminar vehículos del sistema.
- Asociar cada vehículo a un cliente.

5.3. Gestión de celdas  
El sistema debe permitir:
- Registrar las celdas del parqueadero.
- Consultar el estado de una celda (libre u ocupada).
- Asignar una celda a un vehículo cuando ingresa.
- Liberar la celda cuando el vehículo sale.

5.4. Gestión de ingresos y salidas  
El sistema debe permitir:
- Registrar la fecha y hora de ingreso de un vehículo.
- Registrar la fecha y hora de salida de un vehículo.
- Calcular el tiempo de permanencia del vehículo en el parqueadero.

5.5. Gestión de pagos  
El sistema debe permitir:
- Generar un registro de pago por cada salida de vehículo.
- Consultar un pago específico por su identificador.
- Consultar los pagos realizados por un cliente específico.
- Mostrar los detalles de cada pago.

5.6. Consultas y reportes  
El sistema debe permitir:
- Listar los vehículos que se encuentran actualmente en el parqueadero.
- Mostrar el estado general del parqueadero (celdas libres y ocupadas).
- Mostrar el historial de pagos por cliente.
- Mostrar listados generales de clientes, vehículos y pagos.

5.7. Respaldo de información  
El sistema debe permitir:
- Guardar la información registrada en archivos de respaldo.
- Cargar la información almacenada previamente al iniciar la aplicación.

6. Requerimientos no funcionales

- El sistema debe ser fácil de usar para personas sin conocimientos técnicos.
- La interfaz debe ser clara y con menús entendibles.
- Los mensajes de error deben ser comprensibles para el usuario.
- El sistema debe validar que los datos ingresados sean correctos y completos.
- El sistema debe responder rápidamente a las operaciones básicas.
- El sistema debe mantener la información organizada y sin duplicados.

7. Roles de usuario

- Administrador: puede acceder a todas las funciones del sistema, incluyendo registros, modificaciones, eliminaciones y respaldos.
- Operador: puede registrar ingresos, salidas, pagos y realizar consultas básicas.
- Consultor: solo puede ver información y generar reportes, sin modificar datos.

8. Escenarios de uso

- Un cliente llega al parqueadero: el operador registra el ingreso del vehículo y asigna una celda disponible.
- Un cliente sale del parqueadero: el operador registra la salida, calcula el valor a pagar y genera el pago.
- El administrador consulta los pagos de un cliente para verificar su historial.
- El administrador realiza un respaldo de la información al final del día.

9. Criterios de aceptación

- El sistema permite registrar correctamente clientes, vehículos y pagos.
- El sistema asigna y libera celdas correctamente según los ingresos y salidas.
- Las consultas por cliente o por pago muestran la información correcta.
- Los respaldos de información se generan y se pueden recuperar.
- El menú principal permite acceder claramente a todas las funciones.

10. Entregables del proyecto

- Aplicación funcional del sistema de parqueadero.
- Documento de guía del usuario con instrucciones básicas de uso.
- Archivos de respaldo generados por el sistema.
- Documento de requerimientos del sistema.
