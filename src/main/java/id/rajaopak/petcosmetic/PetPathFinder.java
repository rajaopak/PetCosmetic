package id.rajaopak.petcosmetic;

import dev.rajaopak.opaklibrary.libs.ChatUtil;
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
    //private final boolean useEyeLocation;
    /*private double yOffset = 0.0;
    private boolean yOffsetBool = false;*/

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
        Location loc = player.getEyeLocation();

        if (isFlying) {
            double xOffset = Math.cos(Math.toRadians(player.getLocation().getYaw() - 180)) * distance;
            double zOffset = Math.sin(Math.toRadians(player.getLocation().getYaw() - 180)) * distance;

/*        double oscillationAmplitude = 0.1; // Adjust this value for the desired amplitude
        double oscillationFrequency = 0.05; // Adjust this value for the desired frequency

        double pitchOffset = Math.sin(oscillationFrequency * System.currentTimeMillis()) * oscillationAmplitude;*/

            loc.add(xOffset, height, zOffset);

            // Entity will be respawned by Pet instance
            if (entity.getWorld() != loc.getWorld()) {
                return;
            }

            entity.setRotation(loc.getYaw(), loc.getPitch());
            Vector from = entity.getLocation().toVector();
            Vector to = loc.toVector();

            Vector vector = to.subtract(from);
            entity.setVelocity(vector);

            // Old method, im not remove it because maybe this can be for future investigation
//        double yOffset = Math.sin(Math.toRadians(player.getLocation().getYaw() - 180)) * 0.2;
//
//        // Entity will be respawned by Pet instance
//        if (entity.getWorld() != loc.getWorld()) {
//            return;
//        }
//
//        entity.setRotation(loc.getYaw(), loc.getPitch());
//        Vector from = entity.getLocation().toVector();
//        Vector to = loc.toVector();
//
//        double x = Math.cos(Math.toRadians(player.getLocation().getYaw() - 180)) * 1.5;
//        double z = Math.sin(Math.toRadians(player.getLocation().getYaw() - 180)) * 1.5;
//
//        if (!yOffsetBool) {
//            yOffset += 0.2;
//            yOffsetBool = true;
//        } else {
//            yOffset -= 0.2;
//            yOffsetBool = false;
//        }
//
//        double newY = Math.sin(yOffset) * 0.2;
//        to.add(new Vector(x, newY, z));
//        Vector vector = to.subtract(from);
//        entity.setVelocity(vector);

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
                brain.getController().moveTo(loc.add(0, 1, 0), speed);
                brain.getController().jump();
            }
        } else {

            if (entity.getWorld() != loc.getWorld()) {
                return;
            }

            if (entity.getLocation().distanceSquared(loc) > 10 * 10) {
                entity.teleport(loc);
                brain.getController().moveTo(loc, speed);
                return;
            }

            if (loc.distanceSquared(entity.getLocation()) > 3 * 3) {
                brain.getController().moveTo(loc, speed);
                brain.getController().jump();
            }
        }
    }
}
