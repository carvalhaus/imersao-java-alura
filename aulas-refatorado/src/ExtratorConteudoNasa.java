import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{
    
    public List<Conteudo> extraiConteudos(String json) {

        // Extraindo dados de interesse
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // Popular a lista de conteúdos
        for (Map<String, String> atributos : listaAtributos) {
            
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");

            Conteudo conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;

    }
}
