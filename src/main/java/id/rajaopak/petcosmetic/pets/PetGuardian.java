package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;

public class PetGuardian extends Pet {
    public PetGuardian(Player player, PetType<?> petType) {
        super(player, petType);
    }
}