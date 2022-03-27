package application;

import javafx.stage.FileChooser;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
@Slf4j
public class DataLoader {

    public void save(Collection<?> list, String fileName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (Object p : list) {
                out.write(p.toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception occurred during file saving: {}", fileName);
        }
    }

    public List<Point> load() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        Point point;
        List<Point> listOfPoint = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (!Pattern.compile("^-?\\d+,?\\d*\\s-?\\d+,?\\d*").matcher(line).find()) {
                    continue;
                }
                point = new Point();
                try {
                    Matcher matcher = Pattern.compile("-?\\d+,?\\d*").matcher(line);
                    matcher.find();
                    point.x = -1 * Double.parseDouble(matcher.group().replaceAll(",", "."));
                    matcher.find();
                    point.expF = Double.parseDouble(matcher.group().replaceAll(",", ".")) * 1000;
                    listOfPoint.add(point);
                } catch (Exception e) {
                    System.out.println(line);
                }
            }
            log.info("File loaded");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception occurred during file loading: {}", file);
        }
        return listOfPoint;
    }
}