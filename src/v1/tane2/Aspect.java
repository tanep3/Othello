// (c) 2017/07/02 Tane.
package v1.tane2;

import v1.api.Board;
import v1.api.Location;
import v1.api.Point;
import v1.api.Reverse;

public class Aspect {
	private String color;
	private String invColor;
	private Location location;
	private Board board;
	private Point point = new Point();
	private int evalScore;
	private int[][] evalBoard = new int[8][8];

	public Aspect(String color, Location loc, Board b){
		this.color = color;
		if (color.equals("black")) invColor="white";
		else invColor="black";
		this.location = new Location(loc);
		this.board = new Board(b);
		point.checkPoint(board);
		setEvalBoard(evalBoard);
		calcEvaluation();
	}

	public Location getLocation(){return location;}
	public int getEvaluation(){return evalScore;}

	private void calcEvaluation(){
		Location loc=null;
		Reverse rev=null;
		//以下のようなアルゴリズムとする。
		//もし、手数が40未満ならコマ数が少ない方が優勢
		//もし、手数が40以上ならコマ数が多い方が優勢
		int tesuu = point.getBlack()+point.getWhite();
		int evalTesuu=0;
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
					loc = new Location(j,i);
					if(board.getStone(loc).equals(color)){
						evalPosition += evalBoard[j][i];
					}
				}
			}
		}

		//自分が取れる場所が多い方が優勢
		int evalKaihoudo=0;
		if(tesuu<50){
			int ekaihouMe=0;
			int ekaihouYou=0;
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					loc = new Location(j,i);
					rev=new Reverse(board);
					if(rev.canPut(color, loc)) ekaihouMe++;
					if(rev.canPut(invColor, loc)) ekaihouYou++;
				}
			}
			evalKaihoudo=ekaihouMe-ekaihouYou;
		}

		//相手が角をとれるなら減点
		int evalKado=0;
		rev=new Reverse(board);
		loc=new Location(0,0);
		if(rev.canPut(invColor, loc)) evalKado -=1;
		loc=new Location(0,7);
		if(rev.canPut(invColor, loc)) evalKado -=1;
		loc=new Location(7,0);
		if(rev.canPut(invColor, loc)) evalKado -=1;
		loc=new Location(7,7);
		if(rev.canPut(invColor, loc)) evalKado -=1;
		
		//偶数理論
		int evalGuusuu=0;
		if(tesuu>40){
			int ban[][] = new int[8][8];
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					loc=new Location(j,i);
					if(board.getStone(loc).equals("null")){
						ban[j][i]=0;
					}else{
						ban[j][i]=-1;
					}
				}
			}
			int nullNo=1;
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(ban[j][i]==0){
						guusuu(ban,j,i,nullNo);
						nullNo++;
					}
				}
			}
			int[] nullpoint = new int[nullNo];
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(ban[j][i]>0)	nullpoint[ban[j][i]-1]++;
				}
			}
			for(int i=0;i<nullNo;i++){
				if(nullpoint[i]%2==1){
					evalGuusuu--;
				}
			}
		}
		
		evalScore = evalTesuu + evalPosition*2 + evalKaihoudo*5;
		evalScore += evalKado*20 + evalGuusuu*5;
	}
	
	private void guusuu(int[][] ban,int x, int y,int nullNo){
		if(ban[x][y]==0){
			ban[x][y]=nullNo;
			if(x<7) guusuu(ban,x+1,y,nullNo);
			if(y<7) guusuu(ban,x,y+1,nullNo);
			if(x>0) guusuu(ban,x-1,y,nullNo);
			if(y>0) guusuu(ban,x,y-1,nullNo);
		}
	}

	private void setEvalBoard(int evalBoard[][]){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				evalBoard[j][i]=0;
			}
		}

		//角を取ったら加点
		evalBoard[0][0]=15;
		evalBoard[0][7]=15;
		evalBoard[7][0]=15;
		evalBoard[7][7]=15;

		//角の周りを取ったら減点
		evalBoard[1][1]=-20;
		evalBoard[6][6]=-20;
		evalBoard[6][1]=-20;
		evalBoard[1][6]=-20;
		evalBoard[0][1]=-3;
		evalBoard[1][0]=-3;
		evalBoard[6][7]=-3;
		evalBoard[7][6]=-3;
		evalBoard[0][6]=-3;
		evalBoard[1][7]=-3;
		evalBoard[6][0]=-3;
		evalBoard[7][1]=-3;

		//辺を取ったら減点
		evalBoard[0][2]=-1;
		evalBoard[0][5]=-1;
		evalBoard[2][0]=-1;
		evalBoard[5][0]=-1;
		evalBoard[2][7]=-1;
		evalBoard[5][7]=-1;
		evalBoard[7][2]=-1;
		evalBoard[7][5]=-1;

		//辺を取ったら加点
		evalBoard[0][3]=2;
		evalBoard[0][4]=2;
		evalBoard[3][0]=2;
		evalBoard[4][0]=2;
		evalBoard[3][7]=2;
		evalBoard[4][7]=2;
		evalBoard[7][3]=2;
		evalBoard[7][4]=2;


		//辺の一つ上を取ったら減点(-1)
		for(int i=2;i<6;i++){
			evalBoard[1][i]=-2;
			evalBoard[i][1]=-2;
			evalBoard[i][6]=-2;
			evalBoard[6][i]=-2;
		}
	}


}
