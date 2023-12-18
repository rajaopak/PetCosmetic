package id.rajaopak.minepets.command.subcommand;

import dev.rajaopak.opaklibrary.commands.SubCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends SubCommand {

    @Override
    public @NotNull String getName() {
        return "reload";
    }

    @Override
    public @Nullable String getUsage() {
        return "reload";
    }

    @Override
    public @Nullable String getPermission() {
        return "minepets.use.reload";
    }

    @Override
    public @Nullable List<String> parseTabCompletions(JavaPlugin plugin, CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public void execute(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 0) {
            MinePets.getInstance().getConfigFile().reloadConfig();
            //minepets.getInstance().getPetManager().getPlayerPet().values().forEach(pet -> pet.renameMob(minepets.getInstance().getConfigFile().getConfig().getString("default-pet-nametag")));
            ChatUtil.sendMessage(sender, "&cWarning: reloading the plugin may not effect the plugin at all. Consider to restart the server!");
            ChatUtil.sendMessage(sender, "&aSuccessfully reload the plugin!");
        } else {
            ChatUtil.sendMessage(sender, "&6Usage: /pet reload");
        }
    }
}
