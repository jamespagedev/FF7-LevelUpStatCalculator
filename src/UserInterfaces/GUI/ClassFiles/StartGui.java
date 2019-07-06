/**
 * Created by James Page on 4/7/2017.
 */
package ClassFiles;

import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import static ClassFiles.SupportMethods.*;

public class StartGui extends JFrame {
    //**************************************************************************************
    //************************************** Main GUI **************************************
    //**************************************************************************************
    // Interactive Objects
    public Characters[] characters = createCharacters(); // {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"}
    public int selectedCharactersIndex = 0;
    public Armors armors = new Armors();
    public Accessories accessories = new Accessories();
    public Materia materia = new Materia();

    //**************************************************************************************
    // Constructor
    StartGui() {
        /*
         * Display everything in GUI to user
         */
        int mainWindowSizeWidth = 800;
        int mainWindowSizeHeight = 600;

        setContentPane(backgroundFrame);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(guiImgIconLocName)));
        setTitle("Final Fantasy 7 Level Up Calculator");
        add(topFrameScroll, BorderLayout.NORTH);
        add(centerFrameScroll, BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        revalidate();
                        repaint();
                    }
                });
            }
        });

        setSize(mainWindowSizeWidth,mainWindowSizeHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //**************************************************************************************
    // Interactive Object Methods

    public Characters[] createCharacters(){
        Characters[] arr = new Characters[Characters.getListOfCharacterNames().length];

        int CharacterIndex = 0;
        for (String character : Characters.getListOfCharacterNames()){
            arr[CharacterIndex] = new Characters(character);
            CharacterIndex++;
        }

        return arr;
    }

    public void setSelectedCharacterIndex(String name){
        int index = 0;
        for (Characters character : this.characters){
            if (name.equals(character.getCharacterName())){
                this.selectedCharactersIndex = index;
            }
            index++;
        }
    }

    public int getSelectedCharactersIndex(){
        return this.selectedCharactersIndex;
    }

    //**************************************************************************************
    //*************************** Top Panel Variables and Objects **************************
    //**************************************************************************************
    // Object Values
    public final String currLvlLabelValue = "Select Current Level:";
    public final String nextLvlLabelValue = "Next Level:";
    public final String equipLabelValue = "Equipment";
    public final String materiaLabelValue = "Materia (Max Level)";
    public final String resetButtonValue = "Reset";
    public final String imgDir = "/Images/";
    public final String backgroundLocName = imgDir+"Background.png";
    public final String guiImgIconLocName = imgDir+"TitleBarIcon.png";
    public final String[] guiCharSelDefaultValue = {"---  Select Character ---"};
    public boolean charSelModelsSet = false;
    public final String[] currLvlAnsComboDefaultValue = {"- -"};
    public final String[] currLvlAnsComboValues = convertIntArrToStrArr(characters[getSelectedCharactersIndex()].getLevelRanges());
    public final String nextLvlAnsLabelDefaultValue = "- -";
    public final String[] weaponComboDefault = {"Weapon"};
    public final String[] armorComboDefault = {"Armor"};
    public final String[] accessoryComboDefault = {"Accessory"};
    public final String[] materiaComboDefaultValue = {"Materia"};
    public final String[] guiCharSel = (String[])ArrayUtils.addAll(guiCharSelDefaultValue, Characters.getListOfCharacterNames());
    public final int unselectedDefaultElement = 0;
    public final int minLvl = 1;
    public final int maxLvl = characters[getSelectedCharactersIndex()].getLevelRanges()[characters[getSelectedCharactersIndex()].getLevelRanges().length - 1];

    //**************************************************************************************
    // Non-Interactive GUI Objects
    public JLabel currLvlLabel = createWhiteBoldLabel(currLvlLabelValue, 14);
    public JLabel nextLvlLabel = createWhiteBoldLabel(nextLvlLabelValue, 14);
    public JLabel equipLabel = createWhiteBoldLabel(equipLabelValue, 20);
    public JLabel materiaLabel = createWhiteBoldLabel(materiaLabelValue, 20);

    //**************************************************************************************
    // Interactive GUI Objects (Objects where the value/behavior will change)
    public JLabel charImgLabel = createCharPicLabel();
    public JComboBox charSelCombo = createStandardCombo(guiCharSel);
    public JComboBox currLvlAnsCombo = createCurrLvlAnsCombo(currLvlAnsComboDefaultValue);
    public JLabel  nextLvlAnsLabel = createWhiteBoldLabel(nextLvlAnsLabelDefaultValue, 14);
    public JComboBox weaponCombo = createStandardCombo(weaponComboDefault);
    public JComboBox armorCombo = createStandardCombo(armorComboDefault);
    public JComboBox accessoryCombo = createStandardCombo(accessoryComboDefault);
    public JComboBox[] materiaWpnCombos = createMateriaCombos(materiaComboDefaultValue, materia.getMateriaLists(), 8);
    public JComboBox[] materiaArmCombos = createMateriaCombos(materiaComboDefaultValue, materia.getMateriaLists(), 8);
    public JButton resetButton = createResetButton(resetButtonValue);
    //**************************************************************************************
    /*
     * Object panels holding both interactive and non-interactive objects
     * and their action behaviors in GUI
     */
    public BackgroundPanel backgroundFrame = createBackgroundFrame(backgroundLocName);
    public JPanel[] materiaWpnPanels = createMateriaWpnArmPanels(materiaWpnCombos);
    public JPanel[] materiaArmPanels = createMateriaWpnArmPanels(materiaArmCombos);
    public JPanel topFrame = createTopFrame();
    public JScrollPane topFrameScroll = createTopScrollPane();

    //**************************************************************************************
    //************************* Center Panel Variables and Objects *************************
    //**************************************************************************************
    // Center Panel Variables
    public String statsAndIncreaseRatesLabelValue = "Stats And Increase Rates";
    public String hpIncreaseRatesLabelValue = "HP Increase Rates:";
    public String hpLabelValue = "HP:";
    public String mpIncreaseRatesLabelValue = "MP Increase Rates:";
    public String mpLabelValue = "MP:";
    public String strIncreaseRatesLabelValue = "Strength Increase Rates:";
    public String strLabelValue = "Strength:";
    public String dexIncreaseRatesLabelValue = "Dexterity Increase Rates:";
    public String dexLabelValue = "Dexterity:";
    public String vitIncreaseRatesLabelValue = "Vitality Increase Rates:";
    public String vitLabelValue = "Vitality:";
    public String magIncreaeRatesLabselValue = "Magic Increase Rates:";
    public String magLabelValue = "Magic:";
    public String sprIncreaseRatesLabelValue = "Spirit Increase Rates:";
    public String sprLabelValue = "Spirit:";
    public String lckIncreaseRatesLabelValue = "Luck Increase Rates:";
    public String lckLabelValue = "Luck:";
    public String baseStatsLabelValue = "Base Stats";
    public String statsDefaultValue = "0";
    public String statsWithGearLabelValue = "Stats With Gear";
    public String increasedStatsLabelValue = "Static Stat Increase";
    public String StatsAfterLevelUpWithGearLabelValue = "Stats After Level Up (With Gear)";
    public String hpMpLevelUpResultRateLabelDefaultValue = "0%";
    public String statLevelUpResultLabelsDefaultValue = statsDefaultValue + " (" + statsDefaultValue + ")";
    public String[] statLevelUpResultRndNumLabelValue = {"Rnd 1", "Rnd 2", "Rnd 3", "Rnd 4", "Rnd 5", "Rnd 6", "Rnd 7", "Rnd 8"};

    //**************************************************************************************
    // Center Panel Interactive GUI Objects

    //**************************************************************************************
    // Center Panel Non-Interactive GUI Objects
    //======================================================================================
    public int statsAndIncreaseRatesLabelsFontSize = 16;
    public int statsAndIncreaseRatesLabelsWidth = 210;
    public int statsAndIncreaseRatesLabelsHeight = 40;
    public JLabel statsAndIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(statsAndIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(hpIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel hpLabel = createWhiteBoldFixedSizeAlignLabel(hpLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(mpIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLabel = createWhiteBoldFixedSizeAlignLabel(mpLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(strIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLabel = createWhiteBoldFixedSizeAlignLabel(strLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(dexIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLabel = createWhiteBoldFixedSizeAlignLabel(dexLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(vitIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLabel = createWhiteBoldFixedSizeAlignLabel(vitLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(magIncreaeRatesLabselValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLabel = createWhiteBoldFixedSizeAlignLabel(magLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(sprIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLabel = createWhiteBoldFixedSizeAlignLabel(sprLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckIncreaseRatesLabel = createWhiteBoldFixedSizeAlignLabel(lckIncreaseRatesLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLabel = createWhiteBoldFixedSizeAlignLabel(lckLabelValue, statsAndIncreaseRatesLabelsFontSize, statsAndIncreaseRatesLabelsWidth, statsAndIncreaseRatesLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //======================================================================================
    public int baseStatsLabelsFontSize = 16;
    public int baseStatsLabelsWidth = 100;
    public int baseStatsLabelsHeight = 40;
    public JLabel baseStatsLabel = createWhiteBoldFixedSizeAlignLabel(baseStatsLabelValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JTextField hpBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField mpBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField strBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField dexBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField vitBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField magBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField sprBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    public JTextField lckBaseStatsTextField = createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(statsDefaultValue, baseStatsLabelsFontSize, baseStatsLabelsWidth, baseStatsLabelsHeight, SwingConstants.LEFT);
    //======================================================================================
    public int statsWithGearLabelsFontSize = 16;
    public int statsWithGearLabelsWidth = 130;
    public int statsWithGearLabelsHeight = 40;
    public JLabel statsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsWithGearLabelValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel mpStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel strStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel dexStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel vitStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel magStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel sprStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    public JLabel lckStatsWithGearLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, statsWithGearLabelsFontSize, statsWithGearLabelsWidth, statsWithGearLabelsHeight, SwingConstants.LEFT, SwingConstants.CENTER);
    //======================================================================================
    public int increasedStatsLabelsFontSize = 16;
    public int increasedStatsLabelsWidth = 160;
    public int increasedStatsLabelsHeight = 40;
    public JLabel increasedStatsLabel = createWhiteBoldFixedSizeAlignLabel(increasedStatsLabelValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckIncreasedLabel = createWhiteBoldFixedSizeAlignLabel(statsDefaultValue, increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckIncreasedEmptyLabel = createWhiteBoldFixedSizeAlignLabel("", increasedStatsLabelsFontSize, increasedStatsLabelsWidth, increasedStatsLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    //======================================================================================
    public int statsAfterLevelUpWithGearLabelsFontSize = 16;
    public int statsAfterLevelUpWithGearLabelsWidth = 100;
    public int statsAfterLevelUpWithGearLabelWidth = statsAfterLevelUpWithGearLabelsWidth * 8 ;
    public int statsAfterLevelUpWithGearLabelsHeight = 40;
    public JLabel StatsAfterLevelUpWithGearLabel = createWhiteBoldFixedSizeAlignLabel(StatsAfterLevelUpWithGearLabelValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRateRndOneLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndOneLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[0], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[0], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[0], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[0], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[0], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[0], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultOneLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[1], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[1], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[1], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[1], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[1], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[1], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultTwoLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[2], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[2], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[2], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[2], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[2], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[2], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultThreeLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndFourLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndFourLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[3], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[3], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[3], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[3], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[3], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[3], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultFourLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[4], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[4], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[4], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[4], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[4], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[4], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultFiveLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndSixLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndSixLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[5], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[5], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[5], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[5], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[5], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[5], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultSixLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[6], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[6], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[6], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[6], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[6], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[6], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultSevenLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //--------------------------------------------------------------------------------------
    public JLabel hpLevelUpResultRateRndEightLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel hpLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRateRndEightLabel = createWhiteBoldFixedSizeAlignLabel(hpMpLevelUpResultRateLabelDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel mpLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel strLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[7], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel strLevelUpResultEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel dexLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[7], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel dexLevelUpResultEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel vitLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[7], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel vitLevelUpResultEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel magLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[7], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel magLevelUpResultEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel sprLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[7], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel sprLevelUpResultEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    public JLabel lckLevelUpResultRndEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultRndNumLabelValue[7], statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.CENTER, SwingConstants.CENTER);
    public JLabel lckLevelUpResultEightLabel = createWhiteBoldFixedSizeAlignLabel(statLevelUpResultLabelsDefaultValue, statsAfterLevelUpWithGearLabelsFontSize, statsAfterLevelUpWithGearLabelsWidth, statsAfterLevelUpWithGearLabelsHeight, SwingConstants.RIGHT, SwingConstants.CENTER);
    //======================================================================================

    //**************************************************************************************
    /*
     * Object panels holding both interactive and non-interactive objects
     * and their action behaviors in GUI
     */
    public JPanel centerFrame = createCenterFrame();
    public JScrollPane centerFrameScroll = createCenterScrollPane();

    //**************************************************************************************
    //********************************** Top Panel Methods *********************************
    //**************************************************************************************
    // Top Panel Non-Interactive GUI Object Methods

    public JLabel createWhiteBoldLabel(String text, int textSize){
        JLabel lbl = new JLabel(text);

        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font(text, Font.BOLD, textSize));
        return lbl;
    }

    public JLabel createWhiteBoldFixedSizeAlignLabel(String text, int textSize, int width, int height, int hAlign, int vAlign){
        JLabel lbl = new JLabel(text);

        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font(text, Font.BOLD, textSize));
        lbl.setPreferredSize(new Dimension(width, height));
        lbl.setHorizontalAlignment(hAlign);
        lbl.setVerticalAlignment(vAlign);
        return lbl;
    }

    //**************************************************************************************
    // Top Panel Interactive GUI Object Methods

    public JLabel createCharPicLabel(){
        JLabel labelPic = new JLabel("", null, JLabel.CENTER);
        labelPic.setPreferredSize(new Dimension(100,100));
        return labelPic;
    }

    public JComboBox createStandardCombo(String[] defaultValue){
        JComboBox comboBox = new JComboBox(defaultValue);
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(dlcr);
        comboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXX");
        return comboBox;
    }

    public JTextField createWhiteBoldFgDarkGreyBgFixedSizeAlignTextField(String text, int textSize, int width, int height, int hAlign){
        JTextField txtField = new JTextField(text);

        txtField.setForeground(Color.WHITE);
        txtField.setBackground(new Color(50,50,50));
        txtField.setCaretColor(Color.CYAN);
        txtField.setFont(new Font(text, Font.BOLD, textSize));
        txtField.setPreferredSize(new Dimension(width, height));
        txtField.setHorizontalAlignment(hAlign);
        return txtField;
    }

    public void setModels(Object characterSelected){
        charSelCombo.setModel(new DefaultComboBoxModel(Characters.getListOfCharacterNames()));
        charSelCombo.setSelectedItem(characterSelected);
        currLvlAnsCombo.setModel(new DefaultComboBoxModel(currLvlAnsComboValues));
        weaponCombo.setModel(new DefaultComboBoxModel(characters[getSelectedCharactersIndex()].getListOfCharacterWeapons()));
        armorCombo.setModel(new DefaultComboBoxModel(armors.getArmorsList()));
        accessoryCombo.setModel(new DefaultComboBoxModel(accessories.getAccessoriesList()));
        for(int mSlot = 0; mSlot < materiaWpnCombos.length; mSlot++){
            materiaWpnCombos[mSlot].setModel(getMateriaModelList());
        }
        for(int mSlot = 0; mSlot < materiaArmCombos.length; mSlot++){
            materiaArmCombos[mSlot].setModel(getMateriaModelList());
        }
    }

    public void setCharSelComboAction(){
        charSelCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String charName = ((JComboBox)(e.getSource())).getSelectedItem().toString();
                        Object charSelectedItem = ((JComboBox)(e.getSource())).getSelectedItem();
                        if (!(charName.equals(guiCharSelDefaultValue[unselectedDefaultElement]))){
                            String image = imgDir+charName+".png";
                            charImgLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(image)).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                            setSelectedCharacterIndex(charName); // sets the index used for characters object array
                            if (!(charSelModelsSet)){
                                setModels(charSelectedItem);
                                charSelModelsSet = true;
                            } else {
                                weaponCombo.setModel(new DefaultComboBoxModel(characters[getSelectedCharactersIndex()].getListOfCharacterWeapons()));
                            }
                            currLvlAnsCombo.setSelectedItem(Integer.toString(characters[getSelectedCharactersIndex()].getLevel()));
                            weaponCombo.setSelectedItem(characters[getSelectedCharactersIndex()].getEquipedWeapon().getWeaponName());
                            armorCombo.setSelectedItem(characters[getSelectedCharactersIndex()].getEquipedArmor().getArmorName());
                            accessoryCombo.setSelectedItem(characters[getSelectedCharactersIndex()].getEquipedAccessory().getAccessoryName());
                            hpBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseHp()));
                            mpBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseMp()));
                            strBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseStrength()));
                            dexBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseDexterity()));
                            vitBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseVitality()));
                            magBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseMagic()));
                            sprBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseSpirit()));
                            lckBaseStatsTextField.setText(Integer.toString(characters[getSelectedCharactersIndex()].getBaseLuck()));
                        }
                    }
                }
        );
    }

    public JComboBox createCurrLvlAnsCombo(String[] arr){
        JComboBox comboBox = new JComboBox(arr);
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(dlcr);
        comboBox.setPrototypeDisplayValue("X" + getLongestStrFromArr(arr) + "X");
        return comboBox;
    }

    public void setCurrLvlAnsComboAction(){
        currLvlAnsCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String currLvl = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && (isInteger(currLvl))){
                            characters[getSelectedCharactersIndex()].setLevel(Integer.parseInt(currLvl));
                            updateNextLvlAnsLabel();
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void updateNextLvlAnsLabel(){
        String currLvl = currLvlAnsCombo.getSelectedItem().toString();
        if(isInteger(currLvl)){
            if (Integer.parseInt(currLvl) == maxLvl){
                nextLvlAnsLabel.setText(nextLvlAnsLabelDefaultValue);
            } else {
                nextLvlAnsLabel.setText(Integer.toString(Integer.parseInt(currLvl) + 1));
            }
        } else {
            nextLvlAnsLabel.setText(nextLvlAnsLabelDefaultValue);
        }
    }

    public void setWeaponComboAction(){
        weaponCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currWpn = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currWpn.equals(weaponComboDefault[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipWeapon(currWpn);
                            setMateriaWpnCombosSlotsInGui(characters[getSelectedCharactersIndex()]);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setArmorComboAction(){
        armorCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currArm = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currArm.equals(armorComboDefault[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipArmor(currArm);
                            setMateriaArmCombosSlotsInGui(characters[getSelectedCharactersIndex()]);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setAccessoryComboAction(){
        accessoryCombo.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currAcc = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currAcc.equals(accessoryComboDefault[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipAccessory(currAcc);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public DefaultComboBoxModel getMateriaModelList(){
        DefaultComboBoxModel materiaModelList;
        ArrayList<String> elements = new ArrayList<String>();
        elements.addAll(java.util.Arrays.asList(materia.getEmptyMateriaList()));
        elements.addAll(java.util.Arrays.asList(materia.getGreenMateriaList()));
        elements.addAll(java.util.Arrays.asList(materia.getYellowMateriaList()));
        elements.addAll(java.util.Arrays.asList(materia.getRedMateriaList()));
        elements.addAll(java.util.Arrays.asList(materia.getBlueMateriaList()));
        elements.addAll(java.util.Arrays.asList(materia.getPurpleMateriaList()));
        materiaModelList = new DefaultComboBoxModel(elements.toArray());
        return materiaModelList;
    }

    public void setMateriaWpnCombosSlotsInGui(Characters characterSelected){
        int mSlotNotAvailable = 0;
        for (int mSlot = 0; mSlot < characterSelected.getEquipedWeapon().getNumOfMateriaSlots(); mSlot++){
            materiaWpnCombos[mSlot].setSelectedItem(characterSelected.getMateriaInWpnSlot(mSlot + 1).getMateriaName());
            materiaWpnCombos[mSlot].setVisible(true);
            mSlotNotAvailable++;
        }
        for (; mSlotNotAvailable < materiaWpnCombos.length; mSlotNotAvailable++){
            materiaWpnCombos[mSlotNotAvailable].setSelectedItem(materiaComboDefaultValue[unselectedDefaultElement]);
            materiaWpnCombos[mSlotNotAvailable].setVisible(false);
        }
    }

    public void setMateriaArmCombosSlotsInGui(Characters characterSelected){
        int mSlotNotAvailable = 0;
        for (int mSlot = 0; mSlot < characterSelected.getEquipedArmor().getNumOfMateriaSlots(); mSlot++){
            materiaArmCombos[mSlot].setSelectedItem(characterSelected.getMateriaInArmSlot(mSlot + 1).getMateriaName());
            materiaArmCombos[mSlot].setVisible(true);
            mSlotNotAvailable++;
        }
        for (; mSlotNotAvailable < materiaArmCombos.length; mSlotNotAvailable++){
            materiaArmCombos[mSlotNotAvailable].setSelectedItem(materiaComboDefaultValue[unselectedDefaultElement]);
            materiaArmCombos[mSlotNotAvailable].setVisible(false);
        }
    }

    public void setMateriaWpnComboOneAction(){
        materiaWpnCombos[0].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(1, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboTwoAction(){
        materiaWpnCombos[1].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(2, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboThreeAction(){
        materiaWpnCombos[2].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(3, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboFourAction(){
        materiaWpnCombos[3].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(4, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboFiveAction(){
        materiaWpnCombos[4].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(5, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboSixAction(){
        materiaWpnCombos[5].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(6, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboSevenAction(){
        materiaWpnCombos[6].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(7, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaWpnComboEightAction(){
        materiaWpnCombos[7].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInWeapon(8, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboOneAction(){
        materiaArmCombos[0].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(1, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboTwoAction(){
        materiaArmCombos[1].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(2, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboThreeAction(){
        materiaArmCombos[2].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(3, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboFourAction(){
        materiaArmCombos[3].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(4, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboFiveAction(){
        materiaArmCombos[4].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(5, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboSixAction(){
        materiaArmCombos[5].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(6, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboSevenAction(){
        materiaArmCombos[6].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(7, currMateria);
                        }
                    }
                }
        );
    }

    public void setMateriaArmComboEightAction(){
        materiaArmCombos[7].addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String currMateria = ((JComboBox) (e.getSource())).getSelectedItem().toString();
                        if (!(currMateria.equals(materiaComboDefaultValue[unselectedDefaultElement]))){
                            characters[getSelectedCharactersIndex()].equipMateriaInArmor(8, currMateria);
                            updateAllStatGridRowLabelValues();
                        }
                    }
                }
        );
    }

    public JComboBox[] createMateriaCombos(String[] defaultValue, String[][] arr, int numOfComboBoxes){
        JComboBox[] comboBoxes = new JComboBox[numOfComboBoxes];
        MateriaComboBoxRenderer mcbr = new MateriaComboBoxRenderer();
        for (int i = 0; i < numOfComboBoxes; i++){
            comboBoxes[i] = new JComboBox(defaultValue);
            comboBoxes[i].setRenderer(mcbr);
            comboBoxes[i].setPrototypeDisplayValue("X" + getLongestStrFromArr(arr) + "X");
        }
        return comboBoxes;
    }

    public void setResetButtonAction(){
        resetButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String selCharacter = charSelCombo.getSelectedItem().toString();
                        if (!(selCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))){
                            // Reset Character Selection Dropbox
                            charSelCombo.setModel(new DefaultComboBoxModel(guiCharSel));
                            charSelCombo.setSelectedItem(guiCharSelDefaultValue[unselectedDefaultElement]);

                            // Reset Character Image To Null
                            charImgLabel.setIcon(null);
                            charImgLabel.revalidate();

                            // Reset Level Values To Null
                            currLvlAnsCombo.setModel(new DefaultComboBoxModel(currLvlAnsComboDefaultValue));
                            nextLvlAnsLabel.setText(nextLvlAnsLabelDefaultValue);

                            // Reset Equipment Dropboxes
                            weaponCombo.setModel(new DefaultComboBoxModel(weaponComboDefault));
                            armorCombo.setModel(new DefaultComboBoxModel(armorComboDefault));
                            accessoryCombo.setModel(new DefaultComboBoxModel(accessoryComboDefault));

                            // Reset Weapon Materia Dropboxes
                            for(int i = 0; i < materiaWpnCombos.length; i++){
                                materiaWpnCombos[i].setModel(new DefaultComboBoxModel(materiaComboDefaultValue));
                                materiaWpnCombos[i].setVisible(true);
                            }

                            // Reset Armor Materia Dropboxes
                            for(int i = 0; i < materiaArmCombos.length; i++){
                                materiaArmCombos[i].setModel(new DefaultComboBoxModel(materiaComboDefaultValue));
                                materiaArmCombos[i].setVisible(true);
                            }

                            // Reset HP Row
                            hpBaseStatsTextField.setText(statsDefaultValue);
                            hpStatsWithGearLabel.setText(statsDefaultValue);
                            hpIncreasedLabel.setText(statsDefaultValue);
                            hpLevelUpResultRateRndOneLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndTwoLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndThreeLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndFourLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndFiveLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndSixLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndSevenLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            hpLevelUpResultRateRndEightLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            hpLevelUpResultRndEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset MP Row
                            mpBaseStatsTextField.setText(statsDefaultValue);
                            mpStatsWithGearLabel.setText(statsDefaultValue);
                            mpIncreasedLabel.setText(statsDefaultValue);
                            mpLevelUpResultRateRndOneLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndTwoLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndThreeLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndFourLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndFiveLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndSixLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndSevenLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            mpLevelUpResultRateRndEightLabel.setText(hpMpLevelUpResultRateLabelDefaultValue);
                            mpLevelUpResultRndEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Str Row
                            strBaseStatsTextField.setText(statsDefaultValue);
                            strStatsWithGearLabel.setText(statsDefaultValue);
                            strIncreasedLabel.setText(statsDefaultValue);
                            strLevelUpResultRndOneLabel.setText(statLevelUpResultRndNumLabelValue[0]);
                            strLevelUpResultOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndTwoLabel.setText(statLevelUpResultRndNumLabelValue[1]);
                            strLevelUpResultTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndThreeLabel.setText(statLevelUpResultRndNumLabelValue[2]);
                            strLevelUpResultThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndFourLabel.setText(statLevelUpResultRndNumLabelValue[3]);
                            strLevelUpResultFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndFiveLabel.setText(statLevelUpResultRndNumLabelValue[4]);
                            strLevelUpResultFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndSixLabel.setText(statLevelUpResultRndNumLabelValue[5]);
                            strLevelUpResultSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndSevenLabel.setText(statLevelUpResultRndNumLabelValue[6]);
                            strLevelUpResultSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            strLevelUpResultRndEightLabel.setText(statLevelUpResultRndNumLabelValue[7]);
                            strLevelUpResultEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Dex Row
                            dexBaseStatsTextField.setText(statsDefaultValue);
                            dexStatsWithGearLabel.setText(statsDefaultValue);
                            dexIncreasedLabel.setText(statsDefaultValue);
                            dexLevelUpResultRndOneLabel.setText(statLevelUpResultRndNumLabelValue[0]);
                            dexLevelUpResultOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndTwoLabel.setText(statLevelUpResultRndNumLabelValue[1]);
                            dexLevelUpResultTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndThreeLabel.setText(statLevelUpResultRndNumLabelValue[2]);
                            dexLevelUpResultThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndFourLabel.setText(statLevelUpResultRndNumLabelValue[3]);
                            dexLevelUpResultFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndFiveLabel.setText(statLevelUpResultRndNumLabelValue[4]);
                            dexLevelUpResultFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndSixLabel.setText(statLevelUpResultRndNumLabelValue[5]);
                            dexLevelUpResultSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndSevenLabel.setText(statLevelUpResultRndNumLabelValue[6]);
                            dexLevelUpResultSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            dexLevelUpResultRndEightLabel.setText(statLevelUpResultRndNumLabelValue[7]);
                            dexLevelUpResultEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Vit Row
                            vitBaseStatsTextField.setText(statsDefaultValue);
                            vitStatsWithGearLabel.setText(statsDefaultValue);
                            vitIncreasedLabel.setText(statsDefaultValue);
                            vitLevelUpResultRndOneLabel.setText(statLevelUpResultRndNumLabelValue[0]);
                            vitLevelUpResultOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndTwoLabel.setText(statLevelUpResultRndNumLabelValue[1]);
                            vitLevelUpResultTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndThreeLabel.setText(statLevelUpResultRndNumLabelValue[2]);
                            vitLevelUpResultThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndFourLabel.setText(statLevelUpResultRndNumLabelValue[3]);
                            vitLevelUpResultFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndFiveLabel.setText(statLevelUpResultRndNumLabelValue[4]);
                            vitLevelUpResultFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndSixLabel.setText(statLevelUpResultRndNumLabelValue[5]);
                            vitLevelUpResultSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndSevenLabel.setText(statLevelUpResultRndNumLabelValue[6]);
                            vitLevelUpResultSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            vitLevelUpResultRndEightLabel.setText(statLevelUpResultRndNumLabelValue[7]);
                            vitLevelUpResultEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Mag Row
                            magBaseStatsTextField.setText(statsDefaultValue);
                            magStatsWithGearLabel.setText(statsDefaultValue);
                            magIncreasedLabel.setText(statsDefaultValue);
                            magLevelUpResultRndOneLabel.setText(statLevelUpResultRndNumLabelValue[0]);
                            magLevelUpResultOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndTwoLabel.setText(statLevelUpResultRndNumLabelValue[1]);
                            magLevelUpResultTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndThreeLabel.setText(statLevelUpResultRndNumLabelValue[2]);
                            magLevelUpResultThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndFourLabel.setText(statLevelUpResultRndNumLabelValue[3]);
                            magLevelUpResultFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndFiveLabel.setText(statLevelUpResultRndNumLabelValue[4]);
                            magLevelUpResultFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndSixLabel.setText(statLevelUpResultRndNumLabelValue[5]);
                            magLevelUpResultSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndSevenLabel.setText(statLevelUpResultRndNumLabelValue[6]);
                            magLevelUpResultSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            magLevelUpResultRndEightLabel.setText(statLevelUpResultRndNumLabelValue[7]);
                            magLevelUpResultEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Spr Row
                            sprBaseStatsTextField.setText(statsDefaultValue);
                            sprStatsWithGearLabel.setText(statsDefaultValue);
                            sprIncreasedLabel.setText(statsDefaultValue);
                            sprLevelUpResultRndOneLabel.setText(statLevelUpResultRndNumLabelValue[0]);
                            sprLevelUpResultOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndTwoLabel.setText(statLevelUpResultRndNumLabelValue[1]);
                            sprLevelUpResultTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndThreeLabel.setText(statLevelUpResultRndNumLabelValue[2]);
                            sprLevelUpResultThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndFourLabel.setText(statLevelUpResultRndNumLabelValue[3]);
                            sprLevelUpResultFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndFiveLabel.setText(statLevelUpResultRndNumLabelValue[4]);
                            sprLevelUpResultFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndSixLabel.setText(statLevelUpResultRndNumLabelValue[5]);
                            sprLevelUpResultSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndSevenLabel.setText(statLevelUpResultRndNumLabelValue[6]);
                            sprLevelUpResultSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            sprLevelUpResultRndEightLabel.setText(statLevelUpResultRndNumLabelValue[7]);
                            sprLevelUpResultEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Lck Row
                            lckBaseStatsTextField.setText(statsDefaultValue);
                            lckStatsWithGearLabel.setText(statsDefaultValue);
                            lckIncreasedLabel.setText(statsDefaultValue);
                            lckLevelUpResultRndOneLabel.setText(statLevelUpResultRndNumLabelValue[0]);
                            lckLevelUpResultOneLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndTwoLabel.setText(statLevelUpResultRndNumLabelValue[1]);
                            lckLevelUpResultTwoLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndThreeLabel.setText(statLevelUpResultRndNumLabelValue[2]);
                            lckLevelUpResultThreeLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndFourLabel.setText(statLevelUpResultRndNumLabelValue[3]);
                            lckLevelUpResultFourLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndFiveLabel.setText(statLevelUpResultRndNumLabelValue[4]);
                            lckLevelUpResultFiveLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndSixLabel.setText(statLevelUpResultRndNumLabelValue[5]);
                            lckLevelUpResultSixLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndSevenLabel.setText(statLevelUpResultRndNumLabelValue[6]);
                            lckLevelUpResultSevenLabel.setText(statLevelUpResultLabelsDefaultValue);
                            lckLevelUpResultRndEightLabel.setText(statLevelUpResultRndNumLabelValue[7]);
                            lckLevelUpResultEightLabel.setText(statLevelUpResultLabelsDefaultValue);

                            // Reset Application Objects
                            characters = createCharacters(); // {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"}
                            selectedCharactersIndex = 0;
                            armors = new Armors();
                            accessories = new Accessories();
                            materia = new Materia();

                            // Switch used to set models for dropdown boxes first time
                            charSelModelsSet = false;
                        }
                    }
                }
        );
    }

    public JButton createResetButton(String defaultValue){
        JButton button = new JButton(defaultValue);
        Font font = button.getFont();

        button.setHorizontalAlignment(JLabel.CENTER);
        button.setFont(font.deriveFont(Font.BOLD));
        return button;
    }

    //**************************************************************************************
    // Top Panel Methods
    public BackgroundPanel createBackgroundFrame(String imgLocName){
        Image backgroundImg = null;
        try {
            backgroundImg = ImageIO.read(getClass().getResource(imgLocName));
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        BackgroundPanel bgPanel = new BackgroundPanel(backgroundImg, BackgroundPanel.SCALED, 0.0f, 0.0f);
        return bgPanel;
    }

    public JPanel[] createMateriaWpnArmPanels(JComboBox[] materiaCombos){
        JPanel[] pnl = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};

        //*************************************************** Grid ***************************************************
        for(int pnlIndex = 0; pnlIndex < pnl.length; pnlIndex++){
            pnl[pnlIndex].setLayout(new GridLayout(1, 1));
            pnl[pnlIndex].add(materiaCombos[pnlIndex]);
            pnl[pnlIndex].setOpaque(false);
        }
        //************************************************************************************************************

        return pnl;
    }

    public JPanel createTopFrame(){
        JPanel pnl = new JPanel();

        //****************************************** Vertical Line Splitters *****************************************
        Border lineSplitterBoarder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(224,224,224));
        //============================================================================================================
        JLabel lineSplitterOne = new JLabel();
        lineSplitterOne.setBorder(lineSplitterBoarder);
        //============================================================================================================
        JLabel lineSplitterTwo = new JLabel();
        lineSplitterTwo.setBorder(lineSplitterBoarder);
        //============================================================================================================
        JLabel lineSplitterThree = new JLabel();
        lineSplitterThree.setBorder(lineSplitterBoarder);
        //************************************************************************************************************

        //*************************************************** Grid ***************************************************
        pnl.setLayout(new GridBagLayout());
        int column;
        int row;
        /* variable not used
         * int maxColumnsFill = 15; // 0-15
         */
        int maxRowsFill = 4; // 0-4
        int columnsFill;
        int rowsFill;
        int objectExtraWidth;
        int objectExtraHeight;
        String objectAnchorLoc;
        int columnWeight;
        int rowWeight;
        int cellTopInset;
        int cellLeftInset;
        int cellBottomInset;
        int cellRightInset;
        boolean objectResizeToCellMinusInsets;

        //============================================================================================================
        column = 0;
        row = 0;
        columnsFill = 1;
        rowsFill = maxRowsFill;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 10;
        cellLeftInset = 10;
        cellBottomInset = 10;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(charImgLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        setCharSelComboAction();
        column = 1;
        row = 0;
        columnsFill = 2;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 10;
        cellLeftInset = 10;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(charSelCombo, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 10;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(currLvlLabel, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        setCurrLvlAnsComboAction();
        column = 2;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 10;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(currLvlAnsCombo, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 66;
        cellBottomInset = 10;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(nextLvlLabel, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 9;
        objectExtraHeight = 5;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 10;
        cellBottomInset = 10;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(nextLvlAnsLabel, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //-------------------------------------------- Vertical Line Split -------------------------------------------
        column = 3;
        row = 0;
        columnsFill = 1;
        rowsFill = maxRowsFill;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = true;
        pnl.add(lineSplitterOne, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 4;
        row = 0;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 10;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(equipLabel, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        setWeaponComboAction();
        column = 4;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(weaponCombo, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        setArmorComboAction();
        column = 4;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(armorCombo, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        setAccessoryComboAction();
        column = 4;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "NORTHWEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 10;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(accessoryCombo, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //-------------------------------------------- Vertical Line Split -------------------------------------------
        column = 5;
        row = 0;
        columnsFill = 1;
        rowsFill = maxRowsFill;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = true;
        pnl.add(lineSplitterTwo, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 6;
        row = 0;
        columnsFill = 8;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 10;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        column++;
        pnl.add(materiaLabel, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 6;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setMateriaWpnComboOneAction();
        setMateriaWpnComboTwoAction();
        setMateriaWpnComboThreeAction();
        setMateriaWpnComboFourAction();
        setMateriaWpnComboFiveAction();
        setMateriaWpnComboSixAction();
        setMateriaWpnComboSevenAction();
        setMateriaWpnComboEightAction();
        for (int i = 0, tempColumn = column; i < materiaWpnCombos.length; i++, tempColumn++){
            pnl.add(materiaWpnPanels[i], setGbc(tempColumn,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                    objectAnchorLoc, columnWeight, rowWeight,
                    setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                    objectResizeToCellMinusInsets)
            );
        }
        //============================================================================================================
        column = 6;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setMateriaArmComboOneAction();
        setMateriaArmComboTwoAction();
        setMateriaArmComboThreeAction();
        setMateriaArmComboFourAction();
        setMateriaArmComboFiveAction();
        setMateriaArmComboSixAction();
        setMateriaArmComboSevenAction();
        setMateriaArmComboEightAction();
        for (int i = 0, tempColumn = column; i < materiaArmCombos.length; i++, tempColumn++){
            pnl.add(materiaArmPanels[i], setGbc(tempColumn,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                    objectAnchorLoc, columnWeight, rowWeight,
                    setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                    objectResizeToCellMinusInsets)
            );
        }
        //============================================================================================================
        //-------------------------------------------- Vertical Line Split -------------------------------------------
        column = 14;
        row = 0;
        columnsFill = 1;
        rowsFill = maxRowsFill;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 5;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = true;
        pnl.add(lineSplitterThree, setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 15;
        row = 0;
        columnsFill = 1;
        rowsFill = maxRowsFill;
        objectExtraWidth = 50;
        objectExtraHeight = 0;
        objectAnchorLoc = "WEST";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 10;
        cellLeftInset = 10;
        cellBottomInset = 10;
        cellRightInset = 10;
        objectResizeToCellMinusInsets = true;
        setResetButtonAction();
        pnl.add(resetButton,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================

        pnl.setOpaque(false);
        return pnl;
    }

    public JScrollPane createTopScrollPane(){
        JScrollPane scrollPane = new JScrollPane(backgroundFrame) {
            @Override
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                if (getHorizontalScrollBar() != null && getHorizontalScrollBar().isVisible()) {
                    size.height += getHorizontalScrollBar().getHeight();
                }
                return size;
            }
        };
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border lineBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(224,224,224));
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compoundSetup = BorderFactory.createCompoundBorder(raisedBevel, lineBorder);
        Border compoundFinal = BorderFactory.createCompoundBorder(compoundSetup, loweredBevel);

        //scrollPane.setPreferredSize(new Dimension(0, 150));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(compoundFinal);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getViewport().setView(topFrame);
        return scrollPane;
    }

    //**************************************************************************************
    //******************************** Center Panel Methods ********************************
    //**************************************************************************************
    // Center Panel Non-Interactive GUI Object Methods
    public void updateAllStatGridRowLabelValues(){
        setHpStatGridRowValues();
        setMpStatGridRowValues();
        setStrStatGridRowValues();
        setDexStatGridRowValues();
        setVitStatGridRowValues();
        setMagStatGridRowValues();
        setSprStatGridRowValues();
        setLckStatGridRowValues();
    }

    public void setHpStatGridRowValues(){
        hpStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getHp()));

        int staticHpIncrease = Calculator.getHpGradient(Calculator.getCharacterNameIndex(characters[getSelectedCharactersIndex()].getCharacterName()),
                Calculator.getLevelUpBracketIndex((characters[getSelectedCharactersIndex()].getLevel() + 1)));
        hpIncreasedLabel.setText(Integer.toString(staticHpIncrease));

        double[] hpIncreaseRates = Calculator.calculateHpLevelUpRates(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                characters[getSelectedCharactersIndex()].getBaseHp());
        hpLevelUpResultRateRndOneLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[0]));
        hpLevelUpResultRateRndTwoLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[1]));
        hpLevelUpResultRateRndThreeLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[2]));
        hpLevelUpResultRateRndFourLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[3]));
        hpLevelUpResultRateRndFiveLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[4]));
        hpLevelUpResultRateRndSixLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[5]));
        hpLevelUpResultRateRndSevenLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[6]));
        hpLevelUpResultRateRndEightLabel.setText(MessageFormat.format("{0,number,#.##%}", hpIncreaseRates[7]));

        if (characters[getSelectedCharactersIndex()].isSwitchHpMpMateriaEquiped()){
            int[] hpIncreaseResults = Calculator.calculateHpLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                    (characters[getSelectedCharactersIndex()].getLevel() + 1), characters[getSelectedCharactersIndex()].getBaseHp());
            int[] mpIncreaseResults = Calculator.calculateMpLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                    (characters[getSelectedCharactersIndex()].getLevel() + 1), characters[getSelectedCharactersIndex()].getBaseMp());
            hpLevelUpResultRndOneLabel.setText(hpIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[0]) + ")");
            hpLevelUpResultRndTwoLabel.setText(hpIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[1]) + ")");
            hpLevelUpResultRndThreeLabel.setText(hpIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[2]) + ")");
            hpLevelUpResultRndFourLabel.setText(hpIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[3]) + ")");
            hpLevelUpResultRndFiveLabel.setText(hpIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[4]) + ")");
            hpLevelUpResultRndSixLabel.setText(hpIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[5]) + ")");
            hpLevelUpResultRndSevenLabel.setText(hpIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[6]) + ")");
            hpLevelUpResultRndEightLabel.setText(hpIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[7]) + ")");
        } else {
            int[] hpIncreaseResults = Calculator.calculateHpLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                    (characters[getSelectedCharactersIndex()].getLevel() + 1), characters[getSelectedCharactersIndex()].getBaseHp());
            hpLevelUpResultRndOneLabel.setText(hpIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[0]) + ")");
            hpLevelUpResultRndTwoLabel.setText(hpIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[1]) + ")");
            hpLevelUpResultRndThreeLabel.setText(hpIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[2]) + ")");
            hpLevelUpResultRndFourLabel.setText(hpIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[3]) + ")");
            hpLevelUpResultRndFiveLabel.setText(hpIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[4]) + ")");
            hpLevelUpResultRndSixLabel.setText(hpIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[5]) + ")");
            hpLevelUpResultRndSevenLabel.setText(hpIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[6]) + ")");
            hpLevelUpResultRndEightLabel.setText(hpIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[7]) + ")");
        }
    }
    public void setMpStatGridRowValues(){
        mpStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getMp()));
        int staticMpIncrease = Calculator.getMpBaseGain(characters[getSelectedCharactersIndex()].getLevel() + 1,
                Calculator.getMpGradient(Calculator.getCharacterNameIndex(characters[getSelectedCharactersIndex()].getCharacterName()),
                        Calculator.getLevelUpBracketIndex((characters[getSelectedCharactersIndex()].getLevel() + 1)))) ;
        mpIncreasedLabel.setText(Integer.toString(staticMpIncrease));

        double[] mpIncreaseRates = Calculator.calculateMpLevelUpRates(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                characters[getSelectedCharactersIndex()].getBaseMp());
        mpLevelUpResultRateRndOneLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[0]));
        mpLevelUpResultRateRndTwoLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[1]));
        mpLevelUpResultRateRndThreeLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[2]));
        mpLevelUpResultRateRndFourLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[3]));
        mpLevelUpResultRateRndFiveLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[4]));
        mpLevelUpResultRateRndSixLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[5]));
        mpLevelUpResultRateRndSevenLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[6]));
        mpLevelUpResultRateRndEightLabel.setText(MessageFormat.format("{0,number,#.##%}", mpIncreaseRates[7]));

        if (characters[getSelectedCharactersIndex()].isSwitchHpMpMateriaEquiped()){
            int[] mpIncreaseResults = Calculator.calculateMpLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                    (characters[getSelectedCharactersIndex()].getLevel() + 1), characters[getSelectedCharactersIndex()].getBaseMp());
            int[] hpIncreaseResults = Calculator.calculateHpLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                    (characters[getSelectedCharactersIndex()].getLevel() + 1), characters[getSelectedCharactersIndex()].getBaseHp());
            mpLevelUpResultRndOneLabel.setText(mpIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[0]) + ")");
            mpLevelUpResultRndTwoLabel.setText(mpIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[1]) + ")");
            mpLevelUpResultRndThreeLabel.setText(mpIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[2]) + ")");
            mpLevelUpResultRndFourLabel.setText(mpIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[3]) + ")");
            mpLevelUpResultRndFiveLabel.setText(mpIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[4]) + ")");
            mpLevelUpResultRndSixLabel.setText(mpIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[5]) + ")");
            mpLevelUpResultRndSevenLabel.setText(mpIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[6]) + ")");
            mpLevelUpResultRndEightLabel.setText(mpIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseHpNumWithGear(hpIncreaseResults[7]) + ")");
        } else {
            int[] mpIncreaseResults = Calculator.calculateMpLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                    (characters[getSelectedCharactersIndex()].getLevel() + 1), characters[getSelectedCharactersIndex()].getBaseMp());
            mpLevelUpResultRndOneLabel.setText(mpIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[0]) + ")");
            mpLevelUpResultRndTwoLabel.setText(mpIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[1]) + ")");
            mpLevelUpResultRndThreeLabel.setText(mpIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[2]) + ")");
            mpLevelUpResultRndFourLabel.setText(mpIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[3]) + ")");
            mpLevelUpResultRndFiveLabel.setText(mpIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[4]) + ")");
            mpLevelUpResultRndSixLabel.setText(mpIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[5]) + ")");
            mpLevelUpResultRndSevenLabel.setText(mpIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[6]) + ")");
            mpLevelUpResultRndEightLabel.setText(mpIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMpNumWithGear(mpIncreaseResults[7]) + ")");
        }

    }
    public void setStrStatGridRowValues(){
        strStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getStrength()));
        String staticStrIncreaseOnGui;
        int[] staticStrIncrease = Calculator.getPrimaryStatIncreaseAmounts(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Str", characters[getSelectedCharactersIndex()].getBaseStrength());
        if (staticStrIncrease[0] != staticStrIncrease[staticStrIncrease.length - 1]){
            staticStrIncreaseOnGui = staticStrIncrease[0] + " - " + staticStrIncrease[staticStrIncrease.length - 1];
        } else {
            staticStrIncreaseOnGui = Integer.toString(staticStrIncrease[0]);
        }
        strIncreasedLabel.setText(staticStrIncreaseOnGui);

        int[] strIncreaseResults = Calculator.calculatePrimaryStatLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Str", characters[getSelectedCharactersIndex()].getBaseStrength());
        strLevelUpResultOneLabel.setText(strIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[0]) + ")");
        strLevelUpResultTwoLabel.setText(strIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[1]) + ")");
        strLevelUpResultThreeLabel.setText(strIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[2]) + ")");
        strLevelUpResultFourLabel.setText(strIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[3]) + ")");
        strLevelUpResultFiveLabel.setText(strIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[4]) + ")");
        strLevelUpResultSixLabel.setText(strIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[5]) + ")");
        strLevelUpResultSevenLabel.setText(strIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[6]) + ")");
        strLevelUpResultEightLabel.setText(strIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseStrNumWithGear(strIncreaseResults[7]) + ")");
    }
    public void setDexStatGridRowValues(){
        dexStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getDexterity()));
        String staticDexIncreaseOnGui;
        int[] staticDexIncrease = Calculator.getPrimaryStatIncreaseAmounts(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Dex", characters[getSelectedCharactersIndex()].getBaseDexterity());
        if (staticDexIncrease[0] != staticDexIncrease[staticDexIncrease.length - 1]){
            staticDexIncreaseOnGui = staticDexIncrease[0] + " - " + staticDexIncrease[staticDexIncrease.length - 1];
        } else {
            staticDexIncreaseOnGui = Integer.toString(staticDexIncrease[0]);
        }
        dexIncreasedLabel.setText(staticDexIncreaseOnGui);

        int[] dexIncreaseResults = Calculator.calculatePrimaryStatLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Dex", characters[getSelectedCharactersIndex()].getBaseDexterity());
        dexLevelUpResultOneLabel.setText(dexIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[0]) + ")");
        dexLevelUpResultTwoLabel.setText(dexIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[1]) + ")");
        dexLevelUpResultThreeLabel.setText(dexIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[2]) + ")");
        dexLevelUpResultFourLabel.setText(dexIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[3]) + ")");
        dexLevelUpResultFiveLabel.setText(dexIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[4]) + ")");
        dexLevelUpResultSixLabel.setText(dexIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[5]) + ")");
        dexLevelUpResultSevenLabel.setText(dexIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[6]) + ")");
        dexLevelUpResultEightLabel.setText(dexIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseDexNumWithGear(dexIncreaseResults[7]) + ")");
    }
    public void setVitStatGridRowValues(){
        vitStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getVitality()));
        String staticVitIncreaseOnGui;
        int[] staticVitIncrease = Calculator.getPrimaryStatIncreaseAmounts(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Vit", characters[getSelectedCharactersIndex()].getBaseVitality());
        if (staticVitIncrease[0] != staticVitIncrease[staticVitIncrease.length - 1]){
            staticVitIncreaseOnGui = staticVitIncrease[0] + " - " + staticVitIncrease[staticVitIncrease.length - 1];
        } else {
            staticVitIncreaseOnGui = Integer.toString(staticVitIncrease[0]);
        }
        vitIncreasedLabel.setText(staticVitIncreaseOnGui);

        int[] vitIncreaseResults = Calculator.calculatePrimaryStatLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Vit", characters[getSelectedCharactersIndex()].getBaseVitality());
        vitLevelUpResultOneLabel.setText(vitIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[0]) + ")");
        vitLevelUpResultTwoLabel.setText(vitIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[1]) + ")");
        vitLevelUpResultThreeLabel.setText(vitIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[2]) + ")");
        vitLevelUpResultFourLabel.setText(vitIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[3]) + ")");
        vitLevelUpResultFiveLabel.setText(vitIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[4]) + ")");
        vitLevelUpResultSixLabel.setText(vitIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[5]) + ")");
        vitLevelUpResultSevenLabel.setText(vitIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[6]) + ")");
        vitLevelUpResultEightLabel.setText(vitIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseVitNumWithGear(vitIncreaseResults[7]) + ")");
    }
    public void setMagStatGridRowValues(){
        magStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getMagic()));
        String staticMagIncreaseOnGui;
        int[] staticMagIncrease = Calculator.getPrimaryStatIncreaseAmounts(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Mag", characters[getSelectedCharactersIndex()].getBaseMagic());
        if (staticMagIncrease[0] != staticMagIncrease[staticMagIncrease.length - 1]){
            staticMagIncreaseOnGui = staticMagIncrease[0] + " - " + staticMagIncrease[staticMagIncrease.length - 1];
        } else {
            staticMagIncreaseOnGui = Integer.toString(staticMagIncrease[0]);
        }
        magIncreasedLabel.setText(staticMagIncreaseOnGui);

        int[] magIncreaseResults = Calculator.calculatePrimaryStatLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Mag", characters[getSelectedCharactersIndex()].getBaseMagic());
        magLevelUpResultOneLabel.setText(magIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[0]) + ")");
        magLevelUpResultTwoLabel.setText(magIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[1]) + ")");
        magLevelUpResultThreeLabel.setText(magIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[2]) + ")");
        magLevelUpResultFourLabel.setText(magIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[3]) + ")");
        magLevelUpResultFiveLabel.setText(magIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[4]) + ")");
        magLevelUpResultSixLabel.setText(magIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[5]) + ")");
        magLevelUpResultSevenLabel.setText(magIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[6]) + ")");
        magLevelUpResultEightLabel.setText(magIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseMagNumWithGear(magIncreaseResults[7]) + ")");
    }
    public void setSprStatGridRowValues(){
        sprStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getSpirit()));
        String staticSprIncreaseOnGui;
        int[] staticSprIncrease = Calculator.getPrimaryStatIncreaseAmounts(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Spr", characters[getSelectedCharactersIndex()].getBaseSpirit());
        if (staticSprIncrease[0] != staticSprIncrease[staticSprIncrease.length - 1]){
            staticSprIncreaseOnGui = staticSprIncrease[0] + " - " + staticSprIncrease[staticSprIncrease.length - 1];
        } else {
            staticSprIncreaseOnGui = Integer.toString(staticSprIncrease[0]);
        }
        sprIncreasedLabel.setText(staticSprIncreaseOnGui);

        int[] sprIncreaseResults = Calculator.calculatePrimaryStatLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                "Spr", characters[getSelectedCharactersIndex()].getBaseSpirit());
        sprLevelUpResultOneLabel.setText(sprIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[0]) + ")");
        sprLevelUpResultTwoLabel.setText(sprIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[1]) + ")");
        sprLevelUpResultThreeLabel.setText(sprIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[2]) + ")");
        sprLevelUpResultFourLabel.setText(sprIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[3]) + ")");
        sprLevelUpResultFiveLabel.setText(sprIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[4]) + ")");
        sprLevelUpResultSixLabel.setText(sprIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[5]) + ")");
        sprLevelUpResultSevenLabel.setText(sprIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[6]) + ")");
        sprLevelUpResultEightLabel.setText(sprIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseSprNumWithGear(sprIncreaseResults[7]) + ")");
    }
    public void setLckStatGridRowValues(){
        lckStatsWithGearLabel.setText(Integer.toString(characters[getSelectedCharactersIndex()].getLuck()));
        String staticLckIncreaseOnGui;
        int[] staticLckIncrease = Calculator.getLuckStatIncreaseAmounts(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                characters[getSelectedCharactersIndex()].getBaseLuck());
        if (staticLckIncrease[0] != staticLckIncrease[staticLckIncrease.length - 1]){
            staticLckIncreaseOnGui = staticLckIncrease[0] + " - " + staticLckIncrease[staticLckIncrease.length - 1];
        } else {
            staticLckIncreaseOnGui = Integer.toString(staticLckIncrease[0]);
        }
        lckIncreasedLabel.setText(staticLckIncreaseOnGui);

        int[] lckIncreaseResults = Calculator.calculateLuckStatLevelUpResults(characters[getSelectedCharactersIndex()].getCharacterName(),
                (characters[getSelectedCharactersIndex()].getLevel() + 1),
                characters[getSelectedCharactersIndex()].getBaseLuck());
        lckLevelUpResultOneLabel.setText(lckIncreaseResults[0] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[0]) + ")");
        lckLevelUpResultTwoLabel.setText(lckIncreaseResults[1] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[1]) + ")");
        lckLevelUpResultThreeLabel.setText(lckIncreaseResults[2] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[2]) + ")");
        lckLevelUpResultFourLabel.setText(lckIncreaseResults[3] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[3]) + ")");
        lckLevelUpResultFiveLabel.setText(lckIncreaseResults[4] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[4]) + ")");
        lckLevelUpResultSixLabel.setText(lckIncreaseResults[5] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[5]) + ")");
        lckLevelUpResultSevenLabel.setText(lckIncreaseResults[6] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[6]) + ")");
        lckLevelUpResultEightLabel.setText(lckIncreaseResults[7] + " (" + characters[getSelectedCharactersIndex()].calculateBaseLckNumWithGear(lckIncreaseResults[7]) + ")");
    }

    //**************************************************************************************
    // Center Panel Interactive GUI Object Methods
    // updateStatGridLabels();
    public void setHpBaseStatsTextFieldAction(){
        ((AbstractDocument) hpBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxBaseHp()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxBaseHp();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseHpInGui = hpBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseHpInGui)) || baseHpInGui.equals(""))){
                            if (baseHpInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseHp(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseHp(Integer.parseInt(baseHpInGui));
                            }
                            setHpStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setMpBaseStatsTextFieldAction(){
        ((AbstractDocument) mpBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxBaseMp()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxBaseMp();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseMpInGui = mpBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseMpInGui)) || baseMpInGui.equals(""))){
                            if (baseMpInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseMp(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseMp(Integer.parseInt(baseMpInGui));
                            }
                            setMpStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setStrBaseStatsTextFieldAction(){
        ((AbstractDocument) strBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxPrimaryStat()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxPrimaryStat();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseStrInGui = strBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseStrInGui)) || baseStrInGui.equals(""))){
                            if (baseStrInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseStrength(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseStrength(Integer.parseInt(baseStrInGui));
                            }
                            setStrStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setDexBaseStatsTextFieldAction(){
        ((AbstractDocument) dexBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxPrimaryStat()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxPrimaryStat();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseDexInGui = dexBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseDexInGui)) || baseDexInGui.equals(""))){
                            if (baseDexInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseDexterity(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseDexterity(Integer.parseInt(baseDexInGui));
                            }
                            setDexStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setVitBaseStatsTextFieldAction(){
        ((AbstractDocument) vitBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxPrimaryStat()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxPrimaryStat();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseVitInGui = vitBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseVitInGui)) || baseVitInGui.equals(""))){
                            if (baseVitInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseVitality(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseVitality(Integer.parseInt(baseVitInGui));
                            }
                            setVitStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setMagBaseStatsTextFieldAction(){
        ((AbstractDocument) magBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxPrimaryStat()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxPrimaryStat();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseMagInGui = magBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseMagInGui)) || baseMagInGui.equals(""))){
                            if (baseMagInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseMagic(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseMagic(Integer.parseInt(baseMagInGui));
                            }
                            setMagStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setSprBaseStatsTextFieldAction(){
        ((AbstractDocument) sprBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxPrimaryStat()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxPrimaryStat();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseSprInGui = sprBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseSprInGui)) || baseSprInGui.equals(""))){
                            if (baseSprInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseSpirit(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseSpirit(Integer.parseInt(baseSprInGui));
                            }
                            setSprStatGridRowValues();
                        }
                    }
                }
        );
    }
    public void setLckBaseStatsTextFieldAction(){
        ((AbstractDocument) lckBaseStatsTextField.getDocument()).setDocumentFilter(
                new DocumentFilter() {
                    public void replace (FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String textAfterReplacement = new StringBuilder (fb.getDocument ().getText (0, fb.getDocument ().getLength ())).replace (offset, offset + length, text).toString ();
                        if ((text.matches("[0-9]+")) || text.equals("")) {
                            if (isInteger(text)) {
                                try {
                                    int value = Integer.parseInt(textAfterReplacement);
                                    if (value < 0) {
                                        value = 0;
                                    } else if (value > characters[getSelectedCharactersIndex()].getMaxPrimaryStat()) {
                                        value = characters[getSelectedCharactersIndex()].getMaxPrimaryStat();
                                    }
                                    super.replace(fb, 0, fb.getDocument().getLength(), String.valueOf(value), attrs);
                                    updateStatGridLabels();
                                } catch (NumberFormatException e) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                             String text, AttributeSet attr) throws BadLocationException {
                        if ((text.matches("[0-9]+")) || text.equals("")){
                            if (isInteger(text)){
                                fb.insertString(offset, text, attr);
                                updateStatGridLabels();
                            }
                        } else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                    public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
                            throws BadLocationException {
                        super.remove(fb, offset, length);
                        updateStatGridLabels();
                    }

                    public void updateStatGridLabels() {
                        String currCharacter = charSelCombo.getSelectedItem().toString();
                        String baseLckInGui = lckBaseStatsTextField.getText();
                        if ((!(currCharacter.equals(guiCharSelDefaultValue[unselectedDefaultElement]))) && ((isInteger(baseLckInGui)) || baseLckInGui.equals(""))){
                            if (baseLckInGui.equals("")){
                                characters[getSelectedCharactersIndex()].setBaseLuck(0);
                            } else {
                                characters[getSelectedCharactersIndex()].setBaseLuck(Integer.parseInt(baseLckInGui));
                            }
                            setLckStatGridRowValues();
                        }
                    }
                }
        );
    }

    //**************************************************************************************
    // Panel object methods
    public JPanel createCenterFrame(){
        JPanel pnl = new JPanel();

        //************************************************** Borders *************************************************
        int borderLineSize = 4;
        // BorderFactory.createMatteBorder(topSizeLine,leftSizeLine,bottomSizeLine,rightSizeLine,Color)

        Border testLabelBorder = BorderFactory.createMatteBorder(borderLineSize,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        //==============================================================================================================
        Border statsAndIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(borderLineSize,borderLineSize,borderLineSize,0,Color.BLACK);
        Border hpIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border hpLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border mpIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border mpLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border strIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border strLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border dexIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border dexLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border vitIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border vitLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border magIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border magLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border sprIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border sprLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        Border lckIncreaseRatesLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,0,borderLineSize,Color.BLACK);
        Border lckLabelBorder = BorderFactory.createMatteBorder(0,borderLineSize,borderLineSize,borderLineSize,Color.BLACK);
        //==============================================================================================================
        Border baseStatsLabelBorder = BorderFactory.createMatteBorder(borderLineSize,0,borderLineSize,0,Color.BLACK);
        Border hpBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border mpBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border strBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border dexBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border vitBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border magBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border sprBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border lckBaseStatsTextFieldBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        //==============================================================================================================
        Border statsWithGearLabelBorder = BorderFactory.createMatteBorder(borderLineSize,0,borderLineSize,0,Color.BLACK);
        Border hpStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border mpStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border strStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border dexStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border vitStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border magStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border sprStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border lckStatsWithGearLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        //==============================================================================================================
        Border increasedStatsLabelBorder = BorderFactory.createMatteBorder(borderLineSize,0,borderLineSize,0,Color.BLACK);
        Border statIncreasedEmptyLabelBorder = BorderFactory.createMatteBorder(0, 0, borderLineSize, 0, Color.BLACK);

        //==============================================================================================================
        Border StatsAfterLevelUpWithGearLabelBorder = BorderFactory.createMatteBorder(borderLineSize,0,borderLineSize,borderLineSize,Color.BLACK);
        Border levelUpResultLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,0,Color.BLACK);
        Border levelUpLastResultRateLabelBorder = BorderFactory.createMatteBorder(0,0,0,borderLineSize,Color.BLACK);
        Border levelUpLastResultLabelBorder = BorderFactory.createMatteBorder(0,0,borderLineSize,borderLineSize,Color.BLACK);
        //levelUpLastResultLabelBorder
        //**************************************************************************************************************


        //*************************************************** Grid ***************************************************
        pnl.setLayout(new GridBagLayout());
        int column;
        int row;
        /* variable not used
         * int maxColumnsFill = 12; // 0-12
         * int maxRowsFill = 11; // 0-11
        */
        int columnsFill;
        int rowsFill;
        int objectExtraWidth;
        int objectExtraHeight;
        String objectAnchorLoc;
        int columnWeight;
        int rowWeight;
        int cellTopInset;
        int cellLeftInset;
        int cellBottomInset;
        int cellRightInset;
        boolean objectResizeToCellMinusInsets;

        //============================================================================================================
        column = 0;
        row = 0;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        statsAndIncreaseRatesLabel.setBorder(statsAndIncreaseRatesLabelBorder);
        pnl.add(statsAndIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );

        //============================================================================================================
        column = 0;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpIncreaseRatesLabel.setBorder(hpIncreaseRatesLabelBorder);
        pnl.add(hpIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLabel.setBorder(hpLabelBorder);
        pnl.add(hpLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpIncreaseRatesLabel.setBorder(mpIncreaseRatesLabelBorder);
        pnl.add(mpIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLabel.setBorder(mpLabelBorder);
        pnl.add(mpLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strIncreaseRatesLabel.setBorder(strIncreaseRatesLabelBorder);
        pnl.add(strIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLabel.setBorder(strLabelBorder);
        pnl.add(strLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexIncreaseRatesLabel.setBorder(dexIncreaseRatesLabelBorder);
        pnl.add(dexIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLabel.setBorder(dexLabelBorder);
        pnl.add(dexLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitIncreaseRatesLabel.setBorder(vitIncreaseRatesLabelBorder);
        pnl.add(vitIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLabel.setBorder(vitLabelBorder);
        pnl.add(vitLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magIncreaseRatesLabel.setBorder(magIncreaseRatesLabelBorder);
        pnl.add(magIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLabel.setBorder(magLabelBorder);
        pnl.add(magLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprIncreaseRatesLabel.setBorder(sprIncreaseRatesLabelBorder);
        pnl.add(sprIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLabel.setBorder(sprLabelBorder);
        pnl.add(sprLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckIncreaseRatesLabel.setBorder(lckIncreaseRatesLabelBorder);
        pnl.add(lckIncreaseRatesLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 0;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLabel.setBorder(lckLabelBorder);
        pnl.add(lckLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 0;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        baseStatsLabel.setBorder(baseStatsLabelBorder);
        pnl.add(baseStatsLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setHpBaseStatsTextFieldAction();
        hpBaseStatsTextField.setBorder(hpBaseStatsTextFieldBorder);
        pnl.add(hpBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setMpBaseStatsTextFieldAction();
        mpBaseStatsTextField.setBorder(mpBaseStatsTextFieldBorder);
        pnl.add(mpBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setStrBaseStatsTextFieldAction();
        strBaseStatsTextField.setBorder(strBaseStatsTextFieldBorder);
        pnl.add(strBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setDexBaseStatsTextFieldAction();
        dexBaseStatsTextField.setBorder(dexBaseStatsTextFieldBorder);
        pnl.add(dexBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setVitBaseStatsTextFieldAction();
        vitBaseStatsTextField.setBorder(vitBaseStatsTextFieldBorder);
        pnl.add(vitBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setMagBaseStatsTextFieldAction();
        magBaseStatsTextField.setBorder(magBaseStatsTextFieldBorder);
        pnl.add(magBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setSprBaseStatsTextFieldAction();
        sprBaseStatsTextField.setBorder(sprBaseStatsTextFieldBorder);
        pnl.add(sprBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 1;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        setLckBaseStatsTextFieldAction();
        lckBaseStatsTextField.setBorder(lckBaseStatsTextFieldBorder);
        pnl.add(lckBaseStatsTextField,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 0;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        statsWithGearLabel.setBorder(statsWithGearLabelBorder);
        pnl.add(statsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpStatsWithGearLabel.setBorder(hpStatsWithGearLabelBorder);
        pnl.add(hpStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpStatsWithGearLabel.setBorder(mpStatsWithGearLabelBorder);
        pnl.add(mpStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strStatsWithGearLabel.setBorder(strStatsWithGearLabelBorder);
        pnl.add(strStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexStatsWithGearLabel.setBorder(dexStatsWithGearLabelBorder);
        pnl.add(dexStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitStatsWithGearLabel.setBorder(vitStatsWithGearLabelBorder);
        pnl.add(vitStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magStatsWithGearLabel.setBorder(magStatsWithGearLabelBorder);
        pnl.add(magStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprStatsWithGearLabel.setBorder(sprStatsWithGearLabelBorder);
        pnl.add(sprStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 2;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckStatsWithGearLabel.setBorder(lckStatsWithGearLabelBorder);
        pnl.add(lckStatsWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 0;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        increasedStatsLabel.setBorder(increasedStatsLabelBorder);
        pnl.add(increasedStatsLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(hpIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(mpIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(strIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(dexIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(vitIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(magIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(sprIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckIncreasedLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 3;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckIncreasedEmptyLabel.setBorder(statIncreasedEmptyLabelBorder);
        pnl.add(lckIncreasedEmptyLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        column = 4;
        row = 0;
        columnsFill = 8;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        StatsAfterLevelUpWithGearLabel.setBorder(StatsAfterLevelUpWithGearLabelBorder);
        pnl.add(StatsAfterLevelUpWithGearLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 4;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultOneLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultOneLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 5;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultTwoLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultTwoLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 6;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultThreeLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultThreeLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 7;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultFourLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultFourLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 8;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultFiveLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultFiveLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 9;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultSixLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultSixLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(hpLevelUpResultRateRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(hpLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(mpLevelUpResultRateRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(mpLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(strLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(strLevelUpResultSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(dexLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(dexLevelUpResultSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(vitLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(vitLevelUpResultSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(magLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(magLevelUpResultSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(sprLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(sprLevelUpResultSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        pnl.add(lckLevelUpResultRndSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 10;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultSevenLabel.setBorder(levelUpResultLabelBorder);
        pnl.add(lckLevelUpResultSevenLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 1;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRateRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(hpLevelUpResultRateRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 2;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        hpLevelUpResultRndEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(hpLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 3;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRateRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(mpLevelUpResultRateRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 4;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        mpLevelUpResultRndEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(mpLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 5;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(strLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 6;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        strLevelUpResultEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(strLevelUpResultEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 7;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(dexLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 8;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        dexLevelUpResultEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(dexLevelUpResultEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 9;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(vitLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 10;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        vitLevelUpResultEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(vitLevelUpResultEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 11;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(magLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 12;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        magLevelUpResultEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(magLevelUpResultEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 13;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(sprLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 14;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        sprLevelUpResultEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(sprLevelUpResultEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 15;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultRndEightLabel.setBorder(levelUpLastResultRateLabelBorder);
        pnl.add(lckLevelUpResultRndEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //------------------------------------------------------------------------------------------------------------
        column = 11;
        row = 16;
        columnsFill = 1;
        rowsFill = 1;
        objectExtraWidth = 0;
        objectExtraHeight = 0;
        objectAnchorLoc = "CENTER";
        columnWeight = 0;
        rowWeight = 0;
        cellTopInset = 0;
        cellLeftInset = 0;
        cellBottomInset = 0;
        cellRightInset = 0;
        objectResizeToCellMinusInsets = false;
        lckLevelUpResultEightLabel.setBorder(levelUpLastResultLabelBorder);
        pnl.add(lckLevelUpResultEightLabel,setGbc(column,row, columnsFill,rowsFill, objectExtraWidth,objectExtraHeight,
                objectAnchorLoc, columnWeight, rowWeight,
                setInsets(cellTopInset, cellLeftInset, cellBottomInset, cellRightInset),
                objectResizeToCellMinusInsets)
        );
        //============================================================================================================
        //************************************************************************************************************

        // strLevelUpResultRndOneLabel
        pnl.setOpaque(false);
        return pnl;
    }

    public JScrollPane createCenterScrollPane(){
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


