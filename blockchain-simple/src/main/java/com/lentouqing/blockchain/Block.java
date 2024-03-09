package com.lentouqing.blockchain;

import com.lentouqing.blockchain.util.HashUtil;

import java.util.Date;

/**
 * 单个区块，包含自身hash值，存储的data,下一个区块的hash值
 */
public class Block {

    /**
     * 当前区块的hash(通过之前hash值和data通过hash计算出来，前一个区块数据篡改，则此区块的hash值也需做修改)
     */
    public String hash;

    /**
     * 前一个区块的hash
     */
    public String previousHash;

    /**
     * 当前区块的数据
     */
    private String data;

    /**
     * 时间戳
     */
    private long timeStamp;

    /**
     * 挖矿证明
     */
    private int nonce;

    public Block(String hash, String previousHash, String data) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
    }

    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = HashUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) + Integer.toString(nonce) +
                        data);
        return calculatedhash;
    }

    /**
     * 在比特币网络中所有的网络节点都分享了它们各自的区块链，然而最长的有效区块链是被全网所统一承认的，
     * 如果有人恶意来篡改之前的数据，然后创建一条更长的区块链并全网发布呈现在网络中，我们该怎么办呢？
     * 这就涉及到了区块链中另外一个重要的概念工作量证明，这里就不得不提及一下hashcash(参考此文档理解https://www.jianshu.com/p/1971c474ecef)
     *
     * @param difficulty 挖矿难度 低的难度比如1和2，普通的电脑基本都可以马上计算出来，我的建议是在4-6之间进行测试，普通电脑大概会花费3秒时间，在莱特币中难度大概围绕在442592左右
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        // 要求需以指定hash值信息匹配
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
