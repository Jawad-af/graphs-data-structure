


//Student: Jawad Abo Fakher
//Group: 30411 - English Section



#include <stdio.h>
#include <stdlib.h>

#include "Profiler.h"
#define MAX_SIZE 10000
#define STEP_SIZE 100
#define NUMBER_OF_EDGES 20 


// A VISUAL REPRESENTATION OF THE GRAPH USED FOR THE DEMO IMPLEMENTATION OF THE KRUSKAL ALGORITHM  
// The node is repersented this way: |node|

/*


    ---------------11---------------------------|-------------------7---------|7|
    |                                           |                              |
    |                                           |                              |
    |                                           |                              1
    |                                           |                              |
    |     |---|14|-----1-------|13|            |8|----------------6-----------|6|
    |     |                      |              |                              |
    |     |                      7              2                              |
    |     |                      |              |                              |
    |     |                      |              |                              |
   |12|   |      |0|-----4------|1|----8------|2|--------------4--------\      |
    |     |                                    |                         \     2
    |     |                                    |                          \    |
    4     |---23-----|                         7                           \   |
    |                |                         |                            \  |
    |                |                         |                             \ |
   |11|-----1------|10|-----8-----|9|---5-----|3|-----9-----|4|------10-------|5|
                                               |                               |
                                               |---------------14--------------|

*/


/*

For the kruskal Algorithm implementation with disjoint sets, three primary data structures 
were employed: "InputEdges", "Edge", and "Set". 
Initially, each vertex was transformed into a separate set. Subsequently, the edges were organized 
in ascending order using the bottom-up heap method. each edge was 
examined, and a check was performed to ascertain whether the respective vertices belonged to the same
set. If the vertices were part of the same set. if the vertices were from different sets, the edge was 
considered valid, and the two vertices were linked within the same set. if not we simply continue traversing.

The time complexity of each operation on disjoint sets is O(n).

*/

Profiler profiler("kruskalAlgorithm alg. - Disjoint Sets");
int makeSetOperations, findSetOperations, linkSetOperations;

typedef struct Set
{
    int value;
    int rank;
    struct Set* parent;
}Set;

typedef struct Edge
{
    int weight;
    Set* u;
    Set* v;
}Edge;

typedef struct InputEdges
{
    int weight;
    int u;
    int v;
}InputEdges;

Set* makeSet(int value)
{
    makeSetOperations += 3;
    Set* set = (Set*)malloc(sizeof(Set));
    set->value = value;
    set->rank = 0;
    set->parent = set;

    return set;
}

// finds the set that contains the elements set1 using path compression
Set* findSet(Set* set)
{
    findSetOperations++;
    if (set != set->parent)
    {
        findSetOperations++;
        set->parent = findSet(set->parent);
    }
    return set->parent;
}

// performs a union for the given two sets based on their ranks
void link(Set* set1, Set* set2)
{
    linkSetOperations++;
    if (set1->rank > set2->rank)
    {
        linkSetOperations++;
        set2->parent = set1;
    }
    else if(set1->rank < set2->rank)
    {
        linkSetOperations++;
        set1->parent = set2;
    }
    else
    {
        linkSetOperations += 2;
        set2->parent = set1;
        set1->rank = set1->rank + 1;
    }
}

// performs the union of two sets
void unionSet(Set* set1, Set* set2)
{
    link(findSet(set1), findSet(set2));
}


void buttomUpHeapify(Edge* MSTArray[4 * MAX_SIZE], int i, int n)
{
    int largest = i;
    if (2 * i + 1 < n && MSTArray[largest]->weight < MSTArray[2 * i + 1]->weight)
        largest = 2 * i + 1;
    if (2 * i + 2 < n && MSTArray[largest]->weight < MSTArray[2 * i + 2]->weight)
        largest = 2 * i + 2;

    if (largest != i)
    {
        Edge* temp = (Edge*)malloc(sizeof(Edge));
        temp = MSTArray[i];
        MSTArray[i] = MSTArray[largest];
        MSTArray[largest] = temp;
        buttomUpHeapify(MSTArray, largest, n);
    }

}

void buildHeap(Edge* MSTArray[4 * MAX_SIZE], int n)
{
    for (int i = (n / 2) - 1; i >= 0; i--)
        buttomUpHeapify(MSTArray, i, n);
}

