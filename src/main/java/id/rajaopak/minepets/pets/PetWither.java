package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class PetWither extends Pet {
    public PetWither(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() == mob) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean isFlying() {
        return true;
    }
}
