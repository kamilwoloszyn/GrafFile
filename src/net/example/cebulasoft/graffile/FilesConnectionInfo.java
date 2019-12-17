package net.example.cebulasoft.graffile;

import java.util.HashMap;

// zamiana funkcionalnosci kals File[s]ConnectionInfo <=> FileInfo
public class FilesConnectionInfo extends HashMap<String, FileInfo>
{

    @Override
    public FileInfo put(String key, FileInfo info)
    {

        if(get(info.getName())!=null)
        {
            super.put(key, info);
            return info;
        }
        return null;
    }
}
