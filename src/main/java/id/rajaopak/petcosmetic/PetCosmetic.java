package id.rajaopak.petcosmetic;

import dev.rajaopak.opaklibrary.OpakLibrary;
import dev.rajaopak.opaklibrary.config.ConfigUpdater;
import dev.rajaopak.opaklibrary.config.CustomConfig;
import dev.rajaopak.opaklibrary.libs.Debug;
import id.rajaopak.petcosmetic.command.MainCommand;
import id.rajaopak.petcosmetic.manager.PetManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public final class PetCosmetic extends JavaPlugin {

    private static PetCosmetic instance;

    private PetManager petManager;

    private CustomConfig configFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        OpakLibrary.init(this);

        this.petManager = new PetManager();

        this.configFile = new CustomConfig(this, "config.yml", null);

        try {
            ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList());
            Debug.info("Config is up to date!", true);
        } catch (IOException e) {
            Debug.error("Error while updating config file", e, true);
        }

        new MainCommand(this).register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.petManager.clear();
    }

    public static PetCosmetic getInstance() {
        return instance;
    }

    public PetManager getPetManager() {
        return petManager;
    }

    public CustomConfig getConfigFile() {
        return configFile;
    }
}
