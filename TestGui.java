/**
 * Created by James Page on 4/16/2017.
 */
package Main;

import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class TestGui extends JFrame {
    private final String[] guiCharSelDefault = {"---  Select Character ---"};
    private final String[] characters = {"charOne", "charTwo", "charThree", "charFour"};
    private final String[] GuiCharSel = (String[]) ArrayUtils.addAll(guiCharSelDefault, characters);
    private final String[] weapon = {"Weapon"};
    private final String[][] allWeapons = {
            {
                    "weakWeaponOne", "strongWeaponOne", "shortWeaponOne", "longWeaponOne"
            },
            {
                    "weakWeaponTwo", "strongWeaponTwo", "shortWeaponTwo", "longWeaponTwo"
            },
            {
                    "weakWeaponThree", "strongWeaponThree", "shortWeaponThree", "longWeaponThree"
            },
            {
                    "weakWeaponFour", "strongWeaponFour", "shortWeaponFour", "longWeaponFour"
            }
    };
    private JComboBox charCombo = new JComboBox(GuiCharSel);
    private JComboBox weaponsCombo = new JComboBox(weapon);
    private BackgroundPanel backgroundFrame = createBackgroundFrame("../images/Background.png");
    private JPanel topFrame = createTopFrame();
    private JScrollPane topFrameScroll = createTopScrollPane();
    private JPanel centerFrame = createCenterFrame();

    //**************************************************************************************

    private static GridBagConstraints setGbc(int gridx, int gridy, int gridWidth, int gridHeight, int ipadx, int ipady, String anchorLocation, double weightx, double weighty, Insets insets){
        GridBagConstraints gbc = new GridBagConstraints();

        if (anchorLocation.toUpperCase().equals("NORTHWEST")){
            gbc.anchor = GridBagConstraints.NORTHWEST;
        } else if (anchorLocation.toUpperCase().equals("NORTH")){
            gbc.anchor = GridBagConstraints.NORTH;
        } else if (anchorLocation.toUpperCase().equals("NORTHEAST")){
            gbc.anchor = GridBagConstraints.NORTHEAST;
        } else if (anchorLocation.toUpperCase().equals("WEST")){
            gbc.anchor = GridBagConstraints.WEST;
        } else if (anchorLocation.toUpperCase().equals("EAST")){
            gbc.anchor = GridBagConstraints.EAST;
        } else if (anchorLocation.toUpperCase().equals("SOUTHWEST")){
            gbc.anchor = GridBagConstraints.SOUTHWEST;
        } else if (anchorLocation.toUpperCase().equals("SOUTH")){
            gbc.anchor = GridBagConstraints.SOUTH;
        } else if (anchorLocation.toUpperCase().equals("SOUTHEAST")){
            gbc.anchor = GridBagConstraints.SOUTHEAST;
        } else {
            gbc.anchor = GridBagConstraints.CENTER;
        }

        gbc.gridx = gridx; // column
        gbc.gridy = gridy; // row
        gbc.gridwidth = gridWidth; // number of columns
        gbc.gridheight = gridHeight; // number of rows
        gbc.ipadx = ipadx; // width of object
        gbc.ipady = ipady; // height of object
        gbc.weightx = weightx; // shifts rows to side of set anchor
        gbc.weighty = weighty; // shifts columns to side of set anchor
        gbc.insets = insets; // placement inside cell
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;

        return gbc;
    }

    private Insets setInsets(int top, int left, int bottom, int right){
        Insets insets = new Insets(top,left,bottom,right);
        return insets;
    }

    //**************************************************************************************

    private BackgroundPanel createBackgroundFrame(String imgLocName){
        Image backgroundImg = null;
        try {
            backgroundImg = ImageIO.read(getClass().getResource(imgLocName));
            System.out.println("File: " + imgLocName.toString());
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        BackgroundPanel bgPanel = new BackgroundPanel(backgroundImg, BackgroundPanel.SCALED, 0.0f, 0.0f);
        return bgPanel;
    }

    private JPanel createTopFrame(){
        JPanel pnl = new JPanel();
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Color lineColor = new Color(224,224,224);
        Border lineBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, lineColor);
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compoundSetup = BorderFactory.createCompoundBorder(raisedBevel, lineBorder);
        Border compoundFinal = BorderFactory.createCompoundBorder(compoundSetup, loweredBevel);
        TitledBorder topFrameTitle = BorderFactory.createTitledBorder(compoundFinal, "Character");
        topFrameTitle.setTitleJustification(TitledBorder.CENTER);

        JLabel lineSplitter = new JLabel();
        Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));
        lineSplitter.setBorder(lineSplitterBoarder);

        pnl.setBorder(topFrameTitle);
        pnl.setLayout(new GridBagLayout());
        pnl.setPreferredSize(new Dimension(200, 60));

        charCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String charName = ((JComboBox)(e.getSource())).getSelectedItem().toString();
                        if (charName.equals("charOne")){
                            weaponsCombo.removeAllItems();
                            weaponsCombo.setModel(new DefaultComboBoxModel(allWeapons[1]));
                        }
                    }
                }
        );
        pnl.add(charCombo, setGbc(0,0, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 0, 0, 0)));
        pnl.add(lineSplitter, setGbc(1,0, 1,1, 0,0, "WEST", 0, 0, setInsets(0, 10, 0, 0)));
        pnl.add(weaponsCombo, setGbc(2,0, 1,1, 0,0, "WEST", 1, 1, setInsets(0, 10, 0, 0)));

        pnl.setOpaque(false);
        return pnl;
    }

    private JPanel createCenterFrame() {
        JPanel pnl = new JPanel();
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Color lineColor = new Color(224, 224, 224);
        Border lineBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, lineColor);
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compoundSetup = BorderFactory.createCompoundBorder(raisedBevel, lineBorder);
        Border compoundFinal = BorderFactory.createCompoundBorder(compoundSetup, loweredBevel);
        TitledBorder topFrameTitle = BorderFactory.createTitledBorder(compoundFinal, "Stuff");
        topFrameTitle.setTitleJustification(TitledBorder.CENTER);

        pnl.setBorder(topFrameTitle);
        pnl.setLayout(new GridBagLayout());

        pnl.setOpaque(false);
        return pnl;
    }

    private JScrollPane createTopScrollPane(){
        JScrollPane scrollPane = new JScrollPane(backgroundFrame);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Color lineColor = new Color(224, 224, 224);
        Border lineBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, lineColor);
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compoundSetup = BorderFactory.createCompoundBorder(raisedBevel, lineBorder);
        Border compoundFinal = BorderFactory.createCompoundBorder(compoundSetup, loweredBevel);
        TitledBorder topFrameTitle = BorderFactory.createTitledBorder(compoundFinal, "ScrollPane");
        topFrameTitle.setTitleJustification(TitledBorder.CENTER);

        scrollPane.setPreferredSize(new Dimension(topFrame.getWidth(), 154));
        scrollPane.setBorder(topFrameTitle);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    TestGui(){
        setContentPane(backgroundFrame);
        add(topFrame, BorderLayout.NORTH);
        add(centerFrame, BorderLayout.CENTER);
        add(topFrameScroll, BorderLayout.SOUTH);

        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //**************************************************************************************

    public static void main(String[] args) {
        new TestGui();
    }
}