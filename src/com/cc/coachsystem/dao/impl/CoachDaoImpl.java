package com.cc.coachsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cc.coachsystem.beans.Coach;
import com.cc.coachsystem.dao.CoachDao;
import com.cc.coachsystem.utils.DBUtil;

public class CoachDaoImpl implements CoachDao {

	@Override
	public void add(Coach c) {
		String sql = "insert into coach(password,name,phone,idcard) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getPassword());
			pst.setString(2, c.getName());
			pst.setString(3, c.getPhone());
			pst.setString(4, c.getIdcard());
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
		String sql = "delete from coach where coachid=?";
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
	public void update(Coach c) {
		String sql = "update coach set password=?,name=?,phone=?,idcard=? where coachid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getPassword());
			pst.setString(2, c.getName());
			pst.setString(3, c.getPhone());
			pst.setString(4, c.getIdcard());
			pst.setInt(4, c.getCoachid());
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
	public Coach getById(int id) {
		Coach coach = null;
		String sql = "select * from coach where coachid=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				coach = new Coach();
				coach.setCoachid(id);
				coach.setIdcard(rs.getString("idcard"));
				coach.setName(rs.getString("name"));
				coach.setPassword(rs.getString("password"));
				coach.setPhone(rs.getString("phone"));
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
		return coach;
	}

	@Override
	public List<Coach> getAll() {
		List<Coach> list = new ArrayList<Coach>();
		String sql = "select * from coach";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Coach coach = new Coach();
				coach.setCoachid(rs.getInt("coachid"));
				coach.setIdcard(rs.getString("idcard"));
				coach.setName(rs.getString("name"));
				coach.setPassword(rs.getString("password"));
				coach.setPhone(rs.getString("phone"));
				list.add(coach);
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
	public Coach getByNameAndPassword(String name, String password) {
		Coach coach = null;
		String sql = "select * from coach where name=? and password=?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				coach = new Coach();
				coach.setCoachid(rs.getInt("coachid"));
				coach.setIdcard(rs.getString("idcard"));
				coach.setName(rs.getString("name"));
				coach.setPassword(rs.getString("password"));
				coach.setPhone(rs.getString("phone"));
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
		return coach;
	}
}
