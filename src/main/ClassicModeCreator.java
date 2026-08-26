public class ClassicModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("STANDARD MISSION", 6, new ClassicEvaluationStrategy());
    }
}