import javax.websocket.Session;
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

    public static void sendToSession(Session session) {

    }
}
