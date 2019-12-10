package net.example.cebulasoft.graffile;

import java.util.HashMap;

public class FileInfo extends HashMap<String, FileConnectionInfo>
{

    @Override
    public FileConnectionInfo put(String key, FileConnectionInfo info)
    {

        if(get(info.getName())!=null)
        {
            super.put(key, info);
            return info;
        }
        return null;
    }
}
