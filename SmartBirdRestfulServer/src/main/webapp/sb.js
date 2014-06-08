var sb = (function() {
	var dt_pos = {
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
	var stolenPosMap = {};
	stolenPosMap[0] = {1:0,2:1,3:2};
	stolenPosMap[1] = {1:0,2:1, "-1":2};
	stolenPosMap[2] = {1:0,"-2":1,"-1":2};
	stolenPosMap[3] = {"-1":0,"-2":1,"-3":2};
	
	function drawHuro(playerId,stolenTile,stolenPos,tiles){
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
		drawHuro(playerId,stolenTile,pos,tiles);
		drawDiscardedTile(playerId, discardingTile,dt_pos[playerId]);
	}
	function ofChow(playerId, values) {
		naki(playerId,values);
//		var stolenTile = values.stolenTile;
//		var huro1 = values.huro1;
//		var huro2 = values.huro2;
//		var discardingTile = values.discardedTile;
//		var wall = values.wall;
//		var stolenPlayerId = values.stolenPlayerId;
//		
//		clearAllTiles(playerId);
//		drawAllTiles(playerId, wall);
//		
//		var pos = stolenPosMap[playerId][stolenPlayerId];
//		drawHuro(playerId,stolenTile,pos,[huro1,huro2]);
//		drawDiscardedTile(playerId, discardingTile,dt_pos[playerId]);
	}
	function ofPong(playerId, values) {
		naki(playerId,values);
	}
	function ofRichi(playerId, values) {

	}
	function ofRon(playerId, values) {

	}
	function ofTumo(playerId, values) {

	}
	function ofStealKong(playerId, values) {
		naki(playerId,values);
	}
	function ofWallKong(playerId, values) {

	}

	function refreshTiles(playerId, tiles) {
		clearAllTiles(playerId);

		drawAllTiles(playerId, tiles);
	}
	
//	function drawTile(playerId, tile, pos, emphasize) {
//		if (pos > 14) {
//			return;
//		}
//
//		var canvasId = "p" + playerId + "w" + pos;
//		tileDrawing.drawTile(tile, canvasId);
//	}

//	function drawDiscardedTile(playerId, tile) {
//		if (dt_pos[playerId] > 24) {
//			return;
//		}
//		var canvasId = "p" + playerId + "dt" + dt_pos[playerId];
//		// tileFunc[tile](canvasId);
//		tileDrawing.drawTile(tile, canvasId);
//		dt_pos[playerId] = dt_pos[playerId] + 1;
//	}

	function ofDiscardTile(playerId, values) {
		var tile = values.discardedTile;
		var posOfTheTile = tilePos[tile];
		var canvasId = "p" + playerId + "w" + posOfTheTile;
		tileDrawing.clearTile(canvasId);
		drawDiscardedTile(playerId, tile,dt_pos[playerId]);
		dt_pos[playerId] = dt_pos[playerId] + 1;

	}
	function drawAllTiles(playerId, tiles) {

		for ( var i = 0; i < tiles.length; i++) {
			var tile = tiles[i];

			//drawTile(playerId, tile, i);
			drawWallTile(playerId, tile,i);
			tilePos[tile] = i;
		}

	}

	function ofInitTiles(playerId, values) {
		drawAllTiles(playerId, values.initTiles);
	}
	function ofStartRound(playerId, values) {
		var dora = values.dora;
		
		var canvasId = "dora0";
		tileDrawing.drawTile(dora, canvasId);
	}
	var timerId;
	function ofFinishRound(playerId, value) {
		clearInterval(timerId);
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
	

	
	function drawDiscardedTile(playerId,tile,discardedIndex){
		var a = 0;
		var b = discardedIndex;
		while(b >= 6 ){
			b = b- 6;
			a++;
		}
		var divTop = (60 * a) + "px";
		var divLeft = (45 * (discardedIndex % 6)) + "px";
		var divHeight = "60px";
		var divWidth = "50px";
		
		var div = $("<div/>");
		div.css("position","absolute");
		div.css("top",divTop);
		div.css("left",divLeft);
		div.css("height",divHeight);
		div.css("width",divWidth);
		
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
		
	}
	function drawWallTile(playerId,tile,wallIndex){
//		var divTop = "200px";
		var divLeft = (45 * wallIndex) + "px";
		var divHeight = "60px";
		var divWidth = "50px";
		
		var div = $("<div/>");
		div.css("position","absolute");
//		div.css("top",divTop);
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
	
		
//		var canvasWidth = isStolen? "37px" : "45px";
//		var canvasHeight = isStolen? "45px" : "37px";
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
			var host = "http://localhost:8080/SmartBirdRestfulServer/webapi/endpoint/next/" + clientId;

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
			var	host = "http://localhost:8080/SmartBirdRestfulServer/webapi/endpoint/start/" + clientId;
			// socket = new WebSocket(host);
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
