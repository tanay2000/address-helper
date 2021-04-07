import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        String filename = "All-Messages-search-result.csv";
        int c=0;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(filename));
            String[] nextLine;
            while((nextLine = csvReader.readNext())!= null){
                for(String value : nextLine) {

                    if (value.contains("HTTP_CLOUDFRONT_IS_DESKTOP_VIEWER\":[\"true\"]")) {
                        System.out.println(value);
                        c++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(c);
    }
//HTTP_CLOUDFRONT_IS_DESKTOP_VIEWER":["true"]

    //"\"HTTP_CLOUDFRONT_IS_DESKTOP_VIEWER\":[\"true\"]"
}
