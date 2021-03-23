package ui.actionwindows;

import model.Program;
import ui.HomePageGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Swap implements ActionListener {
    private JFrame frame;
    private JLabel exerciselabel;
    private JButton donebutton;
    private JTextField text;
    private Program program;

    // Wdinow that allows user to select which exercise they would want to swap out for an alternative
    public Swap(Program p) {
        this.program = p;
        initFields();
        initLabel();
        initButton();
        initFrame();
        donebutton.addActionListener(this::actionPerformed);
    }

    // Modifies: this
    // Effects: initializes the buttons
    public void initButton() {
        donebutton.setBounds(150,200,100,25);
        donebutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        donebutton.setFocusable(false);
    }

    // Modifies: this
    // Effects: initializes teh labels
    public void initLabel() {
        exerciselabel = createNewlabel("Exercise", 10);
    }

    // Modifies: this
    // Effects: creates a new label with text at ypos
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

    // Effects: when the done button is clicked the exercise will be swapped to the alternative exercise
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        String exercise = text.getText();
        this.program.switchExercise(exercise);
        frame.dispose();
        try {
            new HomePageGui(this.program);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }
}
