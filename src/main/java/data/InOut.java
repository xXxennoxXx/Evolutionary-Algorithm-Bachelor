package data;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InOut {

    // new FileChooser().showOpenDialog(null)

    public static List<Sample> load(File file) {
        List<Sample> listOfPoint = new ArrayList<>();
        Sample point;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (!Pattern.compile("^-?\\d+,?\\d*\\s-?\\d+,?\\d*").matcher(line).find())
                    continue;
                point = new Sample();
                try {
                    Matcher matcher = Pattern.compile("-?\\d+,?\\d*").matcher(line);
                    matcher.find();
                    point.x = -1 * Double.parseDouble(matcher.group().replaceAll(",", "."));
                    matcher.find();
                    point.expF = Double.parseDouble(matcher.group().replaceAll(",", ".")) * 1000;
                    listOfPoint.add(point);
                } catch (Exception e) {
                    System.out.println(line);
                    continue;
                }
            }
            System.out.println("File loaded.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return listOfPoint;
    }

    public static void save(ArrayList list, String fileName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (Object p : list) {
                out.write(p.toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
    }
}
