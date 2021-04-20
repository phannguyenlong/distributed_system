package hw_16_04;
import java.net.*;
import java.io.*;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller for Log in Action
 * @author Long Phan
 */
public class LoginController implements Initializable {
    // FXML variable
    @FXML
    Button submitButton;
    @FXML
    JFXTextField name, id, year, gender;
    @FXML
    ImageView loadingIcon;
    @FXML
    HBox rootPane;

    // Action happen when clicking Button "Sign In"
    public void handleSubmit() {
        Student std = new Student(id.getText(), name.getText(), gender.getText(), year.getText());
        System.out.println(std);

        Socket socket = null;
        int serverPort = 9999;
        try {
            socket = new Socket("127.0.0.1", serverPort);
            
            // Read data froms server
            // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // String line;
            // while ((line = in.readLine()) != null)
            //     System.out.println(line);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(std);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("ALready closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadingIcon.setVisible(false); // hide loading Icon
    }
}
