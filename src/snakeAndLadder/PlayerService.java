package snakeAndLadder;

import java.util.HashMap;
import java.util.Map;

public class PlayerService {

	private static Map<Integer,Player> chanceMap=null;
	
	private static PlayerService playerService=null;
	
	private PlayerService() {
		
	}
	
	public static PlayerService getInstance() {
		if(playerService==null) {
			chanceMap=new HashMap<>();
			playerService=new PlayerService();
		}
		return playerService;
	}
	
	public void addPlayer(Integer chance,String name) {
		Player player=new Player();
		player.setPlayerId(PlayerIdGenerator.getId());
		player.setCurrPos(0);
		player.setPlayerName(name);
		chanceMap.put(chance, player);
	}
	
	public Integer getPlayerPos(Integer chance) {
		return chanceMap.get(chance).getCurrPos();
	}
	
	public String getPlayerName(Integer chance) {
		return chanceMap.get(chance).getPlayerName();
	}
	
	public void updatePlayerPos(Integer chance,Integer newPos) {
		Player player=chanceMap.get(chance);
		player.setCurrPos(newPos);
		chanceMap.put(chance, player);
	}
	
}
