package com.crimsonwarpedcraft.exampleplugin;

import com.crimsonwarpedcraft.exampleplugin.commands.CommandDeleteTrade;
import com.crimsonwarpedcraft.exampleplugin.commands.CommandKit;
import com.crimsonwarpedcraft.exampleplugin.commands.CommandShowTrades;
import com.crimsonwarpedcraft.exampleplugin.commands.CommandTrade;
import com.crimsonwarpedcraft.exampleplugin.listener.JoinListener;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;



public class ExamplePlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    saveDefaultConfig();

    Objects.requireNonNull(this.getCommand("kit")).setExecutor(new CommandKit());
    Objects.requireNonNull(this.getCommand("trade")).setExecutor(new CommandTrade());
    Objects.requireNonNull(this.getCommand("showtrades")).setExecutor(new CommandShowTrades());
    Objects.requireNonNull(this.getCommand("deltrade")).setExecutor(new CommandDeleteTrade());

    Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

  }





}
