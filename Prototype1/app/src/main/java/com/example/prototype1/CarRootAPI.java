package com.example.prototype1;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yifeige on 27/12/14.
 */
public class CarRootAPI
{
    private Map<String,Link> links;

    public CarRootAPI()
    {
        links =new HashMap<String,Link>();
    }

    public Map<String,Link>getLinks()
    {
        return links;
    }
}
