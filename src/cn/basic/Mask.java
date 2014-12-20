package cn.basic;

/**
 * 
 * @author eple
 *
 */
public class Mask {
	
	public static double[][] Guass={{1,2,1},{2,4,2},{1,2,1}};
	
	public static double[][] Sharp={{0,-1,-1,-1,0},
									{1,2,-4,2,-1},
									{-1,-4,13,-4,-1},
									{-1,2,-4,2,-1},
									{0,-1,-1,-1,0}};
	
	public static double[][] Guass7={{1,1,2,2,2,1,1},{1,2,2,4,2,2,1},{2,2,4,8,4,2,2},{2,4,8,16,8,4,2},
									{2,2,4,8,4,2,2},{1,2,2,4,2,2,1},{1,1,2,2,2,1,1}};
	
	public static double[][] Laplacian = {{0,-1,0},
										  {-1,4,-1},
										  {0,-1,0}};
	
	public static double[][] Laplacian5 ={{-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1},{-1,-1,24,-1,-1},{-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1}};
	public static int[][] Expand = {{1,1,1},{1,1,1},{1,1,1}};
	public static int[][] Erosion = {{1,1,1},{1,1,1},{1,1,1}};
}
