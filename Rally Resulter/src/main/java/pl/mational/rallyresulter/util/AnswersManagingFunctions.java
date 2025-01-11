package pl.mational.rallyresulter.util;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.List;

public class AnswersManagingFunctions {
    public static boolean validateBrdPpFields(List<TextField> fields, boolean isCrewAnswers) {
        for (TextField field : fields) {
            String text = field.getText();

            if (isCrewAnswers) {
                if (!text.isEmpty() && !text.matches("^(A|B|C|D|TAK|NIE)$")) {
                    System.out.println("tutaj");
                    return false;
                }
            } else {
                if (text == null || text.isEmpty() || !text.matches("^(A|B|C|D|TAK|NIE)$")) {
                    System.out.println("albo tu");
                    return false; // Jeśli pole jest puste lub nie spełnia wzorca, zwróć false
                }
            }
        }
        return true;
    }

    public static boolean validateNonEmptyFields(List<TextField> fields) {
        for (TextField field : fields) {
            String text = field.getText();
            if (text == null || text.isEmpty()) {
                return true; // Jeśli pole jest puste, zwróć false
            }
        }
        return false;
    }

    public static boolean validateSequentialFields(List<TextField> fields) {
        boolean foundEmpty = false;

        for (TextField field : fields) {
            String text = field.getText();

            if (text == null || text.isEmpty()) {
                foundEmpty = true; // Oznacz napotkanie pustego pola
            } else if (foundEmpty || text.length() != 2) {
                // Jeśli napotkasz wartość po pustym polu, albo wartość pola nie ma dwóch znaków zwróć false
                return false;
            }
        }

        return true; // Wszystkie wypełnione pola są uporządkowane sekwencyjnie
    }

    public static void validateField(TextField field, String regex, int maxTextLength) {
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches(regex)
                    && change.getControlNewText().length() <= maxTextLength
            ) {
                return change;
            }
            return null;
        }));
    }

    public static void clearAllAnswerFields(List<TextField> roadCard, List<TextField> brdPpAnswers,
                                            List<TextField> roadAnswers, List<TextField> touristAnswers) {
        roadCard.forEach(field -> field.setText(""));
        brdPpAnswers.forEach(field -> field.setText(""));
        roadAnswers.forEach(field -> field.setText(""));
        touristAnswers.forEach(field -> field.setText(""));
    }
}
