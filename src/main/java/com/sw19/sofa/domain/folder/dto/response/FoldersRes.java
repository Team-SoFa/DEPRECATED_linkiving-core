package com.sw19.sofa.domain.folder.dto.response;

import java.util.List;

import com.sw19.sofa.domain.folder.dto.FolderDto;

public record FoldersRes(List<FolderDto> folders) {
}
