package com.lentouqing.blockchain;

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
}
