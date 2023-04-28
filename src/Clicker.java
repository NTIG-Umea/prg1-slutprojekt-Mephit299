import javax.swing.*;

public class Clicker {
    private double moneyAmount = 0;
    private double moneyGeneration = 0;
    private double moneyPerClick = 1;
    private JButton button1;
    private JTextArea information;
    private javax.swing.JPanel JPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clicker");
        frame.setContentPane(new Clicker().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.pack();
        frame.setVisible(true);
        setup();

    }

    public static String setup(){

    }
}
