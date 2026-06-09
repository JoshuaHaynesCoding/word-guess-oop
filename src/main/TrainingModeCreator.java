public class TrainingModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("Training Mode", 999, new MilitaryEvaluationStrategy());
    }
}