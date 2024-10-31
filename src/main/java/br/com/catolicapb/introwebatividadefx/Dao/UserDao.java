package br.com.catolicapb.introwebatividadefx.Dao;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import br.com.catolicapb.introwebatividadefx.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserDao {

    private static final String BASE_URL = "http://localhost:5000/api";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static List<User> listarUsuarios(String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/usuarios"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Type userListType = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(response.body(), userListType);
        } else {
            throw new Exception("Erro ao listar usuários: " + response.body());
        }
    }
}
