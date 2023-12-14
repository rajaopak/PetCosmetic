package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PetAllay extends Pet {
    public PetAllay(Player player, PetType<?> type) {
        super(player, type);
    }

    @EventHandler
    public void onClick(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() == mob) {
            event.setCancelled(true);
        }
    }
}