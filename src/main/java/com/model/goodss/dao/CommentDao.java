package com.model.goodss.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.model.goodss.domain.Comment;
import com.model.goodss.util.MyPage;


@Component
public class CommentDao extends BaseDao {
	private static final Logger log = LoggerFactory.getLogger(CommentDao.class);

	public void setEmbed(String goodssid, String value) {
		String sql = "update `Comment` set embed=:embed where goodss=:goodss";
		NativeQuery query = getSession().createNativeQuery(sql);
		query.setParameter("goodss", goodssid);
		query.setParameter("embed", value);
		query.executeUpdate();
	}

	public void setEmbed(String goodssid, Integer number, String value) {
		String sql = "update `Comment` as c inner join (select * from `Comment` where goodss=:goodss order by ctime desc limit 0,:num) as i on c.id=i.id set c.embed=:embed";
		NativeQuery query = getSession().createNativeQuery(sql);
		query.setParameter("goodss", goodssid);
		query.setParameter("num", number);
		query.setParameter("embed", value);
		query.executeUpdate();
	}

	public void save(Comment transientInstance) {
		log.debug("saving Comment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Comment persistentInstance) {
		log.debug("deleting Comment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void delete(String id) {
		try {
			String sqlcount = "delete from Comment where id=:id";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("id", id);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void deleteBygoodss(String goodssid) {
		try {
			String sqlcount = "delete from Comment where goodss=:goodssid";
			SQLQuery sqlcountquery = getSession().createSQLQuery(sqlcount);
			sqlcountquery.setParameter("goodssid", goodssid);
			sqlcountquery.executeUpdate();
		} catch (RuntimeException re) {
			log.error("deleteBygoodss failed", re);
			throw re;
		}
	}

	public void deletesBygoodss(List<String> goodssids) {
		try {
			for (String id : goodssids) {
				deleteBygoodss(id);
			}
		} catch (RuntimeException re) {
			log.error("deletesBygoodss failed", re);
			throw re;
		}
	}

	public Comment findById(java.lang.String id) {
		log.debug("getting Comment instance with id: " + id);
		try {
			Comment instance = (Comment) getSession().get("com.wxine.core.model.Comment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Comment findLikeBygoodss(String userid, String goodssid) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Comment.class);
			dc.createCriteria("user").add(Restrictions.eq("id", userid));
			dc.createCriteria("goodss").add(Restrictions.eq("id", goodssid));
			dc.add(Property.forName("type").eq("like"));
			List<Comment> list = findAllByCriteria(dc);
			if (null != list) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("findBygoodss failed", re);
			throw re;
		}
	}

	public Comment findScoreBygoodss(String userid, String goodssid) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Comment.class);
			// dc.createCriteria("userid").add(Restrictions.eq("id", userid));
			dc.add(Property.forName("userid").eq(userid));
			dc.createCriteria("goodss").add(Restrictions.eq("id", goodssid));
			dc.add(Property.forName("type").eq("rating"));
			List<Comment> list = findAllByCriteria(dc);
			if (null != list && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("findBygoodss failed", re);
			throw re;
		}
	}

	public MyPage<Comment> findByGoodss(String goodssid, String keyword, int pagesize, int page) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Comment.class);
			dc.createCriteria("goodssid").add(Restrictions.eq("id", goodssid));
			if (StringUtils.isNotBlank(keyword)) {
				dc.add(Property.forName("content").like(keyword, MatchMode.ANYWHERE));
			}

			try {
				if (pagesize <= 0) {
					pagesize = 20;
				}
			} catch (Exception e) {
			}

			return findPageByCriteria(dc, pagesize, page);
		} catch (RuntimeException re) {
			log.error("findByGoodss failed", re);
			throw re;
		}
	}

	public MyPage<Comment> findAll(String keyword, String orderfield, String order, int pagesize, int page) {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Comment.class);
			Session session = getSession();
			if (StringUtils.isNotBlank(keyword)) {
				dc.add(Property.forName("content").like(keyword, MatchMode.ANYWHERE));
			}

			if (StringUtils.equals(orderfield, "ilike")) {
				if (StringUtils.equals(order, "desc")) {
					dc.addOrder(Order.desc("ilike"));
				} else {
					dc.addOrder(Order.asc("ilike"));
				}
			} else {
				if (StringUtils.equals(order, "asc")) {
					dc.addOrder(Order.asc("ctime"));
				} else {
					dc.addOrder(Order.desc("ctime"));
				}
			}
			dc.addOrder(Order.desc("ctime"));

			try {
				if (pagesize <= 0) {
					pagesize = 20;
				}
			} catch (Exception e) {
			}

			return findPageByCriteria(session, dc, pagesize, page);
		} catch (RuntimeException re) {
			log.error("findByJob failed", re);
			throw re;
		}
	}

	public Comment merge(Comment detachedInstance) {
		log.debug("merging Comment instance");
		try {
			Comment result = (Comment) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
}