package com.sw19.sofa.domain.folder.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sw19.sofa.domain.folder.dto.FolderDto;
import com.sw19.sofa.domain.folder.dto.request.FolderReq;
import com.sw19.sofa.domain.folder.dto.response.FolderRes;
import com.sw19.sofa.domain.folder.dto.response.FoldersRes;
import com.sw19.sofa.domain.folder.entity.Folder;
import com.sw19.sofa.domain.linkcard.service.LinkCardService;
import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.common.constants.Constants;
import com.sw19.sofa.global.util.EncryptionUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderMangeService {
	private final FolderService folderService;
	private final LinkCardService linkCardService;

	@Transactional(readOnly = true)
	public FoldersRes getFolderList(Member member) {

		List<FolderDto> folderDtos = folderService.getFolderList(member);

		Optional<FolderDto> minEncryptedIdFolder = folderDtos.stream()
			.filter(f -> "휴지통".equals(f.name()))
			.min(Comparator.comparingLong(FolderDto::encryptionId));

		List<FolderDto> ret = folderDtos.stream()
			.filter(f -> minEncryptedIdFolder.isEmpty() || !minEncryptedIdFolder.get().id().equals(f.id()))
			.toList();

		return new FoldersRes(ret);
	}

	@Transactional
	public FoldersRes addFolder(Member member, FolderReq req) {
		folderService.addFolder(member, req.name());
		List<FolderDto> folders = folderService.getFolderList(member);
		return new FoldersRes(folders);
	}

	@Transactional
	public void delFolder(String encryptId, Member member) {
		Long id = EncryptionUtil.decrypt(encryptId);
		Folder folder = folderService.getFolder(id);
		Folder recycleBinFolder = folderService.getFolderByNameAndMemberOrElseThrow(Constants.recycleBinName, member);
		linkCardService.editLinkCardListInSrcFolderByDstFolder(folder, recycleBinFolder);

		folderService.delFolder(folder);
	}

	@Transactional
	public FolderRes editFolder(String encryptId, FolderReq req) {
		Long id = EncryptionUtil.decrypt(encryptId);

		Folder folder = folderService.getFolder(id);
		return folderService.editFolder(folder, req.name());
	}
}
