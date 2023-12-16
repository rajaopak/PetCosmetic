package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;

public class PetGhast extends Pet {
    public PetGhast(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 2.0;
    }

    @Override
    public boolean isFlying() {
        return true;
    }
}
