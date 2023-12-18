package id.rajaopak.minepets.manager;

import id.rajaopak.minepets.MinePets;
import id.rajaopak.minepets.util.LuckpermsHook;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.actionlog.Action;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.time.Instant;
import java.util.HashMap;

public class PlayerManager {

    private final MinePets plugin;
    private final HashMap<Player, PermissionAttachment> players;

    public PlayerManager(MinePets plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
    }

    public void loadPlayer(Player player) {
        this.players.put(player, player.addAttachment(this.plugin));
    }

    public void unloadPlayer(Player player) {
        this.players.remove(player);
    }

    public PermissionAttachment getPlayer(Player player) {
        return this.players.get(player);
    }

    public void setPlayerPermission(Player player, String permission) {
        if (this.plugin.isLuckPermsEnabled()) {
            LuckpermsHook.setPlayerPermission(player, permission);
            System.out.println("luckperms");
            return;
        }

        getPlayer(player).setPermission(permission, true);
        System.out.println("biasa");
    }

    public void removePlayerPermission(Player player, String permission) {
        if (this.plugin.isLuckPermsEnabled()) {
            LuckpermsHook.removePlayerPermission(player, permission);
            System.out.println("luckperms");
            return;
        }

        getPlayer(player).unsetPermission(permission);
        System.out.println("biasa");
    }

    public int getPlayerPetLimit(Player player) {
        int limit = MinePets.getInstance().getDefaultPlayerPetLimit();

        for (PermissionAttachmentInfo pi : player.getEffectivePermissions()) {
            String permission = pi.getPermission();
            int current;
            if (!permission.startsWith("minepets.limit.") || (current = Integer.parseInt(permission.split("\\.")[2])) <= limit) continue;
            limit = current;
        }

        return limit;
    }

    public int countPlayerPets(Player player) {
        int amount = 0;

        for (PermissionAttachmentInfo pi : player.getEffectivePermissions()) {
            String permission = pi.getPermission();
            if (permission.startsWith("minepets.pet.")) {
                amount++;
            }
        }

        return amount;
    }

    public void clear() {
        this.players.clear();
    }

}
