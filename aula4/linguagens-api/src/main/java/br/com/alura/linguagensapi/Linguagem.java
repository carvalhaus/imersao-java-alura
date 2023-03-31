package br.com.alura.linguagensapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "principaisLinguagens")
public class Linguagem {

    @Id
    private String id;
    private String title;
    private String image;
    private int raking;

    public Linguagem() {
        
    }
    
    public Linguagem(String title, String image, int raking) {
        this.title = title;
        this.image = image;
        this.raking = raking;
    }
    
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getRaking() {
        return raking;
    }

}
