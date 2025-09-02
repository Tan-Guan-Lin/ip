package gui;

import bara.Bara;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.UI;

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

    private Bara bara;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.gif"));
    private Image baraImage = new Image(this.getClass().getResourceAsStream("/images/bara.gif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greetUser();
    }

    /** Injects the Duke instance */
    public void setBara(Bara bara) {
        this.bara = bara;
    }

    public void greetUser() {
        String greetings = UI.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetings, baraImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bara.run(input); // replace from whatever is outputted to System.out.println
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, baraImage)
        );
        if(input.equals("bye")) {
            handleExit();
        }
        userInput.clear();
    }

    @FXML
    private void handleExit() {
        Platform.exit();
    }
}
