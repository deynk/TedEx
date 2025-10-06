import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TabPane extends JTabbedPane {
    public List<FileEdit> fileEdits = new ArrayList<>();
    public int selectedTab = 0;

    public void openFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        if(!(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)) return;

        createNewFile(chooser.getSelectedFile());
    }

    public void closeCurrentFile(){
        if(getTabCount() > 1) remove(getSelectedIndex());
        else{
            removeAll();
            revalidate();
            repaint();
            createNewFile(null);
        }
        fileEdits.remove(getSelectedIndex());
    }

    public void createNewFile(){
        add("New File", setTextEditor(null,null));
    }

    public void createNewFile(File file){
        if(file == null){
            createNewFile();
            return;
        }
        add(file.getName(), setTextEditor(file, getFileText(file)));
    }

    private JPanel setTextEditor(File file, String text){ // Returns a panel with an editor
        JPanel editor = new JPanel(new GridLayout(1, 1));

        JPanel textEditor =  new JPanel(new GridLayout(1, 1));

        JTextArea textArea = new JTextArea((text == null? "" : text));
        UndoManager undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(undoManager);


        JScrollPane scrollPane = new JScrollPane(textArea);
        textEditor.add(scrollPane, BorderLayout.NORTH);
        editor.add(textEditor);

        if(file == null) fileEdits.add(new FileEdit(textArea));
        else fileEdits.add(new FileEdit(file.getName(),  file.getAbsolutePath(), textArea));

        (new Thread(() -> {updateTitleTabChangeMade(textArea);})).start();

        return textEditor;
    }

    private void updateTitleTabChangeMade(JTextArea textArea){
        String oldText =  textArea.getText();
        while(true) {
            String actualText = textArea.getText();

            // Checks for change the title
            if (textArea.hasFocus() && !fileEdits.get(getSelectedIndex()).saved) {

                if (!oldText.equals(actualText)) {
                    setTitleAt(getSelectedIndex(), getTitleAt(getSelectedIndex()) + " *");
                    fileEdits.get(getSelectedIndex()).saved = true;
                }
                Form.frame.repaint();
            }

            oldText = actualText;

            // Delay of 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    private String getFileText(File file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            return reader.readAllAsString();
        }catch(Exception e){
            Dialog.Error(null, "Can't read file");
        }
        return null;
    }

    public void saveFile(){
        int index = getSelectedIndex();

        JFileChooser saver = new JFileChooser();
        saver.setDialogType(JFileChooser.SAVE_DIALOG);

        String nameOfFile = fileEdits.get(getSelectedIndex()).name;

        // If is a new file
        if(fileEdits.get(getSelectedIndex()).name == null) {
            nameOfFile = Dialog.getFileName(null);
            if(nameOfFile == null) return;

            saver.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            saver.setDialogType(JFileChooser.OPEN_DIALOG);
            int result = saver.showOpenDialog(null);
            if(saver.getSelectedFile() != null && result == JFileChooser.APPROVE_OPTION){
                File f = new File(saver.getSelectedFile().getAbsolutePath(), nameOfFile);
                try(BufferedWriter br = new BufferedWriter(new FileWriter(f, false))){
                    br.write(fileEdits.get(getSelectedIndex()).text.getText());
                }catch(Exception e){
                    Dialog.Error(null, "Can't save file");
                    e.printStackTrace();
                    return;
                }
                fileEdits.get(getSelectedIndex()).name = nameOfFile;
                fileEdits.get(getSelectedIndex()).path = (new File(saver.getSelectedFile().getAbsolutePath(), nameOfFile)).getAbsolutePath();
            }
            else return;
        }
        else{
            File f = new File(fileEdits.get(getSelectedIndex()).path);
            try(BufferedWriter br = new BufferedWriter(new FileWriter(f, false))){
                br.write(fileEdits.get(getSelectedIndex()).text.getText());
            }catch(Exception e){
                Dialog.Error(null, "Can't save file");
                e.printStackTrace();
            }
        }
        setTitleAt(index, fileEdits.get(getSelectedIndex()).name);
        fileEdits.get(getSelectedIndex()).saved = false;
    }

    public void saveFileIn(){
        int index = getSelectedIndex();

        JFileChooser saver = new JFileChooser();
        saver.setDialogType(JFileChooser.SAVE_DIALOG);

        String nameOfFile = Dialog.getFileName(fileEdits.get(index).name);
        if(nameOfFile == null) return;

        saver.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        saver.setDialogType(JFileChooser.OPEN_DIALOG);
        saver.showOpenDialog(null);

        if(saver.getSelectedFile() != null){
            File f = new File(saver.getSelectedFile().getAbsolutePath(), nameOfFile);
            try(BufferedWriter br = new BufferedWriter(new FileWriter(f, false))){
                br.write(fileEdits.get(getSelectedIndex()).text.getText());
            }catch(Exception e){
                Dialog.Error(null, "Can't save file");
                e.printStackTrace();
                return;
            }
            fileEdits.get(getSelectedIndex()).name = nameOfFile;
            fileEdits.get(getSelectedIndex()).path = (new File(saver.getSelectedFile().getAbsolutePath(), nameOfFile)).getAbsolutePath();
        }

        setTitleAt(index, fileEdits.get(getSelectedIndex()).name);
        fileEdits.get(getSelectedIndex()).saved = false;
    }

    public void undo(){
        fileEdits.get(getSelectedIndex()).undoChange();
    }
    public void redo(){
        fileEdits.get(getSelectedIndex()).redoChange();
    }

    public void copy(){
        fileEdits.get(getSelectedIndex()).text.copy();
    }
    public void cut(){
        fileEdits.get(getSelectedIndex()).text.cut();
    }
    public void paste(){
        fileEdits.get(getSelectedIndex()).text.paste();
    }
    public void selectAll(){
        fileEdits.get(getSelectedIndex()).text.selectAll();
    }
}
