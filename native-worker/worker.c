#include <zmq.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "audio.h"


int main() {

    void* context = zmq_ctx_new();
    void* responder = zmq_socket(context, ZMQ_REP);

    zmq_bind(responder, "tcp://*:5555");

    while(1) {
        int count = 0;
        zmq_recv(responder, &count, sizeof(int), 0);

        float* buffer = malloc(sizeof(float) * count);
        zmq_recv(responder, buffer, sizeof(float) * count, 0);

        normalize(buffer, count);

        zmq_send(responder, &count, sizeof(int), ZMQ_SNDMORE);
        zmq_send(responder, buffer, sizeof(float) * count, 0);

        free(buffer);
    }
    
    zmq_close(responder);
    zmq_ctx_destroy(context);
    return 0;

}
