package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),
    USER_NOT_FOUND(false, 2011, "존재하지 않는 유저id 입니다"),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    POST_USERS_EMPTY_PASSWORD(false, 2018, "비밀번호를 입력해주세요."),
    POST_USERS_EMPTY_NICKNAME(false, 2019, "사용하실 이름을 입력해주세요."),
    POST_USERS_DELETED_USER(false, 2021, "탈퇴한 회원입니다."),
    POST_USERS_SLEEPER_ACCOUNT(false, 2022, "휴먼 계정입니다."),

    // Product 2100번 부터 시작


    // Orders 2200번 부터 시작


    // Reviews 2300번 부터 시작

    // Photo 2400번 부터 시작
    // [POST] /photo
    POST_PHOTO_INVALID_TYPE(false, 2423, "사진 타입이 올바르지 않습니다."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),

    // Product 3100번 부터 시작

    PRODUCT_NOT_FOUND(false, 3100, "존재하지 않는 상품 번호 입니다."),
    PRODUCT_OPTION_NOT_FOUND(false, 3101, "존재하지 않는 상품 옵션 번호입니다."),

    // Orders 3200번 부터 시작
    ORDER_NOT_FOUND(false, 3200, "존재하지 않는 주문 번호 입니다."),


    // Reviews 3300번 부터 시작

    // Categories 3400번 부터 시작
    CATEGORY_NOT_FOUND(false, 3401, "존재하지 않는 카테고리 입니다." ),
    CATEGORY_EXIST(false, 3402, "카테고리가 이미 존재합니다."),
    CATEGORY_CHILDREN_EXIST(false, 3403, "삭제하려는 카테고리의 하위 카테고리가 존재합니다."),

    // Seller 관련 3500 부터 시작
    SELLER_NOT_FOUND(false, 3501, "존해하지 않는 sellerId 입니다"),

    // Scrab 3600번 부터 시작
    SCRAB_TYPE_INVALID(false, 3600, "스크랩 타입이 유효하지 않습니다. 다시 확인해주세요"),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),
    OAUTH_ERROR(false, 4002, "카카오 회원 가입 실패"),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");








    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
