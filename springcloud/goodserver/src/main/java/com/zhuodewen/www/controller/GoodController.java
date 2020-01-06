package com.zhuodewen.www.controller;

import com.zhuodewen.www.domain.Goods;
import com.zhuodewen.www.service.GoodsService;
import com.zhuodewen.www.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品的控制器
 */
@Controller
@RequestMapping("goods")
public class GoodController {

    @Autowired
    private GoodsService goodsService;

    /**
     * Hibernate--新增商品(JPA)
     * @param goods
     * @return
     */
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

    /**
     * Hibernate自定义HQL查询(根据商品名称查询商品)
     * @param name
     * @return
     */
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

    /**
     * Hibernate--自定义HQL查询(根据商品名称和编码查询商品)
     * @param goods
     * @return
     */
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

    /**
     * Hibernate--自定义原生SQL查询(根据商品名称查询商品)
     * @param name
     * @return
     */
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

    // 分页查询
    @RequestMapping(value = "findByGoodNamePageable",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> findByGoodNamePageable(String name, Pageable pageable) {
        return goodsService.findByGoodNamePageable(name, pageable);
    }

    /**
     * 分页查询 + 动态条件(实现方式:Pageable)
     */
    @RequestMapping(value = "findPageIfGoodNameNotNull",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> findPageIfGoodNameNotNull(String name, @PageableDefault(sort={"price","dicount"},direction=Sort.Direction.DESC)Pageable pageable) {
        return goodsService.findPageIfGoodNameNotNull(name, pageable);
    }

    /**
     * 分页查询 + 动态条件 + 原生SQL(实现方式:Pageable)
     */
    @RequestMapping(value = "findPageIfGoodNameNotNullNativeQuery",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> findPageIfGoodNameNotNullNativeQuery(String name, Pageable pageable) {
        return goodsService.findPageIfGoodNameNotNullNativeQuery(name,pageable);
    }

    /**
     * Hibernate分页+高级查询(实现方式:Pageable+Example)
     * @param goods         查询条件(对象中的字段)
     * @return
     */
    @RequestMapping(value = "findPage",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> findPage(Pageable pageable,Goods goods) {
        Page<Goods> result=null;
        try {
            ExampleMatcher matcher = ExampleMatcher.matching()
                    //过滤字段--全部模糊查询，即%{address}%
                    .withMatcher("goodName", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("goodCode", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
                    //忽略字段，即不管password是什么值都不加入查询条件
                    .withIgnorePaths("id").withIgnorePaths("price").withIgnorePaths("discount")
                    .withIgnorePaths("context").withIgnorePaths("url").withIgnorePaths("picUrl");
            Example<Goods> example = Example.of(goods, matcher);
            result = goodsService.findAll(example, pageable);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hibernate分页+高级查询(实现方式:Pageable+Example)
     * @param page
     * @param size
     * @param sortType
     * @param sortFiled
     * @param goods
     * @return
     */
    @RequestMapping(value = "findPage2",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> findPage2(int page,int size,String sortType,String sortFiled,Goods goods) {
        Pageable pageable=null;
        Page<Goods> result=null;
        try {
            if (sortType.equals("ASC")) {
                //of(页码数,每页容量,排序方式,排序字段)
                pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortFiled);
            } else {
                //of(页码数,每页容量,排序方式,排序字段)
                pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortFiled);
            }
            ExampleMatcher matcher = ExampleMatcher.matching()
                    //过滤字段--全部模糊查询，即%{address}%
                    .withMatcher("goodName", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("goodCode", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
                    //忽略字段，即不管password是什么值都不加入查询条件
                    .withIgnorePaths("id").withIgnorePaths("price").withIgnorePaths("discount")
                    .withIgnorePaths("context").withIgnorePaths("url").withIgnorePaths("picUrl");
            Example<Goods> example = Example.of(goods, matcher);
            result = goodsService.findAll(example, pageable);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }




    /**
     * 测试Hibernate保存--代码方式
     * @return
     */
    /*@RequestMapping(value = "testSaveByHQL",method = RequestMethod.GET)
    @ResponseBody
    public void testSaveByHQL(Goods goods) {
        Session session = null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();  //获取session
            session.beginTransaction();                                     //开启事务(提交/回滚后自动关闭资源)
            //session.saveOrUpdate(goods);
            session.save(goods);
            session.getTransaction().commit();                              //提交事务
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                            //事务回滚
        }
    }*/

    /**
     * 测试Hibernate查询--代码方式
     * @return
     */
    /*@RequestMapping(value = "testQueryByHQL",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> testQueryByHQL(String goodName) {
        Session session = null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();  //获取session
            session.beginTransaction();                                     //开启事务(提交/回滚后自动关闭资源)

            String hql = "from Goods g where g.good_name = :goodName";
            Query query = session.createQuery(hql);
            query.setParameter("goodName", goodName);
            List<Goods> list = query.list();
            System.out.println(list.toString());

            session.getTransaction().commit();                              //提交事务
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                            //事务回滚
        }
        return null;
    }*/

    /**
     *  测试Hibernate更新--代码方式
     * @return
     */
    /*@RequestMapping(value = "testUpdateByHQL",method = RequestMethod.GET)
    @ResponseBody
    public void testUpdateByHQL(Integer id) {
        Session session = null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();  //获取session
            session.beginTransaction();                                     //开启事务(提交/回滚后自动关闭资源)
            String  hql="update Goods  set goodName='哈哈,被我改了吧' where id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();                              //提交事务
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                            //事务回滚
        }
    }*/

    /**
     * 测试Hibernate删除--代码方式
     * @return
     */
    /*@RequestMapping(value = "testDeleteByHQL",method = RequestMethod.GET)
    @ResponseBody
    public void testDeleteByHQL(Integer id) {
        Session session = null;
        try {
            session= HibernateUtil.getSessionFactory().getCurrentSession();  //获取session
            session.beginTransaction();                                     //开启事务(提交/回滚后自动关闭资源)

            String hql = "delete Goods where id=?";
            session.createQuery(hql).setParameter(0, id).executeUpdate();

            session.getTransaction().commit();                              //提交事务
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                            //事务回滚
        }
    }*/

    /**
     * 测试Hibernate查询(原生SQL)--代码方式
     */
    /*@RequestMapping(value = "testSelectBySQL",method = RequestMethod.GET)
    @ResponseBody
    public Page<Goods> testSelectBySQL(String goodName) {
        Session session = null;
        try {
            session=HibernateUtil.getSessionFactory().getCurrentSession();  //获取session
            session.beginTransaction();                                     //开启事务(提交/回滚后自动关闭资源)

            String sql = "select * from goods g where g.good_name= ? ";
            NativeQuery query = session.createNativeQuery(sql).setParameter(0,goodName).addEntity(Goods.class);    //封装结果--转换为对象
            List<Goods> list = query.list();

            session.getTransaction().commit();                              //提交事务
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();                            //事务回滚
        }
        return null;
    }*/

}
