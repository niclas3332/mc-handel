package xyz.niclas.trades.commands;

import xyz.niclas.trades.database.Trades;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandShowTrades implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {

            List<Trades.Trade> trades = Trades.getTrades(player);
            for (Trades.Trade trade : trades) {
                player.sendMessage(Component.text(trade.tradeId + ": " + trade.tradeName));
            }

        }


        return true;
    }
}