package com.merinthium.listeners.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.merinthium.Main;
import com.merinthium.handlers.Game;
import com.merinthium.handlers.Team;
import com.merinthium.listeners.MGListener;

public class EntityDamageByEntity extends MGListener {

	public EntityDamageByEntity(Main pl) {
		super(pl);
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		if(!(event.getEntity() instanceof Player && event.getDamager() instanceof Player))
		{
			event.setCancelled(true);
			return;
		}
		if(!Game.hasStarted())
		{
			event.setCancelled(true);
			return;
		}
		Player player = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if(Team.getTeam(player) == Team.getTeam(damager))
			event.setCancelled(true);
	}

}
