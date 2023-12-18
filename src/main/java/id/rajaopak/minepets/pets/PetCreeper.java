package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;

public class PetCreeper extends Pet {
    public PetCreeper(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((Creeper) mob).setPowered(true);
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
