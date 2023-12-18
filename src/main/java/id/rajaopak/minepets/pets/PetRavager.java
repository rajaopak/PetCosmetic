package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

public class PetRavager extends Pet {
    public PetRavager(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 2;
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
