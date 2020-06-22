package chat.Interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

//传递HttpSession的信息到WebSocketSession
public class WsHandshakeInterceptor extends HttpSessionHandshakeInterceptor{
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse respone, WebSocketHandler handler,
			Map<String, Object> att) throws Exception {
		// TODO Auto-generated method stub
		return super.beforeHandshake(request, respone, handler, att);
	}
}
