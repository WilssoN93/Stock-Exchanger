package Client;

import Model.GlobalQuote;
import Model.JsonWrapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Client {

    String START_OF_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=";
    String END_OF_URL = "&apikey=*************";
    Gson gson = new Gson();



    public GlobalQuote getQuote(String symbol){

        try {

            HttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet(START_OF_URL + symbol + END_OF_URL);
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String stringEntity  = EntityUtils.toString(entity);
            JsonWrapper x = gson.fromJson(stringEntity,JsonWrapper.class);
            return x.getQuote();
        }catch (IOException e){
            System.out.println("this dosnt work");
            return null;
        }
    }



}
