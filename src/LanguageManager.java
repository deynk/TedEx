import javax.swing.*;
import java.util.*;

public class LanguageManager {
    public static Locale locale = Locale.getDefault();
    //public static Locale locale = new Locale("en","UK");
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("Language", locale);

    // List of the components
    public static List<Object> components = new ArrayList<Object>();

    public static void addComponent(Object component) { components.add(component); }
    public static void update(){ updateComponents(); }
    public static void updateComponents() {
        updateComponents(components);
    }
    public static void updateComponents(List<Object> components) {
        System.out.println(components.size());
        for (Object component : components) {
            updateComponent(component);
        }
    }

    public static void updateComponent(Object component) {
        String key = "";
        if(component instanceof JDialog){
            key  = ((JDialog)component).getName();
        }else if(component instanceof JComponent){
            key  = ((JComponent)component).getName();
        }else throw new IllegalArgumentException("The component " +  component + " is not a JDialog or a JComponent");

        //System.out.println(key);

        if(component instanceof JLabel) {
            ((JLabel) component).setText(resourceBundle.getString(key));
            if(((JLabel) component).getClientProperty("tooltip") != null) {
                ((JLabel) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
            }
        }
        if(component instanceof JButton) {
            ((JButton) component).setText(resourceBundle.getString(key));
            ((JButton) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
        }
        if(component instanceof JTextField) {
            ((JTextField) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
        }
        if(component instanceof JPasswordField) {
            ((JPasswordField) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
        }
        if(component instanceof DialogPropertySaver) {
            ((DialogPropertySaver) component).title = resourceBundle.getString(key+".title");
            ((DialogPropertySaver) component).message = resourceBundle.getString(key+".message");
        }
        if(component instanceof JDialog) {
            ((JDialog) component).setTitle(resourceBundle.getString(key));
        }
        if(component instanceof StringPropertySaver) {
            ((StringPropertySaver) component).value = resourceBundle.getString(key+".value");
        }
        if(component instanceof JMenu){
            ((JMenu) component).setText(resourceBundle.getString(key));
            if(((JMenu) component).getClientProperty("tooltip") != null) {
                ((JMenu) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
            }
        }
        if(component instanceof JMenuItem) {
            ((JMenuItem) component).setText(resourceBundle.getString(key));
            if(((JMenuItem) component).getClientProperty("tooltip") != null) {
                ((JMenuItem) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
            }
        }
    }
}
