package com.crimsonwarpedcraft.exampleplugin.commands;

import com.crimsonwarpedcraft.exampleplugin.ExamplePlugin;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CommandKit implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player) {
            // Here we need to give items to our player


            ItemStack diamond = new ItemStack(Material.DIAMOND);
            diamond.setAmount(40);
            player.getInventory().addItem(diamond);
        }

        return true;
    }
}