// (c)2017/07/01 Tane.
package v1.api;

public abstract class Player {
	protected String color; //自分が黒か白か。

	public Player(String color){
		//コンストラクタで自分の色を設定しています。
		//継承時にはsuperでこれを呼び出してください。
		this.color = color;
	}
	
	//このクラスの窓口（インターフェース）はteban関数のみです。
	//AIはこの関数内にプログラミングしてください。
	//現在の局面のBoardを引数として渡します。
	//次の一手の座標をLocationクラスに格納して返却して下さい。
	//置けない場所は返却しないよう、AI内でチェックをして下さい。
	abstract public Location teban(Board board);

}
