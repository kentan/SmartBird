#SmartBird

<img title="SmartBird" src="https://raw.githubusercontent.com/kentan/SmartBird/master/sbSample.png" height="400" width="500" />


SmartBird is artificial intelligence, AI, platform for Mahjong.

SmartBird provides the function that enable programmers to make thier own AI based on their strategy.

All programmers must have to do is to decide which tile(牌)should be discarded at each turn.
Calculating the point will be done by Smartbird.


#How to use
## How to run sample code.
You can see the Mahjong game by sample AI players by the following steps.
1. Build the project with maven.<br/>
   %> cd ./Smartbird/ <br/>
   %> mvn install <br/>

2. Deploy the sb.war to the Tomcat.<br/>
   %> cp ./SmartBirdRestfulServer/target/sb.war &lt;Tomcat installDir&gt;/webapp/sb.war<br/>

3. Launch the SmartBird.<br/>
   Open the brower and access to the following URL. <br/>
   http://localhost:8080/sb/index.html<br/>

4. Start game<br/>
   Click "start" button. The game will start.<br/>


## How to make your original AI.
1. Execute the pom in order to obtain sbengine.jar.
   %> cd ./Smartbird/ <br/>
   %> mvn install <br/>

2. Make the class inherited from AbstractGamePlayer that is implemented in sbengine.jar.</br>

3. Implement the "notifyTurn" method. </br>
   The method is called when the game is your turn. You need to decide which tile(牌) should be discarded. This is where you implemeted the AI for Mahjong.

4. Implement the "notifySteal" method. </br>
   The medhod is called when other players discard tiles. You need to decide whether you would like to pong(ポン), chow(チー) or ron(ロン).

5. After implementing your AI, execute the pom again.
   %> cd ./Smartbird/ <br/>
   %> mvn install <br/>

6. Deploy the sb.war to the Tomcat.<br/>
   %> cp ./SmartBirdRestfulServer/target/sb.war &lt;Tomcat installDir&gt;/webapp/sb.war<br/>

7. Launch the SmartBird.<br/>
   Open the brower and access to the following URL. <br/>
   http://localhost:8080/sb/index.html<br/>

8. Start game<br/>
   Click "start" button. The game will start.<br/>

