package com.gmail.zcraig29;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class noGrief extends JavaPlugin {
	Logger log;

	public void onEnable(){
		this.log = getLogger();
		getServer().getPluginManager().registerEvents(new BlockPlace(this), this);
		final File f = new File(getDataFolder(), "config.yml");
		if(!f.exists()){
			makeConfig();
		}
	}
	public void makeConfig() {
		this.log.info("No Configuration file found! Generating a new one!");
		saveDefaultConfig();
		String[] blacklist = {"46:0", "10:0", "11:0" , "8:0", "9:0"};
		getConfig().set("blacklist", Arrays.asList(blacklist));
		saveConfig();
		this.log.info("Configuration file created succesfully!");	
	}
	public void onDisable(){

	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		String prefix = ChatColor.YELLOW + "[" + ChatColor.GOLD + "NoGrief" + ChatColor.YELLOW + "] " + ChatColor.GREEN;
		if(cmd.getName().equalsIgnoreCase("nogrief") && (args.length >= 1)){
				if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("nogrief.reload")){
						this.reloadConfig();
						sender.sendMessage(prefix  + "Configuration has been reloaded.");	
						return true;	
					}
					if(!sender.hasPermission("nogrief.reload")){
						sender.sendMessage(prefix + ChatColor.RED + "Error: You don't have permission to do this!");
					}
				}
				if(args[0].equalsIgnoreCase("info")){
					if(sender.hasPermission("nogrief.info")){
						String version = this.getDescription().getVersion();
						sender.sendMessage(ChatColor.GOLD + "========" + ChatColor.YELLOW + "NoGrief" + ChatColor.GOLD + "========");
						sender.sendMessage(ChatColor.YELLOW + "Current Version: " + version);
						sender.sendMessage(ChatColor.YELLOW + "Author: " + getDescription().getAuthors());
						sender.sendMessage(ChatColor.YELLOW + "Where to look for updates:");
						sender.sendMessage(ChatColor.YELLOW + getDescription().getWebsite());
						sender.sendMessage(ChatColor.GOLD + "======================");
						return true;
					}
					if(!sender.hasPermission("nogrief.info")){
						sender.sendMessage(prefix + ChatColor.RED + "Error: you don't have permission to do this!");
					}
				}
			}
		return false;
	}
}
