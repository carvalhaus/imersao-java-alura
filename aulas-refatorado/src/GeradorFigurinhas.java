import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorFigurinhas{
    
    
    
    public void criaFigurinhas(InputStream inputStream, String nomeArquivo, String texto, InputStream inputStream2) throws Exception {
        
        //leitura das imagens
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        
        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        // copiar a imagem original pra nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        BufferedImage stampRate = ImageIO.read(inputStream2);
        int posicaoStamp = (altura - stampRate.getHeight());
        graphics.drawImage(stampRate, 0, posicaoStamp, null);

        // configurar fonte
        Font fontComic = Font.createFont(Font.TRUETYPE_FONT,
            new File("resource/ComicNeue-Bold.ttf"))
            .deriveFont(Font.BOLD, 88);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(fontComic);

        
        
        // escrever uma frase na nova imagem
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retanguloTexto = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retanguloTexto.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = novaAltura - 80;
        graphics.drawString(texto, posicaoTextoX, posicaoTextoY );
        graphics.drawImage(novaImagem, altura, novaAltura, null);
        
        // outline da frase
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(texto, fontComic, fontRenderContext);
        
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoTextoX, posicaoTextoY);
        graphics.setTransform(transform);

        BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);
        
        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}