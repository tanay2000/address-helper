import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class csvtobean {
    public static void readVariableColumnsWithCsvListReader(){

        final CellProcessor[] allProcessors = new CellProcessor[] { new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), // firstName
                new NotNull(), // lastName
                new ParseDate("dd/MM/yyyy") }; // birthDate

        final CellProcessor[] noBirthDateProcessors = new CellProcessor[] { allProcessors[0], // customerNo
                allProcessors[1], // firstName
                allProcessors[2] }; // lastName

        ICsvListReader listReader = null;
        try {
            try {
                listReader = new CsvListReader(new FileReader("All-Messages-search-result.csv"), CsvPreference.STANDARD_PREFERENCE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
            } catch (IOException e) {
                e.printStackTrace();
            }

            while(true) {
                try {
                    if (!((listReader.read()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // use different processors depending on the number of columns
                final CellProcessor[] processors;
                if( listReader.length() == noBirthDateProcessors.length ) {
                    processors = noBirthDateProcessors;
                } else {
                    processors = allProcessors;
                }

                final List<Object> customerList = listReader.executeProcessors(processors);
                System.out.println(String.format("lineNo=%s, rowNo=%s, columns=%s, customerList=%s",
                        listReader.getLineNumber(), listReader.getRowNumber(), customerList.size(), customerList));
            }

        }
        finally {
            if( listReader != null ) {
                try {
                    listReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
