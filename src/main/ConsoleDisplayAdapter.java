public class ConsoleDisplayAdapter implements GameDisplay {
    private final TerminalOutput terminalOutput;

    public ConsoleDisplayAdapter(TerminalOutput terminalOutput) {
        this.terminalOutput = terminalOutput;
    }

    @Override
    public void showMessage(String message) {
        terminalOutput.printLine(message);
    }

    @Override
    public void showPrompt(String prompt) {
        terminalOutput.printText(prompt);
    }
}