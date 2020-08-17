package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //System.out.println(getClass().getResource("/sample.fxml"));
        // -> file:/C:/Users/mellawa/IdeaProjects/PDFnamerGUI/target/classes/sample.fxml

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = (Parent) loader.load();
        root.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        primaryStage.setScene(new Scene(root));
        //primaryStage.getIcons().add(new Image("file:res/pdfnamer.png"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        Controller sctrl = (Controller) loader.getController();
        sctrl.setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
