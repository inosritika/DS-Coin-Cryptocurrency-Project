import javafx.util.Pair;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;

public class kdtree {
    public Node rootnode;
    public int depth =0;
    static class Node{
        Node left;
        Node right;
        Node parent;
        int numpoints;
        int xmin;
        int xmax;
        int ymin;
        int ymax;
        int depth;
    }

public int query( int x, int y,Node node)
{
    int c=0;
    if(node == null){ return c;}
    if(node.xmin >= x-100 && node.xmax <= x+100 && node.ymin >= y-100 && node.ymax <= y+100)
    {
        return node.numpoints;
    }
//    else if((node.xmin > x+100 && node.xmax < x-100 && node.ymin > y+100 && node.ymax < y-100)&&node!=rootnode){return c;}
    c+=query( x, y ,node.left);
    c+=query( x, y,node.right);
    return c;
}
    Pair<Integer,Integer> form_pair(String s){
        int k =0;
        while(s.charAt(k)!=','){
            k++;
        }
        int a = Integer.parseInt(s.substring(0,k));
        int b = Integer.parseInt(s.substring(k+1,s.length()));
        return new Pair<>(a,b);
    }
    Vector<Pair<Integer,Integer>> sort_first(Vector<Pair<Integer,Integer>>v){
            int i=0, j=0;
            for (i = 0; i < v.size()-1; i++)
                for (j = 0; j < v.size()-i-1; j++)
                    if (v.get(j).getKey() > v.get(j+1).getKey()){
                        Pair<Integer,Integer> p = v.get(j);
                        v.set(j,v.get(j+1)) ;
                        v.set(j+1,p);
                    }
            return v;
    }
    Vector<Pair<Integer,Integer>> sort_second(Vector<Pair<Integer,Integer>>v){
        int i=0, j=0;
        for (i = 0; i < v.size()-1; i++)
            for (j = 0; j < v.size()-i-1; j++)
                if (v.get(j).getValue() > v.get(j+1).getValue()){
                    Pair<Integer,Integer> p = v.get(j);
                    v.set(j,v.get(j+1)) ;
                    v.set(j+1,p);
                }
        return v;
    }
    Node Build_kdtree(Vector<Pair<Integer,Integer>>v, Node n){
        if(v.size()==1){
            n.left = n.right = null;
            n.numpoints = 1;
            n.xmin = n.xmax = v.get(0).getKey();
            n.ymax = n.ymin = v.get(0).getValue();
//            System.out.println("LEAF"+n.xmin+" "+n.ymin);
            return n ;
        }
        int flag = 0;
        if(rootnode==null) {
            rootnode = n;
            n.parent =null;
            n.ymin = n.xmin = Integer.MIN_VALUE;
            n.ymax = n.xmax = Integer.MAX_VALUE;
            flag = 1;
            n.depth = 0;
        }
        int size = v.size() , size1 = 0;
        if(size%2!=0){
            size1 = (size+1)/2;
        }else{
            size1 = size/2;
        }
        if(n.depth%2==0){
            v = sort_first(v);
            if(flag==0){
                n.xmin = v.get(0).getKey();
                n.xmax = v.get(size1-1).getKey();
                n.ymin = n.parent.ymin;
                n.ymax = n.parent.ymax;
            }
        }else{
            v = sort_second(v);
            if(flag==0){
                n.ymin = v.get(0).getValue();
                n.ymax = v.get(size1-1).getValue();
                n.xmin = n.parent.xmin;
                n.xmax = n.parent.xmax;
            }
        }
        Vector<Pair<Integer,Integer>>v1 = new Vector<>();
        Vector<Pair<Integer,Integer>>v2 = new Vector<>();
        int k =0;
        while(k!=size1){
            v1.add(v.get(k));
            k++;
        }
        while(k!=size){
            v2.add(v.get(k));
            k++;
        }
       Node a = new Node();
       Node b = new Node();
        a.parent = n;
        b.parent = n;
        n.left = a;
        n.right = b;
        a.depth = n.depth+1;
        b.depth = n.depth+1;
        a= Build_kdtree(v1,a);
        b= Build_kdtree(v2,b);
        n.numpoints = n.left.numpoints+n.right.numpoints;
        n.depth = n.left.depth+n.right.depth;
        return n;
    }


    public static void main(String[] args) throws FileNotFoundException {
        kdtree obj = new kdtree();
            Vector<Pair<Integer, Integer>> v = new Vector<>();
        try {
            FileInputStream fstream = new FileInputStream("restaurants.txt");
            Scanner scnr = new Scanner(fstream);
            String s;
            int flag = 1;
            while (scnr.hasNextLine()) {
                s = scnr.nextLine();
                if (flag == 1) {
                    flag = 0;
                    continue;
                }
                int a = obj.form_pair(s).getKey();
                int b = obj.form_pair(s).getValue();
                v.add(new Pair<Integer, Integer>(a, b));
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found!!");
        }
        int n = v.size();
        Node nd = new Node();
        nd = obj.Build_kdtree(v, nd);

        FileInputStream fstream1 = new FileInputStream("queries.txt");
        Scanner scnr1 = new Scanner(fstream1);
        Vector<Pair<Integer,Integer>>v1 = new Vector<>();
        String s1;
        int flag1 =1;
        int i=0;
            try {
                FileOutputStream fs = new FileOutputStream("out.txt", false);
                PrintStream p = new PrintStream(fs);

                while (scnr1.hasNext()) {
                    s1= scnr1.nextLine();
                    if (flag1 == 1) {
                        flag1 = 0;
                        continue;
                    }
                    int a = obj.form_pair(s1).getKey();
                    int b = obj.form_pair(s1).getValue();
                    v1.add(new Pair<Integer, Integer>(a, b));
                    int k = obj.query(v1.get(i).getKey(), v1.get(i).getValue(), obj.rootnode);
                    i++;
                    p.println(k);
              }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found !!");
            }
    }
}
