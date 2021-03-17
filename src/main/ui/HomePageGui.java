package ui;

import model.Exercise;
import model.Program;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.actionwindows.Add;
import ui.actionwindows.Premade;
import ui.actionwindows.Remove;
import ui.actionwindows.Swap;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

public class HomePageGui implements ActionListener {
    private static final String JSON_FILE = "./data/program.json";
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
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public HomePageGui(Program p) throws FileNotFoundException {
        this.program = p;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println(e.getActionCommand());
        if (e.getSource() == addbutton) {
            add(this.program);
        } else if (e.getSource() == removebutton) {
            remove(this.program);
        } else if (e.getSource() == preMadebutton) {
            premade(this.program);
        } else if (e.getSource() == swapbutton) {
            swap(this.program);
        } else if (e.getSource() == savebutton) {
            saveProgram(program);
        } else if (e.getSource() == loadbutton) {
            loadProgram(program);
        }
        frame.dispose();
    }

    public void add(Program p) {
        Add addwindow = new Add(p);
        frame.dispose();
    }

    public void remove(Program p) {
        Remove removewindow = new Remove(p);
        frame.dispose();
    }

    public void premade(Program p) {
        Premade premadewindow = new Premade(p);
        frame.dispose();
    }

    public void swap(Program p) {
        Swap swapwindow = new Swap(p);
        frame.dispose();
    }

    // EFFECTS: saves the program to the prorgam.json file
    // MODELLED AFTER THE JSON PROJECT
    private void saveProgram(Program p) {
        try {
            jsonWriter.startWriter();
            jsonWriter.write(p);
            jsonWriter.stopWriter();
            System.out.println("Saved prorgram to " + JSON_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE);
        }
    }

    // EFFECTS: loads the old program from the program.json file
    // MODELLED AFTER THE JSON PROJECT
    private void loadProgram(Program p) {
        try {
            p = jsonReader.read();
            System.out.println("Loaded program from " + JSON_FILE);
            new HomePageGui(p);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE);
        }
    }

    public void initLabel() {
        title.setBounds(100,10,200,15);
        title.setFont(new Font("Verdana",Font.PLAIN,15));
        title.setBorder(BorderFactory.createBevelBorder(3));
        title.setOpaque(true);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setText("Your Current Program");

        programlabel.setBounds(100,50,200,15);
        programlabel.setFont(new Font("Verdana",Font.PLAIN,10));
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
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
    }

    public void initButtons() {
        initButton(preMadebutton,buttonxdist, 25);
        initButton(addbutton,buttonxdist,50);
        initButton(removebutton,buttonxdist,75);
        initButton(swapbutton,buttonxdist,100);
        initButton(savebutton,buttonxdist,125);
        initButton(loadbutton,buttonxdist,150);
        //initbutton(quitbutton,buttonxdist,175);
    }

    public void initButton(JButton jb, int xpos, int ypos) {
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
        frame.add(programlabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(framewidth,framelength);
        frame.setLayout(null);
        frame.setVisible(true);
    }

/*
    class SwapButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Count of listeners: " + ((JButton) e.getSource()).getActionListeners().length);
            Swap swapwindow = new Swap(program);
        }
    }

    class AddButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Add addwindow = new Add(program);
        }
    }

    class RemoveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Remove removewindow = new Remove(program);
        }
    }

    class PremadeButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Premade premadewindow = new Premade(program);
        }
    }

    class SaveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveProgram(program);
        }
    }

    class LoadButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadProgram(program);
        }
    }
 */
}
