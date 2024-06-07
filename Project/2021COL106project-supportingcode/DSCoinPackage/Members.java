package DSCoinPackage;

import java.util.*;

import HelperClasses.CRF;
import HelperClasses.MerkleTree;
import HelperClasses.Pair;
import HelperClasses.TreeNode;//imported
import jdk.nashorn.internal.ir.Block;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

public class Members
 {

  public String UID;
  public List<Pair<String, TransactionBlock>> mycoins;
  public Transaction[] in_process_trans;

  public void initiateCoinsend(String destUID, DSCoin_Honest DSobj)  {

     String coin_id = this.mycoins.get(0).first;
     TransactionBlock trs_blk = this.mycoins.get(0).second;
     this.mycoins.remove(0);
     Transaction trs = new Transaction();
     trs.coinsrc_block = trs_blk;
     trs.coinID = coin_id;
     trs.Source = this;
     for(int i = 0;i<DSobj.memberlist.length;i++){
         if(DSobj.memberlist[i].UID.equals(destUID)){
             trs.Destination=DSobj.memberlist[i];
             break;
         }
     }
     Transaction [] new_in_process = new Transaction[this.in_process_trans.length];
     int idx = 0;
     for(int i =0;i<this.in_process_trans.length;i++){
         if(this.in_process_trans[i]==null){
             idx = i;
         }
         new_in_process [i] = this.in_process_trans[i];
     }
     new_in_process[idx] = trs;
     this.in_process_trans = new_in_process;
     DSobj.pendingTransactions.AddTransactions(trs);
  }

  public void initiateCoinsend(String destUID, DSCoin_Malicious DSobj) {//copy upar wala
      if(this.mycoins.size()==0){
          System.out.println("Empty my coin");
          return ;
      }
      String coin_id = this.mycoins.get(0).first;
//      System.out.println("COIN ID "+coin_id);
      TransactionBlock trs_blk = this.mycoins.get(0).second;
      this.mycoins.remove(0);
      Transaction trs = new Transaction();
      trs.coinID = coin_id;
      trs.coinsrc_block = trs_blk;
      trs.Source = this;
      Members arr[] = DSobj.memberlist;
      for(int i = 0;i<arr.length;i++){
          if(arr[i].UID==destUID){
              trs.Destination=arr[i];
          }
      }
      Transaction [] new_in_process = new Transaction[1+this.in_process_trans.length];
      for(int i =0;i<this.in_process_trans.length;i++){
          new_in_process [i] = this.in_process_trans[i];
      }
      new_in_process[this.in_process_trans.length]=trs;
      this.in_process_trans = new_in_process;
      DSobj.pendingTransactions.AddTransactions(trs);
  }
  public List<Pair<String, String>> path_to_root(MerkleTree mtree,int doc_idx) {
  ArrayList<Pair<String, String>> pathToRoot = new ArrayList<>();
  TreeNode curr = mtree.rootnode;
  int n = mtree.numdocs;
  int k = doc_idx;
  boolean initial = true;
  while (n >= 2) {
      if (initial) {
          pathToRoot.add(0, new Pair<String, String>(curr.val, null));
          initial = false;
      }
      pathToRoot.add(0, new Pair<String, String>(curr.left.val, curr.right.val));
      if ((n / 2) < k) {
          curr = curr.right;
          n = n / 2;
          k = k - n;
      } else {
          curr = curr.left;
          n = n / 2;
      }
  }
  return pathToRoot;
    }

  public Pair<List<Pair<String, String>>, List<Pair<String, String>>> finalizeCoinsend (Transaction tobj, DSCoin_Honest DSObj) throws MissingTransactionException {
      System.out.println("finalise coinsend");
      TransactionBlock blk = DSObj.bChain.lastBlock;
      int flag =0;
      int indx =1;
      while(blk!=null) {
          Transaction[] arr = blk.trarray;
          for(int i = 0;i<arr.length;i++){
              if(arr[i].coinID.equals(tobj.coinID)){
                  indx += i;//check
                  flag = 1;
                  break;
              }
          }
          if(flag==1){
              break;
          }
          blk=blk.previous;
      }
      if(flag==0){
          throw new MissingTransactionException();
      }else{
         List<Pair<String,String>> l1;
         blk.Tree.numdocs = blk.trarray.length;
         l1 = path_to_root(blk.Tree,indx);//check build
         List<Pair<String,String>> l2 = new ArrayList<>();
        TransactionBlock blk_nw = DSObj.bChain.lastBlock;
             CRF obj = new CRF(64);
         while(blk!=blk_nw){
             l2.add(0,new Pair<String,String>(blk_nw.dgst,obj.Fn(blk_nw.previous.dgst+"#"+blk_nw.trsummary+"#"+blk_nw.nonce)));
             blk_nw = blk_nw.previous;
         }
         if(blk.previous==null) System.out.println("BLK PREVIOUS IS NULL");
       else{  l2.add(0,new Pair<String, String>(blk.dgst, obj.Fn(blk.previous.dgst+"#"+blk.trsummary+"#"+blk.nonce)));
         l2.add(0,new Pair<String, String>(blk.previous.dgst, null));}
          Transaction [] new_in_process = new Transaction[this.in_process_trans.length];
          int k =0;
          for(int i = 0;i<this.in_process_trans.length;i++){
              new_in_process[i]= new Transaction();
              new_in_process[i]= null;
//              if(this.in_process_trans[i]==tobj){
              if(this.in_process_trans[i]!=null){
                  System.out.println("IN PROCESS IS NOT NULL");
              }
              if(this.in_process_trans[i].coinID.equals(tobj.coinID)&&this.in_process_trans[i]!=null){
                  k++;
                  continue;
              }
              if(k<this.in_process_trans.length)
              { new_in_process [i] = this.in_process_trans[k];}
              k++;
          }
          this.in_process_trans = new_in_process;
          int j = 0;
          for( int i =0;i<tobj.Destination.mycoins.size();i++){
              if(Integer.parseInt(tobj.Destination.mycoins.get(i).first)>Integer.parseInt(tobj.coinID)){
                  break;
              }
              j++;
          }
          tobj.Destination.mycoins.add(j , new Pair<>(tobj.coinID,blk));
          return new Pair<>(l1, l2);
      }
  }
public boolean checkTransaction_new(Transaction t,DSCoin_Honest DSObj){
      TransactionBlock coin_src = t.coinsrc_block;
      if(coin_src==null){
      return true;
      }
      TransactionBlock last = DSObj.bChain.lastBlock;
      int flag =0;
      for(int i =0;i<coin_src.trarray.length;i++){
          if(coin_src.trarray[i].equals(t.coinID)&&last.trarray[i].Destination==t.Source){
              flag = 1;
              break;
          }
      }
      if(flag==0){
          return false;
      }else{
          while(last!=coin_src){
              for(int i =0;i<last.trarray.length;i++){
                  if(last.trarray[i].equals(t.coinID)){
                      return  false;
                  }
              }
              last=last.previous;
          }
          return  true;
      }
}
  public void MineCoin(DSCoin_Honest DSObj) throws EmptyQueueException {
//        Transaction [] tr_arr = new Transaction[DSObj.bChain.tr_count];
//        tr_arr[0]=DSObj.pendingTransactions.RemoveTransaction();
//        for(int i = 1;i<DSObj.bChain.tr_count-1;i++){
//            tr_arr[i]= DSObj.pendingTransactions.RemoveTransaction();
//            for(int j=0;j<i;j++){
//                if(tr_arr[j].coinID.equals(tr_arr[i].coinID)){
//                   tr_arr[i]= DSObj.pendingTransactions.RemoveTransaction();
//            }

        Transaction [] tr_arr = new Transaction[DSObj.bChain.tr_count];
        Transaction t ;
        int i = 0;
        while(i<DSObj.bChain.tr_count-1){
        t = DSObj.pendingTransactions.RemoveTransaction();
        if(checkTransaction_new(t,DSObj)){
            tr_arr[i] = new Transaction();
            tr_arr[i]=t;
            i++;
        }
        for(int j=0;j<i-1;j++){
                if(tr_arr[j].coinID.equals(tr_arr[i-1].coinID)){
                    i--;
                }
        }
  }
        Transaction trs = new Transaction();
        String s = DSObj.latestCoinID;
        int num = Integer.parseInt(s);
        num++;
            trs.coinID = Integer.toString(num);
            DSObj.latestCoinID = trs.coinID;
            trs.Source = null;
            trs.Destination = this;//check
            tr_arr[DSObj.bChain.tr_count-1]=trs;
            TransactionBlock last = new TransactionBlock(tr_arr);
            trs.coinsrc_block = null;
            DSObj.bChain.InsertBlock_Honest(last);
            this.mycoins.add(new Pair<>(DSObj.latestCoinID,last));

  }
public boolean checkTransaction_mlc_new(Transaction t , DSCoin_Malicious DObj) {
      TransactionBlock tblk = DObj.bChain.FindLongestValidChain();
      TransactionBlock coin_src = t.coinsrc_block;
      int flag =0;
      while(tblk!=null) {
            if(tblk==coin_src) {
                flag = 1;
                break;
            }
            tblk=tblk.previous;
      }
      if(flag==0) {
        return false;
      }else {
          flag =0;
        for(int i = 0;i<coin_src.trarray.length;i++) {
            if(coin_src.trarray[i].coinID.equals(t.coinID)) {
                flag = 1;
                break;
            }
        }
        if(flag==0) {
            return  false;
        }
        else {
            tblk =  DObj.bChain.FindLongestValidChain();
            while(tblk!=coin_src) {
                for(int i = 0;i<tblk.trarray.length;i++) {
                    if(tblk.trarray[i].coinID.equals(t.coinID)) {
                        return false;
                    }
                    }
                tblk=tblk.previous;
            }
            return true;
        }
      }
}
  public void MineCoin(DSCoin_Malicious DSObj) throws EmptyQueueException {
//      Transaction [] tr_arr = new Transaction[DSObj.bChain.tr_count];
//      tr_arr[0]=DSObj.pendingTransactions.RemoveTransaction();
//      for(int i =1;i<DSObj.bChain.tr_count-1;i++){
//          Transaction t =  DSObj.pendingTransactions.RemoveTransaction();
//          tr_arr[i]= DSObj.pendingTransactions.RemoveTransaction();
//          for(int j=0;j<i;j++){
//              if(tr_arr[i]==tr_arr[j]){
//                  tr_arr[j]= DSObj.pendingTransactions.RemoveTransaction();
//              }
//          }
//          Transaction trs = new Transaction();
//          trs.coinID = DSObj.latestCoinID;
//          trs.Source = null;
//          trs.Destination = this;//check
//          trs.coinsrc_block = null;
//          TransactionBlock last = new TransactionBlock(tr_arr);
//          DSObj.bChain.InsertBlock_Malicious(last);//just changed this......get it checked :(
//          this.mycoins.add(new Pair<>(DSObj.latestCoinID,last));
      Transaction [] tr_arr = new Transaction[DSObj.bChain.tr_count];
      Transaction t ;
      int i = 0;
      while(i<DSObj.bChain.tr_count-1){
          t = DSObj.pendingTransactions.RemoveTransaction();
          if(checkTransaction_mlc_new(t,DSObj)){
              tr_arr[i] = new Transaction();
              tr_arr[i]=t;
              i++;
          }
          for(int j=0;j<i-1;j++){
              if(tr_arr[j].coinID.equals(tr_arr[i].coinID)){
                  i--;
              }
          }
          Transaction trs = new Transaction();
          String s = DSObj.latestCoinID;
          int num = Integer.parseInt(s);
          num++;
          trs.coinID = Integer.toString(num);
          DSObj.latestCoinID = trs.coinID;
          trs.Source = null;
          trs.Destination = this;//check
          trs.coinsrc_block = null;
          tr_arr[DSObj.bChain.tr_count-1]=trs;
          TransactionBlock last = new TransactionBlock(tr_arr);
          DSObj.bChain.InsertBlock_Malicious(last);
          this.mycoins.add(new Pair<>(DSObj.latestCoinID,last));

      }
  }
 }

