import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/normalize")
public class NormalizeResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NormalizedResult normalize(AudioRequest request) {
        // Simulate normalization logic
        return new NormalizedResult("normalized-" + request.fileName());
    }

    public record AudioRequest(String fileName) {}
    public record NormalizedResult(String result) {}
}
