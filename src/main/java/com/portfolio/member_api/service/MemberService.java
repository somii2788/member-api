package com.portfolio.member_api.service;

import com.portfolio.member_api.dto.MemberRequestDto;
import com.portfolio.member_api.dto.MemberResponseDto;
import com.portfolio.member_api.entity.Member;
import com.portfolio.member_api.exception.MemberNotFoundException;
import com.portfolio.member_api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	public List<MemberResponseDto> findAll() {
		return memberRepository.findAll().stream()
				.map(this::toResponse)
				.toList();
	}

	public MemberResponseDto findById(Long id) {
		return memberRepository.findById(id)
				.map(this::toResponse)
				.orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));
	}

	@Transactional
	public MemberResponseDto create(MemberRequestDto request) {
		if (memberRepository.existsByEmail(request.getEmail())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 등록된 이메일입니다.");
		}
		Member member = Member.builder()
				.name(request.getName())
				.email(request.getEmail())
				.build();
		return toResponse(memberRepository.save(member));
	}

	@Transactional
	public MemberResponseDto update(Long id, MemberRequestDto request) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."));
		memberRepository.findByEmail(request.getEmail())
				.filter(m -> !m.getId().equals(id))
				.ifPresent(m -> {
					throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 등록된 이메일입니다.");
				});
		member.setName(request.getName());
		member.setEmail(request.getEmail());
		return toResponse(member);
	}

	@Transactional
	public void delete(Long id) {
		if (!memberRepository.existsById(id)) {
			throw new MemberNotFoundException("회원을 찾을 수 없습니다.");
		}
		memberRepository.deleteById(id);
	}

	private MemberResponseDto toResponse(Member member) {
		return MemberResponseDto.builder()
				.id(member.getId())
				.name(member.getName())
				.email(member.getEmail())
				.createdAt(member.getCreatedAt())
				.build();
	}
}
