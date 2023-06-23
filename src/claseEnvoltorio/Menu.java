package claseEnvoltorio;

import clasesPersonas.Usuario;

import java.time.LocalDate;

public class Menu {

    public Menu()
    {

    }

    public void menuPrincipal() {
        System.out.println(" _________________________________________");
        System.out.println("|        << POKEMARKET 2023 UTN >>        |");
        System.out.println("| 1. REGISTRARSE                          |");
        System.out.println("| 2. INICIAR SESION                       |");
        System.out.println("| 3. CERRAR APLICACION                    |");
        System.out.println("|_________________________________________|");
        System.out.printf("\nIngrese el numero de la opcion que desea abrir: ");
    }

    public void menuAdministrador() {
        System.out.println(" _________________________________________");
        System.out.println("|           <<MENU ADMINISTRADOR>>        |");
        System.out.println("| 1. BORRAR USUARIO                       |");
        System.out.println("| 2. VER USUARIOS                         |");
        System.out.println("| 3. VER TODAS LAS VENTAS                 |");
        System.out.println("| 4. VER TODOS LOS INTERCAMBIOS           |");
        System.out.println("| 5. VER MOVIMIENTOS DE UN USUARIO        |");
        System.out.println("|_________________________________________|");
        System.out.printf("\nIngrese el numero de la opcion que desea abrir: ");

    }

    public void menuAdminMovimientosUsuario() {
        System.out.println(" _________________________________________");
        System.out.println("|     <<VER MOVIMIENTOS DE UN USUARIO>>   |");
        System.out.println("| 1. VER VENTAS                           |");
        System.out.println("| 2. VER COMPRAS                          |");
        System.out.println("| 3. VER INTERCAMBIOS                     |");
        System.out.println("|_________________________________________|");
        System.out.printf("\nIngrese el numero de la opcion que desea abrir: ");

    }

    public void menuInicioSesionUsuario(String nombre)
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("| ****************************************");
        System.out.println("|        Bienvendio/a " + nombre);
        System.out.println("| ****************************************");
        System.out.println("| 1- VER PERFIL");
        System.out.println("| 2- VER MARKET");
        System.out.println("| 3- PUBLICAR ITEM");
        System.out.println("| 4- DESPUBLICAR ITEM");
        System.out.println("| 5- CREAR ITEM NUEVO");
        System.out.println("| 6- CERRAR SESION");
        System.out.printf("| Ingrese opcion: ");
    }

    public void menuVerPerfil(PokeMarket pokeMarket, Usuario actual)
    {
        System.out.println("*****************************************************");
        System.out.println(pokeMarket.verPerfil(actual)); //para ver info de perfil
        System.out.println("*****************************************************");
        System.out.println("\t1-EDITAR DATOS DE PERFIL");
        System.out.println("\t2-ELIMINAR CUENTA");
        System.out.println("\t3-VER INVENTARIO");
        System.out.println("\t4-VER CARRITO");
        System.out.println("\t5-VER HISTORIAL DE COMPRA / VENTA O INTERCAMBIO");
        System.out.println("\t6-VOLVER AL PERFIL");
        System.out.printf("\n\tIngrese opcion: ");
    }

    public void menuEditarDatosDePerfil()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("*******************************");
        System.out.println(" «« EDITAR DATOS PERSONALES »» ");
        System.out.println("*******************************");
        System.out.println("\t1-Modificar nombre");
        System.out.println("\t2-Modificar email");
        System.out.println("\t3-Volver al menu anterior");
        System.out.printf("\n\tIngrese opcion: ");
    }

    public void menuModificarNombre(String nombre)
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("****************************************************************************");
        System.out.println(" «« NOMBRE ACTUAL [ "+nombre +" ] »»");
        System.out.println("**************************************************************************\n");
        System.out.printf("Indique el nuevo nombre: ");
    }

    public void menuModificarMail(String email)
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("******************************************");
        System.out.println(" «« MAIL ACTUAL [ "+email +" ] »»");
        System.out.println("******************************************\n");
        System.out.printf("Indique el nuevo email: ");
    }

    public void menuVerInventarioUsuario(String nombre)
    {
        System.out.println("****************************************************************************");
        System.out.println(" «« INVENTARIO DE "+nombre+", ACTUALIZADO [ "+ LocalDate.now()+" ]");
        System.out.println("**************************************************************************\n");
    }

    public void menuVerHistorialCompraVentaOIntercambio(String nombre)
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("*********************************************************************************************************************");
        System.out.println(" «« HISOTRIALES VENTAS-INTERCAMBIOS-COMPRAS DE "+nombre+", ACTUALIZADOS [ "+ LocalDate.now()+" ]");
        System.out.println("*********************************************************************************************************************\n");
        System.out.println("\t 1-Ver historial de compra");
        System.out.println("\t 2-Ver historial de venta");
        System.out.println("\t 3-Ver historial de intercambio");
        System.out.println("\t 4-Volver al menu anterior");
        System.out.printf("\n\tIngrese opcion: ");
    }

    public void menuVerMarket()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("**************************************************************************************************************");
        System.out.println("                                      MARKET SHOP - ITEMS EN VENTA ");
        System.out.println("**************************************************************************************************************");
        String m = "cargando items...\n";
        for (int i = 0; i < m.length(); i++) {
            System.out.print(m.charAt(i));
            try {
                Thread.sleep(100); // Pausa de 100 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void menuOpcionesDelMarket()
    {
        System.out.println("\n********************************************************");
        System.out.println("               OPCIONES DEL MARKET                      ");
        System.out.println("********************************************************");
        System.out.println("| 1- COMPRAR");
        System.out.println("| 2- INTERCAMBIAR");
        System.out.println("| 3- VOLVER AL PERFIL");
        System.out.printf("| Ingrese opcion: ");
    }

    public void menuOpcionesCarrito()
    {
        System.out.println("\t\t**********************");
        System.out.println("\t\t    CARRO DE ITEMS    ");
        System.out.println("\t\t**********************");
        System.out.println("\t\t\t1- AGREGAR AL CARRITO");
        System.out.println("\t\t\t2- ELIMINAR UN ITEM");
        System.out.println("\t\t\t3- ELIMINAR CARRITO COMPLETO");
        System.out.println("\t\t\t4- MOSTRAR CARRITO");
        System.out.println("\t\t\t5- CONFIRMAR COMPRA-CARRITO");
        System.out.printf("\t\tIngrese opcion: ");
    }

    public void mensajeCompraConfirmada()
    {
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("****************************************************************");
        System.out.println("          COMPRA CONFIRMADA - REVISA TU INVENTARIO !            ");
        System.out.println("****************************************************************");
    }
}
