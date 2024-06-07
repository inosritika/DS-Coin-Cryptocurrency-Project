//package Includes;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MarkleTree2 {
//    //	public List<Pair<String,String>> QueryDocument(int doc_idx){
    // Implement Code here
//		List<Pair<String,String>> l = new ArrayList<Pair<String,String>>();
//		int i = (int)(Math.log(numdocs)/Math.log(2)),j;
//		if(doc_idx%2==0){
//		l.add(new Pair(a[i][doc_idx-1].val,a[i][doc_idx].val));
//		j = doc_idx/2;
//		}else{
//		l.add(new Pair(a[i][doc_idx].val,a[i][doc_idx+1].val));
//		j = (doc_idx+1)/2;
//		}
//		for(i = i-1;i>=1;i--){
//			if(j%2==0){
//				l.add(new Pair(a[i][j-1].val,a[i][j].val));
//				j = j/2;
//			}else{
//				l.add(new Pair(a[i][j].val,a[i][j+1].val));
//				j = (j+1)/2;
//			}
//		}
//		l.add(new Pair(a[0][1].val,null));
//		return l ;
//	}
//
//	public static boolean Authenticate(List<Pair<String,String>> path, String summary){
//		// Implement Code here
//
//		return false;
//	}
//
//	public String UpdateDocument(int doc_idx, String new_document){
//		// Implement Code here
//        int indx = doc_idx;
//        int k = (int)(Math.log(numdocs)/Math.log(2));
//        int i = 1,j1;
//        TreeNode node = null;
//        while(k!=0){
//			int i1 = (int) Math.pow(2, (k - 1));
//			if(indx%i1!=0){
//				 j1 = (indx/i1)+1;
//			}else{
//				j1 = indx/i1;
//			}
//			node = a[i][j1] ;
//			k = k-1;
//			i++;
//		}
//        node.val = new_document;
//        int j = indx;
//        CRF crf = new CRF(64);
//        for(i=i-1;i>=0;i--){
//        	if(j%2==0){
//        		a[i-1][j/2].val = crf.Fn(a[i+1][j-1].val+"#"+a[i+1][j]);
//        		j = j/2;
//			}else{
//        		a[i-1][(j+1)/2].val = crf.Fn(a[i+1][j].val+"#"+a[i+1][j+1]);
//        		j = (j+1)/2;
//			}
//		}
//		return a[0][1].val;
//    }
//    public static boolean Authenticate(List<Pair<String, String>> path, String summary) {
//        // Implement Code here
//        CRF newobj = new CRF(64);
//        if (!summary.equals(path.get(path.size() - 1).First)) {
//            return false;
//        }
//        for (int i = 0; i < path.size() - 1; i++) {
//            if (!newobj.Fn(path.get(i).First + "#" + path.get(i).Second).equals(path.get(i + 1).First)
//                    && !newobj.Fn(path.get(i).First + "#" + path.get(i).Second).equals(path.get(i + 1).Second)) {
//                return false;
//            }
//
//        }
//        return true;
//    }
//
//    public List<Pair<String, String>> QueryDocument(int doc_idx) {
////        // Implement Code here
////        ArrayList<Pair<String, String>> pathToRoot = new ArrayList<>();
////        TreeNode curr = this.rootnode;
////        int n = this.numdocs;
////        int k = doc_idx;
////        boolean initial = true;
////        while (n >= 2) {
////            if (initial) {
////
////                pathToRoot.add(0, new Pair<String, String>(curr.val, null));
////                initial = false;
////            }
////            pathToRoot.add(0, new Pair<String, String>(curr.left.val, curr.right.val));
////            if ((n / 2) < k) {
////                curr = curr.right;
////                n = n / 2;
////                k = k - n;
////            } else {
////                curr = curr.left;
////                n = n / 2;
////            }
////        }
////        return pathToRoot;
////    }
//}
//
//}
