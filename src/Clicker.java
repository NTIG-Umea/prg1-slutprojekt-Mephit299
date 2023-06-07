import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker implements Runnable{
    private double moneyAmount = 0;
    private double moneyGeneration = 0;
    private double moneyPerClick = 1;
    private JButton button1;
    private JTextArea information;
    private javax.swing.JPanel JPanel;
    private JTextArea uppgrade1;
    private JButton buyUppgade1;
    private JTextArea uppgrade2;
    private JButton buyUppgrade2;
    private JTextArea uppgrade3;
    private JButton buyUppgrade3;
    private JTextArea uppgrade4;
    private JButton buyUppgrade4;
    private JTextArea uppgrade5;
    private JButton buyUppgrade5;
    private boolean running = false;
    private Thread thread;

    uppgrade uppgradeItem1 = new uppgrade(10,1.2,2);
    uppgrade uppgradeItem2 = new uppgrade(50,1.4,10);
    uppgrade uppgradeItem3 = new uppgrade(100,1.4,10);
    uppgrade uppgradeItem4 = new uppgrade(250,1.4,25);
    uppgrade uppgradeItem5 = new uppgrade(1000,1.4,50);

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
                if (moneyAmount >= uppgradeItem1.cost){
                    uppgraderaClick(uppgradeItem1);
                }
                else {}
            }
        });
        buyUppgrade2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moneyAmount>= uppgradeItem2.cost){
                    uppgraderaGeneration(uppgradeItem2);
                }
            }
        });
        buyUppgrade3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moneyAmount>= uppgradeItem3.cost){
                    uppgraderaClick(uppgradeItem3);
                }
            }
        });
        buyUppgrade4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moneyAmount>= uppgradeItem4.cost){
                    uppgraderaGeneration(uppgradeItem4);
                }
            }
        });
        buyUppgrade5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moneyAmount>= uppgradeItem5.cost){
                    uppgraderaGeneration(uppgradeItem5);
                }
            }
        });
    }

    public static void main(String[] args) {
    Clicker clicker = new Clicker();
    clicker.start();

    }
    public void uppgraderaGeneration(uppgrade uppgradering){
        moneyAmount -= uppgradering.cost;
        moneyGeneration += uppgradering.income;
        uppgradering.cost *= uppgradering.priceIncrese;
        setup();
    }

    public void uppgraderaClick(uppgrade uppgradering){
        moneyAmount -= uppgradering.cost;
        moneyPerClick += uppgradering.income;
        uppgradering.cost *= uppgradering.priceIncrese;
        setup();
    }


    public void setup(){
        information.setText(
                "Pengar: " + moneyAmount +
                "\nPengar pär klick: " + moneyPerClick +
                "\nPengar pär sekund: " + moneyGeneration
                );
        uppgrade1.setText("Kostnad: " + uppgradeItem1.cost + "\nÖkar pengar pär klick med:\n2");
        uppgrade2.setText("Kostnad: " + uppgradeItem2.cost + "\nÖkar pengargeneration med:\n10");
        uppgrade3.setText("Kostnad: " + uppgradeItem3.cost + "\nÖkar pengar pär klick med:\n10");
        uppgrade4.setText("Kostnad: " + uppgradeItem4.cost + "\nÖkar pengargeneration med:\n25");
        uppgrade5.setText("Kostnad: " + uppgradeItem5.cost + "\nÖkar pengargeneration med:\n50");
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
