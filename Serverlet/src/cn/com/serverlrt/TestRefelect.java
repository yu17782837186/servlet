package cn.com.serverlrt;

import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("all")
public class TestRefelect {
    public static void main(String[] args) {
        //获取反射的三种方式
        //1 对象.getClass()
        Iphone iphone = new Iphone();
        Class clzz = iphone.getClass();
        //2 类.class
        clzz = Iphone.class;
        //3 Class.forName("包名+类名")
        try {
            clzz = Class.forName("cn.com.serverlet.Iphone");
            //创建对象
//            Iphone iphone1 = (Iphone) clzz.newInstance(); //不推荐使用
//            System.out.println(iphone1);
            Iphone iphone1 = (Iphone) clzz.getConstructor().newInstance();
            System.out.println(iphone1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class Iphone {
    public Iphone() {
    }
}