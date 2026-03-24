package com.portfolio.member_api.controller;

import com.portfolio.member_api.dto.MemberRequestDto;
import com.portfolio.member_api.dto.MemberResponseDto;
import com.portfolio.member_api.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping
	public List<MemberResponseDto> list() {
		return memberService.findAll();
	}

	@GetMapping("/{id}")
	public MemberResponseDto get(@PathVariable Long id) {
		return memberService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MemberResponseDto create(@Valid @RequestBody MemberRequestDto request) {
		return memberService.create(request);
	}

	@PutMapping("/{id}")
	public MemberResponseDto update(@PathVariable Long id, @Valid @RequestBody MemberRequestDto request) {
		return memberService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		memberService.delete(id);
	}
}
