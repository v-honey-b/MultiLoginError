package com.final_project.Store.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StoreRegisterFormDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다")
    private String storeId;
    
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다")
    private String storePwd;

    @NotEmpty(message = "상호명은 필수 입력 값입니다")
    private String storeName;

    @NotNull(message = "사업자 번호는 필수 입력 값입니다")
    private int storeCorporateNum;

    @NotEmpty(message = "이메일은 필수 입력 값입니다")
    private String storeEmail;

    @NotEmpty(message = "우편번호는 필수 입력 값입니다")
    //@Email(message = "이메일 형식으로 입력하세요")
    private String storeZipCode;

    @NotEmpty(message = "상세 주소는 필수 입력 값입니다")
    private String storeAddress;

    @NotEmpty(message = "전화 번호는 필수 입력 값입니다")
    private String storePhone;
}
