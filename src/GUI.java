import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI {
    private JPanel panel1;
    private JButton saveButton;
    private JButton openButton;
    private JButton findButton;
    private JTextArea textArea1;
    private JButton newButton;
    private JComboBox fileBox1;
    private String filename;

    public GUI() {
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                filename = JOptionPane.showInputDialog("Fil namn:") + ".txt";
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
                if (filename != "") {
                    PrintWriter out = null;
                    try {
                        out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
                        JOptionPane.showMessageDialog(null, "sparade " + filename);
                    } catch (IOException f) {
                        JOptionPane.showMessageDialog(null, "Failed to Save!");
                    }
                    out.println(textArea1.getText());
                    out.flush();
                    out.close();
                } else {
                    filename = JOptionPane.showInputDialog("Ange ett filnamn: ");
                }
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File myObj = new File(JOptionPane.showInputDialog("Ange ett filnamn") + ".txt");
                    if (myObj.createNewFile()) {
                        JOptionPane.showMessageDialog(null, "Fil skapad: " + myObj.getName());
                    } else {
                        System.out.println("Filen finns redan");
                    }
                } catch (IOException g) {
                    System.out.println("An error occurred.");
                    g.printStackTrace();
                }
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
