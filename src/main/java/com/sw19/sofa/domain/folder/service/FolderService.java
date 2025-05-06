package com.sw19.sofa.domain.folder.service;

import static com.sw19.sofa.domain.folder.error.FolderErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sw19.sofa.domain.folder.dto.FolderDto;
import com.sw19.sofa.domain.folder.dto.response.FolderRes;
import com.sw19.sofa.domain.folder.entity.Folder;
import com.sw19.sofa.domain.folder.repository.FolderRepository;
import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {

	private final FolderRepository folderRepository;

	@Transactional(readOnly = true)
	public List<FolderDto> getFolderList(Member member) {
		return folderRepository.findAllByMember(member).stream().map(FolderDto::new).toList();
	}

	@Transactional
	public Folder addFolder(Member member, String name) {
		Folder folder = Folder.builder()
			.member(member)
			.name(name)
			.build();
		return folderRepository.save(folder);
	}

	@Transactional
	public void delFolder(Folder folder) {
		folderRepository.delete(folder);
	}

	@Transactional
	public FolderRes editFolder(Folder folder, String name) {
		folder.edit(name);
		Folder save = folderRepository.save(folder);
		return new FolderRes(save);
	}

	public Folder getFolder(Long id) {
		return folderRepository.findById(id).orElseThrow(() -> new BusinessException(NOT_FOUND_FOLDER));
	}

	public Folder getFolderByNameAndMemberOrElseThrow(String name, Member member) {
		return folderRepository.findByNameAndMember(name, member)
			.orElseThrow(() -> new BusinessException(NOT_FOUND_FOLDER));
	}

	public Folder getFolderByNameAndMemberOrNull(String name, Member member) {
		return folderRepository.findByNameAndMember(name, member).orElse(null);
	}

	public Folder findFolder(Long id) {
		return folderRepository.findById(id).orElseThrow(() -> new BusinessException(NOT_FOUND_FOLDER));
	}
}
