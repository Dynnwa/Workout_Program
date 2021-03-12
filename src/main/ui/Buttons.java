package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Buttons {

    public void initbutton(JButton jb, int xpos, int ypos) {
        jb.setBounds(xpos,ypos,75,25);
        jb.setFont(new Font("Ink Free",Font.PLAIN,10));
        jb.setFocusable(false);
        jb.addActionListener((ActionListener) this);
    }
}
