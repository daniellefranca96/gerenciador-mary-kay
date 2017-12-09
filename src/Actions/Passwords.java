/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Danielle
 */
public class Passwords {

    private static String hexDigits = "0123456789abcdef";
    private static MessageDigest md = null;

    public static String encripta(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        return String.format("%32x", hash);
    }

    public String gerarSenha() {
        Random ran = new Random();
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z"};
        String senha = "";
        for (int i = 0; i < 3; i++) {
            int a = ran.nextInt(letras.length);
            senha += letras[a] + a;
        }
        return senha;
    }
}
