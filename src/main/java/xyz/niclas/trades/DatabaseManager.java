package xyz.niclas.trades;

import xyz.niclas.trades.database.Trades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {

    public static Connection connection;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:sqlite:plugins/TestPlugin/database.db");
        generateDatabase();
        Trades.setTradeList(getTrades());

    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS trades (id INTEGER PRIMARY KEY AUTOINCREMENT, player TEXT, item TEXT, tradeId TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addTrade(Trades.Trade trade) throws SQLException {
        // Add trade to database

        PreparedStatement statement = connection.prepareStatement("INSERT INTO trades (playerUuid, tradeName, tradeId) VALUES (?, ?, ?)");
        statement.setString(1, trade.player.toString());
        statement.setString(2, trade.tradeName);
        statement.setString(3, trade.tradeId);
        statement.executeUpdate();
    }

    public static void delTrade(String tradeId) throws SQLException {
        // Delete Trade from DB

        PreparedStatement statement = connection.prepareStatement("DELETE FROM trades WHERE tradeId = ?");
        statement.setString(1, tradeId);
        statement.executeUpdate();

    }

    public static List<Trades.Trade> getTrades() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM trades");
            List<Trades.Trade> trades = new ArrayList<>();
            while (resultSet.next()) {
                UUID player = UUID.fromString(resultSet.getString("playerUuid"));
                String tradeId = resultSet.getString("tradeId");
                String tradeName = resultSet.getString("tradeName");
                Trades.Trade trade = new Trades.Trade(player, tradeId, tradeName);
                trades.add(trade);
            }
            return trades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}