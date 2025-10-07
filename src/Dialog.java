import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
        JDialog dialog = new JDialog();
        dialog.setTitle("Insert name for the file");
        dialog.setLayout(new GridLayout(2,2));

        dialog.add(new JLabel("  Insert the name of the file:"), BorderLayout.CENTER);

        JTextField name = new JTextField(fileName);
        dialog.add(name);
        JButton ok = new JButton("Submit");
        JButton cancel = new JButton("Cancel");

        dialog.add(ok);
        dialog.add(cancel);

        ok.addActionListener((_) -> {
            if(name.getText().equals("") || name.getText().isBlank()){
                Warning("Name is empty!", "Please, insert a name for the file!");
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
        dialog.setVisible(true);


        if(auxiliar) return name.getText();
        else return null;
    }

    public static void about(){
        JDialog dialog = new JDialog();
        dialog.setTitle("About");
        dialog.setLayout(new FlowLayout());

        dialog.add(new JLabel("TexEd  ------------------  v1.0"), BorderLayout.CENTER);
        dialog.add(new JLabel(" "), BorderLayout.CENTER);
        dialog.add(new JLabel("TexEd is a simple text editor made for editing file text"), BorderLayout.CENTER);
        dialog.add(new JLabel("It doesn't have nothing special, it's a simple text editor"), BorderLayout.CENTER);
        dialog.add(new JPanel(), BorderLayout.CENTER);
        dialog.add(new JLabel("Is developed by Dani (aka Deynk)"));

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
        int option = JOptionPane.showConfirmDialog(null, "You have unsaved changes. Do you want to exit?", "Unsaved changes", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){ System.exit(0);}
        return false;
    }
}
