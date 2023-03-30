import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Conexão HTTP e consumindo API
        //TopSeries
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json"; //link alternativo
        URI link = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(link).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
               
        //MostPopularSeries
        String url1 = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json"; //link alternativo
        URI link1 = URI.create(url1);
        HttpClient client1 = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder(link1).GET().build();
        HttpResponse<String> response1 = client1.send(request1, BodyHandlers.ofString());
        String body1 = response1.body();
        


        // Extraindo dados de interesse
        //Top Series
        JsonParser parserTop = new JsonParser();
        List<Map<String, String>> topSeriesTv = parserTop.parse(body);

        //MostPopularSeries
        JsonParser parserPop = new JsonParser();
        List<Map<String, String>> popSeriesTv = parserPop.parse(body1);


        // Exibindo dados da lista

        String emojiTv = Character.toString(0x1f4fa);
        String emojiStar = Character.toString(0x2b50);

        System.out.println("\u001b[1m===== Lista das Top Séries de TV =====\u001b[m \n");
        for (Map<String,String> top : topSeriesTv) {
            System.out.println(emojiTv + " Título da série: \u001b[3m" + top.get("title") + "\u001b[m");
            System.out.println("Poster da série: " + top.get("image"));
            double ratingDb = Double.parseDouble(top.get("imDbRating")); //convertendo String em Double
            int rate = (int) ratingDb; //convertendo Double em int
            System.out.println("Classificação " + emojiStar.repeat(rate) + "\n");
        }

        System.out.println("\u001b[1m===== Lista das Séries de Tv Mais Populares =====\u001b[m \n");
        for (Map<String,String> pop : popSeriesTv) {
            System.out.println(emojiTv + " Título da série: \u001b[3m" + pop.get("title") + "\u001b[m");;
            System.out.println("Poster da série: " + pop.get("image"));
            double ratingD = Double.parseDouble(pop.get("imDbRating")); //convertendo String em Double
            int rating = (int) ratingD; //convertendo Double em int
            System.out.println("Classificação: " + emojiStar.repeat(rating) + "\n");
        
        }

    }
}
