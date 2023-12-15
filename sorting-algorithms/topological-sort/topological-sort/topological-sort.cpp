#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_NODES 6

// Structure to represent a node in the graph
struct Node {
    int data;
    struct Node* next;
};

// Structure to represent the graph
typedef struct Graph {
    int V;            // Number of vertices
    struct Node** adjList;  // Adjacency list
}Graph;

// Structure to represent a stack
struct Stack {
    int* items;
    int top;
    unsigned int capacity;
};

// Function to create a new node
struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

// Function to create a graph with V vertices
struct Graph* createGraph(int V) {
    struct Graph* graph = (struct Graph*)malloc(sizeof(struct Graph));
    graph->V = V;
    graph->adjList = (struct Node**)malloc(V * sizeof(struct Node*));

    // Initialize each adjacency list as empty by default
    for (int i = 0; i < V; ++i) {
        graph->adjList[i] = NULL;
    }

    return graph;
}

// Function to create a stack
struct Stack* createStack(unsigned int capacity) {
    struct Stack* stack = (struct Stack*)malloc(sizeof(struct Stack));
    stack->capacity = capacity;
    stack->top = -1;
    stack->items = (int*)malloc(capacity * sizeof(int));
    return stack;
}

// Utility function to check if the stack is empty
bool isEmpty(struct Stack* stack) {
    return stack->top == -1;
}

// Utility function to push an item to the stack
void push(struct Stack* stack, int item) {
    stack->items[++stack->top] = item;
}

// Utility function to pop an item from the stack
int pop(struct Stack* stack) {
    return stack->items[stack->top--];
}

// Depth-First Search (DFS) traversal using a stack
void DFS(struct Graph* graph, int startVertex) {
    struct Stack* stack = createStack(graph->V);
    bool* visited = (bool*)malloc(graph->V * sizeof(bool));

    for (int i = 0; i < graph->V; ++i) {
        visited[i] = false;
    }

    push(stack, startVertex);

    while (!isEmpty(stack)) {
        int currentVertex = pop(stack);

        if (!visited[currentVertex]) {
            printf("%d ", currentVertex);
            visited[currentVertex] = true;
        }

        struct Node* current = graph->adjList[currentVertex];
        while (current) {
            int adjacentVertex = current->data;
            if (!visited[adjacentVertex]) {
                push(stack, adjacentVertex);
            }
            current = current->next;
        }
    }

    free(visited);
    free(stack->items);
    free(stack);
}

// Function to add an edge to the graph
void addEdge(struct Graph* graph, int src, int dest) {
    // Create a new node for the destination vertex
    struct Node* newNode = createNode(dest);
    newNode->next = graph->adjList[src]; // Add the node to the adjacency list of the source vertex
    graph->adjList[src] = newNode;
}

// Function to print the graph
void printGraph(struct Graph* graph) {
    for (int i = 0; i < graph->V; ++i) {
        struct Node* current = graph->adjList[i];
        printf("Adjacency list of vertex %d: ", i);
        while (current) {
            printf("%d -> ", current->data);
            current = current->next;
        }
        printf("NULL\n");
    }
}

// DFS algorithm implementaion used to traverse the adjacent nodes
void dfs(Graph* g, int vertex, struct Stack* stk, int visited[])
{
    visited[vertex] = 1;
    struct Node* current = g->adjList[vertex];
    while (current)
    {
        if (!visited[current->data])
            dfs(g, current->data, stk, visited);
        current = current->next;
    }
    push(stk, vertex);
}

// Topological sort algorithm implementation
void topological_sort(Graph* g)
{
    int visited[6];
    for (int i = 0; i < 6; i++)
    {
        visited[i] = 0;
    }
    struct Stack* stk = createStack(6);
    for (int i = 0; i < 6; i++)
    {
        if (!visited[i])
        {
            dfs(g, i, stk, visited);
        }
    }

    while (!isEmpty(stk)) {
        printf("%d  ", pop(stk));
    }
}

int main() {
    // Create a graph with 6 vertices
    int V = 6;
    struct Graph* graph = createGraph(V);

    addEdge(graph, 5, 2);
    addEdge(graph, 5, 0);
    addEdge(graph, 4, 0);
    addEdge(graph, 4, 1);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 1);

    // Print the graph
    printGraph(graph);

    printf("\n\nTopological Sort output: ");
    topological_sort(graph);

    return 0;
}
