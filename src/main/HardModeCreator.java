public class HardModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("Hard Mode", 4, new MilitaryEvaluationStrategy());
    }
}