package com.crimsonwarpedcraft.exampleplugin.commands;

import com.crimsonwarpedcraft.exampleplugin.database.Trades;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandTrade implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {

            String tradeId = strings[0];
            String tradeDesc = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));

            Trades.Trade trade = Trades.getTrade(tradeId);

            if (trade != null && trade.player == player.getUniqueId()) {
                player.sendMessage(Component.text("Du hast bereits einen Trade mit dieser ID."));
                return true;
            }
            Trades.Trade newTrade = new Trades.Trade(player.getUniqueId(), tradeId, tradeDesc);
            Trades.addTrade(newTrade);
            Trades.updateTrades();
            player.sendMessage(Component.text("Dein Trade wurde hinzugefügt."));

        }


        return true;
    }
}