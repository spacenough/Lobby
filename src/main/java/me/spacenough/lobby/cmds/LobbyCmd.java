package me.spacenough.lobby.cmds;

import me.spacenough.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static me.spacenough.lobby.utils.ChatUtil.altChar;

public class LobbyCmd implements CommandExecutor {

    private final Lobby instance;

    private static Set<Player> countdowns = new HashSet<>();

    private static int id;

    public LobbyCmd(Lobby instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda moze byc uzyta tylko przez gracza!");
            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("lobby.lobby")) && !(player.isOp())) {
            player.sendMessage(altChar(instance.config.getString("brak-uprawnien")));
            return true;
        }

        teleportPlayerToLobby(player);

        return false;
    }

    public void teleportPlayerToLobby(Player player) {

        countdowns.add(player);


        World lobbyWorld = Bukkit.getWorld(instance.config.getString("lobby-swiat"));

        Location lobby = new Location(lobbyWorld,
                instance.config.getInt("lobby-x"),
                instance.config.getInt("lobby-y"),
                instance.config.getInt("lobby-z")
        );

        int countdown = instance.config.getInt("cooldown");

        int[] count = new int[1];

        count[0] = countdown;

        id = Bukkit.getScheduler().runTaskTimer(instance, () -> {

            if (count[0] <= 0) {
                Bukkit.getScheduler().cancelTask(id);

                countdowns.remove(player);

                player.teleport(lobby);

                player.sendTitle(altChar(instance.config.getString("teleportacja-lobby-przeteleportowano-title")),
                        altChar(instance.config.getString("teleportacja-lobby-przeteleportowano-subtitle")),
                        10,
                        50,
                        10
                );

                return;
            }

            if (!countdowns.contains(player)) {
                Bukkit.getScheduler().cancelTask(id);

                player.sendTitle(altChar(instance.config.getString("teleportacja-lobby-title")),
                        altChar(instance.config.getString("teleportacja-lobby-subtitle")),
                        10,
                        50,
                        10
                );

                return;
            }

            player.sendTitle(altChar(instance.config.getString("teleportacja-lobby-title")),
                    altChar(instance.config.getString("teleportacja-lobby-subtitle-nastapi-za")) + count[0],
                    10,
                    50,
                    10
            );

            count[0]--;

        }, 0, 20).getTaskId();
    }
}
