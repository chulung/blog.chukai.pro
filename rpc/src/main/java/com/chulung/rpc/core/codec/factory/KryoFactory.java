package com.chulung.rpc.core.codec.factory;

import com.esotericsoftware.kryo.Kryo;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by chulung on 2017/3/9.
 */
public class KryoFactory extends BasePooledObjectFactory<Kryo> {

    @Override
    public Kryo create() throws Exception {
        return new Kryo();
    }

    @Override
    public PooledObject<Kryo> wrap(Kryo kryo) {
        return new DefaultPooledObject<>(kryo);
    }
}
