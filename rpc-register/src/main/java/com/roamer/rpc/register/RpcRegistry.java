package com.roamer.rpc.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * RPC服务注册
 *
 * @author roamer
 * @version V1.0
 * @date 2018/11/8 23:21
 */
@Setter
@Getter
@AllArgsConstructor()
@NoArgsConstructor
public class RpcRegistry {
    public static final Logger LOGGER = LoggerFactory.getLogger(RpcRegistry.class);


    /**
     * zkServer的地址信息
     */
    private String registryAddress;
    /**
     * zk客户端程序
     */
    private ZooKeeper zooKeeper;

    /**
     * 创建节点
     *
     * @param data
     * @throws Exception
     */
    public void createNode(String data) throws Exception {
        //创建一个客户端程序, 对于注册可以不用监听事件
        zooKeeper = new ZooKeeper(registryAddress, Constant.SESSION_TIMEOUT, event -> {
        });
        if (Objects.isNull(zooKeeper)) {
            try {
                //判断注册的目录是否存在
                Stat stat = zooKeeper.exists(Constant.REGISTRY_PATH, false);
                //如果不存在, 创建一个持久的节点目录
                if (Objects.isNull(stat)) {
                    zooKeeper.create(Constant.REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                //创建一个临时的序列节点,并且保存数据信息
                zooKeeper.create(Constant.DATA_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            } catch (Exception e) {
                LOGGER.error("创建zooKeeper节点异常", e);
            }
        } else {
            LOGGER.error("zooKeeper connect is null");
        }
    }
}
