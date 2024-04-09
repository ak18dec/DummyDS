package restapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestMain {
    public static void main(String[] args) {
        System.out.println(getDiscount(74002314));
    }

    public static int getDiscount(int barcode) {
        int result = -1;
        final String url = "https://jsonmock.hackerrank.com/api/inventory?barcode="+barcode;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        JSONParser parser = new JSONParser();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String resp = response.body();
            if(resp != null) {
                JSONObject jsonObject = (JSONObject) parser.parse(resp);
                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                JSONObject data = (JSONObject) jsonArray.get(0);
                Long discount = (Long) data.get("discount");
                Long price = (Long) data.get("price");
                if(discount != null && price != null) {
                    Double discountedPrice = price - ((1.0 * discount/100) * price);
                    result = discountedPrice.intValue();
                }
            }
            return result;
        }catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
