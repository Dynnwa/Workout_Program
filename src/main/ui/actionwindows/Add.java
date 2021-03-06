package ui.actionwindows;

import model.Exercise;
import model.Program;
import ui.HomePageGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Window for user to add exercises. has 5 text fields: exercise, muscle, alternative, sets and reps
public class Add implements ActionListener {
    private JFrame frame;
    private JLabel exerciselabel;
    private JLabel musclelabel;
    private JLabel alternativelabel;
    private JLabel setslabel;
    private JLabel repslabel;
    private JButton donebutton;
    private JTextField exercisetext;
    private JTextField muscletext;
    private JTextField alternativetext;
    private JTextField setstext;
    private JTextField repstext;
    private Program program;

    // Constructor
    public Add(Program p) {
        this.program = p;
        initFields();
        initLabel();
        initButton();
        initFrame();
        donebutton.addActionListener(this::actionPerformed);
    }

    // Modifies: this
    // Effects: creates the done button
    public void initButton() {
        donebutton.setBounds(160,300,100,25);
        donebutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        donebutton.setFocusable(false);
    }

    // Modifies: this
    // Effects: creates all the labels for the textfields
    public void initLabel() {
        exerciselabel = createNewlabel("Exercise", 5);
        musclelabel = createNewlabel("Muscle", 65);
        alternativelabel = createNewlabel("Alternative", 125);
        setslabel = createNewlabel("Sets", 185);
        repslabel = createNewlabel("Reps", 245);
    }

    // Modifies: this
    // Effects: returns a label with text and positioned at ypos
    public JLabel createNewlabel(String text, int ypos) {
        JLabel label = new JLabel();
        label.setBounds(160,ypos,100,15);
        label.setFont(new Font("Verdana",Font.PLAIN,15));
        label.setBorder(BorderFactory.createBevelBorder(3));
        label.setOpaque(true);
        label.setHorizontalAlignment(JTextField.CENTER);
        label.setText(text);
        return label;
    }

    // Modifies this
    // Effects: creates the frame
    public void initFrame() {
        frame.add(exerciselabel);
        frame.add(musclelabel);
        frame.add(alternativelabel);
        frame.add(setslabel);
        frame.add(repslabel);
        frame.add(donebutton);
        frame.add(exercisetext);
        frame.add(muscletext);
        frame.add(alternativetext);
        frame.add(setstext);
        frame.add(repstext);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // Modifies: this
    // Effects: creates a textfield at y pos
    public JTextField createText(int ypos) {
        JTextField text = new JTextField();
        text.setBounds(100,ypos,200,15);
        return text;
    }

    // Modifies: this
    // Effects: initializes all the fields
    public void initFields() {
        frame = new JFrame();
        exerciselabel = new JLabel();
        musclelabel = new JLabel();
        alternativelabel = new JLabel();
        setslabel = new JLabel();
        repslabel = new JLabel();
        donebutton = new JButton("Done");
        exercisetext = createText(30);
        muscletext = createText(90);
        alternativetext = createText(145);
        setstext = createText(210);
        repstext = createText(270);
    }

    // Effects: creates a new homepage app with the new exercises added to the program
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        String exercise = exercisetext.getText();
        String alt = alternativetext.getText();
        String muscle = muscletext.getText();
        String setsstring = setstext.getText();
        int sets = Integer.parseInt(setsstring);
        String repsstring = repstext.getText();
        int reps = Integer.parseInt(repsstring);
        Exercise addtoprogram = new Exercise(exercise, alt, muscle,sets,reps,false);
        this.program.addExercise(addtoprogram);
        frame.dispose();
        try {
            new HomePageGui(this.program);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

}
