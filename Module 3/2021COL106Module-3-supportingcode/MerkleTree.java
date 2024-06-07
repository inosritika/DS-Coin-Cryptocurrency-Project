import Includes.*;
import java.util.*;
	/*
		Pair is a generic data structure defined in the Pair.java file
		It has two attributes - First and Second, that can be set either manually or
		using the constructor

		Edit: The constructor is added
	*/


public class MerkleTree {

	// Check the TreeNode.java file for more details
	public TreeNode rootnode;
	public int numdocs;

//	public String Build(String[] documents) {
//		// Implement Code here
//		numdocs = documents.length;
//		int n = documents.length;
//		TreeNode[][] a = new TreeNode[(int) (Math.log(numdocs) / Math.log(2)) + 1][numdocs + 1];
//		for (int i = (int) (Math.log(numdocs) / Math.log(2)); i >= 0; i--) {
//			for (int j = 1; j <= n; j++) {
//				a[i][j] = new TreeNode();
//				if (i == (int) (Math.log(documents.length) / Math.log(2)) && i != 0) {
//					a[i][j].left = null;
//					a[i][j].right = null;
//					a[i][j].val = documents[j - 1];
//					a[i - 1][(j + 1) / 2] = new TreeNode();
//					a[i][j].parent = a[i - 1][(j + 1) / 2];
//				} else {
//					CRF obj = new CRF(64);
//					a[i][j].left = a[i + 1][2 * j - 1];
//					a[i][j].right = a[i + 1][2 * j];
//					a[i][j].val = obj.Fn(a[i + 1][2 * j - 1].val + "#" + a[i + 1][2 * j].val);
//					if (i != 0) {
//						a[i - 1][(j + 1) / 2] = new TreeNode();
//						a[i][j].parent = a[i - 1][(j + 1) / 2];
//					} else {
//						a[i][j].parent = null;
//					}
//				}
//			}
//			n = n / 2;
//		}
//		rootnode = a[0][1];
//		return rootnode.val;
//	}
//
//public String Build(String[] documents){
//	// Implement Code here
//	numdocs = documents.length;
//	int total = 2*numdocs;
//	TreeNode[] s = new TreeNode[total];
//	for(int i=0; i<total; i++){
//		s[i] = new TreeNode();
//	}
//	for(int i=1; i<=numdocs; i++){
//		s[total-i].val = documents[i-1]	;
//		s[total-i].left = null;
//		s[total-i].right = null;
//		if((total-i)%2==0){
//			s[total-i].parent = s[(total-i)/2];
//		}
//		else{
//			s[total-i].parent = s[(total-i-1)/2];
//		}
//	}
//	CRF crf = new CRF(64);
//	int j = total/2;
//	while(j!=1){
//		for(int i=j-1; i>=j/2; i--){
//			s[i].left = s[(i*2)+1];
//			s[i].right = s[i*2];
//
//			s[i].val = crf.Fn(s[i].left.val+"#"+s[i].right.val);
//			if((i)%2==0){
//				s[i].parent = s[i/2];
//			}
//			else{
//				s[i].parent = s[(i-1)/2];
//			}
//		}
//		j = j/2;
//	}
//	s[1].right = s[2];
//	s[1].left = s[3];
//	s[1].val = crf.Fn(s[1].left.val+"#"+s[1].right.val);
//	s[1].parent = null;
//	// rootnode.val = s[1].val;
//	// rootnode.right = s[1].right;
//	// rootnode.left = s[1].left;
//	// rootnode.parent = s[1].parent;
//	return s[1].val;
//}
//
//	/*
//		Pair is a generic data structure defined in the Pair.java file
//		It has two attributes - First and Second, that can be set either manually or
//		using the constructor
//
//		Edit: The constructor is added
//	*/
//
//	public List<Pair<String,String>> QueryDocument(int doc_idx){
//		// Implement Code here
//		return new ArrayList<Pair<String,String>>(3);
//	}
//
//	public static boolean Authenticate(List<Pair<String,String>> path, String summary){
//		// Implement Code here
//		return false;
//	}
//
////	public String UpdateDocument(int doc_idx, String new_document){
////		// Implement Code here
////
////		return "";
////	}
////}
////	public String UpdateDocument(int doc_idx, String new_document) {
////		TreeNode rootnode1 = this.rootnode;
////		int x = (int) (Math.log(numdocs) / Math.log(2));
////		int n = doc_idx;
////		int y;
////		while (rootnode1.right != null && rootnode1.left != null) {
////			if (n % (int) Math.pow(2, x - 1) == 0) {
////				y = n / (int) Math.pow(2, x - 1);
////			} else {
////				y = n / (int) Math.pow(2, x - 1) + 1;
////			}
////			if (y % 2 == 0) {
////				rootnode1 = rootnode1.right;
////			} else {
////				rootnode1 = rootnode1.left;
////			}
////			x = x - 1;
////		}
////		rootnode1.val = new_document;
////		rootnode1 = rootnode1.parent;
////		CRF obj = new CRF(64);
////		while (rootnode1.parent != null) {
////			rootnode1.val = obj.Fn(rootnode1.left.val + "#" + rootnode1.right.val);
////			rootnode1 = rootnode1.parent;
////		}
////		rootnode.val = obj.Fn(rootnode.left.val + "#" + rootnode.right.val);
////		return rootnode.val;
////	}
//public String UpdateDocument(int doc_idx, String new_document) {
//	TreeNode rootnode1 = this.rootnode;
//	int x = (int) (Math.log(numdocs) / Math.log(2));
//	int n = doc_idx;
//	int y;
//	while (rootnode1.right != null && rootnode1.left != null) {
//		if (n % (int) Math.pow(2, x - 1) == 0) {
//			y = n/(int)Math.pow(2,x-1);
//		}else{
//			y = n/(int)Math.pow(2,x-1)+1;
//		}
//		if(y%2==0){
//			rootnode1 = rootnode1.right;
//		}else{
//			rootnode1 = rootnode1.left;
//		}
//		x = x-1;
//	}
////    System.out.println(rootnode1.val);
//	rootnode1.val = new_document;
//	rootnode1 = rootnode1.parent;
//	CRF obj = new CRF(64);
//	while(rootnode1.parent!=null){
//		rootnode1.val = obj.Fn(rootnode1.left.val+"#"+rootnode1.right.val);
//		rootnode1 = rootnode1.parent;
//	}
//	rootnode.val = obj.Fn(rootnode.left.val+"#"+rootnode.right.val);
//	return rootnode.val;
//}
public String Build(String[] documents) {
	// Implement Code here
	numdocs = documents.length;
	int n = documents.length;
	TreeNode[][] a = new TreeNode[(int) (Math.log(numdocs) / Math.log(2)) + 1][numdocs + 1];
	for (int i = (int) (Math.log(numdocs) / Math.log(2)); i >= 0; i--) {
		for (int j = 1; j <= n; j++) {
			a[i][j] = new TreeNode();
			if (i == (int) (Math.log(documents.length) / Math.log(2)) && i != 0) {
				a[i][j].left = null;
				a[i][j].right = null;
				a[i][j].val = documents[j - 1];
				a[i - 1][(j + 1) / 2] = new TreeNode();
				a[i][j].parent = a[i - 1][(j + 1) / 2];
			} else {
				CRF obj = new CRF(64);
				a[i][j].left = a[i + 1][2 * j - 1];
				a[i][j].right = a[i + 1][2 * j];
				a[i][j].val = obj.Fn(a[i + 1][2 * j - 1].val + "#" + a[i + 1][2 * j].val);
				if (i != 0) {
					a[i - 1][(j + 1) / 2] = new TreeNode();
					a[i][j].parent = a[i - 1][(j + 1) / 2];
				} else {
					a[i][j].parent = null;
				}
			}
		}
		n = n / 2;
	}
	rootnode = a[0][1];
	return rootnode.val;
}


	public String UpdateDocument(int doc_idx, String new_document) {
		TreeNode rootnode1 = this.rootnode;
		int x = (int) (Math.log(numdocs) / Math.log(2));
		int n = doc_idx;
		int y;
		while (rootnode1.right != null && rootnode1.left != null) {
			if (n % (int) Math.pow(2, x - 1) == 0) {
				y = n / (int) Math.pow(2, x - 1);
			} else {
				y = n / (int) Math.pow(2, x - 1) + 1;
			}
			if (y % 2 == 0) {
				rootnode1 = rootnode1.right;
			} else {
				rootnode1 = rootnode1.left;
			}
			x = x - 1;
		}
		rootnode1.val = new_document;
		rootnode1 = rootnode1.parent;
		CRF obj = new CRF(64);
		while (rootnode1.parent != null) {
			rootnode1.val = obj.Fn(rootnode1.left.val + "#" + rootnode1.right.val);
			rootnode1 = rootnode1.parent;
		}
		rootnode.val = obj.Fn(rootnode.left.val + "#" + rootnode.right.val);
		return rootnode.val;
	}


}



