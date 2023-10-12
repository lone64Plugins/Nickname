package io.starweight.nickname.listener;

import io.starweight.nickname.member.Member;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.lone64.nametags.api.NametagsAPI;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!new Member().isUUID(player.getUniqueId())) return;

        String newName = new Member(player.getUniqueId()).getNewName();
        NametagsAPI.getInstance().changeTag(player, newName);
    }

}