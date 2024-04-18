package dev.goldenedit.betterpingcommand;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterPingCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("ping").setExecutor(new PingCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
