import java.util.ArrayList;
import java.util.List;

public class GameEventManager {
    private final List<GameEventListener> listeners;

    public GameEventManager() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(String eventType, String message) {
        for (GameEventListener listener : listeners) {
            listener.onGameEvent(eventType, message);
        }
    }
}