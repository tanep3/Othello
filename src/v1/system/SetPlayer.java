// (c)2017/07/01 Tane.
package v1.system;

import v1.api.Player;

//対戦相手を設定するクラスです。
//このクラスはAIでは使用しません。
public class SetPlayer {
	private Game game;
	
	public SetPlayer(Game game){this.game=game;}
	
	public void set(){
		Player player1 = new Human("black");
		Player player2 = new Human("white");
		
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		
	}

}
