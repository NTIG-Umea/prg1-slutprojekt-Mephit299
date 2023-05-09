import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker implements Runnable{
    private double moneyAmount = 0;
    private double moneyGeneration = 1;
    private double moneyPerClick = 1;
    private JButton button1;
    private JTextArea information;
    private javax.swing.JPanel JPanel;
    private JTextArea uppgade1;
    private JButton buyUppgade1;
    private boolean running = false;
    private Thread thread;

    public Clicker() {
        JFrame frame = new JFrame("Clicker");
        frame.setContentPane(JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.pack();
        frame.setVisible(true);
        setup();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moneyAmount+= moneyPerClick;
                setup();
            }
        });
        buyUppgade1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (moneyAmount >=uppgade1.cost){
                    moneyAmount -= uppgade1.cost;
                    moneyGeneration += uppgade1.income;

                }
                else {}
            }
        });
    }

    public static void main(String[] args) {
    Clicker clicker = new Clicker();
    clicker.start();
    uppgrade uppgrade1 = new uppgrade(10,1.2,10);

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
        thread = new Thread((Runnable) this);
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
        double ns = 1000000000.0 / 1.0;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                moneyAmount +=moneyGeneration;
                setup();
                delta--;
            }
        }
        stop();
    }
}
