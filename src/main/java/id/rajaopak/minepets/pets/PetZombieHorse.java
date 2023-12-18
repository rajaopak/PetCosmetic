package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

public class PetZombieHorse extends Pet {
    public PetZombieHorse(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 1.5;
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
