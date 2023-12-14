package id.rajaopak.petcosmetic.pets;

import id.rajaopak.petcosmetic.PetType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class PetAxolotl extends Pet {
    public PetAxolotl(Player player, PetType<?> petType) {
        super(player, petType);
    }

    @Override
    protected void setupMob() {
        mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.6);
    }
}
