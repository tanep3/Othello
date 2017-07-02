// (c)2017/07/01 Tane.
package v1.system;

import java.util.Scanner;

import v1.api.Player;
import v1.tane1.Tane1;
import v1.tane2.Tane2;

//対戦相手を設定するクラスです。
//このクラスはAIでは使用しません。
public class SetPlayer {
	private Game game;

	public SetPlayer(Game game){this.game=game;}

	public void set(){
		Scanner sc = new Scanner(System.in);
		int p1=0;
		int p2=0;

		while(true){
			System.out.println("対戦者を選択してください。");
			System.out.println("1.人間");
			System.out.println("2.Tane1(たねのAIレベル１)");
			System.out.println("3.Tane2(たねのAIレベル２)");
			System.out.println("先手 後手の順に番号を入力して下さい：");

			String sp1 = sc.next();
			String sp2 = sc.next();
			try {
				p1=Integer.parseInt(sp1);
				p2=Integer.parseInt(sp2);
				if(p1>=1 && p1<=3 && p2>=1 && p2<=3) break;
				System.out.println("対戦者の番号を入力して下さい。");
			}catch(NumberFormatException e){
				System.out.println("半角の数字を入力して下さい。");
			}
		}

		Player player1;
		switch (p1){
		case 1:
			player1 = new Human("black");
			break;
		case 2:
			player1 = new Tane1("black");
			break;
		default:
			player1 = new Tane2("black");
		}
		game.setPlayer1(player1);

		Player player2;
		switch (p2){
		case 1:
			player2 = new Human("white");
			break;
		case 2:
			player2 = new Tane1("white");
			break;
		default:
			player2 = new Tane2("white");
		}
		game.setPlayer2(player2);

	}

}
