package com.quick.file;

public class test {
    public static void main(String [] args) {


        String username = UUIDGenerator.getUUID();
        System.out.println(username);
        System.out.println(username.length());


        System.out.println(getExtensionName("qf1604469642922.jpg"));

//        String url = "http://localhost:8888//statics/carousel/qf1604469642922.jpg";
//        String a  ="carousel";
//
//        System.out.print("返回值 :" );
//        System.out.println(url.substring(url.indexOf(a)));
//
//        System.out.print("返回值 :" );
//			System.out.println(Str.substring(4, 10) );
    }

    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }

        return null;
    }
}

