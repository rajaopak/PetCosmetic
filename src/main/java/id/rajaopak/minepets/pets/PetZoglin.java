package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
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
