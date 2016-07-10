package umec.EncriptPass;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptClass
{
    //шифрование пароля
    public static String md5(String input) 
    {
        String md5 = null;
         
        if(null == input) return null;
         
        try
        {
            //salt
            String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
            
            String mes = input + salt;
            
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(mes.getBytes(), 0, mes.getBytes().length);

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } 
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return md5;
    }
}
