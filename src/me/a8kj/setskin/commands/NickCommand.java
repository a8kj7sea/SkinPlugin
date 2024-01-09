package me.a8kj.setskin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.a8kj.setskin.entity.SkinManager;
import me.a8kj.setskin.manager.SimpleSkinManager;
import me.a8kj.setskin.utils.SimpleUtils;
import net.md_5.bungee.api.ChatColor;

public class NickCommand implements CommandExecutor {

    private final SkinManager simpleSkinManager = new SimpleSkinManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This Command to player usage only!");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("skinplugin.nick.use")) {
            player.sendMessage(ChatColor.RED
                    + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("You must choose a name. Usage: /nick <newName>");
            return false;
        } else {

            String nickname = args[0];
            if (nickname == null) {
                player.sendMessage("You must choose a name. Usage: /nick <newName>");
                return false;
            } else {
                SimpleUtils.PlayerUtils.sendActionbar(player, "&A&LSUCCESS! &eYour Nick is &6&l" + nickname);
                simpleSkinManager.setSkin(player, nickname);
            }
        }

        return true;
    }

}