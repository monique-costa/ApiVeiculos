package br.com.zup.apiVeiculos.apiFipe;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumirApiFipe {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    public String codigoMarca;
    public String codigoModelo;
    public String codigoAno;
    public String preco;
    public String ano;
    		
                
    public String getCodigoMarca(String marca) throws Exception {
    	
    	HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/carros/marcas"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
               
        ObjetoSimples[] arrayMarcas = mapper.readValue(result, ObjetoSimples[].class);
        
        
        for (int i = 0; i < arrayMarcas.length; i++) {
        	if (arrayMarcas[i].getNome().equals(marca)) {
        		codigoMarca = arrayMarcas[i].getCodigo();
        		return codigoMarca;
        	} else {
        		codigoMarca = "Não deu certo";
        	}
        }
        
        return codigoMarca;
        
    }
    
    
    public String getCodigoModelo(String codigoMarca, String modelo) throws Exception {
    	
    	HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codigoMarca+"/modelos"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        JSONObject auxiliar = new JSONObject(result);
        String resultModelo = auxiliar.getJSONArray("modelos").toString();
        
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
               
        ObjetoSimples[] arrayModelos = mapper.readValue(resultModelo, ObjetoSimples[].class);
        
        for (int i = 0; i < arrayModelos.length; i++) {
        	if (arrayModelos[i].getNome().equals(modelo)) {
        		codigoModelo = arrayModelos[i].getCodigo();
        		return codigoModelo;
        	} else {
        		codigoModelo = "Não deu certo";
        	}
        }
        
        return codigoModelo;
        
    }
    
    
    public String getCodigoAno(String codigoMarca, String codigoModelo, String ano) throws Exception {
    	
    	HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codigoMarca+"/modelos/"+codigoModelo+"/anos"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
               
        ObjetoSimples[] arrayAnos = mapper.readValue(result, ObjetoSimples[].class);
        
        
        for (int i = 0; i < arrayAnos.length; i++) {
        	if (arrayAnos[i].getNome().equals(ano)) {
        		codigoAno = arrayAnos[i].getCodigo();
        		return codigoAno;
        	} else {
        		codigoAno = "Não deu certo";
        	}
        }
        
        return codigoAno;
        
    }
    
    
    public ObjetoDetalhado getPreco(String codigoMarca, String codigoModelo, String codigoAno) throws Exception {
    	
    	HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://parallelum.com.br/fipe/api/v1/carros/marcas/"+codigoMarca+"/modelos/"+codigoModelo+"/anos/"+codigoAno))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        
        String teste = result.toLowerCase();
   
        ObjectMapper mapper = new ObjectMapper();
                        
        ObjetoDetalhado detalhes = mapper.readValue(teste, ObjetoDetalhado.class);
        
        return detalhes;
        
    }
    
    
}

