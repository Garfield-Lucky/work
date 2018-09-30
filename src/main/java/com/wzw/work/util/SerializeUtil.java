package com.wzw.work.util;

import java.io.*;

public class SerializeUtil {


    /**
     * @Description: 序列化对象
     * @param
     * @author Created by wuzhangwei on 2018/9/24 14:57
     */
    public static byte[] serialize(Object obj){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);
            byte[] byteArray = baos.toByteArray();
            return byteArray;

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description: 反序列化对象
     * @param
     * @author Created by wuzhangwei on 2018/9/24 14:57
     */
    public static Object unSerialize(byte[] byteArray){
        ByteArrayInputStream bais = null;
        try {
            //反序列化为对象
            bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
