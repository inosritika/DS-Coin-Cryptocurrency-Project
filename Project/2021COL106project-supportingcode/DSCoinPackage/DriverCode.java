package DSCoinPackage;
import java.time.Duration;
import java.time.Instant;
import DSCoinPackage.*;
import HelperClasses.*;
import java.util.*;
public class DriverCode {

    public static String printMember (Members m){
        String s = "Member Name : ";
        s += m.UID + "\n Member Coins \n";
        for (Pair<String, TransactionBlock> p : m.mycoins){
            s += p.first + " ";
        }
        s += "\n";
        return s;
    }
    public static String printBlock (TransactionBlock b){
        String s = "Block Summary: ";
        s += b.trsummary + "\n Nonce ";
        if (b.nonce != null){
            s += b.nonce + " \n dgst ";
        }
        else {
            s += "null \n dgst ";
        }
        if (b.dgst != null){
            s += b.dgst + "\n";
        }
        else {
            s += "null \n";
        }
        return s;
    }
    public static String printBlockChain (BlockChain_Honest c){
        String s = "Block Chain Honest \n ";
        TransactionBlock b = c.lastBlock;
        for (; b != null; b = b.previous){
            s += printBlock(b) + "\n";
        }
        return s;
    }
    public static String printBlockChain (BlockChain_Malicious c){
        String s = "Block Chain Malicious \n";
        List<TransactionBlock> l = new ArrayList<>();
        for (int i = 0; i < c.lastBlocksList.length; i ++){
            if (c.lastBlocksList[i] != null){
                l.add(c.lastBlocksList[i]);
            }
        }
        for (int i = 1; i < l.size(); i ++){
            for (int j = i; j > 0 && l.get(j).trsummary.compareTo(l.get(j-1).trsummary) <= 0; j --){
                TransactionBlock temp = l.get(j);
                l.set(j, l.get(j-1));
                l.set(j-1, temp);
            }
        }
        for (int i = 0; i < l.size(); i ++){
            for (TransactionBlock b = l.get(i); b != null; b = b.previous){
                s += printBlock(b) + "\n";
            }
        }
        return s;
    }

    public static String printDSObj (DSCoin_Honest o){
        String s = "Honest Obj latest coin : ";
        s += o.latestCoinID + " \n Block Chain : \n";
        s += printBlockChain(o.bChain) + "memberlist \n";
        for (int i = 0; i < o.memberlist.length; i ++){
            s += printMember(o.memberlist[i]);
        }
        return s;
    }

    public static String printDSObj (DSCoin_Malicious o){
        String s = "Malicious Obj latest coin : ";
        s += o.latestCoinID + " \n Block Chain : \n";
        s += printBlockChain(o.bChain) + "memberlist \n";
        for (int i = 0; i < o.memberlist.length; i ++){
            s += printMember(o.memberlist[i]);
        }
        return s;
    }

