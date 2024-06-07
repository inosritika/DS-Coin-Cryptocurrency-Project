import Includes.*;
import java.util.*;
import java.lang.Math;
public class TreeNode{
	public TreeNode parent;
	public TreeNode left;
	public TreeNode right;

	public boolean isLeaf;
	public int numberLeaves;
	public String maxleafval;
	public String minleafval;
	public int balanceFactor;
	public int height ;

	public String val;

	public TreeNode SingleLeftRotation(){
		//Implement your code here
		TreeNode x = this;
		TreeNode z = this.right;
		TreeNode y = z.right;

		z.parent = x.parent ;
		x.right = z.left;
		TreeNode r = z.left;
		r.parent= x;
		x.parent = z;
		z.left = x;

		CRF obj = new CRF(64);
		x.val = obj.Fn(x.left.val+"#"+x.right.val);
		x.numberLeaves = x.left.numberLeaves+x.right.numberLeaves;
		x.minleafval = (x.left.minleafval.compareTo(x.right.minleafval)>0?x.left.minleafval:x.right.minleafval);
		x.maxleafval= (x.left.maxleafval.compareTo(x.right.maxleafval)<0?x.left.maxleafval:x.right.maxleafval);
		x.balanceFactor = x.left.height-x.right.height;
		x.height = 1+(Math.max(x.left.height, x.right.height));

		y.val = obj.Fn(y.left.val+"#"+y.right.val);
		y.numberLeaves = y.left.numberLeaves+y.right.numberLeaves;
		y.minleafval = (y.left.minleafval.compareTo(y.right.minleafval)>0?y.left.minleafval:y.right.minleafval);
		y.maxleafval= (y.left.maxleafval.compareTo(y.right.maxleafval)<0?y.left.maxleafval:y.right.maxleafval);
		y.balanceFactor = y.left.height-y.right.height;
		y.height = 1+(Math.max(y.left.height, y.right.height));

		z.val = obj.Fn(z.left.val+"#"+z.right.val);
		z.numberLeaves = z.left.numberLeaves+z.right.numberLeaves;
		z.minleafval = (z.left.minleafval.compareTo(z.right.minleafval)>0?z.left.minleafval:z.right.minleafval);
		z.maxleafval= (z.left.maxleafval.compareTo(z.right.maxleafval)<0?z.left.maxleafval:z.right.maxleafval);
		z.balanceFactor = z.left.height-z.right.height;
		z.height = 1+(Math.max(z.left.height, z.right.height));

		return z;
	}
	
	public TreeNode SingleRightRotation(){
		//Implement your code here
		TreeNode x = this;
		TreeNode z = this.left;
		TreeNode y = z.left;

		z.parent = x.parent ;
		x.left = z.right;
		TreeNode r = z.right;
		r.parent= x;
		x.parent = z;
		z.right = x;

		CRF obj = new CRF(64);
		x.val = obj.Fn(x.left.val+"#"+x.right.val);
		x.numberLeaves = x.left.numberLeaves+x.right.numberLeaves;
		x.minleafval = (x.left.minleafval.compareTo(x.right.minleafval)<0?x.left.minleafval:x.right.minleafval);
		x.maxleafval= (x.left.maxleafval.compareTo(x.right.maxleafval)>0?x.left.maxleafval:x.right.maxleafval);
		x.balanceFactor = x.left.height-x.right.height;
		x.height = 1+(Math.max(x.left.height, x.right.height));

		y.val = obj.Fn(y.left.val+"#"+y.right.val);
		y.numberLeaves = y.left.numberLeaves+y.right.numberLeaves;
		y.minleafval = (y.left.minleafval.compareTo(y.right.minleafval)<0?y.left.minleafval:y.right.minleafval);
		y.maxleafval= (y.left.maxleafval.compareTo(y.right.maxleafval)>0?y.left.maxleafval:y.right.maxleafval);
		y.balanceFactor = y.left.height-y.right.height;
		y.height = 1+(Math.max(y.left.height, y.right.height));

		z.val = obj.Fn(z.left.val+"#"+z.right.val);
		z.numberLeaves = z.left.numberLeaves+z.right.numberLeaves;
		z.minleafval = (z.left.minleafval.compareTo(z.right.minleafval)<0?z.left.minleafval:z.right.minleafval);
		z.maxleafval= (z.left.maxleafval.compareTo(z.right.maxleafval)>0?z.left.maxleafval:z.right.maxleafval);
		z.balanceFactor = z.left.height-z.right.height;
		z.height = 1+(Math.max(z.left.height, z.right.height));
		return z ;
	}
	
