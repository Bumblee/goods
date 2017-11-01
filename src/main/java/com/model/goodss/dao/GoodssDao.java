package com.model.goodss.dao;

import java.util.Set;

import org.hibernate.Filter;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import com.model.goodss.domain.Goodss;
import com.model.goodss.util.MyPage;


@Component
@Transactional
public class GoodssDao extends BaseDao<Goodss>{
	
	public MyPage<Goodss> findAll(String userid, int page, int pagesize, String keyword) {
		log.debug("findAll");
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Goodss.class);
			Session session = getSession();
			Filter filter = session.enableFilter("comments");
			filter.setParameter("embed", "yes");// 默认只加载指定数据
			if (StringUtils.isNotBlank(userid)) {
				dc.add(Property.forName("userid").eq(userid));
			}
			if (StringUtils.isNotBlank(keyword)) {
				Disjunction dis = Restrictions.disjunction();
				dis.add(Property.forName("title").like(keyword, MatchMode.ANYWHERE));
				dis.add(Property.forName("name").like(keyword, MatchMode.ANYWHERE));
				dis.add(Property.forName("content").like(keyword, MatchMode.ANYWHERE));
				dc.add(dis);
			}
			dc.addOrder(Order.desc("ctime"));
			try {
				if (pagesize <= 0) {
					pagesize = 20;
				}
			} catch (Exception e) {
			}
			return findPageByCriteria(dc, pagesize, page);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void save(Goodss goodss){
		log.debug("save Goodss goodss");
		try {
			getSession().save(goodss);
			log.debug("save successful");
		} catch (Exception e) {
			log.debug("save fail",e);
			throw e;
		}
	}
	
	public void delete(String id){
		log.debug("delete Goodss by id");
		try {
			String sql = "delete from goodss where id=:id";
			Query<?> query = getSession().createNativeQuery(sql).setParameter("id", id);
			query.executeUpdate();
			log.debug("delete successful");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Goodss findById(String id){
		log.debug("getting Course instance with id: " + id);
		Session session = getSession();
		Filter filter = session.enableFilter("comments");
		filter.setParameter("embed", "yes");// 默认只加载指定数据
		try {
			Goodss goodss = (Goodss) getSession().get("com.model.goodss.domain.Goodss", id);
			return goodss;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Goodss merge(Goodss goodss) {
		log.debug("merging Course instance");
		try {
			Goodss result = (Goodss) getSession().merge(goodss);
			getSession().flush();// 立即提交，避免新建社群递归不到整个树
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void refreshCmcount(String id) {
		try {// and type='comment'
			String sqlcount = "update goodss set cmcount=("
					+ "select count(id) as number from comment where goodss=:goodss" + ") where id=:goodss";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("goodss", id);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("refreshCmcount_failed", re);
			throw re;
		}
	}
	

}
