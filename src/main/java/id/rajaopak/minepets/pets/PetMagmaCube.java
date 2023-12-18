package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;

public class PetMagmaCube extends Pet {
    public PetMagmaCube(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((MagmaCube) mob).setSize(1);
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
