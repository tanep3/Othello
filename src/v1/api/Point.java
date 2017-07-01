// (c)2017/07/01 Tane.
package v1.api;

//得点を管理するクラスです。
public class Point {
	private int black;
	private int white;

	public Point(){
		black=2;
		white=2;
	}

	public void setBlack(int p){this.black=p;}
	public void setWhite(int p){this.white=p;}
	public int getBlack(){return this.black;}
	public int getWhite(){return this.white;}

	public void set(int b, int w){
		this.black=b;
		this.white=w;
	}

	//黒、白の数を数えます。
	//そしてどちらが優勢か返却します。
	//具体的な得点を知りたい時はこの関数実行後に
	//getメソッドをコールして下さい。
	public String checkPoint(Board board){
		int pb=0;
		int pw=0;
		Location loc=new Location();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				loc.set(j,i);
				String color = board.getStone(loc);
				if(color.equals("black")) pb++;
				if(color.equals("white")) pw++;
			}
		}
		setBlack(pb);
		setWhite(pw);

		if(pb>pw) return "black";
		if(pb<pw) return "white";
		return "draw";
	}

	public void display(){
		int pb=getBlack();
		int pw=getWhite();
		System.out.println("黒："+pb+"  白："+pw);
	}


}
