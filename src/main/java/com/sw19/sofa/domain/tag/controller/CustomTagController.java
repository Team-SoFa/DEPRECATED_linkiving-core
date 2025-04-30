package com.sw19.sofa.domain.tag.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sw19.sofa.domain.member.entity.Member;
import com.sw19.sofa.domain.tag.api.CustomTagApi;
import com.sw19.sofa.domain.tag.dto.request.CustomTagReq;
import com.sw19.sofa.domain.tag.dto.response.CustomTagRes;
import com.sw19.sofa.domain.tag.service.CustomTagService;
import com.sw19.sofa.security.jwt.AuthMember;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/custom-tag")
@RequiredArgsConstructor
public class CustomTagController implements CustomTagApi {
	private final CustomTagService customTagService;

	@GetMapping
	public ResponseEntity<List<CustomTagRes>> getAllCustomTags(@AuthMember Member member) {
		List<CustomTagRes> res = customTagService.getAllCustomTagsByMember(member);
		return ResponseEntity.ok(res);
	}

	@PostMapping
	public ResponseEntity<CustomTagRes> createCustomTag(
		@AuthMember Member member,
		@RequestBody CustomTagReq req) {
		CustomTagRes res = customTagService.createCustomTag(member, req.name());
		return ResponseEntity.ok(res);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomTagRes> updateCustomTag(
		@AuthMember Member member,
		@PathVariable String id,
		@RequestBody CustomTagReq req
	) {
		CustomTagRes res = customTagService.updateCustomTag(id, req.name(), member);
		return ResponseEntity.ok(res);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomTag(
		@AuthMember Member member,
		@PathVariable String id) {
		customTagService.deleteCustomTag(id, member);
		return ResponseEntity.ok().build();
	}
}
