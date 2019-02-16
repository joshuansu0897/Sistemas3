package com.mycompany.practicaextra1.handler;

import com.mycompany.practicaextra1.model.DownloadFile;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;

public class DownloadTableModel extends AbstractTableModel implements Observer {

    final String[] columnNames = {"FileName", "Url", "Progress"};
    final Class[] columnClasses = {String.class, String.class, JProgressBar.class};
    final ArrayList<DownloadFile> data = new ArrayList<>();

    public void addDownload(DownloadFile d) {
        data.add(d);

        d.addObserver(this);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnClasses.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DownloadFile download = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return download.getName();
            case 1:
                return download.getUrl();
            case 2:
                return download.getProgress();
            default:
                return null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        int index = data.indexOf(o);
        if (index != -1) {
            fireTableRowsUpdated(index, index);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
