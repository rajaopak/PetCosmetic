package id.rajaopak.minepets.pets;

import id.rajaopak.minepets.PetType;
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

    @Override
    public boolean isFlying() {
        return false;
    }
}
