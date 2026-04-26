// Utility class StringUtil contains a helper method to help use SHA256 algorithm later.

import java.security.MessageDigest;

// input - data of a block that needs to be hashed
// SHA-256 - Algorithm used to perform hashing and obtain a digital signature
public class StringUtil {
    public static String applySha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0;i<hash.length;i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length()==1) hexString.append('0');
                hexString.append(hex);

            }
            return hexString.toString();  //Returns generated digital signature as a string
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
