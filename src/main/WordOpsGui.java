import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class WordOpsGui extends JFrame {
    private JComboBox<String> modeBox;
    private JTextField guessField;
    private JTextPane feedbackArea;
    private JLabel promptLabel;
    private JLabel guessesLabel;

    private WordProvider wordProvider;
    private GuessEvaluator guessEvaluator;
    private GameConfig gameConfig;
    private CommandParser commandParser;
    private GameContext context;
    private GameEventManager eventManager;
    private ScoreTracker scoreTracker;
    private GuiDisplayAdapter display;

    private String secretWord;
    private int guessesUsed;
    private boolean hasWon;
    private boolean hasQuit;

    public WordOpsGui() {
        setTitle("Word Ops");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        buildGui();
        startNewGame();
    }

    private void buildGui() {
        BackgroundPanel root = new BackgroundPanel();
        root.setLayout(new BorderLayout(15, 15));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        Font titleFont = new Font(Font.MONOSPACED, Font.PLAIN, 42);
        Font terminalFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        Font smallTerminalFont = new Font(Font.MONOSPACED, Font.BOLD, 12);
        Font inputFont = new Font(Font.MONOSPACED, Font.BOLD, 14);

        JLabel title = new JLabel("W.O.R.D. O.P.S.", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(new Color(230, 230, 210));
        root.add(title, BorderLayout.NORTH);

        feedbackArea = new JTextPane();
        feedbackArea.setEditable(false);
        feedbackArea.setFont(terminalFont);
        feedbackArea.setForeground(new Color(190, 255, 190));
        feedbackArea.setBackground(new Color(2, 8, 4));
        feedbackArea.setCaretColor(new Color(57, 255, 20));
        feedbackArea.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(57, 255, 20), 2),
    BorderFactory.createEmptyBorder(14, 14, 14, 14)
));

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setPreferredSize(new Dimension(720, 320));
        root.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);

        JPanel statusPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        statusPanel.setOpaque(false);

        promptLabel = new JLabel("Enter a guess.");
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setFont(smallTerminalFont);

        guessesLabel = new JLabel("Guesses: 0");
        guessesLabel.setForeground(Color.WHITE);
        guessesLabel.setFont(smallTerminalFont);
        guessesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusPanel.add(promptLabel);
        statusPanel.add(guessesLabel);

        JPanel terminalWrapper = new JPanel(new BorderLayout());
        terminalWrapper.setOpaque(false);
        terminalWrapper.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));
        terminalWrapper.add(scrollPane, BorderLayout.CENTER);
        root.add(terminalWrapper, BorderLayout.CENTER);
        

        modeBox = new JComboBox<>(new String[] {
        "Boot Camp",
        "Standard Mission",
        "Clandestine Op"
        });

        guessField = new JTextField();
        guessField.setFont(inputFont);
        guessField.setForeground(new Color(57, 255, 20));
        guessField.setBackground(new Color(2, 8, 4));
        guessField.setCaretColor(new Color(57, 255, 20));
        guessField.setBorder(BorderFactory.createLineBorder(new Color(57, 255, 20), 1));
        JButton submitButton = new JButton("execute");
        JButton helpButton = new JButton("intel");
        JButton quitButton = new JButton("abort");
        JButton restartButton = new JButton("reset");

        styleButton(submitButton);
        styleButton(helpButton);
        styleButton(quitButton);
        styleButton(restartButton);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 8, 8));
        buttonPanel.setOpaque(false);
        buttonPanel.add(submitButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(restartButton);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setOpaque(false);
        
        inputPanel.add(modeBox, BorderLayout.WEST);
        inputPanel.add(guessField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        bottomPanel.add(statusPanel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.SOUTH);

        root.add(bottomPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> submitInput());
        helpButton.addActionListener(e -> runCommand("help"));
        quitButton.addActionListener(e -> runCommand("quit"));
        restartButton.addActionListener(e -> startNewGame());

        guessField.addActionListener(e -> submitInput());
        modeBox.addActionListener(e -> startNewGame());
    }

    private void startNewGame() {
        String selectedMode = modeBox.getSelectedItem().toString();

if (selectedMode.equals("Boot Camp")) {
    selectedMode = "training";
} else if (selectedMode.equals("Standard Mission")) {
    selectedMode = "classic";
} else if (selectedMode.equals("Clandestine Op")) {
    selectedMode = "hard";
}

        GameModeCreator gameModeCreator = GameModeSelector.selectCreator(selectedMode);
        gameConfig = gameModeCreator.createGameConfig();

        wordProvider = new WordProvider();
        guessEvaluator = new GuessEvaluator(gameConfig.getEvaluationStrategy());
        commandParser = new CommandParser();

        secretWord = wordProvider.getRandomWord();
        context = new GameContext(wordProvider, guessEvaluator, gameConfig, secretWord);

        eventManager = new GameEventManager();
        scoreTracker = new ScoreTracker();
        eventManager.addListener(scoreTracker);

        display = new GuiDisplayAdapter(feedbackArea, promptLabel);
        display.clear();

        guessesUsed = 0;
        hasWon = false;
        hasQuit = false;

        guessField.setText("");
        guessField.setEnabled(true);

        display.showMessage("****************************************");
display.showMessage("*        CLASSIFIED INFORMATION        *");
display.showMessage("*          WORD OPS TERMINAL           *");
display.showMessage("****************************************");
display.showMessage("");
display.showMessage("SECURITY PRIVILEGES REQUIRED");
display.showMessage("USER: FIELD_AGENT");
display.showMessage("PASSWORD: ********");
display.showMessage("ACCESS LEVEL: RESTRICTED");
display.showMessage("");
display.showMessage("SYSTEM: MILITARY INTELLIGENCE WORD DATABASE");
display.showMessage("MODE: " + gameConfig.getModeName().toUpperCase());
display.showMessage("THEME: " + GameSettings.getInstance().getThemeName().toUpperCase());
display.showMessage("");
display.showMessage("MISSION BRIEF:");
display.showMessage("IDENTIFY THE " + GameSettings.getInstance().getWordLength()
    + "-LETTER CHALLENGE PHRASE.");
display.showMessage("TRIES AUTHORIZED: " + gameConfig.getMaxGuesses());
display.showMessage("");
display.showMessage(gameConfig.getEvaluationStrategy().getInstructions());
display.showMessage("");
display.showMessage("COMMANDS: help | quit | restart");
display.showMessage("AWAITING INPUT...");
display.showMessage("");

        eventManager.notifyListeners("GAME_STARTED", "Started " + gameConfig.getModeName());
        updateStatus();
    }

    private void submitInput() {
        String input = guessField.getText().trim();

        if (input.isEmpty()) {
            return;
        }

        guessField.setText("");
        runCommand(input);
    }

    private void runCommand(String input) {
        if (hasWon || hasQuit) {
            return;
        }

        GameCommand command = commandParser.parse(input);
        CommandResult result = command.execute(context);

        display.showMessage("> " + input);
        display.showMessage(result.getMessage());
        display.showMessage("");

        if (result.countsAsGuess()) {
            guessesUsed++;
            eventManager.notifyListeners("GUESS_SUBMITTED", "Player submitted a valid guess.");
        } else if (!result.isGameQuit() && !input.trim().equalsIgnoreCase("help")) {
            eventManager.notifyListeners("INVALID_GUESS", "Player submitted an invalid guess.");
        }

        if (result.isGameWon()) {
            hasWon = true;
            eventManager.notifyListeners("GAME_WON", "Player guessed the secret word.");
            Toolkit.getDefaultToolkit().beep();
            endGame();
        }

        if (result.isGameQuit()) {
            hasQuit = true;
            eventManager.notifyListeners("GAME_QUIT", "Player quit the mission.");
            endGame();
        }

        if (!hasWon && !hasQuit && guessesUsed >= gameConfig.getMaxGuesses()) {
            display.showMessage("Mission failed, we'll get 'em next time. The phrase was: " + secretWord);
            eventManager.notifyListeners("GAME_LOST", "Player ran out of guesses.");
            Toolkit.getDefaultToolkit().beep();
            endGame();
        }

        updateStatus();
    }

    private void endGame() {
        guessField.setEnabled(false);
        display.showMessage(scoreTracker.getSummary());
    }

    private void updateStatus() {
        int remaining = gameConfig.getMaxGuesses() - guessesUsed;
        guessesLabel.setText("Guesses used: " + guessesUsed + " | Remaining: " + remaining);
        display.showPrompt("Enter guess #" + (guessesUsed + 1) + " or command.");
    }

    private void styleButton(JButton button) {
    button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
    button.setForeground(new Color(220, 255, 220));
    button.setBackground(new Color(2, 8, 4));
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(57, 255, 20), 1),
        BorderFactory.createEmptyBorder(6, 12, 6, 12)
    ));
}

    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            File imageFile = new File("assets/background.png");

            if (imageFile.exists()) {
                backgroundImage = new ImageIcon(imageFile.getPath()).getImage();
            }
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g2 = (Graphics2D) graphics;

            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2.setColor(new Color(18, 31, 22));
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(45, 64, 41));
                g2.fillOval(-120, -100, 400, 300);

                g2.setColor(new Color(20, 45, 35));
                g2.fillOval(600, 350, 500, 350);

                g2.setColor(new Color(90, 85, 55, 120));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordOpsGui().setVisible(true));
    }
}