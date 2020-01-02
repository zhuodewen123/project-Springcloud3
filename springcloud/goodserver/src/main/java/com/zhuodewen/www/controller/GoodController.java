package com.zhuodewen.www.controller;

import com.zhuodewen.www.domain.Goods;
import com.zhuodewen.www.service.GoodsService;
import com.zhuodewen.www.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品的controller
 */
@Controller
@RequestMapping("goods")
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "save",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult save(Goods goods){
        JSONResult js=new JSONResult();
        try{
            goodsService.save(goods);
            js.setMsg("新增成功");
        }catch(Exception e){
            e.printStackTrace();
            js.setMsg("新增失败");
        }
        return js;
    }

    @RequestMapping(value = "findName",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findName(String name){
        JSONResult js=new JSONResult();
        try{
            Goods goods =goodsService.findName(name);
            js.setMsg("查询成功");
            js.setResult(goods);
        }catch(Exception e){
            e.printStackTrace();
            js.setMsg("查询失败");
        }
        return js;
    }

    @RequestMapping(value = "findNameAndCode",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findNameAndCode(Goods goods){
        JSONResult js=new JSONResult();
        try{
            Goods g =goodsService.findNameAndCode(goods.getGoodName(),goods.getGoodCode());
            js.setMsg("查询成功");
            js.setResult(g);
        }catch(Exception e){
            e.printStackTrace();
            js.setMsg("查询失败");
        }
        return js;
    }

    @RequestMapping(value = "findName2",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findName2(String name){
        JSONResult js=new JSONResult();
        try{
            Goods goods =goodsService.findName2(name);
            js.setMsg("查询成功");
            js.setResult(goods);
        }catch(Exception e){
            e.printStackTrace();
            js.setMsg("查询失败");
        }
        return js;
    }

}
