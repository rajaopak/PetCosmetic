package id.rajaopak.minepets.manager;

import id.rajaopak.minepets.PetType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    private final Player player;
    private final List<PetType<?>> ownedPet;

    private int petLimit;

    public PlayerData(Player player) {
        this.player = player;
        this.petLimit = 5;
        this.ownedPet = new ArrayList<>();
    }

    public void addPet(PetType<?> pet) {
        this.ownedPet.add(pet);
    }

    public void removePet(PetType<?> pet) {
        this.ownedPet.remove(pet);
    }

    public List<PetType<?>> getOwnedPet() {
        return ownedPet;
    }
}
