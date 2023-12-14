package id.rajaopak.petcosmetic.command;

import dev.rajaopak.opaklibrary.commands.BaseCommand;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import id.rajaopak.petcosmetic.PetCosmetic;
import id.rajaopak.petcosmetic.PetType;
import id.rajaopak.petcosmetic.menu.PetMenu;
import id.rajaopak.petcosmetic.pets.Pet;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainCommand extends BaseCommand {

    public MainCommand(JavaPlugin plugin) {
        super(plugin, "pet", List.of("petcosmetic", "pcosmetic", "petc"), "petcosmetic.use",
                sender -> {
                    if (!(sender instanceof Player player)) {
                        ChatUtil.sendMessage(sender, "&cOnly Player can execute this command!");
                        return;
                    }

                    PetMenu menu = new PetMenu(player);

                    menu.openMenu(0);
                },
                sender -> ChatUtil.sendMessage(sender, "&cYou don't have permission to do this!"),
                sender -> ChatUtil.sendMessage(sender, "&cNo sub command was found!"),
                (sender, args) -> {
                    if (args[0].equalsIgnoreCase("summon")) {
                        if (args.length == 4) {
                            String mob = args[1];
                            String playerName = args[2];
                            String isBaby = args[3];

                            Player player = Bukkit.getPlayer(playerName);

                            if (player == null) {
                                ChatUtil.sendMessage(sender, "&cPlayer not found!");
                                return;
                            }

                            PetCosmetic.getInstance().getPetManager().getPetList().forEach(petType -> {
                                if (petType.getName().equalsIgnoreCase(mob)) {
                                    if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                                        PetCosmetic.getInstance().getPetManager().removePet(player);
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
                                        PetCosmetic.getInstance().getPetManager().addPet(player, pet);
                                    }
                                }
                            });
                        } else {
                            ChatUtil.sendMessage(sender, "&6Usage: /pet summon <type> <player> <adult/baby>");
                        }
                    } else if (args[0].equalsIgnoreCase("despawn")) {
                        if (args.length == 2) {
                            String playerName = args[1];

                            Player player = Bukkit.getPlayer(playerName);

                            if (player == null) {
                                ChatUtil.sendMessage(sender, "&cPlayer not found!");
                                return;
                            }

                            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                                PetCosmetic.getInstance().getPetManager().removePet(player);
                            } else {
                                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
                            }
                        } else {
                            ChatUtil.sendMessage(sender, "&6Usage: /pet despawn <player>");
                        }
                    } else if (args[0].equalsIgnoreCase("glow")) {
                        if (!(sender instanceof Player player)) {
                            ChatUtil.sendMessage(sender, "&cOnly player can execute this command!");
                            return;
                        }

                        if (args.length == 1) {
                            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                                PetCosmetic.getInstance().getPetManager().getPet(player).getMob().setGlowing(!PetCosmetic.getInstance().getPetManager().getPet(player).getMob().isGlowing());
                            } else {
                                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
                            }
                        } else {
                            ChatUtil.sendMessage(sender, "&6Usage: /pet glow");
                        }
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if (args.length == 1) {
                            PetCosmetic.getInstance().getConfigFile().reloadConfig();
                            PetCosmetic.getInstance().getPetManager().getPlayerPet().values().forEach(pet -> pet.renameMob(PetCosmetic.getInstance().getConfigFile().getConfig().getString("default-pet-nametag")));
                            ChatUtil.sendMessage(sender, "&cWarning: reloading the plugin may not effect the plugin at all. Consider to restart the server!");
                            ChatUtil.sendMessage(sender, "&aSuccessfully reload the plugin!");
                        } else {
                            ChatUtil.sendMessage(sender, "&6Usage: /pet reload");
                        }
                    }
                }, null);

//        addTabComplete(1, List.of("summon", "despawn"), "petcosmetic.use.summon");
//        addTabComplete(2, PetCosmetic.getInstance().getPetManager().getPetList().stream().map(PetType::getName).collect(Collectors.toList()), "petcosmetic.use.summon");
//        addTabComplete(3, Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()), "petcosmetic.use.summon");
//        addTabComplete(4, List.of("baby", "adult"), "petcosmetic.use.summon");
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (sender.hasPermission("petcosmetic.use.summon") || sender.hasPermission("petcosmetic.use.despawn")) {
                return List.of("summon", "despawn", "glow", "reload").stream().filter(s -> s.toLowerCase().startsWith(args[0].toLowerCase())).sorted().collect(Collectors.toList());
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("summon")) {
                if (sender.hasPermission("petcosmetic.use.summon")) {
                    return PetCosmetic.getInstance().getPetManager().getPetList().stream().map(PetType::getName).filter(s -> s.toLowerCase().startsWith(args[1].toLowerCase())).sorted().collect(Collectors.toList());
                }
            }
            if (args[0].equalsIgnoreCase("despawn")) {
                if (sender.hasPermission("petcosmetic.use.despawn")) {
                    return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(s -> s.toLowerCase().startsWith(args[1].toLowerCase())).sorted().collect(Collectors.toList());
                }
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("summon")) {
                if (sender.hasPermission("petcosmetic.use.summon")) {
                    return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(s -> s.toLowerCase().startsWith(args[2].toLowerCase())).sorted().collect(Collectors.toList());
                }
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("summon")) {
                if (sender.hasPermission("petcosmetic.use.summon")) {
                    return List.of("baby", "adult").stream().filter(s -> s.toLowerCase().startsWith(args[3].toLowerCase())).sorted().collect(Collectors.toList());
                }
            }
        }

        if (args[0].equalsIgnoreCase("glow")) {
            return Collections.emptyList();
        }

        if (args[0].equalsIgnoreCase("reload")) {
            return Collections.emptyList();
        }

        return super.tabComplete(sender, label, args);
    }
}
