package com.NorthKingSys.jbf.cn.util;

public class SnowflakeIdWorkerUntil {
    static SnowflakeIdWorker idWorker= new SnowflakeIdWorker(0,0);
    public static synchronized long nextId(){
        return  idWorker.nextId();
    }

    public static String usrIdNextId(){
         String str="JBF_"+String.valueOf(nextId()).substring(9);
         return str;
    }

    /**
     * 菜单id
     * @return
     */
    public static String menuNextId(){
        String str="M_"+String.valueOf(nextId()).substring(9);
        return str;
    }

    /**
     * 角色id
     * @return
     */
    public static String roleIdNextId(){
        String str="JBF_"+String.valueOf(nextId()).substring(5);
        return str;
    }
//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//                       long id = idWorker.nextId();
//                  System.out.println(Long.toBinaryString(id));
//                  System.out.println(id);
//            System.out.println(String.valueOf(id).substring(9));
//        }
//    }
}
