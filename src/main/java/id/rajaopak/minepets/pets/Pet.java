package id.rajaopak.minepets.pets;

import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import id.rajaopak.minepets.PetPathFinder;
import id.rajaopak.minepets.PetType;
import id.rajaopak.minepets.util.VanishChecker;
import me.gamercoder215.mobchip.EntityBrain;
import me.gamercoder215.mobchip.ai.goal.PathfinderLookAtEntity;
import me.gamercoder215.mobchip.bukkit.BukkitBrain;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public abstract class Pet extends BukkitRunnable implements Listener {

    protected final Player player;
    protected final PetType<?> petType;
    protected ArmorStand armorStand;

    protected Mob mob;

    protected String name = null;

    protected boolean isBaby = false;
    protected boolean isFlying = false;

    public Pet(Player player, PetType<?> petType) {
        this.player = player;
        this.petType = petType;
    }

    public Mob getMob() {
        return mob;
    }

    public boolean spawnPet() {
        if (!player.hasPermission("minepets.pet." + petType.getName().toLowerCase())) {
            ChatUtil.sendMessage(player, "&cYou don't own this pet!");
            return false;
        }

        mob = spawnEntity();

        if (mob instanceof Ageable ageable) {
            if (isBaby) {
                ageable.setBaby();
            } else {
                ageable.setAdult();
            }
            ageable.setAgeLock(true);
        }

        if (mob instanceof Tameable tameable) {
            tameable.setTamed(true);
            tameable.setOwner(player);
        }

        setupNameTag();

        clearPathfinding();

        mob.getEquipment().clear();
        mob.setRemoveWhenFarAway(false);
        mob.setInvulnerable(true);
        mob.setCollidable(false);

        mob.setMetadata("Pet", new FixedMetadataValue(MinePets.getInstance(), "MinePets"));

        Bukkit.getServer().getPluginManager().registerEvents(this, MinePets.getInstance());

        setupMob();
        return true;
    }

    private Mob spawnEntity() {
        if (isFlying()) {
            return (Mob) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 0), petType.getType());
        }
        return (Mob) player.getWorld().spawnEntity(player.getLocation(), petType.getType());
    }

    public void scheduleTask() {
        runTaskTimer(MinePets.getInstance(), 0, 1);
    }

    public void clearPathfinding() {
        EntityBrain ai = BukkitBrain.getBrain(mob);

        ai.getGoalAI().clear();
        ai.getTargetAI().clear();
        ai.getScheduleManager().clear();

        ai.getGoalAI().put(new PetPathFinder(mob, player, isFlying(), getDistance(), getHeight()), 0);
        ai.getGoalAI().put(new PathfinderLookAtEntity<>(mob, Player.class), 1);
    }

    public void setupNameTag() {
        if (name == null) name = ChatUtil.color(MinePets.getInstance().getConfigFile().getConfig().getString("default-pet-nametag").replace("%player%", player.getName()));
        mob.setCustomName(name);
        mob.setCustomNameVisible(true);

//        if (!useArmorStandNameTag()) {
//            return;
//        }
//
//        if (armorStand != null) {
//            armorStand.remove();
//        }
//
//        armorStand = (ArmorStand) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.ARMOR_STAND);
//        armorStand.setVisible(false);
//        armorStand.setSmall(true);
//        armorStand.setMarker(useMarkerArmorStand());
//        armorStand.setCustomNameVisible(true);
//        FixedMetadataValue metadataValue = new FixedMetadataValue(PetCosmetic.getInstance(), "Pet_ArmorStand");
//        armorStand.setMetadata("Pet_ArmorStand", metadataValue);
//        mob.addPassenger(armorStand);
//
//        armorStand.setCustomName(ChatUtil.color("&a" + player.getName() + "'s Pet"));
    }

    public void renameMob(String name) {
        this.name = ChatUtil.color(name.replace("%player%", player.getName()));
        mob.setCustomNameVisible(false);
        mob.setCustomName(this.name);
        mob.setCustomNameVisible(true);
    }

    public boolean useArmorStandNameTag() {
        return true;
    }

    public boolean useMarkerArmorStand() {
        return true;
    }

    public double getDistance() {
        return MinePets.getInstance().getConfigFile().getConfig().getDouble("pet-distance");
    }

    public double getHeight() {
        return MinePets.getInstance().getConfigFile().getConfig().getDouble("pet-height");
    }

    @Override
    public void run() {
        if (mob != null && !mob.isValid()) {
            clear();
            return;
        }

        if (!player.isOnline()) {
            clear();
            return;
        }

        if (mob.getWorld() != player.getWorld()) {
            mob.remove();
            spawnPet();
        }

        if (VanishChecker.isVanished(player)) {
            mob.setInvisible(true);
        } else {
            if (mob.isInvisible()) mob.setInvisible(false);
        }
    }

    public boolean isBaby() {
        return isBaby;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setBaby(boolean baby) {
        this.isBaby = baby;
        if (mob != null && mob.isValid()) {
            mob.remove();
            spawnPet();
        }
    }

    public void setFlying(boolean flying) {
        this.isFlying = flying;
    }

    public void clear() {
        if (armorStand != null) {
            armorStand.remove();
        }

        if (mob != null) {
            mob.remove();
        }

        HandlerList.unregisterAll(this);

        try {
            cancel();
        } catch (IllegalStateException ignored) {
        }
    }

    @EventHandler
    public void onInteract(@NotNull PlayerInteractEntityEvent event) {
        if (event.getRightClicked() == mob) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(@NotNull EntityDamageEvent event) {
        if (event.getEntity() == mob) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerTeleport(@NotNull PlayerTeleportEvent event) {
        if (event.getPlayer() == player) mob.teleport(player.getLocation());
    }

    @EventHandler
    public void onCombust(@NotNull EntityCombustEvent event) {
        if (event.getEntity() == mob) event.setCancelled(true);
    }

    protected void setupMob() {
    }
}
