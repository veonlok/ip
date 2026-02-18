package yappy.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yappy.Yappy;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Yappy yappy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/toji.png"));
    private Image yappyImage = new Image(this.getClass().getResourceAsStream("/images/koro-sensei.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Yappy instance */
    public void setYappy(Yappy y) {
        yappy = y;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yappy.getResponse(input);
        String commandType = yappy.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYappyDialog(response, yappyImage, commandType)
        );
        userInput.clear();

        if (commandType.equals("exit")) {
            Platform.exit();
        }
    }
}
