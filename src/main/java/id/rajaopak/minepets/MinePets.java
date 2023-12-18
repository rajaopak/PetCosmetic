package id.rajaopak.minepets;

import dev.rajaopak.opaklibrary.OpakLibrary;
import dev.rajaopak.opaklibrary.config.ConfigUpdater;
import dev.rajaopak.opaklibrary.config.CustomConfig;
import dev.rajaopak.opaklibrary.libs.Debug;
import id.rajaopak.minepets.command.MainCommand;
import id.rajaopak.minepets.listener.PlayerListener;
import id.rajaopak.minepets.manager.PetManager;
import id.rajaopak.minepets.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public final class MinePets extends JavaPlugin {

    private static MinePets instance;

    private PetManager petManager;
    private PlayerManager playerManager;

    private CustomConfig configFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        OpakLibrary.init(this);

        this.petManager = new PetManager();
        this.playerManager = new PlayerManager(this);

        this.configFile = new CustomConfig( "config.yml", null);

        try {
            ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList());
            Debug.info("Config is up to date!", true);
        } catch (IOException e) {
            Debug.error("Error while updating config file", e, true);
        }

        new MainCommand(this).register();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.petManager.clear();
        this.playerManager.clear();
    }

    public static MinePets getInstance() {
        return instance;
    }

    public PetManager getPetManager() {
        return petManager;
    }

    public CustomConfig getConfigFile() {
        return configFile;
    }

    public int getDefaultPlayerPetLimit() {
        return getConfigFile().getConfig().getInt("player-pet-limit");
    }

    public boolean isUsePetGui() {
        return getConfigFile().getConfig().getBoolean("use-pet-gui");
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public boolean isLuckPermsEnabled() {
        return Bukkit.getServer().getPluginManager().isPluginEnabled("LuckPerms");
    }
}
