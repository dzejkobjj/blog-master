package pl.dzejkobdevelopment.storageservice;

import lombok.Getter;

/**
 * Created by Jakub Michałowski on 01.11.2017.
 * All rights reserved.
 */
public class FileErrorResponse extends UploadingResponse{
    @Getter
    String error;

    public FileErrorResponse(String error){
        this.error = error;
    }
}
