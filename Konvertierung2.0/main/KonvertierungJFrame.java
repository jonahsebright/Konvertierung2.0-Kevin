package main;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Konvertierung von einem Zahlensystem in ein Anderes.
 * Fliesskommazahlen werden bei Angabe eines Kommas (.) auch umgewandelt!
 * <p>
 * K.Chen
 * 1.0
 * <p>
 * Um das Programm aus dem Terminal zu starten:
 * java -cp ./+libs/flanagan.jar:. Satellitenzeit
 */
public class KonvertierungJFrame extends JFrame {
    // Deklaration der Objekte ausserhalb dem Rumpf des Programmes
    private JFrame mainframe;
    private JPanel auswahlpanel1;

    //Buttongroups, damit nur ein Radiobutton einer Gruppe = 1 = true sein kann
    private ButtonGroup auswahl1;
    private ButtonGroup auswahl2;

    //In Panel1 
    private JRadioButton dezimalsystem1;
    private JRadioButton binärsystem1;
    private JRadioButton Octalsystem1;
    private JRadioButton Zehnersystem1;
    private JRadioButton Hexadezimalsystem1;

    //Werden in Panel2, nachdem die erste Wahl getroffen wird, angezeigt
    private JRadioButton dezimalsystem2;
    private JRadioButton binärsystem2;
    private JRadioButton octalsystem2;
    private JRadioButton zehnersystem2;
    private JRadioButton Hexadezimalsystem2;

    private JLabel Eingabesystem;
    private JLabel Ausgabesystem;
    private JLabel Eingabetext;

    private JTextField ConfigEingabe;
    private JTextField ConfigAusgabe;

    private JButton konvertieren;
    private JButton back;
    private JButton next;

    //Deklaration der Itemlistener
    private ItemListener Listener1;

    public KonvertierungJFrame() {
        //construction of a new frame 
        mainframe = new JFrame();
        setupMainFrame();

        next = new JButton("Next");
        auswahlpanel1.add(next, getGridBagConstraints());

        //Name des Objekts muss später in der neuen Klasse angegeben werden ->NextListener, KonvertierungsListener
        NextListener C1 = new NextListener();

        //Implementierung des EventHandlers: ActionListener
        // ACHTUNG! Probleme tauchen auf, wenn mehrere Komponeneten auf einen ActionListener handeln!!!
        //Next und Back haben jdedoch ähnliche Funtionen und MÜSSEN AUF DIESELBEN FUNKTIONEN ZUGREIFEN KÖNNEN
        //und sind deshalb in derselben inneren ActionListenerklasse!!!
        next.addActionListener(C1);
        back.addActionListener(C1);

        //set Backbutton transparent and unclickable
        back.setEnabled(false);
        mainframe.setVisible(true);
    }

    public static String makeAsLongAs(int numCharacter, String s) {
        int numToAdd = numCharacter - s.length();
        return s + nCharacters(numToAdd, ' ');
    }

    public static String nCharacters(int num, char c) {
        return String.valueOf(c).repeat(Math.max(0, num));
    }

    public static final int MIN_NAME_LENGTH = 17;

