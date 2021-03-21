package ui;

import model.Program;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.actionwindows.Add;
import ui.actionwindows.Premade;
import ui.actionwindows.Remove;
import ui.actionwindows.Swap;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

// Window that acts as teh homepage for the workout program application
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
    private JButton done;
    private JTextField muscletext;
    private JLabel title;
    private JLabel programlabel;
    private JLabel musclelabel;
    private JLabel exerciseslabel;
    private JLabel arm;
    private JLabel abs;
    private JLabel leg;
    private Program program;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructor
    public HomePageGui(Program p) throws FileNotFoundException {
        this.program = p;
        initFields();
        initLabel();
        initButtons();
        initTextfield();
        initFrame();
        clickHomepage();
    }

    // Effects: assigns an action listener to each button on the page
    public void clickHomepage() {
        addbutton.addActionListener(this);
        removebutton.addActionListener(this);
        preMadebutton.addActionListener(this);
        swapbutton.addActionListener(this);
        savebutton.addActionListener(this);
        loadbutton.addActionListener(this);
        done.addActionListener(this);
    }

    // Effects: will play a click sound when a button is pressed aswell as perform the action tied to a button
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            playSound();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        }
        performTask(e);
    }

    // Effects: will execute mthods based on which button was pressed
    public void performTask(ActionEvent e) {
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
        } else if (e.getSource() == done) {
            done();
        }
    }

    // Effects: plays button1.wav
    public void playSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("./data/button1.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    // Effects: creates a new done window
    private void done() {
        String muscle = muscletext.getText();
        exerciseslabel.setText(this.program.printExerciseforMuscle(muscle).toString());
        if (muscle == "bicep" || muscle == "tricep") {
            arm.setVisible(true);
        } else if (muscle == "quad" || muscle == "hanstring") {
            leg.setVisible(true);
        } else if (muscle == "abs") {
            abs.setVisible(true);
        }


    }

    // Effects: creates a new add window
    public void add(Program p) {
        frame.dispose();
        Add addwindow = new Add(p);
        frame.dispose();
    }

    // Effects: creates a new remove window
    public void remove(Program p) {
        frame.dispose();
        Remove removewindow = new Remove(p);
        frame.dispose();
    }

    // Effects: creates a new premade window
    public void premade(Program p) {
        frame.dispose();
        Premade premadewindow = new Premade(p);
        frame.dispose();
    }

    // Effects: creates a new swap window
    public void swap(Program p) {
        frame.dispose();
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

    // Modifies: this
    // Effects: initializes teh textfield
    private void initTextfield() {
        muscletext.setBounds(100,175,150,15);
    }

    // Modifies: this
    // Effects: initializes teh label
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
        musclelabel.setBounds(90,150,300,15);
        musclelabel.setFont(new Font("Verdana",Font.PLAIN,10));
        musclelabel.setBorder(BorderFactory.createBevelBorder(3));
        musclelabel.setOpaque(true);
        musclelabel.setHorizontalAlignment(JTextField.CENTER);
        musclelabel.setText("Enter Muscle to show exercises for that muscle");
        exerciseslabel.setBounds(10,275,200,15);
        exerciseslabel.setFont(new Font("Verdana",Font.PLAIN,10));
        exerciseslabel.setBorder(BorderFactory.createBevelBorder(3));
        exerciseslabel.setOpaque(true);
        exerciseslabel.setHorizontalAlignment(JTextField.CENTER);
    }

    // Modifies: this
    // Effects: initializes the fields
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
        musclelabel = new JLabel();
        exerciseslabel = new JLabel();
        arm = new JLabel();
        abs = new JLabel();
        leg = new JLabel();
        muscletext = new JTextField();
        done = new JButton("Done");
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
    }

    // Modifies: this
    // Effects: initializes the buttons
    public void initButtons() {
        initButton(preMadebutton,buttonxdist, 25);
        initButton(addbutton,buttonxdist,50);
        initButton(removebutton,buttonxdist,75);
        initButton(swapbutton,buttonxdist,100);
        initButton(savebutton,buttonxdist,125);
        initButton(loadbutton,buttonxdist,150);
        done.setBounds(100,250,75,25);
        done.setFont(new Font("Ink Free",Font.PLAIN,10));
        done.setFocusable(false);
        done.addActionListener((ActionListener) this);
    }

    // Modifies: this
    // Effects: takes a button and assignes it to xpos and ypos
    public void initButton(JButton jb, int xpos, int ypos) {
        jb.setBounds(xpos,ypos,75,25);
        jb.setFont(new Font("Ink Free",Font.PLAIN,10));
        jb.setFocusable(false);
        jb.addActionListener((ActionListener) this);
    }


    // Modifies: this
    // Effects: initializes the frame
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
        frame.add(musclelabel);
        frame.add(exerciseslabel);
        frame.add(done);
        frame.add(muscletext);
        frame.add(arm);
        frame.add(abs);
        frame.add(leg);
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
