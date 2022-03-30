package com.lennertsoffers.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

// Listener is an interface just used for tagging classes

// ---- Not explained in the course but to give you a better idea of how the Listener works ----
// Every class tagged Listener is read by the pluginHandler on the server
// Every class that implements Listener should have precisely one method annotated with "@EventHandler"
// Because the Listener interface doesn't have any methods in it, the name of the method doesn't matter
// The pluginHandler will read all classes that implement the listener with reflection and search the EventHandler method
// This method will then be subscribed of the HandlersList of the Spigot event you give as an argument
// If the event occurs, this EventHandler method is called
// ---------------------------------------------------------------------------------------------
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Get the player from the event object
        Player player = event.getPlayer();

        // Send a message to the player
        // This isn't a broadcast as you would normally do in the chat
        // It is a message that only can be seen by the player this method is called upon
        player.sendMessage(ChatColor.YELLOW + "Welcome " + player.getName() + "!");



        // ---- Integrated Exercise ----
        // Give every player that joins the game a new ItemStack
        // The ItemStack must have more than 1 items
        // The ItemStack must have a custom display name
        // The ItemStack must have more than 1 line of lore
        ItemStack blazeRods = new ItemStack(Material.BLAZE_ROD, 20);
        ItemMeta blazeRodsMeta = blazeRods.getItemMeta();

        if (blazeRodsMeta != null) {
            blazeRodsMeta.setDisplayName(ChatColor.RED + "Sticks of fire");

            ArrayList<String> lore = new ArrayList<>();
            lore.add("Line 1");
            lore.add("Line 2");
            lore.add("Line 3");

            blazeRodsMeta.setLore(lore);

            blazeRods.setItemMeta(blazeRodsMeta);
        }

        player.getInventory().addItem(blazeRods);
        // -----------------------------
    }
}
