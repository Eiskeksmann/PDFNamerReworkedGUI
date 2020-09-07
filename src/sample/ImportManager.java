package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;

public abstract class ImportManager {

    protected boolean isLinked;
    protected CheckBox cbx_sa;
    protected CheckBox cbx_da;
    protected ToggleButton del;

    public boolean isLinked(){ return isLinked; }
    public abstract void selectAll();
    public abstract void deselectAll();
    public abstract void removeSelected();
    public abstract void enableGUI();
}
