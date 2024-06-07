package DSCoinPackage;
public class TransactionQueue {

  public Transaction firstTransaction;
  public Transaction lastTransaction;
  public int numTransactions;

  public void AddTransactions (Transaction transaction) {
          Transaction trs = new Transaction();
          trs = transaction;
        if(this.numTransactions==0){
          this.firstTransaction = this.lastTransaction = trs;
          trs.prev = trs.next = null;
        }else{
            this.lastTransaction.next = trs;
            trs.prev = this.lastTransaction;
            this.lastTransaction = trs;
            trs.next = null;
        }
          this.numTransactions++;

  }
  
  public Transaction RemoveTransaction () throws EmptyQueueException {
    Transaction t ;
    if(numTransactions==0){
        throw new EmptyQueueException();
    }else if(numTransactions==1){
        t = this.firstTransaction;
        this.firstTransaction= this.lastTransaction=null;
        this.numTransactions--;
    }else{
        t = this.firstTransaction;
        this.firstTransaction = this.firstTransaction.next;
        this.firstTransaction.prev = null;
        this.numTransactions--;
    }
    return t;
  }

  public int size() {//directly return numTransactions
//    if(this.firstTransaction==null){
//        return 0;
//    }else if( this.firstTransaction==this.lastTransaction){
//        return 1;
//    }else{
//        Transaction t = this.firstTransaction;
//        int count = 0;
//        while(t!=null){
//            t=t.next;
//            count++;
//        }
//        return count;
  return this.numTransactions;
  }
  }

