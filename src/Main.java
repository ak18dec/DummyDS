import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.net.*;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
class Person {
    private  String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return this.name;
    }
}
public class Main {
//    public static void main(String[] args) {

////        List<Person> orl = new ArrayList<>();
////
////        orl.add(new Person("ankit", 10));
////        orl.add(new Person("anhya", 20));
////        orl.add(new Person("anjghkfhya", 20));
////
////        System.out.println(orl.stream().collect(Collectors.groupingBy(Person::getAge)));
//
////        Notepad basics = new Notepad("Equals basics", 2000);
////        Notepad advanced = new Notepad("Equals advanced", 2001);
////
//////        System.out.println(basics.equals(basics));
//////        System.out.println(basics.equals(advanced));
////        System.out.println(basics.equals(new Notepad("Equals basics", 2000)));
////        System.out.println(basics.equals(new Notepad("Equals basics", 2001)));
//
//        try {
//            ServerSocket serverSocket = new ServerSocket(9999);
//            Socket socket = serverSocket.accept();
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String mesaage = in.readLine();
//            System.out.println("Client: "+mesaage);
//            socket.close();
//            serverSocket.close();
//        }catch(IOException e) {
//            e.printStackTrace();
//        }

//    }


    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.getDiscount(74002314));
    }

    public int getDiscount(int barcode) {
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