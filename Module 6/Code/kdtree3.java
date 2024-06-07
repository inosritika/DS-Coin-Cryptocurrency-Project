//import javafx.util.Pair;
//
//import java.util.Vector;
//
//public class kdtree3 {
//    static class Node {
//        Node left;
//        Node right;
//        Node parent;
//        int numpoints;
//        Pair<Integer,Integer>pair;
//
//    }
//    public static Node rootnode;
//    static Node Build_kdtree3(Vector<Pair<Integer, Integer>> v, Node n){
//        if(v.size()==1){
//            n.left = n.right = null;
//            n.numpoints = 1;
//           n.pair = new Pair<>(v.get(0).get)
//            return n ;
//        }
////            System.out.println("WHEN SIZE IS NOT 1 ");
////            System.out.println("Depth is "+depth);
////            System.out.println("Depth1 is "+depth1);
//        int flag = 0;
////       Node n = new Node();
//        if(rootnode==null) {
//            rootnode = n;
////            depth++;
//            n.parent =null;
//            n.ymin = n.xmin = Integer.MIN_VALUE;
//            n.ymax = n.xmax = Integer.MAX_VALUE;
//            flag = 1;
//            n.depth = 0;
//        }
//        int size = v.size() , size1 = 0;
//        if(size%2!=0){
//            size1 = (size+1)/2;
//        }else{
//            size1 = size/2;
//        }
//        if(n.depth%2==0){
//            v = sort_first1(v);
//            if(flag==0){
//                n.xmin = v.get(0).getKey();
//                n.xmax = v.get(size1-1).getKey();
//                n.ymin = n.parent.ymin;
//                n.ymax = n.parent.ymax;
//            }
//        }else{
//            v = sort_second1(v);
//            if(flag==0){
//                n.ymin = v.get(0).getValue();
//                n.ymax = v.get(size1-1).getValue();
//                n.xmin = n.parent.xmin;
//                n.xmax = n.parent.xmax;
//            }
//        }
////        depth++;
//        Vector<Pair<Integer,Integer>>v1 = new Vector<>();
//        Vector<Pair<Integer,Integer>>v2 = new Vector<>();
//        int k =0;
////        System.out.println("SIZE "+size);
////        System.out.println("SIZE1 "+size1);
//        while(k!=size1){
//            v1.add(v.get(k));
//            k++;
//        }
//        while(k!=size){
//            v2.add(v.get(k));
//            k++;
//        }
////        System.out.println("V1 "+v1);
////        System.out.println("V2 "+v2);
////        n.left = new Node();
////        n.right = new Node();
////       Node nd = n.left;
////        nd.parent = n;
////        nd = n.right;
////        nd.parent = n;
//        kdtree2.Node a = new kdtree2.Node();
//        kdtree2.Node b = new kdtree2.Node();
//        a.parent = n;
//        b.parent = n;
//        n.left = a;
//        n.right = b;
//        a.depth = n.depth+1;
//        b.depth = n.depth+1;
////        System.out.println("FUNCTION CALL");
////          int d =++depth;
////        System.out.println("VALUE OF D1 IS "+d);
//        a= Build_kdtree2(v1,a);
////        System.out.println("VALUE OF D2 IS "+d);
//        b= Build_kdtree2(v2,b);
//        n.numpoints = n.left.numpoints+n.right.numpoints;
//        n.depth = n.left.depth+n.right.depth;
////        System.out.println();
////        System.out.println();
//        return n;
//    }
//
//}
import java.util.*;
import java.io.*;
import java.util.Scanner;
public class kdtree3{
    public static ArrayList<ArrayList<Integer>> sort(ArrayList<ArrayList<Integer>> l,int c){
        if(c==0){//sort x
            for(int i=0;i<l.size()-1;i++){
                for(int j=i+1;j<l.size();j++){
                    if(l.get(i).get(0)>l.get(j).get(0)){
                        ArrayList<Integer> temp=l.get(i);
                        //l.get(i)=l.get(j);
                        l.set(i,l.get(j));
                        //l.get(j)=temp;
                        l.set(j,temp);}}}
        }
        else if(c==1){
            for(int i=0;i<l.size()-1;i++){
                for(int j=i+1;j<l.size();j++){
                    if(l.get(i).get(1)>l.get(j).get(1)){
                        ArrayList<Integer> temp=l.get(i);
                        // l.get(i).get(1)=l.get(j).get(1);
                        l.set(i,l.get(j));
                        l.set(j,temp);
                    }}}
        }

        // if(l.size()%2==0){
        //     return l.get(l.size()/2-1);}
        // else{
        //     return l.get(l.size()/2);}
        return l;
    }
    public static Node build(ArrayList<ArrayList<Integer>> l,int c){
        Node n= new Node();

        if(l.size()==0){
            n=null;
        }
        else if(l.size()==1){
            n.x=l.get(0).get(0);
            n.y=l.get(0).get(1);
            n.left=null;
            n.right=null;
            System.out.println(n.x+"  =="+n.y);}

        //n.depth=1+n.parent.depth;
        //n.nleaf=0;}
        else{
            n.x=sort(l,c%2).get((l.size()-1)/2).get(0);
            n.y=sort(l,c%2).get((l.size()-1)/2).get(1);
            ArrayList<ArrayList<Integer>> l1=new ArrayList<ArrayList<Integer>> ();
            ArrayList<ArrayList<Integer>> l2=new ArrayList<ArrayList<Integer>> ();
            for(int i=0;i<(l.size()-1)/2;i++){
                l1.add(sort(l,c%2).get(i));
            }
            for(int i=1+(l.size()-1)/2;i<l.size();i++){
                l2.add(sort(l,c%2).get(i));
            }


            n.left=build(l1,c+1);
            n.left.count=c+1;
            System.out.println(n.x+"  ++"+n.y);
            n.right=build(l2,c+1);
            n.right.count=c+1;
        }
        return n;}

