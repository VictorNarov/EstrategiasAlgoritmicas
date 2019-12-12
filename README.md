# EstrategiasAlgoritmicas
Aplicación para resolver el Problema del trío de puntos más cercanos mediante algoritmos Exhaustivo e implementando la técnica de Divide y Vencerás, así como el árbol de recubrimiento mínimo (Minimum spanning tree) implementando algoritmos voraces: Kruskal y Prim.

Para la implementación de la interfaz se ha creado una clase Menú que hereda de JFrame y permite crear la ventana principal donde se ha añadido un objeto JFreeChart para la representación gráfica de los puntos y grafos. El menú se compone de los siguientes paneles principales:

-	**Datos**: se usará para crear el conjunto de puntos que usaremos.
    - Aquí se podrán añadir manualmente los puntos indicando sus coordenadas, generando los puntos aleatorios en el rango y cantidad deseada, o bien cargar un dataset de un fichero .tsp, o guardar el dataset creado en un fichero. Además, se ha facilitado un selector de ficheros predeterminados incluyendo el caso mejor y peor.
![Captura del panel Datos](https://i.gyazo.com/9c1a629755290a3e955c05b050cfed87.png)

-	**Divide y Vencerás**: se usará para resolver el problema del triángulo más pequeño.
    - Se podrán ejecutar los algoritmos Exhaustivo y Divide y Vencerás, obteniendo los puntos que forman el triángulo solución, su perímetro además del tiempo que ha tardado en dar la solución
    - También se podrá marcar la casilla para implementar la mejora anteriormente comenta del DyV.
    - Además, se ha añadido una pestaña que permite seleccionar las etiquetas que se muestran encima de los puntos, para ello se ha implementado la clase generadorEtiquetas en el paquete Común.
![Captura del panel DyV](https://i.gyazo.com/51501735bd74e700d8ee278a3a342501.png)

-	**Greedy**: se usará para resolver el problema del árbol de recubrimiento mínimo.
    - Se podrá elegir si se desea aplicar el algoritmo de Prim o Kruskal directamente
    - Se muestra el tiempo y coste total de la solución, al igual que el conjunto de aristas que forman el árbol de recubrimiento mínimo tras resolver alguno de los algoritmos.
    - De forma opcional, se puede ver la representación gráfica de cómo se genera el grafo, que por defecto conecta cada vértice con el resto de los vértices. Es un grafo muy conexo de v-1 aristas. Como este proceso se puede demorar bastante, a partir de más de 40 vértices aparecerá una pantalla de carga explicando esta situación.
    - De igual forma, se podrá cambiar el modo de representación de las etiquetas, y se recomienda elegir el formato “Vi” para ver el camino del árbol de recubrimiento mínimo de forma más intuitiva.
    - Si se marca la casilla “Imprimir desarrollo” se podrá imprimirá en la consola el desarrollo explicado del algoritmo de Kruskal tras clicar en su botón.
    - Por último, se podrá guardar la solución en un fichero .tsp clicando en el botón “Generar Fichero”. Para ello y la gestión de la lectura/escritura de los archivos se ha implementado la clase “Fichero.java”.
![Captura del panel Greedy](https://i.gyazo.com/0a401df926b928bc399aec0b2cb9a837.png)    


## Comparación modelo teórico-experimental Problema del trío de puntos

![Comparación modelo teórico-experimental Problema del trío de puntos](https://gyazo.com/c1e2099df7465b049e37d2f5abb53ae9.png)
###### Representación gráfica
![Representación gráfica](https://gyazo.com/621cd6b9bbb53d7831669e26334f6ab2.png)


## Pseudocódigos de los algoritmos

![Pseudocódigo algoritmo Exhaustivo](https://gyazo.com/d06230874f62e796cc0ebae2fc198cbd.png)


![Pseudocódigo algoritmo Divide y Vencerás](https://i.gyazo.com/496cbeefbcb5b2c46ad89f3fab6818b9.png)


![Pseudocódigo algoritmo de Prim](https://gyazo.com/783224c3c39a7bf15f5d3f38792cbce3.png)


![Pseudocódigo algoritmo de Kruskal](https://gyazo.com/4a0af618218d0c82fbf5aef6b3802268.png)

## Caso mejor y peor del DyV

###### Caso mejor
Para el caso mejor, generamos 10 puntos equidistantes a 1 unidad de distancia, excepto los 3 primeros, que se encontrarán a la izquierda del todo formando el triángulo de menor perímetro. De esta forma, al aplicar el algoritmo de Divide y vencerás:
-	Se dividirá por el pivote mitad (V5) 
-	Se resolverá el subproblema izquierdo, que al ser menos de 6 puntos llamará a un exhaustivo de talla 5.
-	De la misma forma, se resolverá el subproblema derecho con otro exhaustivo.
-	Se combinarán las soluciones y se quedará con la solución del subproblema izquierdo, que es el triángulo óptimo.
-	A continuación, intentará obtener los índices para realizar una búsqueda en la zona central de puntos, pero al no encontrar suficientes puntos a la distancia del perímetro de la solución, no podrá realizar la llamada al exhaustivo central.
De esta forma, nos ahorramos una llamada a este algoritmo y tenemos el caso mejor de Divide y Vencerás.

![Caso mejor DyV](https://gyazo.com/5be01562ce714c6b2012de2b8b59a75d.png)

###### Caso peor
Para forzar el caso peor generamos unos 3000 puntos todos alineados en la misma coordenada X=50 y equidistantes a 1 unidad de distancia. El triángulo solución lo colocamos en la derecha del plano, de forma que su vértice central esté a distancia 0.5 unidades y el perímetro sea 2 unidades.
Esta situación hará que el algoritmo divide y vencerás sea extremadamente ineficiente, ya que hará repetidas llamadas a exhaustivo al no encontrar la solución en los subproblemas izquierdo y derecho, ya que según la ordenación por heapsort según la coordenada X, la solución estará en las últimas posiciones del array ordenado y tendrá que llamar a exhaustivo numerosas veces hasta llegar ahí.

![Caso peor DyV](https://gyazo.com/c25120b6dabd66cbf48b51d82a72916e.png)
Las soluciones obtenidas en costo de tiempo son las siguiente:
Como podemos apreciar, el algoritmo exhaustivo llega a ser incluso más rápido.
![Soluciones](https://gyazo.com/15acf7989149f7a8f667078ed71396e3.png)

Para arreglar esta situación hemos implementado una mejora para que el DyV sea capaz de dividir por el Eje Y.
Para ello, antes de la ordenación de los puntos por heapsort, llamamos a una función estática de la clase “Desviación” la cual calcula la desviación estándar de los puntos con respecto a la media de la coordenada X. 
Si el valor obtenido es muy bajo (<25) nos indica que los puntos tienden a estar agrupados de forma muy cercana a la media de las X, como es este caso peor, en el que todos los puntos están en la misma X y la desviación estándar obtenida es 0.
Si se da esta situación, se procederá a llamar al heapsort con un parámetro booleano que le indicará que tiene que ordenar con respecto a la coordenada Y en vez de como hacía por defecto, la X.
Mismamente, el DyV recibirá otro parámetro que le indicará que tiene que calcular los índices según la coordenada Y.
Gracias a esta mejora, el DyV obtiene la solución como si de un caso medio se tratase: 
![Solucion con mejora](https://gyazo.com/d23a1bd538e26846b7c304e2fcba106c.png)
