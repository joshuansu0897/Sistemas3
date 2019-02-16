package com.mycompany.practicaextra1.handler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoundedRangeModel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {

    public ProgressBarRenderer() {
        super();
    }

    public ProgressBarRenderer(BoundedRangeModel newModel) {
        super(newModel);
    }

    public ProgressBarRenderer(int orient) {
        super(orient);
    }

    public ProgressBarRenderer(int min, int max) {
        super(min, max);
    }

    public ProgressBarRenderer(int orient, int min, int max) {
        super(orient, min, max);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        int val = (int) ((Float) value).floatValue();

        setValue(val);

        if (val == 100) {
            setForeground(Color.GREEN);
            setFont(getFont().deriveFont(Font.BOLD, 12));
            setString("Downloaded");
        } else {
            setForeground(Color.BLACK);
            setFont(getFont().deriveFont(Font.PLAIN, 12));
            setStringPainted(true);
            setString(null);
        }

        return this;
    }

}
