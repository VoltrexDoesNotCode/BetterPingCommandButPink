package dev.goldenedit.betterpingcommand;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }
        MiniMessage mm = MiniMessage.miniMessage();
        Player player = (Player)sender;
        if (args.length == 0) {
            Player target = player;
            int ping = target.getPing();
            player.sendMessage(mm.deserialize("<gray>Your ping is: <#d0a0d8>" + ping + "ms"));
            return true;
        }
        if (args.length > 0) {
            Player target = player.getServer().getPlayer(args[0]);
            if (target == null || isVanished(target)) {
                player.sendMessage(mm.deserialize("<red>Player not found!"));
                return true;
            }
            int ping = target.getPing();
            player.sendMessage(mm.deserialize("<gray>The ping of <#d0a0d8>" + target.getName() + "<gray> is: <#d0a0d8>" + ping + "ms"));
            return true;
        }
        return true;
    }
    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }
}
