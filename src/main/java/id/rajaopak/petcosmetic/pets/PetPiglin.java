package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PiglinBarterEvent;

public class PetPiglin extends Pet {
    public PetPiglin(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        Piglin piglin = (Piglin) mob;
        piglin.setImmuneToZombification(true);
        piglin.getInterestList().forEach(piglin::removeMaterialOfInterest);
    }

    @EventHandler
    public void onBarter(PiglinBarterEvent event) {
        if (event.getEntity() == mob) {
            event.setCancelled(true);
        }
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
