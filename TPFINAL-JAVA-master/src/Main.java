import Archivos.ControladoraArchivos;
import Excepciones.*;
import Transacciones.Intercambio;
import claseEnvoltorio.Menu;
import claseEnvoltorio.PokeMarket;
import clasesItem.*;
import clasesPersonas.Administrador;
import clasesPersonas.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PokeMarket pokeMarket = new PokeMarket();
        Menu menu = new Menu();
        //crearArchivoConUsuarios(pokeMarket);
        //cargaArchivoConCartas(pokeMarket);
        //ControladoraArchivos.grabarAdministrador("pokeMarket2023","charizard150");

        pokeMarket.leerUsuariosArchivo(); //pasamos usuarios al treeMap de la clase Evoltorio

        Scanner teclado = new Scanner(System.in);
        char continuar = 's';

        do {
            menu.menuPrincipal();
            int opcion = teclado.nextInt();

            switch (opcion) {

                case 1: //REGISTRARSE
                {
                    boolean valido = true;
                    String nombre = " ";

                    teclado.nextLine();
                    do {

                        System.out.printf("\nIngrese un nombre de usuario: ");
                        nombre = teclado.nextLine();
                        valido = pokeMarket.contieneUsuario(nombre);
                        if (valido) {
                            System.out.println("El nombre ingresado ya existe, intente con otro!");
                        }

                    } while (valido);


                    int intentosMaximos = 3;
                    int intentos = 0;

                    for (int i = 1; i <= intentosMaximos; i++) {
                        System.out.printf("\nIntento %d: Ingrese una contraseña de 8 caracteres con 4 letras minúsculas y 4 números: ", i);
                        String contrasenia = teclado.nextLine();

                        if (pokeMarket.validarContrasenia(contrasenia)) {
                            System.out.println("Contraseña válida. \n");

                            //se pide el mail
                            System.out.printf("Ingrese su email: ");
                            String email = teclado.nextLine();

                            String aux = "Registrando usuario ...\n";
                            for (int j = 0; j < aux.length(); j++) {
                                System.out.print(aux.charAt(j));
                                try {
                                    Thread.sleep(100); // Pausa de 100 milisegundos
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            Usuario usuarioNuevo = new Usuario(nombre, contrasenia, email); //creamos usuario nuevo
                            pokeMarket.agregarUsuario(usuarioNuevo); //se agrega al treemap

                            System.out.println("¡Registro existoso, bienvenid@ "+usuarioNuevo.getNombre());

                            i = 4; //se corta el bucle for

                        } else //si la contraseña no es valida y se llega al n° de intentos maximos, se termina el bucle y se vuelve al menu
                        {
                            System.out.println("La contraseña no cumple con los requisitos.");
                            intentos++;

                            if (intentos == intentosMaximos) {
                                System.out.println("Ha alcanzado el número máximo de intentos. Registro fallido.");
                            }
                        }
                    }

                    break;
                }
                case 2: //INICIAR SESION
                {

                    pokeMarket.cargaInicioAdministrador();

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

                            menu.menuAdministrador();
                            int op = teclado.nextInt();

                            switch (op) {
                                case 1: //BORRAR USUARIO
                                {
                                    System.out.println("Ingrese nombre de usuario a borrar: ");
                                    teclado.nextLine();
                                    String nom = teclado.nextLine();

                                    try {
                                        boolean rta = pokeMarket.borrarUnUsuario(nom);
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
                                case 2: //VER USUARIOS
                                {
                                    System.out.println(pokeMarket.verUsuariosAdmin());
                                    break;
                                }
                                case 3: //VER TODAS LAS VENTAS
                                {
                                    System.out.println(pokeMarket.verTodosHistorialesVenta());
                                    break;
                                }
                                case 4: //VER TODOS LOS INTERCAMBIOS
                                {
                                    System.out.println(pokeMarket.verTodosHistorialesIntercambios());
                                    break;
                                }
                                case 5: //VER MOVIMIENTOS DE UN USUARIO
                                {
                                    System.out.println("Ingrese nombre de usuario: ");
                                    teclado.nextLine();
                                    String nom = teclado.nextLine();

                                    boolean rta = pokeMarket.contieneUsuario(nom);
                                    if (rta) //si se encuentra el nombre ingresado
                                    {
                                        Usuario usu = pokeMarket.retornarUsuarioXNombre(nom);

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

                                        char cont = 's';
                                        do {
                                            menu.menuAdminMovimientosUsuario();
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
                                                    System.out.println(usu.mostrarHistorialIntercambios());
                                                    break;
                                                }
                                                default: {
                                                    System.out.println("Opción inválida");
                                                    break;
                                                }
                                            }
                                            System.out.println("\nDesea seguir viendo los movimientos del usuario? (s/n) \n");
                                            teclado.nextLine();
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
                            teclado.nextLine();
                            System.out.println("\nDesea seguir navegando en menu administrador? (s/n)");
                            String aux = teclado.nextLine();
                            s = aux.charAt(0);

                        } while (s == 's');

                    } else //verificar si se accede a las FUNCIONES del USUARIO
                    {
                        try {
                            int opcionUsuario1, opcionUsuario2, opcionUsuario3, opcionUsuario4, opcionUsuario5 = 0;
                            Usuario actual = pokeMarket.iniciarSesion(nombre, contra);
                            do {
                                menu.menuInicioSesionUsuario(actual.getNombre());
                                opcionUsuario1 = teclado.nextInt();
                                String id = "";
                                int bandera = 0;
                                switch (opcionUsuario1) {
                                    case 1: //VER PERFIL
                                    {
                                        opcion = 0;
                                        while (opcion != 6) {
                                            if(bandera == 0) {
                                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                            }
                                            menu.menuVerPerfil(pokeMarket, actual);
                                            opcion = teclado.nextInt();
                                            switch (opcion) {

                                                case 1: //EDITAR DATOS DE PERFIL
                                                {
                                                    boolean op = true;
                                                    while (op) {
                                                        String nombreNuevo = " ";
                                                        String emailNuevo = " ";
                                                        String mensaje = " ";

                                                        int op2 = 0;

                                                        menu.menuEditarDatosDePerfil();
                                                        op2 = teclado.nextInt();

                                                        if (op2 == 1) //MODIFICAR NOMBRE
                                                        {
                                                            menu.menuModificarNombre(actual.getNombre());
                                                            teclado.nextLine();
                                                            nombreNuevo = teclado.nextLine();

                                                            System.out.println(pokeMarket.editarNombre(nombreNuevo, actual));
                                                            System.out.println("Presiona Enter para continuar...");
                                                            teclado.nextLine();

                                                            op = false;
                                                        }
                                                        else if (op2 == 2) //MODIFICAR MAIL
                                                        {
                                                            menu.menuModificarMail(actual.getEmail());
                                                            teclado.nextLine();
                                                            emailNuevo = teclado.nextLine();

                                                            System.out.println(pokeMarket.editarEmail(emailNuevo, actual));
                                                            System.out.println("Presiona Enter para continuar...");
                                                            teclado.nextLine();

                                                            op = false;
                                                        } else  //VOLVER
                                                        {
                                                            op = false;
                                                        }
                                                    }
                                                    break;
                                                }
                                                case 2: //ELIMINAR CUENTA
                                                {
                                                    System.out.println(pokeMarket.eliminarCuenta(actual));
                                                    System.out.println("Presiona Enter para continuar...");
                                                    teclado.nextLine();
                                                    break;
                                                }
                                                case 3: //VER INVENTARIO
                                                {
                                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                                    bandera = 1;

                                                    menu.menuVerInventarioUsuario(actual.getNombre());
                                                    System.out.println(actual.mostrarInventario());

                                                    try {
                                                       String m1 = "Volviendo al perfil en 20 segundos . . .\n\n\n";
                                                        for (int i = 0; i < m1.length(); i++) {
                                                            System.out.print(m1.charAt(i));
                                                            try {
                                                                Thread.sleep(100); // Pausa de 100 milisegundos
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        Thread.sleep(12000);
                                                    }catch (InterruptedException e)
                                                    {
                                                        System.out.println(e.getMessage());
                                                    }
                                                    break;
                                                }
                                                case 4: //VER CARRITO
                                                {
                                                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                                    System.out.println(actual.mostrarCarrito());
                                                    System.out.println("\nPresiona Enter para continuar ...");
                                                    teclado.nextLine();
                                                    teclado.nextLine();
                                                    break;
                                                }
                                                case 5: //VER HISTORIAL DE COMPRA O VENTA O INTERCAMBIO
                                                {
                                                    boolean opc = true;
                                                    while (opc) {

                                                        int opc2 = 0;
                                                       menu.menuVerHistorialCompraVentaOIntercambio(actual.getNombre());
                                                       opc2 = teclado.nextInt();
                                                       System.out.println("\n\n\n");

                                                        if (opc2 == 1) //VER HISTORIAL DE COMPRAS
                                                        {
                                                            System.out.println(actual.mostrarHistorialCompras());
                                                            System.out.printf("\nPresiona Enter para volver al perfil ...");
                                                            teclado.nextLine();
                                                            teclado.nextLine();
                                                            opc = false;
                                                        } else if (opc2 == 2) //VER HISTORIAL DE VENTAS
                                                        {
                                                            System.out.println(actual.mostrarHistorialVentas());
                                                            System.out.printf("\nPresiona Enter para volver al perfil ...");
                                                            teclado.nextLine();
                                                            teclado.nextLine();
                                                            opc = false;
                                                        } else if (opc2 == 3) //VER HISTORIAL INTERCAMBIOS
                                                        {
                                                            System.out.println(actual.mostrarHistorialIntercambios());
                                                            System.out.printf("\nPresiona Enter para volver al perfil ...");
                                                            teclado.nextLine();
                                                            teclado.nextLine();
                                                            opc = false;
                                                        }
                                                        else {
                                                            opc = false;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case 2: //VER MARKET
                                    {
                                        do {
                                            menu.menuVerMarket();
                                            System.out.println(pokeMarket.verItemsPublicados());
                                            menu.menuOpcionesDelMarket();
                                            opcionUsuario2 = teclado.nextInt();

                                            switch (opcionUsuario2) {
                                                case 1: //COMPRAR
                                                {
                                                    teclado.nextLine();
                                                    do {
                                                        menu.menuOpcionesCarrito();
                                                        opcionUsuario3 = teclado.nextInt();
                                                        switch (opcionUsuario3) {
                                                            case 1: //AGREGAR AL CARRITO
                                                            {
                                                                System.out.println("*****AGREGAR ITEM AL CARRITO********");
                                                                System.out.printf("Ingrese ID del Item: ");
                                                                teclado.nextLine();
                                                                id = teclado.nextLine();
                                                                try {
                                                                    Item item = pokeMarket.buscarItemPublicadoXid(id);
                                                                    actual.agregarItemAlCarrito(item);

                                                                } catch (ItemNoEncontradoException f) {
                                                                    System.out.println(f.getMensaje());
                                                                }
                                                                break;
                                                            }
                                                            case 2: //ELIMINAR UN ITEM
                                                            {
                                                                System.out.println("*****ELIMINAR ITEM DEL CARRITO********");
                                                                teclado.nextLine();
                                                                System.out.printf("Ingrese ID del Item: ");
                                                                id = teclado.nextLine();

                                                                try {
                                                                    pokeMarket.eliminarItemDelCarrito(actual, id);
                                                                } catch (CarritoVacioException l) {
                                                                    System.out.println(l.getMensaje());
                                                                }
                                                                break;
                                                            }
                                                            case 3: //ELIMINAR CARRITO COMPLETO
                                                            {
                                                                String m = "Eliminando carrito ...";
                                                                for (int i = 0; i < m.length(); i++) {
                                                                    System.out.print(m.charAt(i));
                                                                    try {
                                                                        Thread.sleep(100); // Pausa de 100 milisegundos
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                                try {
                                                                    pokeMarket.eliminarCarritoTotal(actual);
                                                                } catch (CarritoVacioException e) {
                                                                    System.out.println(e.getMensaje());
                                                                }
                                                                break;
                                                            }
                                                            case 4: //MOSTRAR CARRITO
                                                            {
                                                                System.out.println("*** CARRITO DE COMPRAS ***");
                                                                System.out.println(actual.mostrarCarrito());
                                                                break;
                                                            }
                                                            case 5: //CONFIRMAR CARRITO
                                                            {
                                                                try {
                                                                    pokeMarket.confirmarCarrito(actual);
                                                                    menu.mensajeCompraConfirmada();

                                                                } catch (CarritoVacioException h) {
                                                                    System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                                                    System.out.println("****************************************************************");
                                                                    System.out.println(h.getMensaje());
                                                                    System.out.println("****************************************************************");
                                                                } catch (ValorInvalidoException e) {

                                                                    System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                                                    System.out.println("****************************************************************");
                                                                    System.out.println(e.getMessage());
                                                                    System.out.println("****************************************************************");
                                                                }
                                                                try {
                                                                    Thread.sleep(5000);
                                                                }catch (Exception e)
                                                                {
                                                                    System.out.printf("");
                                                                }

                                                                break;
                                                            }
                                                        }
                                                    } while (opcionUsuario3 != 5);

                                                    break;
                                                }
                                                case 2: //INTERCAMBIAR
                                                {
                                                    System.out.println("USUARIO ANTES DEL INTERCAMBIO" + actual.toString());
                                                    //si o si los productos tienene que estar publicados en ambos usuarios
                                                    System.out.println("Ingrese el id del item que desea");
                                                    teclado.nextLine();
                                                    String idEntrada = teclado.nextLine();

                                                    System.out.println("Ingrese el id del item que ofrecera");
                                                    System.out.println("Recuerde : Debe estar publicado, no en el inventario ");
                                                    String idSalida = teclado.nextLine();

                                                    //confirmamos que el id de salida se encuentre en los items publicados del usuario
                                                    try {
                                                        Item entrado = pokeMarket.buscarItemPublicadoXid(idEntrada);
                                                        System.out.println("ITEM QUE SE QUIERE ADQUIRIR" + entrado.toString());
                                                        Item salido = actual.buscarEnItemsPublicadosPropios(idSalida);
                                                        System.out.println("ITEM QUE SE VA " + salido.toString());

                                                        String aux = "Intercambiando cartas ...";
                                                        for (int j = 0; j < aux.length(); j++) {
                                                            System.out.print(aux.charAt(j));
                                                            try {
                                                                Thread.sleep(100); // Pausa de 100 milisegundos
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                        Intercambio intercambio = new Intercambio(entrado, salido);

                                                        pokeMarket.intercambiarCartas(intercambio, actual);
                                                        System.out.println("Intercambio realizado con exito! :)");

                                                        System.out.println("USUARIO DESPUES DEL INTERCAMBIO" + actual.toString());


                                                    } catch (ItemNoEncontradoException d) {
                                                        System.out.println(d.getMensaje());
                                                    } catch (DiferenteRarezaException k) {
                                                        System.out.println(k.getMessage());
                                                    }
                                                    break;
                                                }
                                                case 3: //VOLVER AL PERFIL
                                                {
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
                                    case 3: //PUBLICAR ITEM
                                    {
                                        System.out.printf("Ingrese ID del Item: ");
                                        teclado.nextLine();
                                        id = teclado.nextLine();
                                        Item item = actual.buscarEnInventario(id);
                                        if (item != null) {
                                            actual.publicarItem(item);
                                            actual.eliminarItemDelInventario(item);
                                        }
                                        break;
                                    }
                                    case 4: //DESPUBLICAR ITEM
                                    {
                                        System.out.printf("Ingrese ID del Item: ");
                                        teclado.nextLine();
                                        id = teclado.nextLine();
                                        try {
                                            Item item = actual.buscarEnItemsPublicadosPropios(id);
                                            if (item != null) {
                                                actual.eliminarItemDePublicados(item);
                                                actual.agregarItemAlCarrito(item);
                                            }
                                        }catch (ItemNoEncontradoException e)
                                        {
                                            System.out.printf(e.getMessage());
                                        }
                                        break;
                                    }
                                    case 5: //etapa x si se quiere agrandar el proyecto.
                                    {
                                        System.out.println("Proximamente .... ");
                                        break;
                                    }
                                    case 6: //SALIR
                                    {
                                        break;
                                    }
                                    default:
                                    {
                                        opcionUsuario1 = 6;
                                        break;
                                    }
                                }
                            } while (opcionUsuario1 != 6);
                        } catch (UsuarioContraseniaInvalidoException e) {
                            System.out.println("----------------------------------------");
                            System.out.println("««  " + e.getMessage() + "  »»");
                            System.out.println("----------------------------------------");
                        }
                    }
                    break;
                }
                case 3: //CERRAR APLICACION
                {
                    continuar = 'n';
                    break;
                }
                default:
                {
                    System.out.println("Opción inválida");
                    break;
                }
            }
        } while (continuar == 's');
        pokeMarket.guardarCambios(); //se graban en el archivo Usuarios todos los cambios realizados
        teclado.close();
    }

    public void crearArchivoConUsuarios(PokeMarket pokeMarket) {
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



