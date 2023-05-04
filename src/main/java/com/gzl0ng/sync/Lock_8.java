package com.gzl0ng.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author:郭正龙
 * @data:2023/4/14
 *
 * 8种锁(是否同一把锁，锁范围)
 * 1-2 同一个对象二个同步方法，二个线程分别调用，先调用先执行，就算第一个方法停4秒钟第二个方法也无法执行另一个方法（同理3也是）
 * 4-5
 *
 * 1.标准访问，先打印短信还是邮件
 * -----sendSMS
 * ------sendEmil
 * 2.停4秒在短信方法内，先打短信还是邮件
 * -----sendSMS
 * ------sendEmil
 *
 * 3.新增普通的hello方法，是先打短信还是hello
 * ------getHello
 * -----sendSMS
 * 4.现在有二部手机，先打印短信还是邮件
 * ------sendEmil
 * -----sendSMS
 * 5.二个静态同步方法，1部手机，先打印短信还是邮件（锁变成Class字节码对象）
 * -----sendSMS
 * ------sendEmil
 * 6.二个静态同步方法，2部手机，先打印短信还是邮件
 * -----sendSMS
 * ------sendEmil
 * 7.1个静态同步方法，一个普通同步方法，1部手机，先打印短信还是邮件
 * ------sendEmil
 * -----sendSMS
 * 8.1个静态同步方法，一个普通同步方法，2部手机，先打印短信还是邮件
 * ------sendEmil
 * -----sendSMS
 *
 * 总结：同步方法锁对象为被调用者本身，加过static后为Class字节码对象
 */
class Phone{
    public synchronized static void sendSMS()throws Exception{
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----sendSMS");
    }

    public synchronized void sendEmail()throws Exception{
        System.out.println("------sendEmil");
    }

    public void getHello(){
        System.out.println("------getHello");
    }
}

public class Lock_8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(()->{
            try {
//                phone.sendEmail();
//                phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },"BB").start();
    }
}
