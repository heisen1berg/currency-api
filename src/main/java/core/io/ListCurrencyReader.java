package core.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

//TODO: Cover exceptions

@Component
@Slf4j
public class ListCurrencyReader implements CurrencyReader {

    private static final String inputFile = "currency.txt";

    public Float getMultiplier(String currencyFrom, String currencyTo) {
        final String[] input = readFile();
        String[] parts;
        for (String s : input) {
            parts = s.split(" ");
            try {
                if (parts[0].equals(currencyFrom) && parts[1].equals(currencyTo))
                    return Float.parseFloat(parts[2]);
            } catch (NumberFormatException | IndexOutOfBoundsException ignore) {
            }
        }
        return null;
    }

    private String[] readFile() {
        final LinkedList<String> lineList = new LinkedList<String>();
        try {
            final BufferedReader reader;
            reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                lineList.add(line);
            }
            reader.close();
        } catch (IOException e) {
            log.error("Error reading file " + inputFile);
        }
        return lineList.toArray(new String[0]);
    }
}
