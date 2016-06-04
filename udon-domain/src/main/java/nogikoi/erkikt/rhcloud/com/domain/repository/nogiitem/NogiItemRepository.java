package nogikoi.erkikt.rhcloud.com.domain.repository.nogiitem;

import java.util.List;

import nogikoi.erkikt.rhcloud.com.domain.model.NogiItem;

public interface NogiItemRepository {

    NogiItem findOne(Integer id);

    List<NogiItem> findAllByUserId(String userId);

    boolean plus(Integer id);

    boolean minus(Integer id);

    boolean create(NogiItem nogiItem);

    boolean delete(Integer id);
}
