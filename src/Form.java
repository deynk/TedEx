import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Form {
    public static JFrame frame;
    private static boolean userIsLogged = false;
    public static TabPane tabs = new TabPane();


    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        frame = iniFrame(900, 600);
        frame.setVisible(false);
        //Login.login();
        frame.setVisible(true);
    }

    private static void RefreshUI(){
        SwingUtilities.updateComponentTreeUI(frame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    private static JFrame iniFrame(int  width, int height) {
        frame = new JFrame("TexEd");

        MenuBar menuBar = new MenuBar();
        frame.setJMenuBar(menuBar);

        frame.add(tabs, BorderLayout.CENTER);
        tabs.setPreferredSize(new Dimension(width, height));
        //Create the initial file (a blank file)
        tabs.createNewFile();


        frame.setBounds(100, 100, width, height);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}
