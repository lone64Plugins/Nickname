package io.starweight.nickname;

import io.starweight.nickname.command.ChangeCommand;
import io.starweight.nickname.command.ResetCommand;
import io.starweight.nickname.listener.PlayerListener;
import io.starweight.nickname.member.Member;
import org.bukkit.plugin.java.JavaPlugin;
import org.lone64.nametags.api.NametagsAPI;

public final class Nickname extends JavaPlugin {

    private static Nickname plugin;

    @Override
    public void onLoad() {
        plugin = this;

        new Member().init();
        NametagsAPI.getInstance().setPlugin(this);
    }

    @Override
    public void onEnable() {
        getCommand("닉네임변경").setExecutor(new ChangeCommand());
        getCommand("닉네임초기화").setExecutor(new ResetCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static Nickname getPlugin() {
        return plugin;
    }

}
