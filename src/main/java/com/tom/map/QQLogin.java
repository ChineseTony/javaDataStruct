package com.tom.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author WangTao
 *
 *
 * 5
 * L 1234567890 myQQ@qq.com
 * N 1234567890 myQQ@qq.com
 * N 1234567890 myQQ@qq.com
 * L 1234567890 myQQ@qq
 * L 1234567890 myQQ@qq.com
 *
 * 1）若新申请帐户成功，则输出“New: OK”；
 * 2）若新申请的号码已经存在，则输出“ERROR: Exist”；
 * 3）若老帐户登陆成功，则输出“Login: OK”；
 * 4）若老帐户QQ号码不存在，则输出“ERROR: Not Exist”；
 * 5）若老帐户密码错误，则输出“ERROR: Wrong PW”。
 */
public class QQLogin {
    private static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        for (int i = 0; i <= num; i++) {
            String tmp = scanner.nextLine();
            String[] arr = tmp.split(" ");
//            登录
            if ("L".equalsIgnoreCase(arr[0])){
                if (!map.containsKey(arr[1])){
                    System.out.println("ERROR: Not Exist");
                }else if (map.get(arr[1]).equals(arr[2])){
                    System.out.println("Login: OK");
                }else {
                    System.out.println("ERROR: Wrong PW");
                }
            }else if ("N".equalsIgnoreCase(arr[0])){
                if (map.containsKey(arr[1])){
                    System.out.println("ERROR: Exist");
                }else {
                    map.put(arr[1],arr[2]);
                    System.out.println("New: OK");
                }
            }
        }
    }
}