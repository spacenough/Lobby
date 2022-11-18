package me.spacenough.lobby.cmds;

import me.spacenough.lobby.Lobby;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static me.spacenough.lobby.utils.ChatUtil.altChar;

public class SetLobbyCmd implements CommandExecutor {

    private final Lobby instance;

    public SetLobbyCmd(Lobby instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage("Ta komenda moze byc uzyta tylko przez gracza!");
            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("lobby.setlobby")) && !(player.isOp())){
            player.sendMessage(altChar(instance.config.getString("brak-uprawnien")));
            return true;
        }

        if (!(player.getLocation().getWorld().getName().equalsIgnoreCase(instance.config.getString("lobby-swiat")))){
            player.sendMessage(altChar(instance.config.getString("inny-swiat")));
            return true;
        }

        setSpawn(player);

        return false;
    }

    public void setSpawn(Player player){

        int x = (int) player.getLocation().getX();
        int y = (int) player.getLocation().getY();
        int z = (int) player.getLocation().getZ();

        instance.config.set("lobby-x", x);
        instance.config.set("lobby-y", y);
        instance.config.set("lobby-z", z);

        try {
            instance.config.update();
            instance.config.save();
        }catch (IOException e){
            e.printStackTrace();
        }

        player.sendTitle(altChar(instance.config.getString("title-tekst")),
                altChar(instance.config.getString("subtitle-tekst")),
                20,5,20
        );
    }
}
