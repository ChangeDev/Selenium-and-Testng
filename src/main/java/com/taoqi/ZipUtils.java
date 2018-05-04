package com.taoqi;

/**
 * Created by TQ-G153 on 2017/12/5.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by ChangFeng on 2017/10/25.
 */
public class ZipUtils {

    public static final String DEFAULT_ENCODING = "UTF-8";

    private ZipUtils() {
    }

    /**
     * 使用默认的UTF-8编码方式压缩
     *
     * @param str
     * @return
     */
    public static String compress(String str) {
        return compress(str, DEFAULT_ENCODING);
    }

    /**
     * 使用默认的UTF-8编码方式解压缩
     *
     * @param str
     * @return
     */
    public static String decompress(String str) {
        return decompress(str, DEFAULT_ENCODING);
    }

    /**
     * 压缩,包含base64编码操作
     *
     * @param str      待压缩的字符串
     * @param encoding 编码
     * @return
     */
    public static String compress(String str, String encoding) {
        if (str == null) {
            return null;
        }
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes(encoding));
            zout.closeEntry();
            compressed = out.toByteArray();
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
            }
        }
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(compressed));
    }

    /**
     * 解压缩，包含base64解码操作
     *
     * @param str      待解压字符串
     * @param encoding 编码
     * @return
     */
    public static String decompress(String str, String encoding) {
        if (str == null) {
            return null;
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        byte[] compressed = null;
        try {
            compressed = org.apache.commons.codec.binary.Base64.decodeBase64(str);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            ZipEntry entry = zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = new String(out.toByteArray(), encoding);

        } catch (IOException e) {
            throw new RuntimeException("解压缩字符串数据出错", e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭ZipInputStream", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭ByteArrayInputStream", e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭ByteArrayOutputStream", e);
                }
            }
        }
        return decompressed;
    }


}
