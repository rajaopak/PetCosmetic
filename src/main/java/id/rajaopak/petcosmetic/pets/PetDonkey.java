package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
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
}
