import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.SingleResp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class OCSPReponseParser {

    private final OCSPRepo ocspRepo;
    public OCSPReponseParser(){
        this.ocspRepo = new OCSPRepo();
    }

    public static Function<OCSPResp, BasicOCSPResp> fetchBasicOCSPResp = ocspResp -> {
        try {
            return  (BasicOCSPResp) ocspResp.getResponseObject();
        } catch (OCSPException e) {
            e.printStackTrace();
        }
        return null;
    };



    public void parseBasicOCSPReponse(Consumer<SingleResp> respConsumer){
        try {
            BasicOCSPResp basicResponse = fetchBasicOCSPResp.apply(new OCSPResp(ocspRepo.fetchOCSPData()));
            System.out.println("array length " + basicResponse.getResponses().length);

            Arrays.stream(Optional.ofNullable(basicResponse.getResponses()).orElse(null)).forEach(respConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
