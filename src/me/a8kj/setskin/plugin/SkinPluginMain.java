package me.a8kj.setskin.plugin;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.a8kj.setskin.commands.NickCommand;
import me.a8kj.setskin.commands.UnNickCommand;

public class SkinPluginMain extends JavaPlugin {

    private @Getter static SkinPlugin skinPlugin;
    private boolean running = false;

    @Override
    public void onEnable() {

        if (!SkinPlugin.found("SkinsRestorer")) {
            this.getLogger().log(Level.SEVERE,
                    "Faild to load `SkinPlugin` please make sure you have install `SkinsRestorer` [not found]!");
            SkinPlugin.shutdown(this);
            return;
        } else {
            running = true;
        }

        if (running) {
            skinPlugin = new SkinPlugin(this, this.getLogger());
            init();
            this.getLogger().info("Plugin started successfully !");
        }
    }

    private void init() {
        //skinPlugin.addConfiguration("messages", new Configuration("messages.yml", this, true));
        skinPlugin.addCommand(new NickCommand());
        skinPlugin.addCommand(new UnNickCommand());
        skinPlugin.onEnable();
    }

    @Override
    public void onDisable() {
        if (running)
            skinPlugin.onDisable();
        running = false;
    }

}
