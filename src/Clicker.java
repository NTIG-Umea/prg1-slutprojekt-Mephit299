import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker {
    private double moneyAmount = 0;
    private double moneyGeneration = 0;
    private double moneyPerClick = 1;
    private JButton button1;
    private JTextArea information;
    private javax.swing.JPanel JPanel;
    private boolean running = false;
    private Thread thread;

    public Clicker() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moneyAmount += moneyPerClick;
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clicker");
        Clicker c = new Clicker();
        frame.setContentPane(c.JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.pack();
        frame.setVisible(true);
        c.setup();
        c.start();



    }

    public void setup(){
        information.setText(
                "Pengar: " + moneyAmount +
                "\nPengar pär klick: " + moneyPerClick +
                "\nPengar pär sekund: " + moneyGeneration

                );
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        double ns = 1000000000.0 / 25.0;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                c.setup();
                delta--;
            }
        }
        stop();
    }
}
