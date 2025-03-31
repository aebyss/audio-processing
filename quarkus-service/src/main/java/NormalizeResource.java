import com.normalize.audio.audio_h;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

@Path("/normalize")
public class NormalizeResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NormalizedResult normalize(AudioRequest request) {
        //TODO Send input to ZeroMQ Socket
        //TODO Wait for Worker Response
        return new NormalizedResult("normalized-" + request.fileName());
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String normalizeTest() {
        float[] samples = { 0.5f, -0.75f, 1.0f };

        try (var arena = Arena.ofConfined()) {
            MemorySegment segment = arena.allocateArray(ValueLayout.JAVA_FLOAT, samples);
            float result = audio_h.normalize(segment, samples.length);
            return "Normalized max: " + result;
        }
    }

    public record AudioRequest(String fileName) {}
    public record NormalizedResult(String result) {}
}
