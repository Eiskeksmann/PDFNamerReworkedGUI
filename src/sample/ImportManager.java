package sample;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public abstract class ImportManager {

    protected boolean isLinked;
    protected CheckBox cbx_sa;
    protected CheckBox cbx_da;
    protected Button del, send;
    protected ToggleGUI tgui;

    public boolean isLinked(){ return isLinked; }
    public void setLinked(boolean set){ isLinked = set; }
    public abstract void selectAll();
    public abstract void deselectAll();
    public abstract void removeSelected();
    public abstract void enableGUI();
    public abstract void disableGUI();
}
