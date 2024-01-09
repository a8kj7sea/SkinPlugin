package me.a8kj.setskin.plugin;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.Data;
import me.a8kj.setskin.entity.Configuration;

@Data
public class SkinPlugin {

    private final JavaPlugin javaPlugin;
    private final Logger logger;

    private final Set<Listener> listeners = Sets.newHashSet();
    private final Map<String, CommandExecutor> commands = Maps.newHashMap();

    private final Map<String, Configuration> configurations = Maps.newHashMap();

    public SkinPlugin(final JavaPlugin javaPlugin, Logger logger) {
        this.javaPlugin = javaPlugin;
        this.logger = logger;
    }

    public void onEnable() {
        if (!listeners.isEmpty()) {
            this.logger.info("Listeners register process started..");
            registerListeners(this.javaPlugin);
        }

        if (!commands.isEmpty()) {
            this.logger.info("Commands register process started..");
            registerCommands();
        }

        if (!configurations.isEmpty()) {
            this.logger.info("Configurations register process started..");
            registerConfiguraions();
        }
    }

    public void onDisable() {
        destory();
    }

    private void destory() {
        listeners.clear();
        commands.clear();
        configurations.clear();
        System.gc();
    }

    @Deprecated
    private void registerConfiguraions() {
        // todo: register logic (Deprecated)
    }

    private void registerListeners(JavaPlugin javaPlugin) {
        for (Listener listener : listeners) {
            javaPlugin.getServer().getPluginManager().registerEvents(listener, javaPlugin);
            this.logger.info("[Listeners] Registerd " + listener.getClass().getSimpleName() + " Successfully !");
        }
    }

    private void registerCommands() {
        this.commands.forEach((name, command) -> {

            this.javaPlugin.getCommand(name).setExecutor(command);
            this.logger.info("[Commands] Registerd " + command.getClass().getSimpleName() + " Successfully !");
        });
    }

    public void addCommand(String command_name, CommandExecutor command) {
        if (commands.containsKey(command_name) || commands.containsValue(command))
            return;
        commands.put(command_name, command);
    }

    public void removeCommand(String command_name, CommandExecutor command) {
        if (!commands.containsKey(command_name) || !commands.containsValue(command))
            return;
        commands.remove(command_name);
    }

    public void addListener(Listener listener) {
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public void addConfiguration(String name, Configuration configuraion) {
        if (configurations.containsKey(name.toLowerCase()) || configurations.containsValue(configuraion))
            throw new IllegalStateException("Faild to add configuration configuration exist already !");
        configurations.put(name.toLowerCase(), configuraion);
    }

    public void removeConfiguration(String name, Configuration configuraion) {
        if (!configurations.containsKey(name.toLowerCase()) || !configurations.containsValue(configuraion))
            throw new IllegalStateException("Faild to remove configuration configuration not exist !");
        configurations.remove(name.toLowerCase());
    }
    // utils

    public static void shutdown(JavaPlugin javaPlugin) {
        Bukkit.getServer().getPluginManager().disablePlugin(javaPlugin);
    }

    public static boolean found(String plugin_name) {
        return Bukkit.getServer().getPluginManager().getPlugin(plugin_name) != null;
    }
}
