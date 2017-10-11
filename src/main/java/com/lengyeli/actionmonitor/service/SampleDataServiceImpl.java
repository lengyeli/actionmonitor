package com.lengyeli.actionmonitor.service;

import com.lengyeli.actionmonitor.api.SampleDataService;
import com.lengyeli.actionmonitor.model.SampleData;
import com.lengyeli.actionmonitor.repository.SampleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for managing SampleData operation
 * Created by ilengyel on 2017. 10. 09..
 */
@Service
@Transactional
public class SampleDataServiceImpl implements SampleDataService {

    private final SampleDataRepository sampleDataRepository;

    @Autowired
    public SampleDataServiceImpl(SampleDataRepository sampleDataRepository) {
        this.sampleDataRepository = sampleDataRepository;
    }

    @Override
    public SampleData findById(Long id) {
        return sampleDataRepository.findById(id);
    }

    @Override
    public SampleData createSampleData(String text) {
        SampleData data = new SampleData();
        data.setText(text);

        return sampleDataRepository.save(data);
    }

    @Override
    public void update(Long id, String text) {
        SampleData oldaSampleData = sampleDataRepository.findOne(id);
        oldaSampleData.setText(text);

        sampleDataRepository.save(oldaSampleData);
    }

    @Override
    public void delete(Long id) {
        sampleDataRepository.deleteById(id);
    }
}
