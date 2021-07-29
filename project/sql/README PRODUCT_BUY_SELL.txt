CREATE TABLE PRODUCT_SELL
(
    PRODUCT_SELL_NO      NUMBER          NOT NULL, 
    MEMBER_ID            VARCHAR2(30)    NOT NULL, 
    PRODUCT_DETAIL_NO    NUMBER          NOT NULL, 
    PRICE                NUMBER(10)      NOT NULL, 
    MEMBER_ADDRESS_NO    NUMBER          NOT NULL, 
    POSTDATE             DATE            NOT NULL, 
    SELLDATE             DATE            NULL, 
    PRODUCT_BUY_NO       NUMBER          NULL, 
    STATE                NUMBER(1)       NOT NULL, 
    CONSTRAINT PK_PRODUCT_SELL PRIMARY KEY (PRODUCT_SELL_NO)
);

	PRODUCT_SELL_NO      일렬번호
	MEMBER_ID            회원 아이디 (FK)
	PRODUCT_DETAIL_NO    상품 일렬번호 (상품정보 + 사이즈) (FK)
	PRICE                원하는 판매금액
	MEMBER_ADDRESS_NO    회원 주소 일련번호 (FK)
	POSTDATE             신청일
	SELLDATE             판매 완료일 (구매자가 나타나면)
	PRODUCT_BUY_NO       구매 일련번호 (FK)
	STATE                상태 -1:취소/삭제, 0:신청, 1:상품받음, 2:판매완료(구매자와 연결됨), 3:결산완료
	

CREATE TABLE PRODUCT_BUY
(
    PRODUCT_BUY_NO       NUMBER          NOT NULL, 
    MEMBER_ID            VARCHAR2(30)    NOT NULL, 
    PRODUCT_DETAIL_NO    NUMBER          NOT NULL, 
    PRICE                NUMBER(10)      NOT NULL, 
    MEMBER_ADDRESS_NO    NUMBER          NOT NULL, 
    POSTDATE             DATE            NOT NULL, 
    BUYDATE              DATE            NULL, 
    STATE                NUMBER(1)       NOT NULL, 
    CONSTRAINT PK_PRODUCT_BUY PRIMARY KEY (PRODUCT_BUY_NO)
);
	
	PRODUCT_BUY_NO       일렬번호
	MEMBER_ID            회원 아이디 (FK)
	PRODUCT_DETAIL_NO    상품 일렬번호 (상품정보 + 사이즈) (FK)
	PRICE                원하는 구매금액
	MEMBER_ADDRESS_NO    회원 주소 일련번호 (FK)
	POSTDATE             신청일
	BUYDATE              구매 완료일 (판매자가 나타나면)
	STATE                상태 -1:취소/삭제, 0:신청, 1:구매완료(판매자와 연결됨), 2:배송중, 3:결산완료
