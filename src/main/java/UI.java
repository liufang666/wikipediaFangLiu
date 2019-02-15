import com.google.gson.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UI extends Application {

    public void start(Stage primaryStage) throws Exception {
        VBox parent = new VBox();
        parent.getChildren().add(new Label("Online Word "));

        HBox urlArea = new HBox(new Label("URL:"));

        final TextField textField = new TextField();
        urlArea.getChildren().add(textField);
        parent.getChildren().add(urlArea);

        Button button = new Button("Count");
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                final Executor executor = Executors.newSingleThreadExecutor();
                try {
                    final URL url = new URL( "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=xml&rvprop=timestamp%7Cuser&rvlimit=30&titles=barack%20"+textField.getText()+"&redirects=");
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("User-Agent",
                            "CS222FirstProject/0.1 (fliu@bsu.edu)");
                          connection.connect();
                    executor.execute(new Runnable(){

                        public void run() {
                            try {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                                String line;

                                while((line = reader.readLine())!=null){
                                    System.out.println(line);


                        }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        parent.getChildren().add(button);

        HBox outputArea = new HBox(new Label("Output:"));
        final TextField outputTextField = new TextField();
        outputArea.getChildren().add(outputTextField);
        parent.getChildren().add(outputArea);

        outputTextField.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });

        HBox checkNetWorkArea = new HBox(new Label("NetWorkResult:"));
        final TextField checkNetWorkTextField = new TextField();
        checkNetWorkArea.getChildren().add(checkNetWorkTextField);
        parent.getChildren().add(checkNetWorkArea);

        HBox checkInputExistArea = new HBox(new Label("IfLabelExist:"));
        final TextField checkInputTextField = new TextField();
        checkInputExistArea.getChildren().add(checkInputTextField);
        parent.getChildren().add(checkInputExistArea);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

}
