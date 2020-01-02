package com.zhuodewen.www.service;

import com.zhuodewen.www.domain.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Repository
public interface GoodsService extends JpaRepository<Goods, Integer> {

    public Goods findById(Long id);

    //新增(JPA)
    public Goods save(Goods goods);

    //使用HQL自定义查询1(:xxx注入,@Param映射)
    @Query(value = "SELECT u FROM Goods u WHERE u.goodName= :name ")
    public Goods findName(@Param("name") String name);

    //使用HQL自定义查询2(?数字注入,按顺序映射)
    @Query(value = "SELECT u FROM Goods u WHERE u.goodName= ?1 AND u.goodCode=?2 ")
    public Goods findNameAndCode(String name,String code);

    //使用原生SQL自定义查询
	@Query(value = "SELECT * FROM goods WHERE good_name=?", nativeQuery = true)
    public Goods findName2(String name);

    //===================================================================================
    // 分页查询(实现方式:Pageable)
    @Query(value = "select s from Goods s where s.goodName=:name")
    Page<Goods> findByGoodNamePageable(@Param("name") String name, Pageable pageable);

    //分页查询 + 动态条件(实现方式:Pageable)
    @Query(value = "select s from Goods s where (:name=null or s.goodName=:name)")
    Page<Goods> findPageIfGoodNameNotNull(@Param("name") String name, Pageable pageable);

    //分页查询 + 动态条件 + 原生SQL(实现方式:Pageable)
    @Query(value = "select * from goods s where (?1=null or s.good_name=?1) order by ?#{#pageable}",nativeQuery = true)
    Page<Goods> findPageIfGoodNameNotNullNativeQuery(String name, Pageable pageable);



}
