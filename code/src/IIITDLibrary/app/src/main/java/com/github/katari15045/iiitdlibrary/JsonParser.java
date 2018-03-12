package com.github.katari15045.iiitdlibrary;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

// Parses the JSON string from Google API to extract the URL of the cover image of a book
class JsonParser {

    static String parse(String unParsedData){
        String parsedData = null;
        try{
            JSONObject root = new JSONObject(unParsedData);
            JSONArray itemArray = root.getJSONArray("items");
            JSONObject volInfo = itemArray.getJSONObject(0).getJSONObject("volumeInfo");
            JSONObject imageLink = volInfo.getJSONObject("imageLinks");
            parsedData = imageLink.getString("smallThumbnail");
        } catch (Exception e){
            e.printStackTrace();
        }
        return parsedData;
    }
}
