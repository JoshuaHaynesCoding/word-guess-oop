public class ConsoleGameLogger implements GameEventListener {
    @Override
    public void onGameEvent(String eventType, String message) {
        System.out.println("[LOG] " + eventType + ": " + message);
    }
}