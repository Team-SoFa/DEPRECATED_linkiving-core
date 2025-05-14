package com.sw19.sofa.domain.folder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sw19.sofa.domain.folder.api.FolderApi;
import com.sw19.sofa.domain.folder.dto.request.FolderReq;
import com.sw19.sofa.domain.folder.dto.response.FolderRes;
import com.sw19.sofa.domain.folder.dto.response.FoldersRes;
import com.sw19.sofa.domain.folder.service.FolderMangeService;
import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.global.common.dto.BaseResponse;
import com.sw19.sofa.security.jwt.AuthMember;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/folder")
public class FolderController implements FolderApi {

	private final FolderMangeService folderMangeService;

	@Override
	@GetMapping
	public ResponseEntity<FoldersRes> getFolderList(@AuthMember Member member) {
		FoldersRes res = folderMangeService.getFolderList(member);
		return BaseResponse.ok(res);
	}

	@Override
	@PostMapping
	public ResponseEntity<FoldersRes> addFolder(@AuthMember Member member, @RequestBody FolderReq req) {
		FoldersRes res = folderMangeService.addFolder(member, req);
		return BaseResponse.ok(res);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delFolder(@PathVariable("id") String id, @AuthMember Member member) {
		folderMangeService.delFolder(id, member);
		return BaseResponse.ok("폴더 삭제 완료");
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<FolderRes> editFolder(@PathVariable("id") String id, @RequestBody FolderReq req) {
		FolderRes res = folderMangeService.editFolder(id, req);
		return BaseResponse.ok(res);
	}
}
