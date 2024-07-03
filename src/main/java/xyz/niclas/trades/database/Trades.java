package xyz.niclas.trades.database;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Trades {

    public static class Trade {

        public UUID player;
        public String tradeId;
        public String tradeName;
        public LocalDateTime dateTime;


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

    public static final List<Trade> tradeList = new ArrayList<>();

    public static void addTrade(Trade trade) {
        tradeList.add(trade);
    }

    public static void delTrade(Trade trade) {
        tradeList.remove(trade);
    }

    public static List<Trade> getTrades(Player player) {
        List<Trade> playerTrades = new ArrayList<>();
        for (Trade trade : tradeList) {
            if (trade.player.equals(player.getUniqueId())) {
                playerTrades.add(trade);
            }
        }
        return playerTrades;
    }

    public static Trade getTrade(String tradeId) {
        for (Trade trade : tradeList) {
            if (trade.tradeId.equals(tradeId)) {
                return trade;
            }
        }
        return null;

    }

    public static void updateTrades() {
        // Set Header to all players

        Component header = generatePlayerHeader();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendPlayerListHeader(header);
        }
    }

    public static Component generatePlayerHeader() {

        Component component = Component.empty();

        for (Trade trade : tradeList) {
            component = component.append(Component.text(trade.getPlayerName() + ": " + trade.tradeName).appendNewline());
        }

        return component;
    }


}


