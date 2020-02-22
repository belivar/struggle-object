package com.example.yinglishzhi.controller.v1;

import com.example.yinglishzhi.service.api.IOrderService;
import com.example.yinglishzhi.vo.PlaceOrderRequestVO;
import com.example.yinglishzhi.vo.PlaceOrderResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LDZ
 * @date 2019-09-04 10:08
 */

@Controller
public class Order extends AbstractController {

    @Override
    @RequestMapping(value = {"api/QueryOutRefFreeze.do"})
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(999);
        try {
            response.getWriter().print("1111");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Autowired
//    IOrderService orderService;

    @ResponseBody
    @RequestMapping(value = {"/api/placeOrder"}, method = RequestMethod.POST)
    protected PlaceOrderResultVO placeOrder(PlaceOrderRequestVO placeOrderRequestVO) {

//        orderService.placeOrder(placeOrderRequestVO);

        PlaceOrderResultVO placeOrderResultVO = new PlaceOrderResultVO();
        placeOrderResultVO.setOid(placeOrderRequestVO.getUid());
        return placeOrderResultVO;

    }
}
