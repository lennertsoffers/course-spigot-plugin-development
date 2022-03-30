// ---- Extra Time: Particles ----
package com.lennertsoffers.events;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class PlayerClick implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Get the world object from the player
        World world = player.getWorld();
        // Get the location above the player's head
        Location location = player.getLocation().add(0, 2.5, 0);
        // Create an instance of Random to generate random numbers
        Random random = new Random();

        // In each iteration of this loop, a particle will spawn
        for (int i = 0; i < 30; i++) {
            // Take the x, y and z values of the location
            // Generate a new random Gaussian
            // This will be a number between -1 and 1
            // Most of the numbers will be closer to the center because of the normal distribution
            // If we do this for each axe, the result will be a sphere
            double newX = location.getX() + random.nextGaussian() / 10;
            double newY = location.getY() + random.nextGaussian() / 10;
            double newZ = location.getZ() + random.nextGaussian() / 10;
            // Create a new location object from the new coordinates
            Location particleLocation = new Location(world, newX, newY, newZ);

            // Spawn a particle in the world
            // The count must be 0, otherwise Minecraft will give the particle a velocity, and we don't want that
            world.spawnParticle(Particle.FLAME, particleLocation, 0);
        }
    }
}
// -------------------------------
