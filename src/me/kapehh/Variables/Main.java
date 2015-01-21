package me.kapehh.Variables;

import me.kapehh.main.pluginmanager.config.EventPluginConfig;
import me.kapehh.main.pluginmanager.config.EventType;
import me.kapehh.main.pluginmanager.config.PluginConfig;
import me.kapehh.main.pluginmanager.db.PluginDatabase;
import me.kapehh.main.pluginmanager.db.PluginDatabaseInfo;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

/**
 * Created by Karen on 21.01.2015.
 */
public class Main extends JavaPlugin {
    PluginConfig pluginConfig;
    PluginDatabase dbHelper;
    PluginDatabaseInfo dbInfo;

    @EventPluginConfig(EventType.LOAD)
    public void onConfigLoad(FileConfiguration cfg) {
        boolean isEnabled = cfg.getBoolean("connect.enabled");

        if (dbHelper != null) {
            try {
                dbHelper.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // DBInfo не переслздаю, не обязательно
        dbInfo.setIp(cfg.getString("connect.ip", ""));
        dbInfo.setDb(cfg.getString("connect.db", ""));
        dbInfo.setLogin(cfg.getString("connect.login", ""));
        dbInfo.setPassword(cfg.getString("connect.password", ""));
        dbInfo.setTable(cfg.getString("connect.table", ""));

        // коннектимся
        try {
            if (isEnabled) {
                // создаем экземпляр класса для соединения с БД
                dbHelper = new PluginDatabase(
                    dbInfo.getIp(),
                    dbInfo.getDb(),
                    dbInfo.getLogin(),
                    dbInfo.getPassword()
                );

                dbHelper.connect();
                getLogger().info("Success connect to MySQL!");
            } else {
                dbHelper = null;
            }
        } catch (SQLException e) {
            dbHelper = null;
            e.printStackTrace();
        }

        getLogger().info("Complete!");
    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PluginManager") == null) {
            getLogger().info("PluginManager not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        pluginConfig = new PluginConfig(this);
        pluginConfig.addEventClasses(this).setup().loadData();
    }

    @Override
    public void onDisable() {
        if (dbHelper != null) {
            try {
                dbHelper.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
