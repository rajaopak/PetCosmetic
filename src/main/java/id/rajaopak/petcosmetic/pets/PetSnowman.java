package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.EntityBlockFormEvent;

public class PetSnowman extends Pet {
    public PetSnowman(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @EventHandler
    public void onTrail(EntityBlockFormEvent event) {
        if (event.getEntity() == mob) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
