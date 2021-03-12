package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePageGui extends Buttons implements ActionListener{
    private static final int framewidth = 420;
    private static final int framelength = framewidth;
    private static final int buttonxdist = 10;
    private JFrame frame;
    private JButton preMadebutton;
    private JButton addbutton;
    private JButton removebutton;
    private JButton swapbutton;
    private JButton savebutton;
    private JButton loadbutton;
    private JButton quitbutton;
    private JLabel programlabel;

    public HomePageGui() {
        initFields();
        initLabel();
        initButtons();
        initFrame();
        addbutton.addActionListener(this::addactionPerformed);
        removebutton.addActionListener(this::removeactionPerformed);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void addactionPerformed(ActionEvent e) {
        programlabel.setText("add");
    }

    public void removeactionPerformed(ActionEvent e) {
        programlabel.setText("remove");
    }

    public void initLabel() {
        programlabel.setBounds(100,10,200,100);
        programlabel.setFont(new Font("Verdana",Font.PLAIN,15));
        programlabel.setBorder(BorderFactory.createBevelBorder(3));
        programlabel.setOpaque(true);
        programlabel.setHorizontalAlignment(JTextField.CENTER);
        programlabel.setText("Your Current Program");
    }

    public void initFields() {
        frame = new JFrame();
        preMadebutton = new JButton("PRE-MADE");
        addbutton = new JButton("ADD");
        removebutton = new JButton("REMOVE");
        swapbutton = new JButton("SWAP");
        savebutton = new JButton("SAVE");
        loadbutton = new JButton("LOAD");
        quitbutton = new JButton("QUIT");
        programlabel = new JLabel();
    }

    public void initButtons() {
        initbutton(preMadebutton,buttonxdist, 25);
        initbutton(addbutton,buttonxdist,50);
        initbutton(removebutton,buttonxdist,75);
        initbutton(swapbutton,buttonxdist,100);
        initbutton(savebutton,buttonxdist,125);
        initbutton(loadbutton,buttonxdist,150);
        initbutton(quitbutton,buttonxdist,175);
    }

    public void initFrame() {
        frame.add(preMadebutton);
        frame.add(addbutton);
        frame.add(removebutton);
        frame.add(swapbutton);
        frame.add(savebutton);
        frame.add(loadbutton);
        frame.add(quitbutton);
        frame.add(programlabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(framewidth,framelength);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