void heapSort(Edge* MSTArray[4 * MAX_SIZE], int n)
{

    buildHeap(MSTArray, n);
    int heapSize = n;
    for (int i = n - 1; i > 0; i--)
    {
        Edge* temp = (Edge*)malloc(sizeof(Edge));
        temp = MSTArray[i];
        MSTArray[i] = MSTArray[0];
        MSTArray[0] = temp;
        heapSize--;
        buttomUpHeapify(MSTArray, 0, heapSize);
    }

}

// creates and sorts an array of edges in ascending order of weights 
int buildSortedEdges(InputEdges* inputEdges[4 * MAX_SIZE], Edge* edges[4 * MAX_SIZE], Set* vertices[MAX_SIZE], int n)
{
    int i, heapsize = 0;
    for (i = 0; i < n; i++)
    {
        Edge* edge = (Edge*)malloc(sizeof(Edge));
        edge->weight = inputEdges[i]->weight;
        edge->u = vertices[inputEdges[i]->u];
        edge->v = vertices[inputEdges[i]->v];
        edges[heapsize] = edge;
        heapsize++;
    }
    heapSort(edges, heapsize);
    return heapsize;
}

int kruskalAlgorithm(InputEdges* inputEdges[4 * MAX_SIZE], int n, Edge* MSTArray[MAX_SIZE])
{
    int size = 0;
    Set* vertices[MAX_SIZE];
    Edge* edges[4 * MAX_SIZE];
    for (int i = 0; i < 4 * n; i++)
    {
        Edge* dynamicEdge = (Edge*)malloc(sizeof(Edge));
        edges[i] = dynamicEdge;
    }
    for (int i = 0; i < n; i++)
    {
        vertices[i] = makeSet(i); //initialize each vertex as a standalone set
    }

    int heapsize = buildSortedEdges(inputEdges, edges, vertices, n);

    for (int i = 0; i < heapsize; i++)
        if (findSet(edges[i]->u) != findSet(edges[i]->v))
        {
            MSTArray[size++] = edges[i];  //adds the edge (u,v) to a
            unionSet(edges[i]->u, edges[i]->v);  // merges the vertices in the two trees
        }

    return size;
}


void generateRandomGraph(InputEdges* edges[4 * MAX_SIZE], int n)
{
    Set* vertices[MAX_SIZE];

    for (int i = 0; i < 4 * n; i++)
    {
        InputEdges* edge = (InputEdges*)malloc(sizeof(InputEdges));
        edges[i] = edge;
    }

    for (int i = 0; i < n; i++)
    {
        vertices[i] = makeSet(i);
    }

    int i = 0;

    while (i < 4 * n)
    {
        edges[i]->weight = rand();
        edges[i]->v = rand() % n;
        edges[i]->u = rand() % n;
        i++;
    }

}

