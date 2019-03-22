import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/room/{roomid}")
public class ChatServer {

    @OnOpen
    public void onOpen(Session session, @PathParam("roomid") String roomid) {
        session.getUserProperties().put("roomID", roomid);
        SessionHandler.addSession(session);
        System.out.println(session.getId() + " has established the connection.");
        SessionHandler.sendToSession(session, "Connection established.");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);
        SessionHandler.sendToAllConnectedSession(message);
    }

    @OnClose
    public void onClose(Session session) {
        SessionHandler.removeSession(session);
        System.out.println("Session " + session.getId() + " has ended.");
    }
}
