import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar  extends JMenuBar{
    public JMenu fileMenu;
    public JMenu editMenu;
    public JMenu helpMenu;
    public JMenu languageMenu;

    public MenuBar(){
        this.add(setFileMenu());
        this.add(setEditMenu());
        this.add(setHelpMenu());
        this.add(setLanguageMenu());
    }

    public JMenu setLanguageMenu(){
        JMenu languageMenu = new JMenu("Language");
        languageMenu.setToolTipText("Change the language");
        languageMenu.setMnemonic(KeyEvent.VK_L);

        ImageIcon languageMenuIcon = new ImageIcon("img/language.png");
        languageMenuIcon = new ImageIcon(languageMenuIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        languageMenu.setIcon(languageMenuIcon);



        JMenuItem english = new  JMenuItem("English");
        english.setToolTipText("Set english language");

        JMenuItem spanish = new  JMenuItem("Spanish");
        spanish.setToolTipText("Set spanish language");

        JMenuItem valencian = new  JMenuItem("Valencian");
        valencian.setToolTipText("Set valencian language");



        languageMenu.add(english);
        languageMenu.add(spanish);
        languageMenu.add(valencian);

        return languageMenu;
    }

    public JMenu setHelpMenu(){
        helpMenu = new JMenu("Help");
        helpMenu.setToolTipText("Help menu");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        ImageIcon infoIcon = new ImageIcon("img/info.png");
        infoIcon = new ImageIcon(infoIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        helpMenu.setIcon(infoIcon);



        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setToolTipText("Shows info about the application");
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK +  InputEvent.ALT_DOWN_MASK));

        ImageIcon aboutIcon = new ImageIcon("img/about.png");
        aboutIcon = new ImageIcon(aboutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        aboutMenuItem.setIcon(aboutIcon);


        JMenuItem help =  new JMenuItem("Help");
        help.setToolTipText("Shows help about the application");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

        ImageIcon helpIcon = new ImageIcon("img/help.png");
        helpIcon = new ImageIcon(helpIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        help.setIcon(helpIcon);



        helpMenu.add(aboutMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(help);


        // ActionListeners...
        aboutMenuItem.addActionListener((_) -> {Dialog.about();});
        help.addActionListener((_) -> {Dialog.help();});

        return helpMenu;
    }

    public JMenu setEditMenu(){
        editMenu = new JMenu("Edit");
        editMenu.setToolTipText("Edit menu");
        editMenu.setMnemonic(KeyEvent.VK_E);

        ImageIcon editMenuIcon = new ImageIcon("img/editMenu.png");
        editMenuIcon = new ImageIcon(editMenuIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        editMenu.setIcon(editMenuIcon);



        JMenuItem undo = new JMenuItem("Undo");
        undo.setToolTipText("Undo the last change");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        ImageIcon undoIcon = new ImageIcon("img/undo.png");
        undoIcon = new ImageIcon(undoIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        undo.setIcon(undoIcon);



        JMenuItem redo = new JMenuItem("Redo");
        redo.setToolTipText("Redo the last change");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));

        ImageIcon redoIcon = new ImageIcon("img/redo.png");
        redoIcon = new ImageIcon(redoIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        redo.setIcon(redoIcon);



        JMenuItem copy = new JMenuItem("Copy");
        copy.setToolTipText("Copy the selected text");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));

        ImageIcon copyIcon = new ImageIcon("img/copy.png");
        copyIcon = new ImageIcon(copyIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        copy.setIcon(copyIcon);



        JMenuItem cut = new JMenuItem("Cut");
        cut.setToolTipText("Cut the selected text");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        ImageIcon cutIcon = new ImageIcon("img/cut.png");
        cutIcon = new ImageIcon(cutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        cut.setIcon(cutIcon);



        JMenuItem paste = new JMenuItem("Paste");
        paste.setToolTipText("Paste the text in the clipboard");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

        ImageIcon pasteIcon = new ImageIcon("img/paste.png");
        pasteIcon = new ImageIcon(pasteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        paste.setIcon(pasteIcon);



        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setToolTipText("Select all the text");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        ImageIcon selectAllIcon = new ImageIcon("img/selectAll.png");
        selectAllIcon = new ImageIcon(selectAllIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        selectAll.setIcon(selectAllIcon);


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
        fileMenu = new JMenu("File");
        fileMenu.setToolTipText("File menu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        ImageIcon fileMenuIcon = new ImageIcon("img/fileMenu.png");
        fileMenuIcon = new ImageIcon(fileMenuIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        fileMenu.setIcon(fileMenuIcon);



        JMenuItem newFile = new JMenuItem("New file");
        newFile.setToolTipText("Create new blank file");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        ImageIcon newFileIcon = new ImageIcon("img/newFile.png");
        newFileIcon = new ImageIcon(newFileIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        newFile.setIcon(newFileIcon);



        JMenuItem open = new JMenuItem("Open...");
        open.setToolTipText("Open a file");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        ImageIcon openIcon = new ImageIcon("img/openFile.png");
        openIcon = new ImageIcon(openIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        open.setIcon(openIcon);



        JMenuItem save =  new JMenuItem("Save");
        save.setToolTipText("Save the current file");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        ImageIcon saveIcon = new ImageIcon("img/save.png");
        saveIcon = new ImageIcon(saveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        save.setIcon(saveIcon);



        JMenuItem saveIn =  new JMenuItem("Save in...");
        saveIn.setToolTipText("Save the current file in other directory");
        saveIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

        ImageIcon saveInIcon = new ImageIcon("img/saveIn.png");
        saveInIcon = new ImageIcon(saveInIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        saveIn.setIcon(saveInIcon);



        JMenuItem closeCurrentFile = new JMenuItem("Close current file");
        closeCurrentFile.setToolTipText("Close the current file");
        closeCurrentFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));

        ImageIcon closeCurrentFileIcon = new ImageIcon("img/closeCurrentFile.png");
        closeCurrentFileIcon = new ImageIcon(closeCurrentFileIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        closeCurrentFile.setIcon(closeCurrentFileIcon);



        JMenuItem quit = new JMenuItem("Quit");
        quit.setToolTipText("Close the program");
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));

        ImageIcon quitIcon = new ImageIcon("img/quit.png");
        quitIcon = new ImageIcon(quitIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        quit.setIcon(quitIcon);



        JMenuItem theme = new JMenu("Theme");
        theme.setMnemonic(KeyEvent.VK_T);
        theme.setToolTipText("Change the theme");

        ImageIcon themeIcon = new ImageIcon("img/theme.png");
        themeIcon = new ImageIcon(themeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        theme.setIcon(themeIcon);



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
        quit.addActionListener(e -> { Form.close = true; });

        defaultTheme.addActionListener((_) -> {Form.setTheme("default");});
        windowsTheme.addActionListener((_) -> {Form.setTheme("windows");});
        windowsClassicTheme.addActionListener((_) -> {Form.setTheme("windowsClassic");});
        metalTheme.addActionListener((_) -> {Form.setTheme("metal");});
        motifTheme.addActionListener((_) -> {Form.setTheme("motif");});
        nimbusTheme.addActionListener((_) -> {Form.setTheme("nimbus");});



        return fileMenu;
    }
}
