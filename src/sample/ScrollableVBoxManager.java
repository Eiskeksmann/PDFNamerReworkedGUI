package sample;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import java.util.ArrayList;

public class ScrollableVBoxManager<L> {

    private VBox parent;
    private ArrayList<Pair<L ,ToggleButton>> link;

    public ScrollableVBoxManager(VBox parent){

        this.parent = parent;
        this.link = new ArrayList<Pair<L, ToggleButton>>();
    }


    public void addLink(L input, String tb_text){

        ToggleButton tb = new ToggleButton(tb_text);
        tb.setMaxSize(270,40);
        tb.setMinSize(270,40);
        tb.setPrefSize(270, 40);
        tb.setId("scrollablevboxbuttonview");
        parent.getChildren().add(tb);
        link.add(new Pair<L, ToggleButton>(input, tb));
    }

    public ArrayList<Pair<L, ToggleButton>> getLink(){
        return link;
    }
}
