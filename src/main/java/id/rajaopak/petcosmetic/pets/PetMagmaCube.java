package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
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
}
