import Includes.*;

import java.util.Stack;

public class AuthStack {
	// PLEASE USE YOUR ENTRY NUMBER AS THE START STRING
	private static final String start_string = "2020MT10838";
	private StackNode top;

	/*
		Note that the Exceptions have already been defined for you in the includes file,
		you just have to raise them accordingly

	*/

	/* 
	Notice that this function is static, the reason why this is static is that we don't want this to be tied with
	an object of the class. 	
	*/

	public static boolean CheckStack(AuthStack current, String proof) throws AuthenticationFailedException{
			if (proof == null) {
				return true;
			}
			CRF obj = new CRF(64);
			StackNode curr = current.top;
			String hsh = "";
			if (curr.next != null) {
				hsh = obj.Fn(curr.next.dgst + "#" + curr.data.value);
			} else {
				hsh = obj.Fn(AuthStack.start_string + "#" + curr.data.value);
			}
			if (!curr.dgst.equals(proof) || !curr.dgst.equals(hsh)) {
				throw new AuthenticationFailedException();
			} else {
				curr = curr.next;
			}
			while (curr != null) {
				if (curr.next == null) {
					hsh = obj.Fn(AuthStack.start_string + "#" + curr.data.value);
					if (!curr.dgst.equals(hsh)) {
						throw new AuthenticationFailedException();
					}
					curr = curr.next;
				} else {
					hsh = obj.Fn(curr.next.dgst + "#" + curr.data.value);
					if (!curr.dgst.equals(hsh)) {
						throw new AuthenticationFailedException();
					}
					curr = curr.next;
				}
			}
			return true;
		}

	public String push(Data datainsert, String proof) throws AuthenticationFailedException {
		/*
			Implement Code here
		*/
		CheckStack(this, proof);
		CRF crf = new CRF(64);
		if (top == null) {
			top = new StackNode();
			top.data = datainsert;
			top.dgst = crf.Fn(start_string + "#" + top.data.value);
			top.next = null;
			return top.dgst;
		} else {
			StackNode s = new StackNode();
			s.data = datainsert;
			s.dgst = crf.Fn(top.dgst + "#" + datainsert.value);
			s.next = top;
			top = s;
			return top.dgst;
		}
	}

	public String pop(String proof) throws AuthenticationFailedException, EmptyStackException {
		/*
			Implement Code here
		*/
		CheckStack(this, proof);
		if (top == null) {
			throw new EmptyStackException();
		} else {
			top = top.next;
			return top.dgst;
		}
	}

	public StackNode GetTop(String proof) throws AuthenticationFailedException, EmptyStackException {
		/*
			Implement Code here
		*/
		CheckStack(this, proof);
		if(top==null){
		   throw new EmptyStackException();
		}
		return top;
	}
}