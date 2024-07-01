package ch.nb;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- POKE API CONNECTOR --");

        try (Connection connection = OracleConnector.getConnection()) {
            if (connection != null) {
                System.out.println("[+] Connection established!");
            } else {
                System.out.println("[-] Connection failed!");
            }
        } catch (SQLException e) {
            System.err.println("[-] An error occurred: " + e.getMessage());
        }

        for (int id = 1; id <= 151; id++) {
            DataInserter.insertPokemonData(id);
        }
    }
}