    public static void main(String []args) {

        int[] array;

        ArrayList<ArrayList<Integer>> intarr = new ArrayList<ArrayList<Integer>>();
        try {
            File file1 = new File("restaurants.txt");
            File file2 = new File("queries.txt");
            Scanner sc1 = new Scanner(file1);
            Scanner sc2 = new Scanner(file2);
            //System.out.println(intarr.get(0));
            sc1.nextLine();
            while (sc1.hasNextLine()) {
                ArrayList<Integer> rest = new ArrayList<Integer>();
                String s = sc1.nextLine();
                int i = s.indexOf(',');
                rest.add(Integer.parseInt(s.substring(0, i)));
                rest.add(Integer.parseInt(s.substring(i + 1, s.length())));
                intarr.add(rest);
            }
        } catch (FileNotFoundException e) {
            System.out.println("no");
        }

        ArrayList<ArrayList<Integer>> qarr = new ArrayList<ArrayList<Integer>>();
        try {
            File file1 = new File("restaurants.txt");
            File file2 = new File("queries.txt");
            Scanner sc1 = new Scanner(file1);
            Scanner sc2 = new Scanner(file2);
            //System.out.println(intarr.get(0));
            sc2.nextLine();
            while (sc2.hasNextLine()) {
                ArrayList<Integer> qlist = new ArrayList<Integer>();
                String f = sc2.nextLine();
                int i = f.indexOf(',');
                qlist.add(Integer.parseInt(f.substring(0, i)));
                qlist.add(Integer.parseInt(f.substring(i + 1, f.length())));
                qarr.add(qlist);
            }
        } catch (FileNotFoundException e) {
            System.out.println("no");
        }
        build(intarr, 0);
        for (int i = 0; i < qarr.size(); i++) {
            query(qarr.get(i).get(0), qarr.get(i).get(0), build(intarr, 0));
        }
    }

    static int z=0;
    public static int query(int a,int b, Node n){

        if(n==null) return z;
        if(n.count%2==0){
            if(n.x<=a+100  && n.x>=a-100){
                if(n.y>=b-100 && n.y<=b+100) z+=1;
                z+=query(a,b,n.left)+query(a,b,n.right);
            }
            else if(n.x<a-100)z+=query(a,b,n.right);
            else z+=query(a,b,n.left);
            return z;
        }

        else{
            if(n.y<=b+100  && n.y>=b-100){
                if(n.x>=a-100 && n.x<=a+100) z+=1;
                z+=query(a,b,n.left)+query(a,b,n.right);
            }
            else if(n.y<b-100)z+=query(a,b,n.right);
            else z+=query(a,b,n.left);
            return z;
        }
    }
}






class Node{
    public Node left;
    public Node right;
    public Node parent;
    int x;
    int y;
    int depth;
    int nleaf;
    int count;
}
