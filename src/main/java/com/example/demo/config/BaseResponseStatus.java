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
    PATCH_USERS_SHORT_PASSWORD(false, 2022, "입력하신 비밀번호가 너무 짧습니다."),

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
    WRONG_DELIVERY_CODE(false, 3201, "올바른 주문 상태코드를 입력해 부세요. 0~5"),

    POST_ORDER_INVALID_PAYMENT_METHOD(false, 3202, "올바르지 않은 지불방법입니다."),
    POST_ORDER_BUYER_NAME_TOO_LONG(false, 3203, "구매자 명이 너무 깁니다. 45자 미만으로 작성해 주세요"),
    POST_ORDER_RECEIVER_NAME_TOO_LONG(false, 3203, "수취인 명이 너무 깁니다. 45자 미만으로 작성해 주세요"),
    POST_ORDER_POSTAL_CODE_LENGTH_WRONG(false, 3204, "우편번호는 5자리여야 합니다"),
    POST_ORDER_ADDRESS1_TOO_LONG(false, 3205, "address1이 너무 깁니다. 150자 미만으로 작성해 주세요."),
    POST_ORDER_ADDRESS2_TOO_LONG(false, 3206, "address2가 너무 깁니다. 150자 미만으로 작성해 주세요."),
    POST_ORDER_REQUEST_TOO_LONG(false, 3207, "request 항목이 너무 깁니다. 150자 미만으로 작성해 주세요."),


    PATCH_ORDER_POSTAL_CODE_LENGTH_WRONG(false, 3204, "우편번호는 5자리여야 합니다"),
    PATCH_ORDER_ADDRESS1_TOO_LONG(false, 3205, "address1이 너무 깁니다. 150자 미만으로 작성해 주세요."),
    PATCH_ORDER_ADDRESS2_TOO_LONG(false, 3206, "address2가 너무 깁니다. 150자 미만으로 작성해 주세요."),
    PATCH_ORDER_REQUEST_TOO_LONG(false, 3207, "request 항목이 너무 깁니다. 150자 미만으로 작성해 주세요."),

    // Reviews 3300번 부터 시작
    POST_REVIEW_SCORE_INVALID(false, 3302, "Score는 0~5중 하나의 정수로 넣어주세요."),
    POST_REVIEW_CONTENT_TOO_LONG(false, 3303, "Review 내용이 너무 깁니다."),
    POST_REVIEW_IMAGE_URL_TOO_LONG(false, 3304, "이미지 주소가 지나치게 깁니다."),


    PATCH_REVIEW_SCORE_INVALID(false, 3302, "Score는 0~5중 하나의 정수로 넣어주세요."),
    PATCH_REVIEW_CONTENT_TOO_LONG(false, 3303, "Review 내용이 너무 깁니다."),
    PATCH_REVIEW_IMAGE_URL_TOO_LONG(false, 3304, "이미지 주소가 지나치게 깁니다."),

    // Categories 3400번 부터 시작
    CATEGORY_NOT_FOUND(false, 3401, "존재하지 않는 카테고리 입니다." ),
    CATEGORY_EXIST(false, 3402, "카테고리가 이미 존재합니다."),
    CATEGORY_CHILDREN_EXIST(false, 3403, "삭제하려는 카테고리의 하위 카테고리가 존재합니다."),
    POST_CATEGORY_NAME_TOO_LONG(false, 3404, "카테고리 명이 지나치게 깁니다."),

    // Seller 관련 3500 부터 시작
    SELLER_NOT_FOUND(false, 3501, "존해하지 않는 sellerId 입니다"),
    BRANDNAME_NOT_FOUND(false, 3502, "존재하지 않는 브랜드 이름입니다."),
    POST_SELLER_BRAND_NAME_TOO_LONG(false, 3503, "브랜드명이 너무 기비다. 45자 미만으로 작성해 주세요."),
    POST_SELLER_BRAND_EXPLAIN_TOO_LONG(false, 3504, "브랜드 설명이 너ㅜ깁니다. 150자 미만으로 작성해 주세요."),

    // Scrab 3600번 부터 시작
    SCRAB_TYPE_INVALID(false, 3600, "스크랩 타입이 유효하지 않습니다. 다시 확인해주세요"),
    REVIEW_NOT_FOUND(false, 3601, "리뷰가 존재하지 않습니다."),

    ALREADY_SCRABBED(false, 3602, "이미 스크랩한 아이템입니다."),

    // 핸드폰 번호 인증  3700부터
    PHONENUMBER_ALREADY_AUTHENTICATED(false, 3701, "핸드폰가 이미 인증되었음" ),
    PHONENUMBER_INVALID(false, 3702, "11자리의 숫자로 입력해 주세요." ),
    PHONENUMBER_AUTH_FAILED(false, 3703, "잘못된 전화번호 이거나, 잘못된 코드입니다."),

    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),
    OAUTH_ERROR(false, 4002, "카카오 회원 가입 실패"),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),

    POST_PRODUCT_NAME_TOO_LONG(false, 3102, "상품명이 지나치게 깁니다."),
    POST_PRODUCT_DATE_TOO_LATE(false, 3103, "이벤트는 미래에 종료되어야 합니다."),
    POST_PRODUCT_REQUIRED_EMPTY(false, 3104, "productPhoto, expPhoto, OptionName, optionPrice 항목이 모두 1개 이상이 맞는지 확인해 주세요."),
    POST_PRODUCT_OPTION_NOT_MATCH(false, 3105, "optionName의 개수와 optionPrice의 개수가 맞지 않습니다. 맞춰주세요.");











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
