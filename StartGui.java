/**
 * Created by James Page on 4/7/2017.
 */
package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.DefaultListCellRenderer;

import static Main.SupportMethods.*;

import org.apache.commons.lang3.ArrayUtils;

public class StartGui extends JFrame {
    //**************************************************************************************
    //*************************** Top Panel Variables and Objects **************************
    //**************************************************************************************
    // Values stored in objects (both interactive and non-interactive)
    private Weapons weapons = new Weapons();
    private Armors armors = new Armors();
    private Accessories accessories = new Accessories();
    private Materia materia = new Materia();
    private final String currLvlLabelValue = "Select Current Level:";
    private final String nextLvlLabelValue = "Next Level:";
    private final String equipLabelValue = "Equipment";
    private final String materiaLabelValue = "Materia (Max Level)";
    private final String resetButtonValue = "Reset";
    private final String imgDir = "../images/";
    private final String backgroundLocName = imgDir+"Background.png";
    private final String[] GuiCharSelDefault = {"---  Select Character ---"};
    private final String[] weaponComboDefault = {"Weapon"};
    private final String[] armorComboDefault = {"Armor"};
    private final String[] accessoryComboDefault = {"Accessory"};
    private final String[] materiaComboDefault = {"Empty"};
    private final String[] GuiCharSel = (String[])ArrayUtils.addAll(GuiCharSelDefault, Characters.Characters);
    private final int unselectedDefaultElement = 0;
    private final int minLvl = 1;
    private final int maxLvl = Characters.levelRanges[Characters.levelRanges.length -1];
    private final String[] lvlRange = createArrRange("- -", minLvl, maxLvl);

    //**************************************************************************************
    // Non-Interactive GUI Objects
    private int mainWindowSizeX = 1280;
    private int mainWindowSizeY = 720;

    private JLabel currLvlLabel = createWhiteBoldLabel(currLvlLabelValue, 14);
    private JLabel nextLvlLabel = createWhiteBoldLabel(nextLvlLabelValue, 14);
    private JLabel equipLabel = createWhiteBoldLabel(equipLabelValue, 20);
    private JLabel materiaLabel = createWhiteBoldLabel(materiaLabelValue, 20);

    //**************************************************************************************
    // Interactive GUI Objects (Objects where the value/behavior will change)
    private JLabel charImgLabel = createCharPicLabel();
    private JComboBox charSelCombo = createStandardCombo(GuiCharSel);
    private JComboBox currLvlAnsCombo = createCurrLvlAnsCombo(lvlRange);
    private JTextField  nextLvlAnsTextField = createNextLvlAnsTextField(lvlRange[unselectedDefaultElement], lvlRange[unselectedDefaultElement].length() + 1);
    private JComboBox weaponCombo = createStandardCombo(weaponComboDefault);
    private JComboBox armorCombo = createStandardCombo(armorComboDefault);
    private JComboBox accessoryCombo = createStandardCombo(accessoryComboDefault);
    private JComboBox[] materiaWpnCombos = createMateriaCombos(materiaComboDefault, materia.getMateriaLists(), 8);
    private JComboBox[] materiaArmCombos = createMateriaCombos(materiaComboDefault, materia.getMateriaLists(), 8);
    private JButton resetButton = createResetButton(resetButtonValue);
    //**************************************************************************************
    /*
     * Object panels holding both interactive and non-interactive objects
     * and their action behaviors in GUI
     */
    private BackgroundPanel backgroundFrame = createBackgroundFrame(backgroundLocName);
    private JPanel charTopPanel = createCharTopPanel();
    private JPanel topFrame = createTopFrame();
    private JScrollPane topFrameScroll = createTopScrollPane();
    private JPanel centerFrame = createCenterFrame();
    private JScrollPane centerFrameScroll = createCenterScrollPane();

    //**************************************************************************************
    //************************* Center Panel Variables and Objects *************************
    //**************************************************************************************
    // Values stored in objects (both interactive and non-interactive)

