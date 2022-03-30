package com.lennertsoffers.commands;

import com.lennertsoffers.models.Pencil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

// CommandExecutor is an interface with only one method "onCommand"
// This method will be called when the associated String (command) is typed in the chat window
public class GetPencilCommand implements CommandExecutor {

    /**
     * Called when the associated String (command) is typed in the chat window
     * @param sender the person that sends a command to the server, this can be a Player but not necessarily
     * @param command the command that is executed
     * @param label the String that is typed in the chat window
     * @param args some possible arguments (if you type "/getPencil blue red" in the chat, blue will be in args[0] and red in args[1])
     * @return a boolean to see if the command succeeded or failed
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // The sender can be a Player on the server but not necessarily
        // It can be a server administrator too for example
        if (sender instanceof Player) {

            // Check if the string that is typed in the chat window is effectively the correct command
            // Can be used if you use the same class to handle multiple commands (not recommended!)
            if (label.equalsIgnoreCase("getPencil")) {
                // We have checked that the sender is effectively a player, so we can safely cast the sender to a Player object
                // In this way we can access useful methods of the Player class
                Player commandSender = (Player) sender;

                // The following lines relate to ItemStacks
                // See the Pencil class for more information
                ArrayList<String> lore = new ArrayList<>();
                lore.add("Take this item in your main hand to leave a trace of red wool");

                Pencil pencil = new Pencil();
                pencil.setDisplayName("Pencil");
                pencil.setDisplayColour(ChatColor.LIGHT_PURPLE);
                pencil.setLore(lore);
                pencil.setMaterial(Material.FEATHER);

                // Sets the first empty slot in the player its inventory to the ItemStack we just created with the Pencil class
                commandSender.getInventory().addItem(pencil.getPencilItem());

                // Return true if the command succeeds
                // It's a best practice to return true if the command is effectively executed
                // It is also possible to always return true, but this will make your server logs incorrect if a command has failed
                return true;
            }
        }

        // Return false if the sender is not an instance of a Player
        return false;
    }
}
