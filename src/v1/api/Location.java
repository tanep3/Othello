// (c)2017/07/01 Tane.
package v1.api;

//座標を管理するクラスです
//方向を与えると座標を一つ進めてくれる便利なinc関数も用意しています。
//ぜひinc関数をご活用下さい。
public class Location {
	private int x;
	private int y;
	
	public Location(){
		setX(0);
		setY(0);
	}
	
	public Location(int x, int y){
		setX(x);
		setY(y);
	}
	
	//このコンストラクタでLocationオブジェクトを複製できます。
	//Location loc = new Location(location);
	//のように使用して下さい。
	public Location(Location loc){
		set(loc.getX(),loc.getY());
	}

	public boolean setX(int x){
		if(x>=0 && x<8){
			this.x = x;
			return true;
		}
		this.x=-1;
		return false;
	}

	public boolean setY(int y){
		if(y>=0 && y<8){
			this.y = y;
			return true;
		}
		this.y=-1;
		return false;
	}
	
	public boolean set(int x, int y){
		if(setX(x) && setY(y)) return true;
		return false;
	}

	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public boolean incX(){
		return setX(this.x + 1);
	}
	public boolean incY(){
		return setY(this.y + 1);
	}
	public boolean decX(){
		return setX(this.x - 1);
	}
	public boolean decY(){
		return setY(this.y - 1);
	}
	
	
	//方向を与えると、その方向に座標を一つ進める関数です。
	public boolean inc(String direction){
		boolean flg=true;
		switch (direction){
		case "R":
			flg=incX();
			break;
		case "RD":
		case "DR":
			flg=(incX() & incY());
			break;
		case "D":
			flg=incY();
			break;
		case "LD":
		case "DL":
			flg=(decX() & incY());
			break;
		case "L":
			flg=decX();
			break;
		case "LU":
		case "UL":
			flg=(decX() & decY());
			break;
		case "U":
			flg=decY();
			break;
		case "RU":
		case "UR":
			flg=(incX() & decY());
			break;
		default:
			flg=false;
		}
		return flg;
	}
}
