package me.spacenough.lobby.events;

import me.spacenough.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinServer implements Listener {

    private final Lobby instance;

    public JoinServer(Lobby instance) {
        this.instance = instance;
    }

    @EventHandler
    public void autoJoinLobby(PlayerJoinEvent event){

        Player player = event.getPlayer();

        World lobbyWorld = Bukkit.getWorld(instance.config.getString("lobby-swiat"));

        Location lobby = new Location(lobbyWorld,
                instance.config.getInt("lobby-x"),
                instance.config.getInt("lobby-y"),
                instance.config.getInt("lobby-z")
        );

        player.teleport(lobby);

    }
}
