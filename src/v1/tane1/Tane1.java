// (c) 2017/07/02 Tane.
package v1.tane1;

import java.util.ArrayList;

import v1.api.Board;
import v1.api.Location;
import v1.api.Player;
import v1.api.Reverse;

public class Tane1 extends Player {

	public Tane1(String color){
		super(color);
	}

	public Location teban(Board boardmaster){

		ArrayList<Aspect> aspects = new ArrayList<Aspect>();

		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Location loc = new Location(j,i);
				Board board = new Board(boardmaster);
				Reverse reverse = new Reverse(board);
				if(reverse.canPut(color, loc)){
					reverse.reverse(color, loc);
					aspects.add(new Aspect(color, loc, board));
				}
			}
		}
		int eval= -9999;
		Location location=new Location();
		for(int i=0;i<aspects.size();i++){
			if(aspects.get(i).getEvaluation()>eval){
				eval=aspects.get(i).getEvaluation();
				location=aspects.get(i).getLocation();
			}
		}

		return location;
	}

}
