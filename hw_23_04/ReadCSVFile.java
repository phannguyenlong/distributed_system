import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadCSVFile {
    public static void main(String[] args) {
        // FileWriter writer = null;
        ArrayList<Advertising> datas = readCsv(
                "D:/Long_Document/VGU-CS/excercise/distributed_system/hw_23_04/advertising.csv");
        for (Advertising data : datas) {
            System.out.println(data);
        }

    }
    
    public static void writeCSV(String path, Advertising data) {
        try {
            FileWriter out = new FileWriter(new File(path), true);
            try {
                out.write("\n" + data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    public static ArrayList<Advertising> readCsv(String path) {
        ArrayList<Advertising> datas = new ArrayList<>();
        Scanner reader = null;

        try {
            reader = new Scanner(
                    new File(path));
            String data = null;
            reader.nextLine(); // skip first line
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                StringTokenizer tokens = new StringTokenizer(data, ",");

                // get Data
                Advertising advert = new Advertising();
                advert.setTv(Double.parseDouble(tokens.nextToken()));
                advert.setRadio(Double.parseDouble(tokens.nextToken()));
                advert.setNewspaper(Double.parseDouble(tokens.nextToken()));
                advert.setSales(Double.parseDouble(tokens.nextToken()));

                datas.add(advert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }

        return datas;
    }}
