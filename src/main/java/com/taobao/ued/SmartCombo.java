package com.taobao.ued;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * smart combo by combo.properties
 *
 * Created by IntelliJ IDEA.
 * User: czy-thinkpad
 * Date: 11-7-31
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class SmartCombo {
    public static void main(String[] args) {
        String propertiesPath = args[0];
        String root = args[1];
        String split = args[2];
        String suffix = args[3];

        if(suffix == null) {
            suffix = "";
        }

//        String propertiesPath = "D:\\project\\tradeface\\assets\\build\\combo.properties";
//        String root = "D:\\project\\tradeface\\assets\\";
//        String split = "??";
//        String propertiesPath = "src/main/java/test.txt";
        if ("".equals(propertiesPath)) {
            System.out.println("no combo.properties to build!");
            return;
        }
        File file = new File(propertiesPath);

        if (!file.exists() || !file.canRead()) {
            System.out.println("combo.properties isn't exist!");
            return;
        }

        try {
            Properties properties = new Properties();
            properties.load(FileUtils.openInputStream(file));
            if (properties.size() > 0) {
                for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
                    String comboFileName = (String) objectEntry.getKey();
                    String containNames = (String) objectEntry.getValue();
                    List<String> strings = splitPath(containNames, filterSpecialChar(split), root);
                    outputFile(root + FilenameUtils.removeExtension(comboFileName)+ suffix, strings);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param comboString
     * @param split       分隔符
     * @param root        前缀目录
     * @return
     */
    public static List<String> splitPath(String comboString, String split, String root) {
        String[] firstCut = comboString.split(split);
        String pathPrefix = firstCut[0];    // e.g.:http://a.tbcdn.cn/p/
        String[] allFiles = firstCut[1].split(",");
        List<String> list = new ArrayList<String>();
        for (String everyFile : allFiles) {
            //过滤时间戳和一些参数
            everyFile = everyFile.replaceAll("[\\?&].*$", "");
            // e.g.:header/header-min.css
            //拼出单个url，然后的逻辑和单文件相同
            String singleFilePath = root + pathPrefix + everyFile;
            list.add(singleFilePath);
        }
        return list;
    }

    public static void outputFile(String outputPath, List<String> comboFiles) throws IOException {
        File outPutFile = new File(outputPath);
        StringBuilder sb = new StringBuilder();
        for (String comboFile : comboFiles) {
            sb.append(FileUtils.readFileToString(new File(comboFile)));
        }
        FileUtils.writeStringToFile(outPutFile, sb.toString());
    }
    
    /**
     * 过滤特殊字符
     * @param input
     * @return
     */
    public static String filterSpecialChar(String input) {
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            switch (c) {
                case '"':filtered.append("\\\"");break;
                case '\'':filtered.append("\\'");break;
                case '\\':filtered.append("\\\\");break;
                case '?':filtered.append("\\?");break;
                case '.':filtered.append("\\.");break;
                default:filtered.append(c);
            }
        }
        return (filtered.toString());
    }
}
