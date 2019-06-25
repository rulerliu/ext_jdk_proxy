import com.liuwq.proxy.MyInvocationHandler;
import com.liuwq.service.OrderService;
import com.liuwq.service.impl.OrderServiceImpl;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/6/20 0020 下午 4:35
 * @version: V1.0
 */
public class Test001 {

    public static void main(String[] args) throws Throwable {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        MyInvocationHandler handler = new MyInvocationHandler(new OrderServiceImpl());
        OrderService proxy = handler.getProxyInstance();
        proxy.add();

    }

}
