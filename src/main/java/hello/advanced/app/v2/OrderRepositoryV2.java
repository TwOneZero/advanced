package hello.advanced.app.v2;

public class OrderRepositoryV2 {
    public void save(String itemId) {
        //저장 로직
        if (itemId.equals("ex")){
            throw new IllegalStateException("예외 발생");
        }
        sleep(1000);
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
