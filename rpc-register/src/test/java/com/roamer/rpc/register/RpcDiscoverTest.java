package com.roamer.rpc.register;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RpcDiscoverTest {
    private RpcDiscover rpcDiscover;

    @Before
    public void setUp() throws Exception {
        rpcDiscover = new RpcDiscover("localhost:2181");
    }

    @After
    public void tearDown() throws Exception {
        rpcDiscover = null;
    }

    @Test
    public void discover() throws IOException {
        System.out.println(rpcDiscover.discover());
        System.in.read();
    }
}