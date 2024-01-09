package me.a8kj.setskin.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.a8kj.setskin.entity.SkinManager;
import me.a8kj.setskin.manager.SimpleSkinManager;
import me.a8kj.setskin.utils.SimpleUtils;

public class NickCommand extends Command {

    private final SkinManager simpleSkinManager = new SimpleSkinManager();

    public NickCommand() {
        super("nick", "to set nick with skin", "/nick <nick-name>", Arrays.asList("nick" , "setnick" , "addnick"));
        setPermission("skinplugin.nick.use");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This Command to player usage only!");
            return false;
        }

        Player player = (Player) sender;

        if (!testPermission(player)) {
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