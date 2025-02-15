package service.reader.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import service.processor.StaffProcessor;
import service.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Getter
@AllArgsConstructor
public class StaffReader implements Reader {

    private final StaffProcessor dataProcessor;
    public void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dataProcessor.processDataLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}