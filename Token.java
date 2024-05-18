public class Token {
    private String lexema;
    private String token;
    private String pts;
    private String linea;

    public Token(String lexema, String token, String pts, String linea) {
        this.lexema = lexema;
        this.token = token;
        this.pts = pts;
        this.linea = linea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    @Override
    public String toString() {
        return lexema + "," + token + "," + pts + "," + linea;
    }
}


