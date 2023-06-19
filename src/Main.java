import claseEnvoltorio.PokeMarket;
import clasesItem.*;
import clasesPersonas.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        /*
            PASAJE DE API-CARTAS POKEMON A UN ARRAY LIST DE CARTAS, PARA QUE LUEGO SE LAS
            ASIGNEMOS A CADA USUARIO Y DE AHI A GUARADAR EL MAP DE USUARIOS EN UN ARCHIVO
        */

        int contador = 0;
        PokeMarket pokeMarket = new PokeMarket();
        ArrayList<Item> cartasDeApi = new ArrayList<>();

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
                    Item item = card;

                    item.setId(cartaJson.getString("id"));

                    item.setDescrip(cartaJson.getString("flavorText"));

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

                    cartasDeApi.add(item);

                } catch (JSONException e3) {
                    System.out.printf("");
                }

                contador = contador + 1;
            }
        } catch (JSONException e) {
            System.out.printf("ERROR FATAL UNA CREACION DE LA CLASE QUE NO ESTA CAPTURADA CON SU PROPIO TRY CATCH HA LANZADO UNA EXCEPCION");
        }

        System.out.printf("\n\n\n\n********************************");
        System.out.printf("\n\tITEMS CARGADOS = " + contador);
        System.out.printf("\n********************************");

        pokeMarket.leerUsuariosArchivo();

        System.out.printf(pokeMarket.mostrarMapa());




        //-----------------------------------------------PASAJE DE EL ARCHIVO JSON (MOCK DATA) A ARCHIVO JAVA DE USUARIOS-------------------------
        /*try
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
            pokeMarket.leerUsuariosArchivo();


        }catch (JSONException ex)
        {
            System.out.println("JSON mal formado");
        }*/


    }
}
