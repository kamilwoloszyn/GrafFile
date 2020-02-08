package net.example.cebulasoft.graffile;

import java.util.HashMap;

// zamiana funkcionalnosci kals File[s]ConnectionInfo <=> FileInfo
public class FilesConnectionInfo extends HashMap<String, FileInfo>
{

    @Override
    public FileInfo put(String key, FileInfo info)
    {

        if(!containsKey(key))
        {
            super.put(key, info);
            return info;
        }
        throw new RuntimeException("Override key not allowed.");
    }
}