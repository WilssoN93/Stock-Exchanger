package Service;

import Client.Client;
import Model.GlobalQuote;

import java.util.HashMap;

import java.util.Map;

public class Exchanger {

    String[] symbols = {"AMD", "INTC", "BA", "MSFT","TSLA","UBER","SNAP","AAPL","ACB"};
    Client client = new Client();
    Map<String, GlobalQuote> quotes = new HashMap<>();
    Map<String,Integer> stockAmount = new HashMap<>();

    public Exchanger (){
        for (String symbol:symbols) {
            stockAmount.put(symbol,0);
        }
    }

    private double money = 10000;


    public void run() {
        while (money > 0) {
            for (String symbol : symbols) {
                GlobalQuote quote = client.getQuote(symbol);
                System.out.println("Stock: " + quote.getSymbol() + " Price: " + quote.getPrice());
                if (quotes.containsKey(quote.getSymbol())) {
                    if (Double.parseDouble(quote.getPrice()) < Double.parseDouble(quotes.get(quote.getSymbol()).getPrice())) {
                        buy(Double.parseDouble(quote.getPrice()));
                        stockAmount.put(quote.getSymbol(),stockAmount.get(quote.getSymbol())+1);
                        quotes.put(quote.getSymbol(), quote);
                    } else if (Double.parseDouble(quote.getPrice()) > Double.parseDouble(quotes.get(quote.getSymbol()).getPrice())) {
                        if (stockAmount.get(quote.getSymbol()) > 0) {
                            sell(Double.parseDouble(quote.getPrice()),stockAmount.get(quote.getSymbol()),quote.getSymbol());
                            stockAmount.put(quote.getSymbol(),stockAmount.get(quote.getSymbol())-1);
                        }
                        quotes.put(quote.getSymbol(), quote);
                    }else {
                        quotes.put(quote.getSymbol(),quote);
                    }
                } else {
                    quotes.put(quote.getSymbol(), quote);
                }
                System.out.println(stockAmount.toString());
                System.out.println("Money equals " + money + "!");
                Utils.Utils.waitTime(15000);
            }
        }
    }

    public void buy(double price) {
        money -= price;
        System.out.println("Bought");
    }

    public void sell(double price, int amount,String stock) {
        money += price * amount;
        System.out.println("SOLD " + amount + " " + stock + " Stocks!");
    }
}
