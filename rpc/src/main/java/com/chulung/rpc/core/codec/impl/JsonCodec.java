package com.chulung.rpc.core.codec.impl;

import com.chulung.common.util.JsonUtil;
import com.chulung.rpc.core.codec.AbstractRpcCodec;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chululng on 2017/3/9.
 */
public class JsonCodec extends AbstractRpcCodec {
    @Override
    public byte[] doEncode(Object request) throws Exception {
        return JsonUtil.writeBytes(new ObjectWrapper(request));
    }

    @Override
    public Object doDecode(byte[] input) throws Exception {
        return JsonUtil.readValue(input,ObjectWrapper.class).getObject();
    }

    @Override
    public void doEncode(Object request, OutputStream outPut) throws Exception {
            outPut.write(JsonUtil.writeBytes(new ObjectWrapper(request)));
    }

    @Override
    public Object doDecode(InputStream input) throws Exception {
        return JsonUtil.readValue(input, ObjectWrapper.class).getObject();
    }

    /**
     * Unable to get the type of the object from JSON, repackaging
     */
    private static class ObjectWrapper {
        private Object object;

        public ObjectWrapper(Object object) {
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}
