import javax.swing.*;
import java.util.*;

public class LanguageManager {
    public static Locale locale = Locale.getDefault();
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("Language", locale);

    // List of the components
    public static List<Object> components = new ArrayList<Object>();
        // todo: cambiar unir lista de Jelemetos y Jdialogos por una lista de objetos y tratarlos en updateComponent()

    public static void addComponent(JComponent component) { components.add(component); }
    public static void addDialog(JDialog dialog) { dialogs.add(dialog); }

    public static void update(){
        updateComponents();
        updateDialogs();
    }
    public static void update(List<JDialog> dialogs){ updateDialogs(dialogs); }

    private static void updateDialogs(){
        for(JDialog dialog : dialogs){
            String key  = dialog.getName();
            dialog.setTitle(resourceBundle.getString(key));
        }
    }
    private static void updateDialogs(List<JDialog> dialogs){
        for(JDialog dialog : dialogs){
            String key  = dialog.getName();
            dialog.setTitle(resourceBundle.getString(key));
        }
    }
    private static void updateComponents() {
        System.out.println(components.size());
        for (JComponent component : components) {
            updateComponent(component);
        }
    }
    private static void updateComponents(List<JComponent> components) {
        System.out.println(components.size());
        for (JComponent component : components) {
            updateComponent(component);
        }
    }

    public static void updateComponent(JComponent component) {
        String key = component.getName();
        System.out.println(key);

        if(component instanceof JLabel) {
            ((JLabel) component).setText(resourceBundle.getString(key));
            ((JLabel) component).setToolTipText(resourceBundle.getString(key+".tooltip"));
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
    }
}
