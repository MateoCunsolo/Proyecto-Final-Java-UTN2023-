import Excepciones.UsuarioContraseniaInvalidoException;
import claseEnvoltorio.PokeMarket;
import clasesItem.*;
import clasesPersonas.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // ******************* INICIO DEL POKE-MARKET *******************
        Scanner teclado = new Scanner(System.in);
        PokeMarket pokeMarket = new PokeMarket();
        pokeMarket.leerUsuariosArchivo(); //ARCHIVO USUARIO TO mapaUSUARIOS
        System.out.printf(pokeMarket.mostrarMapaUsuarios());

        try {
            Usuario actual = pokeMarket.iniciarSesion("Aeriela", "guks5383");
            int opcion = 0;
            while (opcion != 4) {
                System.out.println("1- VER PERFIL");
                System.out.println("2- VER MARKET");
                System.out.println("3- PUBLICAR ITEM");
                System.out.println("4- CERRAR SESION");
                System.out.printf("Ingrese opcion: ");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        //giuli
                        System.out.printf("HOLa");
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

        /*
        // ********* CONSTRUCCION DE CLASE ITEM x 250 TO ArrayList<Item> cartasApi **********

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

        //-----------------------------------------------PASAJE DE EL ARCHIVO JSON (MOCK DATA) A ARCHIVO JAVA DE USUARIOS-------------------------
        try
        {
            String archivoJsonUsu = JsonUtiles.leer("MOCK_DATA (11)");
            JSONArray jsonArray = new JSONArray(archivoJsonUsu);

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario aux = new Usuario(jsonObject.getString("first_name"),jsonObject.getString("password"),jsonObject.getString("email"));

                boolean rta = pokeMarket.agregarUsuario(aux);
                if(rta)
                {
                    System.out.printf("\n Usuario creado con exito" + aux.getNombre());
                }
                else
                {
                    System.out.printf("No fue posible crear el usuario :(");
                }
            }
            pokeMarket.guardarUsuariosArchivo();
            //pokeMarket.leerUsuariosArchivo();

        }catch (JSONException ex)
        {
            System.out.println("JSON mal formado");
        }


        //--------------------------------REPARTIENDO CARTAS------------------------------------
        pokeMarket.repartirCartas(cartasDeApi);
        System.out.printf(pokeMarket.mostrarMapaUsuarios());

        */

    }
}


