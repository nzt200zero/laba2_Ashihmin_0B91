package ConsumerProduser;

import  java.util.LinkedList;

public class Container {
    LinkedList<Integer> list = new LinkedList<Integer>();
    int content = 10;

    public void put(int value){
        while (true){
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this){
                while (list.size() == content){
                    System.out.println("Контейнер заполнен,ждите пока освободится место... ");
                    try {
                        wait();
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Производитель-"+ Thread.currentThread().getName()+" -поместил:" + value);
                list.add(value++);

                notifyAll();
            }
        }
    }
    public Integer take(){
        Integer val = 0;
        while (true){
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (this){

                while (list.size() == 0){
                    System.out.println("Контейнер пуст, ожидайте продукцию...");
                    try {
                        wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                val = list.removeFirst();
                System.out.println("Покупатель-"+ Thread.currentThread().getName()+" -Забрал:" + val);

                notifyAll();
            }
        }
    }
}
