package com.zhuodewen.www.service;

import com.zhuodewen.www.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsService extends JpaRepository<Goods, Integer> {

    public Goods findById(Long id);

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

}
