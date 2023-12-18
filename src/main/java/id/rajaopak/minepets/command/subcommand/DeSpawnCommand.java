package id.rajaopak.minepets.command.subcommand;

import dev.rajaopak.opaklibrary.commands.SubCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeSpawnCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "despawn";
    }

    @Override
    public @Nullable String getUsage() {
        return "despawn";
    }

    @Override
    public @Nullable String getPermission() {
        return "minepets.use.despawn";
    }

    @Override
    public @Nullable List<String> parseTabCompletions(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission("minepets.use.despawn.others")) {
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase())).sorted().collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public void execute(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                ChatUtil.sendMessage(sender, "&cOnly Player can execute this command!");
                return;
            }

            if (MinePets.getInstance().getPetManager().hasPet(player)) {
                MinePets.getInstance().getPetManager().removePet(player);
                ChatUtil.sendMessage(player, "&aSuccessfully despawn your pet!");
            } else {
                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
            }
        } else if (args.length == 1) {
            if (!sender.hasPermission("minepets.use.despawn.others")) {
                ChatUtil.sendMessage(sender, "&cYou don't have permission to do this!");
                return;
            }

            String playerName = args[0];

            Player target = Bukkit.getPlayer(playerName);

            if (target == null) {
                ChatUtil.sendMessage(sender, "&cPlayer not found!");
                return;
            }

            if (MinePets.getInstance().getPetManager().hasPet(target)) {
                MinePets.getInstance().getPetManager().removePet(target);
                ChatUtil.sendMessage(target, "&aSuccessfully despawn &e" + target.getName() + " &apet!");
            } else {
                ChatUtil.sendMessage(target, "&c" + target.getName() + " don't have any selected pet!");
            }
        } else {
            ChatUtil.sendMessage(sender, "&6Usage: /pet despawn <player>");
        }
    }
}
