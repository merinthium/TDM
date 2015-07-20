package com.merinthium.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Map {

	private static Map activeMap = null;
	private static List<Map> allMaps = new ArrayList<Map>();
	
	private List<String> spawns;
	private String fileName, mapName;
	
	public Map(String mapName, String fileName, List<String> spawns) 
	{
		this.spawns = spawns;
		
		this.fileName = fileName;
		this.mapName = mapName;
	}
	
	public static List<Map> getAllMaps()
	{
		return allMaps;
	}
	
	public static void setActiveMap(Map map)
	{
		activeMap = map;
	}
	
	public static Map getActiveMap()
	{
		return activeMap;
	}
	
	public String getMapName()
	{
		return mapName;
	}
	
	public World getWorld()
	{
		return Bukkit.getWorld(fileName);
	}
	
	public Location getSpawn(int teamId)
	{
		//loop through all teams
		for(Team t : Team.getAllTeams())
		{
			//get Team id
			int tId = t.getId();
			//is team id one looking for
			if(tId != teamId)
				continue;
			//Check enough spawn points if not use spawn point(first 
			int actualTeamId = tId;
			if(spawns.get(tId) == null) 
				actualTeamId = 0;
			//tp to spawn point
			String teamSpawn = spawns.get(actualTeamId);
			String[] teamSpawns = teamSpawn.split(",");
			return new Location(
					getWorld(),
					Integer.valueOf(teamSpawns[0])+0.5,
					Integer.valueOf(teamSpawns[1])+0.5,
					Integer.valueOf(teamSpawns[2])+0.5);
		}
		return null;
	}

}
