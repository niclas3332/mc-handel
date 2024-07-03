package xyz.niclas.trades.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.niclas.trades.database.Trades;

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

            if (trade.isPresent() && trade.get().player == player.getUniqueId()) {
                player.sendMessage(Component.text("Du hast bereits einen Trade mit dieser ID."));
                return true;
            }


            Trades.addTrade(new Trades.Trade(player.getUniqueId(), tradeId, tradeDesc));
            Trades.updateTrades();
            player.sendMessage(Component.text("Dein Trade wurde hinzugefügt."));

        }


        return true;
    }
}