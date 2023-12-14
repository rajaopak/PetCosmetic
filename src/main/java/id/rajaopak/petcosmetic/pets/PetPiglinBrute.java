package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;

public class PetPiglinBrute extends Pet {
    public PetPiglinBrute(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((PiglinBrute) mob).setImmuneToZombification(true);
    }
}
