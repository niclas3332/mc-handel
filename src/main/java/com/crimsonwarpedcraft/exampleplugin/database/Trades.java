package com.crimsonwarpedcraft.exampleplugin.database;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Trades {

      public static class Trade {

        public UUID player;
        public String tradeId;
        public String tradeName;


        public Trade(UUID player, String tradeId, String tradeName) {
            this.player = player;
            this.tradeId = tradeId;
            this.tradeName = tradeName;
        }

        public void delete(){
            tradeList.remove(this);
        }

    }

    public static final List<Trade> tradeList = new ArrayList<>();

    public static void addTrade(Trade trade){
        tradeList.add(trade);
    }

    public static void delTrade(Trade trade){
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

    public static Trade getTrade(String tradeId){
        for (Trade trade : tradeList) {
            if (trade.tradeId.equals(tradeId)) {
                return trade;
            }
        }
        return null;

    }

    public static void updateTrades(){
        // Set Header to all players

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendPlayerListHeader(Component.text("Das ist ein Test"));
        }
    }

    public static Component generatePlayerHeader(){
        return Component.text("Das ist ein Test");
    }




}


