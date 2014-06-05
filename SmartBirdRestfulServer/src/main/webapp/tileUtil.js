var tileUtil = (function() {
	var dt_pos = {
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
	function ofChow(playerId, values) {

	}
	function ofPong(playerId, values) {

	}
	function ofRichi(playerId, values) {

	}
	function ofRon(playerId, values) {

	}
	function ofTumo(playerId, values) {

	}
	function ofStealKong(playerId, values) {

	}
	function ofWallKong(playerId, values) {

	}

	function refreshTiles(playerId, tiles) {
		clearAllTiles(playerId);

		drawAllTiles(playerId, tiles);
	}
	
	function drawTile(playerId, tile, pos, emphasize) {
		if (pos > 14) {
			return;
		}

		var canvasId = "p" + playerId + "w" + pos;
		tileDrawing.drawTile(tile, canvasId);
	}

	function drawDiscardedTile(playerId, tile) {
		if (dt_pos[playerId] > 24) {
			return;
		}
		var canvasId = "p" + playerId + "dt" + dt_pos[playerId];
		// tileFunc[tile](canvasId);
		tileDrawing.drawTile(tile, canvasId);
		dt_pos[playerId] = dt_pos[playerId] + 1;
	}

	function ofDiscardTile(playerId, values) {
		var tile = values.discardedTile;
		var posOfTheTile = tilePos[tile];
		var canvasId = "p" + playerId + "w" + posOfTheTile;
		tileDrawing.clearTile(canvasId);
		drawDiscardedTile(playerId, tile);

	}
	function drawAllTiles(playerId, tiles) {

		for ( var i = 0; i < tiles.length; i++) {
			var tile = tiles[i];

			drawTile(playerId, tile, i);
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
		clearAllTiles(playerId);
		clearAllDiscardedTiles(playerId);
		clearTimeout(timerId);
	}

	function clearAllTiles(playerId) {
		for ( var i = 0; i < 14; i++) {
			var canvasId = "p" + playerId + "w" + i;
			tileDrawing.clearTile(canvasId);
		}
		pos = 0;
	}
	function clearAllDiscardedTiles() {
		for ( var i = 0; i < 21; i++) {
			var canvasId = "p" + playerId + "dt" + i;
			tileDrawing.clearTile(canvasId);
		}
		dt_pos = {
			0 : 0,
			1 : 0,
			2 : 0,
			3 : 0
		};
	}

	var rv = {

		getMessageAutomatically : function getMessageAutomatically() {
			timerId = setInterval(getNextMessage, 1000);
		},

		getNextMessage : function getNextMessage() {
			var host = "http://localhost:8080/SmartBirdRestfulServer/webapi/endpoint/next";
			;
			$.ajax({
				type : 'GET',
				url : host,
				success : function(message) {
					$('#log').append(message + "<br/>");
					var json = JSON.parse(message);
					var operation = json.operation;
					var playerId = json.playerId;

					if (operation == "finishRound") {
						operationFunc[operation](value);
					} else {
						var value = json.value;
						operationFunc[operation](playerId, value);

					}
				},
				error : function(d) {
					alert("error");
				},

			});
		},
		startGame : function startGame() {
			var host = "http://localhost:8080/SmartBirdRestfulServer/webapi/endpoint/start";
			
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

		}
	};
	return rv;
})();
