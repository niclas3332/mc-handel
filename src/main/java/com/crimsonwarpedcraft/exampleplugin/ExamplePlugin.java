package com.crimsonwarpedcraft.exampleplugin;

import com.crimsonwarpedcraft.exampleplugin.commands.CommandKit;
import com.crimsonwarpedcraft.exampleplugin.commands.CommandTrade;
import com.crimsonwarpedcraft.exampleplugin.listener.JoinListener;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


/**
 * Created by Levi Muniz on 7/29/20.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ExamplePlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();

    Objects.requireNonNull(this.getCommand("kit")).setExecutor(new CommandKit());
    Objects.requireNonNull(this.getCommand("trade")).setExecutor(new CommandTrade());

    Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

  }





}
