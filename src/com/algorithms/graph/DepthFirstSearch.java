package com.algorithms.graph;

import java.util.Stack;

/**
 * 图的深度优先搜索
 * @author FuYou
 *
 */
public class DepthFirstSearch {
	// 存贮顶点信息;
	private String[] vertices;

	// 存储边信息;这里使用邻接矩阵的方式实现;
	private int[][] arcs;

	// 图的定点数;
	private int verticeNums;

	// 顶点的访问状态;使用是一个数组表示;
	private boolean[] visted;

	// 初始化图信息;
	public DepthFirstSearch(int verticeNums) {
		this.verticeNums = verticeNums;
		// 初始化邻接矩阵
		this.arcs = new int[verticeNums][verticeNums];
		// 初始化顶点数组;
		this.vertices = new String[verticeNums];
		// 初始化状态
		this.visted = new boolean[verticeNums];
		// 将邻接矩阵中的值全部赋为0;0表示两个顶点没有连通,不等于0表示连通;
		for (int i = 0; i < arcs.length; i++) {
			for (int j = 0; j < arcs.length; j++) {
				arcs[i][j] = 0;
			}
		}
	}
	
	//设置顶点集;
	public void setVertices(String[] vertices) {
		this.vertices = vertices;
	}
	//设置状态集;
	public void setVisted(boolean[] visted) {
		this.visted = visted;
	}
	
	/**
	 * 设置边信息;
	 * @param x
	 * @param y
	 */
	public void addEdge(int x ,int y) {
		//如果两个顶点为同一顶点,则什么也不做;
		if(x==y) {
			return ;
		}
		//不为同一顶点,就将邻接矩阵中的值设置为1;
		arcs[x][y] = 1;
		arcs[y][x] = 1;
	}
	
	/**
	 *	打印访问的节点;
	 * @param x
	 */
	public void visit(int x) {
		System.out.print("  "+vertices[x]);
	}
	
	/**
	 * 以x为起始点深度优先搜索;
	 * @param x
	 */
	public void traverse(int x ) {
		//标记当前顶点的状态为true;
		visted[x] = true;
		//打印下当前顶点;
		visit(x);
		//寻找与x相连的顶点
		for (int i = 0; i < verticeNums; i++) {
			//如果x顶点和i顶点有边并且i还没有被访问过时,那么递归搜索它;
			if(arcs[x][i]!=0&&visted[i]==false) {
				traverse(i);
			}
		}
	}
	
	/**
	 * 遍历整个图;
	 */
	public void DFSTraverse() {
		//初始化一下顶点的状态;
		for (int i = 0; i < verticeNums; i++) {
			visted[i] = false;
		}
		//依次访问每个没有被访问过顶点;
		for (int i = 0; i < verticeNums; i++) {
			//事实上,如果图是一个相通图,那么只会深度搜索一次;因为一次搜索过后,所有顶点状态都为true;
			if(visted[i]==false) {
				traverse(i);
			}
		}
	}
	
	/**
	 * 非递归实现;
	 */
	public void DFSTraverse2() {
		//初始化所有顶点的状态;
		for (int i = 0; i < verticeNums; i++) {
			visted[i] = false;
		}
		//创建一个中间栈;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < verticeNums; i++) {
			//判断是否被访问过;
			if(visted[i]==false) {
				//将该顶点压入栈中;
				stack.push(i);
				//每次从栈中取一个顶点进行访问;栈为空时说明,该节点所在的连通子图中的顶点都被访问完毕;
				do {
					Integer vertice = stack.pop();
					//判断栈顶元素是否访问过;因为无法判断栈中元素是否被访问过;
					if(visted[vertice]==false) {
						visted[vertice] = true;
						visit(vertice);
						//寻找与栈顶元素相连并且没有被访问过的顶点;
						for (int j = 0; j < verticeNums; j++) {
							if(arcs[vertice][j]!=0&&visted[j]==false) {
								//压入栈中
								stack.push(j);
							}
						}
					}
				}while(!stack.isEmpty());
			}
		}
	}
	
	public static void main(String[] args) {
		DepthFirstSearch g = new DepthFirstSearch(9);
		String[] vertices = {"A","B","C","D","E","F","G","H","I"};
		g.setVertices(vertices);
		
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(1, 0);
		g.addEdge(1, 2);
		g.addEdge(1, 6);
		g.addEdge(1, 8);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(2, 8);
		g.addEdge(3, 2);
		g.addEdge(3, 4);
		g.addEdge(3, 6);
		g.addEdge(3, 7);
		g.addEdge(3, 8);
		g.addEdge(4, 3);
		g.addEdge(4, 5);
		g.addEdge(4, 7);
		g.addEdge(5, 0);
		g.addEdge(5, 4);
		g.addEdge(5, 6);
		g.addEdge(6, 1);
		g.addEdge(6, 3);
		g.addEdge(6, 5);
		g.addEdge(6, 7);
		g.addEdge(7, 3);
		g.addEdge(7, 4);
		g.addEdge(7, 6);
		g.addEdge(8, 1);
		g.addEdge(8, 2);
		g.addEdge(8, 3);
		
		System.out.println("深度优先搜索(递归实现)");
		g.DFSTraverse();
		System.out.println("");
		System.out.println("深度优先搜索(非递归实现)");
		g.DFSTraverse2();
		
	}
}
