package com.quick.file;

import java.util.UUID;

public class UUIDGenerator {

    public UUIDGenerator() {
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        System.out.println(str);
        // 去掉"-"符号
        String temp = str.replaceAll("-","");
        return temp;
    }
    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String[] args) {
        String[] ss = getUUID(10);
        for (int i = 0; i < ss.length; i++) {
            System.out.println("ss["+i+"]====="+ss[i]);
        }
    }
}
