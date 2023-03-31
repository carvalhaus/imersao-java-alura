public enum API {

    IMDP_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorConteudoImdb()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-01&end_date=2023-03-29", new ExtratorConteudoNasa()),
    LINGUAGENS("http://localhost:8080/linguagens", new ExtratorConteudoImdb());

    private String url;
    private ExtratorConteudo extrator;

    API(String url, ExtratorConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public ExtratorConteudo getExtrator() {
        return extrator;
    }

    public String getUrl() {
        return url;
    }
}
