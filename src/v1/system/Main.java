// (c)2017/07/01 Tane.
package v1.system;

public class Main {

	public static void main(String[] args) {
		Game game=new Game();

		SetPlayer setplayer = new SetPlayer(game);
		setplayer.set();

		game.play();

	}

}
