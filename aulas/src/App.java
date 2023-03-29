import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        //Realizando uma conexão HTTP e consumindo API
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; //utilizando link alternativo
        URI endereco = URI.create(url);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        //Extrair somente os dados de interessa (título, poster, classificação)
        JsonPerser parser = new JsonPerser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir os dados
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            String nomeArquivo = titulo + ".png";
            InputStream inputStream = new URL(urlImagem).openStream();

            GeradorFigurinhas geradora = new GeradorFigurinhas();
            geradora.criaFigurinhas(inputStream, nomeArquivo);

            System.out.println(filme.get("title") + "\n");
            //System.out.println(filme.get("image"));
            //System.out.println(filme.get("imDbRating") + "\n");
        }

    }
}
    