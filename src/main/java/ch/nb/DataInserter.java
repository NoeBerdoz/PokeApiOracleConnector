package ch.nb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataInserter {

    public static void insertPokemonData(int id) {
        try {
            String jsonData = PokeApiFetcher.fetchPokemonData(id);
            JSONObject pokemon = new JSONObject(jsonData);

            String name = pokemon.getString("name");

            JSONObject stats = new JSONObject();
            JSONArray statsArray = pokemon.getJSONArray("stats");
            for (int i = 0; i < statsArray.length(); i++) {
                JSONObject stat = statsArray.getJSONObject(i);
                String statName = stat.getJSONObject("stat").getString("name");
                int baseStat = stat.getInt("base_stat");

                stats.put(statName, baseStat);
            }

            byte[] image = downloadImage(pokemon.getJSONObject("sprites").getString("front_default"));

            insertIntoDatabase(id, name, stats, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] downloadImage(String urlString) {
        try (InputStream in = new URL(urlString).openStream()) {
            return in.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void insertIntoDatabase(int id, String name, JSONObject stats, byte[] image) throws SQLException {
        try (Connection connection = OracleConnector.getConnection()) {
            // Insert stats
            String insertStatsSQL = "INSERT INTO STATS (HP, ATTACK, DEFENSE, SPECIAL_ATTACK, SPECIAL_DEFENSE, SPEED) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statsStmt = connection.prepareStatement(insertStatsSQL, new String[]{"ID"});
            statsStmt.setInt(1, stats.getInt("hp"));
            statsStmt.setInt(2, stats.getInt("attack"));
            statsStmt.setInt(3, stats.getInt("defense"));
            statsStmt.setInt(4, stats.getInt("special-attack"));
            statsStmt.setInt(5, stats.getInt("special-defense"));
            statsStmt.setInt(6, stats.getInt("speed"));
            statsStmt.executeUpdate();

            System.out.println("[+] Inserted Stats");

            // Retrieve the generated stats ID
            ResultSet generatedKeys = statsStmt.getGeneratedKeys();
            int statsId = 0;
            if (generatedKeys.next()) {
                statsId = generatedKeys.getInt(1);
            }

            // Insert Pokemon
            String insertPokemonSQL = "INSERT INTO POKEMONS (NAME, STATS_ID, IMAGE) VALUES (?, ?, ?)";
            PreparedStatement pokemonStmt = connection.prepareStatement(insertPokemonSQL);
            pokemonStmt.setString(1, name);
            pokemonStmt.setInt(2, statsId);
            pokemonStmt.setBytes(3, image);
            pokemonStmt.executeUpdate();

            System.out.println("[+] Inserted Pokémon: " + name);
        }
    }
}
