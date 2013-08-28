簡単なメモ日本語版。正式版のREADMEは英語でそのうち。

■プロジェクト構成
a. SilverBirdModel
生成： sbmodel.jar
依存：なし
AIサーバ全体で必要なデータモデルをここに集めてる。

b. SilverBirdEngine
生成: sbengine.jar
依存: sbmodel.jar
役判定とか点数計算とか。

c. SilverBirdServer
生成:sbserver.jar
依存:sbengine.jar,sbmodel.jar,sbclient.jar(sbclient.jarはbuild時には不要。実行時にあれば良い。リフレクションでよんでるので)
Gameサーバ的なもの。
こいつを起動すると、sbclien.jarを読み込んで、こいつの中にいるAI用クラス(AbstructGamePlayerを継承したクラス)のメソッドを呼び出しながらゲームを続ける。

最終的にはWeb Servlet的なものにしてRestful Server化したいと思っているけど、まずは小さくリリース。

d. SilverBirdClient
AIを実装するプログラマーが唯一コードを各必要があるのがこのプロジェクト。
いまcommitしてあるConcreateGamePlayerは俺が５分で考えたAIが入ってる。
まあSampleRandomPlayer相手ならたまに上がりまで持ってくことができるので、とりあえず参考用に。

ちなみにa~cは実はjarだけあればよい。また後述の実装手順6で指定するクラス指定と一致していれば、プロジェクト名、クラス名ともになんでもいい。

AbstructGamePlayerを継承して実装すること。GameServerとのinteractionは(ツモ宣言とか牌捨てとか)はAbstructGamePlayerがもつメソッドを通じて行う。




■実装手順
1. SilverBirdModelをbuild (build.xmlを叩く)
2. SilverBirdEngineをbuild (build.xmlを叩く)
3. SilverBirdServerをbuild(build.xmlを叩く)
4. SilverBirdClientにAbstuctGamePlayerを継承したGamePlayerを作成する。
5. SilberBirdClientをbuild(build.xmlを叩く)
6. SilberBirdServerのGameServer#mainクラスの第一引数に4で作ったclassを記述する。
例)
public static void main(String args[]){

String playerDefs[] = {<4で作ったクラス>,

"org.sb.server.player.sample.SampleRandomPlayer",// ランダムに牌をきるだけのPlayer

"org.sb.server.player.sample.SampleRandomPlayer",

"org.sb.server.player.sample.SampleRandomPlayer"

};

7. SilverBirdServerをもう一度build(build.xmlを叩く)
SilberBirdServerのGameServer#mainを起動する。

以上でゲームスタート。

■AbstructGamePlayerの実装方法

詳細はSilberBirdClientのConcreatePlayerかなんかを参考にすればわかると思うが、とりあえずさくっと説明。

重要なのは以下の２つのメソッド

1.notifyTurn()
手順が来ると呼び出されるメソッド。
引数の内容は以下。
List<TileEnum> tiles, 自分の手牌。tileAtThisTurnも含んでる。

TileEnum tileAtThisTurn。このターンで取得した牌。

List<MeldElement> huroMelds。自分の副路。

List<TileEnum> discardeTiles。自分の捨て牌

Map<Integer,List<TileEnum>> otherPlayerDiscardedTiles。他家の捨て牌。keyはplayerId。自プレイヤーの捨て牌は含まれてない。

Map<Integer,List<MeldElement>> otherPlayerHuroTiles。他家の副路。keyはplayerId。自プレイヤーの副路は含まれてない。


これらの情報を元に、ツモ宣言したり、牌を捨てたりする。

2.notifySteal();
List<TileEnum> tiles,自分の手牌。

List<MeldElement> huroMelds。自分の副路。

List<TileEnum> discardedTiles。自分の捨て牌。

Map<Integer,List<TileEnum>> otherPlayerDiscardedTiles。他家の捨て牌。keyはplayerId。自プレイヤーの捨て牌は含まれてない。

Map<Integer,List<MeldElement>> otherPlayerHuroTiles。他家の副路。keyはplayerId。自プレイヤーの副路は含まれてない>

TileEnum currentDiscardedTile　上家の捨てた胚。

Steal=鳴き。
他プレイヤーが捨てるたびに、notifyStrealが呼び出される。
ポン、ロン、カン,チーしたいときは、それぞれAbstractGamePlayerのメソッドのpong(),ron(),kong(),chow()を呼び出す。
