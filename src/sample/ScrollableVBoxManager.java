package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

import javax.xml.soap.Node;
import java.util.ArrayList;

public class ScrollableVBoxManager<L> {

    private VBox parent;
    private ArrayList<Pair<L ,ToggleButton>> link;
    private ImportManager im;

    public ScrollableVBoxManager(VBox parent, ImportManager im){

        this.parent = parent;
        System.out.println(parent);
        this.link = new ArrayList<Pair<L, ToggleButton>>();
        this.im = im;
    }

    private EventHandler<ActionEvent> createTBHandler(){

        EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {

                im.tgui.reset();
                ToggleButton selection = (ToggleButton) event.getSource();
                if(selection.isSelected()) {

                    im.send.setDisable(false);
                    im.del.setDisable(false);

                }
                else if (!selection.isSelected()){

                    boolean b = false;
                    for(Pair<L, ToggleButton> p : link){

                        if(p.getValue().isSelected()) {
                            b = true;
                            break;
                        }
                    }
                    if(!b) {

                        im.send.setDisable(true);
                        im.del.setDisable(true);
                    }
                }
            }
        };

        return eh;
    }

    public ArrayList<L> getSelectedItems(){

        ArrayList<L> ret = new ArrayList<L>();
        for(Pair<L, ToggleButton> p : link){

            if(p.getValue().isSelected()) ret.add(p.getKey());
        }
        return ret;
    }

    //Standard size 270, 40
    //Rename Preview 135, 40
    public void addLink(L input, String tb_text , int width, int height, FontAwesomeIconView icon,
                        Pos pa, ContentDisplay cd){

        ToggleButton tb = new ToggleButton(tb_text);
        tb.setMaxSize(width, height);
        tb.setMinSize(width, height);
        tb.setPrefSize(width, height);
        tb.setId("scrollablevboxbuttonview");
        tb.setOnAction(createTBHandler());
        if(icon != null && cd != null) {

            icon.setFill(Paint.valueOf("white"));
            tb.setGraphic(icon);
            tb.setContentDisplay(cd);
        }
        if(pa != null) tb.setAlignment(pa);
        //if(ta != null) tb.setTextAlignment(ta);
        parent.getChildren().add(tb);
        link.add(new Pair<L, ToggleButton>(input, tb));
    }

    public void removeSelected(){

        //TODO: CHECK ON REMOVE LIST
        ArrayList<Pair<L, ToggleButton>> remover = new ArrayList<Pair<L, ToggleButton>>();
        for(Pair<L, ToggleButton> p : link){

            if(p.getValue().isSelected()) {

                remover.add(p);
                parent.getChildren().remove(p.getValue());
            }
        }
        link.removeAll(remover);
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
