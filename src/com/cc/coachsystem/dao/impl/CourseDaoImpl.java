package com.cc.coachsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.dao.CourseDao;
import com.cc.coachsystem.utils.DBUtil;

public class CourseDaoImpl implements CourseDao {

	@Override
	public void add(Course c) {
		String sql = "insert into course(time,place,content,pass) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getTime());
			pst.setString(2, c.getPlace());
			pst.setString(3, c.getContent());
			pst.setBoolean(4, c.isPass());
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
		String sql = "select * from course where courseid=?";
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
	public List<Course> getList(int pageIndex, int pageSize, int userid) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.* from course c, user_course uc where uc.userid=? and uc.courseid=c.id limit ?,?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
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

	@Override
	public List<Course> getListByCoach(int pageIndex, int pageSize, int coachid) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select c.* from course c, coach_course cc where cc.coachid=? and cc.courseid=c.id limit ?,?";
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
		String sql = "select * from course limit ?,?";
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
