package hello.advanced.app.v2;

import hello.advanced.app.v1.OrderServiceV1;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV2 implements OrderServiceV1 {

    private final OrderRepositoryV2 orderRepository;
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
