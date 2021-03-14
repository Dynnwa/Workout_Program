package ui.actionwindows;

import com.sun.tools.internal.xjc.model.CPropertyInfo;
import model.Program;
import sun.jvm.hotspot.types.JBooleanField;
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

    public Swap(Program p) {
        program = p;
        initFields();
        initLabel();
        initButton();
        initFrame();
        donebutton.addActionListener(this::actionPerformed);
    }

    public void initButton() {
        donebutton.setBounds(150,200,100,25);
        donebutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        donebutton.setFocusable(false);
        donebutton.addActionListener((ActionListener) this);
    }

    public void initLabel() {
        exerciselabel = createNewlabel("Exercise", 10);
    }

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

    public void initFrame() {
        frame.add(exerciselabel);
        frame.add(donebutton);
        frame.add(text);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void initFields() {
        frame = new JFrame();
        exerciselabel = new JLabel();
        donebutton = new JButton("Done");
        text = new JTextField();
        text.setBounds(100,125,200,35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == donebutton) {
            String exercise = text.getText();
            program.switchExercise(exercise);
            try {
                new HomePageGui(program);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

    }
}
