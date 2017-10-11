package com.lengyeli.actionmonitor.controller;

import com.lengyeli.actionmonitor.api.SampleDataService;
import com.lengyeli.actionmonitor.model.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for ActionMonitor Application
 * Created by ilengyel on 2017. 10. 09..
 */
@RestController
public class ActionRestController {

    private static Logger logger = LoggerFactory.getLogger(ActionRestController.class);

    @Value("${application.version}")
    private String applicationVersion;

    private final SampleDataService sampleDataService;

    @Autowired
    public ActionRestController(SampleDataService sampleDataService) {
        this.sampleDataService = sampleDataService;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status() {
        return "OK";
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version() {
        return applicationVersion;
    }

    /**
     * Manual testing helper endpoint. {@link RequestMethod#GET} for simple URL based test in browser
     *
     * @param text {@link SampleData#text}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/insert/{text}", method = RequestMethod.GET)
    public ResponseEntity<SampleData> insert(@PathVariable String text) {
        logger.info("creating new SampleData with text: {}", text);

        SampleData data = sampleDataService.createSampleData(text);

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    /**
     * Manual testing helper endpoint. {@link RequestMethod#GET} for simple URL based test in browser
     *
     * @param id {@link SampleData#id}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        logger.info("deleting SampleData with id: {}", id);

        SampleData sampleData = sampleDataService.findById(id);

        if (sampleData == null) {
            logger.error("Unable to delete. SampleData with id {} not found", id);
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        sampleDataService.delete(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    /**
     * Manual testing helper endpoint. {@link RequestMethod#GET} for simple URL based test in browser
     *
     * @param id   {@link SampleData#id}
     * @param text {@link SampleData#text}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(value = "/update/{id}/{text}", method = RequestMethod.GET)
    public ResponseEntity<String> updateMessage(@PathVariable Long id, @PathVariable String text) {
        logger.info("updating SampleData with id: {}", id);

        SampleData sampleData = sampleDataService.findById(id);

        if (sampleData == null) {
            logger.error("Unable to update. SampleData with id {} not found", id);
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        sampleDataService.update(id, text);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
