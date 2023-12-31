package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
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
