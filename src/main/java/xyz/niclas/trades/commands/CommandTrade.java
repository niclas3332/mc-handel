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
import java.util.Arrays;
import java.util.Optional;

/**
 * Represents a command for creating a new trade.
 */
public class CommandTrade implements CommandExecutor {


    /**
     * Executes the command when triggered by a command sender.
     *
     * @param commandSender The command sender who executed the command.
     * @param command       The command that was executed.
     * @param s             The command label.
     * @param strings       The command arguments.
     * @return true if the command was executed successfully, otherwise false.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {

            String tradeId = strings[0];
            String tradeDesc = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));

            Optional<Trades.Trade> trade = Trades.getTrade(tradeId);

            if (trade.isPresent()) {
                if (trade.get().player == player.getUniqueId())
                    player.sendMessage(Component.text("Du hast bereits einen Trade mit dieser ID, bitte l\u00F6sche diesen zuerst."));
                else
                    player.sendMessage(Component.text("Diese Trade-ID ist bereits in Benutzung."));
                return true;
            }
            Trades.Trade newTrade = new Trades.Trade(player.getUniqueId(), tradeId, tradeDesc);

            try {
                DatabaseManager.addTrade(newTrade);
            } catch (SQLException e) {
                player.sendMessage(Component.text("Dein Trade konnte nicht hinzugef\u00FCgt werden."));
                player.sendMessage(Component.text(e.getMessage()));
                return false;
            }
            Trades.addTrade(newTrade);
            Trades.updateTrades();
            player.sendMessage(Component.text("Dein Trade wurde hinzugef\u00FCgt."));

        }


        return true;
    }
}