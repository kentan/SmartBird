var tileUtil = (function() {
	var dt_pos = {
		0 : 0,
		1 : 0,
		2 : 0,
		3 : 0
	};
	var tilePos = {};

	var operationFunc = {
		add : addTile,
		chow : chow,
		pong : pong,
		richi : richi,
		ron : ron,
		tumo : tumo,
		stealKong : stealKong,
		wallKong : wallKong,
		discard : discardTile,
		init : initTiles,
		startRound : startRound,
		finishRound : finishRound,
	};
	function addTile(playerId, values) {
		clearAllTiles(playerId);

		drawAllTiles(playerId, values.wall);
	}
	function chow(playerId, values) {

	}
	function pong(playerId, values) {

	}
	function richi(playerId, values) {

	}
	function ron(playerId, values) {

	}
	function tumo(playerId, values) {

	}
	function stealKong(playerId, values) {

	}
	function wallKong(playerId, values) {

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

	function discardTile(playerId, values) {
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

	function initTiles(playerId, values) {
		drawAllTiles(playerId, values.initTiles);
	}
	function startRound(playerId, values) {
		var dora = values.dora;
		
		var canvasId = "dora0";
		tileDrawing.drawTile(dora, canvasId);
	}
	var timerId;
	function finishRound(playerId, value) {
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
			;
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
