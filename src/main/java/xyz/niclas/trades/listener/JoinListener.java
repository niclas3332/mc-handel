package xyz.niclas.trades.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.niclas.trades.database.Trades;

/**
 * JoinListener class that handles the PlayerJoinEvent.
 */
public class JoinListener implements Listener {


    /**
     * Handles the PlayerJoinEvent.
     * When a player joins the server, this method is called.
     * It sends a join message and updates the player list header.
     *
     * @param event The PlayerJoinEvent triggered when a player joins the server.
     */
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendPlayerListHeader(Trades.generatePlayerHeader());

    }
}
