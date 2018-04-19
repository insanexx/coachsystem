package com.cc.coachsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cc.coachsystem.beans.Coach;
import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.dao.CoachDao;
import com.cc.coachsystem.dao.CourseDao;
import com.cc.coachsystem.utils.DBUtil;

public class CourseDaoImpl implements CourseDao {

	private CoachDao coachDao = new CoachDaoImpl(); 
	
	@Override
	public int add(Course c) {
		String sql = "insert into course(time,place,content,pass) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, c.getTime());
			pst.setString(2, c.getPlace());
			pst.setString(3, c.getContent());
			pst.setBoolean(4, c.isPass());
			pst.executeUpdate();
			ResultSet rs2 = pst.getGeneratedKeys();
			if(rs2.next()) {
				return rs2.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void connectcourseandcoach(Course c,Coach coach) {
		String sql = "insert into coach_course(coachid,courseid) values(?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, coach.getCoachid());
			pst.setInt(2, c.getCourseid());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from course where courseid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Course c) {
		String sql = "update course set time=?,place=?,content=?,pass=? where courseid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getTime());
			pst.setString(2, c.getPlace());
			pst.setString(3, c.getContent());
			pst.setBoolean(4, c.isPass());
			pst.setInt(5, c.getCourseid());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Course getById(int id) {
		Course c = null;
		String sql = "select c.*,cc.coachid from course c,coach_course cc where c.courseid=? and cc.courseid=c.courseid";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				c = new Course();
				c.setContent(rs.getString("content"));
				c.setCourseid(id);
				c.setPass(rs.getBoolean("pass"));
				c.setPlace(rs.getString("place"));
				c.setTime(rs.getString("time"));
				int coachid = rs.getInt("coachid");
				c.setCoach(coachDao.getById(coachid));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return c;
	}

	@Override
	public List<Course> getList(int pageIndex, int pageSize, int userid,boolean pass) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.*,cc.coachid,(select count(*) from user_course uc where uc.userid=? and uc.courseid=c.courseid and c.pass=?)>0 as entered from course c,coach_course cc where c.courseid=cc.courseid and c.pass=? limit ?,?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
			pst.setBoolean(2, pass);
			pst.setBoolean(3, pass);
			pst.setInt(4, pageIndex);
			pst.setInt(5, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setContent(rs.getString("content"));
				c.setCourseid(rs.getInt("courseid"));
				c.setPass(rs.getBoolean("pass"));
				c.setPlace(rs.getString("place"));
				c.setTime(rs.getString("time"));
				c.setEntered(rs.getBoolean("entered"));
				int coachid = rs.getInt("coachid");
				c.setCoach(coachDao.getById(coachid));
				list.add(c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<Course> getListByCoach(int pageIndex, int pageSize, int coachid) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.* from course c, coach_course cc where cc.coachid=? and cc.courseid=c.courseid order by pass limit ?,?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, coachid);
			pst.setInt(2, pageIndex);
			pst.setInt(3, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setContent(rs.getString("content"));
				c.setCourseid(rs.getInt("courseid"));
				c.setPass(rs.getBoolean("pass"));
				c.setPlace(rs.getString("place"));
				c.setTime(rs.getString("time"));
				c.setCoach(coachDao.getById(coachid));
				list.add(c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Course> getListByCoach(int pageIndex, int pageSize, int coachid,boolean pass) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.* from course c, coach_course cc where cc.coachid=? and cc.courseid=c.courseid and c.pass=? limit ?,?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, coachid);
			pst.setInt(2, pageIndex);
			pst.setBoolean(3, pass);
			pst.setInt(4, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setContent(rs.getString("content"));
				c.setCourseid(rs.getInt("courseid"));
				c.setPass(rs.getBoolean("pass"));
				c.setPlace(rs.getString("place"));
				c.setTime(rs.getString("time"));
				c.setCoach(coachDao.getById(coachid));
				list.add(c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<Course> getList(int pageIndex, int pageSize) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.*,cc.coachid from course c,coach_course cc where c.courseid=cc.courseid order by pass limit ?,?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pageIndex);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setContent(rs.getString("content"));
				c.setCourseid(rs.getInt("courseid"));
				c.setPass(rs.getBoolean("pass"));
				c.setPlace(rs.getString("place"));
				c.setTime(rs.getString("time"));
				int coachid = rs.getInt("coachid");
				c.setCoach(coachDao.getById(coachid));
				list.add(c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Course> getList(int pageIndex, int pageSize,boolean pass) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.*,cc.coachid from course c,coach_course cc where c.courseid=cc.courseid and pass=? limit ?,?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, pass);
			pst.setInt(2, pageIndex);
			pst.setInt(3, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Course c = new Course();
				c.setContent(rs.getString("content"));
				c.setCourseid(rs.getInt("courseid"));
				c.setPass(rs.getBoolean("pass"));
				c.setPlace(rs.getString("place"));
				c.setTime(rs.getString("time"));
				list.add(c);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
