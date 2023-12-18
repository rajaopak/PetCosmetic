package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

public class PetParrot extends Pet {
    public PetParrot(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public boolean isFlying() {
        return true;
    }
}
