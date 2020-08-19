package sample;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;

public class ScrollableVBoxManager {

    private VBox parent;
    private ArrayList<Pair<File, ToggleButton>> link;

    public ScrollableVBoxManager(VBox parent){

        this.parent = parent;
        link = new ArrayList<Pair<File, ToggleButton>>();
    }

    public void addLink(File f){

        ToggleButton tb = new ToggleButton(f.getName());
        tb.setMaxSize(270,40);
        tb.setMinSize(270,40);
        tb.setPrefSize(270,40);
        tb.setId("pdfbuttonview");
        parent.getChildren().add(tb);
        link.add(new Pair<File, ToggleButton>(f, tb));

    }



}
