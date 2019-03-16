package com.example.kalmado_dan;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data extends AppCompatActivity {

    File path;
    File fileAdapter;

    public Data(Context context) {
        path = context.getFilesDir();
        fileAdapter = new File(path, "test");
    }

    public void addData(ArrayList<String> dataToAdd) {
        if (!fileAdapter.exists()) {
            try {
                fileAdapter.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileAdapter, true));
            String toAdd = "";
            for (int x = 0; x < dataToAdd.size() - 1; x++) {
                toAdd = toAdd + dataToAdd.get(x) + "|";
            }
            toAdd = toAdd + dataToAdd.get(dataToAdd.size() - 1);
            writer.write(toAdd);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(ArrayList<String> dataToDelete) {
        ArrayList<String> newData = new ArrayList<String>();
        if (fileAdapter.exists()) {
            try {
                String dataDelete = "";
                for (int y = 0; y < dataToDelete.size() - 1; y++) {
                    dataDelete = dataDelete + dataToDelete.get(y) + "|";
                }
                dataDelete = dataDelete + dataToDelete.get(dataToDelete.size() - 1);
                BufferedReader reader = new BufferedReader(new FileReader(fileAdapter));
                String line;
                while((line = reader.readLine()) != null) {
                    if (!line.equals(dataDelete)) {
                        newData.add(line);
                    }
                }
                reader.close();
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileAdapter, false));
                for (int z = 0; z < newData.size(); z++) {
                    writer.write(newData.get(z));
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            return;
        }
    }

    public ArrayList<String> getData() {
        ArrayList<String> fileData = new ArrayList<String>();
        if (fileAdapter.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileAdapter));
                String line;
                while ((line = reader.readLine()) != null) {
                    fileData.add(line);
                }
                reader.close();
                return fileData;
            } catch (FileNotFoundException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }
        else {
            return null;
        }
    }
}