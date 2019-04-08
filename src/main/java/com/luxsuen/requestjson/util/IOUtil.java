package com.luxsuen.requestjson.util;

import java.io.*;

public class IOUtil {

    /**
     * @Author Lux Sun
     * @Description: InputStream 转 String
     * @Param: [in, encode]
     * @Return: java.lang.String
     */
    public static String inputStream2Str(InputStream in, String encode)
    {
        String str = "";
        BufferedReader reader = null;
        try {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            reader = new BufferedReader(new InputStreamReader(in, encode));
            StringBuffer sb = new StringBuffer();

            while ((str = reader.readLine()) != null)
            {
                sb.append(str).append("\n");
            }

            return sb.toString();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return str;
    }
}
