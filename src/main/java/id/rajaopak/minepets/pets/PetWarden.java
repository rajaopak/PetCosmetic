package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPotionEffectEvent;

public class PetWarden extends Pet {
    public PetWarden(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 2;
    }

    @EventHandler
    public void onDarkness(EntityPotionEffectEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && event.getCause() == EntityPotionEffectEvent.Cause.WARDEN
                && event.getEntity().getLocation().distanceSquared(mob.getLocation()) < 22 * 22) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
