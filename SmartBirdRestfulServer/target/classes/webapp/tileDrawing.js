var tileDrawing = (function() {

	var rectangle_x_pos = 3;
	var rectangle_y_pos = 3;
	var radius = 8;//8;
	var bar_len_harf = 8;//4;
	var bar_width = 5;

	var tileFunc = {
		M1 : _drawCharactor1,
		M2 : _drawCharactor2,
		M3 : _drawCharactor3,
		M4 : _drawCharactor4,
		M5 : _drawCharactor5,
		M6 : _drawCharactor6,
		M7 : _drawCharactor7,
		M8 : _drawCharactor8,
		M9 : _drawCharactor9,
		S1 : _drawBamboo1,
		S2 : _drawBamboo2,
		S3 : _drawBamboo3,
		S4 : _drawBamboo4,
		S5 : _drawBamboo5,
		S6 : _drawBamboo6,
		S7 : _drawBamboo7,
		S8 : _drawBamboo8,
		S9 : _drawBamboo9,
		P1 : _drawCircle1,
		P2 : _drawCircle2,
		P3 : _drawCircle3,
		P4 : _drawCircle4,
		P5 : _drawCircle5,
		P6 : _drawCircle6,
		P7 : _drawCircle7,
		P8 : _drawCircle8,
		P9 : _drawCircle9,
		EA : _drawEast,
		SO : _drawSouth,
		WE : _drawWest,
		NO : _drawNorth,
		D1 : _drawWhite,
		D2 : _drawGreen,
		D3 : _drawRed
	};

	function _drawCircle1(id) {
		drawCircle1(id, "#0000ff");
	}

	function _drawCircle2(id) {
		drawCircle2(id, "#0000ff");
	}

	function _drawCircle3(id) {
		drawCircle3(id, "#0000ff");
	}

	function _drawCircle4(id) {
		drawCircle4(id, "#0000ff");
	}

	function _drawCircle5(id) {
		drawCircle5(id, "#0000ff");
	}

	function _drawCircle6(id) {
		drawCircle6(id, "#0000ff");
	}

	function _drawCircle7(id) {
		drawCircle7(id, "#0000ff");
	}

	function _drawCircle8(id) {
		drawCircle8(id, "#0000ff");
	}

	function _drawCircle9(id) {
		drawCircle9(id, "#0000ff");
	}

	function _drawBamboo1(id) {
		drawCircle1(id, "#228B22");
	}

	function _drawBamboo2(id) {
		drawCircle2(id, "#228B22");
	}

	function _drawBamboo3(id) {
		drawCircle3(id, "#228B22");
	}

	function _drawBamboo4(id) {
		drawCircle4(id, "#228B22");
	}

	function _drawBamboo5(id) {
		drawCircle5(id, "#228B22");
	}

	function _drawBamboo6(id) {
		drawCircle6(id, "#228B22");
	}

	function _drawBamboo7(id) {
		drawCircle7(id, "#228B22");
	}

	function _drawBamboo8(id) {
		drawCircle8(id, "#228B22");
	}

	function _drawBamboo9(id) {
		drawCircle9(id, "#228B22");
	}

	function _drawCharactor1(id) {
		drawCircle1(id, "#ff0000");
	}

	function _drawCharactor2(id) {
		drawCircle2(id, "#ff0000");
	}

	function _drawCharactor3(id) {
		drawCircle3(id, "#ff0000");
	}

	function _drawCharactor4(id) {
		drawCircle4(id, "#ff0000");
	}

	function _drawCharactor5(id) {
		drawCircle5(id, "#ff0000");
	}

	function _drawCharactor6(id) {
		drawCircle6(id, "#ff0000");
	}

	function _drawCharactor7(id) {
		drawCircle7(id, "#ff0000");
	}

	function _drawCharactor8(id) {
		drawCircle8(id, "#ff0000");
	}

	function _drawCharactor9(id) {
		drawCircle9(id, "#ff0000");
	}
	
	function drawRoundRect(context, canvas,fill, stroke, strokeStyle) {
		if (typeof stroke == "undefined") {
			stroke = true;
		}
		var x1 = rectangle_x_pos;
		var y1 = rectangle_y_pos;
		
		var radius = 8;//canvas.width / 8;
		var width = canvas.width - 2 * rectangle_x_pos;
		var height = canvas.height - 2 * rectangle_y_pos;
		
		var pos = [
		   		[x1 + radius, y1],
		   		[x1 + width - radius, y1],
		   		[x1 + width, y1],
		   		[x1 + width, y1 + radius],
		   		[x1 + width, y1 + height - radius],
		   		[x1 + width, y1 + height],
		   		[x1 + width - radius, y1 + height],
		   		[x1 + radius, y1 + height],
		   		[x1, y1 + height], 
		   		[x1, y1 + height - radius],
		   		[x1, y1 + radius],
		   		[x1, y1,],
		   		[x1 + radius, y1]
		 ];

		context.beginPath();
		context.moveTo(pos[0][0], pos[0][1]);
		for(var i = 1; i <= 12; i = i + 3){
			context.lineTo(pos[i][0], pos[i][1]);
			context.quadraticCurveTo(pos[i + 1][0],pos[i + 1][1], pos[i + 2][0],pos[i + 2][1]);
		}
	
		context.closePath();
		context.strokeStyle = strokeStyle;
		context.lineWidth = bar_width;
		context.fillStyle = 'rgb(255, 255, 255)';
		context.fill();
		if (stroke) {
			context.stroke();
		}	
	}

	function drawTile(tile, id) {
		tileFunc[tile](id);
	}
	function clearTile(id) {
		var canvas = document.getElementById(id);
		if(canvas == null){
		}else{
			var context = canvas.getContext('2d');
			context.clearRect(0, 0, canvas.width, canvas.height);
		}
	}
	function drawCircle(context, x, y) {

		var centerX = x;
		var centerY = y;

		context.beginPath();
		context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
		context.fillStyle = 'silver';
		context.fill();
		context.lineWidth = 5;
		context.strokeStyle = '#003300';
		context.stroke();

	}

	function drawCircle1(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');

		drawRoundRect(context, canvas, false, true, strokeStyle);
		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf);
		context.stroke();
	}
	function drawCircle2(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');
		drawRoundRect(context, canvas, false, true, strokeStyle);

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();
	}

	function drawCircle3(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');
		// var centerX = x;

		drawRoundRect(context, canvas, false, true, strokeStyle);
		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 - bar_len_harf, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 + bar_len_harf, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

	}

	function drawCircle4(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');
		// var centerX = x;


		drawRoundRect(context, canvas, false, true, strokeStyle);
		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 - bar_len_harf, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 + bar_len_harf, canvas.height / 2 - bar_len_harf);

		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 - bar_len_harf, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

		context.beginPath();

		context.moveTo(canvas.width / 2 + bar_len_harf, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 + bar_len_harf, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

	}

	function drawCircle5(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');
		// var centerX = x;



		drawRoundRect(context, canvas, false, true, strokeStyle);
		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

	}

	function drawCircle6(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');

		drawRoundRect(context, canvas, false, true, strokeStyle);
		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2);
		context.stroke();

	}

	function drawCircle7(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');

		drawRoundRect(context, canvas, false, true, strokeStyle);
		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2.5);
		context.lineTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 1.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2.5);

		context.stroke();

	}

	function drawCircle8(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');

		drawRoundRect(context, canvas, false, true, strokeStyle);

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2.5);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);

		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2.5);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 1.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

	}

	function drawCircle9(id, strokeStyle) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');

		drawRoundRect(context, canvas, false, true, strokeStyle);

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2.5);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 1.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 2.5);
		context.lineTo(canvas.width / 2, canvas.height / 2 - bar_len_harf * 1.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 2.5);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf * 1.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 - bar_len_harf / 2);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf / 2);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2 - bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

		context.beginPath();
		context.moveTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 1.5);
		context.lineTo(canvas.width / 2 + bar_len_harf * 1.5, canvas.height / 2 + bar_len_harf * 2.5);
		context.stroke();

	}
	
	function drawWindAndDragon(id, strokeStyle,text,textColor) {
		var canvas = document.getElementById(id);
		var context = canvas.getContext('2d');
		drawRoundRect(context, canvas, false, true, strokeStyle);
		
		context.font      = "normal 36px monospace";
		context.fillStyle = textColor;
		context.fillText(text, canvas.width * 0.1, canvas.height * 0.7,canvas.width*0.8);
	}
	function _drawEast(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"東","#000000");
	}
	function _drawSouth(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"南","#000000");
	}
	function _drawWest(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"西","#000000");
	}
	function _drawNorth(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"北","#000000");
	}
	function _drawWhite(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"　","#000000");
	}
	function _drawGreen(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"発","#00FF00");
	}
	function _drawRed(id, strokeStyle) {
		drawWindAndDragon(id,strokeStyle,"中","#FF0000");
	}
	
	return {
		drawCharactor1 : _drawCharactor1,
		drawCharactor2 : _drawCharactor2,
		drawCharactor3 : _drawCharactor3,
		drawCharactor4 : _drawCharactor4,
		drawCharactor5 : _drawCharactor5,
		drawCharactor6 : _drawCharactor6,
		drawCharactor7 : _drawCharactor7,
		drawCharactor8 : _drawCharactor8,
		drawCharactor9 : _drawCharactor9,
		drawBamboo1 : _drawBamboo1,
		drawBamboo2 : _drawBamboo2,
		drawBamboo3 : _drawBamboo3,
		drawBamboo4 : _drawBamboo4,
		drawBamboo5 : _drawBamboo5,
		drawBamboo6 : _drawBamboo6,
		drawBamboo7 : _drawBamboo7,
		drawBamboo8 : _drawBamboo8,
		drawBamboo9 : _drawBamboo9,
		drawCircle1 : _drawCircle1,
		drawCircle2 : _drawCircle2,
		drawCircle3 : _drawCircle3,
		drawCircle4 : _drawCircle4,
		drawCircle5 : _drawCircle5,
		drawCircle6 : _drawCircle6,
		drawCircle7 : _drawCircle7,
		drawCircle8 : _drawCircle8,
		drawCircle9 : _drawCircle9,
		drawDragon1 : _drawWhite,
		drawDragon2 : _drawGreen,
		drawDragon3 : _drawRed,
		drawEast : _drawEast,
		drawWest : _drawWest,
		drawSouth : _drawSouth,
		drawNorth : _drawNorth,
		clearTile : clearTile,
		drawTile : drawTile,
	};


})();
