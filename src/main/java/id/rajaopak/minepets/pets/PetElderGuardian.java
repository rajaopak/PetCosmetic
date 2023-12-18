package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffectType;

public class PetElderGuardian extends Pet {
    public PetElderGuardian(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 2;
    }

    @EventHandler
    public void onFatigue(EntityPotionEffectEvent event) {
        if (event.getCause() == EntityPotionEffectEvent.Cause.ATTACK && event.getNewEffect().getType().equals(PotionEffectType.SLOW_DIGGING)) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
