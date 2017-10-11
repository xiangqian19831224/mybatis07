package cn.java.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.java.entity.Computer;

public class ComputerDaoImpl {
    private static SqlSession session = null;

    @Before
    public void init() {
        try {
            // 1、得到SqlSession
            SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
            InputStream ins = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory ssf = sfb.build(ins);
            session = ssf.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 通过多个条件查询
    @Test
    public void dynamicSelect() {
        Map<String, Object> paramter = new HashMap<String, Object>();
        paramter.put("brand", "神舟");
        paramter.put("price", 4500F);
//        paramter.put("memory_size", 4F);
        List<Map<String,Object> > lists = session.selectList("cn.java.dao.impl.ComputerDaoImpl.dynamicSelect", paramter);
        for (Map<String, Object> map : lists) {
            System.out.println(map.toString());
        }
    }

}
