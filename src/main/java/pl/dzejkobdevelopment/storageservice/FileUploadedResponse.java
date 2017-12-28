package pl.dzejkobdevelopment.storageservice;

import lombok.Getter;

/**
 * Created by Jakub Michałowski on 01.11.2017.
 * All rights reserved.
 */
public class FileUploadedResponse extends UploadingResponse{
    @Getter
    String link;

    public FileUploadedResponse(String link){
        this.link = link;
    }
}
