import Archivos.ControladoraArchivos;
import Excepciones.UsuarioContraseniaInvalidoException;
import Excepciones.UsuarioNoEncontradoException;
import claseEnvoltorio.PokeMarket;
import clasesItem.*;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        PokeMarket pokeMarket = new PokeMarket();
        //crearArchivoConUsuarios(pokeMarket);
        //cargaArchivoConCartas(pokeMarket);
        pokeMarket.leerUsuariosArchivo();

        char continuar = 's';
        do {
            menuPrincipal();
            int opcion = teclado.nextInt();
            switch (opcion) {

                case 1:
                    //REGISTRARSE
                    break;
                case 2:
                    //INICIAR SESION

                    // Leer Datos Adminiistrador -> [ Nombre = "pokeMarket2023" ]  &  [ Password = "charizard150" ]
                    pokeMarket.setAdministrador(ControladoraArchivos.leerAdministrador());

                    System.out.printf("Ingrese nombre: ");
                    teclado.nextLine();
                    String nombre = teclado.nextLine();

                    System.out.printf("Ingrese contraseña: ");
                    String contra = teclado.nextLine();

                    Administrador admin = new Administrador(nombre, contra);

                    //Si el nombre y contrasenia coincide con los datos del administrador se accede en MODO-ADMIN.
                    if (pokeMarket.compararAdmin(admin)) {
                        // For para simular que el texto carga de a un caracter
                        String mensaje = "Accediendo a funciones de administrador ...";
                        for (int i = 0; i < mensaje.length(); i++) {
                            System.out.print(mensaje.charAt(i));
                            try {
                                Thread.sleep(100); // Pausa de 100 milisegundos
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println();

                        // Llamado a las funciones del administrador.
                        menuAdministrador();
                        opcion = teclado.nextInt();

                        switch (opcion) {
                            case 1: //BORRAR USUARIO probarrrr
                                System.out.println("Ingrese nombre de usuario a borrar: ");
                                nombre = teclado.nextLine();
                                try {
                                    boolean rta = admin.borrarUsuario(nombre, pokeMarket);
                                    if (rta) {
                                        System.out.println("Usuario eliminado exitosamente");
                                    } else {
                                        System.out.println("No se pudo eliminar el usuario solicitado");
                                    }
                                } catch (UsuarioNoEncontradoException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 2: //VER USUARIOS ok
                                System.out.println(admin.verUsuarios(pokeMarket.getMapaUsuarios()));
                                break;

                            case 3: //VER TODAS LAS VENTAS probarrrr
                                System.out.println(admin.verTodosHistorialVentas(pokeMarket.getMapaUsuarios()));
                                break;

                            case 4: //VER TODOS LOS INTERCAMBIOS probarrrr
                                System.out.println(admin.verTodosHistorialIntercambios(pokeMarket.getMapaUsuarios()));
                                break;

                            case 5: //VER MOVIMIENTOS DE UN USUARIO
                                System.out.printf("Ingrese nombre de usuario: ");
                                nombre = teclado.nextLine();
                                boolean rta = pokeMarket.contieneUsuario(nombre);
                                if (rta) //si se encuentra el nombre ingresado
                                {
                                    Usuario usu = pokeMarket.getMapaUsuarios().get(nombre);
                                    String m = "Buscando datos del usuario" + nombre + "...";
                                    for (int i = 0; i < m.length(); i++) {
                                        System.out.print(m.charAt(i));
                                        try {
                                            Thread.sleep(100); // Pausa de 100 milisegundos
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    System.out.println();
                                    menuAdminMovimientosUsuario();
                                    opcion = teclado.nextInt();
                                    switch (opcion) {
                                        case 1: //VER VENTAS
                                        {
                                            System.out.println(usu.mostrarHistorialVentas());
                                            break;
                                        }
                                        case 2: //VER COMPRAS
                                        {
                                            System.out.println(usu.mostrarHistorialCompras());
                                            break;
                                        }
                                        case 3: //VER INTERCAMBIOS
                                        {
                                            break;
                                        }
                                        default: {
                                            System.out.println("Opción inválida");
                                            break;
                                        }
                                    }


                                } else //si no se encuentra el nombre ingresado
                                {
                                    System.out.println("El nombre ingresado no existe en el sistema, intente con otro nombre");
                                }
                                break;
                            default:
                                System.out.println("Opción inválida");
                                break;
                        }

                    } else //verificar si se accede a las FUNCIONES del USUARIO
                    {
                        try {
                            Usuario actual = pokeMarket.iniciarSesion(nombre, contra);
                            do {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                System.out.println("| ****************************************");
                                System.out.println("|        Bienvendio/a " + actual.getNombre());
                                System.out.println("| ****************************************");
                                System.out.println("| 1- VER PERFIL");
                                System.out.println("| 2- VER MARKET");
                                System.out.println("| 3- PUBLICAR ITEM");
                                System.out.println("| 4- VER INVENTARIO");
                                System.out.println("| 5- CERRAR SESION");
                                System.out.printf("| Ingrese opcion: ");
                                opcion = teclado.nextInt();
                                String id = "";
                                switch (opcion) {
                                    case 1:
                                        //giuli
                                        System.out.printf("HOLa");
                                        break;
                                    case 2:
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
                                        pokeMarket.verItemsPublicados();
                                        do {
                                            System.out.println("1- COMPRAR");
                                            System.out.println("2- INTERCAMBIAR");
                                            System.out.println("3- VOLVER AL PERFIL");
                                            System.out.printf("Ingrese opcion: ");
                                            opcion = teclado.nextInt();
                                            switch (opcion) {
                                                case 1:
                                                    System.out.println("\t\t1- AGREGAR AL CARRITO");
                                                    System.out.println("\t\t2- ELIMINAR UN ITEM");
                                                    System.out.println("\t\t3- ELIMINAR CARRITO COMPLETO");
                                                    System.out.println("\t\t4- MOSTRAR CARRITO");
                                                    System.out.println("\t\t5- CONFIRMAR COMPRA-CARRITO");
                                                    System.out.printf("\t\tIngrese opcion: ");
                                                    opcion = teclado.nextInt();
                                                    switch (opcion) {
                                                        case 1:
                                                            System.out.println("*****AGREGAR ITEM AL CARRITO********");
                                                            System.out.printf("Ingrese ID del Item: ");
                                                            id = teclado.nextLine();
                                                            Item item = pokeMarket.buscarItemPublicadoXid(id);
                                                            actual.agregarItemAlCarrito(item);
                                                            break;
                                                        case 2:
                                                            System.out.println("*****ELIMINAR ITEM DEL CARRITO********");
                                                            System.out.printf("Ingrese ID del Item: ");
                                                            id = teclado.nextLine();
                                                            actual.eliminarItemDelCarrito(id);
                                                            break;
                                                        case 3:
                                                            System.out.printf("Eliminando carrito . . .");
                                                            actual.eliminarCarritoTotal();
                                                            break;
                                                        case 4:
                                                            System.out.println("*** CARRITO DE COMPRAS ***");
                                                            System.out.println(actual.mostrarCarrito());
                                                            break;
                                                        case 5:
                                                            actual.confirmarCarrito();
                                                            break;
                                                    }
                                                    break;
                                                case 2:
                                                    //INTERCAMBIO
                                                    break;
                                                case 3:
                                                    break;
                                            }
                                        } while (opcion != 3);
                                        break;
                                    case 3:
                                        System.out.printf("Ingrese ID del Item: ");
                                        teclado.nextLine();
                                        id = teclado.nextLine();
                                        Item item = actual.buscarEnInventario(id);
                                        if (item != null) {
                                            actual.publicarItem(item);
                                            pokeMarket.guardarUsuariosArchivo();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("" + actual.verInventario());
                                        System.out.printf("«« Apreta enter para volver al menu del perfil »»");
                                        teclado.nextLine();
                                        String enter = teclado.nextLine();
                                        break;
                                    case 5:
                                        //cerrarSesion
                                        break;
                                }
                            } while (opcion != 5);
                        } catch (UsuarioContraseniaInvalidoException e) {
                            System.out.println("----------------------------------------");
                            System.out.println("««  " + e.getMessage() + "  »»");
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;
                case 3:
                    continuar = 'n';
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (continuar == 's');
        teclado.close();

    }

    public static void menuPrincipal() {
        System.out.println(" _________________________________________");
        System.out.println("|                 <<MENU>>                |");
        System.out.println("| 1. REGISTRARSE                          |");
        System.out.println("| 2. INICIAR SESION                       |");
        System.out.println("| 3. CERRAR APLICACION                    |");
        System.out.println("|_________________________________________|");
        System.out.printf("\nIngrese el numero de la opcion que desea abrir: ");
    }

    public static void menuAdministrador() {
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

    public static void menuAdminMovimientosUsuario() {
        System.out.println(" _________________________________________");
        System.out.println("|     <<VER MOVIMIENTOS DE UN USUARIO>>   |");
        System.out.println("| 1. VER VENTAS                           |");
        System.out.println("| 2. VER COMPRAS                          |");
        System.out.println("| 3. VER INTERCAMBIOS                     |");
        System.out.println("|_________________________________________|");
        System.out.printf("\nIngrese el numero de la opcion que desea abrir: ");

    }

    public static void crearArchivoConUsuarios(PokeMarket pokeMarket) {
        //-----------------------------------------------PASAJE DE EL ARCHIVO JSON (MOCK DATA) A ARCHIVO JAVA DE USUARIOS-------------------------
        try {
            String archivoJsonUsu = JsonUtiles.leer("MOCK_DATA (11)");
            JSONArray jsonArray = new JSONArray(archivoJsonUsu);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario aux = new Usuario(jsonObject.getString("first_name"), jsonObject.getString("password"), jsonObject.getString("email"));

                boolean rta = pokeMarket.agregarUsuario(aux);
                if (rta) {
                    System.out.printf("\n Usuario creado con exito" + aux.getNombre());
                } else {
                    System.out.printf("No fue posible crear el usuario :(");
                }
            }
            pokeMarket.guardarUsuariosArchivo();
        } catch (JSONException ex) {
            System.out.println("JSON mal formado");
        }
    }

    public static void cargaArchivoConCartas(PokeMarket pokeMarket) {

        // * CONSTRUCCION DE CLASE ITEM x 250 TO ArrayList<Item> cartasApi **
        ArrayList<Item> cartasDeApi = new ArrayList<>();
        try {

            String archivoJsonCar = JsonUtiles.leer("cartas");
            JSONObject datos = new JSONObject(archivoJsonCar);
            JSONArray arregloCartas = datos.getJSONArray("data");

            for (int i = 0; i < arregloCartas.length(); i++) {

                //--------------CREAMOS EL ITEM CON LOS DATOS DE LA API----------------
                Item item = new Item();

                // ( 1 ) obtenemos la carta
                JSONObject cartaJson = arregloCartas.getJSONObject(i);
                Carta card = new Carta();
                card.fromJson(cartaJson);
                item = card;
                cartasDeApi.add(item);
            }

        } catch (JSONException e) {
            System.out.printf("ERROR FATAL UNA CREACION CLASE NO ESTA CAPTURADA CON SU PROPIO TRY CATCH");
        }

        ///--------------------------------REPARTIENDO CARTAS------------------------------------
        pokeMarket.repartirCartas(cartasDeApi);
    }
}

