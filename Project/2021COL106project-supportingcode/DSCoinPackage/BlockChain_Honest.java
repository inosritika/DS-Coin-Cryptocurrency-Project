package DSCoinPackage;

import HelperClasses.CRF;

public class BlockChain_Honest {

  public int tr_count;
  public static final String start_string = "DSCoin";
  public TransactionBlock lastBlock;

  public String find_nonce(String trsummry , String prevdgst){
      long num = 1000000001;
      CRF obj =  new CRF(64);
      String num_str = Long.toString(num);
      String dgst_new = obj.Fn(prevdgst+"#"+trsummry+"#"+num_str);
      while(!dgst_new.substring(0,4).equals("0000")){
          num++;
          num_str = Long.toString(num);
          dgst_new = obj.Fn(prevdgst+"#"+trsummry+"#"+num_str);
      }
      return num_str;
  }
  public void InsertBlock_Honest (TransactionBlock newBlock) {
//    TransactionBlock new_blk = new TransactionBlock(newBlock.trarray);
    CRF obj = new CRF(64);
 if(this.lastBlock==null){
//    this.lastBlock = new_blk;
//    new_blk.previous = null;
//    new_blk.nonce= find_nonce(new_blk.trsummary,"DSCoin");
//    new_blk.dgst = obj.Fn(start_string+"#"+new_blk.trsummary+"#"+new_blk.nonce);
    this.lastBlock =  newBlock;
     newBlock.previous = null;
     newBlock.nonce= find_nonce( newBlock.trsummary,"DSCoin");
     newBlock.dgst = obj.Fn(start_string+"#"+ newBlock.trsummary+"#"+ newBlock.nonce);
 }else{
     newBlock.previous = this.lastBlock;
     this.lastBlock =  newBlock;
     newBlock.nonce = find_nonce( newBlock.trsummary, newBlock.previous.dgst);
     newBlock.dgst = obj.Fn( newBlock.previous.dgst+"#"+ newBlock.trsummary+"#"+ newBlock.nonce);
//     new_blk.previous = this.lastBlock;
//     this.lastBlock = new_blk;
//     new_blk.nonce = find_nonce(new_blk.trsummary,new_blk.previous.dgst);
//     new_blk.dgst = obj.Fn(new_blk.previous.dgst+"#"+new_blk.trsummary+"#"+new_blk.nonce);
     }
  }

}
