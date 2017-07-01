// (c)2017/07/01 Tane.
package v1.api;

import v1.system.Stone;

//オセロの盤面を管理するクラスです。
public class Board {
	private Stone[][] stone = new Stone[8][8];

	public Board(){
		//盤面の初期化
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				stone[j][i]=new Stone();
			}
		}
		//座標(3,3)(4,4)に黒
		//座標(3,4)(4,3)に白を置く
		stone[3][3].put("black");
		stone[4][4].put("black");
		stone[3][4].put("white");
		stone[4][3].put("white");
	}

	//このコンストラクタを使うと、Boardオブジェクトを複製できます。
	//着手探索の際には、これで複製したBoardオブジェクトを使ってください。
	public Board(Board board){
		//盤面の初期化
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Location loc=new Location(j,i);
				stone[j][i].put(board.getStone(loc));
			}
		}
	}

	public String getStone(Location loc){
		return stone[loc.getX()][loc.getY()].getColor();
	}

	public boolean putStone(String color, Location loc){
		stone[loc.getX()][loc.getY()].put(color);
		return true;
	}

	public void display(){
		System.out.println("  a b c d e f g h");
		for(int i=0;i<8;i++){
			System.out.print(i+" ");
			for(int j=0;j<8;j++){
				System.out.print(stone[j][i].getChar());
			}
			System.out.println();
		}
	}

}
