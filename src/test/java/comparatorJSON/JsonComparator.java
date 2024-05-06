package comparatorJSON;

import org.json.JSONObject;

public class JsonComparator {
    public boolean compare(JSONObject expected, JSONObject actual){
        boolean AreEqual = true;
        for (String key : expected.keySet()) {
            if (actual.keySet().contains(key)) {
                if (!expected.get(key).toString().equals("ignore")) {
                    if (!actual.get(key).equals(expected.get(key))) {
                        AreEqual = false;
                        System.out.println("El resultado actual y el esperado no coinciden");
                    }
                }
            } else {
            AreEqual = false;
            System.out.println("No existe el elemento buscado");
            }
        }
        return AreEqual;
    }
}
