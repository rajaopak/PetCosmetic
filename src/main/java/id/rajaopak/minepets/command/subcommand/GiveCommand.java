package id.rajaopak.minepets.command.subcommand;

import dev.rajaopak.opaklibrary.commands.SubCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import id.rajaopak.minepets.PetType;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.log.LogPublishEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GiveCommand extends SubCommand {

    @Override
    public @NotNull String getName() {
        return "give";
    }

    @Override
    public @Nullable String getUsage() {
        return "give <player> <pet>";
    }

    @Override
    public @Nullable String getPermission() {
        return "minepets.use.give";
    }

    @Override
    public @Nullable List<String> parseTabCompletions(JavaPlugin plugin, CommandSender sender, String[] args) {

        if (args.length == 1) {
            return plugin.getServer().getOnlinePlayers().stream().map(Player::getName).sorted().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2) {
            return MinePets.getInstance().getPetManager().getPetList().stream().map(PetType::getName).sorted().filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public void execute(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 2) {
            String targetName = args[0];
            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                ChatUtil.sendMessage(sender, "&cCannot find player with that name!");
                return;
            }

            ChatUtil.sendMessage(sender, "count: " + MinePets.getInstance().getPlayerManager().countPlayerPets(target));
            ChatUtil.sendMessage(sender, "limit: " + MinePets.getInstance().getPlayerManager().getPlayerPetLimit(target));
            if (MinePets.getInstance().getPlayerManager().getPlayerPetLimit(target) <= MinePets.getInstance().getPlayerManager().countPlayerPets(target)) {
                ChatUtil.sendMessage(sender, "&e" + target.getName() + " &cHas reach their max pet limit!");
                return;
            }

            String petName = args[1];
            PetType<?> petType = MinePets.getInstance().getPetManager().getPetType(petName);

            if (petType == null) {
                ChatUtil.sendMessage(sender, "&cCannot find pet with that name!");
                return;
            }

            if (target.hasPermission("minepets.pet." + petType.getName().toLowerCase())) {
                ChatUtil.sendMessage(sender, "&c" + target.getName() + " already has pet &e" + petType.getName() + "&c!");
                return;
            }

            MinePets.getInstance().getPlayerManager().setPlayerPermission(target, "minepets.pet." + petType.getName().toLowerCase());
            ChatUtil.sendMessage(sender, "&aSuccessfully give pet &e" + petType.getName() + "&a to &e" + target.getName() + "&a!");
        } else {
            ChatUtil.sendMessage(sender, "&6Usage: /minepets " + getUsage());
        }
    }
}
