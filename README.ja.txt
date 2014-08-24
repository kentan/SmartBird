簡単なメモ日本語版。正式版のREADMEは英語でそのうち。

■プロジェクト構成
a. SmartBirdEngine
麻雀の点数計算、プレイヤーの手順管理などゲームとして麻雀を行うのに必要なすべてを行うプロジェクト。
こいつを起動すると、sbclien.jarを読み込んで、こいつの中にいるAI用クラス(AbstructGamePlayerを継承したクラス)のメソッドを呼び出しながらゲームを続ける。


b. SmartBirdClient
AIを実装するプログラマーが唯一コードを書く必要があるのがこのプロジェクト。
AbstructGamePlayerを継承して実装すること。GameServerとのinteractionは(ツモ宣言とか牌捨てとか)はAbstructGamePlayerがもつメソッドを通じて行う。

c. SmartBirdResfulServer
SmartBirdのGUI. 
Tomcat上で動くwarを生成する。jsから受け取ったrestful requestをSmartBirdGameServerに仲介する存在。


■実装手順
1. SmartBirdEngineをbuild. SmartBird/SmartBirdEngine直下にあるpomを実行する。
%>mvn insatall 
これによりsbengine.jarが生成される。
2. SmartBirdClientにAbstuctGamePlayerを継承したGamePlayerを作成する。
3. SmartBirdServerのPlayerDefs.confに4で作ったclassを記述する。player0〜player3のうちどれをつかってもよい。 
例)

player0 = <4で作ったクラス>
player1 = org.smartbirdpj.server.player.sample.SampleRandomPlayer
player2 = org.smartbirdpj.server.player.sample.SampleRandomPlayer
player3 = org.smartbirdpj.server.player.sample.SampleRandomPlayer

4. SmartBird直下にあるpomを使ってwarを生成する。 
これによりsbclient.jar,sbengine.jar,sb.warが生成される。

5. sb.warをtomcatにデプロイする。
6. <デプロイ先>/index.htmlにアクセスし、startボタンを実行する。
例) http://localhost:8080/sb/index.html

以上でゲームスタート。

■AbstructGamePlayerの実装方法

詳細はSmartBirdClientのConcreatePlayerかなんかを参考にすればわかると思うが、とりあえずさくっと説明。

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
