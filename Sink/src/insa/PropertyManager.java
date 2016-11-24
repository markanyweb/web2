package insa;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import sun.misc.BASE64Decoder;

public class PropertyManager
{
  private Properties config = null;

  public String getValue(String key)
  {
    this.config = new Properties();
    try
    {
      File f = new File(".");

      this.config.load(new FileInputStream(f.getCanonicalPath() + "/config.properties"));
    }
    catch (Exception localException) {
    }
    return this.config.getProperty(key);
  }

  public String getValue(String key, String flag)
  {
    this.config = new Properties();
    String value = "99";
    try
    {
      String f = getValue("global.path");

      this.config.load(new BufferedInputStream(new FileInputStream(f)));
      value = this.config.getProperty(key);

      BASE64Decoder decoder = new BASE64Decoder();

      value = new String(decoder.decodeBuffer(value));
    }
    catch (Exception localException) {
    }
    return value;
  }
}