    public static void main(String args[]) {
        long start2 = System.currentTimeMillis();
        System.out.println("Running Code ......");
        DSCoin_Honest DSObj = new DSCoin_Honest();
        DSObj.pendingTransactions = new TransactionQueue();
        DSObj.bChain = new BlockChain_Honest();
        Boolean correct = true;
        DSObj.bChain.tr_count = 4;
        Members m1 = new Members();
        Members m2 = new Members();
        Members m3 = new Members();
        Members m4 = new Members();
        m1.UID = "101";
        m2.UID = "102";
        m3.UID = "103";
        m4.UID = "104";
        m1.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        m2.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        m3.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        m4.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        m1.in_process_trans = new Transaction[100];
        m2.in_process_trans = new Transaction[100];
        m3.in_process_trans = new Transaction[100];
        m4.in_process_trans = new Transaction[100];
        DSObj.memberlist = new Members[4];
        DSObj.memberlist[0] = m1;
        DSObj.memberlist[1] = m2;
        DSObj.memberlist[2] = m3;
        DSObj.memberlist[3] = m4;

        Moderator mod = new Moderator();
        try {
            mod.initializeDSCoin(DSObj, 8);
        } catch (Exception e) {
            System.out.println(e);
        }

        m1.initiateCoinsend("102", DSObj);
        m3.initiateCoinsend("102", DSObj);
        m3.initiateCoinsend("102", DSObj);
        Pair<List<Pair<String, String>>,List<Pair<String, String>>> temp = new Pair<List<Pair<String, String>>, List<Pair<String, String>>>(null,null);
        try {
            m2.MineCoin(DSObj);
            temp = m1.finalizeCoinsend(DSObj.bChain.lastBlock.trarray[0],DSObj);
            temp = m3.finalizeCoinsend(DSObj.bChain.lastBlock.trarray[2],DSObj);
        } catch (Exception e) {
        }

        Transaction fake = new Transaction();
        fake.Source = m1;
        fake.Destination = m3;
        fake.coinsrc_block = DSObj.bChain.lastBlock;
        fake.coinID = "100000";
        DSObj.pendingTransactions.AddTransactions(fake);
        m2.initiateCoinsend("101", DSObj);
        m2.initiateCoinsend("101", DSObj);
        m4.initiateCoinsend("101", DSObj);

        Transaction tm = new Transaction();
        Transaction[] array = new Transaction[DSObj.bChain.tr_count];
        try {
            /*for (int i = 0; i < DSObj.bChain.tr_count; i++) {
                try {
                    tm = DSObj.pendingTransactions.RemoveTransaction();
                } catch (EmptyQueueException e) {
                    e.printStackTrace();
                    break;
                }
                if (Members.isValid(tm,array)) {
                    System.out.println();
                    array[i] = tm;
                } else {
                    System.out.println("Transaction " + tm.coinID + " is invalid");
                }
            }*/
            m3.MineCoin(DSObj);
//            for (int i = 0; i < DSObj.bChain.lastBlock.trarray.length ; i++) {
//                System.out.println("Priting for element " + i);
//                System.out.println(DSObj.bChain.lastBlock.trarray[i].coinID);
//                if (DSObj.bChain.lastBlock.trarray[i].Source != null) {
//                    System.out.println(DSObj.bChain.lastBlock.trarray[i].Source.UID);
//                } else {
//                    System.out.println("null");
//                }
//                System.out.println(DSObj.bChain.lastBlock.trarray[i].Destination.UID);
//                if (DSObj.bChain.lastBlock.trarray[i].coinsrc_block != null) {
//                    System.out.println(DSObj.bChain.lastBlock.trarray[i].coinsrc_block.dgst);
//                } else {
//                    System.out.println("null");
//                }
//            }
            Pair<List<Pair<String, String>>,List<Pair<String, String>>> temp1 = m2.finalizeCoinsend(DSObj.bChain.lastBlock.trarray[0], DSObj);
            temp1 = m2.finalizeCoinsend(DSObj.bChain.lastBlock.trarray[1], DSObj);
            temp1 = m4.finalizeCoinsend(DSObj.bChain.lastBlock.trarray[2], DSObj);
            temp = m3.finalizeCoinsend(DSObj.bChain.lastBlock.previous.trarray[1],DSObj);
        } catch (Exception e) {
            e.printStackTrace();
        }


        correct &= DSObj.bChain.lastBlock.dgst.equals("00004F4D8749BFFE9E1BEF4152F6849BCD0A529B463FF0D409B338AFABF690C8");
        
        try {
            Transaction tobj = new Transaction();
            tobj.Source = m3;
            tobj.Destination = m2;
            tobj.coinID = "100002";
            Pair<List<Pair<String, String>>,List<Pair<String, String>>> p = temp;

            for(int i = 0; i<p.get_first().size(); i++){
                System.out.println(p.get_first().get(i).get_first() + " " + p.get_first().get(i).get_second());
            }

            for(int i = 0; i<p.get_second().size(); i++){
                System.out.println(p.get_second().get(i).get_first() + " " + p.get_second().get(i).get_second());
            }

            correct &= p.get_first().get(0).get_first().equals("3A767231549E643B899B511E0BB1BD438C08A47D0E145B3A77CAFC4E5EF91DEF");
            correct &= p.get_first().get(1).get_first().equals("A86B49EA9F5245DEA4949D3815140D8CF5B1127C6EBB9B54197D87A5B0579C11");
            correct &= p.get_first().get(2).get_first().equals("036462EC76F2BEDA0CE4822E8747FEC9B69671359E7E3D2BD96485748360FD62");
            correct &= p.get_first().get(0).get_second().equals("B8444CE1F8BD43465F42E29A6E7335A29A12EAF96D4249E710098EE52A2027F2");
            correct &= p.get_first().get(1).get_second().equals("3A0E5A405EFB47DB8EB53FEF60C428FD85964E1C2E090DBC87000FEF02A04F0D");
            
            correct &= p.get_second().get(0).get_first().equals("00001DBCB949AF5097FE4F2D4CF1F545624ED5646931466170FCFB771D584D9C");
            correct &= p.get_second().get(1).get_first().equals("00009A7F99D2D09244E99D1F55AD29B49D335D9254A2EC682341ECFBC905AF4C");
            correct &= p.get_second().get(2).get_first().equals("00004F4D8749BFFE9E1BEF4152F6849BCD0A529B463FF0D409B338AFABF690C8");
            correct &= p.get_second().get(1).get_second().equals("00001DBCB949AF5097FE4F2D4CF1F545624ED5646931466170FCFB771D584D9C#036462EC76F2BEDA0CE4822E8747FEC9B69671359E7E3D2BD96485748360FD62#1000052544");
            correct &= p.get_second().get(2).get_second().equals("00009A7F99D2D09244E99D1F55AD29B49D335D9254A2EC682341ECFBC905AF4C#1CE221942486B63BD73A3D89082EF56FB5F0B2DB6B0D87EA8D81557B8A26D4AD#1000188337");
            
            if(correct == true){
                System.out.println("All test cases pass!");
            }
            else{
                System.out.println("Some test case is incorrect!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        DSCoin_Malicious DSObj2 = new DSCoin_Malicious();
        DSObj2.pendingTransactions = new TransactionQueue();
        DSObj2.bChain = new BlockChain_Malicious();
        DSObj2.bChain.lastBlocksList = new TransactionBlock[100];
        Boolean correct2 = true;
        DSObj2.bChain.tr_count = 8;
        Members M1 = new Members();
        Members M2 = new Members();
        Members M3 = new Members();
        Members M4 = new Members();
        Members M5 = new Members();
        Members M6 = new Members();
        M1.UID = "201";
        M2.UID = "202";
        M3.UID = "203";
        M4.UID = "204";
        M5.UID = "205";
        M6.UID = "206";
        M1.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        M2.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        M3.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        M4.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        M5.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        M6.mycoins = new ArrayList<Pair<String,TransactionBlock>>();
        M1.in_process_trans = new Transaction[100];
        M2.in_process_trans = new Transaction[100];
        M3.in_process_trans = new Transaction[100];
        M4.in_process_trans = new Transaction[100];
        M5.in_process_trans = new Transaction[100];
        M6.in_process_trans = new Transaction[100];
        DSObj2.memberlist = new Members[6];
        DSObj2.memberlist[0] = M1;
        DSObj2.memberlist[1] = M2;
        DSObj2.memberlist[2] = M3;
        DSObj2.memberlist[3] = M4;
        DSObj2.memberlist[4] = M5;
        DSObj2.memberlist[5] = M6;
        
        // try {
        //     mod.initializeDSCoin(DSObj2, 24);
        // } catch (Exception e) {
        //     System.out.println(e);
        // }

        mod.initializeDSCoin(DSObj2, 24);

        System.out.println(printDSObj(DSObj2));

//        System.out.println(printDSObj(DSObj2));

//        TransactionBlock tB1 = DSObj2.bChain.lastBlocksList[0];
//        while (tB1 != null) {
//            for (int i = 0; i < tB1.trarray.length; i++) {
//                System.out.println("Priting for element " + i);
//                System.out.println(tB1.trarray[i].coinID);
//                if (tB1.trarray[i].Source != null) {
//                    System.out.println(tB1.trarray[i].Source.UID);
//                } else {
//                    System.out.println("null");
//                }
//                System.out.println(tB1.trarray[i].Destination.UID);
//                if (tB1.trarray[i].coinsrc_block != null) {
//                    System.out.println(tB1.trarray[i].coinsrc_block.dgst);
//                } else {
//                    System.out.println("null");
//                }
//            }
//            tB1 = tB1.previous;
//        }

        System.out.println(DSObj2.bChain.lastBlocksList[0].dgst);
        System.out.println(DSObj2.bChain.lastBlocksList[0].nonce);

        Transaction T1 = new Transaction();
        Transaction T2 = new Transaction();
        Transaction T3 = new Transaction();
        Transaction T4 = new Transaction();
        Transaction T5 = new Transaction();
        Transaction T6 = new Transaction();
        Transaction T7 = new Transaction();
        Transaction T8 = new Transaction();

        T1.coinID = DSObj2.memberlist[0].mycoins.get(0).get_first();
        T1.Source = DSObj2.memberlist[0];
        T1.Destination = DSObj2.memberlist[1];
        T1.coinsrc_block = DSObj2.memberlist[0].mycoins.get(0).get_second();
        DSObj2.pendingTransactions.AddTransactions(T1);

        T2.coinID = DSObj2.memberlist[2].mycoins.get(0).get_first();
        T2.Source = DSObj2.memberlist[2];
        T2.Destination = DSObj2.memberlist[1];
        T2.coinsrc_block = DSObj2.memberlist[2].mycoins.get(0).get_second();
        DSObj2.pendingTransactions.AddTransactions(T2);

        T3.coinID = DSObj2.memberlist[2].mycoins.get(1).get_first();
        T3.Source = DSObj2.memberlist[2];
        T3.Destination = DSObj2.memberlist[1];
        T3.coinsrc_block = DSObj2.memberlist[2].mycoins.get(1).get_second();
        DSObj2.pendingTransactions.AddTransactions(T3);

        T4.coinID = DSObj2.memberlist[3].mycoins.get(0).get_first();
        T4.Source = DSObj2.memberlist[3];
        T4.Destination = DSObj2.memberlist[4];
        T4.coinsrc_block = DSObj2.memberlist[3].mycoins.get(0).get_second();
        DSObj2.pendingTransactions.AddTransactions(T4);

        T5.coinID = DSObj2.memberlist[3].mycoins.get(1).get_first();
        T5.Source = DSObj2.memberlist[3];
        T5.Destination = DSObj2.memberlist[4];
        T5.coinsrc_block = DSObj2.memberlist[3].mycoins.get(1).get_second();
        DSObj2.pendingTransactions.AddTransactions(T5);

        T6.coinID = DSObj2.memberlist[3].mycoins.get(2).get_first();
        T6.Source = DSObj2.memberlist[3];
        T6.Destination = DSObj2.memberlist[5];
        T6.coinsrc_block = DSObj2.memberlist[3].mycoins.get(2).get_second();
        DSObj2.pendingTransactions.AddTransactions(T6);

        T7.coinID = DSObj2.memberlist[0].mycoins.get(1).get_first();
        T7.Source = DSObj2.memberlist[0];
        T7.Destination = DSObj2.memberlist[5];
        T7.coinsrc_block = DSObj2.memberlist[0].mycoins.get(1).get_second();
        DSObj2.pendingTransactions.AddTransactions(T7);

        T8.coinID = DSObj2.memberlist[2].mycoins.get(2).get_first();
        T8.Source = DSObj2.memberlist[2];
        T8.Destination = DSObj2.memberlist[4];
        T8.coinsrc_block = DSObj2.memberlist[2].mycoins.get(2).get_second();
        DSObj2.pendingTransactions.AddTransactions(T8);

       
        try {
            M3.MineCoin(DSObj2);
        } catch (Exception e) {
        }
        System.out.println(DSObj2.bChain.lastBlocksList[0].dgst);
        System.out.println(DSObj2.bChain.lastBlocksList[0].nonce);

        long end2 = System.currentTimeMillis();      
        System.out.println("Elapsed Time in milli seconds: "+ (end2-start2));
    }
}
