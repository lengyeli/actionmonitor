package com.lengyeli.actionmonitor.repository;

import com.lengyeli.actionmonitor.model.SampleData;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ilengyel on 2017. 10. 09..
 */
public interface SampleDataRepository extends CrudRepository<SampleData, Long> {

    void deleteById(Long id);

    SampleData findById(Long id);
}
