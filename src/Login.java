import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.List;

public class Login {
    private static boolean isLoged = false;
    private static List<User> users = User.getLogins(new File("users.txt"));
    private static User user;

    public boolean isLoged() {
        return isLoged;
    }

    public void setLoged(boolean loged) {
        isLoged = loged;
    }

    public static void login() {
        JDialog login = setLoginPanel();
        login.setVisible(true);

        while(!isLoged) {
            System.exit(-1);
        }
    }
    private static JDialog setLoginPanel(){
        JDialog dialog = new JDialog();

        JPanel panel = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(grid);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.0;
        JLabel lblUsername = new JLabel("Username:  ");
        lblUsername.setToolTipText("Set your username");
        panel.add(lblUsername, gbc);
            lblUsername.setName("login.lbl.username");
            lblUsername.putClientProperty("tooltip", "login.lbl.username.tooltip");
            LanguageManager.addComponent(lblUsername);


        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField username = new JTextField(16);
        username.setToolTipText("Set your username");
        panel.add(username, gbc);
            username.setName("login.username");
            username.putClientProperty("tooltip", "login.username.tooltip");
            LanguageManager.addComponent(username);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPassword = new JLabel("Password:  ");
        lblPassword.setToolTipText("Set your password");
        panel.add(lblPassword, gbc);
            lblPassword.setName("login.lbl.password");
            lblPassword.putClientProperty("tooltip", "login.lbl.password.tooltip");
            LanguageManager.addComponent(lblPassword);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JPasswordField password = new JPasswordField(16);
        password.setToolTipText("Set your password");
        panel.add(password, gbc);
            password.setName("login.password");
            password.putClientProperty("tooltip", "login.password.tooltip");
            LanguageManager.addComponent(password);

        password.addFocusListener(new  FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                password.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {} //Does nothing
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(new JPanel(), gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        //ImageIcon icoLogin = new ImageIcon((new ImageIcon("img/login.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon icoLogin = new ImageIcon("img/login.png");
        Image scaled = icoLogin.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icoLogin = new ImageIcon(scaled);
        JButton loginButton = new JButton("Login");
        loginButton.setIcon(icoLogin);

        loginButton.setToolTipText("Log in");
        dialog.getRootPane().setDefaultButton(loginButton);
        panel.add(loginButton, gbc);
            loginButton.setName("login.button");
            loginButton.putClientProperty("tooltip", "login.button.tooltip");
            LanguageManager.addComponent(loginButton);


                DialogPropertySaver errorLoginDialog = new DialogPropertySaver();
                errorLoginDialog.setName("login.dialog.error");
                LanguageManager.addComponent(errorLoginDialog);
        loginButton.addActionListener(e -> {
            user = User.findLogin(username.getText(), new String(password.getPassword()), users);
            if(user != null){
                isLoged = true;
                dialog.dispose();
            }
            else{
                Dialog.Error(errorLoginDialog.title, errorLoginDialog.message);
            }
        });

        dialog.add(panel);



        dialog.setTitle("Login");
            dialog.setName("login.dialog");
            LanguageManager.addDialog(dialog);
        dialog.setModal(true);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(true);

        LanguageManager.update();

        return dialog;
    }

    public static String getUser(){ return user.getUsername(); }
}
