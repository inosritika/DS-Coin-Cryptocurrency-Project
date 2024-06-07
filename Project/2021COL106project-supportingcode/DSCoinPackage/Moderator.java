package DSCoinPackage;

import HelperClasses.Pair;

public class Moderator
 {

  public void initializeDSCoin(DSCoin_Honest DSObj, int coinCount) {
     int n = DSObj.memberlist.length;
     int num = 1;
     int k = 0 , t =0;
     Members moderator = new Members();
     moderator.UID = "Moderator";
     Transaction [] trs = new Transaction[coinCount];

      while(num<=coinCount) {
          t = 1;
          while (t <= n && num <= coinCount) {
              String s = Integer.toString(num+100000-1);
              trs[k] = new Transaction();
              trs[k].coinID = s;
              trs[k].coinsrc_block = null;
              trs[k].Destination = DSObj.memberlist[t-1];
              trs[k].Source = moderator;
              DSObj.memberlist[t-1].mycoins.add(new Pair<>(s, null));
              if (num == coinCount) {
                  DSObj.latestCoinID = s;
              }
              k++;
              t++;
              num++;
          }
      }
     k=0;
     Transaction [] trs_new = new Transaction[DSObj.bChain.tr_count];
     for(int i =0;i<coinCount/DSObj.bChain.tr_count;i++){
         for(int j =0;j<DSObj.bChain.tr_count;j++){
             trs_new[j] = new Transaction();
             trs_new[j] = trs[k];
             k++;
         }
        TransactionBlock blk = new TransactionBlock(trs_new) ;
         DSObj.bChain.InsertBlock_Honest(blk);
     }

  }
    
  public void initializeDSCoin(DSCoin_Malicious DSObj, int coinCount) {
//Same as above
//      int n = DSObj.memberlist.length;
//      int num;
//      int k = 0;
//      Members moderator = new Members();
//      moderator.UID = "Moderator";
//      Transaction [] trs = new Transaction[coinCount];
//      for(int i =0 ; i < n ; i++){
//          int j = i;
//          num=100000+i;
//          while(j<=coinCount){
//              j=j+n;
//              String s = Integer.toString(num);
//              trs[k].coinID = s;
//              trs[k].coinsrc_block=null;
//              trs[k].Destination=DSObj.memberlist[i];
//              trs[k].Source = moderator;
//              DSObj.memberlist[i].mycoins.add(new Pair<>(s,null));
//              if(num==coinCount){
//                  DSObj.latestCoinID = s;
//              }
//              k++;
//              num+=j;
//          }
//      }
//      k=0;
//      Transaction [] trs_new = new Transaction[DSObj.bChain.tr_count];
//      for(int i =0;i<coinCount/DSObj.bChain.tr_count;i++){
//          for(int j =0;j<DSObj.bChain.tr_count;j++){
//              trs_new[j] = trs[k];
//              k++;
//          }
//          TransactionBlock blk = new TransactionBlock(trs_new) ;
//          DSObj.bChain.InsertBlock_Malicious(blk);
//      }
      int n = DSObj.memberlist.length;
      int num = 0;
      int k = 0 , t =0;
      Members moderator = new Members();
      moderator.UID = "Moderator";
      Transaction [] trs = new Transaction[coinCount];

      while(num<coinCount) {
          t = 1;
          while (t <= n && num < coinCount) {
              String s = Integer.toString(num+100000);
              trs[k] = new Transaction();
              trs[k].coinID = s;
              trs[k].coinsrc_block = null;
              trs[k].Destination = DSObj.memberlist[t-1];
              trs[k].Source = moderator;
              DSObj.memberlist[t-1].mycoins.add(new Pair<>(s, null));
              if (num == coinCount) {
                  DSObj.latestCoinID = s;
              }
              k++;
              t++;
              num++;
          }
      }
      k=0;
      Transaction [] trs_new = new Transaction[DSObj.bChain.tr_count];
      for(int i =0;i<coinCount/DSObj.bChain.tr_count;i++){
          for(int j =0;j<DSObj.bChain.tr_count;j++){
              trs_new[j] = trs[k];
              k++;
          }
          TransactionBlock blk = new TransactionBlock(trs_new) ;
          DSObj.bChain.InsertBlock_Malicious(blk);
      }

  }
}
