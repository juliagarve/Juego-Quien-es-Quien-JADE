# Juego Quién es Quién con Agentes JADE

Este proyecto implementa el juego Quién es Quién utilizando la plataforma JADE para la creación de agentes inteligentes. El juego consiste en adivinar el personaje famoso en el que el usuario está pensando haciendo una serie de preguntas que permitan eliminar opciones hasta llegar a una única respuesta.

## Descripción del Proyecto
Este proyecto se basa en la implementación de dos agentes:

1. **AgentCliente**: Este agente representa al usuario y será responsable de recibir las preguntas del juego y enviar las respuestas al agente Quién es Quién. Una vez recibida la predicción, el agente Cliente vuelve al paso inicial para adivinar otro personaje.

2. **AgentQuienEsQuien**: Este agente es el encargado de recibir las conexiones de los clientes y las respuestas, y enviar las preguntas correspondientes. Este agente debe manejar hasta 5 predicciones simultáneas sin problemas.

