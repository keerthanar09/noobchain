import java.util.Date;
public class Block {

    public String hash; //holds digital signature.
    public String prevHash;  //holds previous block's hash
    private String data;  //holds data, in this case, a simple message.
    private long timeStamp;
    private int nonce;


    public String calculateHash(){
        String calculatedhash = StringUtil.applySha256(
                prevHash + Long.toString(timeStamp) + Integer.toString(nonce) + data
        );
        return calculatedhash;
    }

    // difficulty is the number of 0s computers must solve for. Low difficulties can be solved instantly.
    // For testing, we can use 4-6 difficulty.
    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!! :"+hash);
    }


    //Block Constructor
    public Block(String data, String prevHash){
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
}
