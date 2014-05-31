
var tileUtil = (function(){

	var recWidth = 28;
	var recHeight = 36;
	var rec_center = 3;
	var radius = 8;
	var dt_pos = {0:0,1:0,2:0,3:0};
	var bar_len_harf = 4;
	var bar_with = 5;
	var tilePos = {};
	
	var operationFunc = {add:addTile,
			chow:chow,
			pong:pong,
			richi:richi,
			ron:ron,
			tumo:tumo,
			stealKong:stealKong,
			wallKong:wallKong,
			discard:discardTile,
			init:initTiles,
			startRound:startRound,
			finishRound:finishRound,
	};
	
//
// 	
//	function _drawCircle1(id){
//		drawCircle1(id,"#0000ff")
//	}
//	function _drawCircle2(id){
//		drawCircle2(id,"#0000ff")
//	}
//	function _drawCircle3(id){
//		drawCircle3(id,"#0000ff")
//	}
//	function _drawCircle4(id){
//		drawCircle4(id,"#0000ff")
//	}
//	function _drawCircle5(id){
//		drawCircle5(id,"#0000ff")
//	}
//	function _drawCircle6(id){
//		drawCircle6(id,"#0000ff")
//	}
//	function _drawCircle7(id){
//		drawCircle7(id,"#0000ff")
//	}
//	function _drawCircle8(id){
//		drawCircle8(id,"#0000ff")
//	}
//	function _drawCircle9(id){
//		drawCircle9(id,"#0000ff")
//	}
//	function _drawBamboo1(id){
//		drawCircle1(id,"#228B22")
//	}
//	function _drawBamboo2(id){
//		drawCircle2(id,"#228B22")
//	}
//	function _drawBamboo3(id){
//		drawCircle3(id,"#228B22")
//	}
//	function _drawBamboo4(id){
//		drawCircle4(id,"#228B22")
//	}
//	function _drawBamboo5(id){
//		drawCircle5(id,"#228B22")
//	}
//	function _drawBamboo6(id){
//		drawCircle6(id,"#228B22")
//	}
//	function _drawBamboo7(id){
//		drawCircle7(id,"#228B22")
//	}
//	function _drawBamboo8(id){
//		drawCircle8(id,"#228B22")
//	}
//	function _drawBamboo9(id){
//		drawCircle9(id,"#228B22")
//	}
//
//	function _drawCharactor1(id){
//		drawCircle1(id,"#ff0000")
//	}
//	function _drawCharactor2(id){
//		drawCircle2(id,"#ff0000")
//	}
//	function _drawCharactor3(id){
//		drawCircle3(id,"#ff0000")
//	}
//	function _drawCharactor4(id){
//		drawCircle4(id,"#ff0000")
//	}
//	function _drawCharactor5(id){
//		drawCircle5(id,"#ff0000")
//	}
//	function _drawCharactor6(id){
//		drawCircle6(id,"#ff0000")
//	}
//	function _drawCharactor7(id){
//		drawCircle7(id,"#ff0000")
//	}
//	function _drawCharactor8(id){
//		drawCircle8(id,"#ff0000")
//	}
//	function _drawCharactor9(id){
//		drawCircle9(id,"#ff0000")
//	}
	function addTile(playerId,values){
//		refreshTiles(playerId,values);
		clearAllTiles(playerId);

		drawAllTiles(playerId,values.wall);
	}
	function chow(playerId, values){
		
	}
	function pong(playerId, values){
		
	}
	function richi(playerId, values){
		
	}
	function ron(playerId,values){
		
	}
	function tumo(playerId,values){
		
	}
	function stealKong(playerId,values){
		
	}
	function wallKong(playerId,values){
		
	}

	function refreshTiles(playerId,tiles){
		clearAllTiles(playerId);

		drawAllTiles(playerId,tiles);
	}
	function drawTile(playerId,tile,pos,emphasize){
		if(pos > 14){
			return ;
		}

		var canvasId = "p" + playerId + "w" + pos;
		tileDrawing.drawTile(tile,canvasId);
    }
	
	function drawDiscardedTile(playerId,tile){
		if(dt_pos[playerId] > 24){
			return ;
		}
		var canvasId = "p" + playerId + "dt" + dt_pos[playerId];
//		tileFunc[tile](canvasId);
		tileDrawing.drawTile(tile,canvasId);
		dt_pos[playerId] = dt_pos[playerId] + 1;
	}	
	
	function discardTile(playerId,values){
		var tile = values.discardedTile;
		var posOfTheTile = tilePos[tile];
		var canvasId = "p" + playerId + "w" + posOfTheTile;
		tileDrawing.clearTile(canvasId);
		drawDiscardedTile(playerId,tile);

	}
	function drawAllTiles(playerId,tiles){

		for(var i=0; i < tiles.length; i++){
			var tile = tiles[i];

			drawTile(playerId,tile,i);
			tilePos[tile] = i;
		}

	}

	function initTiles(playerId,values){
		drawAllTiles(playerId,values.initTiles);
	}
	function startRound(playerId,value){
		
	}
	var timerId;
	function finishRound(playerId,value){
		clearAllTiles(playerId);
		clearAllDiscardedTiles(playerId);
		clearTimeout(timerId);
	}


//   function drawRoundRect(context, x, y, width, height, radius, fill, stroke,strokeStyle) {
//      if (typeof stroke == "undefined" ) {
//    	  stroke = true;
//      }
//	  context.beginPath();
//	  context.moveTo(x + radius, y);
//	  context.lineTo(x + width - radius, y);
//	  context.quadraticCurveTo(x + width, y, x + width, y + radius);
//	  context.lineTo(x + width, y + height - radius);
//	  context.quadraticCurveTo(x + width, y + height, x + width - radius, y + height);
//	  context.lineTo(x + radius, y + height);
//	  context.quadraticCurveTo(x, y + height, x, y + height - radius);
//	  context.lineTo(x, y + radius);
//	  context.quadraticCurveTo(x, y, x + radius, y);
//	  context.closePath();
//	  context.strokeStyle = strokeStyle;
//	  context.fillStyle = 'rgb(255, 255, 255)';
//	  context.fill();
//	  if (stroke) {
//	    context.stroke();
//	  }
//
////	  context.lineWidth = 15;
//	  context.lineWidth = bar_with;
//   	}
   
  function clearAllTiles(playerId){
	  for(var i = 0; i < 14; i++){
		  var canvasId = "p" + playerId + "w" + i;
		  tileDrawing.clearTile(canvasId);
	  }
	  pos = 0;
  }
  function clearAllDiscardedTiles(){
	  for(var i = 0; i < 21; i++){
		  var canvasId = "p" + playerId + "dt" + i;
		  tileDrawing.clearTile(canvasId);
	  }
	  dt_pos = {0:0,1:0,2:0,3:0};
  }
//  function clearTile(id){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
//	  context.clearRect(0, 0, canvas.width, canvas.height);
//  }
//  function drawCircle(context,x,y){
//      
//      var centerX = x;
//      var centerY = y;
//
//
//      context.beginPath();
//      context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
//      context.fillStyle = 'silver';
//  context.fill();
//  context.lineWidth = 5;
//  context.strokeStyle = '#003300';
//      context.stroke();
//       
//  }
//
//  function drawCircle1(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = rec_center;
//	  var centerY = canvas.height/2;
//  //drawCircle(context,centerX,centerY);
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf);
//      context.stroke();
//  }
//  function drawCircle2(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = rec_center;
//	  var centerY = canvas.height/2;
//	  //drawCircle(context,centerX,centerY);
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 20);
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2);
//      //      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - 10);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf);
//      context.stroke();
//      
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//  }
//    
//  function drawCircle3(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = rec_center;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - 10);
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf);
//      context.stroke();
//      
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 - 10,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 - 10,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2 - bar_len_harf,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 + 10,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 + 10,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2 + bar_len_harf,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//
//  } 
//
//  function drawCircle4(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = rec_center;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 - 10,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2 - 10,centerY + recHeight/2 - 10);
//      context.moveTo(centerX + recWidth/2 - bar_len_harf,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf,centerY + recHeight/2 - bar_len_harf);
//      context.stroke();
//
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 + 10,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2 + 10,centerY + recHeight/2 - 10);
//      context.moveTo(centerX + recWidth/2 + bar_len_harf,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf,centerY + recHeight/2 - bar_len_harf);
//
//      context.stroke();
//
//      
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 - 10,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 - 10,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2 - bar_len_harf,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 + 10,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 + 10,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2 + bar_len_harf,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//
//  } 
//
//  function drawCircle5(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = rec_center;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 10);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 20);
//      context.stroke();
//
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 5);
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf/2);
//      context.stroke();
//
//  } 
//    
//  function drawCircle6(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = rec_center;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 10);
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - 10);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 10);
//      context.stroke();
//
//      
//      context.beginPath();
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 20);
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 2);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 20);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 10);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 20);
//      context.stroke();
//
//  } 
//    
//  function drawCircle7(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
////	  var centerX = x;
//	  var centerX = 3;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2.5);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 1.5);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 25);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - 15);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 5);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 5);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 5);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 25);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 25);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 25);
//      context.stroke();
//
//  }
//    
//  function drawCircle8(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
//      var centerX = rec_center;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2.5);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf / 2);
//
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 25);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 5);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - 20);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 20);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 5);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2.5);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 25);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 5);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf /2);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 5);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 25);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf*1.5);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 5);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 15);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 5);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 5);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 25);
//      context.stroke();
//
//  } 
//    
//  function drawCircle9(id,strokeStyle){
//      var canvas = document.getElementById(id);
//      var context = canvas.getContext('2d');
//      var centerX = rec_center;
//      var centerY = canvas.height/2;
//
//      drawRoundRect(context,centerX,centerY,recWidth,recHeight,radius,false,true,strokeStyle);
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2.5);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 1.5);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 25);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 15);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 2.5);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf * 1.5);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 25);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 - 15);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 2.5);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf * 1.5);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 25);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 15);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 5);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 5);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 - bar_len_harf/2);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf/2);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 - 5);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 5);
//      context.stroke();
//
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2 - bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2 - 15,centerY + recHeight/2 + 25);
//      context.stroke();
//
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2,centerY + recHeight/2 + 25);
//      context.stroke();
//      
//      context.beginPath();
//      context.moveTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 1.5);
//      context.lineTo(centerX + recWidth/2 + bar_len_harf * 1.5,centerY + recHeight/2 + bar_len_harf * 2.5);
////      context.moveTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 15);
////      context.lineTo(centerX + recWidth/2 + 15,centerY + recHeight/2 + 25);
//      context.stroke();
//      
//      
//  };
  var rv = {

	  getMessageAutomatically : function getMessageAutomatically(){
  		timerId = setInterval(getNextMessage,1000);
  	  },
  	
  	  getNextMessage: function getNextMessage(){
    	var host="http://localhost:8080/SmartBirdRestfulServer/webapi/endpoint/next";;
    	$.ajax({
    	    type: 'GET',
    	    url: host,
    	    success: function(message) {
	            $('#log').append(message + "<br/>");
	        	var json = JSON.parse(message);
	        	var operation = json.operation;
				var playerId = json.playerId;
				
				if(operation == "finishRound"){
		        	operationFunc[operation](value);
				}else{
					var value = json.value;          
		        	operationFunc[operation](playerId,value);					

				}
    	    },
    	    error: function( d) {
    	        alert("error");
    	    },
    	    
      });
  	},
  	startGame : function startGame(){
    	var host="http://localhost:8080/SmartBirdRestfulServer/webapi/endpoint/start";;
//        socket = new WebSocket(host);
    	$.ajax({
    	    type: 'GET',
    	    url: host,
    	    success: function(d) {
    	        alert("started");
    	    },
    	    error: function( d) {
    	        alert("error");
    	    },
    	    
        });

    }
  };
  return rv;
})();


