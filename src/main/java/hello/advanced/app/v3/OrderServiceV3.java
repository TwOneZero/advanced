package hello.advanced.app.v3;

import hello.advanced.app.v1.OrderServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderServiceV3 implements OrderServiceV1 {

    private final OrderRepositoryV3 orderRepository;
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
