import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo")
public class EchoServer {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has established the connection.");
        try {
            session.getBasicRemote().sendText("Connection Established.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
