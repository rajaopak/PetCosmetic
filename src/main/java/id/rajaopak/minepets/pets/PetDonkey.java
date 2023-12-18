package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Player;

public class PetDonkey extends Pet {
    public PetDonkey(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((Donkey) mob).setCarryingChest(true);
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
