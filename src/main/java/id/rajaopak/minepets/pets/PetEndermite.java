package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

public class PetEndermite extends Pet {
    public PetEndermite(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
