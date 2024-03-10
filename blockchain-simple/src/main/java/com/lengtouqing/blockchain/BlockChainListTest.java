package com.lengtouqing.blockchain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * 简单的区块链实现
 */
public class BlockChainListTest {

    public static ArrayList<Block> blockchain = new ArrayList();

    public static void main(String[] args) {
        // 创世纪区块(头区块),0为其previousHash
        blockchain.add(new Block("first", "0"));
        blockchain.add(new Block("second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("third block", blockchain.get(blockchain.size() - 1).hash));

        // 通过json方式展示blockChain
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    /**
     * 循环区块链中的所有区块并且比较hash值，
     * 这个方法用来检查hash值是否是于计算出来的hash值相等，
     * 同时previousHash值是否和前一个区块的hash值相等。
     * 或许你会产生如下的疑问，我们就在一个主函数中创建区块链中的区块，所以不存在被修改的可能性，
     * 但是你要注意的是，区块链中的一个核心概念就是去中心化，
     * 每一个区块可能是在网络中的某一个节点中产生的，所以很有可能某个节点把自己节点中的数据修改了
     * ，那么根据上述的理论数据改变会导致整个区块链的破裂，也就是区块链就无效了
     * @return
     */
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            // 防止区块链数据篡改后未更新链表的情况
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes is wrong");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Current hashes is wrong");
                return false;
            }
        }
        return true;
    }
}
