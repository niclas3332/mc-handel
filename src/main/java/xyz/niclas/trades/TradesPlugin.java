package xyz.niclas.trades;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.niclas.trades.commands.CommandDeleteTrade;
import xyz.niclas.trades.commands.CommandShowTrades;
import xyz.niclas.trades.commands.CommandTrade;
import xyz.niclas.trades.listener.JoinListener;

import java.util.Objects;


public class TradesPlugin extends JavaPlugin {

    /**
     * This method is called when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        saveDefaultConfig();

        Objects.requireNonNull(this.getCommand("trade")).setExecutor(new CommandTrade());
        Objects.requireNonNull(this.getCommand("showtrades")).setExecutor(new CommandShowTrades());
        Objects.requireNonNull(this.getCommand("deltrade")).setExecutor(new CommandDeleteTrade());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

    }


}
