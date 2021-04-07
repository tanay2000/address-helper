import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        String filename = "All-Messages-search-result1.csv";
        int apiCall=0,totalRow=0;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(filename));
            String[] nextLine;
            while((nextLine = csvReader.readNext())!= null){
                for(String value : nextLine) {

                    if (value.contains("HTTP_CLOUDFRONT_IS_DESKTOP_VIEWER\":[\"true\"]")) {
                        System.out.println(value);
                        apiCall++;
                    }
                    if(value.contains("unknown"))
                        totalRow++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(apiCall);
        System.out.println(totalRow);
    }
}
