package id.rajaopak.minepets.command.subcommand;

import dev.rajaopak.opaklibrary.commands.SubCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.minepets.MinePets;
import id.rajaopak.minepets.PetType;
import id.rajaopak.minepets.pets.Pet;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReleaseCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "release";
    }

    @Override
    public @Nullable String getUsage() {
        return "release <pet>";
    }

    @Override
    public @Nullable String getPermission() {
        return "minepets.use.release";
    }

    @Override
    public @Nullable List<String> parseTabCompletions(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 1) {
            return MinePets.getInstance().getPetManager().getPetList().stream().map(PetType::getName).filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase())).sorted().collect(Collectors.toList());
        }

        if (args.length == 2) {
            if (sender.hasPermission("minepets.use.release.others")) {
                return plugin.getServer().getOnlinePlayers().stream().map(Player::getName).sorted().filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
            }
        }

        if (args.length == 3) {
            if (sender.hasPermission("minepets.use.release.others")) {
                return List.of("baby", "adult").stream().filter(s -> s.toLowerCase().startsWith(args[2].toLowerCase())).sorted().collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public void execute(JavaPlugin plugin, CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (!(sender instanceof Player player)) {
                ChatUtil.sendMessage(sender, "&cOnly Player can execute this command!");
                return;
            }

            String mob = args[0];

            MinePets.getInstance().getPetManager().getPetList().forEach(petType -> {
                if (petType.getName().equalsIgnoreCase(mob)) {
                    if (MinePets.getInstance().getPetManager().hasPet(player)) {
                        MinePets.getInstance().getPetManager().removePet(player);
                    }

                    Pet pet = null;

                    Constructor<?>[] cons = petType.getPet().getConstructors();

                    try {
                        for (Constructor<?> constructor : cons) {
                            if (constructor.getParameterTypes().length == 2 && constructor.getParameterTypes()[0].isAssignableFrom(Player.class) &&
                                    constructor.getParameterTypes()[1].isAssignableFrom(PetType.class)) {
                                pet = (Pet) constructor.newInstance(player, petType);
                            }
                        }
                    } catch (InstantiationException | IllegalAccessException |
                             InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    if (pet == null) {
                        ChatUtil.sendMessage(player, List.of("&cSomething went wrong while trying to spawn pet!", "&cPlease contact administrator."));
                        return;
                    }

                    if (pet.spawnPet()) {
                        pet.scheduleTask();
                        MinePets.getInstance().getPetManager().addPet(player, pet);
                    }
                }
            });
        } else if (args.length == 2 || args.length == 3) {
            if (!sender.hasPermission("minepets.use.release.others")) {
                ChatUtil.sendMessage(sender, "&cYou don't have permission to do this!");
                return;
            }

            String mob = args[0];
            String playerName = args[1];
            String isBaby = args[2];

            Player player = Bukkit.getPlayer(playerName);

            if (player == null) {
                ChatUtil.sendMessage(sender, "&cPlayer not found!");
                return;
            }

            MinePets.getInstance().getPetManager().getPetList().forEach(petType -> {
                if (petType.getName().equalsIgnoreCase(mob)) {
                    if (MinePets.getInstance().getPetManager().hasPet(player)) {
                        MinePets.getInstance().getPetManager().removePet(player);
                    }

                    Pet pet = null;

                    Constructor<?>[] cons = petType.getPet().getConstructors();

                    try {
                        for (Constructor<?> constructor : cons) {
                            if (constructor.getParameterTypes().length == 2 && constructor.getParameterTypes()[0].isAssignableFrom(Player.class) &&
                                    constructor.getParameterTypes()[1].isAssignableFrom(PetType.class)) {
                                pet = (Pet) constructor.newInstance(player, petType);
                            }
                        }
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }

                    if (pet == null) {
                        ChatUtil.sendMessage(player, List.of("&cSomething went wrong while trying to spawn pet!", "&cPlease contact administrator."));
                        return;
                    }

                    if (isBaby != null) {
                        if (isBaby.equalsIgnoreCase("baby")) {
                            pet.setBaby(true);
                        } else if (isBaby.equalsIgnoreCase("adult")) {
                            pet.setBaby(false);
                        } else {
                            ChatUtil.sendMessage(sender, "&cUnknown age! Pet will using adult age.");
                        }
                    }

                    if (pet.spawnPet()) {
                        pet.scheduleTask();
                        MinePets.getInstance().getPetManager().addPet(player, pet);
                    }
                }
            });
        } else {
            ChatUtil.sendMessage(sender, "&6Usage: /pet release <pet> <player> <adult/baby>");
        }
    }
}
