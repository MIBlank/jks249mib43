package game;

import java.util.ArrayList;

public class Column {

	public int column_size;
	public int player1_saved_progress = 0;
	public int player2_saved_progress = 0;
	public int player1_temp_progress = 0;
	public int player2_temp_progress = 0;
	public boolean is_finished = false;
	
	Column(int columnid){
		column_size = (columnid*2)-1;
	}
	
	public int get_size(){
		return column_size;
	}
	
	public int get_saved_progress(int playerid){
		if (playerid == 0)
			return player1_saved_progress;
		else
			return player2_saved_progress;
	}
	
	public int get_temp_progress(int playerid){
		if (playerid == 0)
			return player1_temp_progress;
		else
			return player2_temp_progress;
	}
	
	public void reset_temp_progress(int playerid){
		if (playerid == 0)
			player1_temp_progress = 0;
		else
			player2_temp_progress = 0;
	}
	
	public void save_temp_progress(int playerid){
		if (playerid == 0){
			player1_saved_progress += player1_temp_progress;
			player1_saved_progress = Math.min(this.column_size,player1_saved_progress);
			player1_temp_progress = 0;
			if(player1_saved_progress == column_size){
				is_finished = true;
			}
		}
		else{
			player2_saved_progress += player2_temp_progress;
			player2_saved_progress = Math.min(this.column_size,player2_saved_progress);
			player2_temp_progress = 0;
			if(player2_saved_progress == column_size){
				is_finished = true;
			}
		}
	}
	
}
