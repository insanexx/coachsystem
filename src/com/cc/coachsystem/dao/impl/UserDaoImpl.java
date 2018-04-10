package com.cc.coachsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cc.coachsystem.beans.Course;
import com.cc.coachsystem.beans.User;
import com.cc.coachsystem.dao.UserDao;
import com.cc.coachsystem.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void add(User u) {
		String sql = "insert into user(username,password,name,phone) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getName());
			pst.setString(4, u.getPhone());
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
		String sql = "delete from user where userid=?";
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
	public void update(User u) {
		String sql = "update user set username=?,password=?,name=?,phone=?  where userid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getName());
			pst.setString(4, u.getPhone());
			pst.setInt(5, u.getUserid());
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
	public User getById(int id) {
		User u = null;
		String sql = "select * from user where userid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				u = new User();
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setUserid(id);
				u.setUsername(rs.getString("username"));
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
		return u;
	}

	@Override
	public List<User> getList(int pageIndex, int pageSize) {
		List<User> list = new ArrayList<User>();
		String sql = "select * from user limit ?,?";
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
				User u = new User();
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setUserid(rs.getInt("userid"));
				u.setUsername(rs.getString("username"));
				list.add(u);
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
	public User getByUsernameAndPassword(String username, String password) {
		User u = null;
		String sql = "select * from user where username=? and password=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				u = new User();
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setPhone(rs.getString("phone"));
				u.setUserid(rs.getInt("userid"));
				u.setUsername(rs.getString("username"));
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
		return u;
	}

	@Override
	public void takecourse(Course c, User u) {
		
	}

	@Override
	public boolean istookCourse(Course c, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUsersByCourse(int courseid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
