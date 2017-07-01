// (c)2017/07/01 Tane.
package v1.system;

import v1.api.Board;
import v1.api.Location;
import v1.api.Player;
import v1.api.Point;
import v1.api.Reverse;

//ゲーム実行用のクラスです。
//このクラスはAIでは使いません。
public class Game {

	private Player player1;
	private Player player2;

	public Game(){}

	public void setPlayer1(Player p){this.player1=p;}
	public void setPlayer2(Player p){this.player2=p;}

	public void play(){
		Board board=new Board();
		Reverse reverse=new Reverse(board);
		Point point = new Point();
		String win="draw";

		board.display();
		point.display();

		while(true){
			Location loc;

			//黒の手番
			if(canBlack(reverse)){
				System.out.println("黒の手番です。");
				loc=player1.teban(board);
				reverse.reverse("black", loc);
				board.display();
				win = point.checkPoint(board);
				point.display();
			}else if(!canWhite(reverse)){
				break;
			}

			//白の手番
			if(canWhite(reverse)){
				System.out.println("白の手番です。");
				loc=player2.teban(board);
				reverse.reverse("white", loc);
				board.display();
				win = point.checkPoint(board);
				point.display();
			}else if(!canBlack(reverse)){
				break;
			}
		}

		switch (win){
		case "black":
			System.out.println("黒の勝ちです。");
			break;
		case "white":
			System.out.println("白の勝ちです。");
			break;
		case "draw":
			System.out.println("引き分けです。");
			break;
		}
	}

	//石が置ける場所があればtrueを返す。
	private boolean canBlack(Reverse reverse){
		boolean fok=false;

		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Location loc = new Location(j,i);
				if(reverse.canPut("black",loc)){
					fok = true;
				}
			}
		}
		return fok;
	}

	private boolean canWhite(Reverse reverse){
		boolean fok=false;

		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Location loc = new Location(j,i);
				if(reverse.canPut("white", loc)){
					fok = true;
				}
			}
		}
		return fok;
	}

}
