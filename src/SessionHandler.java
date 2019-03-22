import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SessionHandler {
    private static final Set<Session> sessions = new HashSet<>();

    public static void addSession(Session session) {
        sessions.add(session);
    }

    public static void removeSession(Session session) {
        sessions.remove(session);
    }

    public static void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            try {
                session.close();
            } catch (IOException e) { e.printStackTrace(); }
            finally {
                removeSession(session);

            }
        }
    }

    public static void sendToAllConnectedSession(String message) {
        for (Session session: sessions) {
            sendToSession(session, message);
        }
    }

    public static void sendToAllConnectedSessionsInRoom(String roomID, String message) {
        for(Session session: sessions) {
            if(session.getUserProperties().get("roomID").equals(roomID)) {
                sendToSession(session, message);
            }
        }
    }
}
