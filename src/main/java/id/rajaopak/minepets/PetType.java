package id.rajaopak.minepets;

import id.rajaopak.minepets.pets.*;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class PetType<E extends Pet> {

    public final static PetType<PetPiggy> PIGGY = new PetType<>("Piggy", EntityType.PIG, Material.PIG_SPAWN_EGG, PetPiggy.class);
    public final static PetType<PetRabbit> RABBIT = new PetType<>("Rabbit", EntityType.RABBIT, Material.RABBIT_SPAWN_EGG, PetRabbit.class);
    public final static PetType<PetCow> COW = new PetType<>("Cow", EntityType.COW, Material.COW_SPAWN_EGG, PetCow.class);
    public final static PetType<PetMooshroom> MOOSHROOM = new PetType<>("Mooshroom", EntityType.MUSHROOM_COW, Material.MOOSHROOM_SPAWN_EGG, PetMooshroom.class);
    public final static PetType<PetDog> DOG = new PetType<>("Dog", EntityType.WOLF, Material.WOLF_SPAWN_EGG, PetDog.class);
    public final static PetType<PetChick> CHICK = new PetType<>("Chick", EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG, PetChick.class);
    public final static PetType<PetIronGolem> IRON_GOLEM = new PetType<>("IronGolem", EntityType.IRON_GOLEM, Material.IRON_GOLEM_SPAWN_EGG, PetIronGolem.class);
    public final static PetType<PetSnowman> SNOWMAN = new PetType<>("Snowman", EntityType.SNOWMAN, Material.SNOW_GOLEM_SPAWN_EGG, PetSnowman.class);
    public final static PetType<PetVillager> VILLAGER = new PetType<>("Villager", EntityType.VILLAGER, Material.VILLAGER_SPAWN_EGG, PetVillager.class);
    public final static PetType<PetBat> BAT = new PetType<>("Bat", EntityType.BAT, Material.BAT_SPAWN_EGG, PetBat.class);
    public final static PetType<PetSheep> SHEEP = new PetType<>("Sheep", EntityType.SHEEP, Material.SHEEP_SPAWN_EGG, PetSheep.class);
    public final static PetType<PetSlime> SLIME = new PetType<>("Slime", EntityType.SLIME, Material.SLIME_SPAWN_EGG, PetSlime.class);
    public final static PetType<PetSilverFish> SILVERFISH = new PetType<>("Silverfish", EntityType.SILVERFISH, Material.SILVERFISH_SPAWN_EGG, PetSilverFish.class);
    public final static PetType<PetBlaze> BLAZE = new PetType<>("Blaze", EntityType.BLAZE, Material.BLAZE_SPAWN_EGG, PetBlaze.class);
    public final static PetType<PetCreeper> CREEPER = new PetType<>("Creeper", EntityType.CREEPER, Material.CREEPER_SPAWN_EGG, PetCreeper.class);
    public final static PetType<PetEnderman> ENDERMAN = new PetType<>("Enderman", EntityType.ENDERMAN, Material.ENDERMAN_SPAWN_EGG, PetEnderman.class);
    public final static PetType<PetSkeleton> SKELETON = new PetType<>("Skeleton", EntityType.SKELETON, Material.SKELETON_SPAWN_EGG, PetSkeleton.class);
    public final static PetType<PetZombie> ZOMBIE = new PetType<>("Zombie", EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG, PetZombie.class);
    public final static PetType<PetCaveSpider> CAVE_SPIDER = new PetType<>("CaveSpider", EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG, PetCaveSpider.class);
    public final static PetType<PetStrider> SPIDER = new PetType<>("Spider", EntityType.SPIDER, Material.SPIDER_SPAWN_EGG, PetStrider.class);
    public final static PetType<PetEndermite> ENDERMITE = new PetType<>("Endermite", EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG, PetEndermite.class);
    public final static PetType<PetGuardian> GUARDIAN = new PetType<>("Guardian", EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG, PetGuardian.class);
    public final static PetType<PetMagmaCube> MAGMA_CUBE = new PetType<>("MagmaCube", EntityType.MAGMA_CUBE, Material.MAGMA_CUBE_SPAWN_EGG, PetMagmaCube.class);
    public final static PetType<PetWitch> WITCH = new PetType<>("Witch", EntityType.WITCH, Material.WITCH_SPAWN_EGG, PetWitch.class);
    public final static PetType<PetHorse> HORSE = new PetType<>("Horse", EntityType.HORSE, Material.HORSE_SPAWN_EGG, PetHorse.class);
    public final static PetType<PetSniffer> SNIFFER = new PetType<>("Sniffer", EntityType.SNIFFER, Material.SNIFFER_SPAWN_EGG, PetSniffer.class);
    public final static PetType<PetFrog> FROG = new PetType<>("Frog", EntityType.FROG, Material.FROG_SPAWN_EGG, PetFrog.class);
    public final static PetType<PetWarden> WARDEN = new PetType<>("Warden", EntityType.WARDEN, Material.WARDEN_SPAWN_EGG, PetWarden.class);
    public final static PetType<PetAllay> ALLAY = new PetType<>("Allay", EntityType.ALLAY, Material.ALLAY_SPAWN_EGG, PetAllay.class);
    public final static PetType<PetTadpole> TADPOLE = new PetType<>("Tadpole", EntityType.TADPOLE, Material.TADPOLE_SPAWN_EGG, PetTadpole.class);
    public final static PetType<PetGoat> GOAT = new PetType<>("Goat", EntityType.GOAT, Material.GOAT_SPAWN_EGG, PetGoat.class);
    public final static PetType<PetAxolotl> AXOLOTL = new PetType<>("Axolotl", EntityType.AXOLOTL, Material.AXOLOTL_SPAWN_EGG, PetAxolotl.class);
    public final static PetType<PetPiglin> PIGLIN = new PetType<>("Piglin", EntityType.PIGLIN, Material.PIGLIN_SPAWN_EGG, PetPiglin.class);
    public final static PetType<PetStrider> STRIDER = new PetType<>("Strider", EntityType.STRIDER, Material.STRIDER_SPAWN_EGG, PetStrider.class);
    public final static PetType<PetHoglin> HOGLIN = new PetType<>("Hoglin", EntityType.HOGLIN, Material.HOGLIN_SPAWN_EGG, PetHoglin.class);
    public final static PetType<PetPiglinBrute> PIGLIN_BRUTE = new PetType<>("PiglinBrute", EntityType.PIGLIN_BRUTE, Material.PIGLIN_BRUTE_SPAWN_EGG, PetPiglinBrute.class);
    public final static PetType<PetZoglin> ZOGLIN = new PetType<>("Zoglin", EntityType.ZOGLIN, Material.ZOGLIN_SPAWN_EGG, PetZoglin.class);
    public final static PetType<PetZombifiedPiglin> ZOMBIFIED_PIGLIN = new PetType<>("ZombifiedPiglin", EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, PetZombifiedPiglin.class);
    public final static PetType<PetBee> BEE = new PetType<>("Bee", EntityType.BEE, Material.BEE_SPAWN_EGG, PetBee.class);
    public final static PetType<PetPanda> PANDA = new PetType<>("Panda", EntityType.PANDA, Material.PANDA_SPAWN_EGG, PetPanda.class);
    public final static PetType<PetFox> FOX = new PetType<>("Fox", EntityType.FOX, Material.FOX_SPAWN_EGG, PetFox.class);
    public final static PetType<PetKitty> KITTY = new PetType<>("Kitty", EntityType.CAT, Material.CAT_SPAWN_EGG, PetKitty.class);
    public final static PetType<PetOcelot> OCELOT = new PetType<>("Ocelot", EntityType.OCELOT, Material.OCELOT_SPAWN_EGG, PetOcelot.class);
    public final static PetType<PetWanderingTrader> WANDERING_TRADER = new PetType<>("WanderingTrader", EntityType.WANDERING_TRADER, Material.WANDERING_TRADER_SPAWN_EGG, PetWanderingTrader.class);
    public final static PetType<PetPillager> PILLAGER = new PetType<>("Pillager", EntityType.PILLAGER, Material.PILLAGER_SPAWN_EGG, PetPillager.class);
    public final static PetType<PetRavager> RAVAGER = new PetType<>("Ravager", EntityType.RAVAGER, Material.RAVAGER_SPAWN_EGG, PetRavager.class);
    public final static PetType<PetCod> COD = new PetType<>("Cod", EntityType.COD, Material.COD_SPAWN_EGG, PetCod.class);
    public final static PetType<PetPufferFish> PUFFER_FISH = new PetType<>("Pufferfish", EntityType.PUFFERFISH, Material.PUFFERFISH_SPAWN_EGG, PetPufferFish.class);
    public final static PetType<PetSalmon> SALMON = new PetType<>("Salmon", EntityType.SALMON, Material.SALMON_SPAWN_EGG, PetSalmon.class);
    public final static PetType<PetTropicalFish> TROPICAL_FISH = new PetType<>("TropicalFish", EntityType.TROPICAL_FISH, Material.TROPICAL_FISH_SPAWN_EGG, PetTropicalFish.class);
    public final static PetType<PetTurtle> TURTLE = new PetType<>("Turtle", EntityType.TURTLE, Material.TURTLE_SPAWN_EGG, PetTurtle.class);
    public final static PetType<PetDolphin> DOLPHIN = new PetType<>("Dolphin", EntityType.DOLPHIN, Material.DOLPHIN_SPAWN_EGG, PetDolphin.class);
    public final static PetType<PetDrowned> DROWNED = new PetType<>("Drowned", EntityType.DROWNED, Material.DROWNED_SPAWN_EGG, PetDrowned.class);
    public final static PetType<PetParrot> PARROT = new PetType<>("Parrot", EntityType.PARROT, Material.PARROT_SPAWN_EGG, PetParrot.class);
    public final static PetType<PetIllusioner> ILLUSIONER = new PetType<>("Illusioner", EntityType.ILLUSIONER, Material.EGG, PetIllusioner.class);
    public final static PetType<PetLlama> LLAMA = new PetType<>("Llama", EntityType.LLAMA, Material.LLAMA_SPAWN_EGG, PetLlama.class);
    public final static PetType<PetVex> VEX = new PetType<>("Vex", EntityType.VEX, Material.VEX_SPAWN_EGG, PetVex.class);
    public final static PetType<PetEvoker> EVOKER = new PetType<>("Evoker", EntityType.EVOKER, Material.EVOKER_SPAWN_EGG, PetEvoker.class);
    public final static PetType<PetVindicator> VINDICATOR = new PetType<>("Vindicator", EntityType.VINDICATOR, Material.VINDICATOR_SPAWN_EGG, PetVindicator.class);
    public final static PetType<PetDonkey> DONKEY = new PetType<>("Donkey", EntityType.DONKEY, Material.DONKEY_SPAWN_EGG, PetDonkey.class);
    public final static PetType<PetMule> MULE = new PetType<>("Mule", EntityType.MULE, Material.MULE_SPAWN_EGG, PetMule.class);
    public final static PetType<PetSkeletonHorse> SKELETON_HORSE = new PetType<>("SkeletonHorse", EntityType.SKELETON_HORSE, Material.SKELETON_HORSE_SPAWN_EGG, PetSkeletonHorse.class);
    public final static PetType<PetZombieHorse> ZOMBIE_HORSE = new PetType<>("ZombieHorse", EntityType.ZOMBIE_HORSE, Material.ZOMBIE_HORSE_SPAWN_EGG, PetZombieHorse.class);
    public final static PetType<PetElderGuardian> ELDER_GUARDIAN = new PetType<>("ElderGuardian", EntityType.ELDER_GUARDIAN, Material.ELDER_GUARDIAN_SPAWN_EGG, PetElderGuardian.class);
    public final static PetType<PetWitherSkeleton> WITHER_SKELETON = new PetType<>("WitherSkeleton", EntityType.WITHER_SKELETON, Material.WITHER_SKELETON_SPAWN_EGG, PetWitherSkeleton.class);
    public final static PetType<PetZombieVillager> ZOMBIE_VILLAGER = new PetType<>("ZombieVillager", EntityType.ZOMBIE_VILLAGER, Material.ZOMBIE_VILLAGER_SPAWN_EGG, PetZombieVillager.class);
    public final static PetType<PetHusk> HUSK = new PetType<>("Husk", EntityType.HUSK, Material.HUSK_SPAWN_EGG, PetHusk.class);
    public final static PetType<PetStray> STRAY = new PetType<>("Stray", EntityType.STRAY, Material.STRAY_SPAWN_EGG, PetStray.class);
    public final static PetType<PetPolarBear> POLAR_BEAR = new PetType<>("PolarBear", EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG, PetPolarBear.class);

    private final String name;

    private final EntityType type;

    private final Material egg;

    private final Class<E> petClass;

    public PetType(String name, EntityType type, Material egg, Class<E> petClass) {
        this.name = name;
        this.type = type;
        this.egg = egg;
        this.petClass = petClass;
    }

    public String getName() {
        return this.name;
    }

    public EntityType getType() {
        return type;
    }

    public Material getEgg() {
        return egg;
    }

    public Class<E> getPet() {
        return petClass;
    }
}