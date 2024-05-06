package comparatorJSON;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ComparatorTest {
    private static JsonComparator jsonComparator;
    private static JSONObject jsonExpected;

    @BeforeAll
    public static void  setup(){
        jsonComparator = new JsonComparator();
        jsonExpected = new JSONObject();
        jsonExpected.put("Id",1234);
        jsonExpected.put("Content","Item Andrea");
        jsonExpected.put("ItemType","ignore");
        jsonExpected.put("Checked",false);
        jsonExpected.put("ProjectId","ignore");
        jsonExpected.put("ParentId","ignore");
        jsonExpected.put("Deleted",false);
    }

    @Test
    public void verifyCorrectComparisons(){
        JSONObject jsonActual = new JSONObject();
        jsonActual.put("Id", 1033);
        jsonActual.put("Content","Item Andrea");
        jsonActual.put("ItemType","9");
        jsonActual.put("Checked",false);
        jsonActual.put("ProjectId","9");
        jsonActual.put("ParentId","7");
        jsonActual.put("Deleted",false);
        Assertions.assertTrue(jsonComparator.compare(jsonExpected, jsonActual));
    }

    @Test
    public void verifyIncorrectComparisons(){
        JSONObject jsonActual = new JSONObject();
        jsonActual.put("Id", 1033);
        jsonActual.put("Content", "Iteeeem");
        jsonActual.put("ItemType","9");
        jsonActual.put("Checked",true);
        jsonActual.put("ProjectId","8");
        jsonActual.put("ParentId","7");
        jsonActual.put("Deleted",false);
        Assertions.assertFalse(jsonComparator.compare(jsonExpected,jsonActual),"Comparacion fallida");
    }

    @Test
    public void verifyIgnoredComparisons(){
        JSONObject jsonActual = new JSONObject();
        jsonActual.put("Id", 1033);
        jsonActual.put("Content", "Item Andrea");
        jsonActual.put("ItemType","9");
        jsonActual.put("Checked",false);
        jsonActual.put("ProjectId","8");
        jsonActual.put("ParentId","7");
        jsonActual.put("Deleted",false);
        Assertions.assertTrue(jsonComparator.compare(jsonExpected,jsonActual),"Comparacion fallida");
    }
}
