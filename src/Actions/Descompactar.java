/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Admin
 */
public class Descompactar {

    private static final String BARRA = "/";
    private static final String BARRA_INVERTIDA = "\\";
    private static final int TAMANHO_BUFFER = 512000;

    public void descompacta(String origem, String destino) throws IOException {
        File file = new File(destino + "\\restoration");
        if (!file.exists()) {
            file.mkdir();
        }
        FileInputStream fis = new FileInputStream(origem);
        ZipInputStream zis = new ZipInputStream(fis);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ZipEntry ze = null;
        String name = null;
        while ((ze = zis.getNextEntry()) != null) {
            if (ze.getName().indexOf("sql") != -1) {
                name = destino + BARRA_INVERTIDA + ze.getName();
                try {
                    fos = new FileOutputStream(name);
                } catch (FileNotFoundException exc) {
                    montaDiretorio(name);
                    fos = new FileOutputStream(name);
                }
                bos = new BufferedOutputStream(fos, TAMANHO_BUFFER);
                int bytes;
                byte buffer[] = new byte[TAMANHO_BUFFER];
                while ((bytes = zis.read(buffer, 0, TAMANHO_BUFFER)) != -1) {
                    bos.write(buffer, 0, bytes);
                }
                bos.flush();
                bos.close();
            }
        }
        zis.close();
    }

    private void montaDiretorio(String nome) throws IOException {
        File f;
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(nome, BARRA_INVERTIDA);
        int tokens = st.countTokens() - 1;
        for (int i = 0; i < tokens; i++) {
            sb.append(st.nextToken() + BARRA_INVERTIDA);
        }
        f = new File(sb.toString());
        f.mkdirs();
    }
}
