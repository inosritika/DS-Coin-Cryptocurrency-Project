import javafx.util.Pair;

import java.util.Vector;

public class kdtree2 {
    static class Node {
        Node left;
        Node right;
        Node parent;
        int numpoints;
        int xmin;
        int xmax;
        int ymin;
        int ymax;
        int depth ;
    }
    public static Node rootnode;
//    public static int depth =0;
    public static int f =0;
    public static Pair<Integer,Integer> form_pair1(String s){
        int k =0;
        while(s.charAt(k)!=','){
            k++;
        }
        int a = Integer.parseInt(s.substring(0,k));
        System.out.println(a);
        int b = Integer.parseInt(s.substring(k+1,s.length()));
        System.out.println(b);
        return new Pair<>(a,b);
    }
    static Vector<Pair<Integer,Integer>> sort_first1(Vector<Pair<Integer, Integer>> v){
        int i=0, j=0;
        for (i = 0; i < v.size()-1; i++)
            for (j = 0; j < v.size()-i-1; j++)
                if (v.get(j).getKey() > v.get(j+1).getKey()){
                    Pair<Integer,Integer> p = v.get(j);
                    v.set(j,v.get(j+1)) ;
                    v.set(j+1,p);
                }
//        System.out.println("SORT FIRST");
//        System.out.println(v);
//        System.out.println();
        return v;
    }
    static Vector<Pair<Integer,Integer>> sort_second1(Vector<Pair<Integer, Integer>> v){
        int i=0, j=0;
        for (i = 0; i < v.size()-1; i++)
            for (j = 0; j < v.size()-i-1; j++)
                if (v.get(j).getValue() > v.get(j+1).getValue()){
                    Pair<Integer,Integer> p = v.get(j);
                    v.set(j,v.get(j+1)) ;
                    v.set(j+1,p);
                }
//        System.out.println("SORT SECOND");
//        System.out.println(v);
//        System.out.println();
        return v;
    }
    static Node Build_kdtree2(Vector<Pair<Integer, Integer>> v, Node n){
//        System.out.println("DEPTH "+depth);
        if(v.size()==1){
//            System.out.println("WHEN SIZE IS 1 ");
//            System.out.println("Depth is "+depth);
//            System.out.println("Depth1 is "+depth1);
//            Node a = new Node();
//            a=n;
//            if(f==0){
//                depth++;
//                f=1;
//            }else if(f==1){
//                f=0;
//            }

            n.left = n.right = null;
            n.numpoints = 1;
            n.xmin = n.xmax = v.get(0).getKey();
            n.ymax = n.ymin = v.get(0).getValue();
            System.out.println(v);
//            depth++;
            return n ;
        }
//            System.out.println("WHEN SIZE IS NOT 1 ");
//            System.out.println("Depth is "+depth);
//            System.out.println("Depth1 is "+depth1);
        int flag = 0;
//       Node n = new Node();
        if(rootnode==null) {
            rootnode = n;
//            depth++;
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
            v = sort_first1(v);
            if(flag==0){
                n.xmin = v.get(0).getKey();
                n.xmax = v.get(size1-1).getKey();
                n.ymin = n.parent.ymin;
                n.ymax = n.parent.ymax;
            }
        }else{
            v = sort_second1(v);
            if(flag==0){
                n.ymin = v.get(0).getValue();
                n.ymax = v.get(size1-1).getValue();
                n.xmin = n.parent.xmin;
                n.xmax = n.parent.xmax;
            }
        }
//        depth++;
        Vector<Pair<Integer,Integer>>v1 = new Vector<>();
        Vector<Pair<Integer,Integer>>v2 = new Vector<>();
        int k =0;
//        System.out.println("SIZE "+size);
//        System.out.println("SIZE1 "+size1);
        while(k!=size1){
            v1.add(v.get(k));
            k++;
        }
        while(k!=size){
            v2.add(v.get(k));
            k++;
        }
//        System.out.println("V1 "+v1);
//        System.out.println("V2 "+v2);
//        n.left = new Node();
//        n.right = new Node();
//       Node nd = n.left;
//        nd.parent = n;
//        nd = n.right;
//        nd.parent = n;
        Node a = new Node();
        Node b = new Node();
        a.parent = n;
        b.parent = n;
        n.left = a;
        n.right = b;
        a.depth = n.depth+1;
        b.depth = n.depth+1;
//        System.out.println("FUNCTION CALL");
//          int d =++depth;
//        System.out.println("VALUE OF D1 IS "+d);
       a= Build_kdtree2(v1,a);
//        System.out.println("VALUE OF D2 IS "+d);
       b= Build_kdtree2(v2,b);
        n.numpoints = n.left.numpoints+n.right.numpoints;
        n.depth = n.left.depth+n.right.depth;
//        System.out.println();
//        System.out.println();
        return n;
    }
    //    public int query(int a,int b,Node n ){
//        int ans =0;
//        int x = a-100, y = a+100, z = b-100,w = b+100;
//        Node curr = n;
//        if(curr.xmin>=x&&curr.xmax<=y&&curr.ymin>=z&&curr.ymax<=w){
//            ans+=curr.numpoints;
//            return ans;
//        }
////        else if((curr.xmin<x&&curr.xmax>y&&curr.ymin<z&&curr.ymax>w)&&curr!=rootnode){
////            return 0;
////        }
//        else {
//            int lft = 0 , rgt =0;
//            if(n.left!=null){
//             lft = query(a,b,n.left);}
//            if(n.right!=null){
//                rgt = query(a,b,n.right);}
//            ans+=lft+rgt;
//            return ans;
//        }
//    }

    public static void main(String[] args) {
        Vector<Pair<Integer,Integer>> v = new Vector<>();
        String s;
        int flag =1;
//    while(scnr.hasNext()){
//        s = scnr.nextLine();
//        if(flag==1){
//            flag=0;
//            continue;
//        }
//        int a = form_pair(s).getKey();
//        int b = form_pair(s).getValue();
//        v.add(new Pair<Integer , Integer>(a,b));
//    }
//        int n = v.size();
//    for(int i=0;i<16;i++){
        v.add(new Pair<>(1,2));
        v.add(new Pair<>(4,-6));
        v.add(new Pair<>(-2,-2));
        v.add(new Pair<>(-5,-7));
        v.add(new Pair<>(9,6));
        v.add(new Pair<>(5,5));
        v.add(new Pair<>(7,3));
        v.add(new Pair<>(2,8));
        v.add(new Pair<>(-1,4));
        v.add(new Pair<>(-8,-2));
        v.add(new Pair<>(-6,-3));
        v.add(new Pair<>(6,7));
        v.add(new Pair<>(8,-3));
        v.add(new Pair<>(-3,7));
        v.add(new Pair<>(-6,1));
        v.add(new Pair<>(-8,8));
//    }
        Node a = new Node();
       a= Build_kdtree2(v,a);
        form_pair1("12345,78906");
    }

}
