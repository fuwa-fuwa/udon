package nogikoi.erkikt.rhcloud.com.domain.service.nogiitem;

import java.util.List;

import nogikoi.erkikt.rhcloud.com.domain.model.NogiItem;

public interface NogiItemService {

    NogiItem getNogiItem(Integer id);

    List<NogiItem> getAllNogiItems(String userId);

    boolean plusCount(Integer id);

    boolean minusCount(Integer id);

    boolean addNogiItem(NogiItem nogiItem);

    boolean deleteNogiItem(Integer id);

}