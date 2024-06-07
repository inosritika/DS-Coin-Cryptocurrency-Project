import Includes.*;

public class AuthList{
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	public static final String start_string = "2020MT10838";
	public Node firstNode;
	public Node lastNode;

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class AuthList. 	
	*/
	public static boolean CheckList(AuthList current, String proof) throws AuthenticationFailedException {
		CRF obj = new CRF(64);
		Node curr = current.firstNode;
		boolean initial = true;
		while(curr != null){
			if(initial){
				String hsh = obj.Fn(AuthList.start_string + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh)) {
					throw new AuthenticationFailedException();
				}
				initial = false;
				curr = curr.next;
			}else if(curr == current.lastNode){
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh) || !curr.dgst.equals(proof)) {
					throw new AuthenticationFailedException();
				}
				curr = curr.next;
			}else{
				String hsh = obj.Fn(curr.previous.dgst + "#" + curr.data.value);
				if(!curr.dgst.equals(hsh))  {
					throw new AuthenticationFailedException();
				}
				curr = curr.next;
			}
		}
		return true;
	}


	public String InsertNode(Data datainsert, String proof) throws AuthenticationFailedException {
		/*
			Implement Code here
		*/
         CRF crf = new CRF(64);
		if (firstNode != null) {
			if(CheckList(this, proof)){
				Node n1 = new Node();
				lastNode.next = n1;
				n1.previous = lastNode;
				n1.next = null;
				n1.data = datainsert;
				n1.dgst= crf.Fn(lastNode.dgst+"#"+datainsert.value);
			   lastNode = n1;
			   return n1.dgst;
			}
			   return null;
		} else {
		firstNode=lastNode = new Node();
		firstNode.previous = null;
		firstNode.next = null;
		firstNode.data = datainsert;
		firstNode.dgst = crf.Fn(start_string+"#"+datainsert.value);
		return firstNode.dgst;
		}
	}

	public String DeleteFirst(String proof) throws AuthenticationFailedException, EmptyListException {
		/*
			Implement Code here check it too
		*/
		 if(CheckList(this,proof)){
			 if(firstNode==null){
				 throw new EmptyListException();
			 }else if(firstNode.next==null){
			 	firstNode = lastNode = null;
			 	return null;
			 }else{
			 	CRF crf = new CRF(64);
			 	firstNode.next.previous = null;
			 	firstNode = firstNode.next;
			 	firstNode.dgst = crf.Fn(start_string+"#"+firstNode.data.value);
			 	Node n1 = firstNode.next;
			 	while(n1!=null){
				 n1.dgst = crf.Fn(n1.previous.dgst+"#"+n1.data.value);
			 	 n1=n1.next;
				}
			 	return lastNode.dgst;
			 }
		}
		return null;
	}


	public String DeleteLast(String proof) throws AuthenticationFailedException, EmptyListException {
		/*
			Implement Code here
		*/
		if(CheckList(this,proof)){
			if(proof==null){//firstNode==null
				throw new EmptyListException();
			}else if(lastNode.next==null){
			     lastNode = firstNode=null;
			     return null;
			} else{
					lastNode.previous.next = null;
					lastNode = lastNode.previous;
					return lastNode.dgst;
				}
			}
		return null;
	}

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class AuthList. 	
	*/
	public static Node RetrieveNode(AuthList current, String proof, Data data) throws AuthenticationFailedException, DocumentNotFoundException{
		/*
			Implement Code here
		*/
		CheckList(current, proof);
		Node n = current.firstNode;
		while(n!=null){
			if(n.data.value==data.value){
			return n;
		    }
			n=n.next;
		}
		throw new DocumentNotFoundException();
	}

	public void AttackList(AuthList current, String new_data)throws EmptyListException{
		/*
			Implement Code here
		*/
	}

}
