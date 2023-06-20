import Archivos.ControladoraArchivos;
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
       crearArchivoConUsuarios(pokeMarket);
       cargaArchivoConCartas(pokeMarket);

        pokeMarket.leerUsuariosArchivo(); //pasamos usuarios al treeMap de la clase Evoltorio

        System.out.println("\n\nUSUARIOS EN TREEMAP PASADOS DE ARCHIVO \n");
        System.out.println(pokeMarket.mostrarMapaUsuarios());
        //pokeMarket.mostrarusu();

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
                                if(rta)
                                {
                                    System.out.println("Usuario eliminado exitosamente");
                                }else
                                {
                                    System.out.println("No se pudo eliminar el usuario solicitado");
                                }

                            }catch (UsuarioNoEncontradoException e)
                            {
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
                            if(rta) //si se encuentra el nombre ingresado
                            {
                                Usuario usu = pokeMarket.getMapaUsuarios().get(nom);

                                String m = "Buscando datos del usuario" +nom + "...";
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

                                switch (o)
                                {
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
                                    default:
                                    {
                                        System.out.println("Opción inválida");
                                        break;
                                    }
                                }


                            }else //si no se encuentra el nombre ingresado
                            {
                                System.out.println("El nombre ingresado no existe en el sistema, intente con otro nombre");
                            }

                            break;

                        default:
                            System.out.println("Opción inválida");
                            break;
                    }

                }

                else //verificar si se accede a las FUNCIONES del USUARIO
                {

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

    } while(continuar == 's');

        teclado.close();

    }

    public static void menuPrincipal()
    {
        System.out.println(" _________________________________________");
        System.out.println("|                 <<MENU>>                |");
        System.out.println("| 1. REGISTRARSE                          |");
        System.out.println("| 2. INICIAR SESION                       |");
        System.out.println("|_________________________________________|");
        System.out.println("\nIngrese el numero de la opcion que desea abrir: ");
    }

    public static void menuAdministrador()
    {
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

    public static void menuAdminMovimientosUsuario()
    {
        System.out.println(" _________________________________________");
        System.out.println("|     <<VER MOVIMIENTOS DE UN USUARIO>>   |");
        System.out.println("| 1. VER VENTAS                           |");
        System.out.println("| 2. VER COMPRAS                          |");
        System.out.println("| 3. VER INTERCAMBIOS                     |");
        System.out.println("|_________________________________________|");
        System.out.println("\nIngrese el numero de la opcion que desea abrir: ");

    }

    public static void crearArchivoConUsuarios(PokeMarket pokeMarket)
    {
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
    }

    public static void cargaArchivoConCartas (PokeMarket pokeMarket)
    {
         /*
            PASAJE DE API-CARTAS POKEMON A UN ARRAY LIST DE CARTAS, PARA QUE LUEGO SE LAS
            ASIGNEMOS A CADA USUARIO Y DE AHI A GUARADAR EL MAP DE USUARIOS EN UN ARCHIVO*/

        int contador = 0;
        ArrayList<Item> cartasDeApi = new ArrayList<>(); //aca van a estar nuestras cartas

        Item item = new Item();

        try {

            String archivoJsonCar = JsonUtiles.leer("cartas");
            JSONObject datos = new JSONObject(archivoJsonCar);
            JSONArray arregloCartas = datos.getJSONArray("data");

            for (int i = 0; i < arregloCartas.length(); i++) {

                //--------------CREAR NUESTRAS CLASES CON LOS DATOS DE LA API----------------


                // ( 1 ) obtenemos la carta
                JSONObject cartaJson = arregloCartas.getJSONObject(i);

                // ( 2 ) creamos el pokemon con la info de "cartaJson"
                Pokemon poke = new Pokemon();

                // ( 2.1 ) Nombre
                poke.setNombre(cartaJson.getString("name"));

                // ( 2.2 ) Puntos de salud
                String auxPS = cartaJson.getString("hp");
                int numeroPS = Integer.parseInt(auxPS);
                poke.setPs(numeroPS);

                // ( 2.3 ) Evoluciones from and to
                try {
                    poke.setEvolucionaDe(cartaJson.getString("evolvesFrom"));
                    JSONArray arregloDeEvolucionaA = cartaJson.getJSONArray("evolvesTo");
                    poke.setEvolucionA(arregloDeEvolucionaA.getString(0));
                } catch (JSONException e) {
                    System.out.println("");
                }

                // ( 2.4 ) Tipo de energia
                JSONArray arregloType = cartaJson.getJSONArray("types");
                String tipoEnergia = arregloType.getString(0);
                if (tipoEnergia.equals("Lightning")) {
                    poke.setTipo(Energia.LIGHTNING);
                } else if (tipoEnergia.equals("Colorless")) {
                    poke.setTipo(Energia.COLORLESS);
                } else if (tipoEnergia.equals("Darkness")) {
                    poke.setTipo(Energia.DARKNESS);
                } else if (tipoEnergia.equals("Dragon")) {
                    poke.setTipo(Energia.DRAGON);
                } else if (tipoEnergia.equals("Fairy")) {
                    poke.setTipo(Energia.FAIRY);
                } else if (tipoEnergia.equals("Fighting")) {
                    poke.setTipo(Energia.FIGHTING);
                } else if (tipoEnergia.equals("Fire")) {
                    poke.setTipo(Energia.FIRE);
                } else if (tipoEnergia.equals("Grass")) {
                    poke.setTipo(Energia.GRASS);
                } else if (tipoEnergia.equals("Metal")) {
                    poke.setTipo(Energia.METAL);
                } else if (tipoEnergia.equals("Psychic")) {
                    poke.setTipo(Energia.PSYCHIC);
                } else {
                    poke.setTipo(Energia.WATER);
                }

                // ( 2.5 ) Debilidades
                try {
                    JSONArray debilidades = cartaJson.getJSONArray("weaknesses");
                    JSONObject nombreDebilidad = debilidades.getJSONObject(0);
                    String tipoDebilidad = nombreDebilidad.getString("type");
                    if (tipoDebilidad.equals("Lightning")) {
                        poke.setDebilidad(Energia.LIGHTNING);
                    } else if (tipoDebilidad.equals("Colorless")) {
                        poke.setDebilidad(Energia.COLORLESS);
                    } else if (tipoDebilidad.equals("Darkness")) {
                        poke.setDebilidad(Energia.DARKNESS);
                    } else if (tipoDebilidad.equals("Dragon")) {
                        poke.setDebilidad(Energia.DRAGON);
                    } else if (tipoDebilidad.equals("Fairy")) {
                        poke.setDebilidad(Energia.FAIRY);
                    } else if (tipoDebilidad.equals("Fighting")) {
                        poke.setDebilidad(Energia.FIGHTING);
                    } else if (tipoDebilidad.equals("Fire")) {
                        poke.setDebilidad(Energia.FIRE);
                    } else if (tipoDebilidad.equals("Grass")) {
                        poke.setDebilidad(Energia.GRASS);
                    } else if (tipoDebilidad.equals("Metal")) {
                        poke.setDebilidad(Energia.METAL);
                    } else if (tipoDebilidad.equals("Psychic")) {
                        poke.setDebilidad(Energia.PSYCHIC);
                    } else {
                        poke.setDebilidad(Energia.WATER);
                    }
                } catch (JSONException e) {
                    System.out.printf("");
                }

                // ( 2.6 ) Ataques
                try {
                    JSONArray arregloAtaques = cartaJson.getJSONArray("attacks");
                    for (int j = 0; j < arregloAtaques.length(); j++) {
                        JSONObject objetoAtaque = arregloAtaques.getJSONObject(j);
                        Ataque ataquePokemon = new Ataque();
                        ataquePokemon.setNombreAtaque(objetoAtaque.getString("name"));
                        ataquePokemon.setDanio(objetoAtaque.getString("damage"));
                        ataquePokemon.setDescripcion(objetoAtaque.getString("text"));
                        poke.agregarAtaque(ataquePokemon);
                    }
                } catch (JSONException e1) {
                    System.out.printf("");
                }


                // ( 3 ) Creacion de la Carta
                try {
                    Carta card = new Carta();

                    // ( 3.1 ) Pokemon
                    card.setPokemon(poke);

                    // ( 3.2 ) Rareza
                    card.setRareza(cartaJson.getString("rarity"));

                    // ( 3.3 ) Artista
                    card.setArtista(cartaJson.getString("artist"));

                    // ( 3.4 ) Numero de carta
                    card.setNumero(cartaJson.getString("number"));

                    // ( 4 ) Creacion del item
                    item = card;

                    item.setId(cartaJson.getString("id"));

                    try{
                        item.setDescrip(cartaJson.getString("flavorText"));

                    }catch(JSONException a)
                    {
                        System.out.printf("");
                    }

                    if (card.getRareza().equals("Rare Holo")) //5
                    {
                        item.setPrecio(1620);

                    } else if (card.getRareza().equals("Rare Holo GX")) //2
                    {
                        item.setPrecio(3500);

                    } else if (card.getRareza().equals("Common")) //8
                    {
                        item.setPrecio(752);

                    } else if (card.getRareza().equals("Uncommon")) //7
                    {
                        item.setPrecio(1300);

                    } else if (card.getRareza().equals("Rare")) //6
                    {
                        item.setPrecio(1543);

                    } else if (card.getRareza().equals("Rare Holo V")) //4
                    {
                        item.setPrecio(2600);

                    } else if (card.getRareza().equals("Promo")) //9
                    {
                        item.setPrecio(513);

                    } else if (card.getRareza().equals("Rare Holo EX")) //3
                    {
                        item.setPrecio(3000);

                    } else // Rare Ultra +cara 1
                    {
                        item.setPrecio(5000);
                    }

                } catch (JSONException e3) {
                    System.out.printf("");
                }

                cartasDeApi.add(item);
                contador = contador + 1;
            }
        } catch (JSONException e) {
            System.out.printf("ERROR FATAL UNA CREACION DE LA CLASE QUE NO ESTA CAPTURADA CON SU PROPIO TRY CATCH HA LANZADO UNA EXCEPCION");
        }

        //System.out.println(cartasDeApi.toString());
        System.out.printf("\n\n\n\n********************************");
        System.out.printf("\n\tITEMS CARGADOS = " + contador);
        System.out.printf("\n********************************");

        //--------------------------------REPARTIENDO CARTAS------------------------------------
         pokeMarket.repartirCartas(cartasDeApi);

    }
}

