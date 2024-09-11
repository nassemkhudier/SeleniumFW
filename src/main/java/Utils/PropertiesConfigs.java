package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfigs {
    private final String properties_path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Config.properties";

    public Properties prop() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(properties_path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
