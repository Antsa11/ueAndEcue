package org.admin.utils;

public class Pagination
{
	private int start, end , nb_par_page,current_page,total_page;
	
	public Pagination(int current_page, int nb_par_page,int total_page)
	{
		this.current_page=current_page;
		this.nb_par_page=nb_par_page;
		this.total_page=total_page;
	}
	
	public int getStart()
	{
		start= (int)Math.floor((double)(this.current_page/this.nb_par_page))*this.nb_par_page;
		if(start==0)
		start=1;
		return start;
	}
	
	public int getEnd()
	{
		end= ((int)Math.floor((double)(this.current_page/this.nb_par_page))*this.nb_par_page)+this.nb_par_page;
		if(end > this.total_page)
		end=this.total_page;
		return end;
	}
	
}