void demoKruskal()
{
    InputEdges* edges[4 * NUMBER_OF_EDGES];
    for (int i = 0; i < NUMBER_OF_EDGES; i++)
    {
        InputEdges* set1 = (InputEdges*)malloc(sizeof(InputEdges));
        edges[i] = set1;
    }

    Edge* MSTArray[NUMBER_OF_EDGES];
    for (int i = 0; i < NUMBER_OF_EDGES; i++)
    {
        Edge* set1 = (Edge*)malloc(sizeof(Edge));
        MSTArray[i] = set1;
    }

    edges[0]->weight = 4;    edges[1]->weight = 8;     edges[2]->weight = 7;     edges[3]->weight = 9;     edges[4]->weight = 10;
    edges[0]->u = 0;         edges[1]->u = 1;          edges[2]->u = 2;          edges[3]->u = 3;          edges[4]->u = 4;
    edges[0]->v = 1;         edges[1]->v = 2;          edges[2]->v = 3;          edges[3]->v = 4;          edges[4]->v = 5;

    edges[5]->weight = 2;    edges[6]->weight = 1;     edges[7]->weight = 7;     edges[8]->weight = 2;     edges[9]->weight = 4;
    edges[5]->u = 5;         edges[6]->u = 6;          edges[7]->u = 7;          edges[8]->u = 8;          edges[9]->u = 2;
    edges[5]->v = 6;         edges[6]->v = 7;          edges[7]->v = 8;          edges[8]->v = 2;          edges[9]->v = 5;

    edges[10]->weight = 6;   edges[11]->weight = 11;   edges[12]->weight = 14;   edges[13]->weight = 5;    edges[14]->weight = 8;
    edges[10]->u = 6;        edges[11]->u = 12;        edges[12]->u = 3;         edges[13]->u = 3;         edges[14]->u = 9;
    edges[10]->v = 8;        edges[11]->v = 7;         edges[12]->v = 5;         edges[13]->v = 9;         edges[14]->v = 10;

    edges[15]->weight = 23;  edges[16]->weight = 1;    edges[17]->weight = 4;    edges[18]->weight = 7;    edges[19]->weight = 1;
    edges[15]->u = 10;       edges[16]->u = 10;        edges[17]->u = 11;        edges[18]->u = 1;         edges[19]->u = 14;
    edges[15]->v = 14;       edges[16]->v = 11;        edges[17]->v = 12;        edges[18]->v = 13;        edges[19]->v = 13;


    /*

    ---------------11---------------------------|-------------------7---------|7|
    |                                           |                              |
    |                                           |                              |
    |                                           |                              |1
    |                                           |                              |
    |     |---|14|-----1-------|13|            |8|----------------6-----------|6|
    |     |                      |              |                              |
    |     |                     7|             2|                              |
    |     |                      |              |                              |
    |     |                      |              |                              |
   |12|   |      |0|-----4------|1|----8------|2|--------------4------ \       |
    |     |                                    |                        \      |2
    |     |                                    |                         \     |
   4|     |---23-----|                         |7                         \    |
    |                |                         |                           \   |
    |                |                         |                            \  |
   |11|-----1------|10|-----8-----|9|---5-----|3|-----9-----|4|------10-------|5|
                                               |                               |
                                               |---------------14--------------|

    */

    int cost = 0;
    int size = kruskalAlgorithm(edges, NUMBER_OF_EDGES, MSTArray);

    printf("\nDemo of Kruskal Algorithm: \n\n");
    printf("Set of the output edges sorted by weight:\n\n");
    for (int i = 0; i < size; i++)
    {
        printf("Vertex u: %d\tVertex v: %d\tWeight:%d\n", MSTArray[i]->u->value, MSTArray[i]->v->value, MSTArray[i]->weight);
        cost = cost + MSTArray[i]->weight;
    }
    printf("\nThe cost of the generated Minimum Spanning Tree = %d", cost);
    printf("\n\n------------------------------------------------------------------------\n\n");
}

void demoMakeSetAndUnionSet() {

    int n = 10;
    Set* vertices[MAX_SIZE];

    for (int i = 0; i < 9; i++)
    {
        vertices[i] = makeSet(i);
    }

    printf("\nDemo of the make set and union operations: \n\n");
    printf("\nSet of vertices generated of the Make Set operation: \n");
    for (int i = 0; i < 9; i++)
    {
        printf("\nvalue: %d rank: %d", vertices[i]->value, vertices[i]->rank);
    }

    unionSet(vertices[1], vertices[2]);
    unionSet(vertices[3], vertices[4]);
    unionSet(vertices[5], vertices[4]);
    unionSet(vertices[1], vertices[4]);
    unionSet(vertices[7], vertices[8]);

    printf("\n\nThe vertices AFTER PERFORMING A UNION BETWEEN\n(1,2) (3,4) (5,4) (1,4) (7,8): \n");
    for (int i = 0; i < 9; i++)
    {
        printf("\nvalue: %d rank: %d", vertices[i]->value, vertices[i]->rank);
    }
}

void performance()
{
    InputEdges* edges[4 * MAX_SIZE];
    for (int i = 0; i < MAX_SIZE; i++)
    {
        InputEdges* set1 = (InputEdges*)malloc(sizeof(InputEdges));
        edges[i] = set1;
    }

    Edge* MSTArray[MAX_SIZE];
    for (int i = 0; i < MAX_SIZE; i++)
    {
        Edge* set1 = (Edge*)malloc(sizeof(Edge));
        MSTArray[i] = set1;
    }

    for (int step = STEP_SIZE; step <= MAX_SIZE; step += STEP_SIZE)
    {
        printf("\n%d", step);
        generateRandomGraph(edges, step);

        findSetOperations = 0; linkSetOperations = 0; makeSetOperations = 0;

        kruskalAlgorithm(edges, step, MSTArray);

        profiler.countOperation("Make Set Operations", step, makeSetOperations);
        profiler.countOperation("Find Set Operations", step, findSetOperations);
        profiler.countOperation("Link Sets Operations", step, linkSetOperations);
    }

    profiler.createGroup("All Operations", "Make Set Operations", "Find Set Operations", "Link Sets Operations");
    profiler.showReport();
}

void multiDemo()
{
    demoKruskal();
    demoMakeSetAndUnionSet();
}

int main()
{
    multiDemo();
    performance();

    return 0;
}
