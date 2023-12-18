package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

public class PetSkeletonHorse extends Pet {
    public PetSkeletonHorse(Player player, PetType<?> petType) {
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
