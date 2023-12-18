package id.rajaopak.minepets;

import me.gamercoder215.mobchip.EntityBrain;
import me.gamercoder215.mobchip.ai.goal.CustomPathfinder;
import me.gamercoder215.mobchip.bukkit.BukkitBrain;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class PetPathFinder extends CustomPathfinder {

    private final Player player;
    private final EntityBrain brain;
    private final boolean isFlying;
    private final double distance;
    private final double height;
    private final double speed;

    /**
     * Constructs a CustomPathfinder.
     *
     * @param mob Mob to use
     */
    public PetPathFinder(@NotNull Mob mob, Player target, boolean isFlying, double distance, double height) {
        super(mob);
        this.player = target;
        this.brain = BukkitBrain.getBrain(mob);
        this.isFlying = isFlying;
        this.distance = distance;
        this.height = height;
        this.speed = 1.50;
        //this.useEyeLocation = useEyeLocation;
    }

    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public @NotNull PathfinderFlag[] getFlags() {
        return new PathfinderFlag[]{PathfinderFlag.MOVEMENT, PathfinderFlag.JUMPING};
    }

    @Override
    public void start() {

    }

    @Override
    public void tick() {
        Location loc;

        if (isFlying) {
            loc = player.getEyeLocation();

            double xOffset = Math.cos(Math.toRadians(player.getLocation().getYaw() - 180)) * distance;
            double zOffset = Math.sin(Math.toRadians(player.getLocation().getYaw() - 180)) * distance;

            loc.add(xOffset, height, zOffset);

            if (entity.getWorld() != loc.getWorld()) {
                return;
            }

            entity.setRotation(loc.getYaw(), loc.getPitch());
            Vector from = entity.getLocation().toVector();
            Vector to = loc.toVector();

            Vector vector = to.subtract(from);
            entity.setVelocity(vector);

            if (entity.getLocation().distanceSquared(loc) > 10 * 10) {
                entity.teleport(loc);
                brain.getController().moveTo(loc, speed);
                //brain.getController().jump();
                return;
            }

            if (loc.distanceSquared(entity.getLocation()) > 3 * 3) {
                brain.getController().moveTo(loc.add(0, 1, 0), speed);
                //brain.getController().jump();
            }
        } else {
            loc = player.getLocation();

            if (entity.getWorld() != loc.getWorld()) {
                return;
            }

            if (entity.getLocation().distanceSquared(loc) > 10 * 10) {
                entity.teleport(loc);
                brain.getController().moveTo(loc, speed);
                brain.getController().jump();
                return;
            }

            // Slime or magma cube
            if (entity instanceof Slime) {
                Location deltaLoc = entity.getLocation().subtract(loc);
                double direction = -Math.atan2(deltaLoc.getX(), deltaLoc.getZ());
                float degrees = (float) (Math.toDegrees(direction) + 180);
                brain.getBody().setRotation(degrees, 0);
            }

            if (loc.distanceSquared(entity.getLocation()) > 3 * 3) {
                brain.getController().moveTo(loc, speed);
                brain.getController().jump();
            }
        }
    }
}
