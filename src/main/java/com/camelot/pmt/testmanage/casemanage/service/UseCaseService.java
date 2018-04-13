package com.camelot.pmt.testmanage.casemanage.service;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.github.pagehelper.PageInfo;

public interface UseCaseService {
	
	public PageInfo<UseCase> selectUseCase(PageBean pageBean);
}
