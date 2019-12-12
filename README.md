# EstrategiasAlgoritmicas
El Problema del trío de puntos más cercanos. Práctica 1 Algorítmica y modelos de computación.

Para la implementación de la interfaz se ha creado una clase Menú que hereda de JFrame y permite crear la ventana principal donde se ha añadido un objeto JFreeChart para la representación gráfica de los puntos y grafos. El menú se compone de los siguientes paneles principales:
-	**Datos**: se usará para crear el conjunto de puntos que usaremos.
  - Aquí se podrán añadir manualmente los puntos indicando sus coordenadas, generando los puntos aleatorios en el rango y cantidad deseada, o bien cargar un dataset de un fichero .tsp, o guardar el dataset creado en un fichero. Además, se ha facilitado un selector de ficheros predeterminados incluyendo el caso mejor y peor.

-	**Divide y Vencerás**: se usará para resolver el problema del triángulo más pequeño.
  - Se podrán ejecutar los algoritmos Exhaustivo y Divide y Vencerás, obteniendo los puntos que forman el triángulo solución, su perímetro además del tiempo que ha tardado en dar la solución
  - También se podrá marcar la casilla para implementar la mejora anteriormente comenta del DyV.
  - Además, se ha añadido una pestaña que permite seleccionar las etiquetas que se muestran encima de los puntos, para ello se ha implementado la clase generadorEtiquetas en el paquete Común.

-	Greedy: se usará para resolver el problema del árbol de recubrimiento mínimo
  - Se podrá elegir si se desea aplicar el algoritmo de Prim o Kruskal directamente
  - Se muestra el tiempo y coste total de la solución, al igual que el conjunto de aristas que forman el árbol de recubrimiento mínimo tras resolver alguno de los algoritmos.
  - De forma opcional, se puede ver la representación gráfica de cómo se genera el grafo, que por defecto conecta cada vértice con el resto de los vértices. Es un grafo muy conexo de v-1 aristas. Como este proceso se puede demorar bastante, a partir de más de 40 vértices aparecerá una pantalla de carga explicando esta situación.
  - De igual forma, se podrá cambiar el modo de representación de las etiquetas, y se recomienda elegir el formato “Vi” para ver el camino del árbol de recubrimiento mínimo de forma más intuitiva.
  - Si se marca la casilla “Imprimir desarrollo” se podrá imprimirá en la consola el desarrollo explicado del algoritmo de Kruskal tras clicar en su botón.
  - Por último, se podrá guardar la solución en un fichero .tsp clicando en el botón “Generar Fichero”. Para ello y la gestión de la lectura/escritura de los archivos se ha implementado la clase “Fichero.java”.

