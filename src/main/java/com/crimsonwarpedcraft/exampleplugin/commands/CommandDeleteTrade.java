package com.crimsonwarpedcraft.exampleplugin.commands;

import com.crimsonwarpedcraft.exampleplugin.ExamplePlugin;
import com.crimsonwarpedcraft.exampleplugin.database.Trades;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandDeleteTrade implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {


            String tradeId = strings[0];

            Trades.Trade playerTrade = Trades.getTrade(tradeId);
            if (playerTrade != null) {

                if (playerTrade.player != player.getUniqueId()) {
                    player.sendMessage(Component.text("Du kannst nur deine eigenen Trades löschen."));
                    return true;
                }
                playerTrade.delete();
                return true;
            }
            player.sendMessage(Component.text("Trade nicht gefunden."));

        }

        return true;
    }
}