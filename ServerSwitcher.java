import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ServerSwitcher extends Application {

    public javafx.scene.control.Label label;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage window) {

        // Seems like you've decompiled my jar file.
        // I assure you, nothing here is harmful
        // Although there are some ways to improve the code I have made.
        // Made by Apollo#6000
        // Oh, and join my discord while you're at it ;)
        // https://discord.gg/ZqHerZXngG

        // Setting the window's basic stats
        window.setTitle("Server Switcher for Win7 by Apollo#6000");
        // More Settings
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.setWidth(1000);
        window.setHeight(250);

        this.label = new javafx.scene.control.Label();
        label.setTextAlignment(TextAlignment.CENTER);
        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.setAlignment(Pos.CENTER);

        label.prefHeightProperty().bind(layout.heightProperty());

        Scene scene = new Scene(layout);
        window.setScene(scene);

        // Open the GUI
        window.show();

        // Run the function
        try {
            downloadFile(new URL("https://skeld.net/setup/regionInfo.dat"), System.getProperty("user.home") + "/AppData/LocalLow/Innersloth/Among Us/regionInfo.dat");
        } catch (Exception err) {
            label.setText("Error: " + err.getLocalizedMessage());
        }
    }

    public void downloadFile(URL url, String fileName) {
        try (InputStream in = url.openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(fileName)) {

            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }

            label.setText("Successfully pasted the regionInfo.dat file into the path: " + fileName + "\nYou can now close this and open Among Us!\n\nIf you wish to play regular among us, you can switch your server region at the bottom right corner!");
        } catch (IOException e) {
            label.setText("Error: " + e.getLocalizedMessage());
        }
    }
}
