import Includes.*;
import java.util.*;

public class MerkleTree {
	// Check the TreeNode.java file for more details
	public TreeNode rootnode;
	public int numstudents;

	public String int_to_str(int n) {
		String s = "";
		if (n == 0) {
			char c = (char) 48;
			s = s + c;
		} else {
			while (n != 0) {
				int a = n % 10;
				a = a + 48;
				char c = (char) a;
				s = s + c;
				n = n / 10;
			}
		}
		String t = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			t = t + s.charAt(i);
		}
		return t;
	}

	public String find_substr(String s) {
		int n = s.length() - 1;
		while (s.charAt(n) != '_') {
			n--;
		}
		return s.substring(0, n + 1);
	}


	/*
		Pair is a generic data structure defined in the Pair.java file
		It has two attributes - First and Second
	*/
	public String Build(List<Pair<String, Integer>> documents){
		numstudents = documents.size();
		int num_col = documents.size()+1,num_row = (int) (Math.log(numstudents) / Math.log(2))+2;
		TreeNode[][] a = new TreeNode[num_row][num_col];
		for(int i = 0;i<num_row;i++){
			for(int j=0;j<num_col;j++){
				a[i][j] = new TreeNode();
			}
		}
		int k=0;
		CRF obj = new CRF(64);
		for(int i=num_row-1;i>=1;i--){//4 - 1
			for(int j =1;j<num_col;j++){//1-8
				if(i==num_row-1){
					a[i][j].parent = a[i-1][(j+1)/2];
					a[i][j].left = null;
					a[i][j].right = null;
					a[i][j].val =  documents.get(k).get_first() + "_" + int_to_str(documents.get(k).get_second());
					a[i][j].maxleafval = documents.get(k).get_second();
					a[i][j].isLeaf = true;
					a[i][j].numberLeaves = 1;
					k++;
				}else{
					if(i==1){
						a[i][j].parent = null;
					}else{
      					a[i][j].parent = a[i-1][(j+1)/2];
					}
					a[i][j].left = a[i+1][2*j-1];
					a[i][j].right =  a[i+1][2*j];
					a[i][j].val =obj.Fn(a[i + 1][2 * j - 1].val + "#" + a[i + 1][2 * j].val);
					a[i][j].maxleafval = Math.max(a[i][j].left.maxleafval, a[i][j].right.maxleafval);
					a[i][j].isLeaf = false;
					a[i][j].numberLeaves = a[i][j].left.numberLeaves+ a[i][j].right.numberLeaves;
				}
//				System.out.println(a[i][j].val);
			}
			num_col=num_col/2+1;
		}
		this.rootnode = a[1][1];
		return this.rootnode.val;
	}

	public String UpdateDocument(int student_id, int newScore) {
		int idx = student_id+1 , n = numstudents;
		TreeNode root = this.rootnode;
		while(root.left!=null&&root.right!=null){
			n=n/2;
			if(idx<=n){
				root = root.left;
			}else{
				idx = idx-n;
				root=root.right;
			}
		}
		root.val = find_substr(root.val)+int_to_str(newScore);
		root.maxleafval = newScore;
		root=root.parent;
			CRF crf = new CRF(64);
			int k =0;
		while(root.parent!=null&&root!=null){
			root.val = crf.Fn(root.left.val+"#"+root.right.val);
			root.maxleafval = Math.max(root.left.maxleafval,root.right.maxleafval);
			root=root.parent;
		}
		this.rootnode.val = crf.Fn(root.left.val+"#"+root.right.val);
		return this.rootnode.val;
	}
}
