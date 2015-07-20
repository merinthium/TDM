package com.merinthium;
//ghello
import java.io.File;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.merinthium.handlers.Kit;
import com.merinthium.listeners.player.AsyncPlayerPreLogin;
import com.merinthium.listeners.player.PlayerDeath;
import com.merinthium.listeners.player.PlayerJoin;
import com.merinthium.listeners.player.PlayerQuit;
import com.merinthium.threads.StartCountdown;

public class Main extends JavaPlugin
{
	public static int startCountdownId;

	public void onEnable()
	{
		GameState.setState(GameState.IN_LOBBY);
		setupConfig();
		registerKits();
		startCountdown();
	}
	
	public void registerListeners()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerQuit(this), this);
		pm.registerEvents(new AsyncPlayerPreLogin(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
	}
	
	public void setupConfig()
	{
		File resetFile = new File(getDataFolder(), "RESET.FILE");
		if(resetFile.exists())
			return;
		FileConfiguration config = getConfig();
		config.set("Kits.Basic.Items", Arrays.asList("272", "297:5"));
		config.set("Kits.Basic.Display_Item", 35);
		saveConfig();
			try
			{
				resetFile.createNewFile();
			}catch (Exception e)
			{
			e.printStackTrace();	
			}
		}
	
	
	public void registerKits()
	{
		FileConfiguration config = getConfig();
		for(String s : config.getConfigurationSection("Kits").getKeys(false))
		{
			String path = "Kits." + s +".";
			new Kit(s, config.getStringList(path + "Items"), config.getInt(path + "DIsplay_Item"));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void startCountdown()
	{
		StartCountdown.timeUntilStart = 60;
		startCountdownId = getServer()
				.getScheduler()
				.scheduleSyncRepeatingTask(this, new StartCountdown(this),201, 201);
	}
	
	public void stopCountdown()
	{
		getServer().getScheduler().cancelTask(startCountdownId);
	}
	
	public void restartCountdown()
	{
		stopCountdown();
		startCountdown();
	}

}
