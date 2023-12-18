package id.rajaopak.minepets.command.subcommand;

import dev.rajaopak.opaklibrary.commands.SubCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class RenameCommand extends SubCommand {

    @Override
    public @NotNull String getName() {
        return "rename";
    }

    @Override
    public @Nullable String getUsage() {
        return "rename <text>";
    }

    @Override
    public @Nullable String getPermission() {
        return "minepets.use.rename";
    }

    @Override
    public @Nullable List<String> parseTabCompletions(JavaPlugin plugin, CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public void execute(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            ChatUtil.sendMessage(sender, "&cOnly player can execute this command!");
            return;
        }

        if (args.length > 0) {
            StringBuilder name = new StringBuilder();

            for (int i = 0; i < args.length; i++) {
                name.append(args[i]);
                if (i+1 != args.length) name.append(" ");
            }

            if (MinePets.getInstance().getPetManager().hasPet(player)) {
                MinePets.getInstance().getPetManager().getPet(player).renameMob(name.toString());
            } else {
                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
            }
        } else {
            ChatUtil.sendMessage(sender, "&6Usage: /pet rename <name>");
        }
    }
}
