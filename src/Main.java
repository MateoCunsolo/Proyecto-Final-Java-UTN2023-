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

        PokeMarket pokeMarket = new PokeMarket();
        //crearArchivoConUsuarios(pokeMarket);
        //cargaArchivoConCartas(pokeMarket);

        pokeMarket.leerUsuariosArchivo(); //pasamos usuarios al treeMap de la clase Evoltorio
        System.out.println(pokeMarket.mostrarMapaUsuarios());

        Scanner teclado = new Scanner(System.in);
        char continuar = 's';

        do {
            menuPrincipal();
            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1: //REGISTRARSE
                {
                    Usuario nuevoUsuario = new Usuario();
                    boolean valido = true;

                    do {

                        System.out.println("Ingrese un nombre de usuario: ");
                        teclado.nextLine();
                        String nombre = teclado.nextLine();

                        valido = pokeMarket.contieneUsuario(nombre);
                        if (valido) {
                            System.out.println("El nombre ingresado ya existe, intente con otro!");
                        }

                    } while (valido);

                    System.out.println("Ingrese una contraseña: ");
                    break;
                }
                case 2: //INICIAR SESION
                {
                    //ControladoraArchivos.grabarAdministrador("pokeMarket2023","charizard150");
                    pokeMarket.setAdministrador(ControladoraArchivos.leerAdministrador()); //aaaaaaaa

                    System.out.printf("Ingrese nombre: ");
                    teclado.nextLine();
                    String nombre = teclado.nextLine();

                    System.out.printf("Ingrese contraseña: ");
                    String contra = teclado.nextLine();

                    Administrador admin = new Administrador(nombre, contra);
                    if (pokeMarket.compararAdmin(admin)) //si el nombre y dato ingresado coinciden, se ACCEDE MODO ADMINISTRADOR
                    {
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
                        char s = 's';
                        do {
                            menuAdministrador();
                            int op = teclado.nextInt();
                            switch (op) {
                                case 1: //BORRAR USUARIO probarrrr
                                {
                                    System.out.println("Ingrese nombre de usuario a borrar: ");
                                    String nom = teclado.nextLine();

                                    try {
                                        boolean rta = admin.borrarUsuario(nom, pokeMarket);
                                        if (rta) {
                                            System.out.println("Usuario eliminado exitosamente");
                                        } else {
                                            System.out.println("No se pudo eliminar el usuario solicitado");
                                        }

                                    } catch (UsuarioNoEncontradoException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 2: //VER USUARIOS ok
                                {
                                    System.out.println(admin.verUsuarios(pokeMarket.getMapaUsuarios()));

                                    break;
                                }
                                case 3: //VER TODAS LAS VENTAS probarrrr
                                {
                                    System.out.println(admin.verTodosHistorialVentas(pokeMarket.getMapaUsuarios()));
                                    break;
                                }
                                case 4: //VER TODOS LOS INTERCAMBIOS probarrrr
                                {
                                    System.out.println(admin.verTodosHistorialIntercambios(pokeMarket.getMapaUsuarios()));
                                    break;
                                }
                                case 5: //VER MOVIMIENTOS DE UN USUARIO probarr
                                {
                                    System.out.println("Ingrese nombre de usuario: ");
                                    teclado.nextLine();
                                    String nom = teclado.nextLine();

                                    boolean rta = pokeMarket.contieneUsuario(nom);
                                    if (rta) //si se encuentra el nombre ingresado
                                    {
                                        Usuario usu = pokeMarket.getMapaUsuarios().get(nom);

                                        String m = "Buscando datos del usuario" + nom + "...";
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
                                        int o = teclado.nextInt();
                                        char cont = 's';
                                        do {

                                            switch (o) {
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
                                                case 3: //VER INTERCAMBIOS probarrrrr
                                                {
                                                    System.out.println(usu.mostrarHistorialIntercambios());
                                                    break;
                                                }
                                                default: {
                                                    System.out.println("Opción inválida");
                                                    break;
                                                }
                                            }

                                            System.out.println("\nDesea seguir viendo los movimientos del usuario? (s/n) \n");
                                            String aux = teclado.nextLine();
                                            cont = aux.charAt(0);

                                        } while (cont == 's');

                                    } else //si no se encuentra el nombre ingresado
                                    {
                                        System.out.println("El nombre ingresado no existe en el sistema, intente con otro nombre");
                                    }

                                    break;
                                }
                                default: {
                                    System.out.println("Opción inválida");
                                    break;
                                }
                            }

                            System.out.println("\nDesea seguir navegando en menu administrador? (s/n)");
                            teclado.nextLine();
                            String aux = teclado.nextLine();
                            s = aux.charAt(0);

                        } while (s == 's');

                    } else //verificar si se accede a las FUNCIONES del USUARIO
                    {
                        try {
                            int opcionUsuario1 = 0;
                            int opcionUsuario2, opcionUsuario3, opcionUsuario4, opcionUsuario5 = 0;
                            Usuario actual = pokeMarket.iniciarSesion(nombre, contra);
                            do {
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                System.out.println("| ****************************************");
                                System.out.println("|        Bienvendio/a " + actual.getNombre());
                                System.out.println("| ****************************************");
                                System.out.println("| 1- VER PERFIL");
                                System.out.println("| 2- VER MARKET");
                                System.out.println("| 3- PUBLICAR ITEM");
                                System.out.println("| 4- DESPUBLICAR ITEM");
                                System.out.println("| 5- CERRAR SESION");
                                System.out.printf("| Ingrese opcion: ");
                                opcionUsuario1 = teclado.nextInt();
                                String id = "";
                                switch (opcion) {
                                    case 1: {
                                        System.out.println(pokeMarket.verPerfil(actual)); //para ver info de perfil
                                        opcion = 0;
                                        while (opcion != 6) {
                                            System.out.println("1-EDITAR DATOS DE PERFIL");
                                            System.out.println("2-ELIMINAR CUENTA");
                                            System.out.println("3-VER INVENTARIO");
                                            System.out.println("4-VER CARRITO");
                                            System.out.println("5-VER HISTORIAL DE COMPRA / VENTA O INTERCAMBIO");
                                            System.out.println("6-VOLVER AL PERFIL");
                                            System.out.printf("Ingrese opcion: ");
                                            opcion = teclado.nextInt();
                                            switch (opcion) {
                                                case 1: {
                                                    boolean op = true;
                                                    while (op) {
                                                        String nombreNuevo = " ";
                                                        String emailNuevo = " ";
                                                        String mensaje = " ";
                                                        int op2 = 0;
                                                        System.out.println("1-Modificar nombre");
                                                        System.out.println("2-Modificar email");
                                                        System.out.println("3-Volver al menu anterior");
                                                        op2 = teclado.nextInt();
                                                        if (op2 == 1) {
                                                            System.out.println("Indique el nuevo nombre");
                                                            teclado.nextLine();
                                                            nombreNuevo = teclado.nextLine();
                                                            mensaje = pokeMarket.editarNombre(actual, nombreNuevo);
                                                            System.out.println(mensaje);
                                                            op = false;
                                                        } else if (op2 == 2) {
                                                            System.out.println("Indique el nuevo email");
                                                            teclado.nextLine();
                                                            emailNuevo = teclado.nextLine();
                                                            mensaje = pokeMarket.editarEmail(actual, emailNuevo);
                                                            System.out.println(mensaje);
                                                            op = false;
                                                        } else {
                                                            op = false;
                                                        }
                                                    }
                                                    break;
                                                }
                                                case 2: {
                                                    String mensaje2 = " ";
                                                    mensaje2 = pokeMarket.eliminarCuenta(actual);
                                                    System.out.println(mensaje2);
                                                    break;
                                                }
                                                case 3: {
                                                    String mensaje3 = " ";
                                                    mensaje3 = actual.mostrarInventario();
                                                    System.out.println(mensaje3);
                                                    break;
                                                }
                                                case 4: {
                                                    String mensaje4 = " ";
                                                    mensaje4 = actual.mostrarCarrito();
                                                    System.out.println(mensaje4);
                                                    break;
                                                }
                                                case 5: {
                                                    boolean opc = true;
                                                    while (opc) {
                                                        int opc2 = 0;
                                                        System.out.println("1-Ver historial de compra");
                                                        System.out.println("2-Ver historial de venta");
                                                        System.out.println("3-Ver historial de intercambio");
                                                        System.out.println("4-Volver al menu anterior");
                                                        opc2 = teclado.nextInt();
                                                        if (opc2 == 1) {
                                                            String msj = " ";
                                                            msj = actual.mostrarHistorialCompras();
                                                            System.out.println(msj);
                                                            opc = false;
                                                        } else if (opc2 == 2) {
                                                            String msj2 = " ";
                                                            msj2 = actual.mostrarHistorialVentas();
                                                            System.out.println(msj2);
                                                            opc = false;
                                                        } else if (opc2 == 3) {
                                                            String msj3 = " ";
                                                            msj3 = pokeMarket.mostrarHistorialIntercambio(actual);
                                                            System.out.println(msj3);
                                                            opc = false;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case 2: {
                                        do {
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
                                            System.out.println("********************************************************");
                                            System.out.println("               OPCIONES DEL MARKET                      ");
                                            System.out.println("********************************************************");
                                            System.out.println("| 1- COMPRAR");
                                            System.out.println("| 2- INTERCAMBIAR");
                                            System.out.println("| 3- VOLVER AL PERFIL");
                                            System.out.printf("| Ingrese opcion: ");
                                            opcionUsuario2 = teclado.nextInt();

                                            switch (opcionUsuario2) {
                                                case 1: {
                                                    do {
                                                        System.out.println("\t\t**********************");
                                                        System.out.println("\t\t    CARRO DE ITEMS    ");
                                                        System.out.println("\t\t**********************");
                                                        System.out.println("\t\t\t1- AGREGAR AL CARRITO");
                                                        System.out.println("\t\t\t2- ELIMINAR UN ITEM");
                                                        System.out.println("\t\t\t3- ELIMINAR CARRITO COMPLETO");
                                                        System.out.println("\t\t\t4- MOSTRAR CARRITO");
                                                        System.out.println("\t\t\t5- CONFIRMAR COMPRA-CARRITO");
                                                        System.out.printf("\t\tIngrese opcion: ");
                                                        opcionUsuario3 = teclado.nextInt();
                                                        switch (opcionUsuario3) {
                                                            case 1: {
                                                                System.out.println("*****AGREGAR ITEM AL CARRITO********");
                                                                System.out.printf("Ingrese ID del Item: ");
                                                                teclado.nextLine();
                                                                id = teclado.nextLine();
                                                                Item item = pokeMarket.buscarItemPublicadoXid(id);
                                                                actual.agregarItemAlCarrito(item);
                                                                break;
                                                            }
                                                            case 2: {
                                                                System.out.println("*****ELIMINAR ITEM DEL CARRITO********");
                                                                System.out.printf("Ingrese ID del Item: ");
                                                                teclado.nextLine();
                                                                id = teclado.nextLine();
                                                                actual.eliminarItemDelCarrito(id);
                                                                break;
                                                            }
                                                            case 3: {
                                                                m = "Eliminando carrito ...\n";
                                                                for (int i = 0; i < m.length(); i++) {
                                                                    System.out.print(m.charAt(i));
                                                                    try {
                                                                        Thread.sleep(100); // Pausa de 100 milisegundos
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                                actual.eliminarCarritoTotal();
                                                                System.out.println("Carrito eliminado.");
                                                                break;
                                                            }
                                                            case 4: {
                                                                System.out.println("*** CARRITO DE COMPRAS ***");
                                                                System.out.println(actual.mostrarCarrito());
                                                                break;
                                                            }
                                                            case 5: {
                                                                actual.confirmarCarrito();
                                                                break;
                                                            }
                                                        }
                                                    } while (opcionUsuario3 != 5);

                                                    break;
                                                }
                                                case 2: {
                                                    //INTERCAMBIO
                                                    break;
                                                }
                                                case 3: {
                                                    int a;
                                                    break;
                                                }
                                                default: {
                                                    System.out.println("OPCION INVALIDA . . . INTENTE DE NUEVO");
                                                    break;
                                                }
                                            }
                                        } while (opcionUsuario2 != 3);
                                        break;
                                    }
                                    case 3: {
                                        System.out.printf("Ingrese ID del Item: ");
                                        teclado.nextLine();
                                        id = teclado.nextLine();
                                        Item item = actual.buscarEnInventario(id);
                                        if (item != null) {
                                            actual.publicarItem(item);
                                            pokeMarket.guardarUsuariosArchivo();
                                        }
                                        break;
                                    }
                                    case 4: {
                                        System.out.println("" + actual.verInventario());
                                        System.out.printf("«« Apreta enter para volver al menu del perfil »»");
                                        teclado.nextLine();
                                        String enter = teclado.nextLine();
                                        break;
                                    }
                                    case 5: {
                                        int a;
                                        //cerrarSesion
                                        break;
                                    }
                                }
                            } while (opcionUsuario1 != 5);
                        } catch (UsuarioContraseniaInvalidoException e) {
                            System.out.println("----------------------------------------");
                            System.out.println("««  " + e.getMessage() + "  »»");
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;
                }
                case 3: {
                    continuar = 'n';
                    break;
                }
                default: {
                    System.out.println("Opción inválida");
                    break;
                }
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



