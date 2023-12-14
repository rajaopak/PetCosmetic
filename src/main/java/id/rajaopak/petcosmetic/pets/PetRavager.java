package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;

public class PetRavager extends Pet {
    public PetRavager(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 2;
    }
}
