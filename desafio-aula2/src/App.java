import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Conexão HTTP e consumindo API
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; //link alternativo
        URI link = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(link).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();        


        // Extraindo dados de interesse
        JsonParser parser = new JsonParser();
        List<Map<String, String>> topMovies = parser.parse(body);

        // Exibindo dados da lista
        String emojiTv = Character.toString(0x1f4fa);
        String emojiStar = Character.toString(0x2b50);

        System.out.println("\u001b[1m===== Gerando Figurinhas dos Top Filmes =====\u001b[m \n");

        String path = "saida";
        Files.createDirectory(Paths.get(path));

        for (Map<String,String> top : topMovies) {
            String urlImagem = top.get("image");
            String titulo = top.get("title");
            String nomeArquivo = "saida/" + titulo + ".png";
            InputStream inputStream = new URL(urlImagem).openStream();

            System.out.println(emojiTv + " Título do filme: \u001b[3m" + titulo + "\u001b[m");
            //System.out.println("Poster da série: " + top.get("image"));
            double ratingDb = Double.parseDouble(top.get("imDbRating")); //convertendo String em Double
            int rate = (int) ratingDb; //convertendo Double em int
            System.out.println("Classificação " + emojiStar.repeat(rate) + "\n");

            GeradorFigurinhas geradora = new GeradorFigurinhas();
            geradora.criaFigurinhas(inputStream, nomeArquivo);
        }

    }
}