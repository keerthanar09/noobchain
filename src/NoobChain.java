import java.util.ArrayList;
import com.google.gson.GsonBuilder;  //to view blocks in a JSON structure.

public class NoobChain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;


    // Only the LONGEST VALID CHAIN is accepted in the network. If chain is invalid, that chain was tampered with.
    public static Boolean isChainValid(){
        Block currentBlock;
        Block prevBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i=1;i<blockchain.size();i++){
            currentBlock = blockchain.get(i);
            prevBlock = blockchain.get(i-1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hash not equal");
                return false;
            }
            if (!prevBlock.hash.equals(currentBlock.prevHash)){
                System.out.println("Previous hashes not equal");
                return false;
            }
            if (!currentBlock.hash.substring(0,difficulty).equals(hashTarget))
            {
                System.out.println("This block hasn't been mined.");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        blockchain.add(new Block("Hello, this is my first secret message", "0"));
        System.out.println("Trying to mine block 1...");
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("Hello, this is the second secret message.", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 2...");
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("Hello, this is the third secret message.", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 3...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid" + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }
}
