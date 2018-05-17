package com.algorithms.graph;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图的广度优先搜索;
 * @author FuYou
 *
 */
public class BreadthFirstSearch {
	// 存贮顶点信息;
		private String[] vertices;

		// 存储边信息;这里使用邻接矩阵的方式实现;
		private int[][] arcs;

		// 图的定点数;
		private int verticeNums;

		// 顶点的访问状态;使用是一个数组表示;
		private boolean[] visted;

		// 初始化图信息;
		public BreadthFirstSearch(int verticeNums) {
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
		 * 图的广度优先搜索;
		 */
		public void breadthFirstSearch() {
			//将所有顶点的状态置为false;
			for (int i = 0; i < verticeNums; i++) {
				visted[i]=false;
			}
			//思路：先搜索未被访问的邻接点并将他们放入队列中;然后出队列搜索邻接点的邻接点,并入队;直到队列为空,广度优先搜索完毕;
			for (int i = 0; i < verticeNums; i++) {
				//判断是否被访问过;
				if(visted[i]==false) {
					//新建一个队列;
					Queue<Integer>	queue = new LinkedBlockingQueue<Integer>();
					
					//入队;
					queue.add(i);
					
					//队列不为空,则一直搜寻下去;
					do {
						Integer poll = queue.poll();
						if(visted[poll]==false) {
							//打印
							visit(poll);
							//将顶点置为true;
							visted[poll] = true;
							//搜寻与它邻接的顶点;
							for (int j = 0; j < verticeNums; j++) {
								if(arcs[poll][j]!=0&&visted[j]==false) {
									queue.add(j);
								}
							}
						}
						
					}while(!queue.isEmpty());
				}
			}
			
		}
		
		public static void main(String[] args) {
			BreadthFirstSearch g = new BreadthFirstSearch(9);
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
			
			System.out.println("广度优先搜索");
			g.breadthFirstSearch();
			System.out.println("");
			
		}
}
