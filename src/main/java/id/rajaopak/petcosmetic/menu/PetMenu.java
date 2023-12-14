package id.rajaopak.petcosmetic.menu;

import dev.rajaopak.opaklibrary.inventory.GuiBuilder;
import dev.rajaopak.opaklibrary.libs.ChatSession;
import dev.rajaopak.opaklibrary.libs.ChatUtil;
import dev.rajaopak.opaklibrary.libs.ItemBuilder;
import id.rajaopak.petcosmetic.PetCosmetic;
import id.rajaopak.petcosmetic.PetType;
import id.rajaopak.petcosmetic.pets.Pet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PetMenu {

    private final Player player;

    private GuiBuilder gui;

    public PetMenu(Player player) {
        this.player = player;
    }

    public void openMenu(int page) {
        this.gui = new GuiBuilder(54, "&2Pet's Cosmetic");

        gui.setItems(gui.getBorders(), ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE).build());

        List<PetType<?>> list = PetCosmetic.getInstance().getPetManager().getPetList();
        int maxPerPage = 28;
        int size = list.size();

        if (page > 0 && size < (page * maxPerPage) + 1) {
            openMenu(page - 1);
            return;
        }

        int max = (page * maxPerPage) + maxPerPage;
        int min = page * maxPerPage;

        if (max > size) {
            max = max - (max - size);
        }

        List<PetType<?>> finalList = list.subList(min, max);

        finalList.forEach(petType -> {
            ItemBuilder item = ItemBuilder.from(petType.getEgg()).setName("&e" + petType.getName()).setFlags(ItemFlag.HIDE_ATTRIBUTES);

            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                if (PetCosmetic.getInstance().getPetManager().getPet(player).getClass().equals(petType.getPet())) {
                    item.setGlowing();
                }
            }

            gui.addItem(item.build(), inventoryClickEvent -> {
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

                gui.close(player);

                if (pet == null) {
                    ChatUtil.sendMessage(player, List.of("&cSomething went wrong while trying to spawn pet!", "&cPlease contact administrator."));
                    return;
                }

                if (pet.spawnPet()) {
                    pet.scheduleTask();
                    PetCosmetic.getInstance().getPetManager().addPet(player, pet);
                }
            });
        });

        gui.setItem(45, ItemBuilder.from(Material.IRON_BARS).setName("&cDeSpawn Pet").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> {
            gui.close(player);

            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                PetCosmetic.getInstance().getPetManager().removePet(player);
            } else {
                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
            }
        });

        gui.setItem(46, ItemBuilder.from(Material.GLOW_INK_SAC).setName("&bToggle Glow Pet").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> {
            gui.close(player);

            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                PetCosmetic.getInstance().getPetManager().getPet(player).getMob().setGlowing(!PetCosmetic.getInstance().getPetManager().getPet(player).getMob().isGlowing());
            } else {
                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
            }
        });

        if (page > 0) gui.setItem(48, ItemBuilder.from(Material.ARROW).setName("&7Previous page").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> openMenu(page - 1));
        if (!(size < ((page + 1) * maxPerPage))) gui.setItem(50, ItemBuilder.from(Material.ARROW).setName("&7Next page").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> openMenu(page + 1));

        gui.setItem(49, ItemBuilder.from(Material.BARRIER).setName("&cClose").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> gui.close(player));

        gui.setItem(52, ItemBuilder.from(Material.ARMOR_STAND).setName("&eSet Pet to Adult").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> {
            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                gui.close(player);
                if (!PetCosmetic.getInstance().getPetManager().getPet(player).isBaby()) {
                    ChatUtil.sendMessage(player, "&cYour pet is already Adult!");
                    return;
                }

                PetCosmetic.getInstance().getPetManager().getPet(player).setBaby(false);
            } else {
                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
            }
        });

        gui.setItem(53, ItemBuilder.from(Material.TOTEM_OF_UNDYING).setName("&eSet Pet to Baby").setFlags(ItemFlag.HIDE_ATTRIBUTES).build(), inventoryClickEvent -> {
            if (PetCosmetic.getInstance().getPetManager().hasPet(player)) {
                gui.close(player);
                if (PetCosmetic.getInstance().getPetManager().getPet(player).isBaby()) {
                    ChatUtil.sendMessage(player, "&cYour pet is already baby!");
                    return;
                }

                PetCosmetic.getInstance().getPetManager().getPet(player).setBaby(true);
            } else {
                ChatUtil.sendMessage(player, "&cYou don't have any selected pet!");
            }
        });

        gui.open(player);
    }
}

