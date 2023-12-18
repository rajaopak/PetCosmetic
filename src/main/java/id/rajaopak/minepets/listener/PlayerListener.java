package id.rajaopak.minepets.listener;

import id.rajaopak.minepets.MinePets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        MinePets.getInstance().getPlayerManager().loadPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        MinePets.getInstance().getPetManager().removePet(event.getPlayer());
        MinePets.getInstance().getPlayerManager().unloadPlayer(event.getPlayer());
    }

}
