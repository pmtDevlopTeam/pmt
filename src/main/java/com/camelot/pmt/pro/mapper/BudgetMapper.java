package com.camelot.pmt.pro.mapper;

import com.camelot.pmt.pro.model.Budget;

public interface BudgetMapper {
	int deleteByPrimaryKey(Integer budgetId);

	int insert(Budget record);

	int insertSelective(Budget record);

	Budget selectByPrimaryKey(Integer budgetId);

	int updateByPrimaryKeySelective(Budget record);

	int updateByPrimaryKey(Budget record);
}