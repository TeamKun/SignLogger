package net.teamfruit.signlogger;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class SignLogger extends JavaPlugin {
    public static Logger logger;
    public static Log log;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();

        File path = getDataFolder();
        if (!path.isDirectory() && !path.mkdirs())
            throw new RuntimeException("Failed to create directory.");

        // Log File
        File logFile = new File(path, "log.csv");
        try {
            log = new Log(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Event
        getServer().getPluginManager().registerEvents(new SignEventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
