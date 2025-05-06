package com.sw19.sofa.domain.folder.dto;

import com.sw19.sofa.domain.folder.entity.Folder;
import com.sw19.sofa.global.util.EncryptionUtil;

public record FolderDto(String id, String name) {
    public FolderDto(Folder folder){
        this(folder.getEncryptId(),folder.getName());
    }

    public Long encryptionId(){
        return EncryptionUtil.decrypt(id);
    }
}