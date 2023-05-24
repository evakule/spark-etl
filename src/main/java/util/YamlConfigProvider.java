package util;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class YamlConfigProvider {

  private final Properties properties = new Properties();

  public YamlConfigProvider(String configFilePath) {

    try (FileInputStream inputStream = new FileInputStream(configFilePath)) {

      HashMap<String, Object> propsMap = new Yaml().load(inputStream);

      if (propsMap != null) {
        for (String key : propsMap.keySet()) {
          properties.setProperty(key, propsMap.get(key).toString());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Properties getProperties() {
    return properties;
  }
}
