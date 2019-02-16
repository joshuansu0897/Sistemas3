/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practicaextra1.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFileChooser;

/**
 *
 * @author joshuansu
 */
public class Util {

    public static int getFileSize(String url) {
        try {
            return getFileSize(new URL(url));
        } catch (MalformedURLException ex) {
            return -1;
        }
    }

    public static int getFileSize(URL url) {
        URLConnection conn = null;
        try {
            conn = url.openConnection();
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).setRequestMethod("HEAD");
            }
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
        }
    }

    public static String chooseDir() {
        JFileChooser jd = new JFileChooser();
        jd.setDialogTitle("Donde desea guardar el archvivo?");
        int returnVal = jd.showSaveDialog(null);

        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        return jd.getSelectedFile().toString();
    }

    public static boolean isUrl(String url) {
        try {
            URL u = new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }
}
