package com.tom.compress;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCompress  implements Compress{

    private static final  int BUFFER_SIZE  = 1024 * 8;

    @Override
    public  byte[] compress(byte[] bytes) {
        if (bytes == null){
            throw new RuntimeException("bytes is null");
        }
        try( ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)){
            gzip.write(bytes);
            gzip.flush();
            gzip.finish();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("gzip compress error", e);
        }
    }

    @Override
    public byte[] decompress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null");
        }
        if (bytes.length < 2){
            throw new RuntimeException("not has gzip magic");
        }
        //check gzip magic
        int b0 = bytes[0];
        int b1 = bytes[1];
        int b = (b1 & 0xFF) << 8 | b0;
        if (b != GZIPInputStream.GZIP_MAGIC){
            throw new RuntimeException("not gzip compress");
        }
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
                 GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(bytes))){
            byte[] buffer = new byte[BUFFER_SIZE];
            int n;
            while ((n = gzip.read(buffer)) > -1){
                out.write(buffer,0,n);
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("gzip compress error", e);
        }
    }

    public static void main(String[] args) {
        String s = "I am a student";
        byte[] bytes = GzipUtil.compress(s.getBytes());
        System.out.println(new String(GzipUtil.decompress(bytes)));
    }
}
