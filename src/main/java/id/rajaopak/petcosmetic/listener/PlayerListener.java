package id.rajaopak.petcosmetic.listener;

import id.rajaopak.petcosmetic.PetCosmetic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PetCosmetic.getInstance().getPetManager().removePet(event.getPlayer());
    }

}
