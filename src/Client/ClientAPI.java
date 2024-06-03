package Client;

import Models.CurrencyModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Currency;

public class ClientAPI {
    private static String key = "4be3e6c274eb7d3e89b3ea26";

    private static String urlApi = "https://v6.exchangerate-api.com/v6/" + key + "/latest/";
    public static Gson gson = new Gson();


    private CurrencyModel makeRequest(String currency) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
//                .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
//                .authenticator(Authenticator.getDefault())
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlApi+currency))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
//                .thenAccept(System.out::println);
//        System.out.println(request);

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CurrencyModel resCurrency = gson.fromJson(response.body(), CurrencyModel.class);
            return resCurrency;
        } catch (Exception err) {
            System.out.println(err);
        }


        return null;
    }

    public CurrencyModel getCurrency(String currency) {

        CurrencyModel res = makeRequest(currency);
//        System.out.println(res);
        return res;
    }
}