    //**************************************************************************************
    //************************************* Constructor ************************************
    //**************************************************************************************
    // Constructor
    public StartGui() {
        /*
         * Display everything in GUI to user
         */
        setContentPane(backgroundFrame);
        add(topFrameScroll, BorderLayout.NORTH);
        add(centerFrameScroll, BorderLayout.CENTER);

        setSize(mainWindowSizeX,mainWindowSizeY);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //**************************************************************************************
    //********************************** Top Panel Methods *********************************
    //**************************************************************************************
    // Non-Interactive GUI Object Methods

    private JLabel createWhiteBoldLabel(String text, int textSize){
        JLabel lbl = new JLabel(text);

        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font(text, Font.BOLD, textSize));

        return lbl;
    }

    //**************************************************************************************
    // Interactive GUI Object Methods

    private JLabel createCharPicLabel(){
        JLabel labelPic = new JLabel("", null, JLabel.CENTER);
        labelPic.setPreferredSize(new Dimension(100,100));
        return labelPic;
    }

    private JComboBox createStandardCombo(String[] defaultValue){
        JComboBox comboBox = new JComboBox(defaultValue);
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(dlcr);
        comboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXX");
        return comboBox;
    }

    private void setCharSelComboAction(){
        charSelCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String charName = ((JComboBox)(e.getSource())).getSelectedItem().toString();
                        if (!(charName.equals(GuiCharSelDefault[unselectedDefaultElement]))){
                            String image = imgDir+charName+".png";
                            charImgLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(image)).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                            charSelCombo.removeItem(GuiCharSel[unselectedDefaultElement]);
                            currLvlAnsCombo.removeItem(lvlRange[unselectedDefaultElement]);
                            weapons.setCharWeaponList(charName);
                            weaponCombo.setModel(new DefaultComboBoxModel(weapons.getCharWeaponList()));
                            String currWpn = (String)weaponCombo.getSelectedItem();
                            if (!(currWpn.equals(weaponComboDefault[unselectedDefaultElement]))){
                                if (Arrays.asList(weapons.getCharWeaponList()).contains(currWpn)){
                                    weapons.setWeapon(currWpn);
                                }
                            }
                            armorCombo.setModel(new DefaultComboBoxModel(armors.getArmorsList()));
                            String currArm = (String)armorCombo.getSelectedItem();
                            if (!(currArm.equals(armorComboDefault[unselectedDefaultElement]))){
                                if (Arrays.asList(armors.getArmorsList()).contains(currArm)){
                                    armors.setArmor(currArm);
                                }
                            }
                            accessoryCombo.setModel(new DefaultComboBoxModel(accessories.getAccessoriesList()));
                            String currAcc = (String)accessoryCombo.getSelectedItem();
                            if (!(currAcc.equals(accessoryComboDefault[unselectedDefaultElement]))){
                                if (Arrays.asList(accessories.getAccessoriesList()).contains(currAcc)){
                                    accessories.setAccessory(currAcc);
                                }
                            }
                        }
                    }
                }
        );
    }

    private JComboBox createCurrLvlAnsCombo(String[] arr){
        JComboBox comboBox = new JComboBox(arr);
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(dlcr);
        comboBox.setPrototypeDisplayValue("X" + getLongestStrFromArr(arr) + "X");
        return comboBox;
    }

    private void setCurrLvlAnsComboAction(){
        currLvlAnsCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currLvl = ((JComboBox)(e.getSource())).getSelectedItem().toString();
                        if(isInteger(currLvl)){
                            if (Integer.parseInt(currLvl) == maxLvl){
                                nextLvlAnsTextField.setText(lvlRange[unselectedDefaultElement]);
                            } else {
                                nextLvlAnsTextField.setText(Integer.toString(Integer.parseInt(currLvl) + 1));
                            }
                        } else {
                            nextLvlAnsTextField.setText(lvlRange[unselectedDefaultElement]);
                        }
                    }
                }
        );
    }

    private JTextField createNextLvlAnsTextField(String defaultTxt, int setWidth){
        JTextField txtField = new JTextField(defaultTxt, setWidth);
        Font font = txtField.getFont();
        Border blackLine = BorderFactory.createLineBorder(Color.black);

        txtField.setBorder(blackLine);
        txtField.setHorizontalAlignment(JLabel.CENTER);
        txtField.setFont(font.deriveFont(Font.BOLD));
        txtField.setEditable(false); // label will not resize, but string can be changed
        return txtField;
    }

    private void setWeaponComboAction(){
        weaponCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currWpn = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currWpn.equals(weaponComboDefault[unselectedDefaultElement]))){
                            if (Arrays.asList(weapons.getCharWeaponList()).contains(currWpn)){
                                weapons.setWeapon(currWpn);
                            }
                        }
                    }
                }
        );
    }

    private void setArmorComboAction(){
        armorCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currArm = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currArm.equals(armorComboDefault[unselectedDefaultElement]))){
                            if (Arrays.asList(armors.getArmorsList()).contains(currArm)){
                                armors.setArmor(currArm);
                            }
                        }
                    }
                }
        );
    }

    private void setAccessoryComboAction(){
        accessoryCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currAcc = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currAcc.equals(accessoryComboDefault[unselectedDefaultElement]))){
                            if (Arrays.asList(accessories.getAccessoriesList()).contains(currAcc)){
                                accessories.setAccessory(currAcc);
                            }
                        }
                    }
                }
        );
    }

    private JComboBox[] createMateriaCombos(String[] defaultValue, String[][] arr, int numOfComboBoxes){
        JComboBox[] comboBoxes = new JComboBox[numOfComboBoxes];
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        for (int i = 0; i < numOfComboBoxes; i++){
            comboBoxes[i] = new JComboBox(defaultValue);
            comboBoxes[i].setRenderer(dlcr);
            comboBoxes[i].setPrototypeDisplayValue("X" + getLongestStrFromArr(arr) + "X");
        }
        return comboBoxes;
    }

    private JButton createResetButton(String defaultValue){
        JButton button = new JButton(defaultValue);
        Font font = button.getFont();

        button.setHorizontalAlignment(JLabel.CENTER);
        button.setFont(font.deriveFont(Font.BOLD));
        return button;
    }

    //**************************************************************************************
    // Panel object methods
    private BackgroundPanel createBackgroundFrame(String imgLocName){
        Image backgroundImg = null;
        try {
            backgroundImg = ImageIO.read(getClass().getResource(imgLocName));
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        BackgroundPanel bgPanel = new BackgroundPanel(backgroundImg, BackgroundPanel.SCALED, 0.0f, 0.0f);
        return bgPanel;
    }

    private JPanel createCharTopPanel(){
        JPanel pnl = new JPanel();

        JLabel lineSplitter = new JLabel();
        Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));
        lineSplitter.setBorder(lineSplitterBoarder);

        pnl.setLayout(new GridBagLayout());
        pnl.setBorder(lineSplitterBoarder);
        /*
        pnl.add(charImgLabel,setGbc(0,0, 1,4, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));

        setCharSelComboAction();
        pnl.add(charSelCombo, setGbc(1,0, 2,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        pnl.add(currLvlLabel, setGbc(1,2, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        setCurrLvlAnsComboAction();
        pnl.add(currLvlAnsCombo, setGbc(2,2, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 7, 0, 0)));
        pnl.add(nextLvlLabel, setGbc(1,3, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 66, 0, 0)));
        pnl.add(nextLvlAnsTextField, setGbc(2,3, 1,1, 9,5, "WEST", 0, 0, setInsets(0, 7, 0, 0)));
*/
        pnl.setOpaque(false);
        return pnl;
    }

    private JPanel createTopFrame(){
        JPanel pnl = new JPanel();

        JLabel lineSplitter = new JLabel();
        Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));
        lineSplitter.setBorder(lineSplitterBoarder);

        pnl.setLayout(new GridBagLayout());

        pnl.add(charImgLabel,setGbc(0,0, 1,4, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));

        setCharSelComboAction();
        pnl.add(charSelCombo, setGbc(1,0, 2,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        pnl.add(currLvlLabel, setGbc(1,2, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        setCurrLvlAnsComboAction();
        pnl.add(currLvlAnsCombo, setGbc(2,2, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 7, 0, 0)));
        pnl.add(nextLvlLabel, setGbc(1,3, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 66, 0, 0)));
        pnl.add(nextLvlAnsTextField, setGbc(2,3, 1,1, 9,5, "WEST", 0, 0, setInsets(0, 7, 0, 0)));

        pnl.add(lineSplitter, setGbc(3,0, 1,4, 0,0, "CENTER", 0, 0, setInsets(0, 10, 0, 0)));
        pnl.add(equipLabel, setGbc(4,0, 1,1, 0,0, "CENTER", 0, 0, setInsets(0, 10, 0, 0)));
        setWeaponComboAction();
        pnl.add(weaponCombo, setGbc(4,1, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        setArmorComboAction();
        pnl.add(armorCombo, setGbc(4,2, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        setAccessoryComboAction();
        pnl.add(accessoryCombo, setGbc(4,3, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));

        pnl.add(materiaLabel, setGbc(5,0, 8,1, 0,0, "CENTER", 0, 0, setInsets(0, 10, 0, 0)));
        for (int i = 0, column = 5; i < materiaWpnCombos.length; i++, column++){
            pnl.add(materiaWpnCombos[i], setGbc(column,1, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        }
        for (int i = 0, column = 5; i < materiaArmCombos.length; i++, column++){
            pnl.add(materiaArmCombos[i], setGbc(column,2, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        }

        pnl.add(resetButton,setGbc(13,0, 1,4, 50,0, "WEST", 0, 0, setInsets(0, 10, 0, 10)));

        pnl.setOpaque(false);
        return pnl;
    }

    private JScrollPane createTopScrollPane(){
        JScrollPane scrollPane = new JScrollPane(backgroundFrame);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border lineBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(224,224,224));
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compoundSetup = BorderFactory.createCompoundBorder(raisedBevel, lineBorder);
        Border compoundFinal = BorderFactory.createCompoundBorder(compoundSetup, loweredBevel);

        scrollPane.setPreferredSize(new Dimension(0, 159));
        scrollPane.setBorder(compoundFinal);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setView(topFrame);
        return scrollPane;
    }

    //**************************************************************************************
    //********************************** Top Panel Methods *********************************
    //**************************************************************************************
    // Non-Interactive GUI Object Methods

    //**************************************************************************************
    // Interactive GUI Object Methods

    //**************************************************************************************
    // Panel object methods
    private JPanel createCenterFrame(){
        JPanel pnl = new JPanel();

        JLabel lineSplitter = new JLabel();
        Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));
        lineSplitter.setBorder(lineSplitterBoarder);

        pnl.setOpaque(false);
        return pnl;
    }

    private JScrollPane createCenterScrollPane(){
        JScrollPane scrollPane = new JScrollPane(backgroundFrame);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border lineBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(224,224,224));
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compoundSetup = BorderFactory.createCompoundBorder(raisedBevel, lineBorder);
        Border compoundFinal = BorderFactory.createCompoundBorder(compoundSetup, loweredBevel);

        scrollPane.setBorder(compoundFinal);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setView(centerFrame);
        return scrollPane;
    }

    //**************************************************************************************
    //********************************* Program Starts Here ********************************
    //**************************************************************************************
    // Starts here

    public static void main(String[] args) {
        new StartGui();
    }
}


