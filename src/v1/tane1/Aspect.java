// (c) 2017/07/02 Tane.
package v1.tane1;

import v1.api.Board;
import v1.api.Location;
import v1.api.Point;
import v1.api.Reverse;

public class Aspect {
	private String color;
	private Location location;
	private Board board;
	private Point point = new Point();
	private int evalScore;
	private int[][] evalBoard = new int[8][8];

	public Aspect(String color, Location loc, Board b){
		this.color = color;
		this.location = new Location(loc);
		this.board = new Board(b);
		point.checkPoint(board);
		setEvalBoard(evalBoard);
		calcEvaluation();
	}

	public Location getLocation(){return location;}
	public int getEvaluation(){return evalScore;}

	private void calcEvaluation(){
		//以下のようなアルゴリズムとする。
		//もし、手数が40未満ならコマ数が少ない方が優勢
		//もし、手数が40以上ならコマ数が多い方が優勢
		int tesuu = point.getBlack()+point.getWhite();
		int evalTesuu;
		if(tesuu<40){
			if(color.equals("black")){
				evalTesuu=point.getWhite()-point.getBlack();
			}else{
				evalTesuu=point.getBlack()-point.getWhite();
			}
		}else{
			if(color.equals("black")){
				evalTesuu=point.getBlack()-point.getWhite();
			}else{
				evalTesuu=point.getWhite()-point.getBlack();
			}
		}
		
		//盤面の位置による評価
		int evalPosition=0;
		if(tesuu<44){
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					Location loc = new Location(j,i);
					if(board.getStone(loc).equals(color)){
						evalPosition += evalBoard[j][i];
					}
				}
			}
		}
		
		//自分が取れる場所が多い方が優勢
		int evalMyspace=0;
		int espBlack=0;
		int espWhite=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Location loc = new Location(j,i);
				Reverse rev=new Reverse(board);
				if(rev.canPut("black", loc)) espBlack++;
				if(rev.canPut("white", loc)) espWhite++;
			}
		}
		if(color.equals("black")){
			evalMyspace=espBlack-espWhite;
		}else{
			evalMyspace=espWhite-espBlack;
		}
		
		
		evalScore=evalTesuu + evalPosition*2 + evalMyspace*2;
	}
	
	private void setEvalBoard(int evalBoard[][]){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				evalBoard[j][i]=0;
			}
		}
		
		//角を取ったら加点(+3)
		evalBoard[0][0]=3;
		evalBoard[0][7]=3;
		evalBoard[7][0]=3;
		evalBoard[7][7]=3;
		
		//角の周りを取ったら減点(-5,-3)
		evalBoard[1][1]=-5;
		evalBoard[6][6]=-5;
		evalBoard[6][1]=-5;
		evalBoard[1][6]=-5;
		evalBoard[0][1]=-3;
		evalBoard[1][0]=-3;
		evalBoard[6][7]=-3;
		evalBoard[7][6]=-3;
		evalBoard[0][6]=-3;
		evalBoard[1][7]=-3;
		evalBoard[6][0]=-3;
		evalBoard[7][1]=-3;
		
		//辺を取ったら減点(-2)
		for(int i=2;i<6;i++){
			evalBoard[0][i]=-2;
			evalBoard[i][0]=-2;
			evalBoard[i][7]=-2;
			evalBoard[7][i]=-2;
		}
		
		//辺の一つ上を取ったら減点(-1)
		for(int i=2;i<6;i++){
			evalBoard[1][i]=-1;
			evalBoard[i][1]=-1;
			evalBoard[i][6]=-1;
			evalBoard[6][i]=-1;
		}
	}


}
