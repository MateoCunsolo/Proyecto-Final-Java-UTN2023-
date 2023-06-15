import claseEnvoltorio.PokeMarket;
import clasesItem.*;
import clasesPersonas.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args)
    {
        PokeMarket pokeMarket = new PokeMarket();

        //PASAJE DE API CARTAS POKEMON A ARCHIVO
        ArrayList<Item> cartasDeApi = new ArrayList<>();
        try {

            String archivoJsonCar = JsonUtiles.leer("cartas");

            JSONObject datos = new JSONObject(archivoJsonCar);
            JSONArray arregloCartas = datos.getJSONArray("data");

            for (int i = 0; i < arregloCartas.length(); i++) {
                JSONObject cartaJson = arregloCartas.getJSONObject(i); //ok


                Pokemon poke = new Pokemon();
                poke.setNombre(cartaJson.getString("name"));
                // System.out.println(poke.getNombre()); //ok

                String auxPS = cartaJson.getString("hp"); //ok
                int numeroPS = Integer.parseInt(auxPS);
                poke.setPs(numeroPS); //ok
                //System.out.println(poke.getPs());

                try {

                    poke.setEvolucionaDe(cartaJson.getString("evolvesFrom"));  //EVOLUCIONA DE
                    //System.out.println("EVOLUCIONA DE "+ poke.getEvolucionaDe());

                    JSONArray arregloDeEvolucionaA = cartaJson.getJSONArray("evolvesTo");
                    poke.setEvolucionA(arregloDeEvolucionaA.getString(0));
                    // System.out.println("EVOLUCIONA a "+ poke.getEvolucionA());

                } catch (JSONException jaja) {
                    System.out.println(" ");
                }


                //tipo energia OK!!!!
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

                //System.out.println("ENERGIA DE POKE" +poke.getTipo());


                //debilidad
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

                System.out.println("DEBILIDADES " + poke.getDebilidad());
/*
                //ataques OK!!!
                JSONArray arregloAtaques = cartaJson.getJSONArray("attacks");
                for (int j = 0; j < arregloAtaques.length(); j++) {
                    JSONObject objetoAtaque = arregloAtaques.getJSONObject(j);
                    Ataque ataquePokemon = new Ataque();
                    ataquePokemon.setNombreAtaque(objetoAtaque.getString("name"));
                    ataquePokemon.setDanio(objetoAtaque.getString("damage"));
                    ataquePokemon.setDescripcion(objetoAtaque.getString("text"));
                    poke.agregarAtaque(ataquePokemon);

                }


                //CARTA
                Carta card = new Carta();
                card.setPokemon(poke);

                card.setArtista(cartaJson.getString("artist"));
                card.setRareza(cartaJson.getString("rarity"));

                String numeroDeCarta = cartaJson.getString("number");
                int numero = Integer.parseInt(numeroDeCarta);
                card.setNumero(numero);
                System.out.println("\n CARTA:" + card.toString());

                /*
                //ITEM
                Item item = card;


                item.setId(cartaJson.getString("id"));

                item.setDescrip(cartaJson.getString("flavorText"));

                if(card.getRareza().equals("Rare Holo")) //5
                {
                    item.setPrecio(1620);

                }else if(card.getRareza().equals("Rare Holo GX")) //2
                {
                    item.setPrecio(3500);

                } else if(card.getRareza().equals("Common")) //8
                {
                    item.setPrecio(752);

                }else if(card.getRareza().equals("Uncommon")) //7
                {
                    item.setPrecio(1300);

                }else if(card.getRareza().equals("Rare")) //6
                {
                    item.setPrecio(1543);

                }else if(card.getRareza().equals("Rare Holo V")) //4
                {
                    item.setPrecio(2600);

                }else if(card.getRareza().equals("Promo")) //9
                {
                    item.setPrecio(513);

                }else if(card.getRareza().equals("Rare Holo EX")) //3
                {
                    item.setPrecio(3000);

                }else // Rare Ultra +cara 1
                {
                    item.setPrecio(5000);
                }

                System.out.println(item.toString() + "\n");
                //cartasDeApi.add(item);


            }

        */

            }

        }catch (JSONException jaj)
        {

        }


        // PASAJE DE EL ARCHIVO JSON (MOCK DATA) A ARCHIVO JAVA DE USUARIOS
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