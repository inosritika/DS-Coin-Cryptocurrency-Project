import Includes.*;
import java.util.*;
import java.io.*;


public class Tester{
	public static void main(String args[]){
		
//
		System.out.println("Testcase 1: 23 Random Strings");
		MerkleTree tree1=new MerkleTree();
		String s1=null;
		s1=tree1.InsertDocument("qoJU50pzvgKmBFlYn");
		s1=tree1.InsertDocument("g01V5VxqkcD19WmqH");
		s1=tree1.InsertDocument("1gUJeYXKz87P9yFgl");
		s1=tree1.InsertDocument("lirxP9DzSlAsUc3rd");
		s1=tree1.InsertDocument("QAbwEglARkXBDy3T8");
		s1=tree1.InsertDocument("OW083M72LW4smd79c");
		s1=tree1.InsertDocument("ONPORvyuRdCRIpNzQ");
		s1=tree1.InsertDocument("6HZe6kQTcjuaPoku2");
		s1=tree1.InsertDocument("wKFJXHLS14Ud3jbPj");
		s1=tree1.InsertDocument("9QziaWPEjJIF7EXOo");
		s1=tree1.InsertDocument("Toe4sxRaHo2ssu97P");
		s1=tree1.InsertDocument("8PkUwV6bIxVGQbLOL");
		s1=tree1.InsertDocument("SggVy6k0gKWK41FVY");
		s1=tree1.InsertDocument("cB39RAhFcdQA6ybQ6");
		s1=tree1.InsertDocument("fI1HyiZOwjt8dSyz1");
		s1=tree1.InsertDocument("hYIRjCedTSLJ4xhiE");
		s1=tree1.InsertDocument("32aWNi3RJcjUTHAJ7");
		s1=tree1.InsertDocument("OFzLLEqTaKAClSU6u");
		s1=tree1.InsertDocument("aYiadmJucIWld0pSg");
		s1=tree1.InsertDocument("9QX1B3INUl4yWRalo");
		s1=tree1.InsertDocument("Qj027UBS9cVdhGThw");
		s1=tree1.InsertDocument("xNzdCBDaCjRfz1pLx");
		s1=tree1.InsertDocument("UEzvVQ5RVPIB7RorJ");

		System.out.println(s1);

		System.out.println("\nTestCase 2: 59 Random Strings");
		MerkleTree tree2=new MerkleTree();
		String s2=null;
		s2=tree2.InsertDocument("DIEmPescWwp6GvI_01");
		s2=tree2.InsertDocument("aam1KRWV6tU8t2J_02");
		s2=tree2.InsertDocument("6hI1ygSgPRDlJXD_03");
		s2=tree2.InsertDocument("CkcaAsICF0GUdNx_04");
		s2=tree2.InsertDocument("CcWgNxfG0On69YT_05");
		s2=tree2.InsertDocument("mcwWsqjFTdQ5F1K_06");
		s2=tree2.InsertDocument("g42pma5meupF6QD_07");
		s2=tree2.InsertDocument("mwq2EDW3ca3h7oZ_08");
		s2=tree2.InsertDocument("4xfgBXdU5QTf41o_09");
		s2=tree2.InsertDocument("NnPOxPKdBjZj4Xl_10");
		s2=tree2.InsertDocument("7lsNb1X5Ne1xGHe_11");
		s2=tree2.InsertDocument("5B2S4N4MfOJWaev_12");
		s2=tree2.InsertDocument("7Ku7h83Iw0ykva5_13");
		s2=tree2.InsertDocument("TuZmqQ9esrvd3LB_14");
		s2=tree2.InsertDocument("9oIJcudqWR7FvDx_15");
		s2=tree2.InsertDocument("h1muYNo7g5Xw0aj_16");
		s2=tree2.InsertDocument("AI5blzbhVH4rjrn_17");
		s2=tree2.InsertDocument("z3uUIR96RiDECHy_18");
		s2=tree2.InsertDocument("Oj10h0CV2eTFxZg_19");
		s2=tree2.InsertDocument("3b0O5RgcuSw1pKV_20");
		s2=tree2.InsertDocument("kbrIHCouyRqgcBo_21");
		s2=tree2.InsertDocument("67BV9q5dkCtJiQb_22");
		s2=tree2.InsertDocument("0GlOHVU8ANbU5tz_23");
		s2=tree2.InsertDocument("zm1G8lSX8QKZ2Un_24");
		s2=tree2.InsertDocument("U6LbtRD6K3itg0x_25");
		s2=tree2.InsertDocument("k1tKT8tcgXG45uG_26");
		s2=tree2.InsertDocument("1pGGpV7BpEa1DXm_27");
		s2=tree2.InsertDocument("zhi05LlZwSxfUKf_28");
		s2=tree2.InsertDocument("0fXdNn0BnpM3Lr5_29");
		s2=tree2.InsertDocument("pCu2Ax4dWg55LfA_30");
		s2=tree2.InsertDocument("M5JAawKNkRwikIR_31");
		s2=tree2.InsertDocument("vtiuZJ6vHjbFoJ1_32");
		s2=tree2.InsertDocument("YCFBkP6aB4davVt_33");
		s2=tree2.InsertDocument("2Zp43RsPu0Df0SB_34");
		s2=tree2.InsertDocument("aIwHD85iKHccXb5_35");
		s2=tree2.InsertDocument("Lemua51wqlq9BSh_36");
		s2=tree2.InsertDocument("4R4zHrAyreD413K_37");
		s2=tree2.InsertDocument("APSMYlC3l2QQIxI_38");
		s2=tree2.InsertDocument("wAe2qQu5Du7cWyA_39");
		s2=tree2.InsertDocument("yOdZ8x5ySIkSY46_40");
		s2=tree2.InsertDocument("z6EYWYhVdu3aIHF_41");
		s2=tree2.InsertDocument("pUzE57BSwbVvaGA_42");
		s2=tree2.InsertDocument("lo7j54n3XR3nyey_43");
		s2=tree2.InsertDocument("mQoiM2JX4PsZPsv_44");
		s2=tree2.InsertDocument("yhz03t6bICezsTu_45");
		s2=tree2.InsertDocument("4Ap1nyT7jFUNsQv_46");
		s2=tree2.InsertDocument("AWD0zLG5A59zKLs_47");
		s2=tree2.InsertDocument("IzUZyaoa2CCzYVi_48");
		s2=tree2.InsertDocument("y4Xilqyd4YxSvcx_49");
		s2=tree2.InsertDocument("eoVORh6PbTyp2fG_50");
		s2=tree2.InsertDocument("r9yrAgLsLPdIHqO_51");
		s2=tree2.InsertDocument("dstt1B34OqXSpNj_52");
		s2=tree2.InsertDocument("4cjbOtyFz7z0iat_53");
		s2=tree2.InsertDocument("J5vxR3amoXcdXKb_54");
		s2=tree2.InsertDocument("EdMkVG1MSDH9VUP_55");
		s2=tree2.InsertDocument("lHRAl0zgA0LrPJN_56");
		s2=tree2.InsertDocument("GgJ9IjCiqlx5Ujf_57");
		s2=tree2.InsertDocument("FX8IIVkpYySi0nz_58");
		s2=tree2.InsertDocument("YPffgjrhyRUzNhm_59");

////		s2=tree2.InsertDocument("1");
////		s2=tree2.InsertDocument("2");
////		s2=tree2.InsertDocument("3");
////		s2=tree2.InsertDocument("4");
////		s2=tree2.InsertDocument("5");
////		s2=tree2.InsertDocument("6");
////		s2=tree2.InsertDocument("7");
////		s2=tree2.InsertDocument("8");
////		s2=tree2.InsertDocument("9");
////		s2=tree2.InsertDocument("10");
////		s2=tree2.InsertDocument("11");
////		s2=tree2.InsertDocument("12");
////		s2=tree2.InsertDocument("13");
////		s2=tree2.InsertDocument("14");
////		s2=tree2.InsertDocument("15");
		System.out.println(s2);

//		CRF obj = new CRF(64);
//		System.out.println("Testcase 4:");
//		MerkleTree tree4 = new MerkleTree();
//		String summary4 = null;
//
//		summary4 = tree4.InsertDocument("A");
//		summary4 = tree4.InsertDocument("B");
//		summary4 = tree4.InsertDocument("C");
//		summary4 = tree4.InsertDocument("D");
//
//		System.out.println(summary4);
//		System.out.println(obj.Fn(obj.Fn("A#B")+"#"+obj.Fn("C#D")));
//
//		System.out.println("Testcase 5:");
//		MerkleTree tree5 = new MerkleTree();
//		String summary5 = null;
//
//		summary5 = tree5.InsertDocument("D");
//		summary5 = tree5.InsertDocument("A");
//		summary5 = tree5.InsertDocument("B");
//		summary5 = tree5.InsertDocument("C");
//
//		System.out.println(summary5);
//		System.out.println(obj.Fn(obj.Fn("A#B")+"#"+obj.Fn("C#D")));
//
//		System.out.println("Testcase 6:");
//		MerkleTree tree6 = new MerkleTree();
//		String summary6 = null;
//
//		summary6 = tree6.InsertDocument("C");
//		summary6 = tree6.InsertDocument("D");
//		summary6 = tree6.InsertDocument("E");
//		summary6 = tree6.InsertDocument("F");
//		summary6 = tree6.InsertDocument("B");
//		summary6 = tree6.InsertDocument("A");
//		System.out.println(summary6);
//		System.out.println(obj.Fn(obj.Fn(obj.Fn("A#B")+"#"+obj.Fn("C#D"))+"#"+obj.Fn("E#F")));
//
//		System.out.println("Testcase 7:");
//		MerkleTree tree7 = new MerkleTree();
//		String summary7 = null;
//
//		summary7 = tree7.InsertDocument("G");
//		summary7 = tree7.InsertDocument("F");
//		summary7 = tree7.InsertDocument("E");
//		summary7 = tree7.InsertDocument("D");
//		summary7 = tree7.InsertDocument("C");
//		summary7 = tree7.InsertDocument("B");
//		summary7 = tree7.InsertDocument("A");
//		System.out.println(summary7);
//		System.out.println(obj.Fn(obj.Fn(obj.Fn("A#B")+"#C")+"#"+obj.Fn(obj.Fn("D#E")+"#"+obj.Fn("F#G"))));
//
//		System.out.println("Testcase 8:");
//		MerkleTree tree8 = new MerkleTree();
//		String summary8 = null;
//
//		summary8 = tree8.InsertDocument("K");
//		summary8 = tree8.InsertDocument("I");
//		summary8 = tree8.InsertDocument("G");
//		summary8 = tree8.InsertDocument("E");
//		summary8 = tree8.InsertDocument("C");
//		summary8 = tree8.InsertDocument("A");
//		summary8 = tree8.InsertDocument("H");
//		System.out.println(summary8);
//		System.out.println(obj.Fn(obj.Fn(obj.Fn("A#C")+"#E")+"#"+obj.Fn(obj.Fn("G#H")+"#"+obj.Fn("I#K"))));
		MerkleTree tree4 = new MerkleTree();
		String summary4 = null;
		summary4 = tree4.InsertDocument("05");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("31");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("11");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("70");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("07");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("20");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("45");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("90");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("25");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("27");
		System.out.println(summary4);
		summary4 = tree4.InsertDocument("21");
		System.out.println(summary4);


	}

}
