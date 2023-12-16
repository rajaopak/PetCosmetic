package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Mule;
import org.bukkit.entity.Player;

public class PetMule extends Pet {
    public PetMule(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((Mule) mob).setCarryingChest(true);
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
