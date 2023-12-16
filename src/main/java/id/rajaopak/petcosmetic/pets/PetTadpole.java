package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tadpole;

public class PetTadpole extends Pet {
    public PetTadpole(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        ((Tadpole) mob).setAge(0);
    }

    @Override
    public double getDistance() {
        return 1;
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
