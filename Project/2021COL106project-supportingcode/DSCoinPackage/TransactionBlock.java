package DSCoinPackage;

import HelperClasses.MerkleTree;
import HelperClasses.CRF;

public class TransactionBlock {

  public Transaction[] trarray;
  public TransactionBlock previous;
  public MerkleTree Tree;
  public String trsummary;
  public String nonce;
  public String dgst;

  TransactionBlock(Transaction [] t) {
    Transaction [] t_new = new Transaction[t.length];
    for(int i=0;i<t.length;i++){
      t_new[i]=t[i];
    }
    this.trarray = t_new;
    this.previous = null;
    MerkleTree new_tree = new MerkleTree();
    this.Tree = new_tree;
    this.trsummary =  new_tree.Build(this.trarray);
    this.dgst = null;
    this.nonce =null;
  }

  public boolean checkTransaction (Transaction t)
  {
    int flag =0;
    Transaction t1 = null;
    if(t.coinsrc_block==null){
      return true;
    }
    for (Transaction trs : t.coinsrc_block.trarray) {
      if(trs.coinID.equals(t.coinID)){
        t1 = trs;
        flag=1;
        break;
      }
    }
    if(flag==0){ return false;}
    if(flag==1){
      if(t1.Destination!=t.Source){
        flag =0;
      }
    }
    if(flag==0){ return false;}
   else{
      TransactionBlock current = this.previous;
      while(current != t.coinsrc_block){
        for(Transaction trs : current.trarray){
          if(trs.coinID.equals(t.coinID)){
            flag =2;
            break;
          }
        }
        if(flag==2)break;
        current = current.previous;
      }
      if(flag==2){
        return false;
      }else{
        return true;
      }

    }
  }
}
