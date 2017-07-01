// (c)2017/07/01 Tane.
package v1.api;

//石をひっくり返すためのクラスです。
//reverse関数を実行すると石の反転をします。
//当クラスの引数のBoardオブジェクトは、必ずBoardコンストラクタで
//複製したものを設定するようにして下さい。
//選んだ場所が着手可能かの判定をするcanPut関数も梱包しています。
//reverse関数は着手できることが保証されている前提で動かして下さい。
//つまり、reverse関数実行前に必ずcanPut関数でチェックをして下さい。
public class Reverse {
	private Board board;
	
	public Reverse(Board board){
		this.board=board;
	}
	
	//石がその場所に置けるか判定する関数。
	public boolean canPut(String color, Location location){
		//もし空白じゃなかったら置けない。
		if(!board.getStone(location).equals("null")) return false;
		
		boolean fok=false; //置けるかどうかのフラグ
		
		//右
		if(canPutOneWay(color,location,"R")) fok=true;
		
		//右下
		if(canPutOneWay(color,location,"RD")) fok=true;

		//下
		if(canPutOneWay(color,location,"D")) fok=true;

		//左下
		if(canPutOneWay(color,location,"LD")) fok=true;

		//左
		if(canPutOneWay(color,location,"L")) fok=true;

		//左上
		if(canPutOneWay(color,location,"LU")) fok=true;

		//上
		if(canPutOneWay(color,location,"U")) fok=true;

		//右上
		if(canPutOneWay(color,location,"RU")) fok=true;

		return fok;
	}

	public boolean canPutOneWay(String color, Location location,String direction){
		Location loc = new Location(location);
		String invColor="black";
		if(color.equals("black")) invColor="white";
		
		boolean fsafe=true; //検索範囲がボード内かどうかのフラグ
		if(loc.inc(direction)) {
			if(!board.getStone(loc).equals(invColor)) return false;
			//最初のマスが反対色だったらチェック開始
			while(board.getStone(loc).equals(invColor)){
				//反対色だったらマスを進める
				if(!loc.inc(direction)) {
					fsafe = false;
					break;
				}
			}
			//自分のコマがあるかチェック
			if(fsafe){
				if(board.getStone(loc).equals(color)) return true;
			}
		}
		return false;
	}
	
	public void reverse(String color, Location loc){
		board.putStone(color, loc);
		
		//右
		if(canPutOneWay(color,loc,"R")){
			revOneWay(color,loc,"R");
		}
		
		//右下
		if(canPutOneWay(color,loc,"RD")){
			revOneWay(color,loc,"RD");
		}
		
		//下
		if(canPutOneWay(color,loc,"D")){
			revOneWay(color,loc,"D");
		}
		
		//左下
		if(canPutOneWay(color,loc,"LD")){
			revOneWay(color,loc,"LD");
		}
		
		//左
		if(canPutOneWay(color,loc,"L")){
			revOneWay(color,loc,"L");
		}
		
		//左上
		if(canPutOneWay(color,loc,"LU")){
			revOneWay(color,loc,"LU");
		}
		
		//上
		if(canPutOneWay(color,loc,"U")){
			revOneWay(color,loc,"U");
		}
		
		//右上
		if(canPutOneWay(color,loc,"RU")){
			revOneWay(color,loc,"RU");
		}
		

	}
	
	private void revOneWay(String color, Location location, String direction){
		String invColor="black";
		if(color.equals("black")) invColor="white";
		Location loc=new Location(location);
		
		loc.inc(direction);
		while(board.getStone(loc).equals(invColor)){
			//コマをひっくり返す
			board.putStone(color, loc);
			//反対色だったらマスを進める
			loc.inc(direction);
		}
	}

}
