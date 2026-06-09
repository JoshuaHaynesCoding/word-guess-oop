import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class GuiDisplayAdapter implements GameDisplay {
    private final JTextPane outputArea;
    private final JLabel promptLabel;

    public GuiDisplayAdapter(JTextPane outputArea, JLabel promptLabel) {
        this.outputArea = outputArea;
        this.promptLabel = promptLabel;
    }

    @Override
    public void showMessage(String message) {
        SwingUtilities.invokeLater(() -> appendColoredMessage(message + "\n"));
    }

    @Override
    public void showPrompt(String prompt) {
        SwingUtilities.invokeLater(() -> promptLabel.setText(prompt));
    }

    public void clear() {
        SwingUtilities.invokeLater(() -> outputArea.setText(""));
    }

    private void appendColoredMessage(String message) {
        StyledDocument doc = outputArea.getStyledDocument();

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "\\[(GREEN|YELLOW|RED|HIT|PING|MISS)[^\\]]*\\]|\\b(GREEN|YELLOW|RED|HIT|PING|MISS)\\b"
        );

        java.util.regex.Matcher matcher = pattern.matcher(message);

        int lastIndex = 0;

        while (matcher.find()) {
            appendText(doc, message.substring(lastIndex, matcher.start()), new Color(190, 255, 190));

            String token = matcher.group();
            Color color = new Color(190, 255, 190);

            if (token.startsWith("[GREEN") || token.equals("GREEN") || token.startsWith("[HIT") || token.equals("HIT")) {
                color = new Color(57, 255, 20);
            } else if (token.startsWith("[YELLOW") || token.equals("YELLOW") || token.startsWith("[PING") || token.equals("PING")) {
                color = new Color(255, 230, 80);
            } else if (token.startsWith("[RED") || token.equals("RED") || token.startsWith("[MISS") || token.equals("MISS")) {
                color = new Color(255, 40, 40);
            }

            appendText(doc, token, color);
            lastIndex = matcher.end();
        }

        appendText(doc, message.substring(lastIndex), new Color(190, 255, 190));
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void appendText(StyledDocument doc, String text, Color color) {
        SimpleAttributeSet style = new SimpleAttributeSet();
        StyleConstants.setForeground(style, color);

        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}