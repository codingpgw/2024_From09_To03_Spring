package com.pcwk.ehr.aspectj;

public class MemberImpl implements Member {

	@Override
	public int doSave() {
		log.debug("┌─+++++++++++++─────────────────────┐");
		log.debug("│ **doSave()**                      │");
		log.debug("└───────────────────────────────────┘");
		return 0;
	}

	@Override
	public int doUpdate() {
		log.debug("┌─+++++++++++++─────────────────────┐");
		log.debug("│ **doUpdate()**                    │");
		log.debug("└───────────────────────────────────┘");
		return 0;
	}

	@Override
	public void doInsert(int num) {
		log.debug("┌─+++++++++++++─────────────────────┐");
		log.debug("│ **doInsert()**                    │");
		log.debug("└───────────────────────────────────┘");
	}

	@Override
	public int delete() {
		log.debug("┌─+++++++++++++─────────────────────┐");
		log.debug("│ **delete()**                      │");
		log.debug("└───────────────────────────────────┘");
		return 0;
	}

	@Override
	public int doRetrieve(int num) {
		log.debug("┌─+++++++++++++─────────────────────┐");
		log.debug("│ **doRetrieve()**                  │");
		log.debug("└───────────────────────────────────┘");
		return 0;
	}

}
