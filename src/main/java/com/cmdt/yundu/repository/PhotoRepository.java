package com.cmdt.yundu.repository;

import com.cmdt.yundu.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by dghcch on 2017/6/3.
 */
@Repository
@Table(name="photo")
public interface PhotoRepository extends CrudRepository<Photo,Long>{

    Photo save(Photo photo);
}
