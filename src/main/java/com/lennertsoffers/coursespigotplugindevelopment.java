package com.lennertsoffers;

import com.lennertsoffers.commands.GetPencilCommand;
import com.lennertsoffers.events.PlayerClick;
import com.lennertsoffers.events.PlayerJoin;
import com.lennertsoffers.events.PlayerMove;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class coursespigotplugindevelopment extends JavaPlugin {

    // 'main' function of a Spigot plugin
    // Minecraft will call these functions for you (if your plugin is hooked in the main game)

    // Minecraft itself will have 1 main function
    // In this main function, Minecraft will loop over all its plugins
    // For every plugin, these 2 functions can be executed


    // onEnable will run 1 time when the plugin gets loaded in the Minecraft server
    // This is the starting point of each plugin
    // If you write a functionality, and you don't address it in the onEnable, it won't run
    @Override
    public void onEnable() {
        this.registerEvents();
        this.registerCommands();
    }

    // onDisable will run 1 time when the plugin gets unloaded
    // This is mainly used to save some configurations, clean up the runtime and safely disable the plugin
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Method to register all the events the plugin uses
    // Subscribes the listener classes to the main Minecraft program
    private void registerEvents() {
        // Events need to be registered in the pluginManager of which 1 instance is accessible on the spigot server
        PluginManager pluginManager = this.getServer().getPluginManager();

        // Registers the event and subscribes a class with an eventHandler method in it
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerClick(), this);
    }

    // Commands won't work if you don't associate a CommandExecutor object with them
    private void registerCommands() {
        // The getCommand method registers a String as a command
        // If you type "/<name-of-command>" in the Minecraft chat window,
        // this associated CommandExecutor object will run its onCommand method
        PluginCommand pluginCommand = this.getCommand("getPencil");

        if (pluginCommand != null) {
            pluginCommand.setExecutor(new GetPencilCommand());
        }
    }
}