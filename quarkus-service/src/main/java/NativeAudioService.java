import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import com.normalize.audio.audio_h;

public class NativeAudioService {

    public float normalize(float[] samples) {
        try(Arena arena = Arena.ofConfined()) {
            MemorySegment input = arena.allocateArray(ValueLayout.JAVA_FLOAT, samples);
            return audio_h.normalize(input, samples.length);
        }
    }

}
