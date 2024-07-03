package xyz.niclas.trades.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.niclas.trades.database.Trades;

public class CommandDeleteTrade implements CommandExecutor {


    /**
     * Executes the command triggered by the command sender.
     *
     * @param commandSender The command sender.
     * @param command       The command that was executed.
     * @param s             The label of the command.
     * @param strings       The arguments passed with the command.
     * @return true if the command was executed successfully, otherwise false.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {


            String tradeId = strings[0];

            Trades.Trade playerTrade = Trades.getTrade(tradeId);
            if (playerTrade != null) {

                if (playerTrade.player != player.getUniqueId() && !player.isOp()) {
                    player.sendMessage(Component.text("Du kannst nur deine eigenen Trades löschen."));
                    return true;
                }
                player.sendMessage(Component.text("Trade wurde gelöscht."));
                playerTrade.delete();
                Trades.updateTrades();
                return true;
            }
            player.sendMessage(Component.text("Trade nicht gefunden."));


        }

        return true;
    }
}