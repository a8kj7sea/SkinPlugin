package me.a8kj.setskin.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.a8kj.setskin.entity.SkinManager;
import me.a8kj.setskin.manager.SimpleSkinManager;
import me.a8kj.setskin.utils.SimpleUtils;

public class UnNickCommand extends Command {

    private final SkinManager simpleSkinManager = new SimpleSkinManager();

    public UnNickCommand() {
        super("unnick", "to remove your nick", "/unnick", Arrays.asList("removenick", "nonick", "unn"));
        setPermission("skinplugin.unnick.use");
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
