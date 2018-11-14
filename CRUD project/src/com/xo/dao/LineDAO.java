package com.xo.dao;



import com.xo.model.Line;

public interface LineDAO {
	public Line get_line( int row );
	public void set_line( int row, Line line );
	public void reset();
	public void swap_turn();
	public Line[] get_all_lines();
	public int get_turn();
	public boolean check_victory();
	

}