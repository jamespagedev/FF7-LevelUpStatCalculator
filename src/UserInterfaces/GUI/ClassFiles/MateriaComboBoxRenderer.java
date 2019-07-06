/**
 * Created by James Page on 4/26/2017.
 */
package ClassFiles;

import javax.swing.*;
import java.awt.*;

class MateriaComboBoxRenderer extends JLabel implements ListCellRenderer
{
    public Color selectionBackgroundColor;
    public Color selectionForegroundColor;
    public Color defaultBackgroundColor;
    public Materia materia;

    // Constructor
    public MateriaComboBoxRenderer()
    {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        defaultBackgroundColor = new DefaultListCellRenderer().getBackground(); // Have to set a color, else a compiler error will occur
        selectionForegroundColor = Color.BLACK;
        this.materia = new Materia();
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        // Set the list background color to default color (default color will show once selection is made)
        selectionBackgroundColor = null;
        selectionForegroundColor = null;
        Color mouseHoverHighlight = Color.LIGHT_GRAY;
        setText((String)value);
        // Check which item is selected
        if(isSelected)
        {
            // Set background color of the item your cursor is hovering over to the original background color
            list.setSelectionForeground(Color.BLACK);
            String selectedTextInDropdownList = getText();
            if (java.util.Arrays.asList(materia.getGreenMateriaList()).contains(selectedTextInDropdownList)){
                list.setSelectionBackground(Color.GREEN);
                list.setSelectionForeground(Color.BLACK);
            } else if (java.util.Arrays.asList(materia.getYellowMateriaList()).contains(selectedTextInDropdownList)){
                list.setSelectionBackground(Color.YELLOW);
                list.setSelectionForeground(Color.BLACK);
            } else if (java.util.Arrays.asList(materia.getRedMateriaList()).contains(selectedTextInDropdownList)){
                list.setSelectionBackground(Color.RED);
                list.setSelectionForeground(Color.WHITE);
            } else if (java.util.Arrays.asList(materia.getBlueMateriaList()).contains(selectedTextInDropdownList)){
                list.setSelectionBackground(Color.BLUE);
                list.setSelectionForeground(Color.WHITE);
            } else if (java.util.Arrays.asList(materia.getPurpleMateriaList()).contains(selectedTextInDropdownList)){
                list.setSelectionBackground(new Color(200,0,200));
                list.setSelectionForeground(Color.WHITE);
            } else {
                list.setSelectionBackground(defaultBackgroundColor);
                list.setSelectionForeground(Color.BLACK);
            }
            setBackground(mouseHoverHighlight);
        }
        else
        {
            // Set background to specific color depending on text value
            selectionForegroundColor = Color.BLACK;
            String selectedText = getText();
            if (java.util.Arrays.asList(materia.getGreenMateriaList()).contains(selectedText)) {
                selectionBackgroundColor = Color.GREEN;
                selectionForegroundColor = Color.BLACK;
            } else if (java.util.Arrays.asList(materia.getYellowMateriaList()).contains(selectedText)) {
                selectionBackgroundColor = Color.YELLOW;
                selectionForegroundColor = Color.BLACK;
            } else if (java.util.Arrays.asList(materia.getRedMateriaList()).contains(selectedText)) {
                selectionBackgroundColor = Color.RED;
                selectionForegroundColor = Color.WHITE;
            } else if (java.util.Arrays.asList(materia.getBlueMateriaList()).contains(selectedText)) {
                selectionBackgroundColor = Color.BLUE;
                selectionForegroundColor = Color.WHITE;
            } else if (java.util.Arrays.asList(materia.getPurpleMateriaList()).contains(selectedText)) {
                selectionBackgroundColor = new Color(200,0,200);
                selectionForegroundColor = Color.WHITE;
            } else {
                selectionBackgroundColor = defaultBackgroundColor;
                selectionForegroundColor = Color.BLACK;
            }
        }


        return this;
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getBackground() {
        return selectionBackgroundColor == null ? super.getBackground() : selectionBackgroundColor;
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getForeground() {
        return selectionForegroundColor == null ? super.getForeground() : selectionForegroundColor;
    }
}