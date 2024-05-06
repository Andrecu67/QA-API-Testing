package factoryRequest;

import java.util.HashMap;
import java.util.Map;

public class FactoryRequest {

    public static IRequest make(String type){
        Map<String,IRequest> requestMap = new HashMap<>();
        requestMap.put("put",new RequestPut());
        requestMap.put("post",new RequestPost());
        requestMap.put("get",new RequestGet());
        requestMap.put("delete",new RequestDelete());

        return requestMap.containsKey(type.toLowerCase())?
                   requestMap.get(type.toLowerCase()):
                   requestMap.get("get");
    }

}
