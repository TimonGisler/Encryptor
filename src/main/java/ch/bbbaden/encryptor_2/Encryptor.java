/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.encryptor_2;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author timon
 */
public class Encryptor {

    SecretKey Key;
    Cipher desCipher;
    String encryptingMethod = "DES";
    String stringKey;

    public String encrypt(String data) {
        try {

            //Generate KEy
            KeyGenerator keygenerator = KeyGenerator.getInstance(encryptingMethod);
            Key = keygenerator.generateKey();
            
           desCipher.init(Cipher.ENCRYPT_MODE, Key);

            //get bytes of Data, because String cannot be encoded
            byte[] dataInByte = data.getBytes("UTF8");

            //encode bytes
            byte[] textEncrypted = desCipher.doFinal(dataInByte);

            //convert SecreKey to String
            stringKey = secretKeyToString(Key);

            //convert bytes to String and use Base64 to be able to change it back to bytes
            String r = Base64.getEncoder().encodeToString(textEncrypted);

            return r;

        } catch (Exception e) {
            System.out.println("EXCEPTION AT METHOD: ENCRYPT");
            return null;
        }

    }

    public String decrypt(String data, String stringKey) {
        try {
            //Convert String to secretKey
            byte[] decodedKey = Base64.getDecoder().decode(stringKey);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, encryptingMethod);
            System.out.println("1");

            //Set Cypher to decryppt mode
            desCipher.init(Cipher.DECRYPT_MODE, originalKey);
            System.out.println("2");

            //Decode String Data into bytes
            byte[] dataInByte = Base64.getDecoder().decode(data);
            System.out.println("3");

            //Decrypt data with Cipher
            byte[] textDecrypted = desCipher.doFinal(dataInByte);
            System.out.println("4");

            String s = new String(textDecrypted);
            return s;

        } catch (Exception e) {
            System.out.println("EXCEPTION at METHOD: DECRYPT ");
            System.out.println(e);
            return null;
        }

    }

    public String getStringKey() {
        return stringKey;
    }

    public String secretKeyToString(SecretKey sKey) {
        // get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(sKey.getEncoded());
        return encodedKey;
    }

    public Encryptor() {
        try {
            desCipher = Cipher.getInstance(encryptingMethod);
        } catch (Exception e) {
        }
        
    }



}
