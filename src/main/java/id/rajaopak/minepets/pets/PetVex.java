package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

public class PetVex extends Pet {
    public PetVex(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public boolean isFlying() {
        return true;
    }
}
