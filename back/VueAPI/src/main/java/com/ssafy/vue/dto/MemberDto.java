package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class MemberDto {

	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value = "회원 이름")
	private String username;
	@ApiModelProperty(value = "회원 비밀번호")
	private String userpwd;
	@ApiModelProperty(value = "회원 이메일")
	private String email;
	@ApiModelProperty(value = "회원 등급(권한)")
	private String role;
//	@ApiModelProperty(value = "소셜 로그인 시 어떤 소셜을 통해 로그인했는지")
//	private String provider;
//	@ApiModelProperty(value = "소셜 로그인 식별 ID")
//	private String providerId;

	public MemberDto() {

	}

	public MemberDto(String userid, String userpwd) {
		this.userid = userid;
		this.userpwd = userpwd;
	}

	public MemberDto(String userid, String username, String userpwd, String email) {
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public String getProvider() {
//		return provider;
//	}
//
//	public void setProvider(String provider) {
//		this.provider = provider;
//	}
//
//	public String getProviderId() {
//		return providerId;
//	}
//
//	public void setProviderId(String providerId) {
//		this.providerId = providerId;
//	}

	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", username=" + username + ", userpwd=" + userpwd + ", email=" + email
				+ ", role=" + role +
//						+ ", provider=" + provider + ", providerId=" + providerId +
				"]";
	}

}
