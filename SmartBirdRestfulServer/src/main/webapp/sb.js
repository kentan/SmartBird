var sb = (function() {
	var discardedTilePosMap = {
		0 : 0,
		1 : 0,
		2 : 0,
		3 : 0
	};
	var huroIndexMap = {
			0 : 0,
			1 : 0,
			2 : 0,
			3 : 0
	};
	var richiFlagMap = {
			0 : [false,false,false,false],
			1 : [false,false,false,false],
			2 : [false,false,false,false],
			3 : [false,false,false,false]
	};
	var stolenPosMap = {};
	stolenPosMap[0] = {1:0,2:1,3:2};
	stolenPosMap[1] = {1:0,2:1, "-1":2};
	stolenPosMap[2] = {1:0,"-2":1,"-1":2};
	stolenPosMap[3] = {"-1":0,"-2":1,"-3":2};
	var tilePos = {};

	var operationFunc = {
		add : ofAddTile,
		chow : ofChow,
		pong : ofPong,
		richi : ofRichi,
		ron : ofRon,
		tumo : ofTumo,
		stealKong : ofStealKong,
		wallKong : ofWallKong,
		discard : ofDiscardTile,
		init : ofInitTiles,
		startRound : ofStartRound,
		finishRound : ofFinishRound,
	};
	function ofAddTile(playerId, values) {
		clearAllTiles(playerId);

		drawAllTiles(playerId, values.wall);
	}
	
	function drawHuro(playerId,tiles){
		var i = 0;
		while(i < tiles.length){	
			drawHuroTile(playerId,tiles[i],i ,huroIndexMap[playerId],tiles.length + 1,false);
			i++;
		}
		huroIndexMap[playerId] = huroIndexMap[playerId] + 1;
		
	}
	function drawStolenHuro(playerId,stolenTile,stolenPos,tiles){
		var i = 0;
		var j = 0;
		while(i < tiles.length + 1){
			if(i == stolenPos){
				drawHuroTile(playerId,stolenTile,i , huroIndexMap[playerId],tiles.length + 1, true);
			}else{	
				drawHuroTile(playerId,tiles[j],i ,huroIndexMap[playerId],tiles.length + 1,false);
				j++;
			}

			i++;
		}
		huroIndexMap[playerId] = huroIndexMap[playerId] + 1;
		
	}
	function naki(playerId,values){
		var stolenTile = values.stolenTile;
		var huro1 = values.huro1;
		var huro2 = values.huro2;
		var huro3 = values.huro3;
		var discardingTile = values.discardedTile;
		var wall = values.wall;
		var stolenPlayerId = values.stolenPlayerId;
		
		clearAllTiles(playerId);
		drawAllTiles(playerId, wall);
		
		var pos = stolenPosMap[playerId][stolenPlayerId];
		var tiles = [];
		if(huro3 === undefined){
			tiles = [huro1,huro2];
		}else{
			tiles = [huro1,huro2,huro3];
		}
		drawStolenHuro(playerId,stolenTile,pos,tiles);
		drawDiscardedTile(playerId, discardingTile,discardedTilePosMap[playerId]);
		discardedTilePosMap[playerId] = discardedTilePosMap[playerId] + 1;
	}
	function ofChow(playerId, values) {
		naki(playerId,values);
	}
	function ofPong(playerId, values) {
		naki(playerId,values);
	}
	function ofRichi(playerId, values) {
		var tile = values.richiTile;
		var posOfTheTile = tilePos[tile];
		var canvasId = "p" + playerId + "w" + posOfTheTile;
		tileDrawing.clearTile(canvasId);
		drawDiscardedTile(playerId, tile,discardedTilePosMap[playerId],true);
		discardedTilePosMap[playerId] = discardedTilePosMap[playerId] + 1;
	}
	function ofRon(playerId, values) {

	}
	function ofTumo(playerId, values) {
		var tumo = values.tumoTile;
		var player0payment = values.player0payment;
		var player1payment = values.player1payment;
		var player2payment = values.player2payment;
		var player3payment = values.player3payment;
		
		var message = "Player" + playerId + " has won. " + tumo;
		if(player0payment != undefined){
			message += " Player 0: -" + player0payment;
		}
		if(player1payment != undefined){
			message += " Player 1: - " + player1payment;
		}
		if(player2payment != undefined){
			message += " Player 2: - " + player2payment;
		}
		if(player3payment != undefined){
			message += " Player 3: - " + player3payment;
		}
		alert(message);
	}
	function ofStealKong(playerId, values) {
		naki(playerId,values);
	}
	function ofWallKong(playerId, values) {
		var huro1 = values.huro1;
		var huro2 = values.huro2;
		var huro3 = values.huro3;
		var huro4 = values.huro4;
		var discardingTile = values.discardedTile;
		var wall = values.wall;
		
		clearAllTiles(playerId);
		drawAllTiles(playerId, wall);
		
		var tiles = [huro1,huro2,huro3,huro4];

		drawHuro(playerId,tiles);
		drawDiscardedTile(playerId, discardingTile,discardedTilePosMap[playerId],false);
		discardedTilePosMap[playerId] = discardedTilePosMap[playerId] + 1;
	}

	function ofDiscardTile(playerId, values) {
		var tile = values.discardedTile;
		var posOfTheTile = tilePos[tile];
		var canvasId = "p" + playerId + "w" + posOfTheTile;
		tileDrawing.clearTile(canvasId);
		drawDiscardedTile(playerId, tile,discardedTilePosMap[playerId],false);
		discardedTilePosMap[playerId] = discardedTilePosMap[playerId] + 1;

	}
	function drawAllTiles(playerId, tiles) {

		for ( var i = 0; i < tiles.length; i++) {
			var tile = tiles[i];
			drawWallTile(playerId, tile,i);
			tilePos[tile] = i;
		}

	}

	function ofInitTiles(playerId, values) {
		drawAllTiles(playerId, values.initTiles);
		$("#info" + playerId).append("<h2>" + values.wind + "</h2>");
	}
	function ofStartRound(playerId, values) {
		var dora = values.dora;
		var wind = values.prevailingWind;
		var round = values.roundNumber + 1;
		$("#round").append(wind + round);
		var canvasId = "dora0";
		tileDrawing.drawTile(dora, canvasId);
	}
	var timerId;
	function ofFinishRound(playerId, value) {
		clearInterval(timerId);
		alert("finished");
	}

	function clearAllTiles(playerId) {
		for ( var i = 0; i < 14; i++) {
			var canvasId = "p" + playerId + "w" + i;
			$("#"+canvasId).remove();
		}
		pos = 0;
	}
	function clearAllDiscardedTiles() {
		for ( var i = 0; i < 21; i++) {
			var canvasId = "p" + playerId + "dt" + i;
			$("#"+canvasId).remove();
		}

	}
	

	
	function drawDiscardedTile(playerId,tile,discardedIndex,isRichi){
		var column = 0;
		var b = discardedIndex;
		while(b >= 6 ){
			b = b- 6;
			column++;
		}
		var divTop = (60 * column + (isRichi ? 8 : 0)) + "px";
		var divLeft = ((45 * (discardedIndex % 6)) + (richiFlagMap[playerId][column] ? 15 : 0)) + "px";
		var divHeight = isRichi ? "50px" : "60px";
		var divWidth =  isRichi ? "60px" : "50px";
		
		var div = $("<div/>");
		div.css("position","absolute");
		div.css("top",divTop);
		div.css("left",divLeft);
		div.css("height",divHeight);
		div.css("width",divWidth);

	
		if(isRichi){
			div.css("-webkit-transform","rotate(90deg)");
			div.css("-moz-transform","rotate(90deg)");
		}
				
		var canvasWidth = "45px";
		var canvasHeight = "55px";
		var canvasId = "p" + playerId + "d" + discardedIndex;
		var canvas = $("<canvas/>");
		canvas.attr("id",canvasId);
		canvas.attr("width",canvasWidth);
		canvas.attr("height",canvasHeight);
		
		div.append(canvas);
		$( "#discarded" + playerId).append(div);
		tileDrawing.drawTile(tile, canvasId);	
		
		if(isRichi){
			richiFlagMap[playerId][column] = true;
		}
	}
	function drawWallTile(playerId,tile,wallIndex){
		var divLeft = (45 * wallIndex) + "px";
		var divHeight = "60px";
		var divWidth = "50px";
		
		var div = $("<div/>");
		div.css("position","absolute");
		div.css("left",divLeft);
		div.css("height",divHeight);
		div.css("width",divWidth);
		
		var canvasWidth = "45px";
		var canvasHeight = "55px";
		var canvasId = "p" + playerId + "w" + wallIndex;
		var canvas = $("<canvas/>");
		canvas.attr("id",canvasId);
		canvas.attr("width",canvasWidth);
		canvas.attr("height",canvasHeight);
		
		div.append(canvas);
		$("#wall" + playerId).append(div);
		tileDrawing.drawTile(tile, canvasId);	

	}
	
	function drawHuroTile(playerId,tile,index,huroIndex,huroTileNumber,isStolen){

		var divLeft = ((-120 * (huroIndex - 1) - 40 * huroTileNumber)  + (36 * index)) + "px";
		var divHeight = isStolen? "45px" : "37px";
		var divWidth = isStolen? "37px" : "45px";

		
		var div = $("<div/>");
		
		div.css("position","absolute");
		if(isStolen){
			div.css("top","4px");
		}
		div.css("left",divLeft);

		if(isStolen){
			div.css("-webkit-transform","rotate(90deg)");
			div.css("-moz-transform","rotate(90deg)");
		}
		div.css("height",divHeight);
		div.css("width",divWidth);
	
		var canvasWidth = "37px";
		var canvasHeight = "45px";
		var canvasId = "p" + playerId + "s" + huroIndex + "" + index;
		var canvas = $("<canvas/>");
		canvas.attr("id",canvasId);
		canvas.attr("width",canvasWidth);
		canvas.attr("height",canvasHeight);
		
	
		div.append(canvas);
		$("#huro" + playerId).append(div);
		tileDrawing.drawTile(tile, canvasId);	

	}
	
	var clientId = "";

	return {

		getMessageAutomatically : function getMessageAutomatically() {
			var waitingTime = $("#auto_waiting_time").val();
			timerId = setInterval(sb.getNextMessage, waitingTime);
		},

		getNextMessage : function getNextMessage() {
			var host = "/SmartBirdRestfulServer/webapi/endpoint/next/" + clientId;
			$.ajax({
				type : 'GET',
				url : host,
				success : function(message) {
					$('#log').append(message + "<br/>");
					var json = JSON.parse(message);
					var operation = json.operation;
					var playerId = json.playerId;
					var value = json.value;
					
					operationFunc[operation](playerId, value);

				},
				error : function(d) {
					alert("error");
				},

			});
		},
		startGame : function startGame(_clientId) {
			clientId = _clientId;
			var	host = "/SmartBirdRestfulServer/webapi/endpoint/start/" + clientId;
			$.ajax({
				type : 'GET',
				url : host,
				success : function(d) {
					alert("started");
				},
				error : function(d) {
					alert("error");
				},

			});

		},
	};

})();
