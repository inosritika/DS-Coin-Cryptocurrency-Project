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



