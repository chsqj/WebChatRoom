//获取请求路径中的变量值
function getQueryVariable(variable)
{
   var query = window.location.search.substring(1);
   var vars = query.split("&");
   for (var i=0;i<vars.length;i++) {
           var pair = vars[i].split("=");
           if(pair[0] == variable){return pair[1];}
   }
   return(false);
}

//初始化聊天界面
function initData(){
	var username = getQueryVariable("username");
	$("#username").append(username);
	WebSocketTest(username);
}

//客户端与服务器的数据交流
function WebSocketTest(username){
	if ("WebSocket" in window) {
		ws = new WebSocket("ws://localhost:8080/Chat/Test?username="+username);
		//建立连接
		ws.onopen = function()
        {
			ws.send(username+"已登录");
        };
        //接收数据事件
        ws.onmessage = function (evt) 
        { 
        	if(evt.data.indexOf("nameAdd") == 0) {
        		$("#users").append(evt.data.substring(7));
        	}else if(evt.data.indexOf("nameDelete") == 0){
        		$("#"+evt.data.substring(10)).remove();
        	}else if(evt.data.indexOf("record") == 0) {
        		$("#record").append(evt.data.substring(6));
        	}else {
        		alert("意料外的数据"+evt.data);
        		ws.close();
        	}
        	
        };
        //关闭连接后
        ws.onclose = function()
        { 
           alert(username+"已下线"); 
        };
	} else {
		alert("您的浏览器不支持 WebSocket!");
	}
}

//发送消息并清空输入栏
function sendNewMessage() {
	var mg = $("#message").val();
	ws.send(mg);
	$("#message").val("");
}

//切换消息接受者
function callUser(username){
	$("#record").empty();
	ws.send("recipient : "+username);
}

//关闭连接
function quitSession(){
	ws.close();
}