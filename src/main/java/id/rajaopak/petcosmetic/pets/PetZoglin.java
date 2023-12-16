package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;

public class PetZoglin extends Pet {
    public PetZoglin(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 1.8;
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
