import javax.swing.*;
import java.awt.*;

public class Dialog {
    private static Boolean auxiliar;

    public static void Error(String title, String message){
        if(title == null){
            JOptionPane.showMessageDialog(null, message, "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
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
            dialog.dispose();
            // Tells to submit the name
            auxiliar = true;
        });
        cancel.addActionListener((_) -> {dialog.dispose(); });


        dialog.setSize(350, 100);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);


        if(auxiliar) return name.getText();
        else return null;
    }
}
