package me.kapehh.Variables;

import me.kapehh.main.pluginmanager.db.PluginDatabase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Karen on 21.01.2015.
 */
public class Commands {

    private Commands() { }

    public static void init(JavaPlugin plugin, PluginDatabase database) {
        plugin.getCommand("setvariable").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
                return false;
            }
        });

        plugin.getCommand("getvariable").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
                return false;
            }
        });

        plugin.getCommand("setvariablepass").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
                return false;
            }
        });
    }
}
