package ConsumerProduser;

public class Consumer implements Runnable {
    public Container container;
    public Consumer (Container container) {
        this.container = container;
    }

    @Override
    public void run(){
        Integer val = container.take();
    }
}
