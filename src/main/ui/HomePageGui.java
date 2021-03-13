package ui;

import model.Program;
import ui.actionwindows.Add;
import ui.actionwindows.Premade;
import ui.actionwindows.Remove;
import ui.actionwindows.Swap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePageGui implements ActionListener {
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
    private JLabel title;
    private JLabel programlabel;
    private Program program;

    public HomePageGui() {
        initFields();
        initLabel();
        initButtons();
        initFrame();
        clickHomepage(); // Method that actually does stuff
    }

    public void clickHomepage() {
        addbutton.addActionListener(this::actionPerformed);
        removebutton.addActionListener(this::actionPerformed);
        preMadebutton.addActionListener(this::actionPerformed);
        swapbutton.addActionListener(this::actionPerformed);
        savebutton.addActionListener(this::actionPerformed);
        loadbutton.addActionListener(this::actionPerformed);
        quitbutton.addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addbutton) {
            add();
        } else if (e.getSource() == removebutton) {
            remove();
        } else if (e.getSource() == preMadebutton) {
            premade();
        } else if (e.getSource() == swapbutton) {
            swap();
        } else if (e.getSource() == savebutton) {
            save();
        } else if (e.getSource() == loadbutton) {
            load();
        }
    }

    public void add() {
        Add addwindow = new Add();
    }

    public void remove() {
        Remove removewindow = new Remove();
    }

    public void premade() {
        Premade premadewindow = new Premade();
    }

    public void swap() {
        Swap swapwindow = new Swap();
    }

    public void save() {
    }

    public void load() {}

    public void initLabel() {
        title.setBounds(100,10,200,100);
        title.setFont(new Font("Verdana",Font.PLAIN,15));
        title.setBorder(BorderFactory.createBevelBorder(3));
        title.setOpaque(true);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setText("Your Current Program");

        programlabel.setBounds(100,50,200,100);
        programlabel.setFont(new Font("Verdana",Font.PLAIN,15));
        programlabel.setBorder(BorderFactory.createBevelBorder(3));
        programlabel.setOpaque(true);
        programlabel.setHorizontalAlignment(JTextField.CENTER);
        programlabel.setText(program.printExercises().toString());
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
        title = new JLabel();
        programlabel = new JLabel();
        program = new Program();
    }

    public void initButtons() {
        initbutton(preMadebutton,buttonxdist, 25);
        initbutton(addbutton,buttonxdist,50);
        initbutton(removebutton,buttonxdist,75);
        initbutton(swapbutton,buttonxdist,100);
        initbutton(savebutton,buttonxdist,125);
        initbutton(loadbutton,buttonxdist,150);
        //initbutton(quitbutton,buttonxdist,175);
    }

    public void initbutton(JButton jb, int xpos, int ypos) {
        jb.setBounds(xpos,ypos,75,25);
        jb.setFont(new Font("Ink Free",Font.PLAIN,10));
        jb.setFocusable(false);
        jb.addActionListener((ActionListener) this);
    }

    public void initFrame() {
        frame.add(preMadebutton);
        frame.add(addbutton);
        frame.add(removebutton);
        frame.add(swapbutton);
        frame.add(savebutton);
        frame.add(loadbutton);
        frame.add(quitbutton);
        frame.add(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(framewidth,framelength);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
