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
        exerciseslabel.setVisible(true);
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
        muscletext.setBounds(125,225,150,15);
    }

    // Modifies: this
    // Effects: initializes teh label
    public void initLabel() {
        setLabel(title, 100, 10, 200, 15, "Your Current Program");
        setLabel(programlabel, 100, 50, 200, 10, program.printExercises().toString());
        setLabel(musclelabel, 50, 200, 300, 10, "Enter Muscle to show exercises for that muscle");
        setLabel(exerciseslabel, 75,275,200,10,"");
        exerciseslabel.setVisible(false);
    }

    // Modifies: this
    // Effects: Changes the label to x,y positions with width and font size
    private void setLabel(JLabel title, int x, int y, int width, int font, String text) {
        title.setBounds(x, y, width, 15);
        title.setFont(new Font("Verdana", Font.PLAIN, font));
        title.setBorder(BorderFactory.createBevelBorder(3));
        title.setOpaque(true);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setText(text);
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
        muscletext = new JTextField();
        done = new JButton("Done");
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
    }

    // Modifies: this
    // Effects: initializes the buttons
    public void initButtons() {
        setButton(preMadebutton,buttonxdist, 25);
        setButton(addbutton,buttonxdist,50);
        setButton(removebutton,buttonxdist,75);
        setButton(swapbutton,buttonxdist,100);
        setButton(savebutton,buttonxdist,125);
        setButton(loadbutton,buttonxdist,150);
        setButton(done,160,245);
    }

    // Modifies: this
    // Effects: takes a button and assignes it to xpos and ypos
    public void setButton(JButton jb, int xpos, int ypos) {
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
