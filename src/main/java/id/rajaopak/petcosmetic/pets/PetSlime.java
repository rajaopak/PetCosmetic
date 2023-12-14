package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

public class PetSlime extends Pet {
    public PetSlime(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((Slime) mob).setSize(1);
    }
}
