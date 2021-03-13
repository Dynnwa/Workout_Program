package ui.actionwindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Premade implements ActionListener {
    JFrame frame;
    JLabel questionlabel;
    JButton armbutton;
    JButton legbutton;
    JButton abbutton;

    public Premade() {
        initFields();
        initLabel();
        initButton();
        initFrame();
    }

    public void initButton() {
        armbutton.setBounds(200,375,75,25);
        armbutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        armbutton.setFocusable(false);
        armbutton.addActionListener((ActionListener) this);

        legbutton.setBounds(200,375,75,25);
        legbutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        legbutton.setFocusable(false);
        legbutton.addActionListener((ActionListener) this);

        abbutton.setBounds(200,375,75,25);
        abbutton.setFont(new Font("Ink Free",Font.PLAIN,10));
        abbutton.setFocusable(false);
        abbutton.addActionListener((ActionListener) this);
    }

    public void initLabel() {
        questionlabel = createNewlabel("Which muscle?", 20);
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
        frame.add(questionlabel);
        frame.add(abbutton);
        frame.add(legbutton);
        frame.add(armbutton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void initFields() {
        frame = new JFrame();
        questionlabel = new JLabel();
        armbutton = new JButton("Arms");
        legbutton = new JButton("Legs");
        abbutton = new JButton("Abs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
