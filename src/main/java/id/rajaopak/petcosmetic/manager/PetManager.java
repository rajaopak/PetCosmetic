package id.rajaopak.petcosmetic.manager;

import id.rajaopak.petcosmetic.PetType;
import id.rajaopak.petcosmetic.pets.Pet;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetManager {

    private final HashMap<Player, Pet> playerPet;

    private final List<PetType<?>> petList = new ArrayList<>();

    public PetManager() {
        this.playerPet = new HashMap<>();
        initPet();
    }

    public void addPet(Player player, Pet pet) {
        if (hasPet(player)) removePet(player);
        this.playerPet.put(player, pet);
    }

    public boolean hasPet(Player player) {
        return this.playerPet.containsKey(player);
    }

    public void removePet(Player player) {
        if (hasPet(player)) {
            getPet(player).clear();
            this.playerPet.remove(player);
        }
    }

    public Pet getPet(Player player) {
        if (hasPet(player)) return this.playerPet.get(player);
        return null;
    }

    public HashMap<Player, Pet> getPlayerPet() {
        return playerPet;
    }

    public List<PetType<?>> getPetList() {
        return petList;
    }

    public void clear() {
        this.playerPet.values().forEach(Pet::clear);
        this.playerPet.clear();
        this.petList.clear();
    }

    private void initPet() {
        this.petList.add(PetType.ALLAY);
        this.petList.add(PetType.AXOLOTL);
        this.petList.add(PetType.BAT);
        this.petList.add(PetType.BEE);
        this.petList.add(PetType.BLAZE);
        this.petList.add(PetType.CAVE_SPIDER);
        this.petList.add(PetType.CHICK);
        this.petList.add(PetType.COD);
        this.petList.add(PetType.COW);
        this.petList.add(PetType.CREEPER);
        this.petList.add(PetType.DOG);
        this.petList.add(PetType.DOLPHIN);
        this.petList.add(PetType.DONKEY);
        this.petList.add(PetType.DROWNED);
        this.petList.add(PetType.ELDER_GUARDIAN);
        this.petList.add(PetType.ENDERMAN);
        this.petList.add(PetType.ENDERMITE);
        this.petList.add(PetType.EVOKER);
        this.petList.add(PetType.FOX);
        this.petList.add(PetType.FROG);
        this.petList.add(PetType.GOAT);
        this.petList.add(PetType.GUARDIAN);
        this.petList.add(PetType.HOGLIN);
        this.petList.add(PetType.HORSE);
        this.petList.add(PetType.HUSK);
        this.petList.add(PetType.ILLUSIONER);
        this.petList.add(PetType.IRON_GOLEM);
        this.petList.add(PetType.KITTY);
        this.petList.add(PetType.LLAMA);
        this.petList.add(PetType.MAGMA_CUBE);
        this.petList.add(PetType.MOOSHROOM);
        this.petList.add(PetType.MULE);
        this.petList.add(PetType.OCELOT);
        this.petList.add(PetType.PANDA);
        this.petList.add(PetType.PARROT);
        this.petList.add(PetType.PIGGY);
        this.petList.add(PetType.PIGLIN);
        this.petList.add(PetType.PIGLIN_BRUTE);
        this.petList.add(PetType.PILLAGER);
        this.petList.add(PetType.POLAR_BEAR);
        this.petList.add(PetType.PUFFER_FISH);
        this.petList.add(PetType.RABBIT);
        this.petList.add(PetType.RAVAGER);
        this.petList.add(PetType.SALMON);
        this.petList.add(PetType.SHEEP);
        this.petList.add(PetType.SILVERFISH);
        this.petList.add(PetType.SKELETON);
        this.petList.add(PetType.SKELETON_HORSE);
        this.petList.add(PetType.SLIME);
        this.petList.add(PetType.SNIFFER);
        this.petList.add(PetType.SNOWMAN);
        this.petList.add(PetType.SPIDER);
        this.petList.add(PetType.STRAY);
        this.petList.add(PetType.STRIDER);
        this.petList.add(PetType.TADPOLE);
        this.petList.add(PetType.TROPICAL_FISH);
        this.petList.add(PetType.TURTLE);
        this.petList.add(PetType.VEX);
        this.petList.add(PetType.VILLAGER);
        this.petList.add(PetType.VINDICATOR);
        this.petList.add(PetType.WANDERING_TRADER);
        this.petList.add(PetType.WARDEN);
        this.petList.add(PetType.WITCH);
        this.petList.add(PetType.WITHER_SKELETON);
        this.petList.add(PetType.ZOGLIN);
        this.petList.add(PetType.ZOMBIE);
        this.petList.add(PetType.ZOMBIE_HORSE);
        this.petList.add(PetType.ZOMBIE_VILLAGER);
        this.petList.add(PetType.ZOMBIFIED_PIGLIN);
    }
}
