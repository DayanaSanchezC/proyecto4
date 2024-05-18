public class Simbolo {
    private String lexema;
    private String token;
    private String valor;
    private String ambito;


    public Simbolo(String lexema, String token, String valor,
            String ambito) {
        this.lexema = lexema;
        this.token = token;
        this.valor = valor;
        this.ambito = ambito;
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


    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }


    public String getAmbito() {
        return ambito;
    }


    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
    
    public String toString() {
        return lexema + "," + token + "," + valor + "," + ambito;
    }
}