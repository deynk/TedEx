import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Form {
    // todo: crear opcion de buscar, buscar y remplazar
    public static JFrame frame;
    public static boolean close = false;
    public static TabPane tabs = new TabPane();


    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        frame = iniFrame(900, 600);
        frame.setVisible(false);

        Login.login();
        SplashScreen ss = new SplashScreen();
        frame.setVisible(true);
        LanguageManager.update();


        // Avoid closing the app with unsaved files
        (new Thread(() -> {tabs.checkIfCanExit();})).start();
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
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() { // Avoid closing the app if it has unsaved files
            @Override
            public void windowClosing(WindowEvent e){
                close = true;
            }
        });
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public static void setTheme(String theme){
        try{
            switch (theme)
            {
                case "default":
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;

                case "windows":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;

                case "windowsClassic":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    break;

                case "metal":
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    break;

                case "motif":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    break;

                case "nimbus":
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    break;
            }
            SwingUtilities.updateComponentTreeUI(frame);
        }catch(Exception e){
            System.out.println("Can't set theme");
            e.printStackTrace();
        }
    }
}