    private GridBagConstraints getGridBagConstraints() {
        //Bestimmung von location, size, growth factor, anchor, inset, filling, and padding
        GridBagConstraints c = new GridBagConstraints();
        //Die Komponenten sollen kein extra Platz beanspruchen
        c.fill = GridBagConstraints.NONE;

        //Konstruktion der ersten Buttongroup
        auswahl1 = new ButtonGroup();
        auswahl2 = new ButtonGroup();

        //Insets für den Abstand zwischen den Radiobuttons
        int top = 5;
        int left = 0;
        int bottom = 15;
        int right = 315;
        c.insets = new Insets(top, left, bottom, right);
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;

        //Konstruktion des JLabels über den Eingabebuttons
        Eingabesystem = new JLabel("1. Wählen Sie ihr gewünschtes Eingabesystem aus");
        c.gridx = 0;
        c.gridy = 0;
        auswahlpanel1.add(Eingabesystem, c);

        right = 395;
        bottom = 2;
        c.insets = new Insets(top, left, bottom, right);


        //Konstruktion der Radiobuttons
        dezimalsystem1 = new JRadioButton(makeAsLongAs(MIN_NAME_LENGTH, "Dezimalsystem"), false);
        addRadioButtonToGroup(c, dezimalsystem1, auswahl1, 1, 0, true);

        binärsystem1 = new JRadioButton(makeAsLongAs(MIN_NAME_LENGTH, "Binärsystem"), false);
        addRadioButtonToGroup(c, binärsystem1, auswahl1, 2, 0, true);

        Octalsystem1 = new JRadioButton(makeAsLongAs(MIN_NAME_LENGTH, "Octalsystem"), false);
        addRadioButtonToGroup(c, Octalsystem1, auswahl1, 3, 0, true);

        Zehnersystem1 = new JRadioButton("Zehnersystem         ", false);
        //TODO: clean up
        c.gridx = 0;
        c.gridy = 4;
        auswahl1.add(Zehnersystem1);
        auswahlpanel1.add(Zehnersystem1, c);

        Hexadezimalsystem1 = new JRadioButton("Hexadezimalsystem", false);
        c.gridx = 0;
        c.gridy = 5;
        auswahl1.add(Hexadezimalsystem1);
        auswahlpanel1.add(Hexadezimalsystem1, c);

        //Abstand zwischen den einzelnen ZELLEN (nicht Komponenten!!!)
        top = 5;
        left = -270;
        bottom = 5;
        right = 0;
        c.insets = new Insets(top, left, bottom, right);
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        //Konstruktion des JTextFields über den Ausgabebuttons
        Ausgabesystem = new JLabel("2. Wählen Sie ihr gewünschtes Ausgabesystem aus");
        c.gridx = 1;
        c.gridy = 0;
        //Font of JLabel
        //Ausgabesystem.setFont(new Font("Serif", Font.BOLD, 14));
        //Color to red
        Ausgabesystem.setForeground(Color.GRAY);
        auswahlpanel1.add(Ausgabesystem, c);
        left = -160;
        bottom = 5;
        //Insets müssen immer wieder neu im Konstruktor angegeben werden
        c.insets = new Insets(top, left, bottom, right);

        //Konstruktion der Radiobuttons
        //Neue Insets für den Abstand zwischen den Radiobuttons

        dezimalsystem2 = new JRadioButton(makeAsLongAs(MIN_NAME_LENGTH, "Dezimalsystem"), false);
        addRadioButtonToGroup(c, dezimalsystem2, auswahl2, 1, 1, false);


        binärsystem2 = new JRadioButton(makeAsLongAs(MIN_NAME_LENGTH, "Binärsystem"), false);
        addRadioButtonToGroup(c, binärsystem2, auswahl2, 2, 1, false);

        octalsystem2 = new JRadioButton("Octalsystem             ", false);
        //TODO: clean up
        c.gridx = 1;
        c.gridy = 3;
        octalsystem2.setEnabled(false);
        auswahl2.add(octalsystem2);
        auswahlpanel1.add(octalsystem2, c);

        zehnersystem2 = new JRadioButton("Zehnersystem          ", false);
        c.gridx = 1;
        c.gridy = 4;
        zehnersystem2.setEnabled(false);
        auswahl2.add(zehnersystem2);
        auswahlpanel1.add(zehnersystem2, c);

        Hexadezimalsystem2 = new JRadioButton("Hexadezimalsystem", false);
        c.gridx = 1;
        c.gridy = 5;
        Hexadezimalsystem2.setEnabled(false);
        auswahl2.add(Hexadezimalsystem2);
        auswahlpanel1.add(Hexadezimalsystem2, c);

        //Insets des Eingabetexts
        top = 50;
        left = 225;
        bottom = 0;
        right = 0;
        c.insets = new Insets(top, left, bottom, right);

        //Konstruktion des Eingabetexts
        Eingabetext = new JLabel("3. Bitte geben Sie Ihre (Fliesskomma)Zahl ein");
        c.gridx = 0;
        c.gridy = 6;
        Eingabetext.setForeground(Color.GRAY);
        auswahlpanel1.add(Eingabetext, c);

        //Konstruktion der JButtons
        konvertieren = new JButton("Konvertieren");
        bottom = 0;
        top = 0;
        left = 105;
        //setzt den Button in die Mitte zwischen den JTextFields
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(top, left, bottom, right);
        c.gridx = 0;
        c.gridy = 8;
        konvertieren.setEnabled(false);
        auswahlpanel1.add(konvertieren, c);

        //Insets der JTextfields
        top = 20;
        left = 50;
        bottom = 20;
        right = -70;
        c.fill = c.HORIZONTAL;
        c.insets = new Insets(top, left, bottom, right);

        //Konstruktion der Eingabe-Ausgabefelder
        ConfigEingabe = new JTextField(10);
        c.gridx = 0;
        c.gridy = 7;
        auswahlpanel1.add(ConfigEingabe, c);
        ConfigEingabe.setEditable(false);
        c.insets = new Insets(top, left, bottom, right);

        ConfigAusgabe = new JTextField();
        c.gridx = 0;
        c.gridy = 9;
        ConfigAusgabe.setEditable(false);
        auswahlpanel1.add(ConfigAusgabe, c);

        //Insets Next/Back Buttons
        top = 40;
        left = -37;
        bottom = 0;
        right = 0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(top, left, bottom, right);
        //Konstruktion der Back/Next Buttons
        back = new JButton("Back");
        c.gridx = 1;
        c.gridy = 10;
        c.anchor = GridBagConstraints.WEST;
        auswahlpanel1.add(back, c);

        c.gridx = 1;
        c.gridy = 10;
        left = 0;
        c.insets = new Insets(top, left, bottom, right);
        c.anchor = GridBagConstraints.EAST;
        return c;
    }

