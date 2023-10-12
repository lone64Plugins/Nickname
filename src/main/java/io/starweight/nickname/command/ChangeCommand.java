package io.starweight.nickname.command;

import io.slib.library.util.ColorUtil;
import io.starweight.nickname.member.Member;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.lone64.nametags.api.NametagsAPI;
import org.lone64.nametags.api.data.NameData;

public class ChangeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof ConsoleCommandSender) return true;

        Player player = (Player) sender;
        if (!player.hasPermission("nickname.admin")) return true;
        if (args.length < 1) {
            player.sendMessage(ColorUtil.getColor("&a[닉네임] &c설정할 플레이어를 선택하여 주세요."));
            return true;
        } else if (args.length < 2) {
            player.sendMessage(ColorUtil.getColor("&a[닉네임] &c설정할 닉네임을 입력하여 주세요."));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ColorUtil.getColor("&a[닉네임] &c오프라인 상태인 플레이어입니다."));
            return true;
        } else if (new Member().isUUID(target.getUniqueId())) {
            player.sendMessage(ColorUtil.getColor("&a[닉네임] &c이미 닉네임을 변경했습니다."));
            return true;
        }

        String name = args[1];

        NameData data = NametagsAPI.getInstance().changeTag(target, name);
        new Member().addMember(data.getUUID(), data.getOldName(), data.getNewName());
        player.sendMessage(ColorUtil.getColor("&a[닉네임] &e" + name + "&f으로 변경되었습니다!"));
        return false;
    }

}