package com.wordfq.service.loader;

import com.wordfq.service.builder.EntityBuilder;
import com.wordfq.service.exception.FileLoaderException;
import com.wordfq.service.processor.EntityProcessor;

import java.io.IOException;

/**
 * Created by wiseman on 7/2/16.
 */
public abstract class AbstractFileLoader implements FileLoaderService {

    protected EntityBuilder builder = null;

    protected EntityProcessor processor = null;

    protected String fileName;

    public AbstractFileLoader(EntityBuilder builder, EntityProcessor processor) {
        this.builder = builder;
        this.processor = processor;
    }

    @Override
    public void loadFile(String fileName) throws IOException {
        this.fileName = fileName;
        if (preProcessValidation()) {
            try {
                perform();
            } catch (IOException e) {
                throw new FileLoaderException("Couldn't load: " + fileName, e);
            }
        }
    }

    /**
     * Option to perform pre file processing validation (e.g. filename, file suffix, etc.)
     */
    protected boolean preProcessValidation() {
        return true;
    }


    protected abstract void perform() throws IOException;
}
