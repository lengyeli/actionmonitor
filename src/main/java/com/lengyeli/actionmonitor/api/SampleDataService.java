package com.lengyeli.actionmonitor.api;

import com.lengyeli.actionmonitor.model.SampleData;

/**
 * Created by ilengyel on 2017. 10. 09..
 */
public interface SampleDataService {

    SampleData findById(Long id);

    SampleData createSampleData(String text);

    void update(Long id, String text);

    void delete(Long id);
}
