package com.yy.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yy.common.domain.ResponseResult;
import com.yy.service.LoanOrderService;
import com.yy.web.utils.JsonViewFactory;

/**
 * @ClassName: LoanOrderControl
 * @Description: 贷款管理
 * @author caizhen
 * @date 2016年7月13日 下午4:19:36
 */
@Controller
@RequestMapping(value="loadOrder")
public class LoanOrderControl {
	@Autowired
	LoanOrderService loanOrderService;
	
	@RequestMapping(value="getLoadOrders",method=RequestMethod.GET)
	public ModelAndView getLoadOrders(HttpServletRequest request){
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true,"操作成功",loanOrderService.selectObject(request)));
	}
	@RequestMapping(value="getLoadOrderDetail",method=RequestMethod.GET)
	public ModelAndView getLoadOrderDetail(HttpServletRequest request){
		return JsonViewFactory.buildJsonView(new ResponseResult<>(true,"操作成功",""));
	}
}
