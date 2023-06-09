// classe criada na aula 2 da imersão

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorFigurinhas {
    
    public void criaFigurinhas(InputStream inputStream, String nomeArquivo) throws Exception {
        // leitura da imagem
        //BufferedImage imagemOriginal = ImageIO.read(new File("entrada/TopMovies_1.jpg")); lendo imagem através do path do arquivo
        // InputStream image = new FileInputStream(new File("entrada/TopMovies_1.jpg")); lendo imagem através do path com InputStream
        //InputStream image = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream(); lendo imagem com URL via InputStream
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        // copiar a imagem original pra nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 66);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 185, novaAltura - 80);

        // escrever a nova imagem em um arquivo
        //DESAFIO CRIAR DIRETÓRIO DE SAÍDA NO CÓDIGO
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}
