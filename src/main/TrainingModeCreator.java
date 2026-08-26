public class TrainingModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("BOOT CAMP", 999, new MilitaryEvaluationStrategy());
    }
}