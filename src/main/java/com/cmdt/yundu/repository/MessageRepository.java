package com.cmdt.yundu.repository;

import com.cmdt.yundu.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by dingguohua on 2017/5/27.
 */
@Repository
@Table(name="message")
public interface MessageRepository extends CrudRepository<Message, Long > {

    Message save(Message message);
}
