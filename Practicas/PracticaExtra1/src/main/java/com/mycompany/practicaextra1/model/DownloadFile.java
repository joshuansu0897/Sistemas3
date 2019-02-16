package com.mycompany.practicaextra1.model;

import com.mycompany.practicaextra1.util.Util;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;

public final class DownloadFile extends Observable implements Runnable {

    private Thread thread;

    private String name;
    private String url;
    private float progress;
    private int filesize;
    private String filePath;

    public DownloadFile() {
    }

    public DownloadFile(String name, String url, int filesize) {
        this.name = name;
        this.url = url;
        this.filesize = filesize;

        start();
    }

    public DownloadFile start() {
        thread = new Thread(this);
        thread.start();

        return this;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!Util.isUrl(url)) {
            return;
        }

        this.url = url;
    }

    public float getProgress() {
        return progress;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {

        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            int count = 0;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                count += bytesRead;
                progress = ((float) count / filesize) * 100;

                setChanged();
                notifyObservers(this);
            }
            progress = 100;
            setChanged();
            notifyObservers(this);
        } catch (IOException e) {
        }

    }
}
