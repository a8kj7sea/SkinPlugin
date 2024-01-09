package me.a8kj.setskin.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.a8kj.setskin.entity.SkinManager;
import me.a8kj.setskin.utils.AndGateHelper;

public class SimpleSkinManager implements SkinManager {

    @Override
    public void setSkin(Player player, String skin_name) {
        String command = "/skin set " + skin_name + " " + player.getName();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        updateSkin(player);
        player.setDisplayName(skin_name);
        player.setPlayerListName(skin_name);
    }

    @Override
    public void removeSkin(Player player) {
        String command = "/skin clear " + player.getName();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        updateSkin(player);
        player.setDisplayName(player.getName());
        player.setPlayerListName(player.getName());
    }

    @Override
    public void updateSkin(Player player) {
        String command = "/skin update " + player.getName();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    @Override
    public String getNick(Player player) {

        if (!hasNick(player)) {
            return null;
        }
        return player.getDisplayName();
    }

    @Override
    public boolean hasNick(Player player) {
        AndGateHelper result = new AndGateHelper();

        result.append(player.getDisplayName() != player.getName());
        result.append(player.getPlayerListName() != player.getName());
        return result.toBoolean();
    }
}
