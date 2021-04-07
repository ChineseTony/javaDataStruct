package com.tom.array;

/**
 *
 */
public class Mutil {

        public static void main(String[] args) {
            int[] a = new int[]{1,2,3};
            int[] b = new int[] {2,3};
            int[] c = mulitArr2(a,b);
//            for(int i:c){
//                System.out.print(i + " ");
//            }
        }

    public static int[] mulitArr2(int[] a,int[] b){
        int tmp = 1;
        int sum = 0;
        for (int i = a.length-1; i >=0 ; i--) {
            sum += a[i] * tmp;
            tmp = tmp * 10;
        }
        int tmpValue = 1;
        int sumValue = 0;
        for (int i = b.length-1; i >=0 ; i--) {
            sumValue += b[i] * tmpValue;
            tmpValue = tmpValue * 10;
        }
        System.out.println(sumValue * sum);
//        System.out.println(tmpValue);
//        int value = tmpValue * tmp;
//        System.out.println(value);
        return new int[]{};
    }

        public static int[] mulitArr(int[] a,int[] b){
            int totalLen = a.length + b.length;
            int[] result = new int[totalLen];
            int index = totalLen - 1;
            int i = a.length - 1;
            int j = b.length - 1;
            while(j >= 0){
                int tmp = i;
                //进位
                int jin = 0;
                while(tmp >= 0){
                    int tmpValue = b[j] * a[tmp] + jin;
                    //放到数组里面
                    System.out.print(tmpValue+":"+(tmp+1) * (j+1)+"--->" );
                    result[tmp * j ] += tmpValue % 10 ;
                    jin = tmpValue / 10 ;
                    tmp--;
                }
                System.out.println();
                j--;
            }
            return result;
        }

}
