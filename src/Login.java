import javax.swing.*;
import java.awt.*;
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
        panel.add(new JLabel("Username:  "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField username = new JTextField(16);
        username.setToolTipText("Set your username");
        panel.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:  "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JPasswordField password = new JPasswordField(16);
        password.setToolTipText("Set your password");
        panel.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(new JPanel(), gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");

        loginButton.setToolTipText("Log in");
        dialog.getRootPane().setDefaultButton(loginButton);
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            user = User.findLogin(username.getText(), new String(password.getPassword()), users);
            if(user != null){
                isLoged = true;
                dialog.dispose();
            }
            else{ Dialog.Error("Error", "Invalid username or password");}
        });

        dialog.add(panel);


        dialog.setTitle("Login");
        dialog.setModal(true);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(true);

        return dialog;
    }

    public static String getUser(){ return user.getUsername(); }
}
