import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.util.ArrayList;
import java.util.List;

public class FileEdit{
    public String name = null;
    public String path = null;
    public JTextArea text;
    public boolean saved = false;
    public UndoManager undoManager = new  UndoManager();
    public boolean ignoreNextChange = false;

    public FileEdit (String Name, String path, JTextArea text){
        this.name = Name;
        this.path = path;
        this.text = text;
        this.text.getDocument().addUndoableEditListener(undoManager);
    }
    public FileEdit (JTextArea text){
        this.text = text;
        this.text.getDocument().addUndoableEditListener(undoManager);
    }

    public void undoChange(){
        if(undoManager.canUndo()){
            undoManager.undo();
        }
        text.repaint();
    }
    public void redoChange(){
        if(undoManager.canRedo()){
            undoManager.redo();
        }
        text.repaint();
    }
}
