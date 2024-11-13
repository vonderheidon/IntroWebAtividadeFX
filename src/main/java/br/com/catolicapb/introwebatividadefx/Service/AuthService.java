package br.com.catolicapb.introwebatividadefx.Service;

import br.com.catolicapb.introwebatividadefx.Dao.UserDao;
import br.com.catolicapb.introwebatividadefx.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService {
    private static final String BASE_URL = "http://localhost:5000/api";
    private static String accessToken;
    private static User user;

    public static boolean login(String username, String password) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            JsonObject loginData = new JsonObject();
            loginData.addProperty("login", username);
            loginData.addProperty("senha", password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + "/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(loginData.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject responseObject = new Gson().fromJson(response.body(), JsonObject.class);
                accessToken = responseObject.get("access_token").getAsString();
                user = UserDao.buscarUsuarioPorLogin(accessToken ,username);
                return true;
            } else {
                System.out.println("Erro: " + response.body());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static User getUser() {
        return user;
    }

    public static void logout() {
        accessToken = null;
        user = null;
    }
}
