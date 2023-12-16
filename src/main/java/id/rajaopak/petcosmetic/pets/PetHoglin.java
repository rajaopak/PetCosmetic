package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Player;

public class PetHoglin extends Pet {
    public PetHoglin(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    public double getDistance() {
        return 1.8;
    }

    @Override
    protected void setupMob() {
        ((Hoglin) mob).setImmuneToZombification(true);
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
