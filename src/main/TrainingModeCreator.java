public class TrainingModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("Training Mode", 6, new MilitaryEvaluationStrategy());
    }
}