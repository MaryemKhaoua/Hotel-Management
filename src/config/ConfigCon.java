package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigCon {
    private static ConfigCon instance;
    private Connection connection;

    // Constructeur privé pour Singleton
    private ConfigCon() throws SQLException {
        String DATABASE_URL = System.getenv("DATABASE_URL");
        String USER = System.getenv("USER");
        String PASSWORD = System.getenv("PASSWORD");

        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL JDBC non trouvé : " + e.getMessage());
            throw new SQLException(e);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            throw e;
        }
    }

    // Méthode pour obtenir l'instance Singleton
    public static ConfigCon getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConfigCon();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConfigCon();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion
    public Connection getConnection() {

        return connection;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() {
        if (instance != null && instance.getConnection() != null) {
            try {
                instance.getConnection().close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
