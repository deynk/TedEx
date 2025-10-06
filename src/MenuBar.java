import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar  extends JMenuBar{
    public JMenu fileMenu;
    public JMenu editMenu;

    public JMenu helpMenu;

    public MenuBar(){
        this.add(setFileMenu());
        this.add(setEditMenu());
        this.add(setHelpMenu());
    }

    public JMenu setHelpMenu(){
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);



        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setToolTipText("Shows info about the application");

        JMenuItem help =  new JMenuItem("Help");
        help.setToolTipText("Shows help about the application");



        helpMenu.add(aboutMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(help);

        return helpMenu;
    }

    public JMenu setEditMenu(){
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);



        JMenuItem undo = new JMenuItem("Undo");
        undo.setToolTipText("Undo the last change");

        JMenuItem redo = new JMenuItem("Redo");
        redo.setToolTipText("Redo the last change");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setToolTipText("Copy the selected text");

        JMenuItem cut = new JMenuItem("Cut");
        cut.setToolTipText("Cut the selected text");

        JMenuItem paste = new JMenuItem("Paste");
        paste.setToolTipText("Paste the text in the clipboard");

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setToolTipText("Select all the text");



        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.addSeparator();
        editMenu.add(copy);
        editMenu.add(cut);
        editMenu.add(paste);
        editMenu.addSeparator();
        editMenu.add(selectAll);



        // ActionListeners...
        undo.addActionListener(e -> {Form.tabs.undo();});
        redo.addActionListener(e -> {Form.tabs.redo();});
        copy.addActionListener((_) -> {Form.tabs.copy();});
        cut.addActionListener((_) -> {Form.tabs.cut();});
        paste.addActionListener((_) -> {Form.tabs.paste();});
        selectAll.addActionListener((_) -> {Form.tabs.selectAll();});

        return editMenu;
    }

    public JMenu setFileMenu(){
        fileMenu = new JMenu("Menu");
        fileMenu.setMnemonic(KeyEvent.VK_F);



        JMenuItem newFile = new JMenuItem("New file");
        newFile.setToolTipText("Create new blank file");

        JMenuItem open = new JMenuItem("Open...");
        open.setToolTipText("Open a file");

        JMenuItem save =  new JMenuItem("Save");
        save.setToolTipText("Save the current file");

        JMenuItem saveIn =  new JMenuItem("Save in...");
        saveIn.setToolTipText("Save the current file in other directory");

        JMenuItem closeCurrentFile = new JMenuItem("Close current file");
        closeCurrentFile.setToolTipText("Close the current file");

        JMenuItem quit = new JMenuItem("Quit");
        quit.setToolTipText("Close the program");

        JMenuItem theme = new JMenu("Theme");
        theme.setMnemonic(KeyEvent.VK_T);
        theme.setToolTipText("Change the theme");

        JMenuItem defaultTheme = new JMenuItem("Default");
        defaultTheme.setToolTipText("Change to the default theme");
        JMenuItem windowsTheme = new JMenuItem("Windows");
        windowsTheme.setToolTipText("Change the windows theme");
        JMenuItem windowsClassicTheme = new JMenuItem("Windows Classic");
        windowsClassicTheme.setToolTipText("Change the Windows Classic Theme");
        JMenuItem metalTheme = new JMenuItem("Metal");
        metalTheme.setToolTipText("Change the Metal theme");
        JMenuItem motifTheme = new JMenuItem("Motif");
        motifTheme.setToolTipText("Change the Motif theme");
        JMenuItem nimbusTheme = new JMenuItem("Nimbus");
        nimbusTheme.setToolTipText("Change the Nimbus theme");



        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveIn);
        fileMenu.add(closeCurrentFile);

        fileMenu.add(theme);
        theme.add(defaultTheme);
        theme.add(windowsTheme);
        theme.add(windowsClassicTheme);
        theme.add(metalTheme);
        theme.add(motifTheme);
        theme.add(nimbusTheme);

        fileMenu.addSeparator();
        fileMenu.add(quit);



        // ActionListeners
        newFile.addActionListener((_) -> {Form.tabs.createNewFile();});
        open.addActionListener((_) -> {Form.tabs.openFile();});
        save.addActionListener((_) -> {Form.tabs.saveFile();});
        saveIn.addActionListener((_) -> {Form.tabs.saveFileIn();});
        closeCurrentFile.addActionListener((_) -> { Form.tabs.closeCurrentFile();});
        quit.addActionListener(e -> {System.exit(0);});

        defaultTheme.addActionListener((_) -> {Form.setTheme("default");});
        windowsTheme.addActionListener((_) -> {Form.setTheme("windows");});
        windowsClassicTheme.addActionListener((_) -> {Form.setTheme("windowsClassic");});
        metalTheme.addActionListener((_) -> {Form.setTheme("metal");});
        motifTheme.addActionListener((_) -> {Form.setTheme("motif");});
        nimbusTheme.addActionListener((_) -> {Form.setTheme("nimbus");});



        return fileMenu;
    }
}
