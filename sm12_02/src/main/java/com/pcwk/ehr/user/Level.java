package com.pcwk.ehr.user;

public enum Level {
	//업그레이드 순서를 담을 Level
	//BASIC(1), SILVER(2), GOLD(3);
	GOLD(3,null), SILVER(2,GOLD), BASIC(1,SILVER);
	
	private final int value ;
	private final Level next ;//다음 단계의 레벨 정보를 같도록 변수 추가
	

	Level(int value,Level next) {
		this.value = value;
		this.next = next;
	}
	
	public Level nextLevel() {
		return this.next;
	}
	
	public int intValue() {
		return this.value;
	}
	
	
	//값으로 부터 Level 오브젝트 return
	
	public static Level valueOf(int value) {
		
		switch (value) {
		case 1:
			return BASIC;
			
		case 2:
			return SILVER;
			
		case 3:
			return GOLD;			
		default:
			throw new AssertionError("Unknown value:"+value);
		}
		
	}
	
}
