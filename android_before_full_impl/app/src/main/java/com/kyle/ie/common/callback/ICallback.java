package com.kyle.ie.common.callback;

/**
 * Created by Kyle on 18/01/2017.
 */
public interface ICallback <T> {

    void callback(Payload<T> payload);
}
