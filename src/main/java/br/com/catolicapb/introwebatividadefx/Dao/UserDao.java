package br.com.catolicapb.introwebatividadefx.Dao;

import br.com.catolicapb.introwebatividadefx.Model.User;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserDao {
    private static final String API_URL = "http:localhost:5000/api";
    private final HttpClient httpClient;
    private final Gson gson;

    public UserDao() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public User getUserById(String token) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return gson.fromJson(response.body(), User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

