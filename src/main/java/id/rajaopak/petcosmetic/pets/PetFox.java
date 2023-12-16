package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;

import java.util.Random;

public class PetFox extends Pet {
    public PetFox(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        Random random = new Random();

        int number = random.nextInt(3);

        switch (number) {
            case 0, 2 -> ((Fox) mob).setFoxType(Fox.Type.RED);
            default -> ((Fox) mob).setFoxType(Fox.Type.SNOW);
        }
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}
