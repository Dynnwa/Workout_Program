package ui.actionwindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add implements ActionListener {
    JFrame frame;
    JLabel exerciselabel;
    JLabel musclelabel;
    JLabel alternativelabel;
    JLabel setslabel;
    JLabel repslabel;
    JButton donebutton;

    public Add() {
        initFields();
        initLabel();
        initButton();
        initFrame();
    }

    public void initButton() {
        donebutton.setBounds(200,375,75,25);
        donebutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        donebutton.setFocusable(false);
        donebutton.addActionListener((ActionListener) this);
    }

    public void initLabel() {
        exerciselabel = createNewlabel("Exercise", 10);
        musclelabel = createNewlabel("Muscle", 20);
        alternativelabel = createNewlabel("Alternative", 30);
        setslabel = createNewlabel("Sets", 40);
        repslabel = createNewlabel("Reps", 50);
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
        frame.add(musclelabel);
        frame.add(alternativelabel);
        frame.add(setslabel);
        frame.add(repslabel);
        frame.add(donebutton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void initFields() {
        frame = new JFrame();
        exerciselabel = new JLabel();
        musclelabel = new JLabel();
        alternativelabel = new JLabel();
        setslabel = new JLabel();
        repslabel = new JLabel();
        donebutton = new JButton("Done");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
