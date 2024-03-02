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


    public Block(String hash, String previousHash, String data) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.data = data;
    }

    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = HashUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        data);
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
