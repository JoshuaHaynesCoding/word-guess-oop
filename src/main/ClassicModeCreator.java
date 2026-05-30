public class ClassicModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("Classic Mode", 6, new ClassicEvaluationStrategy());
    }
}