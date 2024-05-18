public class Direcciones {
    private String lexema;
    private String token;
    private String linea;
    private String vci;


    public Direcciones(String lexema, String token, String linea,
            String vci) {
        this.lexema = lexema;
        this.token = token;
        this.linea = linea;
        this.vci = vci;
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


    public String getLinea() {
        return linea;
    }


    public void setLinea (String linea) {
        this.linea = linea;
    }


    public String getvci() {
        return vci;
    }


    public void setvci(String vci) {
        this.vci = vci;
    }
    
    public String toString() {
        return lexema + "," + token + "," + linea + "," + vci;
    }
}