    private void addRadioButtonToGroup(GridBagConstraints c, JRadioButton systemButton,
                                       ButtonGroup buttonGroup, int gridY, int gridX, boolean enabled) {
        c.gridx = gridX;
        c.gridy = gridY;
        systemButton.setEnabled(enabled);
        buttonGroup.add(systemButton);
        auswahlpanel1.add(systemButton, c);
    }

    private void setupMainFrame() {
        mainframe.setTitle("Konvertierung von Zahlensystemen");
        //Festlegung des Layouts als GridBag
        mainframe.setLayout(new GridBagLayout());
        //1024 x 612 px size
        mainframe.setSize(1024, 612);
        //Das Fenster soll in der Mitte des Bildschirms angezeigt werden
        //if =/null wird die Position relativ zu einem anderen Fenster bestimmt
        mainframe.setLocationRelativeTo(null);
        //Das Fenster soll vergrössert/verkleinert werden können
        //Default = true , dennoch zur Demonstration
        mainframe.setResizable(true);
        //closing frame option
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupAuswahlPanel();
        mainframe.add(auswahlpanel1);
    }

    private void setupAuswahlPanel() {
        //Konstruktion des ersten Panels für die erste Gruppe an Radiobuttons
        auswahlpanel1 = new JPanel();
        //Panel eines GridBagLayouts
        auswahlpanel1.setLayout(new GridBagLayout());
        //Sets transparent panel
        auswahlpanel1.setOpaque(false);
        //Das Panel wied mit dem JFrame vrknüpft
    }

    private void buttonsDisablenAusserGewaehlten(ArrayList<JRadioButton> buttons, JRadioButton ausgewaelter) {
        // Mit der Buttonreihe kann nicht mehr interagiert werden und wird grau
        buttons.remove(ausgewaelter);
        enableButtons(buttons, false);
    }

    private void enableButtons(ArrayList<JRadioButton> buttons, boolean enabled) {
        for (JRadioButton jRadioButton : buttons) {
            jRadioButton.setEnabled(enabled);
        }
    }

    private ArrayList<JRadioButton> buttonsVonGruppe1() {
        return new ArrayList<>(Arrays.asList(
                dezimalsystem1,
                binärsystem1,
                Octalsystem1,
                Zehnersystem1,
                Hexadezimalsystem1
        ));
    }

    private ArrayList<JRadioButton> buttonsVonGruppe2() {
        return new ArrayList<>(Arrays.asList(
                dezimalsystem2,
                binärsystem2,
                octalsystem2,
                zehnersystem2,
                Hexadezimalsystem2
        ));
    }

