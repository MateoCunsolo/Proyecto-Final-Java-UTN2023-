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

import java.util.*;

public class Main {
    public static void main(String[] args) {

        PokeMarket pokeMarket = new PokeMarket();
        //crearArchivoConUsuarios(pokeMarket);
        //cargaArchivoConCartas(pokeMarket);

        pokeMarket.leerUsuariosArchivo(); //pasamos usuarios al treeMap de la clase Evoltorio

        System.out.println("\n\nUSUARIOS EN TREEMAP PASADOS DE ARCHIVO \n");
        System.out.println(pokeMarket.mostrarMapaUsuarios());

        Scanner teclado = new Scanner(System.in);

        menuPrincipal();
        int opcion = teclado.nextInt();

        //pokeMarket.leerUsuariosArchivo(); //pasamos archivo usuarios al treeMap de la clase Evoltorio
        //System.out.println(pokeMarket.mostrarMapaUsuarios());

        char continuar = 's';

        do {

            switch (opcion) {

                case 1: //REGISTRARSE
                {
                    System.out.println("¡Hola!");
                    break;
                }
                case 2: //INICIAR SESION
                {
                    // ControladoraArchivos.grabarAdministrador("pokeMarket2023","charizard150");
                    pokeMarket.setAdministrador(ControladoraArchivos.leerAdministrador()); //aaaaaaaa

                    System.out.println("Ingrese nombre: ");
                    teclado.nextLine();
                    String nombre = teclado.nextLine();

                    System.out.println("Ingrese contraseña: ");
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
                            case 5: //VER MOVIMIENTOS DE UN USUARIO

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
                            Usuario actual = pokeMarket.iniciarSesion("Aeriela", "guks5383");
                            opcion = 0;
                            while (opcion != 4) {
                                System.out.println("1- VER PERFIL");
                                System.out.println("2- VER MARKET");
                                System.out.println("3- PUBLICAR ITEM");
                                System.out.println("4- CERRAR SESION");
                                System.out.printf("Ingrese opcion: ");
                                opcion = teclado.nextInt();
                                switch (opcion) {
                                    case 1:
                                        pokeMarket.verPerfil(actual); //para ver info de perfil
                                        opcion = 0;
                                        while (opcion!=6)
                                            {
                                                System.out.println("1-EDITAR DATOS DE PERFIL");
                                                System.out.println("2-ELIMINAR CUENTA");
                                                System.out.println("3-VER INVENTARIO");
                                                System.out.println("4-VER CARRITO");
                                                System.out.println("5-VER HISTORIAL DE COMPRA / VENTA O INTERCAMBIO");
                                                System.out.println("6-VOLVER AL PERFIL");
                                                System.out.printf("Ingrese opcion: ");
                                                opcion = teclado.nextInt();
                                                switch (opcion){
                                                    case 1:
                                                        boolean op =true;
                                                        while(op)
                                                        {
                                                            String nombreNuevo=" ";
                                                            String emailNuevo= " ";
                                                            String mensaje = " ";
                                                            int op2 = 0;
                                                            System.out.println("1-Modificar nombre");
                                                            System.out.println("2-Modificar email");
                                                            System.out.println("3-Modificar nombre y email");
                                                            System.out.println("4-Volver al menu anterior");
                                                            op2 = teclado.nextInt();
                                                            if(op2==1)
                                                            {
                                                                System.out.println("Indique el nuevo nombre");
                                                                nombreNuevo = teclado.nextLine();
                                                                mensaje = pokeMarket.editarNombre(actual,nombreNuevo);
                                                                System.out.println(mensaje);
                                                                op=false;
                                                            }
                                                            else if (op2 ==2)
                                                            {
                                                                System.out.println("Indique el nuevo email");
                                                                emailNuevo = teclado.nextLine();
                                                                mensaje = pokeMarket.editarEmail(actual,emailNuevo);
                                                                System.out.println(mensaje);
                                                                op=false;
                                                            }

                                                            else if(op2==3)
                                                            {
                                                                System.out.println("Indique el nuevo nombre");
                                                                nombreNuevo = teclado.nextLine();
                                                                System.out.println("Indique el nuevo email");
                                                                emailNuevo = teclado.nextLine();
                                                                System.out.println();
                                                                mensaje = pokeMarket.editarDatosCompletos(actual,emailNuevo,nombreNuevo);
                                                                System.out.println(mensaje);
                                                                op=false;
                                                            }
                                                            else
                                                            {
                                                                op=false;
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        String mensaje2 = " ";
                                                        mensaje2 =pokeMarket.eliminarCuenta(actual);
                                                        System.out.println(mensaje2);
                                                        break;
                                                    case 3:
                                                        String mensaje3 = " ";
                                                        mensaje3 = pokeMarket.verInventario(actual);
                                                        System.out.println(mensaje3);
                                                        break;
                                                    case 4:
                                                        String mensaje4 = " ";
                                                        mensaje4 = actual.mostrarCarrito();
                                                        System.out.println(mensaje4);
                                                    case 5:
                                                        boolean opc = true;
                                                        while(opc)
                                                        {
                                                            int opc2 = 0;
                                                            System.out.println("1-Ver historial de compra");
                                                            System.out.println("2-Ver historial de venta");
                                                            System.out.println("3-Ver historial de intercambio");
                                                            System.out.println("4-Volver al menu anterior");
                                                            opc2 = teclado.nextInt();
                                                            if(opc2==1)
                                                            {
                                                                String msj = " ";
                                                                msj= actual.mostrarHistorialCompras();
                                                                System.out.println(msj);
                                                                opc=false;
                                                            }
                                                            else if(opc2==2)
                                                            {
                                                                String msj2= " ";
                                                                msj2=actual.mostrarHistorialVentas();
                                                                System.out.println(msj2);
                                                                opc=false;
                                                            }
                                                            else if (opc2==3)
                                                            {
                                                                String msj3= " ";
                                                                msj3= pokeMarket.mostrarHistorialIntercambio(actual);
                                                                System.out.println(msj3);
                                                                opc=false;
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                        break;
                                    case 2:
                                        pokeMarket.verItemsPublicados();
                                        opcion = 0;
                                        while (opcion != 3) {
                                            System.out.println("1- COMPRAR");
                                            System.out.println("2- INTERCAMBIAR");
                                            System.out.println("3- VOLVER AL PERFIL");
                                            System.out.printf("Ingrese opcion: ");
                                            opcion = teclado.nextInt();
                                            String id = "";
                                            switch (opcion) {
                                                case 1:
                                                    opcion = 0;
                                                    System.out.println("1- AGREGAR AL CARRITO");
                                                    System.out.println("2- ELIMINAR UN ITEM");
                                                    System.out.println("3- ELIMINAR CARRITO COMPLETO");
                                                    System.out.println("4- MOSTRAR CARRITO");
                                                    System.out.println("5- CONFIRMAR COMPRA-CARRITO");
                                                    System.out.printf("Ingrese opcion: ");
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
                                                    break;

                                                case 3:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 3:

                                        break;
                                    case 4:

                                        break;
                                }
                            }
                        } catch (UsuarioContraseniaInvalidoException e) {
                            System.out.println("----------------------------------------");
                            System.out.println("««  " + e.getMessage() + "  »»");
                            System.out.println("----------------------------------------");
                        }
                    }

                    break;
                }
                default:
                    System.out.println("Opción inválida");
                    break;
            }

            System.out.println("\nDesea seguir navegando? (s/n) \n");
            String rta = teclado.nextLine();
            continuar = rta.charAt(0);

        } while (continuar == 's');

        teclado.close();

    }

    public static void menuPrincipal() {
        System.out.println(" _________________________________________");
        System.out.println("|                 <<MENU>>                |");
        System.out.println("| 1. REGISTRARSE                          |");
        System.out.println("| 2. INICIAR SESION                       |");
        System.out.println("|_________________________________________|");
        System.out.println("\nIngrese el numero de la opcion que desea abrir: ");
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
        System.out.println("\nIngrese el numero de la opcion que desea abrir: ");

    }

    public static void menuAdminMovimientosUsuario() {
        System.out.println(" _________________________________________");
        System.out.println("|     <<VER MOVIMIENTOS DE UN USUARIO>>   |");
        System.out.println("| 1. VER VENTAS                           |");
        System.out.println("| 2. VER COMPRAS                          |");
        System.out.println("| 3. VER INTERCAMBIOS                     |");
        System.out.println("|_________________________________________|");
        System.out.println("\nIngrese el numero de la opcion que desea abrir: ");

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
            //pokeMarket.leerUsuariosArchivo();


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

            ///--------------------------------REPARTIENDO CARTAS------------------------------------
            pokeMarket.repartirCartas(cartasDeApi);
            System.out.printf(pokeMarket.mostrarMapaUsuarios());

        } catch (JSONException e) {
            System.out.printf("ERROR FATAL UNA CREACION CLASE NO ESTA CAPTURADA CON SU PROPIO TRY CATCH");
        }

        ///--------------------------------REPARTIENDO CARTAS------------------------------------
        pokeMarket.repartirCartas(cartasDeApi);
        System.out.printf(pokeMarket.mostrarMapaUsuarios());
    }
}



