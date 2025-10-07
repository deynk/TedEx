import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Dialog {
    private static Boolean auxiliar;

    public static void Error(String title, String message){
        if(title == null){
            JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void Warning(String title, String message){
        if(title == null){
            JOptionPane.showMessageDialog(null, message, "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static String getFileName(String fileName){
        auxiliar = false;
        List<Object> languageComponents = new ArrayList<Object>();

        JDialog dialog = new JDialog();
        dialog.setTitle("Insert name for the file");
            dialog.setName("getFileName.dialog");
            languageComponents.add(dialog);
        dialog.setLayout(new GridLayout(2,2));

        JLabel lblName = new JLabel("  Enter the name of the file:");
        lblName.setToolTipText("Enter the name of the file");
        dialog.add(lblName, BorderLayout.CENTER);
            lblName.putClientProperty("tooltip", "getFileName.lblName.tooltip");
            lblName.setName("getFileName.lblName");
            languageComponents.add(lblName);


        JTextField name = new JTextField(fileName);
        dialog.add(name);
        JButton ok = new JButton("Create file");
        ok.setToolTipText("Create file");
            ok.setName("getFileName.button.ok");
            ok.putClientProperty("tooltip", "getFileName.button.ok.tooltip");
            languageComponents.add(ok);

        JButton cancel = new JButton("Cancel");
        cancel.setToolTipText("Cancel");
            cancel.setName("getFileName.button.cancel");
            cancel.putClientProperty("tooltip", "getFileName.button.cancel.tooltip");
            languageComponents.add(cancel);

        dialog.add(ok);
        dialog.add(cancel);

        DialogPropertySaver warningGetFileName = new DialogPropertySaver();
        warningGetFileName.setName("getFileName.warning");
        languageComponents.add(warningGetFileName);

        ok.addActionListener((_) -> {
            if(name.getText().equals("") || name.getText().isBlank()){
                Warning(warningGetFileName.title, warningGetFileName.message);
                name.requestFocus();
            }
            else{
                dialog.dispose();
                // Tells to submit the name
                auxiliar = true;
            }
        });
        dialog.getRootPane().setDefaultButton(ok);

        cancel.addActionListener((_) -> {dialog.dispose(); });
        InputMap inputMap = cancel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = cancel.getActionMap();
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        inputMap.put(keyStroke, "cancel");
        actionMap.put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel.doClick();
            }
        });


        dialog.setSize(350, 100);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        LanguageManager.updateComponents(languageComponents);
        dialog.setVisible(true);


        if(auxiliar) return name.getText();
        else return null;
    }

    public static void about(){
        List<Object> languageComponents = new ArrayList<>();

        JDialog dialog = new JDialog();
        dialog.setTitle("About");
            dialog.setName("about.menu");
            languageComponents.add(dialog);
        dialog.setLayout(new FlowLayout());

        dialog.add(new JLabel("TexEd  ------------------  v1.0"), BorderLayout.CENTER);
        dialog.add(new JLabel(" "), BorderLayout.CENTER);
        JLabel lbl1 = new JLabel("TexEd is a simple text editor made for editing file text");
            lbl1.setName("about.lbl1");
            languageComponents.add(lbl1);
        dialog.add(lbl1, BorderLayout.CENTER);

        JLabel lbl2 = new JLabel("It doesn't have nothing special, it's a simple text editor");
            lbl2.setName("about.lbl2");
            languageComponents.add(lbl2);
        dialog.add(lbl2, BorderLayout.CENTER);

        dialog.add(new JPanel(), BorderLayout.CENTER);
        JLabel lbl3 = new JLabel("Is developed by Dani (aka Deynk)");
            lbl3.setName("about.lbl3");
            languageComponents.add(lbl3);
        dialog.add(lbl3);

        LanguageManager.updateComponents(languageComponents);

        dialog.setSize(400, 150);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public static void help(){
        try{
            (new ProcessBuilder("cmd.exe", "/c","start .\\Unit3.pdf")).start();
        }catch(Exception e){}
        System.out.println("Process started");
    }

    public static boolean exitWithoutSaving(){
        //JOptionPane optionPane = new JOptionPane("You have unsaved changes. Do you want to exit?", JOptionPane.QUESTION_MESSAGE);
        //optionPane.setOptionType(JOptionPane.YES_NO_OPTION);
        DialogPropertySaver exitWithoutSavingDialog = new DialogPropertySaver();
            exitWithoutSavingDialog.setName("exitWithoutSaving");
            exitWithoutSavingDialog.title = "exitWithoutSaving.title";
            exitWithoutSavingDialog.message = "exitWithoutSaving.message";
        LanguageManager.updateComponent(exitWithoutSavingDialog);
        int option = JOptionPane.showConfirmDialog(null, exitWithoutSavingDialog.message, exitWithoutSavingDialog.title, JOptionPane.YES_NO_OPTION);

        if(option == JOptionPane.YES_OPTION){ System.exit(0);}
        return false;
    }
}
