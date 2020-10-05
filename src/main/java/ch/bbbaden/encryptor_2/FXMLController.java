package ch.bbbaden.encryptor_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    Encryptor e = new Encryptor();

    private Label label;
    @FXML
    private TextArea txtaInputText;
    @FXML
    private TextField txtKeyUsed;
    @FXML
    private TextArea txtaOutputEncryptedText;
    @FXML
    private TextArea txtaEncryptedInputText;
    @FXML
    private TextField txtDecryptKey;
    @FXML
    private TextArea txtaOutputText;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtKeyUsed.setEditable(false);
        txtaOutputEncryptedText.setEditable(false);
        
        txtaOutputText.setEditable(false);
    }

    @FXML
    private void encrypt(ActionEvent event) {

        String text = txtaInputText.getText();
        System.out.println("YOU ENTERED THE FOLLOWING TEXT: " + text);
        txtaOutputEncryptedText.setText(e.encrypt(text));
        txtKeyUsed.setText(e.getStringKey());
    }

    @FXML
    private void decrypt(ActionEvent event) {
        txtaOutputText.setText(e.decrypt(txtaEncryptedInputText.getText(), txtDecryptKey.getText()));
    }
}
