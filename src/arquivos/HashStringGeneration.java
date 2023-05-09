package arquivos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashStringGeneration {
	public static String generateHash(String texto) throws NoSuchAlgorithmException {
        // Obtém uma instância do objeto MessageDigest para SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Calcula o hash da string de entrada
//        md.update(texto.getBytes());
//        byte[] hash = md.digest();
        byte[] hashBytes = md.digest(texto.getBytes());

        // Converte o hash em hexadecimal para exibição
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
	
//	public static void main(String[] args) {
//		try {
//			String hash = generateHash("Minha string para hash");
//			System.out.println("Hash SHA256 da string: " + hash);
//		} catch (NoSuchAlgorithmException e) {
//			System.err.println("Erro ao gerar hash: " + e.getMessage());
//		}
//	}
}
