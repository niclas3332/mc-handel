package xyz.niclas.trades.listener;

import xyz.niclas.trades.database.Trades;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(
                Component.text("Player " + player.getName() + " ist beigetreten.")
        );
        player.sendPlayerListHeader(Trades.generatePlayerHeader());

    }
}
