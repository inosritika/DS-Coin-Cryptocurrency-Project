import Includes.*;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.lang.Math;

public class MerkleTree{
	//check TreeNode.java for more details
	public TreeNode rootnode;
	public int numdocs;


	public String InsertDocument(String document){
		//Implement your code here
		numdocs++;
		TreeNode current  ;
		if(rootnode==null){
			TreeNode node = new TreeNode();
			rootnode = node;
			node.val = document;
			node.left = null;
			node.right = null;
			node.parent = null;
			node.isLeaf = true;
			node.numberLeaves = 1;
			node.maxleafval = node.minleafval = node.val;
			node.balanceFactor = 0;
			node.height = 1;
		}else{
			CRF obj = new CRF(64);
			current = rootnode;
			while(!current.isLeaf){
				String val1 = current.right.minleafval;
			    if(val1.compareTo(document)>0 ){
			    	current.balanceFactor++;
			    	current = current.left;
				}else{
			    	current.balanceFactor--;
			    	current = current.right;
				}
			}
				TreeNode A = new TreeNode();
				TreeNode B = new TreeNode();

				A.parent = current;
				B.parent = current;

				A.left=A.right=B.right=B.left = null;

				A.val =  (current.val.compareTo(document)<0?current.val:document);
				B.val =  (current.val.compareTo(document)>0?current.val:document);

				A.isLeaf = B.isLeaf = true;

				A.numberLeaves = B.numberLeaves = 1;

				A.maxleafval = A.minleafval = A.val;
				B.maxleafval = B.minleafval = B.val;

				A.balanceFactor = B.balanceFactor = 0;

				A.height = B.height = 1;

				current.left = A;
				current.right=B;

				current.val = obj.Fn(A.val+"#"+B.val);
				current.isLeaf = false;
				current.minleafval = A.val;
				current.maxleafval = B.val;
				current.balanceFactor = current.left.height-current.right.height;
				current.height = 1+(Math.max(current.left.height, current.right.height));
				TreeNode parent1 = null;
				while(current.parent!=null) {
					parent1 = current.parent;
					current.balanceFactor = current.left.height - current.right.height;
					if (current.balanceFactor == -2) {
						if (current.right.balanceFactor == -1) {
							current = current.SingleLeftRotation();
						}
						else{
							current = current.DoubleRightLeftRotation();
						}
						parent1.right = current;
					} else if (current.balanceFactor == 2) {
						if (current.left.balanceFactor == 1) {
							current = current.SingleRightRotation();
						}
						else{
							current = current.DoubleLeftRightRotation();
						}
						parent1.left = current;
					} else {
						current.val = obj.Fn(current.left.val + "#" + current.right.val);
						current.numberLeaves = current.left.numberLeaves + current.right.numberLeaves;
						current.minleafval = (current.left.minleafval.compareTo(current.right.minleafval) < 0 ? current.left.minleafval : current.right.minleafval);
						current.maxleafval = (current.left.maxleafval.compareTo(current.right.maxleafval) > 0 ? current.left.maxleafval: current.right.maxleafval);
						current.balanceFactor = current.left.height - current.right.height;
						current.height = 1 + (Math.max(current.left.height, current.right.height));
					}
					current = current.parent;
				}

				current.balanceFactor = current.left.height - current.right.height;
				if (current.balanceFactor == -2) {
					if (current.right.balanceFactor == -1) {
						current = current.SingleLeftRotation();
					}
					else{
						current = current.DoubleRightLeftRotation();
					}
				} else if (current.balanceFactor == 2) {
					if (current.left.balanceFactor == 1) {
						current = current.SingleRightRotation();
					}
					else {
						current = current.DoubleLeftRightRotation();
					}
				} else {
					current.val = obj.Fn(current.left.val + "#" + current.right.val);
					current.numberLeaves = current.left.numberLeaves + current.right.numberLeaves;
					current.minleafval = (current.left.minleafval.compareTo(current.right.minleafval) < 0 ? current.left.minleafval : current.right.minleafval);
					current.maxleafval = (current.left.maxleafval.compareTo(current.right.maxleafval) > 0 ? current.left.maxleafval : current.right.maxleafval);
					current.balanceFactor = current.left.height - current.right.height;
					current.height = 1 + (Math.max(current.left.height, current.right.height));
				}

			this.rootnode = current;
		}
		return this.rootnode.val;
	}
	
	public String DeleteDocument(String document){
		//Implement your code here
		return "";
	}
}


