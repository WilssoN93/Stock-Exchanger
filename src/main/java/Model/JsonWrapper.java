package Model;

import com.google.gson.annotations.SerializedName;

public class JsonWrapper {

    @SerializedName("Global Quote")
    private GlobalQuote quote;

    public JsonWrapper (){

    }

    @Override
    public String toString() {
        return "JsonWrapper{" +
                "quote=" + quote +
                '}';
    }

    public GlobalQuote getQuote() {
        return quote;
    }

    public void setQuote(GlobalQuote quote) {
        this.quote = quote;
    }
}
