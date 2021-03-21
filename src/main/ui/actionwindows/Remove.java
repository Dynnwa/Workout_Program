package ui.actionwindows;

import model.Program;
import ui.HomePageGui;
import ui.HomepageApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Window that contains a textfield, a label and a done button that lets the user remove a given exercise
public class Remove implements ActionListener {
    private JFrame frame;
    private JLabel exerciselabel;
    private JButton donebutton;
    private JTextField text;
    private Program program;

    // Constructor
    public Remove(Program p) {
        this.program = p;
        initFields();
        initLabel();
        initButton();
        initFrame();
        donebutton.addActionListener(this::actionPerformed);
    }

    // Modifies: this
    // Effects: intitializes the button
    public void initButton() {
        donebutton.setBounds(150,200,100,25);
        donebutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        donebutton.setFocusable(false);
        donebutton.addActionListener((ActionListener) this);
    }

    // Modifies: this
    // Effects: initializes the label
    public void initLabel() {
        exerciselabel = createNewlabel("Exercise", 10);
    }

    // Modifies: this
    // Effects: creates a label with text at y pos
    public JLabel createNewlabel(String text, int ypos) {
        JLabel label = new JLabel();
        label.setBounds(100,ypos,200,100);
        label.setFont(new Font("Verdana",Font.PLAIN,15));
        label.setBorder(BorderFactory.createBevelBorder(3));
        label.setOpaque(true);
        label.setHorizontalAlignment(JTextField.CENTER);
        label.setText(text);
        return label;
    }

    // Modifies: this
    // Effects: initializes the frame
    public void initFrame() {
        frame.add(exerciselabel);
        frame.add(donebutton);
        frame.add(text);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // Modifies: this
    // Effects: initializes the fields
    public void initFields() {
        frame = new JFrame();
        exerciselabel = new JLabel();
        donebutton = new JButton("Done");
        text = new JTextField();
        text.setBounds(100, 125, 200, 35);
    }

    // Effects: when done button is clicked then the window will close and the exercise will be removed form the program
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        String exersice = text.getText();
        this.program.removeanExercise(exersice);
        frame.dispose();
        try {
            new HomePageGui(this.program);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
