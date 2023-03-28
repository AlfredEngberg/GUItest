import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI {
    private JPanel panel1;
    private JButton arkivButton;
    private JButton saveButton;
    private JButton openButton;
    private JButton findButton;
    private JTextArea textArea1;

    public GUI() {
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "text.txt";
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(filename));
                } catch (FileNotFoundException ex) {
                    textArea1.setText("");
                    return;
                }
                String nextLine = null;
                try {
                    nextLine = in.readLine();
                    while (nextLine != null) {
                        textArea1.append(nextLine +"\n");
                        nextLine = in.readLine();
                    }
                } catch (IOException ex) {
                    textArea1.setText("");
                }
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "text.txt";
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(null,"Failed to Save!");
                }
                out.println("Hello World");
                out.flush();
                out.close();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        GUI gui = new GUI();
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
