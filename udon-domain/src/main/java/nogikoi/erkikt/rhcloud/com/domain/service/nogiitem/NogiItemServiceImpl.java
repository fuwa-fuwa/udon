package nogikoi.erkikt.rhcloud.com.domain.service.nogiitem;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import nogikoi.erkikt.rhcloud.com.domain.model.NogiItem;
import nogikoi.erkikt.rhcloud.com.domain.repository.nogiitem.NogiItemRepository;

@Transactional
@Service
public class NogiItemServiceImpl implements NogiItemService {

    @Inject
    NogiItemRepository nogiItemRepository;

    @Transactional(readOnly = true)
    @Override
    public NogiItem getNogiItem(Integer id) {
        NogiItem nogiItem = nogiItemRepository.findOne(id);
        if (nogiItem == null) {
            throw new ResourceNotFoundException(ResultMessages.error().add("e.ex.ng.5001", id));
        }
        return nogiItem;
    }

    @Transactional(readOnly = true)
    @Override
    public List<NogiItem> getAllNogiItems(String userId) {
        return nogiItemRepository.findAllByUserId(userId);
    }

    @Override
    public boolean plusCount(Integer id) {
        return nogiItemRepository.plus(id);
    }

    @Override
    public boolean minusCount(Integer id) {
        return nogiItemRepository.minus(id);
    }

    @Override
    public boolean addNogiItem(NogiItem nogiItem) {
        return nogiItemRepository.create(nogiItem);
    }

    @Override
    public boolean deleteNogiItem(Integer id) {
        return nogiItemRepository.delete(id);
    }

}
