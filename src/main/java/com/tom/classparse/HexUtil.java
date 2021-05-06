package com.tom.classparse;

/**
 * @author tom 
 */
public class HexUtil {


    private HexUtil(){

    }

    private static final char[] HexCharArr =
            {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private static final String HexStr = "0123456789abcdef";


    // int 4 个字节
    public static byte[] toBytes(int number){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)number;
        bytes[1] = (byte) (number >> 8);
        bytes[2] = (byte) (number >> 16);
        bytes[3] = (byte) (number >> 24);
        return bytes;
    }

    public static int toInt(byte[] bytes){
        int number = 0;
        for(int i = 0; i < 4 ; i++){
            number += bytes[i] << i*8;
        }
        return number;
    }



    public static String byteArrToHex(byte[] btArr) {
        char[] chars = new char[btArr.length * 2];
        int i = 0;
        for (byte bt : btArr) {
            chars[i++] = HexCharArr[bt>>>4 & 0xf];
            chars[i++] = HexCharArr[bt & 0xf];
        }
        return new String(chars);
    }

    public static byte[] hexToByteArr(String hexStr) {
        char[] charArr = hexStr.toCharArray();
        byte[] btArr = new byte[charArr.length / 2];
        int index = 0;
        for (int i = 0; i < charArr.length; i++) {
            int highBit = HexStr.indexOf(charArr[i]);
            int lowBit = HexStr.indexOf(charArr[++i]);
            btArr[index] = (byte) (highBit << 4 | lowBit);
            index++;
        }
        return btArr;
    }
}