    // multiple Actionlisteners with inner classes, so to implement buttons with independent actions
    private class NextListener implements ActionListener {
        int progress = 0;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == next) {
                progress++;
                try {
                    handleNextClick();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (e.getSource() == back) {
                progress--;
            }

            back.setEnabled(progress > 0);

            if (e.getSource() == back) {
                handleBackClick();
            }
        }

        private void handleBackClick() {
            if(progress == 0){
                //Erste Buttonreihe wird freigeschalten und gecleared
                enableButtons(buttonsVonGruppe1(), true);
                Eingabesystem.setForeground(Color.BLACK);
                auswahl1.clearSelection();
                //Die zweite Buttonreihe wird deaktiviert
                enableButtons(buttonsVonGruppe2(), false);
                Ausgabesystem.setForeground(Color.GRAY);
                auswahl2.clearSelection();
            }else if(progress == 1){
                enableButtons(buttonsVonGruppe2(), true);
                Ausgabesystem.setForeground(Color.BLACK);
                auswahl2.clearSelection();
                komponentenDerLetztenStufeDeAktivieren();
                ConfigEingabe.setText(null);
                ConfigAusgabe.setText(null);
            }
        }

        private void handleNextClick() throws Exception {
            if (progress == 1) {
                JRadioButton selected = findSelectedButton(buttonsVonGruppe1());
                buttonsDisablenAusserGewaehlten(buttonsVonGruppe1(), selected);
                //Die zweite Buttonreihe wird freigeschalten
                enableButtons(buttonsVonGruppe2(), true);
                //Komponenten der letzten Stufe werden aktiviert
                komponentenDerLetztenStufeDeAktivieren();
                Ausgabesystem.setForeground(Color.BLACK);
            } else if (progress == 2) {
                if(noSystemSelected()){
                    showErrorNoSystemSelected();
                    progress = 1;
                    return;
                }
                if (twiceSameSystemSelected()) {
                    showErrorSystemSelectedTwice();
                    progress = 1;
                    return;
                }
                komponentenDerLetztenStufeAktivieren();
                //gruppe 2 deaktivieren ausser ausgewähltes System
                JRadioButton selected = findSelectedButton(buttonsVonGruppe2());
                buttonsDisablenAusserGewaehlten(buttonsVonGruppe2(), selected);
                Eingabesystem.setForeground(Color.GRAY);
                Ausgabesystem.setForeground(Color.BLACK);
                next.setEnabled(false);
                setConvertButtonCLickListener();
            }
        }

        private void setConvertButtonCLickListener() {
            konvertieren.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String zahlInOriginalBasis = ConfigEingabe.getText();
                    if(zahlInOriginalBasis == null || zahlInOriginalBasis.isEmpty()){
                        showMessageNumberEmpty();
                        return;
                    }
                    BaseConverter baseConverter = new BaseConverter();
                    BigDecimal konvertiert = baseConverter.convert(zahlInOriginalBasis, 2, 10);
                    ConfigAusgabe.setText(konvertiert.stripTrailingZeros().toString());
                }
            });
        }

        private void showMessageNumberEmpty() {
            JOptionPane.showMessageDialog(null,
                    "Biite gebe eine Zahl ein (TODO)", //TODO: text
                    "INFORMATION",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        private void showErrorNoSystemSelected() {
            JOptionPane.showMessageDialog(null,
                    "TODO: text asuwählen (kein system gewählt)",
                    "INFORMATION",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        private boolean noSystemSelected() {
            try{
               findSelectedButton(buttonsVonGruppe2());
               return false;
            }catch (Exception e){
                return true;
            }
        }

        private void showErrorSystemSelectedTwice() {
            JOptionPane.showMessageDialog(null,
                    "Bitte wählen Sie zuerst ein anderes Ausgabesystem aus und bestätigen sie dies mit \"Next\" ",
                    "INFORMATION",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        private boolean twiceSameSystemSelected() throws Exception {
            JRadioButton selectedGruppe1 = findSelectedButton(buttonsVonGruppe1());
            int positionButton1 = buttonsVonGruppe1().indexOf(selectedGruppe1);
            JRadioButton selectedGruppe2 = findSelectedButton(buttonsVonGruppe2());
            int positionButton2 = buttonsVonGruppe2().indexOf(selectedGruppe2);
            return positionButton1 == positionButton2;
        }

        private JRadioButton findSelectedButton(ArrayList<JRadioButton> buttons) throws Exception {
            for (JRadioButton button : buttons) {
                if (button.isSelected()) return button;
            }
            throw new Exception("Kein Knopf war ausgewählt!!");
        }

        private void komponentenDerLetztenStufeAktivieren() {
            Eingabetext.setForeground(Color.BLACK);
            ConfigEingabe.setEditable(true);
            konvertieren.setEnabled(true);
            ConfigAusgabe.setEditable(true);
        }

        private void komponentenDerLetztenStufeDeAktivieren() {
            Eingabetext.setForeground(Color.magenta);
            ConfigEingabe.setEditable(false);
            konvertieren.setEnabled(false);
            ConfigAusgabe.setEditable(false);
        }
    }

    //Main method
    public static void main(String[] args) {
        //Führe den Code der Methode Konvertierung aus
        new KonvertierungJFrame();
    }
}
