package nogikoi.erkikt.rhcloud.com.app.nogiitem;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class NogiItemForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6969527235104668151L;
    @NotNull
    private String name;
    @NotNull
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
