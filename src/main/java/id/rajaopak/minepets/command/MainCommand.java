package id.rajaopak.minepets.command;

import dev.rajaopak.opaklibrary.commands.BaseCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import id.rajaopak.minepets.command.subcommand.*;
import id.rajaopak.minepets.menu.PetMenu;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MainCommand extends BaseCommand {

    public MainCommand(JavaPlugin plugin) {
        super(plugin, "pet", List.of("minepets", "mpets", "mpet", "pets"), "minepets.use",
                sender -> {
                    if (MinePets.getInstance().isUsePetGui()) {
                        if (!(sender instanceof Player player)) {
                            ChatUtil.sendMessage(sender, "&cOnly Player can execute this command!");
                            return;
                        }

                        if (!sender.hasPermission("minepets.menu")) {
                            ChatUtil.sendMessage(sender, "&cYou don't have permission to do this!");
                            return;
                        }

                        PetMenu menu = new PetMenu(player);

                        menu.openMenu(0);
                    } else {
                        ChatUtil.sendMessage(sender, "&6Usage: /minepets <despawn|give|glow|release|reload|rename>");
                    }
                },
                sender -> ChatUtil.sendMessage(sender, "&cYou don't have permission to do this!"),
                sender -> ChatUtil.sendMessage(sender, "&cNo sub command was found!"),
                null, new GiveCommand(), new ReleaseCommand(), new DeSpawnCommand(), new GlowCommand(), new RenameCommand(), new ReloadCommand());
    }
}
