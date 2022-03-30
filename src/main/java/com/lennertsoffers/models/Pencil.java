package com.lennertsoffers.models;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Pencil {
    private String displayName;
    private ChatColor displayColour;
    private Material material;
    private ArrayList<String> lore;

    public Pencil() {}

    // To create a new ItemStack, set the values of it and return it
    public ItemStack getPencilItem() {
        // Minecraft items can't be seen as single entities
        // It seems that every item is a single entity, and your inventory displays the amount of items you have in each slot
        // However this is not true
        // Minecraft items must be seen as the combination of a Material and an Amount
        // What you see as a single item like "beef", actually is the combination of the Material "beef" and the amount 1
        // This combination is called an ItemStack

        // Create a new ItemStack for the pencil
        ItemStack pencil = new ItemStack(this.material, 1);
        // Get the ItemMeta of the ItemStack
        // ItemMeta is for the most part textual info about an ItemStack, including:
        // - The display name
        // - The lore: A description of the item
        ItemMeta pencilMeta = pencil.getItemMeta();

        if (pencilMeta != null) {
            // Text colours in Minecraft are included in the same string as the text
            // The colour string will be preceded with "§<value>"
            // ChatColor is just a collection of constant objects that translate to a string with this "§" value
            // That is why we can concatenate these 2 objects
            pencilMeta.setDisplayName(this.displayColour + this.displayName);

            // To set the lore, we need to give a Collection of String as an argument
            // Each entry in this list will than be set as a newline in the item description
            pencilMeta.setLore(this.lore);

            // Method to actually set the ItemMeta on the ItemStack
            pencil.setItemMeta(pencilMeta);
        }

        return pencil;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDisplayColour(ChatColor displayColour) {
        this.displayColour = displayColour;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setLore(ArrayList<String> lore) {
        this.lore = lore;
    }
}
