package me.a8kj.setskin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.a8kj.setskin.entity.SkinManager;
import me.a8kj.setskin.manager.SimpleSkinManager;
import me.a8kj.setskin.utils.SimpleUtils;
import net.md_5.bungee.api.ChatColor;

public class UnNickCommand implements CommandExecutor {

    private final SkinManager simpleSkinManager = new SimpleSkinManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This Command to player usage only!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("skinplugin.unnick.use")) {
            player.sendMessage(ChatColor.RED
                    + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
            return false;
        }
        if (args.length != 0) {
            player.sendMessage("Usage: /unnick");
            return false;
        } else {
            if (!simpleSkinManager.hasNick(player)) {
                player.sendMessage("You don't have nick !");
                return false;
            } else {
                SimpleUtils.PlayerUtils.sendActionbar(player, "&A&LSUCCESS!");
                simpleSkinManager.removeSkin(player);
            }

        }
        return true;
    }

}
