package ui.actionwindows;

import model.Exercise;
import model.Program;
import ui.HomePageGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Window for the user to select a program that contains exercises for the targetted muscle
public class Premade implements ActionListener {
    private JFrame frame;
    private JLabel questionlabel;
    private JButton armbutton;
    private JButton legbutton;
    private JButton abbutton;
    private Program program;
    private Program armprogram;
    private Program legprogram;
    private Program abprogram;

    // Constuctor
    public Premade(Program p) {
        this.program = p;
        initFields();
        initProgram();
        initLabel();
        initButton();
        initFrame();
        armbutton.addActionListener(this::actionPerformed);
        legbutton.addActionListener(this::actionPerformed);
        abbutton.addActionListener(this::actionPerformed);
    }

    // MOdifies: this
    // Effects: creates buttons
    public void initButton() {
        armbutton.setBounds(150,200,100,25);
        armbutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        armbutton.setFocusable(false);
        armbutton.addActionListener((ActionListener) this);

        legbutton.setBounds(150,250,100,25);
        legbutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        legbutton.setFocusable(false);
        legbutton.addActionListener((ActionListener) this);

        abbutton.setBounds(150,300,100,25);
        abbutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        abbutton.setFocusable(false);
        abbutton.addActionListener((ActionListener) this);
    }

    // Modifes: this
    // Effects: creates labels
    public void initLabel() {
        questionlabel = createNewlabel("Which muscle?", 20);
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
    // Effects: creates a frame
    public void initFrame() {
        frame.add(questionlabel);
        frame.add(abbutton);
        frame.add(legbutton);
        frame.add(armbutton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // Modifies: this
    // Effects: initializes the fields
    public void initFields() {
        frame = new JFrame();
        questionlabel = new JLabel();
        armbutton = new JButton("Arms");
        legbutton = new JButton("Legs");
        abbutton = new JButton("Abs");
    }

    // Modifies: this
    // Effects: creates all teh exercises that will go into programs
    public void initProgram() {
        Exercise extension = new Exercise("Skullcrusher", "Press down", "tricep",
                3, 8, false);
        Exercise curl = new Exercise("Barbell curl", "Band curl", "bicep",
                4, 10, false);
        Exercise deadlift = new Exercise("Deadlift", "Cable dl", "hamstring",
                3, 3, false);
        Exercise squat = new Exercise("Barbell squat", "Hack squat", "quad",
                4, 8, false);
        Exercise core = new Exercise("Leg raise", "Cable hold", "core",
                3, 8, false);
        Exercise abs = new Exercise("Situp", "Machine Crunch", "abs",
                5, 10, false);

        armprogram = new Program();
        armprogram.addExercise(extension);
        armprogram.addExercise(curl);

        legprogram = new Program();
        legprogram.addExercise(deadlift);
        legprogram.addExercise(squat);

        abprogram = new Program();
        abprogram.addExercise(core);
        abprogram.addExercise(abs);
    }

    // Effects: creates a new homepage with a pragam updated with the user selected muscle focus
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            frame.dispose();
            if (e.getSource() == armbutton) {
                this.program = armprogram;
            } else if (e.getSource() == legbutton) {
                this.program = legprogram;
            } else if (e.getSource() == abbutton) {
                this.program = abprogram;
            }
            frame.dispose();
            new HomePageGui(this.program);
            frame.dispose();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

    }
}
