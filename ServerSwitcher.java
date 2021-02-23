import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URI;
import java.net.URL;

public class ServerSwitcher extends Application {

    public javafx.scene.control.Label label;

    public static void main(String[] args) {
        launch();
    }

    /*
     * Seems like you've decompiled my jar file, or you're looking at the source code in github.
     * I assure you, nothing here is harmful as everything is handled in 1 class (+ the alertbox class)
     *
     */

    @Override
    public void start(Stage window) {

        /*
         * Just a little advertisement ;)
         */

        try {
            URI uri = new URI("https://discord.gg/gbXcMta");
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (Exception ignored) {
        }

        // Setting the window's basic stats
        window.setTitle("SkeldServerSwitcher by Apollo#6000");
        // More Settings
        window.setResizable(false);

        ImageView iview = null;
        ImageView iview2 = null;
        try {
            Image logo = new Image(getClass().getResourceAsStream("assets/amogus.png"));
            iview = new ImageView(logo);
            iview2 = new ImageView(logo);
        } catch (Exception ignored) {
            System.out.println("[DEBUG] Failed to fetch amongus.png, Ignoring!");
        }

        if (iview != null) {
            iview.setFitHeight(30);
            iview.setFitWidth(30);
        }

        if (iview2 != null) {
            iview2.setFitHeight(30);
            iview2.setFitWidth(30);
        }

        Button injectSkeld = new Button("Inject skeld.net");
        if (iview != null)
            injectSkeld.setGraphic(iview);
        injectSkeld.setOnAction(e -> {
            try {
                injectSkeld(new URL("https://skeld.net/setup/regionInfo.dat"), System.getProperty("user.home") + "/AppData/LocalLow/Innersloth/Among Us/regionInfo.dat");
            } catch (Exception err) {
                showError("An unexpected error has occured!\n\n" + err.toString());
            }
        });

        Button uninjectSkeld = new Button("Uninject skeld.net");
        if (iview2 != null)
            uninjectSkeld.setGraphic(iview2);
        uninjectSkeld.setOnAction(e -> {

            try {
                File file = new File(System.getProperty("user.home") + "/AppData/LocalLow/Innersloth/Among Us/regionInfo.dat");
                if (file.exists()) {
                    if (file.delete()) {
                        showSuccess(true, "Successfully switched your Among Us servers back to normal!");
                    } else {
                        showError("Failed to switch your Among Us servers!\nYou would need to manually do it by clicking the globe at the bottom right corner.");
                    }
                } else showError("Failed to uninject skeld.net! You're not on skeld.net anymore!");
            } catch (Exception err) {
                showError("An unexpected error has occured!\n\n" + err.toString());
            }
        });

        VBox box = new VBox();
        box.setLayoutY(50);
        box.setLayoutX(125);
        injectSkeld.setAlignment(Pos.CENTER);
        uninjectSkeld.setAlignment(Pos.CENTER);
        box.getChildren().addAll(injectSkeld, uninjectSkeld);
        Scene scene = new Scene(box, 400, 200);

        window.setScene(scene);
        window.show();
        // Open the GUI
        window.show();
    }

    public void injectSkeld(URL url, String fileName) {
        try (InputStream in = url.openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(fileName)) {

            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }

            showSuccess(false, "Successfully pasted the regionInfo.dat file into the path: " + fileName + "\nYou can now close this and open Among Us!\n\nIf you wish to play regular among us, you can switch your server region at the bottom right corner!");
        } catch (IOException e) {
            showError("An unexpected error has occured!\n\n" + e.toString());
        }
    }

    private void showSuccess(boolean uninjected, String contextText) {
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Success!");
        success.setHeaderText("Successfully " + (uninjected ? "uninjected" : "injected") + " skeld.net!");
        success.setContentText(contextText);
        success.showAndWait();
    }

    private void showError(String contentText) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error!");
        error.setHeaderText("Uh oh..");
        error.setContentText(contentText);
        error.showAndWait();
    }
}
