import HelperClasses.DigitalSignature;
import HelperClasses.Conversion;
import java.security.KeyPair;

public class Signature extends DigitalSignature {

    public static SignatureKeys keygen() {
        KeyPair keys = generate_keys();
        String sk = Conversion.byteToHex(keys.getPrivate().getEncoded());
        String vk = Conversion.byteToHex(keys.getPublic().getEncoded());
        return new SignatureKeys(sk, vk);
    }

    public static String BoundedMsgSign(String message, String sk) {

        assert message.length() == 64;

        return sign_message(message, sk);
    }

    public static boolean BoundedMsgVerify(String message, String pk, String signature) {

        assert message.length() == 64;

        return verify_signature(message, pk, signature);
    }

    /*==========================
    |- To be done by students -|
    ==========================*/

//    public static String Sign(String m, String sk) {
//              CRF CRF_obj = new CRF(64);
//              String dgst = CRF_obj.Fn(m);
//              String sig = BoundedMsgSign(dgst , sk);
//              return sig;
//    }
//
//    public static boolean Verify(String m, String vk, String sig) {
//        CRF CRF_obj = new CRF(64);
//        String dgst = CRF_obj.Fn(m);
//        boolean ans = BoundedMsgVerify(dgst , vk , sig);
//        return ans;
//    }
public static String Sign(String m, String sk) {
    CRF objCrf=new CRF(64);
    String s=objCrf.Fn(m);
    assert s.length()==64;
    return BoundedMsgSign(s, sk);
}

    public static boolean Verify(String m, String vk, String sig) {
        CRF objCrf=new CRF(64);
        String s=objCrf.Fn(m);
        assert s.length()==64;
        return BoundedMsgVerify(s, vk, sig);
    }
}
