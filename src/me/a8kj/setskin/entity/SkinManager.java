package me.a8kj.setskin.entity;

import org.bukkit.entity.Player;

public interface SkinManager {


    String getNick(Player player);
    boolean hasNick(Player player);
    void setSkin(Player player , String skin_name);
    void removeSkin(Player player);
    void updateSkin(Player player);

}
