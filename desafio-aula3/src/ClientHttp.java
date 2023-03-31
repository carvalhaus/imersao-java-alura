import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {
    
    public String buscaDados (String url) throws SemConexaoApi {

        try {
            
            URI link = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(link).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();
            return body;

        } catch (IOException | InterruptedException e) {
            throw new SemConexaoApi("Não foi possível estabelecer conexão com a API, verifique se a URL está correta!");   
        }

    }

}