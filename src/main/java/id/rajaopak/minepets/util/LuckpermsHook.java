package id.rajaopak.minepets.util;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.entity.Player;

public class LuckpermsHook {

    public static void setPlayerPermission(Player player, String permission) {
        LuckPermsProvider.get().getUserManager().modifyUser(player.getUniqueId(), user -> {
            user.data().add(PermissionNode.builder(permission).value(true).build());
        });
    }

    public static void removePlayerPermission(Player player, String permission) {
        LuckPermsProvider.get().getUserManager().modifyUser(player.getUniqueId(), user -> {
            user.data().remove(PermissionNode.builder(permission).build());
        });
    }

}
