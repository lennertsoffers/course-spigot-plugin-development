package com.lennertsoffers.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// Listener is an interface just used for tagging classes

// ---- Not explained in the course but to give you a better idea of how the Listener works ----
// Every class tagged Listener is read by the pluginHandler on the server
// Every class that implements Listener should have precisely one method annotated with "@EventHandler"
// Because the Listener interface doesn't have any methods in it, the name of the method doesn't matter
// The pluginHandler will read all classes that implement the listener with reflection and search the EventHandler method
// This method will then be subscribed of the HandlersList of the Spigot event you give as an argument
// If the event occurs, this EventHandler method is called
// ---------------------------------------------------------------------------------------------
public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Get the player form the event object
        Player player = event.getPlayer();

        // Send a message with his location to the player
        player.sendMessage("You moved!");

        // Get the item the player is holding in its right hand
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        // Get the itemMeta of the item (name, lore, material, amount...)
        ItemMeta itemInMainHandMeta = itemInMainHand.getItemMeta();

        if (itemInMainHandMeta != null) {
            // Check the display name of the item
            String displayName = itemInMainHandMeta.getDisplayName();

            // Text colours in Minecraft are included in the same string as the text
            // The colour string will be preceded with "§<value>"
            // We are only interested in the name and not in the colour, so we do the check with a ".contains()" and not ".equals()"
            if (displayName.contains("Pencil")) {
                // Get the location of the player
                // A location consists of:
                // - The world: The Minecraft world you are currently in
                // - The x, y and z values in the 3D space
                // - The rotation of the player
                Location playerLocation = player.getLocation();

                // The ".getLocation()" method returns the location of the player his feet
                // We want the block the player is standing on, so we decrease the y level with -1
                // Remember that playerLocation is an object, if we were to use the ".add()" method on it, its value would change too
                // The ".clone()" method creates a new Location object, so we can continue with this
                Location blockLocation = playerLocation.clone().add(0, -1, 0);

                // Gets the block on the selected location
                Block block = blockLocation.getBlock();

                // As explained in the Pencil class, every Minecraft Block and ItemStack has a Material
                // We get the material by using the ".getType()" method
                Material material = block.getType();

                // We only want to replace solid blocks and not water, air and lava
                if (
                        material != Material.AIR &&
                        material != Material.WATER &&
                        material != Material.LAVA
                ) {
                    // By calling the ".setType()" method, the material of the block is instantaneously updated in the world
                    block.setType(Material.RED_WOOL);
                }
            }
        }



        // ---- Extra Time: Particles Exercises ----
        // Spawn a particle at the player its feet every time he/she moves
        // If you do this correct, the player will leave a trail of particles
        World world = player.getWorld();
        Location location = player.getLocation();

        world.spawnParticle(Particle.FLAME, location, 0);
        // -----------------------------------------
    }
}
