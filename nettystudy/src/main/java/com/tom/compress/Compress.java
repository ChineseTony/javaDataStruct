package com.tom.compress;

public interface Compress {

    byte[] compress(byte[] bytes);

    byte[] decompress(byte[] bytes);
}
