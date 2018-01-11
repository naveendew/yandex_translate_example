package com.autoinfini.stackoverflowproblems;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by naveendewangan on 11/01/18.
 */

public class OkhttpHandler extends AsyncTask<String,Void,String>
{
    String res;
    String transres;
    String finalres;
    public AsyncResponse delegate;

    public OkhttpHandler(AsyncResponse delegate)
    {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String[] values)
    {
        // TODO: Implement this method
        OkHttpClient client = new OkHttpClient();
        String texttotranslate = values[0];
        String lang_pair = values[1];
        String key = "trnsl.1.1.20180111T100424Z.0b98b1163f2fe387.b3a0a778cfd2eccaa2c9baad25b7088290abcd11";
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + key
                + "&text=" + texttotranslate + "&lang=" + lang_pair;

        Request request = new Request.Builder().url(url).build();

        try
        {
            Response response = client.newCall(request).execute();
            res = response.body().string();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (res != null)
        {
            try
            {
                JSONObject jsonobj = new JSONObject(res);
                transres = jsonobj.getString("text");
                String f = transres.replace("[", "");
                String s = f.replace("]", "");
                finalres = s.replace("\"", "");
                Log.d("final result", "" + finalres);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return finalres;
    }

    @Override
    protected void onPostExecute(String result)
    {
        // TODO: Implement this method
        super.onPostExecute(result);
        delegate.processFinish(result);
        Log.d("result:", "" + result);
    }

}