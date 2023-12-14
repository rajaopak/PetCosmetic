package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;

public class PetWanderingTrader extends Pet {
    public PetWanderingTrader(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((WanderingTrader) mob).setDespawnDelay(0);
    }

    @Override
    public boolean useMarkerArmorStand() {
        return false;
    }
}
