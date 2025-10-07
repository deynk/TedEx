import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JDialog{

    private JProgressBar barraProgreso;

    public SplashScreen() {
        setBounds(100, 100, 637, 567);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(2, 1, 0, 0));
        //panelInferior.setLayout(new FlowLayout());

        JLabel lblLoading = new JLabel("<html><h1>Loading</h1></html>");
        lblLoading.setForeground(Color.BLACK);
        lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoading.setVerticalAlignment(SwingConstants.CENTER);
        panelInferior.add(lblLoading, BorderLayout.CENTER);

        barraProgreso = new JProgressBar();
        barraProgreso.setStringPainted(true);
        barraProgreso.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        barraProgreso.setForeground(Color.blue);
        barraProgreso.setBackground(Color.white);
        //barraProgreso.setIndeterminate(true);
        panelInferior.add(barraProgreso,  FlowLayout.CENTER);


        contentPane.add(panelInferior, SwingConstants.CENTER);

        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setSize(400, 100);
        setVisible(true);

        try {
            iniciarBarraCarga();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dispose();
    }

    private void iniciarBarraCarga() throws InterruptedException {
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(20);
            //Thread.sleep(5000);  // Maldad
            actualizarBarraProgreso(i);
        }
    }

    private void actualizarBarraProgreso(int valor) throws InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                barraProgreso.setValue(valor);
            }
        });
    }
}


