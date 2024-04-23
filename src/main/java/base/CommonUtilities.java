package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class CommonUtilities {
    public List<HashMap<String,String>> getjsonData(String jsonFilePath) throws IOException
    {
        //TODO: Convert json file content to JSON string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;
    }

    public static void staticWait(int sleepTimeInSeconds)
    {
        try {
            Thread.sleep(sleepTimeInSeconds* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties properties = new Properties();
    private final static String propFilePath = System.getProperty("user.dir")+"/src/main/resources/config.properties";
    public static String getCustomProperty(String customPropertyName)
    {
        try {
            properties.load(new FileInputStream(propFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  properties.getProperty(customPropertyName);
    }


}
