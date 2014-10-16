// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fasterxml.jackson.core.util:
//            JsonParserDelegate

public class JsonParserSequence extends JsonParserDelegate
{

    protected JsonParserSequence(JsonParser ajsonparser[])
    {
        super(ajsonparser[0]);
        _parsers = ajsonparser;
        _nextParser = 1;
    }

    public static JsonParserSequence createFlattened(JsonParser jsonparser, JsonParser jsonparser1)
    {
        if(!(jsonparser instanceof JsonParserSequence) && !(jsonparser1 instanceof JsonParserSequence))
            return new JsonParserSequence(new JsonParser[] {
                jsonparser, jsonparser1
            });
        ArrayList arraylist = new ArrayList();
        if(jsonparser instanceof JsonParserSequence)
            ((JsonParserSequence)jsonparser).addFlattenedActiveParsers(arraylist);
        else
            arraylist.add(jsonparser);
        if(jsonparser1 instanceof JsonParserSequence)
            ((JsonParserSequence)jsonparser1).addFlattenedActiveParsers(arraylist);
        else
            arraylist.add(jsonparser1);
        return new JsonParserSequence((JsonParser[])arraylist.toArray(new JsonParser[arraylist.size()]));
    }

    protected void addFlattenedActiveParsers(List list)
    {
        int i = -1 + _nextParser;
        int j = _parsers.length;
        int k = i;
        while(k < j) 
        {
            JsonParser jsonparser = _parsers[k];
            if(jsonparser instanceof JsonParserSequence)
                ((JsonParserSequence)jsonparser).addFlattenedActiveParsers(list);
            else
                list.add(jsonparser);
            k++;
        }
    }

    public void close()
        throws IOException
    {
        do
            _flddelegate.close();
        while(switchToNext());
    }

    public int containedParsersCount()
    {
        return _parsers.length;
    }

    public JsonToken nextToken()
        throws IOException, JsonParseException
    {
        JsonToken jsontoken = _flddelegate.nextToken();
        if(jsontoken != null)
            return jsontoken;
        while(switchToNext()) 
        {
            JsonToken jsontoken1 = _flddelegate.nextToken();
            if(jsontoken1 != null)
                return jsontoken1;
        }
        return null;
    }

    protected boolean switchToNext()
    {
        if(_nextParser >= _parsers.length)
        {
            return false;
        } else
        {
            JsonParser ajsonparser[] = _parsers;
            int i = _nextParser;
            _nextParser = i + 1;
            _flddelegate = ajsonparser[i];
            return true;
        }
    }

    protected int _nextParser;
    protected final JsonParser _parsers[];
}
