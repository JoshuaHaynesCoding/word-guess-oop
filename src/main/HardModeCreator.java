public class HardModeCreator extends GameModeCreator {
    @Override
    public GameConfig createGameConfig() {
        return new GameConfig("CLANDESTINE OP", 4, new MilitaryEvaluationStrategy());
    }
}