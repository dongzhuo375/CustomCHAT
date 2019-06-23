package com.github.dongzhuo375.CustomCHAT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCHAT extends JavaPlugin implements Listener{
	File file=new File(getDataFolder(),"config.yml");
	public void loadConfigs() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
			}
			 if (!(file.exists())) {
			saveDefaultConfig();
	}
	reloadConfig();
	}
	@EventHandler
    public void onPlayerInteractEvent(AsyncPlayerChatEvent event) throws IOException {
		String a =  getConfig().getString("player-left");
		String b =  getConfig().getString("message-left");
		String c =  getConfig().getString("message-right");
		Player p = event.getPlayer();
		String p2 = p.getName();
		String message = event.getMessage();
		File pfile=new File(getDataFolder(),p2);;
		 if (!(pfile.exists())) {
             try {
                 pfile.createNewFile();
                 FileWriter fileWriter = new FileWriter(file,true);
                 fileWriter.write("\r\n" + p2 + ": " + "2" +"\r\n" + p2 + "-player-left: " + "\r\n" + p2 + "-message-left: " + "\r\n" + p2 + "-message-right: ");
                 fileWriter.flush();
                 fileWriter.close();
                 reloadConfig();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } 
			String aa =  getConfig().getString(p2 + "-player-left");
			String bb =  getConfig().getString(p2 + "-message-left");
			String cc =  getConfig().getString(p2 + "-message-right");
			int dd =  getConfig().getInt(p2);
		if (dd == 1) {
			Bukkit.getServer().broadcastMessage(aa.replaceAll("&","§" ) + p2 + bb.replaceAll("&","§" ) + message + cc.replaceAll("&","§" ));
			event.setCancelled(true);
		}
		if (dd == 2) {
			Bukkit.getServer().broadcastMessage(a.replaceAll("&","§" ) + p2 + b.replaceAll("&","§" ) + message + c.replaceAll("&","§" ));
			event.setCancelled(true);
		}
	}
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getLogger().info("启用了");
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	@Override 
	public void onDisable() {
		getLogger().info("停用了");
	}     
	@Override
	public void onLoad() {
		getLogger().info("重载了");
	}
}
