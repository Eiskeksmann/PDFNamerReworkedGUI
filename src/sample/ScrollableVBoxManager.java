package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import java.util.ArrayList;

public class ScrollableVBoxManager<L> {

    private VBox parent;
    private ArrayList<Pair<L ,ToggleButton>> link;
    private ToggleButton del;

    public ScrollableVBoxManager(VBox parent){

        this.parent = parent;
        this.link = new ArrayList<Pair<L, ToggleButton>>();
    }

    private EventHandler<ActionEvent> createTBHandler(){

        EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                if(del != null && del.isDisable()) del.setDisable(false);
            }
        };

        return eh;
    }

    public void addDelButton(ToggleButton del){

        this.del = del;
    }

    public void addLink(L input, String tb_text){

        ToggleButton tb = new ToggleButton(tb_text);
        tb.setMaxSize(270,40);
        tb.setMinSize(270,40);
        tb.setPrefSize(270, 40);
        tb.setId("scrollablevboxbuttonview");
        tb.setOnAction(createTBHandler());
        parent.getChildren().add(tb);
        link.add(new Pair<L, ToggleButton>(input, tb));
    }

    public void removeSelected(){

        //TODO: CHECK ON REMOVE LIST
        for(Pair<L, ToggleButton> p : link){

            if(p.getValue().isSelected()) {

                parent.getChildren().remove(p.getValue());
                link.remove(p);
            }
        }
    }

    public void selectAll(){

        for(Pair<L, ToggleButton> p : link){

            p.getValue().setSelected(true);
        }
    }

    public void deselectAll(){

        for(Pair<L, ToggleButton> p : link){

            p.getValue().setSelected(false);
        }
    }

    public ArrayList<Pair<L, ToggleButton>> getLink(){
        return link;
    }
}
