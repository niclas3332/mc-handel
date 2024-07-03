package xyz.niclas.trades.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.niclas.trades.database.Trades;

import java.util.List;

public class CommandShowTrades implements CommandExecutor {


    /**
     * Executes the "onCommand" method when a command is issued.
     *
     * @param commandSender the command sender
     * @param command       the command being executed
     * @param s             the alias used for the command
     * @param strings       the arguments provided with the command
     * @return true if the command was executed successfully, false otherwise
     */
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