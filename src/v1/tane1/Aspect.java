package v1.tane1;

import v1.api.Board;
import v1.api.Location;
import v1.api.Point;

public class Aspect {
	private String color;
	private Location location;
	private Board board;
	private Point point = new Point();
	private int evalScore;

	public Aspect(String color, Location loc, Board b){
		this.color = color;
		this.location = new Location(loc);
		this.board = new Board(b);
		point.checkPoint(board);
		calcEvaluation();
	}

	public Location getLocation(){return location;}
	public int getEvaluation(){return evalScore;}

	private void calcEvaluation(){
		//以下のようなアルゴリズムとする。
		//もし、手数が40未満ならコマ数が少ない方が優勢
		//もし、手数が40以上ならコマ数が多い方が優勢
		int tesuu = point.getBlack()+point.getWhite();
		if(tesuu<40){
			if(color.equals("black")){
				evalScore=point.getWhite()-point.getBlack();
			}else{
				evalScore=point.getBlack()-point.getWhite();
			}
		}else{
			if(color.equals("black")){
				evalScore=point.getBlack()-point.getWhite();
			}else{
				evalScore=point.getWhite()-point.getBlack();
			}
		}
	}


}
