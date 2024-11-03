package br.com.catolicapb.introwebatividadefx.Dao;

import br.com.catolicapb.introwebatividadefx.Model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ProductDao {

    private static final String BASE_URL = "http://localhost:5000/api";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static List<Product> listarProdutos(String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/produtos"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Type productListType = new TypeToken<List<Product>>(){}.getType();
            return gson.fromJson(response.body(), productListType);
        } else {
            throw new Exception("Erro ao listar produtos: " + response.body());
        }
    }

    public static Product buscarProdutoPorId(String token, String id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/produtos/" + id))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), Product.class);
        } else {
            throw new Exception("Erro ao buscar produto por ID: " + response.body());
        }
    }

    public static Product inserirProduto(String token, Product product) throws Exception {
        String json = gson.toJson(product);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/produtos"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            return gson.fromJson(response.body(), Product.class);
        } else {
            throw new Exception("Erro ao inserir produto: " + response.body());
        }
    }

    public static void atualizarProduto(String token, Product product) throws Exception {
        String json = gson.toJson(product);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/produtos/" + product.getId()))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Erro ao atualizar produto: " + response.body());
        }
    }

}

