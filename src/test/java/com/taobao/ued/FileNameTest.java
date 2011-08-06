package com.taobao.ued;

import org.apache.commons.io.FilenameUtils;

/**
 * Created by IntelliJ IDEA.
 * User: czy-thinkpad
 * Date: 11-8-6
 * Time: ÏÂÎç4:19
 * To change this template use File | Settings | File Templates.
 */
public class FileNameTest {
    public static void main(String[] args) {
        // basename
        System.out.println(FilenameUtils.getBaseName("tc/cart/orderConfirm.css"));
        System.out.println(FilenameUtils.getBaseName("tc/cart/orderConfirm.css.gpp"));

        System.out.println(FilenameUtils.removeExtension("tc/cart/orderConfirm.css.gpp"));
        
        System.out.println(FilenameUtils.getPath("tc/cart/orderConfirm.css"));
        System.out.println(FilenameUtils.getFullPath("tc/cart/orderConfirm.css"));
        System.out.println(FilenameUtils.normalize("tc/cart/orderConfirm.css"));

        //fullpath
        System.out.println(FilenameUtils.getFullPathNoEndSeparator(FilenameUtils.normalize("D:/project/")));

    }
}
