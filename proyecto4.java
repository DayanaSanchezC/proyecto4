import java.io.*;
import java.util.*;
import javax.swing.*;

public class proyecto4 {
    public static void main(String[] args) {
        Stack<Token> Pilaej = new Stack<Token>();
        String nombreArchivo = seleccionarArchivo();
        Token[] tokens = procesarArchivo(nombreArchivo);
        Simbolo[] simbolos = archivoSimbolos();
        Direcciones[] direcciones = archivoDirecciones();
        int ipc = Integer.parseInt(direcciones[0].getvci());
        int pts;
        System.out.println("Pantalla");
        for (int pc = ipc; pc < tokens.length; pc++) {
            Token token = tokens[pc];
            if (token.getToken().equals("-4")) {
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-51"))) {
                    String input = JOptionPane
                            .showInputDialog("Ingrese el valor entero de la variable " + tokens[pc + 1].getLexema());
                    if (input != null && !input.isEmpty()) {
                        int variable = Integer.parseInt(input);
                        pts = Integer.parseInt(tokens[pc + 1].getPts());
                        simbolos[pts].setValor(String.valueOf(variable));
                    }
                }
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-52"))) {
                    String input = JOptionPane
                            .showInputDialog("Ingrese el valor real de la variable " + tokens[pc + 1].getLexema());
                    if (input != null && !input.isEmpty()) {
                        double variable = Double.parseDouble(input);
                        pts = Integer.parseInt(tokens[pc + 1].getPts());
                        simbolos[pts].setValor(String.valueOf(variable));
                    }
                }
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-53"))) {
                    String variable = JOptionPane
                            .showInputDialog("Ingrese el valor cadena de la variable " + tokens[pc + 1].getLexema());
                    if (variable != null && !variable.isEmpty()) {
                        pts = Integer.parseInt(tokens[pc + 1].getPts());
                        simbolos[pts].setValor(String.valueOf(variable));
                    }
                }
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-54"))) {
                    String input = JOptionPane
                            .showInputDialog("Ingrese el valor logico de la variable " + tokens[pc + 1].getLexema());
                    if (input != null && !input.isEmpty()) {
                        boolean variable = Boolean.parseBoolean(input);
                        pts = Integer.parseInt(tokens[pc + 1].getPts());
                        simbolos[pts].setValor(String.valueOf(variable));
                    }
                }

            } else if (token.getToken().equals("-5")) {
                if (pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-61"))
                    System.out.println(tokens[pc + 1].getLexema());
                if (pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-62"))
                    System.out.println(tokens[pc + 1].getLexema());
                if (pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-63"))
                    System.out.println(tokens[pc + 1].getLexema());
                if (pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-64"))
                    System.out.println(tokens[pc + 1].getLexema());
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-51"))) {
                    pts = Integer.parseInt(tokens[pc + 1].getPts());
                    System.out.println(simbolos[pts].getValor());
                }
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-52"))) {
                    pts = Integer.parseInt(tokens[pc + 1].getPts());
                    System.out.println(simbolos[pts].getValor());
                }
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-53"))) {
                    pts = Integer.parseInt(tokens[pc + 1].getPts());
                    System.out.println(simbolos[pts].getValor());
                }
                if ((pc < tokens.length - 1 && tokens[pc + 1].getToken().equals("-54"))) {
                    pts = Integer.parseInt(tokens[pc + 1].getPts());
                    System.out.println(simbolos[pts].getValor());
                }

            } else if (!esOp(token.getToken()) &&
                    !token.getToken().equals("-16") &&
                    !token.getToken().equals("-10") &&
                    !token.getToken().equals("-17") &&
                    !token.getToken().equals("-7") &&
                    !token.getLexema().equals("FIN-MIENTRAS") &&
                    !(pc > 0 && tokens[pc - 1].getToken().equals("-5")) &&
                    !(pc > 0 && tokens[pc - 1].getToken().equals("-4"))) {
                Pilaej.push(token);
            }
            if (esOperador(token.getToken()) != null) {
                if (esOperador(token.getToken()).equals("-43")) {
                    Token operando = Pilaej.pop();
                    boolean r = !Boolean.parseBoolean(operando.getLexema());
                    Pilaej.push(new Token(String.valueOf(r), null, null, null));
                    System.out.println(r);
                } else {
                    Token operador = token;
                    Token operando2 = Pilaej.pop();

                    Token operando1 = Pilaej.pop();
                    if (operando2.getPts() != null && !operando2.getPts().equals("-1")) {
                        pts = Integer.parseInt(operando2.getPts());
                        operando2 = new Token(simbolos[pts].getValor(), simbolos[pts].getToken(), operando2.getPts(),
                                null);
                    }
                    if (operando1.getPts() != null && !operando1.getPts().equals("-1")) {
                        pts = Integer.parseInt(operando1.getPts());
                        operando1 = new Token(simbolos[pts].getValor(), simbolos[pts].getToken(), operando1.getPts(),
                                null);
                    }
                    if ((operando1.getToken() != null && operando1.getToken().equals("-51")
                            || operando1.getToken() != null && operando1.getToken().equals("-61")
                            || operando1.getToken() == null)
                            && (operando2.getToken() != null && operando2.getToken().equals("-51")
                                    || operando2.getToken() != null && operando2.getToken().equals("-61")
                                    || operando2.getToken() == null)) {
                        switch (operador.getToken()) {
                            case "-21":
                                Pilaej.push(new Token(String.valueOf(Integer.parseInt(operando1.getLexema())
                                        * Integer.parseInt(operando2.getLexema())), operando1.getToken(), null, null));
                                break;
                            case "-22": {
                                if (!operando1.getLexema().equals("0")) {
                                    Pilaej.push(new Token(String.valueOf(Integer.parseInt(operando1.getLexema())
                                            / Integer.parseInt(operando2.getLexema())), operando1.getToken(), null,
                                            null));
                                } else {
                                    System.err.println("No se pueden realizar diviciones entre 0");
                                }
                                break;
                            }
                            case "-23":
                                Pilaej.push(new Token(String.valueOf(Integer.parseInt(operando1.getLexema())
                                        % Integer.parseInt(operando2.getLexema())), operando1.getToken(), null, null));
                                break;
                            case "-24":
                                Pilaej.push(new Token(String.valueOf(Integer.parseInt(operando1.getLexema())
                                        + Integer.parseInt(operando2.getLexema())), operando1.getToken(), null, null));
                                break;
                            case "-25":
                                Pilaej.push(new Token(String.valueOf(Integer.parseInt(operando1.getLexema())
                                        - Integer.parseInt(operando2.getLexema())), operando1.getToken(), null, null));
                                break;
                            case "-26": {
                                int op1;
                                int op2 = Integer.parseInt(operando2.getLexema());
                                op1 = op2;
                                pts = Integer.parseInt(operando1.getPts());
                                simbolos[pts].setValor(String.valueOf(op1));
                            }
                                break;
                            case "-31":
                                boolean r = false;
                                if (Integer.parseInt(operando1.getLexema()) < Integer.parseInt(operando2.getLexema()))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }

                                break;
                            case "-32":
                                r = false;
                                if (Integer.parseInt(operando1.getLexema()) <= Integer.parseInt(operando2.getLexema()))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }

                                break;
                            case "-33":
                                r = false;
                                if (Integer.parseInt(operando1.getLexema()) > Integer.parseInt(operando2.getLexema()))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }

                                break;
                            case "-34":
                                r = false;
                                if (Integer.parseInt(operando1.getLexema()) >= Integer.parseInt(operando2.getLexema()))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }

                                break;
                            case "-35":
                                r = false;
                                if (Integer.parseInt(operando1.getLexema()) == Integer.parseInt(operando2.getLexema()))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }

                                break;
                            case "-36":
                                r = false;
                                if (Integer.parseInt(operando1.getLexema()) != Integer.parseInt(operando2.getLexema()))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }

                            default:
                                System.err.println("Operador no válido: " + operador.getToken());

                        }
                    }
                    if ((operando1.getToken() != null && operando1.getToken().equals("-52")
                            || operando1.getToken() != null && operando1.getToken().equals("-62")
                            || operando1.getToken() == null)
                            && (operando2.getToken() != null && operando2.getToken().equals("-52")
                                    || operando2.getToken() != null && operando2.getToken().equals("-62")
                                    || operando2.getToken() == null)) {
                        switch (operador.getToken()) {
                            case "-21":
                                Pilaej.push(new Token(String.valueOf(Double.parseDouble(operando1.getLexema())
                                        * Double.parseDouble(operando2.getLexema())), null, null, null));
                                break;
                            case "-22":
                                if (operando1.getLexema().equals("0"))
                                    Pilaej.push(new Token(String.valueOf(Double.parseDouble(operando1.getLexema())
                                            / Double.parseDouble(operando2.getLexema())), null, null, null));
                                else
                                    System.err.println("No se pueden realizar diviciones entre 0");
                                break;
                            case "-23":
                                Pilaej.push(new Token(String.valueOf(Double.parseDouble(operando1.getLexema())
                                        % Double.parseDouble(operando2.getLexema())), null, null, null));
                                break;
                            case "-24":
                                Pilaej.push(new Token(String.valueOf(Double.parseDouble(operando1.getLexema())
                                        + Double.parseDouble(operando2.getLexema())), null, null, null));
                                break;
                            case "-25":
                                Pilaej.push(new Token(String.valueOf(Double.parseDouble(operando1.getLexema())
                                        - Double.parseDouble(operando2.getLexema())), null, null, null));

                                break;
                            case "-26":
                                double op1 = Double.parseDouble(operando1.getLexema());
                                double op2 = Double.parseDouble(operando2.getLexema());
                                op1 = op2;
                                pts = Integer.parseInt(operando1.getPts());
                                simbolos[pts].setValor(String.valueOf(op1));
                                break;
                            case "-31":
                                boolean r = false;
                                if (Double.parseDouble(operando1.getLexema()) < Double
                                        .parseDouble(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-32":
                                r = false;
                                if (Double.parseDouble(operando1.getLexema()) <= Double
                                        .parseDouble(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-33":
                                r = false;
                                if (Double.parseDouble(operando1.getLexema()) > Double
                                        .parseDouble(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-34":
                                r = false;
                                if (Double.parseDouble(operando1.getLexema()) >= Double
                                        .parseDouble(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-35":
                                r = false;
                                if (Double.parseDouble(operando1.getLexema()) == Double
                                        .parseDouble(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-36":
                                r = false;
                                if (Double.parseDouble(operando1.getLexema()) != Double
                                        .parseDouble(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            default:
                                System.err.println("Operador no válido: " + operador.getToken());
                                break;

                        }
                    }
                    if ((operando1.getToken() != null && operando1.getToken().equals("-54")
                            || operando1.getToken() != null && operando1.getToken().equals("-64")
                            || operando1.getToken() != null && operando1.getToken().equals("-65")
                            || operando1.getToken() == null)
                            && (operando2.getToken() != null && operando2.getToken().equals("-54")
                                    || operando2.getToken() != null && operando2.getToken().equals("-65")
                                    || operando2.getToken() != null && operando2.getToken().equals("-64")

                                    || operando2.getToken() == null)) {
                        switch (operador.getToken()) {
                            case "-26":
                                boolean op1 = Boolean.parseBoolean(operando1.getLexema());
                                boolean op2 = Boolean.parseBoolean(operando2.getLexema());
                                op1 = op2;
                                pts = Integer.parseInt(operando1.getPts());
                                simbolos[pts].setValor(String.valueOf(op1));
                                break;
                            case "-35":
                                boolean r = false;
                                if (operando1.getLexema().equals(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-36":
                                r = false;
                                if (!operando1.getLexema().equals(operando2.getLexema()))
                                    r = true;
                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-41":
                                r = false;
                                if (operando1.getToken().equals("-64")
                                        && operando2.getToken().equals("-64"))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            case "-42":
                                r = false;
                                if ((operando1.getToken().equals("-64"))
                                        || (operando2.getToken().equals("-64")))
                                    r = true;

                                if (r == true) {
                                    Pilaej.push(new Token(String.valueOf(r), "-64", null, null));
                                } else {
                                    Pilaej.push(new Token(String.valueOf(r), "-65", null, null));
                                }
                                break;
                            default:
                                System.err.println("Operador no válido: " + operador.getToken());
                                break;

                        }
                    }
                }

            }
            if (token.getToken().equals("-16") || token.getToken().equals("-10") || token.getToken().equals("-17")) {
                int pc_aux = Integer.parseInt(Pilaej.pop().getLexema());
                boolean vv = Boolean.parseBoolean(Pilaej.pop().getLexema());
                if (vv == true)
                    pc = pc++;
                else
                    pc = pc_aux - 1;
            } else if (token.getToken().equals("-7") || token.getLexema().equals("FIN-MIENTRAS")) {
                pc = Integer.parseInt(Pilaej.pop().getLexema()) - 1;
            }
        }
        sobreescribirArchivoSimbolos(simbolos);
    }

    private static String esOperador(String token) {
        switch (token) {
            case "-21":
            case "-22":
            case "-23":
            case "-24":
            case "-25":
            case "-26":
            case "-31":
            case "-32":
            case "-33":
            case "-34":
            case "-35":
            case "-36":
            case "-41":
            case "-42":
            case "-43":
                return token;
            default:
                return null;
        }
    }

    private static boolean esOp(String token) {
        switch (token) {
            case "-21":
            case "-22":
            case "-23":
            case "-24":
            case "-25":
            case "-26":
            case "-31":
            case "-32":
            case "-33":
            case "-34":
            case "-35":
            case "-36":
            case "-41":
            case "-42":
            case "-43":
                return true;
            default:
                return false;
        }
    }

    public static String seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    public static Token[] procesarArchivo(String nombreArchivo) {
        Token[] tokens = null;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numLineas = contarLineas(nombreArchivo);
            tokens = new Token[numLineas];
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 4);
                if (partes.length != 4) {
                    System.err.println("Error, se espera que este en el siguiente formato\n" +
                            "Lexema,Token,Posicion,Linea" + linea);
                    continue;
                }
                String lexema = partes[0].trim();
                String token = partes[1].trim();
                String pts = partes[2].trim();
                String lineaNum = partes[3].trim();
                tokens[i] = new Token(lexema, token, pts, lineaNum);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    public static Simbolo[] archivoSimbolos() {
        Simbolo[] simbolo = null;
        try (BufferedReader br = new BufferedReader(new FileReader("TS.txt"))) {
            String linea;
            int numLineas = contarLineas("TS.txt");
            simbolo = new Simbolo[numLineas];
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("[,\\s]+", 4);
                if (partes.length != 4) {
                    System.err.println("Error, se espera que este en el siguiente formato\n" +
                            "Lexema,Token,Posicion,Linea");
                    continue;
                }
                String lexema = partes[0].trim();
                String token = partes[1].trim();
                String valor = partes[2].trim();
                String ambito = partes[3].trim();
                simbolo[i] = new Simbolo(lexema, token, valor, ambito);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return simbolo;

    }

    public static Direcciones[] archivoDirecciones() {
        Direcciones[] direcciones = null;
        try (BufferedReader br = new BufferedReader(new FileReader("TD.txt"))) {
            String linea;
            int numLineas = contarLineas("TS.txt");
            direcciones = new Direcciones[numLineas];
            int i = 0;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("[,\\s]+", 4);
                if (partes.length != 4) {
                    System.err.println("Error, se espera que este en el siguiente formato\n" +
                            "Lexema,Token,Posicion,Linea");
                    continue;
                }
                String lexema = partes[0].trim();
                String token = partes[1].trim();
                String lineas = partes[2].trim();
                String vci = partes[3].trim();
                direcciones[i] = new Direcciones(lexema, token, lineas, vci);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return direcciones;

    }

    public static void sobreescribirArchivoSimbolos(Simbolo[] simbolos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("TS.txt"))) {
            for (Simbolo simbolo : simbolos) {
                bw.write(simbolo.getLexema()+"," +simbolo.getToken()+","+simbolo.getValor()+","+ simbolo.getAmbito());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int contarLineas(String nombreArchivo) throws IOException {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            while (reader.readLine() != null)
                lineCount++;
        }
        return lineCount;
    }

}
