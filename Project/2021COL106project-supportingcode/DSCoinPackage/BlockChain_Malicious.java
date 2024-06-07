package DSCoinPackage;

import HelperClasses.CRF;
import HelperClasses.MerkleTree;//imported

public class BlockChain_Malicious {

  public int tr_count;
  public static final String start_string = "DSCoin";
  public TransactionBlock[] lastBlocksList;

  public static boolean checkTransactionBlock (TransactionBlock tB) {
    CRF obj = new CRF(64);
    if(tB.dgst.substring(0,4).equals("0000")){
      return false;
    }
    if(tB.previous==null){
      if(!tB.dgst.equals(obj.Fn(start_string+"#"+tB.trsummary+"#"+tB.nonce))){
        return false;
      }
    }else if(!tB.dgst.equals(obj.Fn(tB.previous.dgst+"#"+tB.trsummary+"#"+tB.nonce))){
      return false;
    }
    MerkleTree mtree = new MerkleTree();
    String trsummary_new = mtree.Build(tB.trarray);
    if(!trsummary_new.equals(tB.trsummary)){
      return false;
    }
    for(int i =0;i<tB.trarray.length;i++){
      if(!tB.checkTransaction(tB.trarray[i])){
        return false;
      }
    }
    return true;
  }

  public TransactionBlock FindLongestValidChain () {
    int len = lastBlocksList.length;
    if(len==0){
      return null;
    }
    int [] count = new int[len];
    TransactionBlock[] arr = new TransactionBlock[len];
    for(int i=0;i<len;i++){
        TransactionBlock trs = this.lastBlocksList[i];
      if(trs==null){
//        System.out.println("LAST LIST IS NULL");
//        System.out.println("I IS "+i);
        arr[i] = new TransactionBlock(trs.trarray);
      }else{
        arr[i] = null;
      }
      count[i]=0;
      while(trs!=null){
        if(!checkTransactionBlock(trs)){
          count[i]=0;
          if(trs.previous!=null){
            arr[i]=trs.previous;
          }else{
            return null;
          }
        }else{
          count[i]++;
        }
        trs=trs.previous;
      }
    }
    int indx = 0;
    for(int i=0;i<len;i++){
     if(count[i]>count[indx]){
       indx = i;
     }
    }
    return arr[indx];
  }
//  public TransactionBlock FindLongestValidChain (){
//    int num
//  }
  public String find_nonce_new(String trsummry , String prevdgst){
    long num = 1000000001;
    CRF obj =  new CRF(64);
    String num_str = Long.toString(num);
    String dgst_new = obj.Fn(prevdgst+"#"+trsummry+"#"+num_str);
    while(dgst_new.substring(0,4).equals("0000")){
      num++;
      num_str = Long.toString(num);
      dgst_new = obj.Fn(prevdgst+"#"+trsummry+"#"+num_str);
    }
    return num_str;
  }
  public void InsertBlock_Malicious (TransactionBlock newBlock) {//DOUBT
//      TransactionBlock last_blk = new TransactionBlock(newBlock.trarray);
      TransactionBlock last_blk =newBlock;
      TransactionBlock prev = this.FindLongestValidChain();
//      last_blk.previous = prev;
      last_blk.previous = prev;

      CRF obj = new CRF(64);
      String s = find_nonce_new(last_blk.trsummary,prev.dgst);
      last_blk.dgst= obj.Fn(prev.dgst+"#"+last_blk.trsummary+"#"+s);
      last_blk.nonce = s;
  }
}
