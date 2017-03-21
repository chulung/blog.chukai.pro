package com.chulung.rpc.core.codec.impl;

import com.chulung.rpc.core.codec.AbstractRpcCodec;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chulung on 2017/3/9.
 */
public class KryoCodec extends AbstractRpcCodec {
    @Override
    public byte[] doEncode(Object request) throws Exception {
        return new byte[0];
    }

    @Override
    public Object doDecode(byte[] input) throws Exception {
        return null;
    }

    @Override
    public void doEncode(Object request, OutputStream outPut) throws Exception {

    }

    @Override
    public Object doDecode(InputStream input) throws Exception {
        return null;
    }

}
