import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorFigurinhas{
    
    
    
    public void criaFigurinhas(InputStream inputStream, String nomeArquivo) throws Exception {
        
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //Image imagemMenor = imagemOriginal.getScaledInstance(600, 835, Image.SCALE_SMOOTH);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        // copiar a imagem original pra nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte
        Font fontComic = Font.createFont(Font.TRUETYPE_FONT,
            new File("fonts/ComicNeue-Bold.ttf"))
            .deriveFont(Font.BOLD, 66);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(fontComic);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        new TextLayout(null, fontRenderContext)


        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 185, novaAltura - 80);

        // escrever a nova imagem em um arquivo

        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}
