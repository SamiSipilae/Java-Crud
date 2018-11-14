package com.xo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xo.util.DBUtil;

import com.xo.model.Line;

public class LineDAOImplementation implements LineDAO {
	
	private Connection conn;

	public LineDAOImplementation() {
		conn = DBUtil.getConnection();
	}

	@Override
	public void set_line( int row, Line line ) {
		try {
			int[] array = line.getLine();
			String query = "update game  set `0`=?, `1`=?, `2`=?, `3`=?, `4`=?, `5`=?, `6`=?, `7`=?, `8`=?, `9`=? where line=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			for(int x= 1; x <11; x++)
			{
				preparedStatement.setInt( x, array[x-1] );
			}
			preparedStatement.setInt(11, row+1);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void reset(){
		Line line = new Line();
		for(int x= 0; x <10; x++)
		{
			line.setLine(x, 0);
		}
		for(int x= 0; x <10; x++)
		{
			set_line(x, line);
		}		
	}
	@Override
	public Line get_line( int row ){
		Line line = new Line();
		try {
			String query = "select * from game where line=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setInt(1, row+1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ) {
				for(int x= 0; x <9; x++)
				{
				line.setLine(x, resultSet.getInt( Integer.toString(x) ));
				}

			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	@Override
	public Line[] get_all_lines(){
		Line[] lines = new Line[11];
		for(int x= 0; x<10; x++)
		{
		lines[x] = new Line();
		}
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery( "select * from game where line <11 and line is not null" );
			while( resultSet.next() ) {
				int row = resultSet.getInt( "line" );
				for(int x= 0; x<10; x++)
				{
				lines[row-1].setLine(x, resultSet.getInt( Integer.toString(x) ));
				}

			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lines;
	}

	
	@Override
	public int get_turn(){
		int[] line = new int[10];
		try {
			String query = "select * from game where line=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setInt(1, 11);
			ResultSet resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ) {
				for(int x= 0; x <9; x++)
				{
				line[x]=( resultSet.getInt( Integer.toString(x) ) );
				}

			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return line[0];
	}


	
	@Override
	public void swap_turn(){
		int turn = 1;
		try {
			
			String query = "select * from game where line=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setInt(1, 11);
			ResultSet resultSet = preparedStatement.executeQuery();
			while( resultSet.next() ) {
			
				turn=( resultSet.getInt( "0" ) );
				if(turn == 1)
					turn = 2;
				else
					turn =1;
			}
			resultSet.close();
			preparedStatement.close();
			Line line = new Line();
			line.setLine(0, turn);
			set_line(10, line);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public boolean check_victory(){
		int[][] table = new int[10][10];
		Line[] lines = get_all_lines();
		int turn = get_turn();
		
		for (int i=0;i<10;i++){
			table[i] = lines[i].getLine();		
		}
		
		for (int y=0;y<10;y++){			
			for (int x=0;x<10;x++){
				if(table[y][x] == turn){
					if(x<7){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y][x+i] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(y<7){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y+i][x] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(x>2){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y][x-i] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(y>2){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y-i][x] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(x>2 && y>2){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y-i][x-i] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(x<7 && y<7){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y+i][x+i] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(x>2 && y<7){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y+i][x-i] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					if(x<7 && y>2){
						int found = 1;
						for(int i=1; i<4;i++){
							if(table[y-i][x+i] == turn)
								found++;
						}
						if(found>3)
							return true;
					}
					
				}
					
			}

		}

		
		return false;
	}
}
