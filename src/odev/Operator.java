package odev;

import java.util.Collections;
import java.util.List;

public class Operator {

    private final String str;
    private final List<String> types;

    public Operator(String str, String type) {
        this.str = str;
        this.types = Collections.singletonList(type);
    }

    public Operator(String str, List<String> types) {
        this.str = str;
        this.types = types;
    }

    public String getStr() {
        return str;
    }

    public List<String> getTypes() {
        return types;
    }
}
