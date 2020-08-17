package sample;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;

public class DescriptionContentPackage {

    private VBox description;
    private ArrayList<Pair<VBox, Pair<ToggleButton, Pane>>> pack;

    public DescriptionContentPackage (VBox description){

        pack = new ArrayList<Pair<VBox, Pair<ToggleButton, Pane>>>();
        this.description = description;

    }
    public void addPackage(ToggleButton nav, Pane content){

        pack.add(new Pair<VBox, Pair<ToggleButton, Pane>>(description, new Pair<ToggleButton, Pane>(nav, content)));
    }
    public void setInVisible(){

        for(Pair<VBox, Pair<ToggleButton, Pane>> p : pack){

            p.getKey().setVisible(false);
            //p.getValue().getKey().setVisible(false);
            p.getValue().getValue().setVisible(false);
        }
    }
    public void setVisible(){

        for(Pair<VBox, Pair<ToggleButton, Pane>> p : pack){

            p.getKey().setVisible(true);
            if(p.getValue().getKey().isSelected()){

                p.getValue().getValue().setVisible(true);
            } else {

                p.getValue().getValue().setVisible(false);
           }
        }
    }
    public void selectContent(ToggleButton tb){

        for(Pair<VBox, Pair<ToggleButton, Pane>> p : pack){

            if(p.getValue().getKey().equals(tb)){

                p.getValue().getKey().setSelected(true);
                p.getValue().getValue().setVisible(true);

            } else {

                p.getValue().getKey().setSelected(false);
                p.getValue().getValue().setVisible(false);
            }
        }
    }
    public String toString(){

        return description.toString();
    }
}
