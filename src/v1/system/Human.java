// (c)2017/07/01 Tane.
package v1.system;

import java.util.Scanner;

import v1.api.Board;
import v1.api.Location;
import v1.api.Player;
import v1.api.Reverse;

//対人用のPlayerクラス
public class Human extends Player {
	private String msgTeban;
	
	public Human(String color){
		super(color);
		
		
		if(color.equals("black")) msgTeban="黒の手番です。";
		else msgTeban="白の手番です。";
	}

	public Location teban(Board board){
		Reverse reverse = new Reverse(board);
		Location loc;

		while(true){
			loc = keyInput(board);
			if(reverse.canPut(color,loc)) break;
			board.display();
			System.out.println(msgTeban);
			System.out.println("そこには置けません。");
		}
		return loc;

	}

	private Location keyInput(Board board){
		Scanner sc = new Scanner(System.in);
		int x,y;

		while(true){
			System.out.println("手を入力してください。例：a0");
			String te = sc.next();

			if(te.length()==2){
				try {
					x = (int)(te.charAt(1)-'a');
					y = Integer.parseInt(te.substring(0,1));
					if(x>=0 && x<8 && y>=0 && y<8) break;
				}catch(NumberFormatException e1){
					try{
						x = (int)(te.charAt(0)-'a');
						y = Integer.parseInt(te.substring(1,2));
						if(x>=0 && x<8 && y>=0 && y<8) break;
					}catch(NumberFormatException e2){
					}
				}
			}
			board.display();
			System.out.println(msgTeban);
			System.out.println("手を正しく入力してください");
		}
		Location loc = new Location(x,y);
		return loc;

	}

}
