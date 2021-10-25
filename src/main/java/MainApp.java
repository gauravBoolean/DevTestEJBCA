import org.bouncycastle.cert.ocsp.CertificateStatus;
import org.bouncycastle.cert.ocsp.SingleResp;

import java.io.IOException;
import java.util.function.Consumer;

public class MainApp {

    public static void main(String[] args) throws IOException {
        OCSPReponseParser parser = new OCSPReponseParser();
        parser.parseBasicOCSPReponse(printResponseInfo);
    }

    public static Consumer<SingleResp> printResponseInfo = singleResp -> {
        CertificateStatus status = singleResp.getCertStatus();
        System.out.println("Status: " + (status == CertificateStatus.GOOD ? "Good" : status));
        System.out.println("This Update: " + singleResp.getThisUpdate());
        System.out.println("Next Update: " + singleResp.getCertID().getHashAlgOID().getId());
    };
}
