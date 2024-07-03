package xyz.niclas.trades.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.niclas.trades.DatabaseManager;
import xyz.niclas.trades.database.Trades;

import java.sql.SQLException;

/**
 * Represents a command for deleting a trade.
 */
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

            Trades.getTrade(tradeId)
                    .filter(playerTrade -> playerTrade.player.equals(player.getUniqueId()) || player.isOp())
                    .ifPresentOrElse(
                            playerTrade -> {
                                try {
                                    DatabaseManager.delTrade(tradeId);
                                } catch (SQLException e) {
                                    player.sendMessage(Component.text("Trade konnte nicht gel\u00F6scht werden. Bitte später erneut versuchen."));
                                }

                                player.sendMessage(Component.text("Trade wurde gel\u00F6scht."));
                                playerTrade.delete();
                                Trades.updateTrades();

                            },
                            () -> player.sendMessage(Component.text("Trade nicht gefunden."))
                    );

        }

        return true;
    }
}