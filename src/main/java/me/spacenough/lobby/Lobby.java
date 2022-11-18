package me.spacenough.lobby;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import me.spacenough.lobby.cmds.LobbyCmd;
import me.spacenough.lobby.cmds.SetLobbyCmd;
import me.spacenough.lobby.events.JoinServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Lobby extends JavaPlugin {
    public YamlDocument config;

    public Lobby instance;

    private PluginManager pm;

    @Override
    public void onEnable() {
        instance = this;

        plsDoNotSellThisPlugin();
        configInitialize();
        registerEvents();
        registerCommands();
    }

    public void registerEvents(){
        pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinServer(this), this);
    }

    public void registerCommands(){
        Objects.requireNonNull(getCommand("setlobby")).setExecutor(new SetLobbyCmd(this));
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new LobbyCmd(this));

    }


    public void configInitialize() {
        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        plsDoNotSellThisPlugin();
    }

    public void plsDoNotSellThisPlugin(){
        getLogger().warning("Ten plugin został udostępniony za darmo na githubie: https://github.com/spacenough");
        getLogger().warning("Jeżeli ktoś sprzedał ci ten plugin, to znaczy, że zostałeś oszukany!");
    }
}