	public TreeNode DoubleLeftRightRotation(){//replace
		//Implement your code here
		TreeNode x = this;
		TreeNode y = this.left;
		TreeNode z = y.right;

		z.parent = x.parent;
		y.right = z.left;
		TreeNode r = z.left;
		r.parent = y;
		x.left = z.right;
		r = z.right;
		r.parent = x;
		z.right = x;
		z.left = y;
		y.parent = z;
		x.parent = z;

		CRF obj = new CRF(64);
		x.val = obj.Fn(x.left.val+"#"+x.right.val);
		x.numberLeaves = x.left.numberLeaves+x.right.numberLeaves;
		x.minleafval = (x.left.minleafval.compareTo(x.right.minleafval)<0?x.left.minleafval:x.right.minleafval);
		x.maxleafval= (x.left.maxleafval.compareTo(x.right.maxleafval)>0?x.left.maxleafval:x.right.maxleafval);
		x.balanceFactor = x.left.height-x.right.height;
		x.height = 1+(Math.max(x.left.height, x.right.height));

		y.val = obj.Fn(y.left.val+"#"+y.right.val);
		y.numberLeaves = y.left.numberLeaves+y.right.numberLeaves;
		y.minleafval = (y.left.minleafval.compareTo(y.right.minleafval)<0?y.left.minleafval:y.right.minleafval);
		y.maxleafval= (y.left.maxleafval.compareTo(y.right.maxleafval)>0?y.left.maxleafval:y.right.maxleafval);
		y.balanceFactor = y.left.height-y.right.height;
		y.height = 1+(Math.max(y.left.height, y.right.height));

		z.val = obj.Fn(z.left.val+"#"+z.right.val);
		z.numberLeaves = z.left.numberLeaves+z.right.numberLeaves;
		z.minleafval = (z.left.minleafval.compareTo(z.right.minleafval)<0?z.left.minleafval:z.right.minleafval);
		z.maxleafval= (z.left.maxleafval.compareTo(z.right.maxleafval)>0?z.left.maxleafval:z.right.maxleafval);
		z.balanceFactor = z.left.height-z.right.height;
		z.height = 1+(Math.max(z.left.height, z.right.height));

		return z;

	}
	
	public TreeNode DoubleRightLeftRotation(){
		//Implement your code here
		TreeNode x = this;
		TreeNode y = this.right;
		TreeNode z = y.left;

		z.parent = x.parent;
		y.left = z.right;
		TreeNode r = z.right;
		r.parent = y;
		x.right = z.left;
		r = z.left;
		r.parent = x;
		z.left = x;
		z.right = y;
		y.parent = z;
		x.parent = z;

		CRF obj = new CRF(64);
		x.val = obj.Fn(x.left.val+"#"+x.right.val);
		x.numberLeaves = x.left.numberLeaves+x.right.numberLeaves;
		x.minleafval = (x.left.minleafval.compareTo(x.right.minleafval)<0?x.left.minleafval:x.right.minleafval);
		x.maxleafval= (x.left.maxleafval.compareTo(x.right.maxleafval)>0?x.left.maxleafval:x.right.maxleafval);
		x.balanceFactor = x.left.height-x.right.height;
		x.height = 1+(Math.max(x.left.height, x.right.height));

		y.val = obj.Fn(y.left.val+"#"+y.right.val);
		y.numberLeaves = y.left.numberLeaves+y.right.numberLeaves;
		y.minleafval = (y.left.minleafval.compareTo(y.right.minleafval)<0?y.left.minleafval:y.right.minleafval);
		y.maxleafval= (y.left.maxleafval.compareTo(y.right.maxleafval)>0?y.left.maxleafval:y.right.maxleafval);
		y.balanceFactor = y.left.height-y.right.height;
		y.height = 1+(Math.max(y.left.height, y.right.height));

		z.val = obj.Fn(z.left.val+"#"+z.right.val);
		z.numberLeaves = z.left.numberLeaves+z.right.numberLeaves;
		z.minleafval = (z.left.minleafval.compareTo(z.right.minleafval)<0?z.left.minleafval:z.right.minleafval);
		z.maxleafval= (z.left.maxleafval.compareTo(z.right.maxleafval)>0?z.left.maxleafval:z.right.maxleafval);
		z.balanceFactor = z.left.height-z.right.height;
		z.height = 1+(Math.max(z.left.height, z.right.height));

		return z;
	}
}

