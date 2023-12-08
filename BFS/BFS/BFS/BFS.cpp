#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define NUM_VERTICES 8

typedef struct graph
{
	int vertices;
	bool adjMatrix[NUM_VERTICES][NUM_VERTICES];
}graph;

graph* createGraph(int vertices)
{
	graph* g = (graph*)malloc(sizeof(graph));
	g->vertices = vertices;
	for (int i = 0; i < vertices; i++)
	{
		for (int j = 0; j < vertices; j++)
		{
			g->adjMatrix[i][j] = false;
		}
	}
	return g;
}

int* bfs(graph* g, int vertex)
{
	int bfs[NUM_VERTICES];
	int index = -1;
	bool visited[NUM_VERTICES];
	for (int i = 0; i < NUM_VERTICES; i++)
	{
		visited[i] = false;
	}

	int queue[NUM_VERTICES];
	int front = -1, rear = -1;

	queue[++rear] = vertex;
	visited[vertex] = true;

	while (front != rear)
	{
		//dequeue
		int dequeued = queue[++front];
		bfs[++index] = dequeued;
		printf("%d ", dequeued);

		for (int i = 0; i < NUM_VERTICES; i++)
		{
			if (g->adjMatrix[dequeued][i] != false && visited[i] == false)
			{
				visited[i] = true;
				queue[++rear] = i;
			}
		}

	}

	return bfs;
}


void addEdge(graph* g, int src, int des)
{
	g->adjMatrix[src][des] = true;
	g->adjMatrix[des][src] = true;
}

void printGraph(graph* g) {
	printf("Graph Visualization:\n\n");
	printf("  ");
	for (int i = 0; i < g->vertices; i++) {
		printf("%d ", i);
	}
	printf("\n");

	for (int i = 0; i < g->vertices; i++) {
		printf("%d ", i);
		for (int j = 0; j < g->vertices; j++) {
			if (g->adjMatrix[i][j]) {
				printf("1 ");
			}
			else {
				printf("0 ");
			}
		}
		printf("\n");
	}
}

int main()
{
	graph* g = createGraph(NUM_VERTICES);
	addEdge(g, 0, 3);
	addEdge(g, 1, 3);
	addEdge(g, 2, 3);
	addEdge(g, 0, 4);
	addEdge(g, 4, 5);
	addEdge(g, 5, 2);
	addEdge(g, 2, 6);

	printGraph(g);

	for (int i = 0; i < NUM_VERTICES; i++)
	{
		printf("\nBFS from vertex %d:", i);
		printf("\n");
		bfs(g, i);
		printf("\n");
	}

	return 0;
}