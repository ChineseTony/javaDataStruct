package com.tom.compress;

public class GzipUtil {

    private static final GzipCompress gzipCompress = new GzipCompress();

    private GzipUtil(){

    }


    public static byte[] compress(byte[] bytes){
        return gzipCompress.compress(bytes);
    }

    public static byte[] decompress(byte[] bytes){
        return gzipCompress.decompress(bytes);
    }


}
