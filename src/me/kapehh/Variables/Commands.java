package me.kapehh.Variables;

import me.kapehh.main.pluginmanager.db.PluginDatabase;
import me.kapehh.main.pluginmanager.db.PluginDatabaseInfo;
import me.kapehh.main.pluginmanager.db.PluginDatabaseResult;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Karen on 21.01.2015.
 */
public class Commands {

    private Commands() { }

    public static void init(final Main plugin) {
        plugin.getCommand("setvariable").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
                if (args.length < 2) {
                    return false;
                }

                if (!sender.isOp()) {
                    sender.sendMessage("You need OP!");
                    return true;
                }

                try {
                    plugin.getDbHelper().prepareQueryUpdate("INSERT INTO " + plugin.getDbInfo().getTable() + " VALUES(?, ?, now()) ON duplicate key update value = ?", args[0], args[1], args[1]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        plugin.getCommand("getvariable").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
                if (args.length < 1) {
                    return false;
                }

                try {
                    PluginDatabaseResult result = plugin.getDbHelper().prepareQueryStart("SELECT * FROM " + plugin.getDbInfo().getTable() + " WHERE `key` = ?", args[0]);
                    ResultSet resultSet = result.getResultSet();
                    if (resultSet.next()) {
                        sender.sendMessage("Key: " + resultSet.getString("key") + ", Value: " + resultSet.getString("value"));
                    } else {
                        sender.sendMessage("Key not found!");
                    }
                    plugin.getDbHelper().queryEnd(result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        plugin.getCommand("setvariablepass").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
                if (args.length < 3) {
                    return false;
                }

                if (!args[0].equals(plugin.getVarPass())) {
                    sender.sendMessage("Incorrect password!");
                    return true;
                }

                try {
                    plugin.getDbHelper().prepareQueryUpdate("INSERT INTO " + plugin.getDbInfo().getTable() + " VALUES(?, ?, now()) ON duplicate key update value = ?", args[1], args[2], args[2]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }
}
