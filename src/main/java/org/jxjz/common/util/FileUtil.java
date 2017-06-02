package org.jxjz.common.util;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileUtil
{
    public FileUtil()
    {
    }

    public static void copyFile(String src, String dest) throws Exception
    {
        FileUtils.copyFile(new File(src), new File(dest));
    }

    public static void copyDir(String srcDir, String destDir) throws Exception
    {
        File files[] = (new File(srcDir)).listFiles();
        for(int i = 0;i < files.length;i++)
        {
            if(files[i].isDirectory())
            {
                String newdirpath = destDir + File.separator + files[i].getName();
                copyDir(files[i].getAbsolutePath(), newdirpath);
            }
            else
            {
                FileUtils.copyFileToDirectory(files[i], new File(destDir));
            }
        }
    }

    public static String getFileExt(String fileName)
    {
        int index = fileName.lastIndexOf(".");
        if(index == -1)
        {
            return "";
        }
        else if(index == (fileName.length() - 1))
        {
            return "";
        }
        else
        {
            return fileName.substring(index + 1);
        }
    }

    public static String getFileName(String urlpath)
    {
        int index = urlpath.lastIndexOf("/");
        if(index == -1)
        {
            return "";
        }
        else if(index == (urlpath.length() - 1))
        {
            return "";
        }
        else
        {
            return urlpath.substring(index + 1);
        }
    }
    
    
}
