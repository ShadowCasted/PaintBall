package com.bukkit.feeddz.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Signs extends JavaPlugin implements Listener{
	
	String prefix = getConfig().getString("Prefix");
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	
	  @EventHandler
	  public void sClick(PlayerInteractEvent event) {
		  Player player = event.getPlayer();
		  if (event.getClickedBlock().getState() instanceof Sign){
			  Sign sign = (Sign) event.getClickedBlock().getState();
			  if(sign.getLine(0).equalsIgnoreCase(ChatColor.BLUE + "[PaintBall]")) {
					  if(player.hasPermission("paintball.signs.use") || player.isOp()){
						  if(sign.getLine(1).equals(ChatColor.BLUE + "Join")){
							  String l2 = sign.getLine(2);
							  String arena = ChatColor.BLUE + l2;
							  if(getConfig().contains(arena)){
								  //Get the players in lobbie and teleport the player to the lobbie then change the sign
								  
							  }else{
								  player.sendMessage(ChatColor.RED + "This sign is invalid. Contact a server admin.");
							  }
						  }
					  }else{
						  player.sendMessage(ChatColor.RED + "You do not have permision to use PaintBall Signs");
					  }
					  
				}
			  }
	  }
	  
	  @EventHandler
	  public void signChangeEvent(SignChangeEvent sign) {
		  String l2 = sign.getLine(2);
	      Player player = sign.getPlayer();
	      if (sign.getLine(0).equalsIgnoreCase("paintball")){
	    	  if(player.hasPermission("paintball.signs.create")){
	    	  if(sign.getLine(1).equalsIgnoreCase("join")){
	    		  if(getConfig().contains(sign.getLine(2))){
	    			  sign.setLine(0, ChatColor.BLUE + "[PaintBall]");
	    			  sign.setLine(1, ChatColor.BLUE + "Join");
	    			  sign.setLine(2, ChatColor.BLUE + l2);
	    			  sign.setLine(3, ChatColor.GREEN + "0/24");
	    		  }else{
	    			  player.sendMessage(ChatColor.RED + "No arena exists with the name " + ChatColor.DARK_RED + l2);
	    		  }
	    	  }
	    	  }else{
	    		  player.sendMessage(ChatColor.RED + "You do not have permision to create a paintball sign.");
	    	  }
	}
	}
	  
	  
	  
}
