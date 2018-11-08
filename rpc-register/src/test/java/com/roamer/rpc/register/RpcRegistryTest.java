package com.roamer.rpc.register;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RpcRegistryTest {

    private RpcRegistry rpcRegistry;

    @Before
    public void setUp() throws Exception {
        rpcRegistry = new RpcRegistry();
    }

    @After
    public void tearDown() throws Exception {
        rpcRegistry = null;
    }

    @Test
    public void createNode() throws Exception {
        rpcRegistry.setRegistryAddress("localhost:2181");
        rpcRegistry.createNode("testdata");
        //让程序等待输入,程序一直处于运行状态
        System.in.read();
    }
}