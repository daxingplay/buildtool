package com.taobao.ued;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: czy-thinkpad
 * Date: 11-7-31
 * Time: ÏÂÎç7:48
 * To change this template use File | Settings | File Templates.
 */
public class SmartCombo {
    public static void main(String[] args) {
//        String propertiesPath = args[0];
        String propertiesPath = "src/main/java/test.txt";
        if("".equals(propertiesPath)) {
            System.out.println("no combo.properties to build!");
            return;
        }
        File file = new File(propertiesPath);
        System.out.println(file.getAbsolutePath());

        if(!file.exists() || !file.canRead()) {
            System.out.println("combo.properties isn't exist!");
            return;
        }

        String normalize = null;
        try {
            Properties properties = new Properties();
            properties.load(FileUtils.openInputStream(file));
            if(properties.size() > 0) {
                for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
                    String comboFileName = (String) objectEntry.getKey();
                    String containNames = (String) objectEntry.getValue();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(normalize);
    }
}
