package ch.nb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeApiFetcher {

    public static String fetchPokemonData(int id) throws IOException {
        String urlString = "https://pokeapi.co/api/v2/pokemon/" + id;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder json = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            json.append(inputLine);
        }
        in.close();
        return json.toString();
    }
}