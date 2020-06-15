package chat.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import chat.JavaBean.ChatRecordBean;
import chat.Service.ChatRecordService;

public class ChatSocketHandle extends TextWebSocketHandler{
	
	private static Map<String, WebSocketSession> sessions = new	ConcurrentHashMap<>();
	
	@Autowired
	ChatRecordService service;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		session.getAttributes().put("recipient", "all");//登录默认接受者为all
		//遍历已登录用户名称集合并打印
		for(String name : sessions.keySet()){
			session.sendMessage(new TextMessage(parseNameAdd(name)));
		}
		//获取当前登录名并加入session集合
		String username = (String) session.getAttributes().get("username");
		System.out.println(sessions.keySet().contains(username));
		if(!sessions.keySet().contains(username)){
			sessions.put(username, session);
			//向所有session会话发出当前用户已登录信息
			for(WebSocketSession webSocketSession : sessions.values()) {
				webSocketSession.sendMessage(new TextMessage(parseNameAdd(username)));
			}
			sendMessageFromDB(session);
		}else {
			session.sendMessage(new TextMessage(username+"已登录"));
		}
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("1111111");
		String username = (String) session.getAttributes().get("username");
		String sendMessage = message.getPayload().toString();
		//为了区分浏览器端发来的消息是要操作用户名集合和消息记录集合
		if(sendMessage.startsWith("recipient : ")){
			session.getAttributes().put("recipient", sendMessage.substring(12));
			sendMessageFromDB(session);
		}else {
			String recipient = (String) session.getAttributes().get("recipient");
			service.addChatRecord(new ChatRecordBean(username, recipient, sendMessage));
			if(recipient.equals("all")) {
				for(WebSocketSession socketSession : sessions.values()) {
					socketSession.sendMessage(new TextMessage("record" + username + " : " + sendMessage + "<br>"));
				}
			} else {
				String privMessage = "record" + username + " : " + sendMessage + "(私聊)<br>";
				sessions.get(username).sendMessage(new TextMessage(privMessage));
				sessions.get(recipient).sendMessage(new TextMessage(privMessage));
			}
		}
		super.handleMessage(session, message);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		String username = (String) session.getAttributes().get("username");
		sessions.remove(username);
		service.addChatRecord(new ChatRecordBean(username, "all", username+"下线了"));
		for(WebSocketSession socketSession : sessions.values()){
			socketSession.sendMessage(new TextMessage(parseNameDelete(username)));
			System.out.println("222222"+parseNameDelete(username));
		}
		super.afterConnectionClosed(session, status);
	}
	
	//将用户名转化为页面所需html语句
	public String parseNameAdd(String username) {
		return "nameAdd<div id='"+username+"'; onclick='callUser(&quot;"+username+"&quot;)'>" + username+"</div>";
	}
	
	public String parseNameDelete(String username) {
		return "nameDelete"+username;
	}
	
	//发送聊天记录
	public void sendMessageFromDB(WebSocketSession session) throws IOException {
		String recipient = (String) session.getAttributes().get("recipient");
		String username = (String) session.getAttributes().get("username");
		ArrayList<ChatRecordBean> records = service.getChatRecord(recipient, username);
		for(ChatRecordBean bean : records) {
			session.sendMessage(new TextMessage("record" + bean.getUsername()+" ： "+bean.getSentence()+"<br>"));
		}
	}
}
