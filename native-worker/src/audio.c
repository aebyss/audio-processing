#include "audio.h"

float normalize(float *samples, int count) {
    float max = 0.0f;
    for (int i = 0; i < count; i++) {
        float val = samples[i];
        if (val > max) max = val;
        if (-val > max) max = -val;
    }

    if (max == 0.0f) return 0.0f;

    for (int i = 0; i < count; i++) {
        samples[i] /= max;
    }

    return max;
}