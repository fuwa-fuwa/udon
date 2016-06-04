package nogikoi.erkikt.rhcloud.com.domain.model;

import java.io.Serializable;

public class NogiItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8009839720708483324L;
    private Integer id;
    private String name;
    private Integer count;
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
