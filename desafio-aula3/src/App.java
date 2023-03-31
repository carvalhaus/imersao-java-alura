import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        API api = API.NASA;

        String url = api.getUrl();

        ExtratorConteudo extrator = api.getExtrator();
        
        
        ClientHttp clientHttp = new ClientHttp();
        String json = clientHttp.buscaDados(url);        
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // Exibindo dados da lista
        String emojiTelescope = Character.toString(0x1F52D);

        System.out.println("\u001b[1m===== Gerando Figurinhas dos Filmes =====\u001b[m \n");

        File path = new File("saida/");
        path.mkdir();
        
        GeradorFigurinhas geradora = new GeradorFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";
            
            System.out.println(emojiTelescope + " Título da imagem: \u001b[3m" + conteudo.titulo() + "\u001b[m \n");
            
            String textoFigurinha = "RECOMENDO!";
            InputStream stamp = new FileInputStream(new File("resource/stamp-approved.png"));
            
            geradora.criaFigurinhas(inputStream, nomeArquivo, textoFigurinha, stamp);
        }

    }
}