package xyz.niclas.trades.database;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Trades {

    /**
     * A list of trades.
     */
    public static final List<Trade> tradeList = new ArrayList<>();

    /**
     * Retrieves a list of trades associated with a player.
     *
     * @param player The player whose trades are being retrieved.
     * @return A list of trades associated with the specified player.
     */
    public static List<Trade> getTrades(Player player) {
        return tradeList.stream()
                .filter(trade -> trade.player.equals(player.getUniqueId()))
                .collect(Collectors.toList());
    }

    public static void addTrade(Trade trade) {
        tradeList.add(trade);
    }


    public static void delTrade(Trade trade) {
        tradeList.remove(trade);
    }

    /**
     * Retrieves a trade with the specified trade ID.
     *
     * @param tradeId The ID of the trade to retrieve.
     * @return An Optional containing the trade with the specified ID, or an empty Optional if no trade is found.
     */
    public static Optional<Trade> getTrade(String tradeId) {
        return tradeList.stream()
                .filter(trade -> trade.tradeId.equals(tradeId))
                .findFirst();
    }

    /**
     * Retrieves a trade with the specified trade ID.
     *
     * @param tradeId The ID of the trade to retrieve.
     * @return An Optional containing the trade with the specified ID, or an empty Optional if no trade is found.
     */
    public static Optional<Trade> getTrade(String tradeId, Player player) {
        return tradeList.stream()
                .filter(trade -> trade.tradeId.equals(tradeId) && trade.player == player.getUniqueId())
                .findFirst();
    }


    /**
     * Updates the trades by setting the header to all players in the server.
     */
    public static void updateTrades() {
        // Set Header to all players

        Component header = generatePlayerHeader();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendPlayerListHeader(header);
        }
    }

    public static void setTradeList(List<Trade> trades) {
        // Set the trade list
        tradeList.clear();
        tradeList.addAll(trades);
    }

    /**
     * Generates the player header component to be displayed in the player list.
     *
     * @return The player header component.
     */
    public static Component generatePlayerHeader() {

        Component component = Component.empty();

        for (Trade trade : tradeList) {
            component = component.append(Component.text(trade.getPlayerName() + ": " + trade.tradeName).appendNewline());
        }

        return component;
    }

    public static class Trade {

        public UUID player;
        public String tradeId;
        public String tradeName;
        public LocalDateTime dateTime;


        /**
         * Constructs a new Trade object.
         *
         * @param player    The UUID of the player involved in the trade.
         * @param tradeId   The ID of the trade.
         * @param tradeName The name of the trade.
         */
        public Trade(UUID player, String tradeId, String tradeName) {
            this.player = player;
            this.tradeId = tradeId;
            this.tradeName = tradeName;
            this.dateTime = LocalDateTime.now();
        }

        public void delete() {
            tradeList.remove(this);
        }

        public String getPlayerName() {
            Player p = Bukkit.getPlayer(player);
            if (p == null)
                return "PlayerNotFound";
            return p.getName();
        }

    }


}


