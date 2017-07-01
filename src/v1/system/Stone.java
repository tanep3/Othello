// (c)2017/07/01 Tane.
package v1.system;

//石を管理するクラスです。
//このクラスはAIでは使用しません。
//石の情報が欲しい時にはBoardクラスを使用して下さい。
public class Stone {
	//color は
	// 空白 0
	// 黒   -1
	// 白   1
	private int color;

	public Stone(){
		this.color = 0;
	}

	public boolean put(String c){
		switch (c) {
		case "null":
			this.color=0;
			break;
		case "black":
			this.color=-1;
			break;
		case "white":
			this.color=1;
			break;
		default:
			return false;
		}
		return true;
	}

	public String getColor(){
		switch (this.color){
		case 0:
			return "null";
		case -1:
			return "black";
		case 1:
			return "white";
		default:
			return "null";
		}
	}

	public String getChar(){
		switch (this.color){
		case 0:
			return ". ";
		case -1:
			return "○";
		case 1:
			return "●";
		default:
			return "  ";
		}
	}
}
